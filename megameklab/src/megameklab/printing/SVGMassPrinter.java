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


import megamek.client.ratgenerator.RATGenerator;
import megamek.client.ui.util.FluffImageHelper;
import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.GunEmplacement;
import megamek.common.MekSummary;
import megamek.common.MekSummaryCache;
import megamek.common.UnitRole;
import megamek.common.alphaStrike.ASUnitType;
import megamek.common.enums.WeaponSortOrder;
import megamek.common.util.C3Util;
import megamek.logging.MMLogger;
import megameklab.MMLOptions;
import megameklab.util.CConfig;
import megameklab.util.SVGOptimizer;
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
import org.w3c.dom.svg.SVGDocument;

import java.io.FileOutputStream;

/**
 * @author drake
 * Generates SVG sheets for all units in the Mek Summary Cache and saves them
 */
public class SVGMassPrinter {
    private static final MMLogger logger = MMLogger.create(SVGMassPrinter.class);
    private static final String SHEETS_DIR = "sheets";
    private static final String VERSION_FILE = "version.json";
    private static final String UNIT_FILE = "units.json";
    private static final String ROOT_FOLDER = "svgexport";
    private static final int DEFAULT_MARGINS = 5; // Default margins for the page
    private final static RATGenerator RAT_GENERATOR = RATGenerator.getInstance();

    public static class UnitData {
        public String name; // Unique name of the unit, used for deduplication
        public int id; // Unique identifier for the unit on MUL
        public String chassis; // Name of the unit (Chassis)
        public String model; // Model of the unit
        public int year; // Year of introduction
        public int weightClass; // Weight class, 0 for Light, 1 for Medium, etc.
        public double tons; // Weight in tons, rounded to the nearest integer
        public int bv; // Battle Value, rounded to the nearest integer
        public int pv; // Point Value, rounded to the nearest integer
        public long cost; // Cost in C-Bills, rounded to the nearest integer
        public int level; // Tech Level
        public String techBase;
        public String techRating;
        public String type; // Major type, "Mek", "Vehicle", etc.
        public String subtype; // Subtype, "Assault", "Light", etc.
        public String source; // Source of the unit, e.g. "TRO 3050"
        public String role; // Role, "Assault", "Scout", etc.
        public int armor; // Total armor
        public int internal; // Total internal structure
        public int heat; // Total heat generation
        public int dissipation; // Heat capacity
        public int walk; // Walk MP
        public int run; // Run MP
        public int jump; // Jump MP
        public int su; // 1 for small units (Battle Armor, ProtoMek, Infantry), 0 for others
        public List<String> sheets; // Path to the SVG sheet

        public UnitData(Entity entity) {
            this.id = entity.getMulId();
            this.chassis = entity.getFullChassis();
            this.model = entity.getModel();
            this.year = entity.getYear();
            this.weightClass = entity.getWeightClass();
            this.tons = entity.getWeight();
            this.bv = entity.getBvCalculator().calculateBV(false,true);
            this.cost = Math.round(entity.getCost(false));
            this.level = entity.getStaticTechLevel().ordinal();
            this.techBase = formatTechBase(entity);
            this.techRating = entity.getFullRatingName();
            this.type = Entity.getEntityMajorTypeName(entity.getEntityType());
            this.subtype = Entity.getEntityTypeName(entity.getEntityType());
            this.source = entity.getSource();
            this.role = formatRole(entity);
            this.armor = entity.getTotalOArmor();
            this.internal = entity.getTotalInternal();
            this.heat = UnitUtil.getTotalHeatGeneration(entity);
            this.dissipation = entity.getHeatCapacity();
            this.walk = entity.getWalkMP();
            this.run = entity.getRunMP();
            this.jump = entity.getJumpMP();
            this.sheets = new ArrayList<>();
        }

        private String formatTechBase(Entity entity) {
            if (entity.isMixedTech()) {
                return "Mixed";
            } else if (entity.isClan()) {
                return "Clan";
            } else {
                return "Inner Sphere";
            }
        }

        private String formatRole(Entity entity) {
            UnitRole role = entity.getRole();
            if (role != UnitRole.UNDETERMINED) {
                return role.toString();
            } else {
                return "None";
            }
        }
    }

    public static void main(String[] args) {
        logger.info("Starting SVG Mass Printer...");
        final String rootPath = ROOT_FOLDER + File.separator + SHEETS_DIR;
        File sheetsDir = new File(rootPath);

        if (sheetsDir.exists()) {
            try (var walk = Files.walk(sheetsDir.toPath())) {
                walk.sorted(Comparator.reverseOrder())
                      .map(Path::toFile)
                      .forEach(file -> {
                          if (!file.delete()) {
                              logger.warn("Failed to delete file: {}", file.getPath());
                          }
                      });
                logger.info("Deleted existing sheets directory: {}", sheetsDir.getPath());
            } catch (IOException e) {
                logger.error("Failed to delete sheets directory: {}", e.getMessage());
            }
        }
        if (!sheetsDir.exists() || !sheetsDir.isDirectory()) {
            if (!sheetsDir.mkdirs()) {
                logger.error("Failed to create sheets directory: {}", sheetsDir.getPath());
                System.exit(1);
            } else {
                logger.info("Sheets directory created: {}", sheetsDir.getPath());
            }
        }
        HashSet<String> processedFiles = new HashSet<>();
        Locale.setDefault(new MMLOptions().getLocale());
        EquipmentType.initializeTypes();
        CConfig.load();
        CConfig.setParam(CConfig.RS_FONT, "Roboto");
        RecordSheetOptions recordSheetOptions = getRecordSheetOptions();
        MekSummaryCache cache = MekSummaryCache.getInstance(true);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.INDENT_OUTPUT);

        int processedCount = 0;
        MekSummary[] meks = cache.getAllMeks();
        logger.info("Processing {} meks...", meks.length);
        long timestamp = System.currentTimeMillis();
        
        try (FileWriter versionWriter = new FileWriter(ROOT_FOLDER + File.separator + VERSION_FILE)) {
            versionWriter.write("{\"version\":"+timestamp+"}");
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
//                if (!mekSummary.getName().contains("Archangel")) {
//                    continue;
//                }
                // if (mekSummary.getUnitType() != "Mek") continue; // Skip non-Mek units
                // logger.info("{}", mekSummary.getName());

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
//                    logger.info("Skipping: {}", mekSummary.getName());
                    System.gc();
                    continue;
                }
                UnitUtil.updateLoadedUnit(entity);
                entity.getCrew().setName("", 0);
                if (entity.getId() == -1) {
                    entity.setId(entity.getGame().getNextEntityId());
                }
                C3Util.wireC3(entity.getGame(), entity);
                String svgPath = FluffImageHelper.getFluffPath(entity).toLowerCase();
                File sheetPath = new File(sheetsDir.getPath(), svgPath);

                if (!sheetPath.exists() && !sheetPath.mkdirs()) {
                    logger.error("Couldn't create folder {}", sheetPath);
                    System.exit(1);
                }
                String name = generateName(entity);
                if (processedFiles.contains(name)) {
                    logger.warn("Duplication detected! Hash {} already exists for {} {}", name,
                          mekSummary.getFullChassis(), mekSummary.getModel());
                    continue;
                }
                processedFiles.add(name);

                UnitData unitData = new UnitData(entity);
                unitData.name = name;
                boolean isSmallUnit = entity.isBattleArmor() || entity.isProtoMek() || entity.isInfantry();
                try {
                    // List<Entity> units = printableListOfUnits(entity);
                    List<PrintRecordSheet> sheets = UnitPrintManager.createSheets(List.of(entity), true, recordSheetOptions);
                    if (sheets.isEmpty()) {
                        logger.error("No sheets generated for {}", mekSummary.getName());
                        System.exit(1);
                    }
                    List<Document> svgDocs = new ArrayList<>();
                    for (PrintRecordSheet sheet : sheets) {
                        if (sheet instanceof PrintSmallUnitSheet) {
                            pf.setPaper(paperDef.createPaper());
                        } else {
                            pf.setPaper(paperDef.createPaper(DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS, DEFAULT_MARGINS));
                        }
                        int pageCount = sheet.getPageCount();
                        for (int pageIndexInSheet = 0; pageIndexInSheet < pageCount; pageIndexInSheet++) {
                            sheet.createDocument(pageIndexInSheet, pf, false);
                            if (pageCount > 1) {
                                // Multiple pages, clone the SVG document for each page to prevent overwriting
                                svgDocs.add((Document) sheet.getSVGDocument().cloneNode(true));
                            } else {
                                // Single page, add directly
                                svgDocs.add(sheet.getSVGDocument());
                            }
                        }
                    }
                    if (svgDocs.isEmpty()) {
                        logger.error("No SVG documents for {}", mekSummary.getName());
                        System.exit(1);
                    }
                    int idx = 0;
                    for (Document svgDoc : svgDocs) {
                        SVGOptimizer.optimize((SVGDocument) svgDoc);
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.setOutputProperty(OutputKeys.INDENT, "no");
                        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                        String baseSvgFilename = unitData.name + (idx > 0 ? "_" + idx : "");
                        String unoptimizedSvgFilename = baseSvgFilename + ".svg";
                        File finalUnoptimizedFilename = new File(sheetPath, unoptimizedSvgFilename);
                        try (FileOutputStream fos = new FileOutputStream(finalUnoptimizedFilename)) {
                            DOMSource source = new DOMSource(svgDoc);
                            StreamResult result = new StreamResult(fos);
                            transformer.transform(source, result);
                        }
                        String pathToSave = (svgPath + File.separator + unoptimizedSvgFilename).replace("\\", "/");
                        unitData.sheets.add(pathToSave);
                        idx++;
                    }
                    // logger.info("Printed: {}", finalFilename);
                } catch (Exception e) {
                    logger.error(e, "Printing Error");
                    System.exit(1);
                }

                // Set additional fields
                unitData.pv = mekSummary.getPointValue();
                unitData.su = isSmallUnit ? 1 : 0; // 1 for small units, 0 for others

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

    private static RecordSheetOptions getRecordSheetOptions() {
        RecordSheetOptions recordSheetOptions = new RecordSheetOptions();
        recordSheetOptions.setColor(RecordSheetOptions.ColorMode.LOGO_ONLY);
        recordSheetOptions.setHeatScaleMarker(RecordSheetOptions.HeatScaleMarker.ARROW);
        recordSheetOptions.setC3inBV(true);
        recordSheetOptions.setBoldType(true);
        recordSheetOptions.setHeatProfile(true);
        recordSheetOptions.setCondensedReferenceCharts(true);
        recordSheetOptions.setRole(true);
        recordSheetOptions.setEraIcon(true);
        recordSheetOptions.setQuirks(true);
        recordSheetOptions.setDamage(false);
        recordSheetOptions.setWeaponsOrder(WeaponSortOrder.RANGE_HIGH_LOW);
        recordSheetOptions.setPaperSize(PaperSize.US_LETTER);
        recordSheetOptions.setMergeIdenticalEquipment(false);
        return recordSheetOptions;
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
            double xOffset = i * sheetWidth;
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
        if (!widthAttr.isEmpty()) {
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
        if (!viewBox.isEmpty()) {
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
        if (!heightAttr.isEmpty()) {
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
        if (!viewBox.isEmpty()) {
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

    private static String generateName(Entity entity) {
        ASUnitType asUnitType = ASUnitType.getUnitType(entity);
        return String.format("%s%s_%s", (asUnitType != ASUnitType.UNKNOWN) ? asUnitType.name() : "",
                    entity.getChassis(),
                    entity.getModel())
                            .replaceAll("[^a-zA-Z0-9_]", "")
                            .replaceAll("_+", "_")
                            .replaceAll("^_+|_+$", "");
    }

    private SVGMassPrinter() {
        throw new IllegalStateException();
    }
}
