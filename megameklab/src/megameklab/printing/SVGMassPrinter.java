/*
 * Copyright (c) 2024-2025 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */

package megameklab.printing;

import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import megamek.client.ratgenerator.ModelRecord;
import megamek.client.ui.util.FluffImageHelper;
import megamek.codeUtilities.StringUtility;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.GunEmplacement;
import megamek.common.MekSummary;
import megamek.common.MekSummaryCache;
import megamek.common.UnitRole;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.UnitPrintManager;
import megameklab.util.UnitUtil;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.w3c.dom.Document;

import java.io.FileOutputStream;

public class SVGMassPrinter {
    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final String SHEETS_DIR = "sheets";
    private static final String VERSION_FILE = "version.json";
    private static final String UNIT_FILE = "units.json";
    private static final String ROOT_FOLDER = "svgexport";
    private static final int DEFAULT_MARGINS = 5; // Default margins for the page

    public static class UnitData {
        public int id; // Unique identifier for the unit on MUL
        public String n; // Name of the unit (Chassis)
        public String m; // Model of the unit
        public int y; // Year of introduction
        public int wc; // Weight class, e.g. 0 for Light, 1 for Medium, etc.
        public double t; // Weight in tons, rounded to the nearest integer
        public int bv; // Battle Value, rounded to the nearest integer
        public int pv; // Point Value, rounded to the nearest integer
        public long c; // Cost in C-Bills, rounded to the nearest integer
        public int l; // Tech Level
        public String ty; // Major type, e.g. "Mek", "Vehicle", etc.
        public String sub; // Subtype, e.g. "Assault", "Light", etc.
        public String src; // Source of the unit, e.g. "TRO 3050"
        public String rl; // Role, e.g. "Assault", "Scout", etc.
        public boolean cl; // true for Clan, false for Inner Sphere
        public int ar; // Total armor
        public int in; // Total internal structure
        public int h; // Total heat generation
        public int h2; // Heat capacity
        public int w; // Walk MP
        public int r; // Run MP
        public int j; // Jump MP
        public int s; // 1 for small units (Battle Armor, ProtoMek, Infantry), 0 for others
        public String sh; // Path to the SVG sheet

        public UnitData() {
        }

        public UnitData(MekSummary summary) {
            this.id = summary.getMulId();
            this.n = summary.getFullChassis();
            this.m = summary.getModel();
            this.y = summary.getYear();
            this.wc = summary.getWeightClass();
            this.t = summary.getTons();
            this.bv = summary.getBV();
            this.pv = summary.getPointValue();
            this.c = summary.getCost();
            this.ty = summary.getUnitType();
            this.sub = summary.getUnitSubType();
            this.cl = summary.isClan();
            this.w = summary.getWalkMp();
            this.r = summary.getRunMp();
            this.j = summary.getJumpMp();
        }

        public UnitData(Entity entity) {
            this.id = entity.getMulId();
            this.n = entity.getFullChassis();
            this.m = entity.getModel();
            this.y = entity.getYear();
            this.wc = entity.getWeightClass();
            this.t = entity.getWeight();
            this.bv = entity.getBvCalculator().calculateBV(false,true);
            this.c = Math.round(entity.getCost(false));
            this.l = entity.getStaticTechLevel().ordinal();
            this.ty = Entity.getEntityMajorTypeName(entity.getEntityType());
            this.sub = Entity.getEntityTypeName(entity.getEntityType());
            this.src = entity.getSource();
            UnitRole role = entity.getRole();
            if (role != UnitRole.UNDETERMINED) {
                this.rl = role.toString();
            } else {
                this.rl = "None";
            }
            this.cl = entity.isClan();
            this.ar = entity.getTotalOArmor();
            this.in = entity.getTotalInternal();
            this.h = UnitUtil.getTotalHeatGeneration(entity);
            this.h2 = entity.getHeatCapacity();
            this.w = entity.getWalkMP();
            this.r = entity.getRunMP();
            this.j = entity.getJumpMP();
        }
    }

    public static void main(String[] args) {
        logger.info("Starting SVG Mass Printer...");
        final String rootPath = ROOT_FOLDER + File.separator + SHEETS_DIR;
        File sheetsDir = new File(rootPath);

        if (sheetsDir.exists()) {
            try {
                Files.walk(sheetsDir.toPath())
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
                logger.info("Deleted existing sheets directory: {}", sheetsDir.getPath());
            } catch (IOException e) {
                logger.error("Failed to delete sheets directory: {}", e.getMessage());
            }
        }
        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            sheetsDir.mkdirs();
            logger.error("Sheets directory does not exist, creating it.");
        }

        RecordSheetOptions recordSheetOptions = new RecordSheetOptions();
        recordSheetOptions.setC3inBV(true);
        recordSheetOptions.setBoldType(true);
        recordSheetOptions.setHeatProfile(true);
        recordSheetOptions.setCondensedReferenceCharts(true);
        recordSheetOptions.setRole(true);
        recordSheetOptions.setEraIcon(true);
        recordSheetOptions.setColor(false);
        recordSheetOptions.colorLogo = true;
        
        HashSet<String> processedFiles = new HashSet<>();
        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.INDENT_OUTPUT);

        int i = 0;
        int processedCount = 0;
        MekSummary[] meks = cache.getAllMeks();
        logger.info("Processing {} meks...", meks.length);
        long timestamp = System.currentTimeMillis();
        
        try (FileWriter versionWriter = new FileWriter(ROOT_FOLDER + File.separator + VERSION_FILE)) {
            versionWriter.write("{\"version\":"+String.valueOf(timestamp)+"}");
            logger.info("Version file written: {}", timestamp);
        } catch (IOException e) {
            logger.error("Failed to write version file: {}", e.getMessage());
        }
        
        PageFormat pf = new PageFormat();
        PaperSize paperDef = recordSheetOptions.getPaperSize();

        try (FileWriter jsonWriter = new FileWriter(ROOT_FOLDER + File.separator + UNIT_FILE)) {
            jsonWriter.write("{\"version\":"+ timestamp +",\n");
            jsonWriter.write("\"units\":[\n");
            boolean firstUnit = true;
            for (MekSummary mekSummary : meks) {
                // if (mekSummary.getUnitType() != "Mek") continue; // Skip non-Mek units
                i++;
                logger.info("{}: {}", i, mekSummary.getName());

                // if (i > 10) break; // For testing, remove this line in production
                /*
                 * if (!mekSummary.isProtoMek() && !mekSummary.isCombatVehicle()) {
                 * continue;
                 * }
                 *
                 * // 1 - uncomment this block and cycle all the start characters A-Z (only
                 * // uppercase)
                 * if (!mekSummary.getName().toUpperCase().startsWith("C")) {
                 * continue;
                 * }
                 *
                 * // 2 - uncomment this block, comment the above block, run once more
                 * if (mekSummary.getName().toUpperCase().charAt(0) <= 'Z' &&
                 * mekSummary.getName().toUpperCase().charAt(0) >= 'A') {
                 * continue;
                 * }
                 *
                 */
                Entity entity = mekSummary.loadEntity();
                if ((entity == null) || (entity instanceof GunEmplacement)) {
                    logger.info("Skipping: {}", mekSummary.getName());
                    System.gc();
                    continue;
                }
                UnitUtil.updateLoadedUnit(entity);
                String svgPath = FluffImageHelper.getFluffPath(entity).toLowerCase();
                File sheetPath = new File(sheetsDir.getPath(), svgPath);

                if (!sheetPath.exists() && !sheetPath.mkdirs()) {
                    logger.error("Couldn't create folder {}", sheetPath);
                    System.exit(1);
                }

                String svgFilename = generateFilename(mekSummary);
                File finalFilename = new File(sheetPath, svgFilename);

                if (processedFiles.contains(finalFilename.getPath())) {
                    logger.warn("Duplication detected! File already exists: {}", finalFilename.getPath());
                    continue;
                }
                processedFiles.add(finalFilename.getPath());
                boolean isSmallUnit = entity.isBattleArmor() || entity.isProtoMek() || entity.isInfantry();
                try {
                    // List<Entity> units = printableListOfUnits(entity);
                    List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(List.of(entity), true, recordSheetOptions);
                    if (sheets.isEmpty()) {
                        logger.error("No sheets generated for {}", mekSummary.getName());
                        System.exit(1);
                    }
                    int pageIndex = 1;
                    for (PrintRecordSheet sheet : sheets) {
                        if (sheet instanceof PrintSmallUnitSheet) {
                            pf.setPaper(paperDef.createPaper());
                        } else {
                            pf.setPaper(paperDef.createPaper(DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS));
                        }
                        sheet.createDocument(pageIndex, pf, false);
                        pageIndex++;
                    }
                    List<Document> svgDocs = sheets.stream()
                        .map(PrintRecordSheet::getSVGDocument)
                        .filter(doc -> doc != null)
                        .collect(Collectors.toList());
                    if (svgDocs.isEmpty()) {
                        logger.error("No SVG documents for {}", mekSummary.getName());
                        System.exit(1);
                    }
                    Document combinedSvg;
                    if (svgDocs.size() == 1) {
                        // Single sheet - use as is
                        combinedSvg = svgDocs.get(0);
                    } else {
                        // Multiple sheets - combine side by side
                        combinedSvg = combineSVGDocuments(svgDocs);
                    }
                    
                    // Save the combined SVG
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "no");
                    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                    
                    DOMSource source = new DOMSource(combinedSvg);
                    StreamResult result = new StreamResult(new FileOutputStream(finalFilename));
                    transformer.transform(source, result);
                    
                    logger.info("Printed: {}", finalFilename);
                } catch (Exception e) {
                    logger.error(e, "Printing Error");
                    System.exit(1);
                }

                UnitData unitData = new UnitData(entity);
                // Set additional fields
                unitData.sh = (svgPath + File.separator + svgFilename).replace("\\", "/");
                unitData.pv = mekSummary.getPointValue();
                unitData.s = isSmallUnit ? 1 : 0; // 1 for small units, 0 for others

                String jsonLine = mapper.writeValueAsString(unitData);
                if (!firstUnit) {
                    jsonWriter.write(",\n");
                }
                jsonWriter.write(jsonLine);

                firstUnit = false;
                processedCount++;
                System.gc();
            }
            jsonWriter.write("\n]}");
        } catch (IOException e) {
            logger.error("Failed to write JSON Lines file: {}", e.getMessage());
        }

        logger.info("Done. Processed {} units.", processedCount);
        System.exit(0);
    }

    /**
     * Combines multiple SVG documents side by side into a single SVG
     * Assumes Letter size (612x792 points) for each sheet
     */
    private static Document combineSVGDocuments(List<Document> svgDocs) throws Exception {
        if (svgDocs.isEmpty()) {
            throw new IllegalArgumentException("No SVG documents to combine");
        }
        Document firstDoc = svgDocs.get(0);
        Element firstRoot = firstDoc.getDocumentElement();
        double sheetWidth = getSheetWidth(firstRoot);
        double sheetHeight = getSheetHeight(firstRoot);
        logger.debug("Detected sheet dimensions: {}x{}", sheetWidth, sheetHeight);
    
        // Create new SVG document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document combinedDoc = builder.newDocument();
        
        // Create root SVG element
        Element svgRoot = combinedDoc.createElementNS("http://www.w3.org/2000/svg", "svg");
        svgRoot.setAttribute("version", "1.1");
        svgRoot.setAttribute("xmlns", "http://www.w3.org/2000/svg");
        svgRoot.setAttribute("xmlns:xlink", "http://www.w3.org/1999/xlink");
        
        // Set combined dimensions (width = number of sheets * sheet width)
        double totalWidth = svgDocs.size() * sheetWidth;
        svgRoot.setAttribute("width", String.valueOf(totalWidth));
        svgRoot.setAttribute("height", String.valueOf(sheetHeight));
        svgRoot.setAttribute("viewBox", String.format("0 0 %.1f %.1f", totalWidth, sheetHeight));
        
        combinedDoc.appendChild(svgRoot);
        
        // Add each sheet side by side
        for (int i = 0; i < svgDocs.size(); i++) {
            Document sourceDoc = svgDocs.get(i);
            Element sourceRoot = sourceDoc.getDocumentElement();
            
            // Create a group for this sheet with translation
            Element group = combinedDoc.createElementNS("http://www.w3.org/2000/svg", "g");
            double xOffset = i * totalWidth;
            group.setAttribute("transform", String.format("translate(%.1f,0)", xOffset));
            
            // Copy all child elements from source SVG to the group
            copyChildElements(sourceRoot, group, combinedDoc);
            
            svgRoot.appendChild(group);
        }
        
        return combinedDoc;
    }


    /**
     * Extracts the width from an SVG root element
     */
    private static double getSheetWidth(Element svgRoot) {
        String widthAttr = svgRoot.getAttribute("width");
        if (widthAttr != null && !widthAttr.isEmpty()) {
            // Remove units (pt, px, etc.) and parse
            String numericWidth = widthAttr.replaceAll("[^0-9.]", "");
            try {
                return Double.parseDouble(numericWidth);
            } catch (NumberFormatException e) {
                logger.warn("Could not parse width '{}', using viewBox", widthAttr);
            }
        }
        
        // Fallback to viewBox width
        String viewBox = svgRoot.getAttribute("viewBox");
        if (viewBox != null && !viewBox.isEmpty()) {
            String[] parts = viewBox.split("\\s+");
            if (parts.length >= 3) {
                try {
                    return Double.parseDouble(parts[2]);
                } catch (NumberFormatException e) {
                    logger.warn("Could not parse viewBox width from '{}'", viewBox);
                }
            }
        }
        
        // Final fallback to Letter size width
        logger.warn("Could not determine SVG width, using default 612pt");
        return 612.0;
    }

    /**
     * Extracts the height from an SVG root element
     */
    private static double getSheetHeight(Element svgRoot) {
        String heightAttr = svgRoot.getAttribute("height");
        if (heightAttr != null && !heightAttr.isEmpty()) {
            // Remove units (pt, px, etc.) and parse
            String numericHeight = heightAttr.replaceAll("[^0-9.]", "");
            try {
                return Double.parseDouble(numericHeight);
            } catch (NumberFormatException e) {
                logger.warn("Could not parse height '{}', using viewBox", heightAttr);
            }
        }
        
        // Fallback to viewBox height
        String viewBox = svgRoot.getAttribute("viewBox");
        if (viewBox != null && !viewBox.isEmpty()) {
            String[] parts = viewBox.split("\\s+");
            if (parts.length >= 4) {
                try {
                    return Double.parseDouble(parts[3]);
                } catch (NumberFormatException e) {
                    logger.warn("Could not parse viewBox height from '{}'", viewBox);
                }
            }
        }
        
        // Final fallback to Letter size height
        logger.warn("Could not determine SVG height, using default 792pt");
        return 792.0;
    }


    /**
     * Recursively copies child elements from source to target
     */
    private static void copyChildElements(Element source, Element target, Document targetDoc) {
        NodeList children = source.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            Node importedNode = targetDoc.importNode(child, true);
            target.appendChild(importedNode);
        }
    }

    private static List<Entity> printableListOfUnits(Entity entity) {
        if (entity.isBattleArmor() || entity.isProtoMek()) {
            return List.of(entity, entity, entity, entity, entity);
        } else {
            return List.of(entity);
        }
    }

    private static String generateFilename(MekSummary unit) {
        String fileName = String.format("%s_%s.svg",
                sanitize(unit.getChassis()),
                sanitize(unit.getModel()))
                  .replace(" ", "_")
                  .replace("\"", "")
                  .replace("/", "")
                  .replace("(", "_")
                  .replace(")", "")
                  .replace("[", "_")
                  .replace("]", "")
                  .replaceAll("_+", "_")
                .toLowerCase();
        return fileName;
    }

    private static String sanitize(String original) {
        return original.replace("\"", "").replace("/", "");
    }

    private SVGMassPrinter() {
        throw new IllegalStateException();
    }
}
