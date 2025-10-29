/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.printing;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import javax.imageio.ImageIO;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import megamek.common.annotations.Nullable;
import megamek.common.util.ImageUtil;
import megamek.logging.MMLogger;
import megameklab.printing.reference.ReferenceTable;
import megameklab.util.CConfig;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGLocatableSupport;
import org.apache.batik.anim.dom.SVGOMElement;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.util.SAXDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.apache.fop.configuration.Configuration;
import org.apache.fop.configuration.DefaultConfigurationBuilder;
import org.apache.fop.svg.PDFTranscoder;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGRectElement;

/**
 * Base class for rendering record sheets. This is mostly a collection of utility methods.
 *
 * @author Neoancient
 */
public abstract class PrintRecordSheet implements Printable, IdConstants {
    private static final MMLogger logger = MMLogger.create(PrintRecordSheet.class);

    public static final String DEFAULT_TYPEFACE = "Eurostile";
    public static final float DEFAULT_PIP_SIZE = 0.38f;
    public static final float DEFAULT_PIP_STROKE = 0.5f;
    public static final float FONT_SIZE_LARGE = 7.2f;
    public static final float FONT_SIZE_MEDIUM = 6.76f;
    public static final float FONT_SIZE_SMALL = 6.2f;
    public static final float FONT_SIZE_VERY_SMALL = 5.8f;
    public static final String FILL_BLACK = "#000000";
    public static final String FILL_GREY = "#3f3f3f";
    public static final String FILL_SHADOW = "#c7c7c7";
    public static final String FILL_WHITE = "#ffffff";

    public static final String FILL_RED = "#ee0000";
    /** Scale factor for record sheets with reference tables */
    public static final double TABLE_RATIO = 0.8;

    private static final String TEMPLATE_DIRECTORY = "data/images/recordsheets/";
    private static final String UNIT_TEST_TEMPLATE_DIRECTORY = "testresources/" + TEMPLATE_DIRECTORY;

    public static final String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    private final int firstPage;
    protected final RecordSheetOptions options;
    private Document svgDocument;
    private SVGGraphics2D svgGenerator;
    // Used to update progress bar
    private Consumer<Integer> callback;

    private Font normalFont = null;
    private Font boldFont = null;
    private Font italicFont = null;
    private Font boldItalicFont = null;
    private String typeface = null;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param firstPage The print job page number for this sheet
     * @param options   Overrides the global options for which elements are printed
     */
    protected PrintRecordSheet(int firstPage, RecordSheetOptions options) {
        this.firstPage = firstPage;
        this.options = options;
    }

    /**
     * @return The page number of the first page of this record sheet within the book.
     */
    protected final int getFirstPage() {
        return firstPage;
    }

    public final @Nullable Document getSVGDocument() {
        return svgDocument;
    }

    public final void setSVGDocument(final @Nullable Document svgDocument) {
        this.svgDocument = svgDocument;
    }

    /**
     * Provides a callback function that can be used to provide updates on printing progress. As each page is rendered,
     * the callback is invoked with the page number.
     *
     * @param callback The function to call with the current page number.
     */
    public void setCallback(Consumer<Integer> callback) {
        this.callback = callback;
    }

    /**
     * @return The name of the typeface to use when printing record sheets.
     */
    protected final String getTypeface() {
        if (null == typeface) {
            assignFonts();
        }
        return typeface;
    }

    /**
     * Used for measuring font metrics of a normal weight font
     *
     * @param size The font size
     *
     * @return A font derived from the default
     */
    protected final Font getNormalFont(float size) {
        if (null == normalFont) {
            assignFonts();
        }
        return normalFont.deriveFont(size);
    }

    /**
     * Used for measuring font metrics of a bold weight font
     *
     * @param size The font size
     *
     * @return A font derived from the default bold
     */
    protected final Font getBoldFont(float size) {
        if (null == boldFont) {
            assignFonts();
        }
        return boldFont.deriveFont(size);
    }

    /**
     * Used for measuring font metrics of an italic font
     *
     * @param size The font size
     *
     * @return A font derived from the default italic
     */
    protected final Font getItalicFont(float size) {
        if (null == italicFont) {
            assignFonts();
        }
        return italicFont.deriveFont(size);
    }

    /**
     * Used for measuring font metrics of a bold italic font
     *
     * @param size The font size
     *
     * @return A font derived from the default italic
     */
    protected final Font getBoldItalicFont(float size) {
        if (null == boldItalicFont) {
            assignFonts();
        }
        return boldItalicFont.deriveFont(size);
    }

    private void assignFonts() {
        typeface = CConfig.getParam(CConfig.RS_FONT, DEFAULT_TYPEFACE);
        Font font = Font.decode(typeface);
        normalFont = font.deriveFont(Font.PLAIN, 8);
        boldFont = font.deriveFont(Font.BOLD, 8);
        italicFont = font.deriveFont(Font.ITALIC, 8);
        boldItalicFont = font.deriveFont(Font.ITALIC | Font.BOLD, 8);
    }

    /**
     * Finds all text elements in the SVG document and replaces the font-family attribute.
     *
     * @param doc The document to perform replacement in.
     */
    private void subFonts(SVGDocument doc) {
        NodeList list = doc.getElementsByTagName("*");
        if (list != null) {
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node instanceof Element elem) {
                    // First we check if it has the font-family in the style attribute
                    if (elem.hasAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE)) {
                        String style = elem.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
                        if (style.contains("font-family:")) {
                            String newStyle = style.replaceAll(
                                  "font-family\\s*:\\s*[^;]+",
                                  "font-family:" + getTypeface()
                            );
                            newStyle = newStyle.replaceAll(";;+", ";").replaceAll("^;+|;+$", "").trim();
                            if (newStyle.isEmpty()) {
                                elem.removeAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
                            } else {
                                elem.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE, newStyle);
                            }
                            if (elem.hasAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE)) {
                                elem.removeAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE);
                            }
                            continue;
                        }
                    }
                    if (elem.hasAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE)) {
                        elem.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, getTypeface());
                    }
                }
            }
        }
    }

    private void subColorElements() {
        Element element = getSVGDocument().getElementById(RS_TEMPLATE);
        if (element != null) {
            String style = element.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
            for (String field : style.split(";")) {
                if (field.startsWith(MML_COLOR_ELEMENTS + ":")) {
                    String[] ids = field.substring(field.indexOf(":") + 1).split(",");
                    for (String id : ids) {
                        if (options.useColor() == RecordSheetOptions.ColorMode.LOGO_ONLY) {
                            if (id.contains("btLogo")) {
                                hideElement(id + "Color", false);
                                removeElement(id + "BW");
                                continue;
                            }
                        }
                        if (options.useColor() == RecordSheetOptions.ColorMode.ALL) {
                            removeElement(id + "BW");
                            hideElement(id + "Color", false);
                        } else {
                            removeElement(id + "Color");
                            hideElement(id + "BW", false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds elements by class name (using XPath).
     *
     * @param className The class name to search for.
     *
     * @return A list of matching elements.
     */
    private ArrayList<Element> getElementsByClass(String className) {
        ArrayList<Element> ret = new ArrayList<>();
        if (className == null || className.isEmpty() || getSVGDocument() == null) {
            return ret;
        }

        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String expressionStr = "//*[contains(concat(' ', normalize-space(@class), ' '), ' " + className + " ')]";
            XPathExpression expr = xpath.compile(expressionStr);

            NodeList nodeList = (NodeList) expr.evaluate(getSVGDocument(), XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i) instanceof Element element) {
                    ret.add(element);
                }
            }

        } catch (XPathExpressionException e) {
            logger.error("XPath error finding elements by class '{}': {}", className, e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error finding elements by class '{}' using XPath: {}",
                  className,
                  e.getMessage(),
                  e);
        }

        return ret;
    }

    private void shadeTableRows() {
        for (Element e : getElementsByClass(ROW_SHADING)) {
            hideElement(e.getAttributes().getNamedItem("id").getNodeValue(), !options.useRowShading());
        }
    }

    private void makeBoldType() {
        if (options.useBoldType()) {
            Element e = getSVGDocument().getElementById(TYPE);
            if (e == null) {
                return;
            }
            String style = e.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
            if (style.isEmpty()) {
                e.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE, "font-weight:bold;");
            } else if (!style.contains("font-weight:")) {
                e.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE, style + "font-weight:bold;");
            } else {
                e.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE,
                      style.replaceAll("font-weight:.*?;", "font-weight:bold;"));
            }
        }
    }

    /**
     * Fixes the lack of unit in font-size attributes in the SVG document.
     */
    private void fixFontSize() {
        if (getSVGDocument() == null) {
            return;
        }
        NodeList allElements = getSVGDocument().getElementsByTagName("*");
        for (int i = 0; i < allElements.getLength(); i++) {
            Element el = (Element) allElements.item(i);

            // Fix font-size in style attribute
            String style = el.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
            if (!style.isEmpty()) {
                String fixed = style.replaceAll(
                      "font-size:\\s*(\\d+(?:\\.\\d+)?)(?!px)(;?)", "font-size:$1px$2"
                );
                if (!fixed.equals(style)) {
                    el.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE, fixed);
                }
            }

            // Fix font-size attribute directly
            String fontSize = el.getAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE);
            if (!fontSize.isEmpty() && !fontSize.endsWith("px")) {
                // Only add px if it's a number (not e.g. 'small')
                if (fontSize.matches("\\d+(\\.\\d+)?")) {
                    el.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize + "px");
                }
            }
        }
    }

    private void makeFrameless() {
        if (!options.isFrameless()) {
            return;
        }
        for (Element e : getElementsByClass(FRAME)) {
            hideElement(e.getAttributes().getNamedItem("id").getNodeValue());

            // I have no idea with this loop is necessary
            // Hiding a parent should hide its children
            // But without it pilot data sneaks onto the sheet
            for (int i = 0; i < e.getChildNodes().getLength(); i++) {
                var c = e.getChildNodes().item(i);
                if (!(c instanceof SVGOMElement child)) {
                    continue;
                }
                if (child.getId() == null) {
                    child.setId(UUID.randomUUID().toString());
                }
                hideElement(child.getId());
            }
        }
    }

    /**
     * Creates a {@link Document} from a svg image file
     *
     * @param filename The name of the SVG file
     *
     * @return The document object
     */
    private @Nullable Document loadSVG(String directoryPath, String filename) {
        final Path filePath = Paths.get(directoryPath, filename);
        if (!Files.exists(filePath)) {
            logger
                  .error("SVG file does not exist at path: {}/{}", directoryPath, filename);
            System.out.println("SVG file does not exist at path: " + directoryPath + "/" + filename);
            return null;
        }

        Document document = null;
        try (InputStream is = Files.newInputStream(filePath)) {
            SAXDocumentFactory df = new SAXDocumentFactory(SVGDOMImplementation.getDOMImplementation(),
                  XMLResourceDescriptor.getXMLParserClassName());
            document = df.createDocument(filePath.toUri().toASCIIString(), is);
            Element rootElement = document.getDocumentElement();
            if (rootElement != null) {
                rootElement.setAttributeNS(null, "overflow", "visible");
            }
        } catch (Exception ex) {
            logger.error("", ex);
        }

        if (document == null) {
            logger.error("Failed to open SVG file! Path: {}/{}", directoryPath, filename);
            return null;
        }

        return document;
    }

    /**
     * Checks the <code>style</code> attribute of an {@link Element} for a given property and returns its value, or null
     * if the property does not exist.
     *
     * @param element  The element to check the property of
     * @param property The name of the property
     *
     * @return The value of the property, or <code>null</code> if the property does not exist.
     */
    static @Nullable String parseStyle(Element element, String property) {
        final String style = element.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE);
        return Arrays.stream(style.split(";"))
              .filter(field -> field.startsWith(property + ':'))
              .findFirst()
              .map(field -> field.substring(field.indexOf(':') + 1)).orElse(null);
    }

    /**
     * Creates the base template document. This is usually loaded from a file, but some composite record sheets override
     * this to create a document in memory which is then filled in using the individual record sheet templates.
     *
     * @param pageIndex                    The index of this page in the print job
     * @param pageFormat                   The page format selected by the user
     * @param useUnitTestTemplateDirectory {@code true} if the unit test template directory should be used
     *
     * @return An SVG document for one page of the print job
     */
    protected @Nullable Document loadTemplate(int pageIndex, PageFormat pageFormat,
          boolean useUnitTestTemplateDirectory) {
        return loadSVG(getSVGDirectoryName(useUnitTestTemplateDirectory), getSVGFileName(pageIndex - firstPage));
    }

    /**
     * Creates the base template document. This is usually loaded from a file, but some composite record sheets override
     * this to create a document in memory which is then filled in using the individual record sheet templates.
     *
     * @param pageIndex  The index of this page in the print job
     * @param pageFormat The page format selected by the user
     *
     * @return An SVG document for one page of the print job
     */
    protected @Nullable Document loadTemplate(int pageIndex, PageFormat pageFormat) {
        return loadTemplate(pageIndex, pageFormat, false);
    }

    public boolean createDocument(int pageIndex, PageFormat pageFormat, boolean addMargin) {
        return createDocument(pageIndex, pageFormat, addMargin, false);
    }

    /**
     * @return true if the document was created successfully, otherwise false
     */
    public boolean createDocument(int pageIndex, PageFormat pageFormat, boolean addMargin,
          boolean useUnitTestTemplateDirectory) {
        setSVGDocument(loadTemplate(pageIndex, pageFormat, useUnitTestTemplateDirectory));
        if (getSVGDocument() == null) {
            return false;
        }
        subFonts((SVGDocument) getSVGDocument());
        subColorElements();
        SVGGeneratorContext context = SVGGeneratorContext.createDefault(getSVGDocument());
        svgGenerator = new SVGGraphics2D(context, false);
        double ratio = Math.min(pageFormat.getImageableWidth() / (options.getPaperSize().pxWidth - 36),
              pageFormat.getPaper().getImageableHeight() / (options.getPaperSize().pxHeight - 36));
        if ((pageIndex == firstPage) && includeReferenceCharts()) {
            ratio *= TABLE_RATIO;
        }
        Element svgRoot = getSVGDocument().getDocumentElement();
        svgRoot.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(pageFormat.getWidth()));
        svgRoot.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(pageFormat.getHeight()));
        Element g = getSVGDocument().getElementById(RS_TEMPLATE);
        if (g != null) {
            if (addMargin) {
                g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                      String.format("%s(%f 0 0 %f %f %f)", SVGConstants.SVG_MATRIX_VALUE,
                            ratio, ratio, pageFormat.getImageableX(), pageFormat.getImageableY()));
            } else {
                g.setAttributeNS(null, SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                      String.format("%s(%f %f)", SVGConstants.SVG_SCALE_ATTRIBUTE,
                            ratio, ratio));
            }
        }
        processImage(pageIndex - firstPage, pageFormat);
        applyCoreComponentsCriticalDamage();
        shadeTableRows();
        makeFrameless();
        makeBoldType();
        return true;
    }

    /**
     * Applies the critical damage to the core components of the unit and crew. This should be overridden by subclasses
     * that have core components.
     */
    protected void applyCoreComponentsCriticalDamage() {
    }

    protected void fillCoreComponentCriticalDamage(String idPrefix, int damage) {
        if (damage < 1) {
            return;
        }
        for (int i = 1; i <= damage; i++) {
            Element el = getSVGDocument().getElementById(idPrefix + i);
            if (el != null) {
                el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
            }
        }
    }

    protected void fillCoreComponentCriticalDamage(String id, boolean damaged) {
        if (!damaged) {
            return;
        }
        Element el = getSVGDocument().getElementById(id);
        if (el != null) {
            el.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, getDamageFillColor());
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        Graphics2D g2d = (Graphics2D) graphics;
        if (g2d != null) {
            // Expand clip to full paper so overflow content can be painted
            Shape oldClip = g2d.getClip();
            g2d.setClip(new Rectangle2D.Double(
                  0, 0, pageFormat.getWidth(), pageFormat.getHeight()));
            try {
                if (!createDocument(pageIndex, pageFormat, true)) {
                    return NO_SUCH_PAGE;
                }
                GraphicsNode node = build();
                if (node != null) {
                    node.paint(g2d);
                }
            } finally {
                // Restore original clip
                g2d.setClip(oldClip);
            }
        }
        if (callback != null) {
            callback.accept(pageIndex);
        }
        return PAGE_EXISTS;
    }

    public @Nullable InputStream exportPDF(int pageNumber, PageFormat pageFormat) throws Exception {
        if (!createDocument(pageNumber + firstPage, pageFormat, true)) {
            return null;
        }
        PDFTranscoder transcoder = new PDFTranscoder();
        transcoder.addTranscodingHint(PDFTranscoder.KEY_AUTO_FONTS, true);

        String configXml = "<fop version=\"1.0\"><fonts><auto-detect/></fonts></fop>";
        try (InputStream configStream = PrintRecordSheet.class.getResourceAsStream("fop-config.xml")) {
            if (configStream != null) {
                configXml = new String(configStream.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException ignored) {
            logger.warn("Failed to load fop-config.xml");
        }

        // Define font directories based on OS
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuilder systemFontDirectories = new StringBuilder();
        List<String> directories = new ArrayList<>();
        if (osName.contains("windows")) {
            String winDir = System.getenv("WINDIR");
            if (winDir == null) {
                winDir = System.getenv("SystemRoot");
            }
            if (winDir == null) {
                winDir = "C:\\Windows";
            }
            directories.add(winDir + "\\Fonts");
            directories.add(System.getenv("LOCALAPPDATA") + "\\Microsoft\\Windows\\Fonts");
        } else if (osName.contains("mac")) {
            directories.add("/System/Library/Fonts");
            directories.add("/Library/Fonts");
            directories.add("~/Library/Fonts");
        } else {
            directories.add("/usr/share/fonts");
            directories.add("/usr/local/share/fonts");
            directories.add("~/.local/share/fonts");
        }
        // Add only directories that exist
        for (String path : directories) {
            File fontDir = new File(path);
            if (fontDir.exists() && fontDir.isDirectory()) {
                systemFontDirectories.append("<directory recursive=\"true\">").append(path).append("</directory>");
            }
        }
        int insertPoint = configXml.indexOf("</fonts>");
        if (insertPoint > 0) {
            configXml = configXml.substring(0, insertPoint) + systemFontDirectories + configXml.substring(insertPoint);
        } else {
            logger.warn("Failed to inject system font directories into fop-config.xml");
        }
        DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
        Configuration config = cfgBuilder.build(
              new ByteArrayInputStream(configXml.getBytes(StandardCharsets.UTF_8)));
        transcoder.configure(config);

        TranscoderInput input = new TranscoderInput(getSVGDocument());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        TranscoderOutput transOutput = new TranscoderOutput(output);
        // The default for the transcoder is 96 dpi, but the source document is 72 dpi.
        transcoder.addTranscodingHint(PDFTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER, 0.352778f);
        transcoder.transcode(input, transOutput);

        if (callback != null) {
            callback.accept(pageNumber + firstPage);
        }
        return new ByteArrayInputStream(output.toByteArray());
    }

    public GraphicsNode build() {
        if (getSVGDocument() == null) {
            logger.error("Attempted to build graphics node with null SVG document");
            return null;
        }
        GVTBuilder builder = new GVTBuilder();
        BridgeContext ctx = new BridgeContext(new UserAgentAdapter() {
            @Override
            // If an image can't be rendered we'll log it and return an empty document in
            // its place rather than throwing an exception.
            public SVGDocument getBrokenLinkDocument(Element e, String url, String message) {
                logger.warn("Cannot render image: {}", message);
                DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
                SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, SVGConstants.SVG_SVG_TAG, null);
                Element text = doc.createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
                text.setTextContent("?");
                doc.getDocumentElement().appendChild(text);
                return doc;
            }
        });
        ctx.setDynamic(true);
        return builder.build(ctx, getSVGDocument());
    }

    /**
     * @return The number of pages required to print this record sheet
     */
    public int getPageCount() {
        return 1;
    }

    /**
     * Renders the sheet to the Graphics object.
     *
     * @param pageNum Indicates which page of multipage sheets to print. The first page is 0.
     */
    protected void processImage(int pageNum, PageFormat pageFormat) {
        Element element = getSVGDocument().getElementById(COPYRIGHT);
        if (null != element) {
            element.setTextContent(String.format(element.getTextContent(), LocalDate.now().getYear()));
        }
    }

    String getSVGDirectoryName(boolean useUnitTestTemplateDirectory) {
        return (useUnitTestTemplateDirectory ? UNIT_TEST_TEMPLATE_DIRECTORY : TEMPLATE_DIRECTORY)
              + options.getPaperSize().dirName;
    }

    /**
     * @param pageNumber The page number in the current record sheet, where the first page is numbered zero.
     *
     * @return The file name for the current page in the record sheet image directory
     */
    protected abstract String getSVGFileName(int pageNumber);

    /**
     * @return The title to use for the record sheet
     */
    protected abstract String getRecordSheetTitle();

    /**
     * Used to build an outline of a PDF document
     *
     * @return Names of outline entries
     */
    public abstract List<String> getBookmarkNames();

    protected void setTextField(String id, int i) {
        setTextField(id, String.valueOf(i));
    }

    protected void setTextField(String id, String text) {
        setTextField(id, text, false);
    }

    /**
     * Sets the text content of the text element in the SVG diagram corresponding with the given id. If the element does
     * not exist, does nothing. If the text is null, hides the element instead. Any text previously in the element is
     * cleared.
     *
     * @param id     The String id of a text element
     * @param text   The text to set as content
     * @param unhide Sets the element visible if the text is non-null
     */
    protected void setTextField(String id, String text, boolean unhide) {
        Element element = getSVGDocument().getElementById(id);
        if (null != element) {
            if (null == text) {
                hideElement(element, true);
            } else {
                if (unhide) {
                    hideElement(element, false);
                }
                element.setTextContent(text);
                /*
                 * In cases where the text may be too long for the space we will need to add the
                 * textLength attribute to fit it into the space. We only want to set the
                 * textLength
                 * when the text is too long so we don't stretch shorter text to fit. So we
                 * abuse the
                 * style attribute to sneak in metadata about the max width of the space.
                 */
                String fieldWidth = parseStyle(element, MML_FIELD_WIDTH);
                if (null != fieldWidth) {
                    try {
                        double width = Double.parseDouble(fieldWidth);
                        build();
                        double textWidth = SVGLocatableSupport.getBBox(element).getWidth();
                        if (textWidth > width) {
                            element.setAttributeNS(null, SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE,
                                  String.valueOf(width));
                            element.setAttributeNS(null, SVGConstants.SVG_LENGTH_ADJUST_ATTRIBUTE,
                                  SVGConstants.SVG_SPACING_AND_GLYPHS_VALUE);
                        }
                    } catch (NumberFormatException ex) {
                        logger.warn("Could not parse fieldWidth: {}", fieldWidth);
                    }
                }
            }
        }
    }

    /**
     * Add a line through the text at the given coordinates. The line is drawn at the center of the text.
     *
     * @param parent The SVG element to add the line to.
     * @param x      The X position of the line.
     * @param y      The Y position of the line.
     * @param width  The width of the line.
     */
    protected void addLineThrough(Element parent, double x, double y, double width) {
        addLineThrough(parent, x, y, width, getDamageFillColor());
    }

    /**
     * Add a line through the text at the given coordinates. The line is drawn at the center of the text.
     *
     * @param parent The SVG element to add the line to.
     * @param x      The X position of the line.
     * @param y      The Y position of the line.
     * @param width  The width of the line.
     * @param color  The color of the line.
     */
    protected void addLineThrough(Element parent, double x, double y, double width, String color) {
        final int STROKE_THICKNESS = 1;
        Element line = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_LINE_TAG);
        line.setAttributeNS(null, SVGConstants.SVG_X1_ATTRIBUTE, String.valueOf(x));
        line.setAttributeNS(null, SVGConstants.SVG_Y1_ATTRIBUTE, String.valueOf(y));
        line.setAttributeNS(null, SVGConstants.SVG_X2_ATTRIBUTE, String.valueOf(x + width));
        line.setAttributeNS(null, SVGConstants.SVG_Y2_ATTRIBUTE, String.valueOf(y));
        line.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, color);
        line.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, STROKE_THICKNESS + "px");
        parent.appendChild(line);
    }

    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent. The width of the text is
     * returned, to aid in layout.
     *
     * @param parent   The SVG element to add the text element to.
     * @param x        The X position of the new element.
     * @param y        The Y position of the new element.
     * @param text     The text to display.
     * @param fontSize Font size of the text.
     * @param anchor   Set the Text elements text-anchor. Should be either start, middle, or end.
     * @param weight   The font weight, either normal or bold.
     *
     * @return The width of the text in the current font size
     */
    protected double addTextElement(Element parent, double x, double y, String text,
          float fontSize, String anchor, String weight) {
        return addTextElement(parent, x, y, text, fontSize, anchor, weight, SVGConstants.SVG_NORMAL_VALUE, FILL_BLACK);
    }

    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent. The height of the text is
     * returned, to aid in layout.
     *
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor. Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     * @param fontStyle The font style, either normal or italic.
     * @param fill      The fill color for the text (e.g. foreground color)
     *
     * @return The width of the added text element
     */
    protected double addTextElement(Element parent, double x, double y, String text,
          float fontSize, String anchor, String weight, String fontStyle, String fill) {
        return addTextElement(parent, x, y, text, fontSize, anchor, weight, fontStyle, fill, null, null);
    }

    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent. The height of the text is
     * returned, to aid in layout.
     *
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor. Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     * @param fontStyle The font style, either normal or italic.
     * @param fill      The fill color for the text (e.g. foreground color)
     * @param id        The id of the text element, or null if not needed.
     * @param className The class name of the text element, or null if not needed.
     *
     * @return The width of the added text element
     */
    protected double addTextElement(Element parent, double x, double y, String text,
          float fontSize, String anchor, String weight, String fontStyle, String fill, String id, String className) {
        Element newText = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        newText.setTextContent(text);
        newText.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        newText.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, getTypeface());
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize + "px");
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_WEIGHT_ATTRIBUTE, weight);
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_STYLE_ATTRIBUTE, fontStyle);
        newText.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, anchor);
        newText.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        if (id != null && !id.isEmpty()) {
            newText.setAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE, id);
        }
        if (className != null && !className.isEmpty()) {
            newText.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, className);
        }
        parent.appendChild(newText);
        return getTextLength(text, fontSize, weight, fontStyle);
    }

    /**
     * Creates a new text element with black fill and adds it to the parent. If the text is wider than the available
     * space, the text is compressed to fit.
     *
     * @param parent   The SVG element to add the text element to.
     * @param x        The X position of the new element.
     * @param y        The Y position of the new element.
     * @param width    The width of the space the text has to fit.
     * @param text     The text to display.
     * @param fontSize Font size of the text.
     * @param anchor   Set the Text elements text-anchor. Should be either start, middle, or end.
     * @param weight   The font weight, either normal or bold.
     */
    protected void addTextElementToFit(Element parent, double x, double y, double width,
          String text, float fontSize, String anchor, String weight) {
        addTextElementToFit(parent, x, y, width, text, fontSize, anchor, weight, FILL_BLACK);
    }

    /**
     * Creates a new text element and adds it to the parent. If the text is wider than the available space, the text is
     * compressed to fit.
     *
     * @param parent   The SVG element to add the text element to.
     * @param x        The X position of the new element.
     * @param y        The Y position of the new element.
     * @param width    The width of the space the text has to fit.
     * @param text     The text to display.
     * @param fontSize Font size of the text.
     * @param anchor   Set the Text elements text-anchor. Should be either start, middle, or end.
     * @param weight   The font weight, either normal or bold.
     * @param fill     The fill color for the text (e.g. foreground color)
     */
    protected void addTextElementToFit(Element parent, double x, double y, double width,
          String text, float fontSize, String anchor, String weight, String fill) {
        addTextElementToFit(parent, x, y, width, text, fontSize, anchor, weight, fill, null, null);
    }

    /**
     * Creates a new text element and adds it to the parent. If the text is wider than the available space, the text is
     * compressed to fit.
     *
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param width     The width of the space the text has to fit.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor. Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     * @param fill      The fill color for the text (e.g. foreground color)
     * @param id        The id of the text element, or null if not needed.
     * @param className The class name of the text element, or null if not needed.
     */
    protected void addTextElementToFit(Element parent, double x, double y, double width,
          String text, float fontSize, String anchor, String weight, String fill, String id, String className) {
        Element newText = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        newText.setTextContent(text);
        newText.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        newText.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, getTypeface());
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize + "px");
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_WEIGHT_ATTRIBUTE, weight);
        newText.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, anchor);
        newText.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        if (getTextLength(text, fontSize, weight) > width) {
            newText.setAttributeNS(null, SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE, String.valueOf(width));
            newText.setAttributeNS(null, SVGConstants.SVG_LENGTH_ADJUST_ATTRIBUTE,
                  SVGConstants.SVG_SPACING_AND_GLYPHS_VALUE);
        }
        if (id != null && !id.isEmpty()) {
            newText.setAttributeNS(null, SVGConstants.SVG_ID_ATTRIBUTE, id);
        }
        if (className != null && !className.isEmpty()) {
            newText.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, className);
        }
        parent.appendChild(newText);
    }

    /**
     * Adds a text element to a region with limited width. If there are multiple lines, the text will be split over
     * multiple lines, broken on a space character. The space will still be included on the next line as a small
     * indent.
     *
     * @param canvas     The parent <code>SVGElement</code> to the new
     *                   <code>Text</code>.
     * @param x          The x coordinate of the upper left corner of the text region
     * @param y          The y coordinate of the upper left corner of the text region
     * @param width      The allowable width of the text element.
     * @param lineHeight The amount to increase the y coordinate for a new line
     * @param text       The text to add
     * @param fontSize   The font-size attribute
     * @param anchor     The text-anchor attribute
     * @param weight     The font-weight attribute
     *
     * @return The number of lines of text added
     */
    protected int addMultilineTextElement(Element canvas, double x, double y, double width, double lineHeight,
          String text, float fontSize, String anchor, String weight) {
        return addMultilineTextElement(canvas, x, y, width, lineHeight,
              text, fontSize, anchor, weight, SVGConstants.SVG_NORMAL_VALUE, FILL_BLACK, ' ', null);
    }

    /**
     * Adds a text element to a region with limited width. If there are multiple lines, the text will be split over
     * multiple lines, broken on a space character. The space will still be included on the next line as a small
     * indent.
     *
     * @param canvas     The parent <code>SVGElement</code> to the new
     *                   <code>Text</code>.
     * @param x          The x coordinate of the upper left corner of the text region
     * @param y          The y coordinate of the upper left corner of the text region
     * @param width      The allowable width of the text element.
     * @param lineHeight The amount to increase the y coordinate for a new line
     * @param text       The text to add
     * @param fontSize   The font-size attribute
     * @param anchor     The text-anchor attribute
     * @param weight     The font-weight attribute
     * @param fontStyle  The font style, e.g., normal or italic.
     *
     * @return The number of lines of text added
     */
    protected int addMultilineTextElement(Element canvas, double x, double y, double width, double lineHeight,
          String text, float fontSize, String anchor, String weight, String fontStyle, String className) {
        return addMultilineTextElement(canvas, x, y, width, lineHeight,
              text, fontSize, anchor, weight, fontStyle, FILL_BLACK, ' ', className);
    }

    /**
     * Adds a text element to a region with limited width. If there are multiple lines, the text will be split over
     * multiple lines, broken on the provided character. The line break character will be included on the next line.
     *
     * @param canvas     The parent <code>SVGElement</code> to the new
     *                   <code>Text</code>.
     * @param x          The x coordinate of the upper left corner of the text region
     * @param y          The y coordinate of the upper left corner of the text region
     * @param width      The allowable width of the text element.
     * @param lineHeight The amount to increase the y coordinate for a new line
     * @param text       The text to add
     * @param fontSize   The font-size attribute
     * @param anchor     The text-anchor attribute
     * @param weight     The font-weight attribute
     * @param fontStyle  The font style, e.g., normal or italic.
     * @param fill       The fill color for the text (e.g. foreground color)
     * @param delimiter  The character to use as an acceptable line ending
     *
     * @return The number of lines of text added
     */
    protected int addMultilineTextElement(Element canvas, double x, double y, double width, double lineHeight,
          String text, float fontSize, String anchor, String weight, String fontStyle, String fill, char delimiter,
          String className) {
        int lines = 0;
        // The index of the character after the most recent delimiter found. Everything
        // in text
        // up to pos will fit in the available space.
        int pos = 0;
        Element textGroup = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_G_TAG);
        if (className != null && !className.isEmpty()) {
            textGroup.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, className);
        }
        canvas.appendChild(textGroup);

        while (!text.isBlank()) {
            // If the remaining text fits, add a line and exit.
            double textLength = getTextLength(text, fontSize, weight, fontStyle);
            if (textLength <= width) {
                addTextElement(textGroup, x, y, text, fontSize, anchor, weight, fontStyle, fill, null, null);
                lines++;
                return lines;
            }
            // Check for another delimiter after the last one; we might be able to fit more
            // text on the line.
            int index = text.substring(pos).indexOf(delimiter);
            // If the delimiter doesn't exist in the text, add it as is.
            if ((index < 0) && (pos == 0)) {
                addTextElement(textGroup, x, y, text, fontSize, anchor, weight, fontStyle, fill, null, null);
                lines++;
                return lines;
            }
            // If there are no more delimiters in the text, or adding the next section after
            // the previous
            // delimiter that was found, add the text up to pos.
            final String subText = text.substring(0, pos + index);
            textLength = getTextLength(subText, fontSize, weight, fontStyle);
            if ((index < 0)
                  || ((textLength > width)
                  && (pos > 0))) {
                addTextElement(textGroup,
                      x,
                      y,
                      text.substring(0, pos),
                      fontSize,
                      anchor,
                      weight,
                      fontStyle,
                      fill,
                      null,
                      null);
                lines++;
                y += lineHeight;
                text = text.substring(pos);
                pos = 0;
            } else {
                // Otherwise we know that the text up to index will fit so we update pos to the
                // first character
                // after the delimiter and keep checking.
                pos += index + 1;
            }
        }
        return lines;
    }

    // Format String for writing a curve to a path definition attribute
    private final static String FMT_LINE = " l %f %f";

    protected Element createPip(double x, double y, double radius, double strokeWidth) {
        return createPip(x, y, radius, strokeWidth, PipType.CIRCLE, FILL_WHITE, null, null, false);
    }

    /**
     * Approximates a circle using four Bézier curves.
     *
     * @param x           Position of left of bounding rectangle.
     * @param y           Position of top of bounding rectangle.
     * @param radius      Radius of the circle
     * @param strokeWidth Width of the stroke
     * @param type        PipType
     * @param fill        Fill color for the pip
     *
     * @return A Path describing the circle
     */
    protected Element createPip(double x, double y, double radius, double strokeWidth, PipType type, String fill) {
        return createPip(x, y, radius, strokeWidth, type, fill, null, null, false);
    }

    /**
     * Approximates a circle using four Bézier curves.
     *
     * @param x           Position of left of bounding rectangle.
     * @param y           Position of top of bounding rectangle.
     * @param radius      Radius of the circle
     * @param strokeWidth Width of the stroke
     * @param type        PipType
     * @param fill        Fill color for the pip
     * @param className   Class name for the pip, or null if not needed
     * @param loc         Location of the pip, or null if not needed
     * @param rear        If true, the pip is a rear pip
     *
     * @return A Path describing the circle
     */
    protected Element createPip(double x, double y, double radius, double strokeWidth, PipType type, String fill,
          String className, String loc, boolean rear) {

        // Move to start of pip, at (1, 0)
        String classAttr = "pip";
        if (className != null && !className.isEmpty()) {
            classAttr += " " + className;
        }
        switch (type) {
            case DIAMOND -> {
                // Use diamond shape for hardened armor pips
                Element path = preparePolygonalPip(strokeWidth, fill, loc, rear, classAttr);
                String d = "M" + (x + radius * 2) + "," + (y + radius)
                      + String.format(FMT_LINE, -radius, -radius)
                      + String.format(FMT_LINE, -radius, radius)
                      + String.format(FMT_LINE, radius, radius)
                      + String.format(FMT_LINE, radius, -radius);
                path.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE, d);
                return path;
            }
            case PENTAGON -> {
                Element path = preparePolygonalPip(strokeWidth, fill, loc, rear, classAttr);

                // This formula was generated by the following Mathematica code:
                // $Assumptions = r > 0
                // Simplify[
                //  RegionBoundary[
                //   ReflectionTransform[{0, 1}, {0, y + r}][
                //    RegularPolygon[{x + r, y + r}, r, 5]]]][[1]]
                var r = radius;
                var s5 = Math.sqrt(5);
                var a = Math.sqrt(5d/8 - s5/8);
                var b = Math.sqrt(2 * (5 + s5));
                var c = Math.sqrt(10 - 2 * s5);
                String d = ("M %f %f" + " L %f %f".repeat(5)).formatted(
                      0.25 * (4 + c) * r + x, -((5 * r)/(-5 + s5)) + y,
                      r - a * r + x, 0.25 * (5 + s5) * r + y,
                      -0.25 * (-4 + b) * r + x, -0.25 * (-5 + s5) * r + y,
                      r + x, y,
                      0.25 * (4 + b) * r + x, -0.25 * (-5 + s5) * r + y,
                      0.25 * (4 + c) * r + x, -((5 * r)/(-5 + s5)) + y
                );

                path.setAttribute(SVGConstants.SVG_D_ATTRIBUTE, d);
                return path;
            }
            case CIRCLE, CIRCLE_DASHED -> {
                // Use circle element for normal pips
                Element circle = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_CIRCLE_TAG);
                circle.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, classAttr);
                if (loc != null && !loc.isEmpty()) {
                    circle.setAttributeNS(null, "loc", loc);
                }
                if (rear) {
                    circle.setAttributeNS(null, "rear", "true");
                }
                double centerX = x + radius;
                double centerY = y + radius;
                circle.setAttributeNS(null, SVGConstants.SVG_CX_ATTRIBUTE, Double.toString(centerX));
                circle.setAttributeNS(null, SVGConstants.SVG_CY_ATTRIBUTE, Double.toString(centerY));
                circle.setAttributeNS(null, SVGConstants.SVG_R_ATTRIBUTE, Double.toString(radius));
                circle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
                circle.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, FILL_BLACK);
                circle.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, Double.toString(strokeWidth));

                if (type == PipType.CIRCLE_DASHED) {
                    circle.setAttribute(SVGConstants.SVG_STROKE_DASHARRAY_ATTRIBUTE, "1.8 .85");
                }

                return circle;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private Element preparePolygonalPip(double strokeWidth, String fill, String loc, boolean rear, String classAttr) {
        Element path = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        path.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, classAttr);
        if (loc != null && !loc.isEmpty()) {
            path.setAttributeNS(null, "loc", loc);
        }
        if (rear) {
            path.setAttributeNS(null, "rear", "true");
        }
        path.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        path.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, FILL_BLACK);
        path.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, Double.toString(strokeWidth));
        return path;
    }

    /**
     * Creates a rectangle with Bézier curves on the corners
     *
     * @param x           The x coordinate of the top left corner
     * @param y           The y coordinate of the top left corner
     * @param width       The width of the rectangle
     * @param height      The height of the rectangle
     * @param radius      The radius of the curve in the corners
     * @param control     The length from the endpoint of each curve to its control point
     * @param strokeWidth The width of the stroke
     * @param stroke      The fill color of the stroke
     *
     * @return A path element
     */
    protected Element createRoundedRectangle(double x, double y, double width, double height,
          double radius, double control, double strokeWidth,
          String stroke) {
        Element path = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_ATTRIBUTE);
        path.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, "bombBox");
        path.setAttributeNS(null, SVGConstants.CSS_FILL_PROPERTY, SVGConstants.SVG_NONE_VALUE);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_PROPERTY, stroke);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(strokeWidth));
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_LINEJOIN_PROPERTY, SVGConstants.SVG_ROUND_VALUE);
        final String CURVE_FORMAT = "c %.3f,%.3f %.3f,%.3f %.3f,%.3f";
        String sb = "M " + x + "," + (y + radius)
              + String.format(CURVE_FORMAT, 0.0, -control, radius - control, -radius, radius, -radius)
              + "h " + (width - radius * 2)
              + String.format(CURVE_FORMAT, control, 0.0, radius, radius - control, radius, radius)
              + "v " + (height - radius * 2)
              + String.format(CURVE_FORMAT, 0.0, control, control - radius, radius, -radius, radius)
              + "h " + (-width + radius * 2)
              + String.format(CURVE_FORMAT, -control, 0.0, -radius, control - radius, -radius, -radius)
              + "Z";
        path.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE, sb);
        return path;
    }

    protected void hideElement(String id) {
        Element element = getSVGDocument().getElementById(id);
        if (null != element) {
            hideElement(element, true);
        }
    }

    protected void hideElement(String id, boolean hide) {
        Element element = getSVGDocument().getElementById(id);
        if (null != element) {
            hideElement(element, hide);
        }
    }

    protected void removeElement(String id) {
        Element element = getSVGDocument().getElementById(id);
        if (null != element) {
            element.getParentNode().removeChild(element);
        }
    }

    /**
     * Sets the visibility attribute to "hidden"
     *
     * @param element The element to hide
     */
    protected void hideElement(Element element) {
        hideElement(element, true);
    }

    /**
     * Sets an element's visibility attribute
     *
     * @param element The element to hide or show
     * @param hide    If true, visibility will be set to hidden. If false, the
     */
    protected void hideElement(Element element, boolean hide) {
        if (hide) {
            element.setAttributeNS(null, SVGConstants.CSS_VISIBILITY_PROPERTY, SVGConstants.CSS_HIDDEN_VALUE);
        } else {
            element.setAttributeNS(null, SVGConstants.CSS_VISIBILITY_PROPERTY, SVGConstants.CSS_VISIBLE_VALUE);
        }
    }

    /**
     * Determines the vertical space taken up by a line of text.
     *
     * @param fontSize Value of CSS font-size attribute
     *
     * @return The height of the bounding box of a text element
     */
    public float getFontHeight(float fontSize) {
        Font f = getNormalFont(fontSize);
        return getFontHeight(fontSize, f);
    }

    /**
     * Determines the vertical space taken up by a line of text.
     *
     * @param fontSize Value of CSS font-size attribute
     * @param font     The font to use for the text
     *
     * @return The height of the bounding box of a text element
     */
    protected float getFontHeight(float fontSize, Font font) {
        FontMetrics fm = svgGenerator.getFontMetrics(font);
        return fm.getHeight();
    }

    public double getNormalTextLength(String text, float fontSize) {
        Font font = getNormalFont(fontSize);
        return font.getStringBounds(text, svgGenerator.getFontRenderContext()).getWidth();
    }

    public double getBoldTextLength(String text, float fontSize) {
        Font font = getBoldFont(fontSize);
        return font.getStringBounds(text, svgGenerator.getFontRenderContext()).getWidth();
    }

    public double getItalicTextLength(String text, float fontSize) {
        Font font = getItalicFont(fontSize);
        return font.getStringBounds(text, svgGenerator.getFontRenderContext()).getWidth();
    }

    public double getBoldItalicTextLength(String text, float fontSize) {
        Font font = getBoldItalicFont(fontSize);
        return font.getStringBounds(text, svgGenerator.getFontRenderContext()).getWidth();
    }

    public double getTextLength(String text, float fontSize) {
        return getTextLength(text, fontSize, SVGConstants.SVG_NORMAL_VALUE, SVGConstants.SVG_NORMAL_VALUE);
    }

    public double getTextLength(String text, float fontSize, String weight) {
        return getTextLength(text, fontSize, weight, SVGConstants.SVG_NORMAL_VALUE);
    }

    public double getTextLength(String text, float fontSize, String weight, String fontStyle) {
        double textLength;
        if ((fontStyle.equals(SVGConstants.SVG_ITALIC_VALUE)) && (weight.equals(SVGConstants.SVG_BOLD_VALUE))) {
            textLength = getBoldItalicTextLength(text, fontSize);
        } else if (weight.equals(SVGConstants.SVG_BOLD_VALUE)) {
            textLength = getBoldTextLength(text, fontSize);
        } else if (fontStyle.equals(SVGConstants.SVG_ITALIC_VALUE)) {
            textLength = getItalicTextLength(text, fontSize);
        } else {
            textLength = getNormalTextLength(text, fontSize);
        }
        return textLength;
    }

    public static Rectangle2D getRectBBox(SVGRectElement rect) {
        return new Rectangle2D.Float(rect.getX().getBaseVal().getValue(),
              rect.getY().getBaseVal().getValue(),
              rect.getWidth().getBaseVal().getValue(),
              rect.getHeight().getBaseVal().getValue());
    }

    /**
     * Inserts an image into the SVG diagram scaled to fit into the provided bounds.
     *
     * @param imageFile The file containing the image to embed.
     * @param canvas    The parent element for the image element.
     * @param bbox      The bounding box for the image. The image will be scaled to fit.
     * @param center    Whether to center the image vertically and horizontally.
     */
    public void embedImage(@Nullable File imageFile, Element canvas, Rectangle2D bbox, boolean center) {
        if (imageFile == null) {
            return;
        }
        final Path filePath = Paths.get(imageFile.getPath());
        try (InputStream is = Files.newInputStream(filePath)) {
            embedImage(ImageIO.read(is), canvas, bbox, center, null);
        } catch (FileNotFoundException e) {
            logger.error("Fluff image file not found: {}", filePath);
        } catch (IOException e) {
            logger.error("Error reading fluff image file: {}", filePath);
        }
    }

    /**
     * Inserts an image into the SVG diagram scaled to fit into the provided bounds.
     *
     * @param image  The file containing the image to embed.
     * @param canvas The parent element for the image element.
     * @param bbox   The bounding box for the image. The image will be scaled to fit.
     * @param center Whether to center the image vertically and horizontally.
     */
    public void embedImage(@Nullable Image image, Element canvas, Rectangle2D bbox, boolean center,
          @Nullable String className) {
        if (image == null) {
            return;
        }

        String mimeType = "image/png";
        String format = "png";
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = ImageUtil.convertToBufferedImage(image);
            ImageIO.write(bufferedImage, format, bytes);

            double width = bufferedImage.getWidth();
            double height = bufferedImage.getHeight();
            double scale = Math.min(bbox.getWidth() / width, bbox.getHeight() / height);
            width *= scale;
            height *= scale;
            double x = bbox.getX();
            double y = bbox.getY();
            if (center) {
                x += (bbox.getWidth() - width) / 2;
                y += (bbox.getHeight() - height) / 2;
            }
            Element img = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_IMAGE_TAG);
            if (className != null && !className.isEmpty()) {
                img.setAttributeNS(null, SVGConstants.SVG_CLASS_ATTRIBUTE, className);
            }
            img.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, Double.toString(x));
            img.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, Double.toString(y));
            img.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, Double.toString(width));
            img.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, Double.toString(height));
            img.setAttributeNS(SVGConstants.XLINK_NAMESPACE_URI, SVGConstants.XLINK_HREF_QNAME,
                  "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(bytes.toByteArray()));
            canvas.appendChild(img);
        } catch (IOException ex) {
            logger.error("Error embedding fluff image", ex);
        }
    }

    /**
     * Used to determine whether to scale the record sheet to make room for charts. This depends both on whether the
     * option is selected and on whether the sheet supports reference charts.
     *
     * @return Whether to include reference tables
     */
    protected boolean includeReferenceCharts() {
        return false;
    }

    protected List<ReferenceTable> getRightSideReferenceTables() {
        return Collections.emptyList();
    }

    /**
     * Adds reference charts to the right side of the record sheet.
     *
     * @param pageFormat The document's page format.
     */
    protected void addReferenceCharts(PageFormat pageFormat) {
        placeReferenceCharts(
              getRightSideReferenceTables(),
              getSVGDocument().getDocumentElement(),
              pageFormat.getImageableX() + pageFormat.getImageableWidth() * 0.8 + 3.0,
              pageFormat.getImageableY(),
              pageFormat.getImageableWidth() * 0.2, pageFormat.getImageableHeight());
    }

    /**
     * Adds a list of reference tables to the document, sizing to fit withing the available space. Layout is vertical.
     *
     * @param tables The list of tables to add.
     * @param parent The parent node of the table {@link Element}.
     * @param x      The x coordinate of the top left corner of the tables, relative to the parent node.
     * @param y      The y coordinate of the top left corder of the tables, relative to the parent node.
     * @param width  The width of the table column.
     * @param height The height of the table column.
     */
    protected void placeReferenceCharts(List<ReferenceTable> tables, Node parent, double x, double y, double width,
          double height) {
        double BORDER = 3.0;
        double lines = tables.stream().mapToDouble(ReferenceTable::lineCount).sum();
        double yPos = y + BORDER;
        double margin = ReferenceTable.getMargins(this);
        for (ReferenceTable table : tables) {
            double tableHeight = (height - margin * tables.size())
                  * table.lineCount() / lines + margin;
            parent.appendChild(
                  table.createTable(x, yPos, width, tableHeight - BORDER));
            yPos += tableHeight;
        }
    }

    /**
     * @return The color to use for the damage fill
     */
    protected String getDamageFillColor() {
        return options.getDamageColor();
    }
}
