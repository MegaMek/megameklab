/*
 * MegaMekLab - Copyright (C) 2010
 *
 * Original author - jtighe (torren@users.sourceforge.net)
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
package megameklab.com.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import megamek.common.Aero;
import megamek.common.AmmoType;
import megamek.common.Bay;
import megamek.common.Dropship;
import megamek.common.Entity;
import megamek.common.EntityMovementMode;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.weapons.bayweapons.BayWeapon;

public class ImageHelperDropShip {
    public static final String[] LOCATION_ABBRS_SPHEROID = { "N", "LF", "RF", "A", "FL",
            "FR", "AL", "AR", "FL/FR", "AL/AR" };
    public static final String[] LOCATION_ABBRS_AERODYNE = { "N", "LF", "RF", "A", "LW",
        "RW", "LW(R)", "RW(R)", "LW/RW", "LW(R)/RW(R)" };
    public static final int[] LOCATION_PRINT = { 0, 1, 2, 4, 5, 8, 9, 6, 7, 3 };
    public static final int LOC_FL = 4;
    public static final int LOC_FR = 5;
    public static final int LOC_AL = 6;
    public static final int LOC_AR = 7;
    public static final int LOC_FL_FR = 8;
    public static final int LOC_AL_AR = 9;

    public static void printDropShipCargo(Dropship dropship, Graphics2D g2d,
            float pointY) {

        if (dropship.getTransportBays().size() < 1) {
            return;
        }

        float pointX = 22;
        double lineFeed = ImageHelper.getStringHeight(g2d, "H", g2d.getFont());

        Font font = UnitUtil.deriveFont(true, g2d.getFont().getSize2D());

        g2d.setFont(font);
        pointY += lineFeed;
        g2d.drawString("Cargo: ", pointX, pointY);

        pointY += lineFeed;

        font = UnitUtil.deriveFont(g2d.getFont().getSize2D());

        g2d.setFont(font);
        Map<Integer, List<Bay>> baySetup = new HashMap<Integer, List<Bay>>();
        for (Bay bay : dropship.getTransportBays()) {
            if (bay.isQuarters()) {
                continue;
            }
            if (baySetup.get(bay.getBayNumber()) == null) {
                List<Bay> list = new ArrayList<Bay>();
                list.add(bay);
                baySetup.put(bay.getBayNumber(), list);
            } else {
                baySetup.get(bay.getBayNumber()).add(bay);
            }
        }
        for (int bayNumber : baySetup.keySet()) {
            List<Bay> bays = baySetup.get(bayNumber);
            if (bayNumber == 0) {
                for (Bay bay : bays) {
                    g2d.drawString(ImageHelperDropShip.getBayString(bay),
                            pointX, pointY);
                    pointY += lineFeed;
                }
            } else {
                String bayString = "Bay " + bayNumber + ":  ";
                g2d.drawString(bayString, pointX, pointY);
                float offsetWidth = ImageHelper.getStringWidth(g2d, bayString,
                        g2d.getFont());
                for (Bay bay : bays) {
                    g2d.drawString(ImageHelperDropShip.getBayString(bay),
                            pointX + offsetWidth, pointY);
                    pointY += lineFeed;
                }
            }

        }

    }

    public static void drawDropshipArmorPip(Graphics2D g2d, float width,
            float height) {
        ImageHelperDropShip.drawDropshipArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawDropshipArmorPip(Graphics2D g2d, float width,
            float height, float fontsize) {
        Font font = new Font("Arial", Font.PLAIN, 6);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static void drawDropshipISPip(Graphics2D g2d, int width, int height, int circleSize, int fillCircleSize) {
        Dimension circle = new Dimension(circleSize, circleSize);
        Dimension fillCircle = new Dimension(fillCircleSize, fillCircleSize);
        g2d.setColor(Color.black);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.white);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static Font getDropShipWeaponsNEquipmentFont(Graphics2D g2d,
            boolean bold, float stringHeight,
            ArrayList<Vector<EquipmentInfo>> equipmentLocations,
            ArrayList<Vector<EquipmentInfo>> capitalEquipmentLocations,
            float pointSize) {

        Font font = UnitUtil.deriveFont(bold, pointSize);
        boolean hasCapital = false;
        boolean hasSubCapital = false;

        int weaponCount = 1;
        for (int pos = Aero.LOC_NOSE; pos <= ImageHelperDropShip.LOC_AL_AR; pos++) {

            Vector<EquipmentInfo> eqVector = equipmentLocations.get(pos);
            if (eqVector.size() < 1) {
                continue;
            }

            hasSubCapital = true;

            weaponCount += (2*eqVector.size());
        }

        for (int pos = Aero.LOC_NOSE; pos <= Aero.LOC_WINGS; pos++) {

            Vector<EquipmentInfo> eqVector = capitalEquipmentLocations.get(pos);
            if (eqVector.size() < 1) {
                continue;
            }

            hasCapital = true;

            for (EquipmentInfo eqi : eqVector) {

                weaponCount+=2;
                if (eqi.isWeapon) {
                    if (eqi.isAR10) {
                        weaponCount += eqi.ar10AmmoTypes;
                    }
                    /*
                     * else { if (ImageHelper.getStringWidth(g2d,
                     * eqi.damage.trim(), font) > 22) { weaponCount++; } }
                     */
                }
            }
        }

        if (hasCapital) {
            weaponCount += 2;
        }

        if (hasSubCapital) {
            weaponCount += 2;
        }

        while (((ImageHelper.getStringHeight(g2d, "H", font) * weaponCount) > stringHeight)
                && (pointSize > 0)) {
            pointSize -= .1;
            font = UnitUtil.deriveFont(bold, pointSize);
        }

        return font;
    }

    public static void printDropshipWeaponsNEquipment(Dropship dropship,
            Graphics2D g2d) {
        int qtyPoint = 26;
        int typePoint = 38;
        int locPoint = 111;
        int heatPoint = 135;
        int shtPoint = 151;
        int medPoint = 169;
        int longPoint = 192;
        int erPoint = 211;
        int nameSize = 65;
        float linePoint = 210f;
        float lineFeed = 6.7f;
        float maxHeight = 140f;
        float stringHeight = 0;
        float fontSize = 7.0f;
        boolean newLineNeeded = false;
        boolean hasCapital = false;
        boolean hasSubCapital = false;
        String[] locAbbr = dropship.isSpheroid()?LOCATION_ABBRS_SPHEROID:LOCATION_ABBRS_AERODYNE;

        ArrayList<Vector<EquipmentInfo>> equipmentLocations = new ArrayList<Vector<EquipmentInfo>>(
                locAbbr.length);
        ArrayList<Vector<EquipmentInfo>> capitalEquipmentLocations = new ArrayList<Vector<EquipmentInfo>>(
                locAbbr.length);

        if (dropship.getMovementMode() == EntityMovementMode.AERODYNE) {
            linePoint = 201;
        }

        for (int pos = 0; pos < locAbbr.length; pos++) {
            equipmentLocations.add(pos, new Vector<EquipmentInfo>());
            capitalEquipmentLocations.add(pos, new Vector<EquipmentInfo>());
        }

        for (Mounted eq : dropship.getWeaponBayList()) {

            if ((eq.isWeaponGroup() || (eq.getType() instanceof AmmoType))
                    || (eq.getLocation() == Entity.LOC_NONE)
                    || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Vector<EquipmentInfo> eqHash = equipmentLocations.get(eq
                    .getLocation());
            Vector<EquipmentInfo> capitalEqHash = capitalEquipmentLocations
                    .get(eq.getLocation());

            String equipmentName = "";
            if (eq.isRearMounted()) {
                switch (eq.getLocation()) {
                    case Aero.LOC_LWING:
                        eqHash = equipmentLocations
                                .get(ImageHelperDropShip.LOC_AL);
                        capitalEqHash = capitalEquipmentLocations
                                .get(ImageHelperDropShip.LOC_AL);
                        break;
                    case Aero.LOC_RWING:
                        eqHash = equipmentLocations
                                .get(ImageHelperDropShip.LOC_AR);
                        capitalEqHash = capitalEquipmentLocations
                                .get(ImageHelperDropShip.LOC_AR);
                        break;
                }
            }

            if ((eq.getType() instanceof BayWeapon)
                    && ((BayWeapon) eq.getType()).isCapital()) {

                EquipmentInfo eqi = null;
                for (int weaponIndex : eq.getBayWeapons()) {
                    Mounted weapon = dropship.getEquipment(weaponIndex);

                    if ((eqi == null)
                            || (equipmentName == "")
                            || !equipmentName.equalsIgnoreCase(UnitUtil
                                    .getCritName(dropship, weapon.getType()))
                            || (weapon.getType().getTechLevel(
                                    dropship.getTechLevelYear()) != eqi.techLevel)) {
                        if (eqi != null) {
                            eqi = new EquipmentInfo(dropship, weapon, eq);
                            equipmentName = eqi.name;
                            eqi.shouldIndent = true;
                            capitalEqHash.add(eqi);
                        } else {
                            eqi = new EquipmentInfo(dropship, weapon, eq);
                            capitalEqHash.add(eqi);
                            equipmentName = eqi.name;
                        }
                    } else {
                        eqi.count++;
                    }
                }
            } else {
                EquipmentInfo eqi = null;
                for (int weaponIndex : eq.getBayWeapons()) {
                    Mounted weapon = dropship.getEquipment(weaponIndex);

                    if ((eqi == null)
                            || (equipmentName == "")
                            || !equipmentName.equalsIgnoreCase((UnitUtil
                                    .getCritName(dropship, weapon.getType())))
                            || (weapon.getType().getTechLevel(
                                    dropship.getTechLevelYear()) != eqi.techLevel)) {
                        if (eqi != null) {
                            eqi = new EquipmentInfo(dropship, weapon, eq);
                            eqi.shouldIndent = true;
                            eqHash.add(eqi);
                            equipmentName = eqi.name;
                        } else {
                            eqi = new EquipmentInfo(dropship, weapon, eq);
                            eqHash.add(eqi);
                            equipmentName = eqi.name;
                        }
                    } else {
                        eqi.count++;
                    }
                }
            }
        }

        equipmentLocations.get(ImageHelperDropShip.LOC_FL_FR).addAll(
                equipmentLocations.get(Aero.LOC_LWING));
        equipmentLocations.get(Aero.LOC_LWING).clear();
        equipmentLocations.get(Aero.LOC_RWING).clear();
        equipmentLocations.get(ImageHelperDropShip.LOC_AL_AR).addAll(
                equipmentLocations.get(ImageHelperDropShip.LOC_AL));
        equipmentLocations.get(ImageHelperDropShip.LOC_AL).clear();
        equipmentLocations.get(ImageHelperDropShip.LOC_AR).clear();

        capitalEquipmentLocations.get(ImageHelperDropShip.LOC_FL_FR).addAll(
                capitalEquipmentLocations.get(Aero.LOC_LWING));
        capitalEquipmentLocations.get(Aero.LOC_LWING).clear();
        capitalEquipmentLocations.get(Aero.LOC_RWING).clear();
        capitalEquipmentLocations.get(ImageHelperDropShip.LOC_AL_AR).addAll(
                capitalEquipmentLocations.get(ImageHelperDropShip.LOC_AL));
        capitalEquipmentLocations.get(ImageHelperDropShip.LOC_AL).clear();
        capitalEquipmentLocations.get(ImageHelperDropShip.LOC_AR).clear();

        Font font = ImageHelperDropShip.getDropShipWeaponsNEquipmentFont(g2d,
                true, maxHeight, equipmentLocations, capitalEquipmentLocations,
                fontSize);
        g2d.setFont(font);

        fontSize = font.getSize2D();
        stringHeight = ImageHelper.getStringHeight(g2d, "H", font);

        lineFeed = stringHeight;

        for (int pos = 0; pos < LOCATION_PRINT.length; pos++) {

            Vector<EquipmentInfo> eqHash = capitalEquipmentLocations
                    .get(LOCATION_PRINT[pos]);

            if (eqHash.isEmpty()) {
                continue;
            }

            // damage has to be combined for all stuff in a bay
            for (int i = 0; i < eqHash.size(); i++) {
                EquipmentInfo eqi = eqHash.get(i);
                if (!eqi.shouldIndent) {
                    // first, calculate the damage values here, so we can add
                    // other
                    // weapons in the same bay in the next step
                    eqi.heat *= eqi.count;
                    if (eqi.shtRange > 0) {
                        eqi.shtRange *= eqi.count;
                    }
                    if (eqi.medRange > 0) {
                        eqi.medRange *= eqi.count;
                    }
                    if (eqi.longRange > 0) {
                        eqi.longRange *= eqi.count;
                    }
                    if (eqi.erRange > 0) {
                        eqi.erRange *= eqi.count;
                    }
                    // now, get all the equipmentinfos after this that are
                    // shouldIndent and as such belong in the same bay
                    for (int j = i + 1; j < eqHash.size(); j++) {
                        EquipmentInfo eqiBay = eqHash.get(j);
                        if (eqiBay.shouldIndent) {
                            eqi.heat += (eqiBay.heat * eqiBay.count);
                            eqiBay.heat = -1;
                            if (eqiBay.shtRange > 0) {
                                eqi.shtRange += (eqiBay.shtRange * eqiBay.count);
                            }
                            if (eqiBay.medRange > 0) {
                                eqi.medRange += (eqiBay.medRange * eqiBay.count);
                            }
                            if (eqiBay.longRange > 0) {
                                eqi.longRange += (eqiBay.longRange * eqiBay.count);
                            }
                            if (eqiBay.erRange > 0) {
                                eqi.erRange += (eqiBay.erRange * eqiBay.count);
                            }
                            eqiBay.shtRange = -1;
                            eqiBay.medRange = -1;
                            eqiBay.longRange = -1;
                            eqiBay.erRange = -1;
                            i++;
                        } else {
                            break;
                        }
                    }
                }
            }
            if (!hasCapital) {
                hasCapital = true;

                g2d.drawString("Capital Scale", typePoint, linePoint);
                font = UnitUtil.getNewFont(g2d,
                        "(1-12) (13-24) (25-40) (41-50)", true, 75, fontSize);
                g2d.setFont(font);
                g2d.drawString("(1-12) (13-24) (25-40) (41-50)", shtPoint,
                        linePoint);
                linePoint += lineFeed;
                font = UnitUtil.getNewFont(g2d, "Bay", true, 68, fontSize);
                g2d.setFont(font);

                g2d.drawString("Bay", typePoint, linePoint);
                g2d.drawString("Loc", locPoint, linePoint);
                g2d.drawString("Ht", heatPoint, linePoint);
                g2d.drawString("SRV", shtPoint, linePoint);
                g2d.drawString("MRV", medPoint, linePoint);
                g2d.drawString("LRV", longPoint, linePoint);
                g2d.drawString("ERV", erPoint, linePoint);
                linePoint += lineFeed;
            }

            for (EquipmentInfo eqi : eqHash) {
                newLineNeeded = false;

                if (eqi.shouldIndent) {
                    qtyPoint += 5;
                    typePoint += 5;
                    nameSize -= 10;
                }
                font = UnitUtil.deriveFont(false, fontSize);
                g2d.setFont(font);
                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();
                if (eqi.hasArtemis) {
                    name = name + " w/Artemis IV FCS";
                } else if (eqi.hasArtemisV) {
                    name = name + " w/Artemis V FCS";
                } else if (eqi.hasApollo) {
                    name = name + " w/Apollo FCS";
                }
                font = UnitUtil
                        .getNewFont(g2d, name, false, nameSize, fontSize);
                g2d.setFont(font);

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printNavalC3Name(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3EM) {
                    ImageHelper.printC3EmName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3REMOTESENSOR) {
                    ImageHelper.printC3RemoteSensorName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.isMashCore) {
                    ImageHelper.printMashCore(g2d, typePoint, linePoint, font,
                            false, dropship);
                } else if (eqi.isDroneControl) {
                    ImageHelper.printDroneControl(g2d, typePoint, linePoint,
                            font, false, dropship);
                } else {
                    g2d.drawString(name, typePoint, linePoint);

                    if ((eqi.damage.trim().length() > 0) && !eqi.isAR10) {
                        g2d.drawString(eqi.damage, typePoint, linePoint
                                + lineFeed);
                        newLineNeeded = true;
                    }
                }
                if (eqi.shouldIndent) {
                    qtyPoint -= 5;
                    typePoint -= 5;
                    nameSize += 10;
                }

                String location = locAbbr[LOCATION_PRINT[pos]];

                ImageHelper.printCenterString(g2d, location, font,
                        locPoint + 5, linePoint);
                if ((eqi.heat != -1) && !eqi.isAR10) {
                    ImageHelper.printCenterString(g2d,
                            Integer.toString(eqi.heat), font, heatPoint + 4,
                            linePoint);
                }
                font = UnitUtil.deriveFont(Math.min(6f, fontSize));
                g2d.setFont(font);
                if (eqi.isAR10) {
                    int ammoLines = StringUtils.countOccurrences(eqi.damage,
                            '[');
                    String ammoString = eqi.damage;
                    for (int i = 0; i < ammoLines; i++) {
                        String printString = ammoString.substring(0,
                                ammoString.indexOf("]") + 1);
                        ammoString = ammoString.substring(ammoString
                                .indexOf("]") + 1);
                        linePoint += lineFeed;
                        g2d.drawString(printString, typePoint, linePoint);
                        String damage = "";
                        if (printString.indexOf("Barracuda") != -1) {
                            ImageHelper.printCenterString(g2d,
                                    Integer.toString(10 * eqi.count), font,
                                    heatPoint + 4, linePoint);
                            int baseDam = 2 * eqi.count;
                            int baseDamNormalScale = 20 * eqi.count;
                            if (baseDamNormalScale < 100) {
                                damage = baseDam + " (" + baseDamNormalScale
                                        + ")";
                            } else {
                                damage = Integer.toString(baseDam);
                            }
                        } else if (printString.indexOf("White Shark") != -1) {
                            ImageHelper.printCenterString(g2d,
                                    Integer.toString(15 * eqi.count), font,
                                    heatPoint + 4, linePoint);
                            int baseDam = 3 * eqi.count;
                            int baseDamNormalScale = 30 * eqi.count;
                            if (baseDamNormalScale < 100) {
                                damage = baseDam + " (" + baseDamNormalScale
                                        + ")";
                            } else {
                                damage = Integer.toString(baseDam);
                            }
                        } else if (printString.indexOf("Killer Whale") != -1) {
                            ImageHelper.printCenterString(g2d,
                                    Integer.toString(20 * eqi.count), font,
                                    heatPoint + 4, linePoint);
                            int baseDam = 4 * eqi.count;
                            int baseDamNormalScale = 40 * eqi.count;
                            if (baseDamNormalScale < 100) {
                                damage = baseDam + " (" + baseDamNormalScale
                                        + ")";
                            } else {
                                damage = Integer.toString(baseDam);
                            }
                        }
                        g2d.drawString(damage, shtPoint, linePoint);
                        g2d.drawString(damage, medPoint, linePoint);
                        g2d.drawString(damage, longPoint, linePoint);
                        g2d.drawString(damage, erPoint, linePoint);
                    }
                } else if (eqi.shtRange > 0) {
                    if ((eqi.shtRange * 10) < 100) {
                        g2d.drawString(String.format("%1$d (%2$d)",
                                eqi.shtRange, eqi.shtRange * 10), shtPoint,
                                linePoint);
                    } else {
                        g2d.drawString(String.format("%1$d", eqi.shtRange),
                                shtPoint, linePoint);
                    }
                    if (eqi.medRange > 0) {
                        if ((eqi.medRange * 10) < 100) {
                            g2d.drawString(String.format("%1$d (%2$d)",
                                    eqi.medRange, eqi.medRange * 10), medPoint,
                                    linePoint);
                        } else {
                            g2d.drawString(String.format("%1$d", eqi.medRange),
                                    medPoint, linePoint);
                        }
                    } else if (eqi.medRange != -1) {
                        g2d.drawString("\u2014", medPoint, linePoint);
                    }
                    if (eqi.longRange > 0) {
                        if ((eqi.longRange * 10) < 100) {
                            g2d.drawString(String.format("%1$d (%2$d)",
                                    eqi.longRange, eqi.longRange * 10),
                                    longPoint, linePoint);
                        } else {
                            g2d.drawString(
                                    String.format("%1$d", eqi.longRange),
                                    longPoint, linePoint);
                        }
                    } else if (eqi.longRange != -1) {
                        g2d.drawString("\u2014", longPoint, linePoint);
                    }
                    if (eqi.erRange > 0) {
                        if ((eqi.erRange * 10) < 100) {
                            g2d.drawString(String.format("%1$d (%2$d)",
                                    eqi.erRange, eqi.erRange * 10), erPoint,
                                    linePoint);
                        } else {
                            g2d.drawString(String.format("%1$d", eqi.erRange),
                                    erPoint, linePoint);
                        }
                    } else if (eqi.erRange != -1) {
                        g2d.drawString("\u2014", erPoint, linePoint);
                    }
                } else if (eqi.shtRange != -1) {
                    g2d.drawString("\u2014", shtPoint, linePoint);
                } else if (eqi.isAMS) {
                    g2d.drawString("Point Defense", medPoint, linePoint);
                }
                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
            }
        }
        g2d.setFont(UnitUtil.deriveFont(true, g2d.getFont().getSize2D()));

        for (int pos = 0; pos < LOCATION_PRINT.length; pos++) {

            Vector<EquipmentInfo> eqHash = equipmentLocations
                    .get(LOCATION_PRINT[pos]);

            if (eqHash.isEmpty()) {
                continue;
            }
            // damage has to be combined for all stuff in a bay
            for (int i = 0; i < eqHash.size(); i++) {
                EquipmentInfo eqi = eqHash.get(i);
                if (!eqi.shouldIndent) {
                    // first, calculate the damage values here, so we can add
                    // other
                    // weapons in the same bay in the next step
                    eqi.heat *= eqi.count;
                    if (eqi.shtRange > 0) {
                        eqi.shtRange *= eqi.count;
                    }
                    if (eqi.medRange > 0) {
                        eqi.medRange *= eqi.count;
                    }
                    if (eqi.longRange > 0) {
                        eqi.longRange *= eqi.count;
                    }
                    if (eqi.erRange > 0) {
                        eqi.erRange *= eqi.count;
                    }
                    // now, get all the equipmentinfos after this that are
                    // shouldIndent and as such belong in the same bay
                    for (int j = i + 1; j < eqHash.size(); j++) {
                        EquipmentInfo eqiBay = eqHash.get(j);
                        if (eqiBay.shouldIndent) {
                            eqi.heat += eqiBay.heat * eqiBay.count;
                            eqiBay.heat = -1;
                            if (eqiBay.shtRange > 0) {
                                eqi.shtRange += eqiBay.shtRange * eqiBay.count;
                            }
                            if (eqiBay.medRange > 0) {
                                eqi.medRange += eqiBay.medRange * eqiBay.count;
                            }
                            if (eqiBay.longRange > 0) {
                                eqi.longRange += eqiBay.longRange
                                        * eqiBay.count;
                            }
                            if (eqiBay.erRange > 0) {
                                eqi.erRange += eqiBay.erRange * eqiBay.count;
                            }
                            eqiBay.shtRange = -1;
                            eqiBay.medRange = -1;
                            eqiBay.longRange = -1;
                            eqiBay.erRange = -1;
                            i++;
                        } else {
                            break;
                        }
                    }
                }
            }
            if (!hasSubCapital) {
                hasSubCapital = true;

                g2d.drawString("Standard Scale", typePoint, linePoint);
                font = UnitUtil.getNewFont(g2d, "(1-6) (7-12) (13-20) (21-25)",
                        true, 75, fontSize);
                g2d.setFont(font);
                g2d.drawString("(1-6) (7-12) (13-20) (21-25)", shtPoint,
                        linePoint);
                linePoint += lineFeed;

                font = UnitUtil.getNewFont(g2d, "Bay", true, 68, fontSize);
                g2d.setFont(font);

                g2d.drawString("Bay", typePoint, linePoint);
                g2d.drawString("Loc", locPoint, linePoint);
                g2d.drawString("Ht", heatPoint, linePoint);
                g2d.drawString("SRV", shtPoint, linePoint);
                g2d.drawString("MRV", medPoint, linePoint);
                g2d.drawString("LRV", longPoint, linePoint);
                g2d.drawString("ERV", erPoint, linePoint);
                linePoint += lineFeed;
            }

            for (EquipmentInfo eqi : eqHash) {
                newLineNeeded = false;

                if (eqi.shouldIndent) {
                    qtyPoint += 5;
                    typePoint += 5;
                    nameSize -= 10;
                }
                font = UnitUtil.deriveFont(false, fontSize);
                g2d.setFont(font);
                g2d.drawString(Integer.toString(eqi.count), qtyPoint, linePoint);
                String name = eqi.name.trim();
                if (eqi.hasArtemis) {
                    name = name + " w/Artemis IV FCS";
                } else if (eqi.hasArtemisV) {
                    name = name + " w/Artemis V FCS";
                } else if (eqi.hasApollo) {
                    name = name + " w/Apollo FCS";
                }
                font = UnitUtil.getNewFont(g2d, name, false, 68, fontSize);
                g2d.setFont(font);

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printNavalC3Name(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3REMOTESENSOR) {
                    ImageHelper.printC3RemoteSensorName(
                            g2d,
                            typePoint,
                            linePoint,
                            font,
                            false,
                            dropship.isMixedTech()
                                    && TechConstants.isClan(dropship
                                            .getTechLevel()));
                } else {
                    g2d.drawString(name, typePoint, linePoint);

                    if ((eqi.damage.trim().length() > 0) && !eqi.isMML) {
                        g2d.drawString(eqi.damage, typePoint, linePoint
                                + lineFeed);
                        newLineNeeded = true;
                    }
                }
                if (eqi.shouldIndent) {
                    qtyPoint -= 5;
                    typePoint -= 5;
                    nameSize += 10;
                }
                String location = locAbbr[LOCATION_PRINT[pos]];

                ImageHelper.printCenterString(g2d, location, font,
                        locPoint + 5, linePoint);
                if (eqi.heat != -1) {
                    ImageHelper.printCenterString(g2d,
                            Integer.toString(eqi.heat), font, heatPoint + 4,
                            linePoint);
                }
                if (eqi.isMML) {
                    String lrmAmmoString = eqi.damage.substring(0,
                            eqi.damage.indexOf("]") + 1);
                    String srmAmmoString = eqi.damage.substring(eqi.damage
                            .indexOf("]") + 1);
                    linePoint += lineFeed;
                    g2d.drawString(srmAmmoString, typePoint, linePoint);
                    String damage = String.format("%1$d (%2$d)",
                            Math.round((eqi.shtRange * 2) / 10f),
                            eqi.shtRange * 2);
                    font = UnitUtil
                            .getNewFont(g2d, damage, false, 17, fontSize);
                    g2d.setFont(font);
                    g2d.drawString(damage, shtPoint, linePoint);
                    font = UnitUtil.deriveFont(false, fontSize);
                    g2d.setFont(font);
                    g2d.drawString("\u2014", medPoint, linePoint);
                    g2d.drawString("\u2014", longPoint, linePoint);
                    g2d.drawString("\u2014", erPoint, linePoint);

                    linePoint += lineFeed;
                    g2d.drawString(lrmAmmoString, typePoint, linePoint);
                    damage = String.format("%1$d (%2$d)",
                            Math.round((eqi.shtRange) / 10f), eqi.shtRange);
                    font = UnitUtil
                            .getNewFont(g2d, damage, false, 17, fontSize);
                    g2d.setFont(font);
                    g2d.drawString(damage, shtPoint, linePoint);
                    g2d.drawString(damage, medPoint, linePoint);
                    g2d.drawString(damage, longPoint, linePoint);
                    g2d.drawString("\u2014", erPoint, linePoint);
                    font = UnitUtil.deriveFont(false, fontSize);
                    g2d.setFont(font);
                } else if (eqi.shtRange > 0) {
                    String damage = String.format("%1$d (%2$d)",
                            Math.round((eqi.shtRange) / 10f), eqi.shtRange);
                    font = UnitUtil
                            .getNewFont(g2d, damage, false, 17, fontSize);
                    g2d.setFont(font);
                    g2d.drawString(damage, shtPoint, linePoint);
                    font = UnitUtil.deriveFont(fontSize);
                    g2d.setFont(font);
                } else if (eqi.shtRange != -1) {
                    g2d.drawString("\u2014", shtPoint, linePoint);
                }

                if (eqi.isAMS) {
                    g2d.drawString("Point Defense", medPoint, linePoint);
                } else {
                    if ((eqi.medRange > 0) && !eqi.isMML) {
                        String damage = String.format("%1$d (%2$d)",
                                Math.round((eqi.medRange) / 10f), eqi.medRange);
                        font = UnitUtil.getNewFont(g2d, damage, false, 17,
                                fontSize);
                        g2d.setFont(font);
                        g2d.drawString(damage, medPoint, linePoint);
                        font = UnitUtil.deriveFont(fontSize);
                        g2d.setFont(font);
                    } else if (!eqi.isMML && (eqi.medRange != -1)) {
                        g2d.drawString("\u2014", medPoint, linePoint);
                    }
                    if ((eqi.longRange > 0) && !eqi.isMML) {
                        String damage = String.format("%1$d (%2$d)",
                                Math.round((eqi.longRange) / 10f),
                                eqi.longRange);
                        font = UnitUtil.getNewFont(g2d, damage, false, 17,
                                fontSize);
                        g2d.setFont(font);
                        g2d.drawString(damage, longPoint, linePoint);
                        font = UnitUtil.deriveFont(fontSize);
                        g2d.setFont(font);
                    } else if (!eqi.isMML && (eqi.longRange != -1)) {
                        g2d.drawString("\u2014", longPoint, linePoint);
                    }
                    if ((eqi.erRange > 0) && !eqi.isMML) {
                        String damage = String.format("%1$d (%2$d)",
                                Math.round((eqi.erRange) / 10f), eqi.erRange);
                        font = UnitUtil.getNewFont(g2d, damage, false, 17,
                                fontSize);
                        g2d.setFont(font);
                        g2d.drawString(damage, erPoint, linePoint);
                        font = UnitUtil.deriveFont(fontSize);
                        g2d.setFont(font);
                    } else if (!eqi.isMML && (eqi.erRange != -1)) {
                        g2d.drawString("\u2014", erPoint, linePoint);
                    }
                }
                linePoint += lineFeed;
                if (newLineNeeded && !eqi.isMML) {
                    linePoint += lineFeed;
                }
            }
        }

        int pointX = 22;
        boolean notesPrinted = false;
        font = UnitUtil.deriveFont(true, g2d.getFont().getSize2D());
        EquipmentInfo eq = null;
        Vector<EquipmentInfo> eqHash = new Vector<EquipmentInfo>();

        for (Mounted mount : dropship.getEquipment()) {
            if ((mount.getLocation() == Entity.LOC_NONE) && UnitUtil.isPrintableEquipment(mount.getType())) {

                eq = new EquipmentInfo(dropship, mount, null);
                eqHash.add(eq);

            }
        }
        for (EquipmentInfo eqi : eqHash) {
            if (!notesPrinted) {
                g2d.setFont(font);
                g2d.drawString("Notes: ", pointX, linePoint);

                linePoint += lineFeed;

                font = UnitUtil.deriveFont(g2d.getFont().getSize2D());
                notesPrinted = true;
                g2d.setFont(font);
            }
            if (eqi.c3Level == EquipmentInfo.C3I) {
                ImageHelper
                        .printNavalC3Name(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.c3Level == EquipmentInfo.C3EM) {
                ImageHelper
                        .printC3EmName(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.c3Level == EquipmentInfo.C3S) {
                ImageHelper
                        .printC3sName(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.c3Level == EquipmentInfo.C3M) {
                ImageHelper
                        .printC3mName(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                ImageHelper
                        .printC3sbName(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                ImageHelper
                        .printC3mbName(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.c3Level == EquipmentInfo.C3REMOTESENSOR) {
                ImageHelper
                        .printC3RemoteSensorName(
                                g2d,
                                pointX,
                                linePoint,
                                font,
                                false,
                                dropship.isMixedTech()
                                        && TechConstants.isClan(dropship
                                                .getTechLevel()));
            } else if (eqi.isMashCore) {
                ImageHelper.printMashCore(g2d, pointX, linePoint, font, false,
                        dropship);
            } else if (eqi.isDroneControl) {
                ImageHelper.printDroneControl(g2d, pointX, linePoint, font,
                        false, dropship);
            } else {
                g2d.drawString(eqi.name, pointX, linePoint);
            }

            linePoint += lineFeed;

        }
        ImageHelperDropShip.printDropShipCargo(dropship, g2d, linePoint);

    }

    public static String getBayString(Bay bay) {
        return bay.getUnusedString(false);
    }

    static public void printISPoints(Graphics2D g2d,
            Vector<float[]> pipPlotter, float totalArmor, int circleSize, int fillCircleSize) {
        pipPlotter.trimToSize();
        float pipSpace = 1;
        for (float pos = 0; pos < pipPlotter.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperDropShip.drawDropshipISPip(g2d,
                    (int) pipPlotter.get(currentPip)[0],
                    (int) pipPlotter.get(currentPip)[1], circleSize, fillCircleSize);
            if (--totalArmor <= 0) {
                return;
            }
        }

    }

    static public void printArmorPoints(Graphics2D g2d,
            Vector<float[]> pipPlotter, float totalArmor) {
        pipPlotter.trimToSize();
        float pipSpace = 1;
        for (float pos = 0; pos < pipPlotter.size(); pos += pipSpace) {
            int currentPip = (int) pos;
            ImageHelperDropShip.drawDropshipArmorPip(g2d,
                    pipPlotter.get(currentPip)[0],
                    pipPlotter.get(currentPip)[1], 5.0f);
            if (--totalArmor <= 0) {
                return;
            }
        }
    }

    static public int printTotalDoors(Aero aero, Bay bay) {

        int result = 0;
        for (Bay next : aero.getTransportBays()) {
            if (next.getType().equals(bay.getType())) {
                result += next.getDoors();
            }
        }
        return result;
    }
}