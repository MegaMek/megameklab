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
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

import megamek.common.AmmoType;
import megamek.common.BattleArmor;
import megamek.common.Entity;
import megamek.common.MiscType;
import megamek.common.Mounted;
import megamek.common.TechConstants;
import megamek.common.weapons.battlearmor.ISBACompactNarc;

public class ImageHelperBattleArmor {
    public static void drawBAArmorPip(Graphics2D g2d, float width, float height) {
        ImageHelperBattleArmor.drawBAArmorPip(g2d, width, height, 9.0f);
    }

    public static void drawBAArmorPip(Graphics2D g2d, float width, float height, float fontsize) {
        Font font = new Font("Arial", Font.PLAIN, 9);
        font = font.deriveFont(fontsize);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        g2d.setBackground(Color.WHITE);
        g2d.drawString("O", width, height);
    }

    public static void drawBAISPip(Graphics2D g2d, int width, int height) {
        Dimension circle = new Dimension(7, 7);
        Dimension fillCircle = new Dimension(5, 5);
        g2d.setColor(Color.BLACK);
        g2d.fillOval(width, height, circle.width, circle.height);
        g2d.setColor(Color.GRAY);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
    }

    public static Image getBATrooper(int position) {
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;
        return new ImageIcon(path + "twba-trooper" + position + ".png").getImage();
    }

    public static Image getBACheckBox() {
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;
        return new ImageIcon(path + "checkbox.png").getImage();
    }

    public static void printBAArmor(BattleArmor ba, Graphics2D g2d, float lineFeed, float offset) {
        float positionX = 35;
        float positionY = 190 + offset;
        String armorString = "Armor:";
        StringBuffer buffer = new StringBuffer();

        // camo system is not armor and should print separately
        if (ba.isStealthActive() && (ba.isStealthy() || ba.isMimetic())) {
            if (ba.isMimetic()) {
                buffer.append("  "+ba.getStealthName() + " (+3 - hexes moved)");
            } else {
                buffer.append(String.format("  %1$s (+%2$s/+%3$s/+%4$s)", ba.getStealthName(), ba.getShortStealthMod(), ba.getMediumStealthMod(), ba.getLongStealthMod()));
            }
        }

        if (ba.isFireResistant()) {
            buffer.append(" Fire Resistant");
        }
        if (ba.isReflective()) {
            buffer.append(" Reflective");
        }
        if (ba.isReactive()) {
            buffer.append(" Reactive");
        }

        if (buffer.length() > 2) {
            Font font = UnitUtil.deriveFont(true, 9.0f);
            g2d.setFont(font);
            g2d.drawString(armorString, positionX, positionY);

            positionX += ImageHelper.getStringWidth(g2d, armorString, font);
            g2d.setFont(UnitUtil.getNewFont(g2d, buffer.toString(), false, 178, 7.0f));
            g2d.drawString(buffer.toString(), positionX, positionY);
        }
        if (ba.hasCamoSystem()) {
            Font font = UnitUtil.getNewFont(g2d, buffer.toString(), false, 178, 7.0f);
            g2d.setFont(font);
            String camoString = (ba.getCamoName() + " (+2 - hexes moved)");
            g2d.drawString(camoString, 35, positionY-lineFeed);
        }
    }

    public static void printBattleArmorWeaponsNEquipment(BattleArmor ba, Graphics2D g2d) {
        ImageHelperBattleArmor.printBattleArmorWeaponsNEquipment(ba, g2d, 0);
    }

    public static void printBattleArmorWeaponsNEquipment(BattleArmor ba, Graphics2D g2d, float offset) {

        int typePoint = 34;
        int damagePoint = 133;
        int minPoint = 155;
        int shtPoint = 172;
        int medPoint = 188;
        int longPoint = 204;
        float linePoint = 150f + offset;
        float maxHeight = 38.0f;
        float lineFeed = 6.7f;
        float stringHeight = 0f;

        boolean newLineNeeded = false;

        ArrayList<ArrayList<EquipmentInfo>> equipmentLocations = new ArrayList<ArrayList<EquipmentInfo>>(BattleArmor.LOC_TROOPER_6 + 1);

        for (int pos = 0; pos <= BattleArmor.LOC_TROOPER_6; pos++) {
            equipmentLocations.add(pos, new ArrayList<EquipmentInfo>());
        }

        boolean hasNarcCompact = false;
        boolean hasMineLayer = false;
        Mounted glove = null;
        int numberOfGloves = 0;

        for (Mounted eq : ba.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE) || !UnitUtil.isPrintableBAEquipment(eq.getType())) {
                continue;
            }

            if (!hasNarcCompact && (eq.getType() instanceof ISBACompactNarc)) {
                hasNarcCompact = true;
            } else if (hasNarcCompact && (eq.getType() instanceof ISBACompactNarc)) {
                continue;
            }
            if (!hasMineLayer && eq.getType().hasFlag(MiscType.F_MINE) && eq.getType().hasFlag(MiscType.F_BA_EQUIPMENT)) {
                hasMineLayer = true;
            } else if (hasMineLayer && eq.getType().hasFlag(MiscType.F_MINE) && eq.getType().hasFlag(MiscType.F_BA_EQUIPMENT)) {
                continue;
            }

            if (UnitUtil.isManipulator(eq)) {

                if ((glove != null) && (glove.getName().equals(eq.getName()))) {
                    numberOfGloves++;
                    continue;
                } else {
                    glove = eq;
                    numberOfGloves = 1;
                }

            }

            equipmentLocations.get(eq.getLocation()).add(new EquipmentInfo(ba, eq));

        }

        if (!ba.isFireResistant() && !ba.isStealthActive() && !ba.isReactive() && !ba.isReflective()) {
            maxHeight += lineFeed;
        }
        if (ba.hasCamoSystem()) {
            maxHeight -= lineFeed;
        }

        if (ba.isBurdened()) {
            maxHeight -= lineFeed;
        }
        if (ba.hasDWP()) {
            maxHeight -= lineFeed;
        }
        if (ba.isExoskeleton() && !ba.hasWorkingMisc(MiscType.F_EXTENDED_LIFESUPPORT)) {
            maxHeight -= lineFeed;
        }

        Font font = ImageHelperBattleArmor.getBattleArmorWeaponsNEquipmentFont(g2d, false, maxHeight, equipmentLocations, 7.0f);
        g2d.setFont(font);
        stringHeight = ImageHelper.getStringHeight(g2d, "H", font);

        // linePoint -= stringHeight / 2;

        lineFeed = stringHeight;

        for (int pos = BattleArmor.LOC_SQUAD; pos <= BattleArmor.LOC_TROOPER_6; pos++) {

            ArrayList<EquipmentInfo> equipmentList = equipmentLocations.get(pos);

            if (equipmentList.size() < 1) {
                continue;
            }
            boolean indented = false;
            if ((pos != BattleArmor.LOC_SQUAD) && !hasNarcCompact && !hasMineLayer) {
                String loc = ba.getLocationName(pos);
                g2d.setFont(UnitUtil.getNewFont(g2d, loc, false, 68, font.getSize()));
                g2d.drawString(loc, typePoint, linePoint);
                g2d.setFont(font);
                linePoint += lineFeed;
                typePoint += 5;
                indented = true;
            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                String name = eqi.name.trim();

                if (eqi.isBACargolifter) {
                    float tons = (numberOfGloves*0.5f)/2.0f;
                    name += " ("+Double.toString(tons)+" ton"+((tons%1)==0?"":"s ")+"lifting capability)";
                } else if (eqi.isManipulator && (numberOfGloves > 1)) {
                    if (!eqi.isBACargolifter) {
                        name += " (2)";
                    }
                }

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 88, font.getSize()));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printBC3iName(g2d, typePoint, linePoint, font, false, ba.isMixedTech() && TechConstants.isClan(ba.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printBC3Name(g2d, typePoint, linePoint, font, false, ba.isMixedTech() && TechConstants.isClan(ba.getTechLevel()));
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                // font = UnitUtil.deriveFont(7.0f);
                g2d.setFont(font);

                if (eqi.isWeapon) {
                    if (eqi.isMML) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("LRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("6", minPoint, linePoint);
                        g2d.drawString("7", shtPoint, linePoint);
                        g2d.drawString("14", medPoint, linePoint);
                        g2d.drawString("21", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("SRM", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawString("\u2014", minPoint, linePoint);
                        //g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else if (eqi.isATM) {
                        ImageHelper.printCenterString(g2d, "[M,S,C]", font, damagePoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Standard", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "2/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("5", shtPoint, linePoint);
                        g2d.drawString("10", medPoint, linePoint);
                        g2d.drawString("15", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("Extended-Range", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "1/Msl", font, damagePoint, linePoint);
                        g2d.drawString("4", minPoint, linePoint);
                        g2d.drawString("9", shtPoint, linePoint);
                        g2d.drawString("18", medPoint, linePoint);
                        g2d.drawString("27", longPoint, linePoint);
                        linePoint += lineFeed - 1.0f;

                        g2d.drawString("High-Explosive", typePoint, linePoint);
                        ImageHelper.printCenterString(g2d, "3/Msl", font, damagePoint, linePoint);
                        g2d.drawString("\u2014", minPoint, linePoint);
                        //g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else {
                        g2d.setFont(UnitUtil.getNewFont(g2d, eqi.damage, false, 30, font.getSize()));
                        ImageHelper.printCenterString(g2d, eqi.damage, g2d.getFont(), damagePoint, linePoint);
                        g2d.setFont(font);
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, (int) linePoint);
                        } else {
                            g2d.drawString("\u2014", minPoint, linePoint);
                            //g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.shtRange > 0) {
                            g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, (int) linePoint);
                        } else {
                            g2d.drawString("\u2014", shtPoint, linePoint);
                            //g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.medRange > 0) {
                            g2d.drawString(Integer.toString(eqi.medRange), medPoint, (int) linePoint);
                        } else {
                            g2d.drawString("\u2014", medPoint, linePoint);
                            //g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                        }
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                        } else {
                            g2d.drawString("\u2014", longPoint, linePoint);
                            //g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                        }
                    }
                } else {
                    g2d.setFont(UnitUtil.getNewFont(g2d, eqi.damage, false, 30, font.getSize()));
                    ImageHelper.printCenterString(g2d, eqi.damage, g2d.getFont(), damagePoint, linePoint);
                    g2d.setFont(font);
                    if (eqi.minRange > 0) {
                        g2d.drawString(Integer.toString(eqi.minRange), minPoint, (int) linePoint);
                    } else {
                        g2d.drawString("\u2014", minPoint, linePoint);
                        //g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.shtRange > 0) {
                        g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, (int) linePoint);
                    } else {
                        g2d.drawString("\u2014", shtPoint, linePoint);
                        //g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.medRange > 0) {
                        g2d.drawString(Integer.toString(eqi.medRange), medPoint, (int) linePoint);
                    } else {
                        g2d.drawString("\u2014", medPoint, linePoint);
                        //g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint - 2);
                    }
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                    } else {
                        g2d.drawString("\u2014", longPoint, linePoint);
                        //g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint - 2);
                    }
                }

                if (eqi.hasAmmo) {
                    if (!newLineNeeded) {
                        newLineNeeded = true;
                    }
                    StringBuilder ammoString = new StringBuilder("Ammo ");

                    if (eqi.isCompactNarc || eqi.isBAPopUpMine) {
                        for (int baPos = 0; baPos < ba.getNumberActiverTroopers(); baPos++) {
                            for (int ammoCount = 0; ammoCount < eqi.ammoCount; ammoCount++) {
                                ammoString.append("O ");
                            }
                            ammoString.append("/ ");
                        }
                        ammoString.setLength(ammoString.length() - 2);
                    } else {
                        for (int ammoCount = 1; ammoCount <= eqi.ammoCount; ammoCount++) {
                            ammoString.append("O ");
                        }
                    }

                    g2d.setFont(UnitUtil.getNewFont(g2d, ammoString.toString(), false, 138, font.getSize()));
                    g2d.drawString(ammoString.toString(), typePoint + 5, (int) (linePoint + lineFeed));
                    g2d.setFont(font);
                }

                if (eqi.isBAMineLayer) {
                    StringBuilder ammoString = new StringBuilder("Ammo ");
                    for (int baPos = 0; baPos < ba.getNumberActiverTroopers(); baPos++) {
                        ammoString.append("O O / ");
                    }
                    ammoString.setLength(ammoString.length() - 2);
                    g2d.setFont(UnitUtil.getNewFont(g2d, ammoString.toString(), false, 138, font.getSize()));
                    g2d.drawString(ammoString.toString(), typePoint + 5, (int) (linePoint + lineFeed));
                    g2d.setFont(font);
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                if (indented) {
                    typePoint -= 5;
                }
            }
        }
        if (ba.isBurdened() && ((ba.getJumpMP(false, true, true) > 0) || UnitUtil.canLegAttack(ba) || UnitUtil.canSwarm(ba))) {
            String burdenInfo = "Must detach missiles before jumping or swarm/leg attacks.";
            g2d.setFont(UnitUtil.getNewFont(g2d, burdenInfo, false, 175, 7.0f));
            g2d.drawString(burdenInfo, typePoint, linePoint);
            linePoint += lineFeed;
            g2d.setFont(font);
        }
        if (ba.isExoskeleton() && !ba.hasWorkingMisc(MiscType.F_EXTENDED_LIFESUPPORT)) {
            String exoInfo = "unsealed Exoskeleton";
            g2d.setFont(UnitUtil.getNewFont(g2d, exoInfo, false, 175, 7.0f));
            g2d.drawString(exoInfo, typePoint, linePoint);
            linePoint += lineFeed;
            g2d.setFont(font);
        }
        if (ba.hasDWP()) {
            String burdenInfo;
            if (ba.getJumpMP(true, true, true) > 0) {
                burdenInfo = "Must detach DWP before jumping or moving full ground speed.";
            } else {
                burdenInfo = "Must detach DWP before moving full ground speed.";
            }
            g2d.setFont(UnitUtil.getNewFont(g2d, burdenInfo, false, 175, 7.0f));
            g2d.drawString(burdenInfo, typePoint, linePoint);
            linePoint += lineFeed;
            g2d.setFont(font);
        }
        ImageHelperBattleArmor.printBAArmor(ba, g2d, lineFeed, offset);
    }

    public static Image getBASquad() {
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;
        return new ImageIcon(path + "twba-squad.png").getImage();
    }

    public static Font getBattleArmorWeaponsNEquipmentFont(Graphics2D g2d, boolean bold, float stringHeight, ArrayList<ArrayList<EquipmentInfo>> equipmentLocations, float pointSize) {

        Font font = UnitUtil.deriveFont(pointSize);

        int weaponCount = 0;
        for (int pos = BattleArmor.LOC_SQUAD; pos <= BattleArmor.LOC_TROOPER_6; pos++) {

            ArrayList<EquipmentInfo> equipmentList = equipmentLocations.get(pos);

            if (equipmentList.size() < 1) {
                continue;
            }

            if (pos != BattleArmor.LOC_SQUAD) {
                weaponCount++;
            }

            for (EquipmentInfo eqi : equipmentList) {

                weaponCount++;
                if (eqi.isWeapon) {
                    if (eqi.isMML) {
                        weaponCount++;
                    } else if (eqi.isATM) {
                        weaponCount++;
                    } /*
                       * else { if (ImageHelper.getStringWidth(g2d,
                       * eqi.damage.trim(), font) > 22) { weaponCount++; } }
                       */

                    if (eqi.hasAmmo) {
                        weaponCount++;
                    }
                }
            }
        }

        while (((ImageHelper.getStringHeight(g2d, "H", font) * weaponCount) > stringHeight) && (pointSize > 0)) {
            pointSize -= .1;
            font = UnitUtil.deriveFont(bold, pointSize);
        }
        return font;
    }

}
