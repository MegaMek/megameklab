/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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
package megameklab.com.printing;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.RenderedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.dom.util.SAXDocumentFactory;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.util.SVGConstants;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGElement;
import org.w3c.dom.svg.SVGRectElement;

import megamek.common.EquipmentType;
import megamek.common.logging.LogLevel;
import megameklab.com.MegaMekLab;

/**
 * Base class for rendering record sheets. This is mostly a collection of utility methods.
 * 
 * @author Neoancient
 *
 */
public abstract class PrintRecordSheet implements Printable {
    
    final static float DEFAULT_PIP_SIZE  = 0.38f;
    final static float FONT_SIZE_MEDIUM  = 6.76f;
    final static float FONT_SIZE_SMALL   = 6.2f;
    final static float FONT_SIZE_VSMALL  = 5.8f;
    
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

    public final String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
    private final int firstPage;
    private Document svgDocument;
    private SVGGraphics2D svgGenerator;
    
    private Font normalFont = null;
    private Font boldFont = null;
    
    protected PrintRecordSheet(int firstPage) {
        this.firstPage = firstPage;
    }
    
    protected final Document getSVGDocument() {
        return svgDocument;
    }
    
    protected final Font getNormalFont(float size) {
        if (null == normalFont) {
            loadFonts();
        }
        return normalFont.deriveFont(size);
    }
    
    protected final Font getBoldFont(float size) {
        if (null == boldFont) {
            loadFonts();
        }
        return boldFont.deriveFont(size);
    }
    
    private void loadFonts() {
        final String METHOD_NAME = "loadFonts()";
        String fName = "data/fonts/Eurosti.TTF";
        try {
            File fontFile = new File(fName);
            InputStream is = new FileInputStream(fontFile);
            normalFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception ex) {
            MegaMekLab.getLogger().log(PrintRecordSheet.class, METHOD_NAME, LogLevel.ERROR,
                            fName + " not loaded.  Using Arial font.", ex);
            normalFont = new Font("Arial", Font.PLAIN, 8);
        }
        fName = "data/fonts/Eurostib.TTF";
        try {
            File fontFile = new File(fName);
            InputStream is = new FileInputStream(fontFile);
            boldFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is.close();
        } catch (Exception ex) {
            MegaMekLab.getLogger().log(PrintRecordSheet.class, METHOD_NAME, LogLevel.ERROR,
                            fName + " not loaded.  Using Arial font.", ex);
            normalFont = new Font("Arial", Font.BOLD, 8);
        }
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        final String METHOD_NAME = "print(Graphics,PageFormat,int)";
        
        Graphics2D g2d = (Graphics2D) graphics;
        if (null != g2d) {
            File f = new File("data/images/recordsheets/" + getSVGFileName());
            svgDocument = null;
            try {
                InputStream is = new FileInputStream(f);
                DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
                final String parser = XMLResourceDescriptor.getXMLParserClassName();
                SAXDocumentFactory df = new SAXDocumentFactory(impl, parser);
                svgDocument = df.createDocument(f.toURI().toASCIIString(), is);
            } catch (Exception e) {
                MegaMekLab.getLogger().log(PrintRecordSheet.class, METHOD_NAME, e);
            }
            if (null == svgDocument) {
                MegaMekLab.getLogger().log(PrintRecordSheet.class, METHOD_NAME,
                        LogLevel.ERROR,
                        "Failed to open Mech SVG file! Path: data/images/recordsheets/" + getSVGFileName());
            } else {
                svgGenerator = new SVGGraphics2D(svgDocument);
                printImage(g2d, pageFormat, pageIndex - firstPage);
                GraphicsNode node = build();
                node.paint(g2d);
            }
        }
        return Printable.PAGE_EXISTS;
    }
    
    protected GraphicsNode build() {
        GVTBuilder builder = new GVTBuilder();
        BridgeContext ctx = new BridgeContext(new UserAgentAdapter() {
            @Override
            // If an image can't be rendered we'll log it and return an empty document in its place
            // rather than throwing an exception.
            public SVGDocument getBrokenLinkDocument(Element e, String url, String message) {
                MegaMekLab.getLogger().log(PrintRecordSheet.class, "build()",
                        LogLevel.WARNING, "Cannot render image: " + message);
                DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
                SVGDocument doc = (SVGDocument) impl.createDocument(svgNS, "svg", null);
                Element text = doc.createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
                text.setTextContent("?");
                doc.getDocumentElement().appendChild(text);
                return doc;
            }
        });
        ctx.setDynamic(true);
        return builder.build(ctx, svgDocument);
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
     * @param graphics   The graphics object passed by {@link Printable#print(Graphics, PageFormat, int) print}
     * @param pageFormat The page format passed by {@link Printable#print(Graphics, PageFormat, int) print}
     * @param pageNum    Indicates which page of multi-page sheets to print. The first page is 0.
     * 
     * @throws PrinterException
     */
    protected abstract void printImage(Graphics2D g2d, PageFormat pageFormat, int pageNum)
            throws PrinterException;

    protected abstract String getSVGFileName();
    
    /**
     * @return The title to use for the record sheet
     */
    protected abstract String getRecordSheetTitle();
    
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
        Element element = svgDocument.getElementById(id);
        if (null != element) {
            if (null == text) {
                hideElement(element, true);
            } else {
                if (unhide) {
                    hideElement(element, false);
                }
                element.setTextContent(text);
            }
        }
    }
    
    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent.  The height of the text is
     * returned, to aid in layout.
     * 
     * @param parent    The SVG element to add the text element to.
     * @param x         The X position of the new element.
     * @param y         The Y position of the new element.
     * @param text      The text to display.
     * @param fontSize  Font size of the text.
     * @param anchor    Set the Text elements text-anchor.  Should be either start, middle, or end.
     * @param weight    The font weight, either normal or bold.
     */
    protected double addTextElement(Element parent, double x, double y, String text,
            float fontSize, String anchor, String weight) {
        return addTextElement(parent, x, y, text, fontSize, anchor, weight, "#000000");
    }
    
    /**
     * Convenience method for creating a new SVG Text element and adding it to the parent.  The height of the text is
     * returned, to aid in layout.
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
        Element newText = svgDocument.createElementNS(svgNS, SVGConstants.SVG_TEXT_TAG);
        newText.setTextContent(text);
        newText.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(x));
        newText.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, String.valueOf(y));
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, "Eurostile");
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_SIZE_ATTRIBUTE, fontSize + "px");
        newText.setAttributeNS(null, SVGConstants.SVG_FONT_WEIGHT_ATTRIBUTE, weight);
        newText.setAttributeNS(null, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, anchor);
        newText.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, fill);
        parent.appendChild(newText);
        
        return getTextLength(text, fontSize);
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
                text, fontSize, anchor, weight, "#000000", ' ');
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
        int pos = 0;
        while (text.length() > 0) {
            if (getTextLength(text, fontSize) <= width) {
                addTextElement(canvas, x, y, text, fontSize, anchor, weight, fill);
                lines++;
                return lines;
            }
            int index = text.substring(pos).indexOf(delimiter);
            if ((index < 0) && (pos == 0)) {
                addTextElement(canvas, x, y, text, fontSize, anchor, weight, fill);
                lines++;
                return lines;
            }
            if ((index < 0) || (getTextLength(text.substring(0, pos + index), fontSize) > width)) {
                addTextElement(canvas, x, y, text.substring(0, pos), fontSize, anchor, weight, fill);
                lines++;
                y += lineHeight;
                text = text.substring(pos);
                pos = 0;
            } else if (index > 0) {
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
        return createPip(x, y, radius, strokeWidth, PipType.CIRCLE);
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
            PipType type) {
        Element path = svgDocument.createElementNS(svgNS, SVGConstants.SVG_PATH_TAG);
        path.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, "none");
        path.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, "black");
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
    
    protected void addPips(Element group, int pipCount, boolean symmetric) {
        addPips(group, pipCount, symmetric, PipType.CIRCLE, DEFAULT_PIP_SIZE);
    }
    
    protected void addPips(Element group, int pipCount, boolean symmetric, PipType pipType) {
        addPips(group, pipCount, symmetric, pipType, DEFAULT_PIP_SIZE);
    }
    
    protected void addPips(Element group, int pipCount, boolean symmetric, double size) {
        addPips(group, pipCount, symmetric, PipType.CIRCLE, size);
    }

    protected void addPips(Element group, int pipCount, boolean symmetric, PipType pipType,
            double size) {
        addPips(group, pipCount, symmetric, pipType, size, 0.5);
    }
    
    /**
     * Adds pips to the SVG diagram. The rows are defined in the SVG diagram with a series of <rect>
     * elements, each of which determines the bounds of a row of pips. The spacing between pips is
     * determined by the height of the first row. If rows overlap the pips are offset by half in the next
     * row. 
     * 
     * @param group           A <g> element that has <rect> children that describe pip rows
     * @param pipCount        The number of pips to place in the region
     * @param symmetric       If true, the left and right sides will be mirror images (assuming the row
     *                        bounds are symmetric). Used for regions on a unit's center line.
     * @param size            The ratio of pip radius to the spacing between pips. 
     * @param strokeWidth     The value to use for the stroke-width attribute when drawing the pips.
     */
    protected void addPips(Element group, int pipCount, boolean symmetric, PipType pipType,
            double size, double strokeWidth) {
        
        if (pipCount == 0) {
            return;
        }
        
        final String METHOD_NAME = "addArmorPips(SVGElement,int)";
        double spacing = 6.15152;
        double left = Double.MAX_VALUE;
        double top = Double.MAX_VALUE;
        double right = 0;
        double bottom = 0;
        List<Rectangle2D> regions = new ArrayList<>();
        for (int i = 0; i < group.getChildNodes().getLength(); i++) {
            final Node r = group.getChildNodes().item(i);
            if (r instanceof SVGRectElement) {
                Rectangle2D bbox = getRectBBox((SVGRectElement) r);
                if (bbox.getX() < left) {
                    left = bbox.getX();
                }
                if (bbox.getY() < top) {
                    top = bbox.getY();
                }
                if (bbox.getX() + bbox.getWidth() > right) {
                    right = bbox.getX() + bbox.getWidth();
                }
                if (bbox.getY() + bbox.getHeight() > bottom) {
                    bottom = bbox.getY() + bbox.getHeight();
                }
                regions.add(bbox);
            }
        }
        if (regions.isEmpty()) {
            MegaMekLab.getLogger().log(getClass(), METHOD_NAME, LogLevel.WARNING,
                    "No pip rows defined for region " + group.getAttribute("id"));
            return;
        }
        
        Rectangle2D bounds = new Rectangle2D.Double(left, top, right - left, bottom - top);
        double aspect = bounds.getWidth() / bounds.getHeight();
        double centerLine = regions.get(0).getX() + regions.get(0).getWidth() / 2.0;
        
        int maxWidth = 0;
        
        Collections.sort(regions, (r1, r2) -> (int) r1.getY() - (int) r2.getY());
        // Maximum number of pips that can be displayed on each row
        int[] rowLength = null;
        int[][] halfPipCount = null;
        int totalPips = 0;
        double scale = 1.0;
        List<Rectangle2D> rows = null;
        while (totalPips < pipCount) {
            totalPips = 0;
            rows = rescaleRows(regions, scale);
            rowLength = new int[rows.size()];
            halfPipCount = new int[rows.size()][];
            
            double prevRowBottom = 0;
            int centerPip = 0;
            spacing = rows.stream().mapToDouble(Rectangle2D::getHeight).min().orElse(spacing);
            for (int i = 0; i < rows.size(); i++) {
                final Rectangle2D rect = rows.get(i);
                int halfPipsLeft = (int) ((centerLine - rect.getX()) / (spacing / 2));
                int halfPipsRight = (int) ((rect.getX() + rect.getWidth() - centerLine) / (spacing / 2));
                if ((i > 0) && (rect.getY() < prevRowBottom)) {
                    centerPip = (1 - centerPip);
                    if (halfPipsLeft %2 != centerPip) {
                        halfPipsLeft--;
                    }
                    if (halfPipsRight %2 != centerPip) {
                        halfPipsRight--;
                    }
                    rowLength[i] = (halfPipsLeft + halfPipsRight) / 2;
                } else {
                    rowLength[i] = (halfPipsLeft + halfPipsRight) / 2;
                    centerPip = rowLength[i] % 2;
                }
                if (rowLength[i] > maxWidth) {
                    maxWidth = rowLength[i];
                }
                halfPipCount[i] = new int[] { halfPipsLeft, halfPipsRight };
                totalPips += rowLength[i];
                prevRowBottom = rect.getY() + spacing;
            }
            
            scale *= 0.9;
        };
        
        int nRows = adjustedRows(pipCount, rows.size(), maxWidth, aspect);
        
        // Now we need to select the rows to use. If the total pips available in those rows is
        // insufficient, add a row and try again.
        
        int available = 0;
        int minWidth = maxWidth;
        List<Integer> useRows = new ArrayList<>();
        while (available < pipCount) {
            int start = rows.size() / (nRows * 2);
            for (int i = 0; i < nRows; i++) {
                int r = start + i * rows.size() / nRows;
                if (rowLength[r] > 0) {
                    useRows.add(r);
                    available += rowLength[r];
                    if (rowLength[r] < minWidth) {
                        minWidth = rowLength[r];
                    }
                }
            }
            if (available < pipCount) {
                nRows++;
                available = 0;
                useRows.clear();
                minWidth = maxWidth;
            }
        }
        
        // Sort the rows into the order pips should be added: longest rows first, then for rows of
        // equal length the one closest to the middle first
        final int rowCount = rows.size();
        final int[] rowSize = Arrays.copyOf(rowLength, rowLength.length);
        Collections.sort(useRows, (r1, r2) -> {
            if (rowSize[r1] == rowSize[r2]) {
                return Math.abs(r1 - rowCount / 2) - Math.abs(r2 - rowCount / 2);
            } else {
                return rowSize[r2] - rowSize[r1];
            }
        });
        
        // Now we iterate through the rows and assign pips as many times as it takes to get all assigned.
        int[] pipsByRow = new int[rows.size()];
        int remaining = pipCount;
        while (remaining > 0) {
            for (int r : useRows) {
                if (rowLength[r] > pipsByRow[r]) {
                    int toAdd = Math.min(remaining,
                            Math.min(rowLength[r] / minWidth, rowLength[r] - pipsByRow[r]));
                    pipsByRow[r] += toAdd;
                    remaining -= toAdd;
                }
            }
        }
        
        // Locations on the unit's center line require that rows with an even width don't get assigned
        // an odd number of pips.
        if (symmetric) {
            // First we remove all the odd pips in even rows
            remaining = 0;
            for (int r = 0; r < rows.size(); r++) {
                if ((rowLength[r] % 2 == 0) && (pipsByRow[r] % 2 == 1)) {
                    pipsByRow[r]--;
                    remaining++;
                }
            }
            // Now we go through all the selected rows and assign them; this time even rows can
            // only be assigned pips in pairs.
            int toAdd = 0;
            boolean added = false;
            do {
                for (int r : useRows) {
                    toAdd = 2 - rowLength[r] % 2;
                    if ((remaining >= toAdd) && (pipsByRow[r] + toAdd <= rowLength[r])) {
                        pipsByRow[r] += toAdd;
                        remaining -= toAdd;
                    }
                }
            } while ((remaining > 0) && added);
            
            // We may still have one or more left. At this point all rows are considered available.
            int centerRow = rows.size() / 2;
            while (remaining > 0) {
                for (int i = 0; i <= centerRow; i++) {
                    int r = centerRow - i;
                    toAdd = 2 - rowLength[r] % 2;
                    if (remaining < toAdd) {
                        continue;
                    }
                    if (rowLength[r] >= pipsByRow[r] + toAdd) {
                        pipsByRow[r] += toAdd;
                        remaining -= toAdd;
                    }
                    if (i > 0) {
                        r = centerRow + i;
                        if (r >= rows.size()) {
                            continue;
                        }
                        toAdd = 2 - rowLength[r] % 2;
                        if (remaining < toAdd) {
                            continue;
                        }
                        if (rowLength[r] >= pipsByRow[r] + toAdd) {
                            pipsByRow[r] += toAdd;
                            remaining -= toAdd;
                        }
                    }
                }
                // Possible gotcha: one remaining pip to allocate and the only rows with empty space have
                // an even number of pips. In that case remove one from an odd row and assign it along
                // with the remaining pip to one of the even rows.
                if (remaining == 1) {
                    boolean noSingle = true;
                    int fromRow = -1;
                    for (int r = 0; r < rows.size(); r++) {
                        if (rowLength[r] % 2 == 1) {
                            if (pipsByRow[r] < rowLength[r]) {
                                noSingle = false;
                                break;
                            } else {
                                fromRow = r;
                            }
                        }
                    }
                    if (noSingle) {
                        pipsByRow[fromRow]--;
                        remaining++;
                    }
                }
            }
        }
        
        // It's likely that there's extra spacing between rows, so we're going to check whether
        // we can increase horizontal spacing between pips to keep the approximate aspect ratio.
        
        int firstRow = 0;
        int lastRow = rows.size() - 1;
        int r = 0;
        while (r < rows.size()) {
            if (pipsByRow[r] > 0) {
                firstRow = r;
                break;
            }
            r++;
        }
        r = rows.size() - 1;
        while (r >= 0) {
            if (pipsByRow[r] > 0) {
                lastRow = r;
                break;
            }
            r--;
        }
        double targetWidth = aspect * (rows.get(lastRow).getY() + rows.get(lastRow).getHeight()
                - rows.get(firstRow).getY());
        double hSpacing = targetWidth / pipsByRow[firstRow] - spacing;
        for (r = firstRow + 1; r <= lastRow; r++) {
            if (pipsByRow[r] > 0) {
                hSpacing = Math.min(hSpacing, (Math.min(targetWidth, rows.get(r).getWidth()) - spacing) / pipsByRow[r]);
            }
        }
        if (hSpacing < spacing) {
            hSpacing = spacing;
        }
        
        for (r = 0; r < pipsByRow.length; r++) {
            if (pipsByRow[r] > 0) {
                double radius = rows.get(r).getHeight() * size;
                Element pip = null;
                // Symmetric and this row is centered
                if (symmetric && (halfPipCount[r][0] == halfPipCount[r][1])) {
                    double leftX = centerLine - hSpacing;
                    double rightX = centerLine;
                    if (rowLength[r] % 2 == 1) {
                        leftX -= radius;
                        rightX += hSpacing - radius;
                        if (pipsByRow[r] % 2 == 1) {
                            pip = createPip(leftX + hSpacing, rows.get(r).getY(), radius, strokeWidth, pipType);
                            group.appendChild(pip);
                            pipsByRow[r]--;
                        }
                    } else {
                        leftX += hSpacing / 2 - radius;
                        rightX += hSpacing / 2 - radius;
                    }
                    while (pipsByRow[r] > 0) {
                        pip = createPip(leftX, rows.get(r).getY(), radius, strokeWidth, pipType);
                        group.appendChild(pip);
                        pip = createPip(rightX, rows.get(r).getY(), radius, strokeWidth, pipType);
                        group.appendChild(pip);
                        leftX -= hSpacing;
                        rightX += hSpacing;
                        pipsByRow[r] -= 2;
                    }
                } else {
                    // If the location is symmetric but the middle of the current row is to the left
                    // of the centerline, right justify. If non-symmetric, balance the extra space at the
                    // ends of the rows with any odd space going on the right margin.
                    double x = centerLine - halfPipCount[r][0] * spacing / 2.0;
                    if (symmetric && halfPipCount[r][0] > halfPipCount[r][1]) {
                        x += (rowLength[r] - pipsByRow[r]) * hSpacing;
                    } else if (!symmetric) {
                        x += ((rowLength[r] - pipsByRow[r]) / 2) * hSpacing;
                    }
                    while (pipsByRow[r] > 0) {
                        pip = createPip(x, rows.get(r).getY(), radius, strokeWidth, pipType);
                        group.appendChild(pip);
                        pipsByRow[r]--;
                        x += hSpacing;
                    }
                }
            }
        }
    }
    
    /**
     * Creates a new set pip row regions sized according to the scaling factor.
     * 
     * @param list  The rectangular regions describing pip rows in the SVG diagram.
     * @param scale The scaling factor
     * @return      A list of rectangular regions scaled according to the provided factor.
     */
    private List<Rectangle2D> rescaleRows(List<Rectangle2D> rows, double scale) {
        if (rows.isEmpty() || (rows.size() == Math.floor(rows.size() * scale))) {
            return rows;
        }
        List<Rectangle2D> retVal = new ArrayList<>();
        // We need to account for the possibility of gaps between some rows, so we split the
        // list into sublists of contiguous regions.
        List<List<Rectangle2D>> groups = new ArrayList<>();
        List<Rectangle2D> group = new ArrayList<>();
        for (int r = 0; r < rows.size(); r++) {
            final Rectangle2D rect = rows.get(r);
            group.add(rows.get(r));
            if ((r + 1 < rows.size()) && rows.get(r + 1).getY() > rect.getY() + rect.getHeight()) {
                groups.add(group);
                group = new ArrayList<>();
            }
        }
        if (group.size() > 0) {
            groups.add(group);
        }
        
        for (List<Rectangle2D> list : groups) {
            Rectangle2D rect = list.get(0);
            Rectangle2D rect2 = null;
            double yPos = rect.getY();
            double height = list.get(list.size() - 1).getY() + list.get(list.size() - 1).getHeight();
            double dy = scale * height / list.size();
            double rowHeight = dy / 0.866;
            
            int r = 0;
            while ((r < list.size()) && (yPos + rowHeight <= height)) {
                rect = list.get(r);
                if (r + 1 < list.size()) {
                    rect2 = list.get(r + 1);
                } else {
                    rect2 = null;
                }
                
                if ((rect2 == null) || (rect2.getY() > yPos)) {
                    retVal.add(new Rectangle2D.Double(rect.getX(), yPos,
                            rect.getWidth(), rowHeight));
                } else {
                    double left = Math.max(rect.getX(), rect2.getX());
                    double right = Math.min(rect.getX() + rect.getWidth(), rect2.getX() + rect2.getWidth());
                    retVal.add(new Rectangle2D.Double(left, yPos, right - left, rowHeight));
                }
                
                yPos += dy;
                if (yPos > rect.getY() + rect.getHeight()) {
                    r++;
                }
            }
        }
        
        return retVal;
    }
    
    /**
     * Calculate how many rows to use to give the pip pattern the approximate aspect ratio of the region
     * 
     * @param pipCount  The number of pips to display
     * @param maxRows   The maximum number of rows in the region
     * @param maxWidth  The number of pips in the longest row
     * @param aspect    The aspect ratio of the region (w/h)
     * @return          The number of rows to use in the pattern
     */
    private int adjustedRows(int pipCount, int maxRows, int maxWidth, double aspect) {
        double nRows = Math.min(pipCount,  maxRows);
        double width = Math.ceil(pipCount / nRows);
        double pipAspect = width / nRows;
        double sqrAspect = aspect * aspect;
        if (aspect <= 1) {
            while ((width < maxWidth) && (nRows > 1)) {
                double tmpWidth = width + 1;
                double tmpRows = Math.ceil(pipCount / tmpWidth);
                double tmpAspect = tmpWidth / tmpRows;
                if (pipAspect * tmpAspect / sqrAspect < 2) {
                    width = tmpWidth;
                    nRows = tmpRows;
                    pipAspect = tmpAspect;
                } else {
                    break;
                }
            }
        } else {
            while ((nRows < maxRows) && (width > 1)) {
                double tmpRows = nRows + 1;
                double tmpWidth = Math.ceil(pipCount / tmpRows);
                double tmpAspect = tmpWidth / tmpRows;
                if (pipAspect * tmpAspect / sqrAspect > 2) {
                    width = tmpWidth;
                    nRows = tmpRows;
                    pipAspect = tmpAspect;
                } else {
                    break;
                }
            }
        }
        return (int) nRows;
    }
    
    // Older method that was unsuitable for mechs but could work for vees and aerospace. Would need
    // some updating to work with regions rather than fixed pips in the SVG.
    protected void setArmorPips(Element group, int armorVal, boolean symmetric) {
        final String METHOD_NAME = "setArmorPips(SVGElement,int)";
        // First sort pips into rows. We can't rely on the pips to be in order, so we use
        // maps to allow non-sequential loading.
        Map<Integer,Map<Integer,Element>> rowMap = new TreeMap<>();
        int pipCount = 0;
        for (int i = 0; i < group.getChildNodes().getLength(); i++) {
            final Node n = group.getChildNodes().item(i);
            if (n instanceof SVGElement) {
                final SVGElement pip = (SVGElement) n;
                try {
                    int index = pip.getId().indexOf(":");
                    String[] coords = pip.getId().substring(index + 1).split(",");
                    int r = Integer.parseInt(coords[0]);
                    rowMap.putIfAbsent(r, new TreeMap<>());
                    rowMap.get(r).put(Integer.parseInt(coords[1]), pip);
                    pipCount++;
                } catch (Exception ex) {
                    MegaMekLab.getLogger().log(getClass(), METHOD_NAME, LogLevel.ERROR,
                            "Malformed id for SVG armor pip element: " + pip.getId());
                }
            }
        }
        if (pipCount < armorVal) {
            MegaMekLab.getLogger().log(getClass(), METHOD_NAME, LogLevel.ERROR,
                    "Armor pip group " + ((SVGElement) group).getId() + " does not contain enough pips for " + armorVal + " armor");
            return;
        } else if (pipCount == armorVal) {
            // Simple case; leave as is
            return;
        }
        // Convert map into array for easier iteration in both directions. This will also skip
        // over gaps in the numbering.
        Element[][] rows = new Element[rowMap.size()][];
        int row = 0;
        for (Map<Integer,Element> r : rowMap.values()) {
            rows[row] = new Element[r.size()];
            int i = 0;
            for (Element e : r.values()) {
                rows[row][i] = e;
                i++;
            }
            row++;
        }
        
        // Get the ratio of the number of pips to show to the total number of pips
        // and distribute the number of pips proportionally to each side
        double saturation = Math.min(1.0, (double) armorVal / pipCount);
        
        // Now we find the center row, which is the row that has the same number of pips above
        // and below it as nearly as possible.
        
        int centerRow = rows.length / 2;
        int pipsAbove = 0;
        for (int r = 0; r < rows.length; r++) {
            pipsAbove += rows[r].length;
            if (pipsAbove > pipCount / 2) {
                centerRow = r;
                break;
            }
        }
        int showAbove = (int) Math.round(pipsAbove * saturation);
        int showBelow = armorVal - showAbove;
        // keep a running total of the number to hide in each row
        int[] showByRow = new int[rows.length];
        double remaining = pipsAbove;
        for (int i = centerRow; i >= 0; i--) {
            showByRow[i] = (int) Math.round(rows[i].length * showAbove / remaining);
            if (symmetric && (showByRow[i] > 0) && (showByRow[i] % 2) != (rows[i].length % 2)) {
                if ((showByRow[i] < showAbove) && (showByRow[i] < rows[i].length)) {
                    showByRow[i]++;
                } else {
                    showByRow[i]--;
                }
            }
            showAbove -= showByRow[i];
            remaining -= rows[i].length;
        }
        // We may have some odd ones left over due to symmetry imposed on middle pip of the row
        showBelow += showAbove;
        remaining = pipCount - pipsAbove;
        for (int i = centerRow + 1; i < rows.length; i++) {
            showByRow[i] = (int) Math.round(rows[i].length * showBelow / remaining);
            if (symmetric && (showByRow[i] > 0) && (showByRow[i] % 2) != (rows[i].length % 2)) {
                if ((showByRow[i] < showBelow) && (showByRow[i] < rows[i].length)) {
                    showByRow[i]++;
                } else {
                    showByRow[i]--;
                }
            }
            showBelow -= showByRow[i];
            remaining -= rows[i].length;
        }
        
        // Now we need to deal with leftovers, starting in the middle and adding one or two at a time
        // (depending on whether there are an odd or even number of pips in the row) moving out toward
        // the top and bottom and repeating until they are all placed.
        
        remaining = showBelow;
        while (remaining > 0) {
            for (int i = 0; i <= centerRow; i++) {
                row = centerRow - i;
                int toAdd = symmetric? (2 - rows[row].length % 2) : 1;
                if (remaining < toAdd) {
                    continue;
                }
                if (rows[row].length >= showByRow[row] + toAdd) {
                    showByRow[row] += toAdd;
                    remaining -= toAdd;
                }
                if (i > 0) {
                    row = centerRow + i;
                    if (row >= rows.length) {
                        continue;
                    }
                    toAdd = symmetric? (2 - rows[row].length % 2) : 1;
                    if (remaining < toAdd) {
                        continue;
                    }
                    if (rows[row].length >= showByRow[row] + toAdd) {
                        showByRow[row] += toAdd;
                        remaining -= toAdd;
                    }
                }
            }
        }
        
        // Now select which pips in each row to hide
        for (row = 0; row < rows.length; row++) {
            int toHide = rows[row].length - showByRow[row];
            if (toHide == 0) {
                continue;
            }
            double ratio = (double) toHide / rows[row].length;
            int length = rows[row].length;
            if (symmetric) {
                length /= 2;
                if (toHide % 2 == 1) {
                    hideElement(rows[row][length]);
                    toHide--;
                    ratio = (double) toHide / (rows[row].length - 1);
                }
            }
            Set<Integer> indices = new HashSet<>();
            double accum = 0.0;
            for (int i = length % 2; i < length; i += 2) {
                accum += ratio;
                if (accum >= 1 -saturation) {
                    indices.add(i);
                    accum -= 1.0;
                    toHide--;
                    if (symmetric) {
                        indices.add(rows[row].length - 1 - i);
                        toHide--;
                    }
                }
                if (toHide == 0) {
                    break;
                }
            }
            if (toHide > 0) {
                for (int i = length - 1; i >= 0; i -= 2) {
                    accum += ratio;
                    if (accum >= saturation) {
                        indices.add(i);
                        accum -= 1.0;
                        toHide--;
                        if (symmetric) {
                            indices.add(rows[row].length - 1 - i);
                            toHide--;
                        }
                    }
                    if (toHide == 0) {
                        break;
                    }
                }
            }
            int i = 0;
            while (toHide > 0) {
                if (!indices.contains(i)) {
                    indices.add(i);
                    toHide--;
                    if (symmetric) {
                        indices.add(rows[row].length - 1 - i);
                        toHide--;
                    }
                }
                i++;
            }
            for (int index : indices) {
                hideElement(rows[row][index]);
            }
        }
    }
    
    protected void hideElement(String id) {
        Element element = svgDocument.getElementById(id);
        if (null != element) {
            hideElement(element, true);
        }
    }
    
    protected void hideElement(String id, boolean hide) {
        Element element = svgDocument.getElementById(id);
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
     * @param fontSize  Value of CSS font-family attribute
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
    public void embedImage(File imageFile, Element canvas, Rectangle2D bbox, boolean center) {
        final String METHOD_NAME = "addFluffImage(File,Rectangle2D)";
        if (null == imageFile) {
            return;
        }
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(imageFile));
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            String format = mimeType.substring(mimeType.indexOf("/") + 1);

            RenderedImage fluffImage = ImageIO.read(imageFile);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ImageIO.write(fluffImage, format, bytes);
            
            double width = fluffImage.getWidth();
            double height = fluffImage.getHeight();
            double scale = Math.min(bbox.getWidth() / width, bbox.getHeight() / height);
            width *= scale;
            height *= scale;
            double x = bbox.getX();
            double y = bbox.getY();
            if (center) {
                x += (bbox.getWidth() - width) / 2;
                y += (bbox.getHeight() - height) / 2;
            }
            Element img = svgDocument.createElementNS(svgNS, SVGConstants.SVG_IMAGE_TAG);
            img.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, Double.toString(x));
            img.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, Double.toString(y));
            img.setAttributeNS(null, SVGConstants.SVG_WIDTH_ATTRIBUTE, Double.toString(width));
            img.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, Double.toString(height));
            img.setAttributeNS(SVGConstants.XLINK_NAMESPACE_URI, SVGConstants.XLINK_HREF_QNAME,
                    "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(bytes.toByteArray()));
            canvas.appendChild(img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            MegaMekLab.getLogger().log(PrintRecordSheet.class, METHOD_NAME, LogLevel.ERROR,
                    "Fluff image file not found: " + imageFile.getPath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            MegaMekLab.getLogger().log(PrintRecordSheet.class, METHOD_NAME, LogLevel.ERROR,
                    "Error reading fluff image file: " + imageFile.getPath());
        }
        
    }
}
