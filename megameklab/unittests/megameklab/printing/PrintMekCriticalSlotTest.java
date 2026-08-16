/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 */
package megameklab.printing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;

import megamek.common.CriticalSlot;
import megamek.common.equipment.EquipmentType;
import megamek.common.equipment.Mounted;
import megamek.common.game.Game;
import megamek.common.rules.RulesManager;
import megamek.common.rules.core.CoreRulesManager;
import megamek.common.rules.totalwarfare.TWRulesManager;
import megamek.common.units.BipedMek;
import megamek.common.units.Mek;
import megameklab.testing.util.InitializeTypes;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGRectElement;

@ExtendWith(value = InitializeTypes.class)
class PrintMekCriticalSlotTest {

    private RulesManager originalRulesManager;

    @BeforeEach
    void rememberRulesManager() {
        originalRulesManager = Game.rulesManager;
    }

    @AfterEach
    void restoreRulesManager() {
        Game.rulesManager = originalRulesManager;
    }

    @Test
    void coreAddsExtraHitPointToSingleSlotAutocannon() throws Exception {
        Game.rulesManager = new CoreRulesManager();

        assertTrue(PrintMek.hasExtraHitPoint(criticalSlot("Autocannon/2")));
    }

    @Test
    void totalWarfareDoesNotAddExtraHitPointToSingleSlotAutocannon() throws Exception {
        Game.rulesManager = new TWRulesManager();

        assertFalse(PrintMek.hasExtraHitPoint(criticalSlot("Autocannon/2")));
    }

    @Test
    void coreDoesNotAddExtraHitPointToMultiSlotAutocannon() throws Exception {
        Game.rulesManager = new CoreRulesManager();

        assertFalse(PrintMek.hasExtraHitPoint(criticalSlot("Autocannon/5")));
    }

    @Test
    void corePlacesArmoredAndExtraHitPipsOnOppositeSidesOfText() throws Exception {
        Game.rulesManager = new CoreRulesManager();
        Element group = renderCriticalSlot("Autocannon/2", true);

        assertTrue(group.hasAttribute("armored"));
        assertTrue(group.hasAttribute("extraHit"));

        Element text = (Element) group.getElementsByTagName(SVGConstants.SVG_TEXT_TAG).item(0);
        Element armoredPip = childWithClass(group, "armoredLocPip");
        Element extraHitPip = childWithClass(group, "extraHitPip");
        assertNotNull(text);
        assertNotNull(armoredPip);
        assertNotNull(extraHitPip);
        assertEquals(SVGConstants.SVG_CIRCLE_TAG, armoredPip.getTagName());
        assertEquals(SVGConstants.SVG_RECT_TAG, extraHitPip.getTagName());

        double textX = Double.parseDouble(text.getAttribute(SVGConstants.SVG_X_ATTRIBUTE));
        double textEndX = textX + (text.getTextContent().length() * TestPrintMek.CHARACTER_WIDTH);
        assertTrue(shapeCenterX(armoredPip) < textX);
        assertTrue(shapeCenterX(extraHitPip) > textEndX);
    }

    @Test
    void corePlacesOnlyExtraHitPipOnUnarmoredAutocannon() throws Exception {
        Game.rulesManager = new CoreRulesManager();
        Element group = renderCriticalSlot("Autocannon/2", false);

        assertFalse(group.hasAttribute("armored"));
        assertTrue(group.hasAttribute("extraHit"));
        assertNull(childWithClass(group, "armoredLocPip"));
        Element extraHitPip = childWithClass(group, "extraHitPip");
        assertNotNull(extraHitPip);
        assertEquals(SVGConstants.SVG_RECT_TAG, extraHitPip.getTagName());
    }

    private CriticalSlot criticalSlot(String equipmentId) throws Exception {
        BipedMek mek = new BipedMek();
        mek.initializeInternal(10, Mek.LOC_CENTER_TORSO);
        Mounted<?> mounted = mek.addEquipment(EquipmentType.get(equipmentId), Mek.LOC_CENTER_TORSO);
        return new CriticalSlot(mounted);
    }

    private Element renderCriticalSlot(String equipmentId, boolean armored) throws Exception {
        BipedMek mek = new BipedMek();
        mek.initializeInternal(10, Mek.LOC_CENTER_TORSO);
        mek.addEquipment(EquipmentType.get(equipmentId), Mek.LOC_CENTER_TORSO);
        CriticalSlot criticalSlot = mek.getCritical(Mek.LOC_CENTER_TORSO, 0);
        criticalSlot.setArmored(armored);

        TestPrintMek printMek = new TestPrintMek(mek);
        DOMImplementation implementation = SVGDOMImplementation.getDOMImplementation();
        Document document = implementation.createDocument(SVGDOMImplementation.SVG_NAMESPACE_URI,
              SVGConstants.SVG_SVG_TAG, null);
        Element canvas = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
        document.getDocumentElement().appendChild(canvas);
        SVGRectElement rect = (SVGRectElement) document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI,
              SVGConstants.SVG_RECT_TAG);
        rect.setAttribute(SVGConstants.SVG_X_ATTRIBUTE, "0");
        rect.setAttribute(SVGConstants.SVG_Y_ATTRIBUTE, "0");
        rect.setAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, "200");
        rect.setAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, "120");
        canvas.appendChild(rect);
        printMek.setSVGDocument(document);

        Method writeCriticalSlots = PrintMek.class.getDeclaredMethod("writeLocationCriticalSlots", int.class,
              SVGRectElement.class);
        writeCriticalSlots.setAccessible(true);
        writeCriticalSlots.invoke(printMek, Mek.LOC_CENTER_TORSO, rect);

        NodeList groups = document.getElementsByTagName(SVGConstants.SVG_G_TAG);
        for (int i = 0; i < groups.getLength(); i++) {
            Element group = (Element) groups.item(i);
            if (equipmentId.equals(group.getAttribute("name"))) {
                return group;
            }
        }
        throw new AssertionError("Rendered critical slot not found");
    }

    private Element childWithClass(Element parent, String className) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element element) {
                String classes = " " + element.getAttribute(SVGConstants.SVG_CLASS_ATTRIBUTE).trim() + " ";
                if (classes.contains(" " + className + " ")) {
                    return element;
                }
            }
        }
        return null;
    }

    private double shapeCenterX(Element shape) {
        if (SVGConstants.SVG_CIRCLE_TAG.equals(shape.getTagName())) {
            return Double.parseDouble(shape.getAttribute(SVGConstants.SVG_CX_ATTRIBUTE));
        }
        return Double.parseDouble(shape.getAttribute(SVGConstants.SVG_X_ATTRIBUTE))
              + Double.parseDouble(shape.getAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE)) / 2;
    }

    private static class TestPrintMek extends PrintMek {
        private static final double CHARACTER_WIDTH = 5;

        TestPrintMek(Mek mek) {
            super(mek, 0, new RecordSheetOptions());
        }

        @Override
        public double getTextLength(String text, float fontSize, String weight, String fontStyle) {
            return text.length() * CHARACTER_WIDTH;
        }
    }
}
