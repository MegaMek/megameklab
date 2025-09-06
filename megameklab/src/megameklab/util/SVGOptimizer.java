/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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

package megameklab.util;

import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import megameklab.printing.IdConstants;
import megameklab.printing.PrintRecordSheet;
import org.apache.batik.parser.TransformListHandler;
import org.apache.batik.parser.TransformListParser;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

/**
 * @author drake Utility class for optimizing SVG documents. This class provides methods to clean up and optimize SVG
 *       files by removing unnecessary metadata, simplifying transforms, rounding float values, and more.
 */
public class SVGOptimizer {
    private static final String REPLACEMENT_FONT = "Roboto";
    private static final DecimalFormat DECIMAL_FORMAT3 = new DecimalFormat("#.###",
          DecimalFormatSymbols.getInstance(Locale.US));
    private static final DecimalFormat DECIMAL_FORMAT4 = new DecimalFormat("#.####",
          DecimalFormatSymbols.getInstance(Locale.US));
    private static final Pattern RGB_PATTERN = Pattern.compile("rgb\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d*\\.\\d+|-?\\d+");
    private static final Pattern URL_ID_PATTERN = Pattern.compile("url\\(#(.*?)\\)");
    private static final Pattern DEFAULT_IDS = Pattern.compile("^(text|tspan|g|q|rect|path|svg|polygon)\\d+$");
    private static final Map<String, String> DEFAULT_ATTRIBUTE_VALUES = Map.ofEntries(
          Map.entry("fill", "black"),
          Map.entry("stroke", "none"),
          Map.entry("stroke-width", "1"),
          Map.entry("fill-opacity", "1"),
          Map.entry("stroke-opacity", "1"),
          Map.entry("opacity", "1"),
          Map.entry("text-anchor", "start"),
          Map.entry("visibility", "visible"),
          Map.entry("font-style", "normal"),
          Map.entry("font-weight", "normal"),
          Map.entry("fill-rule", "nonzero"),
          Map.entry("stroke-linecap", "butt"),
          Map.entry("stroke-linejoin", "miter"),
          Map.entry("stroke-miterlimit", "4"),
          Map.entry("stroke-dasharray", "none"),
          Map.entry("stroke-dashoffset", "0")
    );
    private static final Map<String, String> COLOR_KEYWORDS = Map.of("black", "#000000");
    private static final Set<String> PRESERVE_IDS_PREFIXES = Set.of(
          IdConstants.CREW_NAME, IdConstants.BLANKS_CREW, IdConstants.BLANK_CREW_NAME, IdConstants.PILOT_NAME,
          IdConstants.GUNNERY_SKILL, IdConstants.PILOTING_SKILL, IdConstants.BLANK_GUNNERY_SKILL,
          IdConstants.BLANK_PILOTING_SKILL, IdConstants.GUNNERY_SKILL_TEXT, IdConstants.PILOTING_SKILL_TEXT,
          IdConstants.ASF_GUNNERY_SKILL, IdConstants.ASF_PILOTING_SKILL, IdConstants.ASF_BLANK_GUNNERY_SKILL,
          IdConstants.ASF_BLANK_PILOTING_SKILL
    );
    private static final Map<String, Integer> ATTRIBUTE_ORDER;

    static {
        List<String> order = List.of(
              "id", "width", "height", "x", "x1", "x2", "y", "y1", "y2",
              "cx", "cy", "r", "fill", "stroke", "marker", "d", "points"
        );
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < order.size(); i++) {
            map.put(order.get(i), i);
        }
        ATTRIBUTE_ORDER = Collections.unmodifiableMap(map);
    }

    private SVGOptimizer() {
        // Utility class
    }

    public static void optimize(SVGDocument document) {
        if (document == null) {
            return;
        }
        Element root = document.getDocumentElement();
        if (root == null) {
            return;
        }

        // Optimize
        removeMetadata(document);
        removeEditorMetadata(document);
        removeComments(document);
        roundFloatValues(root);
        simplifyTransformsParams(root);
        simplifyPathData(root);
        removeInvisiblePreservedIdGroups(root);
        removeInvisibleElements(root);
        removeNonRenderingElements(root);
        final String desiredTypeface = CConfig.getParam(CConfig.RS_FONT, PrintRecordSheet.DEFAULT_TYPEFACE);
        if (!PrintRecordSheet.DEFAULT_TYPEFACE.equals(desiredTypeface)) {
            replaceFonts(document, PrintRecordSheet.DEFAULT_TYPEFACE, desiredTypeface);
        }
        optimizeColors(root);

        final Set<String> referencedIds = getReferencedIds(document);
        removeUnusedDefs(document, referencedIds);
        removeUnusedPatterns(document, referencedIds);
        //        removeUnusedIds(document, referencedIds); // This is too aggressive, it removes IDs that are later used.
        removedDefaultIds(document);
        optimizeAttributes(root);
        removeWhitespaceNodes(document);
        collapseEmptyGroups(root);
        //        sortAttributes(document); //Too slow!
    }

    private static void replaceFonts(SVGDocument doc, String oldFont, String newFont) {
        // Replace in text elements
        replaceTextElementFonts(doc, oldFont, newFont);
        // Replace in style elements and style attributes
        replaceStyleFonts(doc, oldFont, newFont);
        // Replace in CSS within <defs> or <style> elements
        replaceCSSFonts(doc, oldFont, newFont);
    }

    private static void replaceTextElementFonts(SVGDocument doc, String oldFont, String newFont) {
        NodeList textElements = doc.getElementsByTagName(SVGConstants.SVG_TEXT_TAG);
        for (int i = 0; i < textElements.getLength(); i++) {
            Element element = (Element) textElements.item(i);

            // Replace font-family attribute
            if (element.hasAttribute(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE)) {
                String fontFamily = element.getAttribute(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE);
                if (fontFamily.contains(oldFont)) {
                    String newFontFamily = fontFamily.replace(oldFont, newFont);
                    element.setAttribute(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, newFontFamily);
                }
            }

            // Replace in style attribute
            if (element.hasAttribute(SVGConstants.SVG_STYLE_TAG)) {
                String style = element.getAttribute(SVGConstants.SVG_STYLE_TAG);
                String newStyle = replaceFontInStyle(style, oldFont, newFont);
                if (!newStyle.equals(style)) {
                    element.setAttribute(SVGConstants.SVG_STYLE_TAG, newStyle);
                }
            }
        }

        // Also check tspan elements (which can be inside text elements)
        NodeList tspanElements = doc.getElementsByTagName(SVGConstants.SVG_TSPAN_TAG);
        for (int i = 0; i < tspanElements.getLength(); i++) {
            Element element = (Element) tspanElements.item(i);

            if (element.hasAttribute(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE)) {
                String fontFamily = element.getAttribute(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE);
                if (fontFamily.contains(oldFont)) {
                    String newFontFamily = fontFamily.replace(oldFont, newFont);
                    element.setAttribute(SVGConstants.SVG_FONT_FAMILY_ATTRIBUTE, newFontFamily);
                }
            }

            if (element.hasAttribute(SVGConstants.SVG_STYLE_TAG)) {
                String style = element.getAttribute(SVGConstants.SVG_STYLE_TAG);
                String newStyle = replaceFontInStyle(style, oldFont, newFont);
                if (!newStyle.equals(style)) {
                    element.setAttribute(SVGConstants.SVG_STYLE_TAG, newStyle);
                }
            }
        }
    }

    private static void replaceStyleFonts(SVGDocument doc, String oldFont, String newFont) {
        NodeList allElements = doc.getElementsByTagName("*");
        for (int i = 0; i < allElements.getLength(); i++) {
            Element element = (Element) allElements.item(i);

            if (element.hasAttribute(SVGConstants.SVG_STYLE_TAG)) {
                String style = element.getAttribute(SVGConstants.SVG_STYLE_TAG);
                String newStyle = replaceFontInStyle(style, oldFont, newFont);
                if (!newStyle.equals(style)) {
                    element.setAttribute(SVGConstants.SVG_STYLE_TAG, newStyle);
                }
            }
        }
    }

    private static void replaceCSSFonts(SVGDocument doc, String oldFont, String newFont) {
        NodeList styleElements = doc.getElementsByTagName(SVGConstants.SVG_STYLE_TAG);
        for (int i = 0; i < styleElements.getLength(); i++) {
            Element styleElement = (Element) styleElements.item(i);
            String cssContent = styleElement.getTextContent();

            if (cssContent != null && cssContent.contains(oldFont)) {
                // Replace font-family declarations in CSS
                String newCssContent = cssContent.replaceAll(
                      "font-family\\s*:\\s*[^;]*" + Pattern.quote(oldFont) + "[^;]*",
                      "font-family: " + newFont
                );
                styleElement.setTextContent(newCssContent);
            }
        }
    }

    private static String replaceFontInStyle(String style, String oldFont, String newFont) {
        if (style == null || !style.contains(oldFont)) {
            return style;
        }
        return style.replaceAll(
              "font-family\\s*:\\s*[^;]*" + Pattern.quote(oldFont) + "[^;]*",
              "font-family: " + newFont
        );
    }

    private static void optimizeColors(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            String attrName = attr.getName();
            String attrValue = attr.getValue();

            if (isColorAttribute(attrName)) {
                attr.setValue(optimizeColorValue(attrValue));
            } else if (SVGConstants.SVG_STYLE_ATTRIBUTE.equals(attrName)) {
                attr.setValue(optimizeStyleColors(attrValue));
            }
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                optimizeColors((Element) child);
            }
        }
    }

    private static boolean isColorAttribute(String attrName) {
        return "fill".equals(attrName)
              || "stroke".equals(attrName)
              || "stop-color".equals(attrName)
              || "color".equals(attrName);
    }

    private static String optimizeStyleColors(String style) {
        if (style == null || style.isEmpty()) {
            return style;
        }
        String[] declarations = style.split(";");
        List<String> newDeclarations = new ArrayList<>();
        for (String declaration : declarations) {
            if (declaration.trim().isEmpty()) {
                continue;
            }
            String[] parts = declaration.split(":", 2);
            if (parts.length == 2) {
                String prop = parts[0].trim();
                String value = parts[1].trim();
                if (isColorAttribute(prop)) {
                    value = optimizeColorValue(value);
                }
                newDeclarations.add(prop + ":" + value);
            } else {
                newDeclarations.add(declaration);
            }
        }
        return String.join(";", newDeclarations);
    }

    private static String optimizeColorValue(String color) {
        if (color == null || "none".equalsIgnoreCase(color) || "inherit".equalsIgnoreCase(color)
              || "currentColor".equalsIgnoreCase(color)) {
            return color;
        }

        String lowerColor = color.toLowerCase();
        String hexColor = COLOR_KEYWORDS.getOrDefault(lowerColor, color);

        // Convert rgb(r, g, b) to #RRGGBB
        Matcher rgbMatcher = RGB_PATTERN.matcher(hexColor);
        if (rgbMatcher.matches()) {
            try {
                int r = Integer.parseInt(rgbMatcher.group(1));
                int g = Integer.parseInt(rgbMatcher.group(2));
                int b = Integer.parseInt(rgbMatcher.group(3));
                hexColor = String.format("#%02x%02x%02x", r, g, b);
            } catch (NumberFormatException e) {
                return color; // Not a valid rgb color, leave as is.
            }
        }

        hexColor = hexColor.toLowerCase();

        // Collapse #RRGGBB to #RGB
        if (hexColor.length() == 7 && hexColor.startsWith("#")) {
            char r1 = hexColor.charAt(1);
            char r2 = hexColor.charAt(2);
            char g1 = hexColor.charAt(3);
            char g2 = hexColor.charAt(4);
            char b1 = hexColor.charAt(5);
            char b2 = hexColor.charAt(6);
            if (r1 == r2 && g1 == g2 && b1 == b2) {
                return "#" + r1 + g1 + b1;
            }
        }

        return hexColor;
    }

    private static void removeMetadata(Document doc) {
        NodeList metadata = doc.getElementsByTagName(SVGConstants.SVG_METADATA_TAG);
        for (int i = metadata.getLength() - 1; i >= 0; i--) {
            Node node = metadata.item(i);
            node.getParentNode().removeChild(node);
        }
    }

    private static void removeEditorMetadata(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element el = (Element) node;

            String tag = el.getTagName();
            if (tag.contains("sodipodi") || SVGConstants.SVG_TITLE_TAG.equals(tag) || SVGConstants.SVG_DESC_TAG.equals(
                  tag)) {
                if (node.getParentNode() != null) {
                    node.getParentNode().removeChild(node);
                }
                return;
            }

            NamedNodeMap attrs = el.getAttributes();
            for (int i = attrs.getLength() - 1; i >= 0; i--) {
                Node attr = attrs.item(i);
                String name = attr.getNodeName();
                String value = attr.getNodeValue();
                String namespaceUri = attr.getNamespaceURI();

                final String[] editorJunk = { "inkscape", "sodipodi", "adobe", "illustrator" };
                boolean remove = false;
                for (String junk : editorJunk) {
                    if (name.contains(junk)
                          || (namespaceUri != null && namespaceUri.contains(junk))
                          || (name.startsWith("xmlns:") && value.contains(junk))) {
                        remove = true;
                        break;
                    }
                }

                if (remove) {
                    el.removeAttributeNode((Attr) attr);
                }
            }

            if (el.hasAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE)) {
                String style = el.getAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE);
                // Remove all style properties starting with -inkscape
                String newStyle = style.replaceAll("(?i)(?:^|;)\\s*-inkscape[^:;]*:[^;]*(;|$)", ";");
                // Clean up extra semicolons and whitespace
                newStyle = newStyle.replaceAll(";;+", ";").replaceAll("^;+|;+$", "").trim();
                if (newStyle.isEmpty()) {
                    el.removeAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE);
                } else {
                    el.setAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE, newStyle);
                }
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            removeEditorMetadata(children.item(i));
        }
    }

    private static void removeComments(Node node) {
        if (node.getNodeType() == Node.COMMENT_NODE) {
            node.getParentNode().removeChild(node);
            return;
        }
        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            removeComments(children.item(i));
        }
    }

    private static void roundFloatValues(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attr = attributes.item(i);
            String attrName = attr.getNodeName();
            String attrValue = attr.getNodeValue();

            // Skip attributes that may contain embedded data or URLs
            if (shouldSkipAttribute(attrName)) {
                continue;
            }
            // Skip attributes that are not numeric or contain non-numeric values
            if (attrValue != null &&
                  (attrValue.startsWith("data:") ||
                        attrValue.contains("base64") ||
                        attrValue.contains("#"))) {
                continue;
            }

            attr.setNodeValue(roundNumbersInString(attr.getNodeValue()));
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                roundFloatValues((Element) child);
            }
        }
    }

    private static boolean shouldSkipAttribute(String attrName) {
        return attrName.equals("href") ||
              attrName.equals("xlink:href") ||
              attrName.equals("src") ||
              attrName.startsWith("data-") ||
              attrName.equals("d") ||  // path data - handled separately
              attrName.equals("style") ||
              isColorAttribute(attrName);
    }


    private static String roundNumbersInString(String input) {
        if (input == null) {
            return null;
        }
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            try {
                double num = Double.parseDouble(matcher.group(0));
                matcher.appendReplacement(sb, format3(num));
            } catch (NumberFormatException e) {
                // Ignore, not a valid number
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String format3(double val) {
        String formatted = DECIMAL_FORMAT3.format(val);
        if (formatted.startsWith("0.")) {
            return formatted.substring(1);
        }
        if (formatted.startsWith("-0.")) {
            return "-" + formatted.substring(2);
        }
        return formatted;
    }

    private static String format4(double val) {
        String formatted = DECIMAL_FORMAT4.format(val);
        if (formatted.startsWith("0.")) {
            return formatted.substring(1);
        }
        if (formatted.startsWith("-0.")) {
            return "-" + formatted.substring(2);
        }
        return formatted;
    }

    private static void simplifyTransforms(Element element) {
        if (element.hasAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE)) {
            String transform = element.getAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE);
            try {
                AffineTransform combined = parseAndCombineTransform(transform);
                String simplified = formatAffineTransform(combined);
                element.setAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, simplified);
            } catch (Exception e) {
                System.err.println("Failed to parse transform: " + transform);
            }
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                simplifyTransforms((Element) child);
            }
        }
    }

    private static AffineTransform parseAndCombineTransform(String transformStr) throws Exception {
        TransformListParser parser = new TransformListParser();
        final AffineTransform combined = new AffineTransform();

        parser.setTransformListHandler(new TransformListHandler() {
            public void startTransformList() {}

            public void endTransformList() {}

            public void matrix(float a, float b, float c, float d, float e,
                  float f) {combined.concatenate(new AffineTransform(a, b, c, d, e, f));}

            public void rotate(
                  float theta) {combined.concatenate(AffineTransform.getRotateInstance(Math.toRadians(theta)));}

            public void rotate(float theta, float cx, float cy) {
                combined.concatenate(AffineTransform.getRotateInstance(Math.toRadians(theta),
                      cx,
                      cy));
            }

            public void translate(float tx) {combined.concatenate(AffineTransform.getTranslateInstance(tx, 0));}

            public void translate(float tx, float ty) {
                combined.concatenate(AffineTransform.getTranslateInstance(tx,
                      ty));
            }

            public void scale(float sx) {combined.concatenate(AffineTransform.getScaleInstance(sx, sx));}

            public void scale(float sx, float sy) {combined.concatenate(AffineTransform.getScaleInstance(sx, sy));}

            public void skewX(float skx) {
                combined.concatenate(new AffineTransform(1,
                      0,
                      Math.tan(Math.toRadians(skx)),
                      1,
                      0,
                      0));
            }

            public void skewY(float sky) {
                combined.concatenate(new AffineTransform(1,
                      Math.tan(Math.toRadians(sky)),
                      0,
                      1,
                      0,
                      0));
            }
        });

        parser.parse(transformStr);
        return combined;
    }

    private static String formatAffineTransform(AffineTransform at) {
        double[] m = new double[6];
        at.getMatrix(m);
        return String.format("matrix(%s %s %s %s %s %s)",
              format4(m[0]), format4(m[1]), format4(m[2]),
              format4(m[3]), format4(m[4]), format4(m[5]));
    }


    private static void simplifyPathData(Element element) {
        if (SVGConstants.SVG_PATH_TAG.equals(element.getTagName())
              && element.hasAttribute(SVGConstants.SVG_D_ATTRIBUTE)) {
            String d = element.getAttribute(SVGConstants.SVG_D_ATTRIBUTE);
            String simplified = simplifyPathD(d);
            element.setAttribute(SVGConstants.SVG_D_ATTRIBUTE, simplified);
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                simplifyPathData((Element) child);
            }
        }
    }

    private static String simplifyPathD(String d) {
        d = d.replaceAll("[,\\s]+", " ").trim();
        Matcher matcher = NUMBER_PATTERN.matcher(d);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            try {
                double val = Double.parseDouble(matcher.group(0));
                matcher.appendReplacement(sb, format3(val));
            } catch (NumberFormatException e) {
                matcher.appendReplacement(sb, matcher.group(0));
            }
        }
        matcher.appendTail(sb);
        return sb.toString().replaceAll("\\s+", " ").trim();
    }

    private static void roundTransformValues(Element element) {
        if (element.hasAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE)) {
            String transform = element.getAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE);
            String roundedTransform = roundNumbersInTransform(transform);
            element.setAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, roundedTransform);
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                roundTransformValues((Element) child);
            }
        }
    }

    private static String roundNumbersInTransform(String transform) {
        if (transform == null || transform.isEmpty()) {
            return transform;
        }

        Matcher matcher = NUMBER_PATTERN.matcher(transform);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            try {
                double num = Double.parseDouble(matcher.group(0));
                matcher.appendReplacement(sb, format4(num));
            } catch (NumberFormatException e) {
                // Keep original value if it's not a valid number
                matcher.appendReplacement(sb, matcher.group(0));
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static boolean shouldPreserveId(Element element) {
        if (element.hasAttribute(SVGConstants.SVG_ID_ATTRIBUTE)) {
            String id = element.getAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
            for (String prefix : PRESERVE_IDS_PREFIXES) {
                if (id.startsWith(prefix)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void removeInvisiblePreservedIdGroups(Element element) {
        NodeList allElements = element.getElementsByTagName("*");
        for (int testNum = 0; testNum <= 4; testNum++) {
            boolean anyVisible = false;
            List<Element> elementsToRemove = new ArrayList<>();

            for (int i = 0; i < allElements.getLength(); i++) {
                Element el = (Element) allElements.item(i);
                if (el.hasAttribute("id")) {
                    String id = el.getAttribute("id");
                    for (String prefix : PRESERVE_IDS_PREFIXES) {
                        if (id.equals(prefix + testNum)) {
                            String visibility = el.getAttribute("visibility");
                            if (!"hidden".equalsIgnoreCase(visibility)) {
                                anyVisible = true;
                            }
                            elementsToRemove.add(el);
                        }
                    }
                }
            }

            if (!anyVisible) {
                for (Element el : elementsToRemove) {
                    Node parent = el.getParentNode();
                    if (parent != null) {
                        parent.removeChild(el);
                    }
                }
            }
        }
    }

    private static void removeInvisibleElements(Element element) {
        // Check if the current element should be removed
        if (!shouldPreserveId(element)) {
            String display = element.getAttribute("display");
            String visibility = element.getAttribute("visibility");
            String opacity = element.getAttribute(SVGConstants.SVG_OPACITY_ATTRIBUTE);

            if (SVGConstants.SVG_NONE_VALUE.equals(display) || "hidden".equals(visibility) || "0".equals(opacity)) {
                element.getParentNode().removeChild(element);
                return; // Stop further processing for this element
            }
        }

        // Process child nodes
        List<Node> nodesToRemove = new ArrayList<>();
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) child;
                if (!shouldPreserveId(childElement)) {
                    String display = childElement.getAttribute("display");
                    String visibility = childElement.getAttribute("visibility");
                    String opacity = childElement.getAttribute(SVGConstants.SVG_OPACITY_ATTRIBUTE);

                    if (SVGConstants.SVG_NONE_VALUE.equals(display)
                          || "hidden".equals(visibility)
                          || "0".equals(opacity)) {
                        nodesToRemove.add(child);
                    } else {
                        removeInvisibleElements(childElement);
                    }
                } else {
                    removeInvisibleElements(childElement);
                }
            }
        }

        // Remove collected nodes
        for (Node node : nodesToRemove) {
            node.getParentNode().removeChild(node);
        }
    }

    private static void removeNonRenderingElements(Node node) {
        // Collect nodes to remove
        List<Node> nodesToRemove = new ArrayList<>();

        // Process child nodes first
        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeNonRenderingElements(child);
            }
        }

        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return;
        }

        Element element = (Element) node;

        // Skip elements with IDs that should be preserved
        if (shouldPreserveId(element)) {
            return;
        }

        // Skip non-shape elements
        if (!isShapeElement(element)) {
            return;
        }

        // Check rendering properties
        String fill = getComputedStyle(element, SVGConstants.SVG_FILL_ATTRIBUTE);
        String stroke = getComputedStyle(element, SVGConstants.SVG_STROKE_ATTRIBUTE);
        String strokeWidth = getComputedStyle(element, SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE);
        String opacity = getComputedStyle(element, SVGConstants.SVG_OPACITY_ATTRIBUTE);
        String fillOpacity = getComputedStyle(element, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE);
        String strokeOpacity = getComputedStyle(element, SVGConstants.SVG_STROKE_OPACITY_ATTRIBUTE);

        boolean hasFill = !SVGConstants.SVG_NONE_VALUE.equalsIgnoreCase(fill) && !"transparent".equalsIgnoreCase(fill);
        if (hasFill && ("0".equals(opacity) || "0".equals(fillOpacity))) {
            hasFill = false;
        }

        boolean hasStroke = !SVGConstants.SVG_NONE_VALUE.equalsIgnoreCase(stroke);
        if (hasStroke && ("0".equals(strokeWidth) || "0".equals(opacity) || "0".equals(strokeOpacity))) {
            hasStroke = false;
        }

        // Mark element for removal if it has neither fill nor stroke
        if (!hasFill && !hasStroke) {
            nodesToRemove.add(element);
        }

        // Remove collected nodes
        for (Node nodeToRemove : nodesToRemove) {
            nodeToRemove.getParentNode().removeChild(nodeToRemove);
        }
    }

    private static String getComputedStyle(Element element, String propertyName) {
        Element current = element;
        while (current != null) {
            String value = getStyleProperty(current, propertyName);
            if (value != null && !"inherit".equalsIgnoreCase(value)) {
                return value;
            }

            Node parent = current.getParentNode();
            if (parent instanceof Element) {
                current = (Element) parent;
            } else {
                current = null;
            }
        }
        return getDefaultCssValue(propertyName);
    }

    private static String getStyleProperty(Element element, String propertyName) {
        if (element.hasAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE)) {
            String style = element.getAttribute(SVGConstants.SVG_STYLE_ATTRIBUTE);
            String[] declarations = style.split(";");
            for (String declaration : declarations) {
                String[] parts = declaration.split(":", 2);
                if (parts.length == 2 && parts[0].trim().equalsIgnoreCase(propertyName)) {
                    return parts[1].trim();
                }
            }
        }

        if (element.hasAttribute(propertyName)) {
            return element.getAttribute(propertyName);
        }

        return null;
    }

    private static String getDefaultCssValue(String propertyName) {
        return switch (propertyName) {
            case SVGConstants.SVG_FILL_ATTRIBUTE -> "black";
            case SVGConstants.SVG_STROKE_ATTRIBUTE -> SVGConstants.SVG_NONE_VALUE;
            case SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE,
                 SVGConstants.SVG_OPACITY_ATTRIBUTE,
                 SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE,
                 SVGConstants.SVG_STROKE_OPACITY_ATTRIBUTE -> "1";
            default -> "";
        };
    }

    private static boolean isShapeElement(Element element) {
        String tagName = element.getTagName();
        return switch (tagName) {
            case SVGConstants.SVG_RECT_TAG,
                 SVGConstants.SVG_CIRCLE_TAG,
                 SVGConstants.SVG_ELLIPSE_TAG,
                 SVGConstants.SVG_LINE_TAG,
                 SVGConstants.SVG_POLYLINE_TAG,
                 SVGConstants.SVG_POLYGON_TAG,
                 SVGConstants.SVG_PATH_TAG -> true;
            default -> false;
        };
    }

    private static Set<String> getReferencedIds(Document doc) {
        Set<String> referencedIds = new HashSet<>();
        NodeList all = doc.getElementsByTagName("*");

        for (int i = 0; i < all.getLength(); i++) {
            Element el = (Element) all.item(i);
            NamedNodeMap attrs = el.getAttributes();
            for (int j = 0; j < attrs.getLength(); j++) {
                String val = attrs.item(j).getNodeValue();
                Matcher m = URL_ID_PATTERN.matcher(val);
                while (m.find()) {
                    referencedIds.add(m.group(1));
                }
                if (val.startsWith("#")) {
                    referencedIds.add(val.substring(1));
                }
            }
        }
        return referencedIds;
    }

    private static void removeUnusedDefs(Document doc, Set<String> referencedIds) {
        NodeList defsList = doc.getElementsByTagName(SVGConstants.SVG_DEFS_TAG);
        for (int i = defsList.getLength() - 1; i >= 0; i--) {
            Element defs = (Element) defsList.item(i);
            NodeList children = defs.getChildNodes();
            for (int j = children.getLength() - 1; j >= 0; j--) {
                Node child = children.item(j);
                if (child instanceof Element def) {
                    String id = def.getAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
                    if (!id.isEmpty() && !referencedIds.contains(id)) {
                        defs.removeChild(def);
                    }
                }
            }
            if (!defs.hasChildNodes()) {
                defs.getParentNode().removeChild(defs);
            }
        }
    }

    private static void removeUnusedIds(Document doc, Set<String> referencedIds) {
        NodeList all = doc.getElementsByTagName("*");
        for (int i = 0; i < all.getLength(); i++) {
            Element el = (Element) all.item(i);
            if (el.hasAttribute(SVGConstants.SVG_ID_ATTRIBUTE)) {
                String id = el.getAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
                if (!referencedIds.contains(id)) {
                    el.removeAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
                }
            }
        }
    }

    private static void removeUnusedPatterns(Document doc, Set<String> referencedIds) {
        NodeList patternList = doc.getElementsByTagName(SVGConstants.SVG_PATTERN_TAG);
        for (int i = patternList.getLength() - 1; i >= 0; i--) {
            Node node = patternList.item(i);
            if (node instanceof Element pattern) {
                String id = pattern.getAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
                if (!id.isEmpty() && !referencedIds.contains(id)) {
                    pattern.getParentNode().removeChild(pattern);
                }
            }
        }
    }

    private static void removedDefaultIds(Document doc) {
        NodeList allElements = doc.getElementsByTagName("*");

        for (int i = 0; i < allElements.getLength(); i++) {
            Node node = allElements.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                if (element.hasAttribute(SVGConstants.SVG_ID_ATTRIBUTE)) {
                    String id = element.getAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
                    if (DEFAULT_IDS.matcher(id).matches()) {
                        element.removeAttribute(SVGConstants.SVG_ID_ATTRIBUTE);
                    }
                }
            }
        }
    }

    private static String optimizeAttributeValue(String name, String value) {
        if (value == null) {
            return null;
        }

        String trimmedValue = value.trim();
        if ("font-weight".equalsIgnoreCase(name)) {
            if ("bold".equalsIgnoreCase(trimmedValue)) {
                return "700";
            }
            if ("normal".equalsIgnoreCase(trimmedValue)) {
                return "400";
            }
        } else if ("font-size".equalsIgnoreCase(name) && trimmedValue.toLowerCase().endsWith("px")) {
            return trimmedValue.substring(0, trimmedValue.length() - 2).trim();
        }
        return value;
    }

    private static void optimizeAttributes(Element element) {
        NamedNodeMap attributes = element.getAttributes();
        List<Attr> toRemove = new ArrayList<>();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            String name = attr.getName();
            String value = attr.getValue();

            // Handle style attributes
            if (SVGConstants.SVG_STYLE_ATTRIBUTE.equals(name)) {
                if (value == null || value.isEmpty()) {
                    continue;
                }
                String[] declarations = value.split(";");
                List<String> newDeclarations = new ArrayList<>();
                for (String declaration : declarations) {
                    if (declaration.trim().isEmpty()) {
                        continue;
                    }
                    String[] parts = declaration.split(":", 2);
                    if (parts.length == 2) {
                        String prop = parts[0].trim();
                        String propValue = parts[1].trim();

                        String optimizedPropValue = optimizeAttributeValue(prop, propValue);

                        String defaultValue = DEFAULT_ATTRIBUTE_VALUES.get(prop);

                        boolean isDefault = false;
                        if (defaultValue != null) {
                            String optimizedDefault = isColorAttribute(prop) ?
                                  optimizeColorValue(defaultValue) :
                                  defaultValue;
                            optimizedDefault = optimizeAttributeValue(prop, optimizedDefault);
                            if (optimizedPropValue.equals(optimizedDefault)) {
                                isDefault = true;
                            }
                        }
                        if (!isDefault) {
                            newDeclarations.add(prop + ":" + optimizedPropValue);
                        }
                    } else {
                        newDeclarations.add(declaration);
                    }
                }
                String newStyle = String.join(";", newDeclarations);
                if (newStyle.isEmpty()) {
                    toRemove.add(attr);
                } else if (!newStyle.equals(value)) {
                    attr.setValue(newStyle);
                }
                continue;
            }

            // Optimize attribute value before checking for default
            String optimizedValue = optimizeAttributeValue(name, value);
            if (!optimizedValue.equals(value)) {
                attr.setValue(optimizedValue);
                value = optimizedValue;
            }

            // Remove attributes with default values
            String defaultValue = DEFAULT_ATTRIBUTE_VALUES.get(name);
            if (defaultValue != null) {
                String optimizedDefault = isColorAttribute(name) ? optimizeColorValue(defaultValue) : defaultValue;
                optimizedDefault = optimizeAttributeValue(name, optimizedDefault);
                if (value.equals(optimizedDefault)) {
                    toRemove.add(attr);
                }
            }
        }

        for (Attr attr : toRemove) {
            element.removeAttributeNode(attr);
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                optimizeAttributes((Element) child);
            }
        }
    }

    private static void removeWhitespaceNodes(Node node) {
        // Don't remove whitespace within text-related elements
        boolean preserveSpace = false;
        if (node instanceof Element) {
            String tagName = ((Element) node).getTagName();
            if (SVGConstants.SVG_TEXT_TAG.equals(tagName) || SVGConstants.SVG_TSPAN_TAG.equals(tagName)) {
                preserveSpace = true;
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                if (!preserveSpace && child.getNodeValue().trim().isEmpty()) {
                    node.removeChild(child);
                }
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespaceNodes(child);
            }
        }
    }

    private static void collapseEmptyGroups(Element element) {
        NodeList children = element.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child instanceof Element) {
                collapseEmptyGroups((Element) child);
            }
        }

        if (SVGConstants.SVG_G_TAG.equals(element.getTagName())) {
            Node parent = element.getParentNode();
            if (parent == null) {
                return;
            }

            boolean hasElementChildren = false;
            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (childNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    hasElementChildren = true;
                    break;
                }
            }
            // If the group has no element children, remove it, regardless of attributes.
            if (!hasElementChildren) {
                parent.removeChild(element);
            }
            // Else, if the group has children but no attributes, collapse it.
            else if (element.getAttributes().getLength() == 0) {
                while (element.hasChildNodes()) {
                    parent.insertBefore(element.getFirstChild(), element);
                }
                parent.removeChild(element);
            }
        }
    }

    private static class AttributeInfo {
        final String namespaceURI;
        final String name;
        final String value;

        AttributeInfo(Attr attr) {
            this.namespaceURI = attr.getNamespaceURI();
            this.name = attr.getName();
            this.value = attr.getValue();
        }
    }

    private static final java.util.Comparator<AttributeInfo> ATTRIBUTE_COMPARATOR = (a1, a2) -> {
        String name1 = a1.name;
        String name2 = a2.name;

        boolean isNs1 = name1.startsWith("xmlns:") || "xmlns".equals(name1);
        boolean isNs2 = name2.startsWith("xmlns:") || "xmlns".equals(name2);

        if (isNs1 != isNs2) {
            return isNs1 ? -1 : 1;
        }
        if (isNs1) { // Both are namespaces
            return name1.compareTo(name2);
        }

        Integer index1 = ATTRIBUTE_ORDER.get(name1);
        Integer index2 = ATTRIBUTE_ORDER.get(name2);

        if (index1 != null && index2 != null) {
            return Integer.compare(index1, index2);
        }
        if (index1 != null) {
            return -1;
        }
        if (index2 != null) {
            return 1;
        }

        return name1.compareTo(name2);
    };

    private static void sortAttributes(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            NamedNodeMap attributes = element.getAttributes();
            int attrCount = attributes.getLength();

            if (attrCount > 1) {
                List<AttributeInfo> attrList = new ArrayList<>(attrCount);
                for (int i = 0; i < attrCount; i++) {
                    attrList.add(new AttributeInfo((Attr) attributes.item(i)));
                }

                boolean isSorted = true;
                for (int i = 0; i < attrList.size() - 1; i++) {
                    if (ATTRIBUTE_COMPARATOR.compare(attrList.get(i), attrList.get(i + 1)) > 0) {
                        isSorted = false;
                        break;
                    }
                }

                if (!isSorted) {
                    attrList.sort(ATTRIBUTE_COMPARATOR);

                    List<Attr> attrsToRemove = new ArrayList<>();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        attrsToRemove.add((Attr) attributes.item(i));
                    }
                    // Remove all attributes
                    for (Attr attr : attrsToRemove) {
                        element.removeAttributeNode(attr);
                    }

                    // Add them back in sorted order.
                    for (AttributeInfo attr : attrList) {
                        element.setAttributeNS(attr.namespaceURI, attr.name, attr.value);
                    }
                }
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            sortAttributes(children.item(i));
        }
    }

    private static void convertShapesToPaths(Element element) {
        // Create a list of child elements to iterate over, to avoid issues with modification
        List<Element> childElements = new ArrayList<>();
        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i) instanceof Element) {
                childElements.add((Element) children.item(i));
            }
        }

        for (Element child : childElements) {
            Element path = convertShapeToPath(child);
            if (path != null) {
                element.replaceChild(path, child);
            } else {
                // If not a convertible shape, recurse
                convertShapesToPaths(child);
            }
        }
    }

    private static Element convertShapeToPath(Element shape) {
        String d;
        String tagName = shape.getTagName();

        // Other shapes like ellipse, polyline could be added here
        d = switch (tagName) {
            case SVGConstants.SVG_RECT_TAG -> convertRectToPath(shape);
            case SVGConstants.SVG_CIRCLE_TAG -> convertCircleToPath(shape);
            case SVGConstants.SVG_POLYGON_TAG -> convertPolygonToPath(shape);
            case SVGConstants.SVG_LINE_TAG -> convertLineToPath(shape);
            default -> null;
        };

        if (d == null || d.isEmpty()) {
            return null;
        }

        Document doc = shape.getOwnerDocument();
        Element path = doc.createElementNS(shape.getNamespaceURI(), SVGConstants.SVG_PATH_TAG);
        path.setAttribute(SVGConstants.SVG_D_ATTRIBUTE, d);

        // Copy non-geometric attributes from the shape to the path
        NamedNodeMap attributes = shape.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Attr attr = (Attr) attributes.item(i);
            if (!isGeometricAttribute(tagName, attr.getName())) {
                path.setAttributeNS(attr.getNamespaceURI(), attr.getName(), attr.getValue());
            }
        }
        return path;
    }

    private static boolean isGeometricAttribute(String tagName, String attrName) {
        return switch (tagName) {
            case SVGConstants.SVG_RECT_TAG -> "x".equals(attrName)
                  || "y".equals(attrName)
                  || "width".equals(attrName)
                  || "height".equals(attrName)
                  || "rx".equals(attrName)
                  || "ry".equals(attrName);
            case SVGConstants.SVG_CIRCLE_TAG -> "cx".equals(attrName) || "cy".equals(attrName) || "r".equals(attrName);
            case SVGConstants.SVG_POLYGON_TAG, SVGConstants.SVG_POLYLINE_TAG -> "points".equals(attrName);
            case SVGConstants.SVG_LINE_TAG ->
                  "x1".equals(attrName) || "y1".equals(attrName) || "x2".equals(attrName) || "y2".equals(attrName);
            default -> false;
        };
    }

    private static float getFloatAttr(Element e, String name) {
        try {
            return e.hasAttribute(name) ? Float.parseFloat(e.getAttribute(name)) : 0f;
        } catch (NumberFormatException ex) {
            return 0f;
        }
    }

    private static String convertRectToPath(Element rect) {
        float x = getFloatAttr(rect, "x");
        float y = getFloatAttr(rect, "y");
        float width = getFloatAttr(rect, "width");
        float height = getFloatAttr(rect, "height");
        // This implementation does not handle rounded corners (rx, ry) for simplicity.
        if (width <= 0 || height <= 0) {return "";}
        return String.format(Locale.US,
              "M%s %sH%sV%sH%sZ",
              format3(x),
              format3(y),
              format3(x + width),
              format3(y + height),
              format3(x));
    }

    private static String convertCircleToPath(Element circle) {
        float cx = getFloatAttr(circle, "cx");
        float cy = getFloatAttr(circle, "cy");
        float r = getFloatAttr(circle, "r");
        if (r <= 0) {return "";}
        return String.format(Locale.US, "M%s %sa%s,%s 0 1,0 %s,0a%s,%s 0 1,0 -%s,0",
              format3(cx - r), format3(cy),
              format3(r), format3(r), format3(2 * r),
              format3(r), format3(r), format3(2 * r));
    }

    private static String convertPolygonToPath(Element polygon) {
        String points = polygon.getAttribute("points").trim();
        if (points.isEmpty()) {return "";}
        return "M" + points + "Z";
    }

    private static String convertLineToPath(Element line) {
        float x1 = getFloatAttr(line, "x1");
        float y1 = getFloatAttr(line, "y1");
        float x2 = getFloatAttr(line, "x2");
        float y2 = getFloatAttr(line, "y2");
        return String.format(Locale.US, "M%s %sL%s %s", format3(x1), format3(y1), format3(x2), format3(y2));
    }

    private static void simplifyTransformsParams(Element element) {
        if (element.hasAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE)) {
            String transform = element.getAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE);
            try {
                String simplified = simplifyTransformString(transform);
                if (simplified.isEmpty()) {
                    element.removeAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE);
                } else {
                    element.setAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, simplified);
                }
            } catch (Exception e) {
                // It's better to leave the original transform if simplification fails
                System.err.println("Failed to simplify transform: " + transform + " (" + e.getMessage() + ")");
            }
        }

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                simplifyTransformsParams((Element) child);
            }
        }
    }

    private static String simplifyTransformString(String transformStr) {
        if (transformStr == null || transformStr.trim().isEmpty()) {
            return "";
        }
        TransformListParser parser = new TransformListParser();
        SimplifyingTransformListHandler handler = new SimplifyingTransformListHandler();
        parser.setTransformListHandler(handler);
        parser.parse(transformStr);
        return handler.getSimplifiedTransform();
    }

    private static class SimplifyingTransformListHandler implements TransformListHandler {
        private final List<String> transforms = new ArrayList<>();
        private static final float EPSILON = 1e-5f;

        public String getSimplifiedTransform() {
            return String.join(" ", transforms);
        }

        private boolean isClose(float a, float b) {
            return Math.abs(a - b) < EPSILON;
        }

        @Override
        public void startTransformList() {}

        @Override
        public void endTransformList() {}

        @Override
        public void matrix(float a, float b, float c, float d, float e, float f) {
            if (isClose(a, 1) && isClose(b, 0) && isClose(c, 0) && isClose(d, 1) && isClose(e, 0) && isClose(f, 0)) {
                return; // Identity matrix
            }
            if (isClose(b, 0) && isClose(c, 0) && isClose(e, 0) && isClose(f, 0)) {
                scale(a, d); // Scale
                return;
            }
            if (isClose(a, 1) && isClose(b, 0) && isClose(c, 0) && isClose(d, 1)) {
                translate(e, f); // Translate
                return;
            }
            transforms.add(String.format("matrix(%s %s %s %s %s %s)",
                  format4(a), format4(b), format4(c), format4(d), format4(e), format4(f)));
        }

        @Override
        public void rotate(float theta) {
            if (isClose(theta, 0)) {return;}
            transforms.add("rotate(" + format4(theta) + ")");
        }

        @Override
        public void rotate(float theta, float cx, float cy) {
            if (isClose(theta, 0)) {return;}
            if (isClose(cx, 0) && isClose(cy, 0)) {
                rotate(theta);
            } else {
                transforms.add(String.format("rotate(%s %s %s)", format4(theta), format4(cx), format4(cy)));
            }
        }

        @Override
        public void translate(float tx) {
            if (isClose(tx, 0)) {return;}
            transforms.add("translate(" + format4(tx) + ")");
        }

        @Override
        public void translate(float tx, float ty) {
            if (isClose(tx, 0) && isClose(ty, 0)) {return;}
            if (isClose(ty, 0)) {
                translate(tx);
            } else {
                transforms.add(String.format("translate(%s %s)", format4(tx), format4(ty)));
            }
        }

        @Override
        public void scale(float sx) {
            if (isClose(sx, 1)) {return;}
            transforms.add("scale(" + format4(sx) + ")");
        }

        @Override
        public void scale(float sx, float sy) {
            if (isClose(sx, 1) && isClose(sy, 1)) {return;}
            if (isClose(sx, sy)) {
                scale(sx);
            } else {
                transforms.add(String.format("scale(%s %s)", format4(sx), format4(sy)));
            }
        }

        @Override
        public void skewX(float skx) {
            if (isClose(skx, 0)) {return;}
            transforms.add("skewX(" + format4(skx) + ")");
        }

        @Override
        public void skewY(float sky) {
            if (isClose(sky, 0)) {return;}
            transforms.add("skewY(" + format4(sky) + ")");
        }
    }

}
