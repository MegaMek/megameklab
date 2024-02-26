/*
 * MegaMekLab
 * Copyright (c) 2017-2022 - The MegaMek Team. All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.printing;

import megamek.common.EquipmentType;
import megamek.common.annotations.Nullable;
import megamek.common.util.ImageUtil;
import megameklab.printing.reference.ReferenceTable;
import megameklab.util.CConfig;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.anim.dom.SVGLocatableSupport;
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
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGRectElement;
import org.w3c.dom.xpath.XPathEvaluator;
import org.w3c.dom.xpath.XPathResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Base class for rendering record sheets. This is mostly a collection of utility methods.
 * 
 * @author Neoancient
 */
public abstract class PrintRecordSheet implements Printable, IdConstants {

    public final static String DEFAULT_TYPEFACE = "Eurostile";
    public final static float DEFAULT_PIP_SIZE  = 0.38f;
    public final static float FONT_SIZE_LARGE   = 7.2f;
    public final static float FONT_SIZE_MEDIUM  = 6.76f;
    public final static float FONT_SIZE_SMALL   = 6.2f;
    public final static float FONT_SIZE_VSMALL  = 5.8f;
    public final static String FILL_BLACK = "#231f20";
    public final static String FILL_GREY = "#3f3f3f";
    public final static String FILL_SHADOW = "#c8c7c7";
    public final static String FILL_WHITE = "#ffffff";
    /** Scale factor for record sheets with reference tables */
    public final static double TABLE_RATIO = 0.8;

    enum PipType {
        CIRCLE, DIAMOND;
        
        public static PipType forAT(int at) {
            if (at == EquipmentType.T_ARMOR_HARDENED) {
                return DIAMOND;
            } else {
                return CIRCLE;
            }
        }
    }

    public final static String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    private final int firstPage;
    protected final RecordSheetOptions options;
    private Document svgDocument;
    private SVGGraphics2D svgGenerator;
    // Used to update progress bar
    private Consumer<Integer> callback;
    
    private Font normalFont = null;
    private Font boldFont = null;
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
     * Provides a callback function that can be used to provide updates on printing progress.
     * As each page is rendered, the callback is invoked with the page number.
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
     * @return     A font derived from the default
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
     * @return     A font derived from the default bold
     */
    protected final Font getBoldFont(float size) {
        if (null == boldFont) {
            assignFonts();
        }
        return boldFont.deriveFont(size);
    }
    
    private void assignFonts() {
        typeface = CConfig.getParam(CConfig.RS_FONT, DEFAULT_TYPEFACE);
        Font font = Font.decode(typeface);
        normalFont = font.deriveFont(Font.PLAIN, 8);
        boldFont = font.deriveFont(Font.BOLD, 8);
    }
    
    /**
     * Finds all text elements in the SVG document and replaces the font-family attribute.
     * 
     * @param doc The document to perform replacement in.
     */
    private void subFonts(SVGDocument doc) {
        final XPathResult res = (XPathResult) ((XPathEvaluator) doc).evaluate(".//*[local-name()=\"text\"]",
                doc.getRootElement(), null, XPathResult.ORDERED_NODE_ITERATOR_TYPE, null);
        for (Node node = res.iterateNext(); node != null; node = res.iterateNext()) {
            if (node instanceof Element) {
                final Element elem = (Element) node;
                // First we want to make sure it's not set in the style attribute, which could override
                // the change
                if (elem.hasAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE)) {
                    elem.setAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE,
                            elem.getAttributeNS(null, SVGConstants.SVG_STYLE_ATTRIBUTE)
                            .replaceAll("font-family:.*?;", ""));
                }
                elem.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, getTypeface());
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
                        hideElement(id + "Color", !options.useColor());
                        hideElement(id + "BW", options.useColor());
                    }
                }
            }
        }
    }

    /**
     * Creates a {@link Document} from an svg image file
     *
     * @param filename The name of the SVG file
     * @return The document object
     */
    private @Nullable Document loadSVG(String directoryPath, String filename) {
        final File file = new File(directoryPath, filename);
        if (!file.exists()) {
            LogManager.getLogger().error(String.format("SVG file does not exist at path: %s/%s", directoryPath, filename));
            return null;
        }

        Document svgDocument = null;
        try (InputStream is = new FileInputStream(file)) {
            SAXDocumentFactory df = new SAXDocumentFactory(SVGDOMImplementation.getDOMImplementation(),
                    XMLResourceDescriptor.getXMLParserClassName());
            svgDocument = df.createDocument(file.toURI().toASCIIString(), is);
        } catch (Exception ex) {
            LogManager.getLogger().error("", ex);
        }

        if (svgDocument == null) {
            LogManager.getLogger().error(String.format("Failed to open SVG file! Path: %s/%s", directoryPath, filename));
            return null;
        }

        return svgDocument;
    }

    /**
     * Checks the <code>style</code> attribute of an {@link Element} for a given property and returns its
     * value, or null if the property does not exist.
     *
     * @param element The element to check the property of
     * @param property The name of the property
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
     * Creates the base template document. This is usually loaded from a file, but
     * some composite record sheets override this to create a document in memory
     * which is then filled in using the individual record sheet templates.
     *
     * @param pageIndex   The index of this page in the print job
     * @param pageFormat  The page format selected by the user
     * @return            An SVG document for one page of the print job
     */
    protected @Nullable Document loadTemplate(int pageIndex, PageFormat pageFormat) {
        return loadSVG(getSVGDirectoryName(), getSVGFileName(pageIndex - firstPage));
    }

    /**
     * @return true if the document was created successfully, otherwise false
     */
    protected boolean createDocument(int pageIndex, PageFormat pageFormat, boolean addMargin) {
        setSVGDocument(loadTemplate(pageIndex, pageFormat));
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
        return true;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        Graphics2D g2d = (Graphics2D) graphics;
        if (null != g2d) {
            if (!createDocument(pageIndex, pageFormat, true)) {
                return NO_SUCH_PAGE;
            }
            GraphicsNode node = build();
            node.paint(g2d);
            /* Testing code that outputs the generated svg
            try {
                javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
                javax.xml.transform.Result output = new javax.xml.transform.stream.StreamResult(new File("out.svg"));
                javax.xml.transform.Source input = new javax.xml.transform.dom.DOMSource(svgDocument);
                transformer.transform(input, output);
            } catch (Exception ex) {
                LogManager.getLogger.error(ex);
            }
            */
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
        DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
        Configuration cfg = cfgBuilder.build(getClass().getResourceAsStream("fop-config.xml")); // TODO : remove inline filename
        PDFTranscoder transcoder = new PDFTranscoder();
        transcoder.configure(cfg);
        transcoder.addTranscodingHint(PDFTranscoder.KEY_AUTO_FONTS, false);
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
    
    protected GraphicsNode build() {
        GVTBuilder builder = new GVTBuilder();
        BridgeContext ctx = new BridgeContext(new UserAgentAdapter() {
            @Override
            // If an image can't be rendered we'll log it and return an empty document in its place
            // rather than throwing an exception.
            public SVGDocument getBrokenLinkDocument(Element e, String url, String message) {
                LogManager.getLogger().warn("Cannot render image: " + message);
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
     * @param pageNum    Indicates which page of multi-page sheets to print. The first page is 0.
     */
    protected void processImage(int pageNum, PageFormat pageFormat) {
        Element element = getSVGDocument().getElementById(COPYRIGHT);
        if (null != element) {
            element.setTextContent(String.format(element.getTextContent(), LocalDate.now().getYear()));
        }
    }

    String getSVGDirectoryName() {
        return "data/images/recordsheets/" + options.getPaperSize().dirName; // TODO : Remove inline file path
    }

    /**
     * @param pageNumber  The page number in the current record sheet, where the first page is numbered zero.
     * @return            The file name for the current page in the record sheet image directory
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
     * Sets the text content of the text element in the SVG diagram corresponding with the given id.
     * If the element does not exist, does nothing. If the text is null, hides the element instead.
     * Any text previously in the element is cleared.
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
                /* In cases where the text may be too long for the space we will need to add the
                 * textLength attribute to fit it into the space. We only want to set the textLength
                 * when the text is too long so we don't stretch shorter text to fit. So we abuse the
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
                        LogManager.getLogger().warn("Could not parse fieldWidth: " + fieldWidth);
                    }
                }
            }
        }
    }
    
    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent. The width
     * of the text is returned, to aid in layout.
     * 
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor.  Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     *
     * @return          The width of the text in the current font size
     */
    protected double addTextElement(Element parent, double x, double y, String text,
            float fontSize, String anchor, String weight) {
        return addTextElement(parent, x, y, text, fontSize, anchor, weight, FILL_BLACK);
    }
    
    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent. The
     * height of the text is returned, to aid in layout.
     * 
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor.  Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     * @param fill      The fill color for the text (e.g. foreground color)
     * 
     * @return          The width of the added text element
     */
    protected double addTextElement(Element parent, double x, double y, String text,
            float fontSize, String anchor, String weight, String fill) {
        Element newText = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        newText.setTextContent(text);
        newText.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        newText.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, getTypeface());
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize + "px");
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_WEIGHT_ATTRIBUTE, weight);
        newText.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, anchor);
        newText.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        parent.appendChild(newText);
        
        return weight.equals(SVGConstants.SVG_BOLD_VALUE) ?
                getBoldTextLength(text, fontSize) : getTextLength(text, fontSize);
    }
    
    /**
     * Creates a new text element with black fill and adds it to the parent.
     * If the text is wider than the available
     * space, the text is compressed to fit.
     * 
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param width     The width of the space the text has to fit.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor.  Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     */
    protected void addTextElementToFit(Element parent, double x, double y, double width,
            String text, float fontSize, String anchor, String weight) {
        addTextElementToFit(parent, x, y, width, text, fontSize, anchor, weight, FILL_BLACK);
    }
    
    /**
     * Creates a new text element and adds it to the parent. If the text is wider than the available
     * space, the text is compressed to fit.
     * 
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param width     The width of the space the text has to fit.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor.  Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     * @param fill      The fill color for the text (e.g. foreground color)
     */
    protected void addTextElementToFit(Element parent, double x, double y, double width,
            String text, float fontSize, String anchor, String weight, String fill) {
        Element newText = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        newText.setTextContent(text);
        newText.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        newText.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, getTypeface());
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize + "px");
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_WEIGHT_ATTRIBUTE, weight);
        newText.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, anchor);
        newText.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        if (getTextLength(text, fontSize) > width) {
            newText.setAttributeNS(null,  SVGConstants.SVG_TEXT_LENGTH_ATTRIBUTE, String.valueOf(width));
            newText.setAttributeNS(null, SVGConstants.SVG_LENGTH_ADJUST_ATTRIBUTE,
                    SVGConstants.SVG_SPACING_AND_GLYPHS_VALUE);
        }
        parent.appendChild(newText);
    }
    
    /**
     * Adds a text element to a region with limited width. If there are multiple lines, the text
     * will be split over multiple lines, broken on a space character. The space will still be
     * included on the next line as a small indent.
     * 
     * @param canvas      The parent <code>SVGElement</code> to the new <code>Text</code>.
     * @param x           The x coordinate of the upper left corner of the text region
     * @param y           The y coordinate of the upper left corner of the text region
     * @param width       The allowable width of the text element.
     * @param lineHeight  The amount to increase the y coordinate for a new line
     * @param text        The text to add
     * @param fontSize    The font-size attribute
     * @param anchor      The text-anchor attribute
     * @param weight      The font-weight attribute
     * 
     * @return            The number of lines of text added
     */
    protected int addMultilineTextElement(Element canvas, double x, double y, double width, double lineHeight,
            String text, float fontSize, String anchor, String weight) {
        return addMultilineTextElement(canvas, x, y, width, lineHeight,
                text, fontSize, anchor, weight, FILL_BLACK, ' ');
    }
    
    /**
     * Adds a text element to a region with limited width. If there are multiple lines, the text
     * will be split over multiple lines, broken on the provided character. The line break character
     * will be included on the next line.
     * 
     * @param canvas      The parent <code>SVGElement</code> to the new <code>Text</code>.
     * @param x           The x coordinate of the upper left corner of the text region
     * @param y           The y coordinate of the upper left corner of the text region
     * @param width       The allowable width of the text element.
     * @param lineHeight  The amount to increase the y coordinate for a new line
     * @param text        The text to add
     * @param fontSize    The font-size attribute
     * @param anchor      The text-anchor attribute
     * @param weight      The font-weight attribute
     * @param fill        The fill color for the text (e.g. foreground color)
     * @param delimiter   The character to use as an acceptable line ending
     * 
     * @return            The number of lines of text added
     */
    protected int addMultilineTextElement(Element canvas, double x, double y, double width, double lineHeight,
            String text, float fontSize, String anchor, String weight, String fill, char delimiter) {
        int lines = 0;
        // The index of the character after the most recent delimiter found. Everything in text
        // up to pos will fit in the available space.
        int pos = 0;
        while (!text.isBlank()) {
            // If the remaining text fits, add a line and exit.
            if (getTextLength(text, fontSize) <= width) {
                addTextElement(canvas, x, y, text, fontSize, anchor, weight, fill);
                lines++;
                return lines;
            }
            // Check for another delimiter after the last one; we might be able to fit more text on the line.
            int index = text.substring(pos).indexOf(delimiter);
            // If the delimiter doesn't exist in the text, add it as is.
            if ((index < 0) && (pos == 0)) {
                addTextElement(canvas, x, y, text, fontSize, anchor, weight, fill);
                lines++;
                return lines;
            }
            // If there are no more delimiters in the text, or adding the next section after the previous
            // delimiter that was found, add the text up to pos.
            if ((index < 0)
                    || ((getTextLength(text.substring(0, pos + index), fontSize) > width)
                    && (pos > 0))) {
                addTextElement(canvas, x, y, text.substring(0, pos), fontSize, anchor, weight, fill);
                lines++;
                y += lineHeight;
                text = text.substring(pos);
                pos = 0;
            } else {
                // Otherwise we know that the text up to index will fit so we update pos to the first character
                // after the delimiter and keep checking.
                pos += index + 1;
            }
        }
        return lines;
    }
    
    // Constants used for approximating circles with Bezier curves.
    
    // Ratio of distance from end point to control point to the radius.
    private final static double CONST_C = 0.55191502449;
    // Format String for writing a curve to a path definition attribute
    private final static String FMT_CURVE = " c %f %f,%f %f,%f %f";
    private final static String FMT_LINE = " l %f %f";
    
    protected Element createPip(double x, double y, double radius, double strokeWidth) {
        return createPip(x, y, radius, strokeWidth, PipType.CIRCLE, FILL_WHITE);
    }
    /**
     * Approximates a circle using four Bezier curves.
     * 
     * @param x      Position of left of bounding rectangle.
     * @param y      Position of top of bounding rectangle.
     * @param radius Radius of the circle
     * @return       A Path describing the circle
     */
    protected Element createPip(double x, double y, double radius, double strokeWidth,
            PipType type, String fill) {
        Element path = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        path.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        path.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, FILL_BLACK);
        path.setAttributeNS(null, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, Double.toString(strokeWidth));
        
        // Move to start of pip, at (1, 0)
        StringBuilder d = new StringBuilder("M").append(x + radius * 2).append(",").append(y + radius);
        if (type == PipType.DIAMOND) {
            d.append(String.format(FMT_LINE, -radius, -radius));
            d.append(String.format(FMT_LINE, -radius, radius));
            d.append(String.format(FMT_LINE, radius, radius));
            d.append(String.format(FMT_LINE, radius, -radius));
        } else {
            // c is the length of each control line
            double c = CONST_C * radius;
            
            // Draw arcs anticlockwise. The coordinates are relative to the beginning of the arc.
            d.append(String.format(FMT_CURVE, 0.0, -c, c - radius, -radius, -radius, -radius));
            d.append(String.format(FMT_CURVE, -c, 0.0, -radius, radius - c, -radius, radius));
            d.append(String.format(FMT_CURVE, 0.0, c, radius - c, radius, radius, radius));
            d.append(String.format(FMT_CURVE, c, 0.0, radius, c - radius, radius, -radius));
        }
        path.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE, d.toString());
        return path;
    }

    /**
     * Creates a rectangle with bezier curves on the corners
     *
     * @param x The x coordinate of the top left corner
     * @param y The y coordinate of the top left corner
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param radius The radius of the curve in the corners
     * @param control The length from the endpoint of each curve to its control point
     * @param strokeWidth The width of the stroke
     * @param stroke The fill color of the stroke
     * @return A path element
     */
    protected Element createRoundedRectangle(double x, double y, double width, double height,
                                             double radius, double control, double strokeWidth,
                                             String stroke) {
        Element path = getSVGDocument().createElementNS(svgNS, SVGConstants.SVG_PATH_ATTRIBUTE);
        path.setAttributeNS(null, SVGConstants.CSS_FILL_PROPERTY, SVGConstants.SVG_NONE_VALUE);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_PROPERTY, stroke);
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_WIDTH_PROPERTY, String.valueOf(strokeWidth));
        path.setAttributeNS(null, SVGConstants.CSS_STROKE_LINEJOIN_PROPERTY, SVGConstants.SVG_ROUND_VALUE);
        StringBuilder sb = new StringBuilder("M ").append(x).append(",").append(y+radius);
        final String CURVE_FORMAT = "c %.3f,%.3f %.3f,%.3f %.3f,%.3f";
        sb.append(String.format(CURVE_FORMAT, 0.0, -control, radius - control, -radius, radius, -radius));
        sb.append("h ").append(width - radius * 2);
        sb.append(String.format(CURVE_FORMAT, control, 0.0, radius, radius - control, radius, radius));
        sb.append("v ").append(height - radius * 2);
        sb.append(String.format(CURVE_FORMAT, 0.0, control, control - radius, radius, -radius, radius));
        sb.append("h ").append(-width + radius * 2);
        sb.append(String.format(CURVE_FORMAT, -control, 0.0, -radius, control - radius, -radius, -radius));
        sb.append("Z");
        path.setAttributeNS(null, SVGConstants.SVG_D_ATTRIBUTE, sb.toString());
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
    
    /**
     * Sets the visibility attribute to "hidden"
     * @param element The element to hide
     */
    protected void hideElement(Element element) {
        hideElement(element, true);
    }
    
    /**
     * Sets an element's visibility attribute
     * 
     * @param element  The element to hide or show
     * @param hide     If true, visibility will be set to hidden. If false, the 
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
     * @param fontSize  Value of CSS font-size attribute
     * @return          The height of the bounding box of a text element
     */
    public float getFontHeight(float fontSize) {
        Font f = getNormalFont(fontSize);
        FontMetrics fm = svgGenerator.getFontMetrics(f);
        return fm.getHeight();
    }
    
    public double getTextLength(String text, float fontSize) {
        Font font = getNormalFont(fontSize);
        return font.getStringBounds(text, svgGenerator.getFontRenderContext()).getWidth();
    }
    
    public double getBoldTextLength(String text, float fontSize) {
        Font font = getBoldFont(fontSize);
        return font.getStringBounds(text, svgGenerator.getFontRenderContext()).getWidth();
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
     * @param imageFile  The file containing the image to embed.
     * @param canvas     The parent element for the image element.
     * @param bbox       The bounding box for the image. The image will be scaled to fit.
     * @param center     Whether to center the image vertically and horizontally.
     */
    public void embedImage(@Nullable File imageFile, Element canvas, Rectangle2D bbox, boolean center) {
        if (imageFile == null) {
            return;
        }

        try {
            embedImage(ImageIO.read(imageFile), canvas, bbox, center);
        } catch (FileNotFoundException e) {
            LogManager.getLogger().error("Fluff image file not found: " + imageFile.getPath());
        } catch (IOException e) {
            LogManager.getLogger().error("Error reading fluff image file: " + imageFile.getPath());
        }
    }

    /**
     * Inserts an image into the SVG diagram scaled to fit into the provided bounds.
     *
     * @param image  The file containing the image to embed.
     * @param canvas     The parent element for the image element.
     * @param bbox       The bounding box for the image. The image will be scaled to fit.
     * @param center     Whether to center the image vertically and horizontally.
     */
    public void embedImage(@Nullable Image image, Element canvas, Rectangle2D bbox, boolean center) {
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
            img.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, Double.toString(x));
            img.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, Double.toString(y));
            img.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, Double.toString(width));
            img.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, Double.toString(height));
            img.setAttributeNS(SVGConstants.XLINK_NAMESPACE_URI, SVGConstants.XLINK_HREF_QNAME,
                    "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(bytes.toByteArray()));
            canvas.appendChild(img);
        } catch (IOException ex) {
            LogManager.getLogger().error("Error embedding fluff image", ex);
        }
    }

    /**
     * Used to determine whether to scale the record sheet to make room for charts. This
     * depends both on whether the option is selected and on whether the sheet supports
     * reference charts.
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
     * @param pageFormat The document's page format.
     */
    protected void addReferenceCharts(PageFormat pageFormat) {
        placeReferenceCharts(
                getRightSideReferenceTables(),
                getSVGDocument().getDocumentElement(),
                pageFormat.getImageableX() + pageFormat.getImageableWidth() * 0.8 + 3.0,
                pageFormat.getImageableY(),
                pageFormat.getImageableWidth() *0.2, pageFormat.getImageableHeight());
    }

    /**
     * Adds a list of reference tables to the document, sizing to fit withing the available space.
     * Layout is vertical.
     *
     * @param tables The list of tables to add.
     * @param parent The parent node of the table {@link Element}.
     * @param x      The x coordinate of the top left corner of the tables, relative to the parent node.
     * @param y      The y coordinate of the top left corder of the tables, relative to the parent node.
     * @param width  The with of the table column.
     * @param height The height of the table column.
     */
    protected void placeReferenceCharts(List<ReferenceTable> tables, Node parent, double x, double y, double width, double height) {
        double BORDER = 3.0;
        double lines = tables.stream().mapToDouble(ReferenceTable::lineCount).sum();
        double ypos = y + BORDER;
        double margin = ReferenceTable.getMargins(this);
        for (ReferenceTable table: tables) {
            double tableHeight = (height - margin * tables.size())
                    * table.lineCount() / lines + margin;
            parent.appendChild(
                    table.createTable(x, ypos, width, tableHeight - BORDER));
            ypos += tableHeight;
        }
    }
}
