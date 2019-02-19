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
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ImageIcon;

import megamek.common.AmmoType;
import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.Protomech;
import megamek.common.TechConstants;

public class ImageHelperProto {
    public static void drawProtoISPip(Graphics2D g2d, int width, int height) {
        Dimension fillCircle = new Dimension(3, 3);
        g2d.setColor(Color.GRAY);
        g2d.fillOval(width + 1, height + 1, fillCircle.width, fillCircle.height);
        g2d.setColor(Color.BLACK);
    }

    public static Image getProtoMech(int number) {
        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;

        if (number == 2) {
            return new ImageIcon(path + "twproto-singlemiddle.png").getImage();
        }

        return new ImageIcon(path + "twproto-singletop.png").getImage();
    }

    public static Image getProtoLogo() {

        String path = new File(ImageHelper.recordSheetPath).getAbsolutePath() + File.separatorChar;

        return new ImageIcon(path + "twproto-logo.png").getImage();

    }

    public static void printProtoAmmo(Entity proto, Graphics2D g2d, int offset) {
        if (proto.getAmmo().size() < 1) {
            return;
        }

        int pointY = 160 + offset;
        int pointX = 124;

        HashMap<String, Integer> ammoHash = new HashMap<String, Integer>();

        for (Mounted ammo : proto.getAmmo()) {
            // don't print one shot ammo
            if (ammo.getLocation() == Entity.LOC_NONE) {
                continue;
            }
            AmmoType aType = (AmmoType) ammo.getType();
            String shortName = aType.getShortName().replace("Ammo", "");
            shortName = shortName.replace('(', '.').replace(')', '.').replace(".Clan.", "");
            shortName = shortName.replace("-capable", "");
            shortName += " ";
            if ((aType.getAmmoType() == AmmoType.T_AC) || (aType.getAmmoType() == AmmoType.T_MML)
                    || (aType.getAmmoType() == AmmoType.T_SRM) || (aType.getAmmoType() == AmmoType.T_SRM_STREAK)
                    || (aType.getAmmoType() == AmmoType.T_SRM_TORPEDO) || (aType.getAmmoType() == AmmoType.T_LRM)
                    || (aType.getAmmoType() == AmmoType.T_LRM_STREAK) || (aType.getAmmoType() == AmmoType.T_LRM_TORPEDO)
                    || (aType.getAmmoType() == AmmoType.T_MML) || (aType.getAmmoType() == AmmoType.T_AC)
                    || (aType.getAmmoType() == AmmoType.T_AC_LBX) || (aType.getAmmoType() == AmmoType.T_AC_LBX_THB)
                    || (aType.getAmmoType() == AmmoType.T_AC_ROTARY) || (aType.getAmmoType() == AmmoType.T_AC_ULTRA)
                    || (aType.getAmmoType() == AmmoType.T_AC_ULTRA_THB) || (aType.getAmmoType() == AmmoType.T_MRM)
                    || (aType.getAmmoType() == AmmoType.T_MRM_STREAK) || (aType.getAmmoType() == AmmoType.T_ATM)
                    || (aType.getAmmoType() == AmmoType.T_HAG) || (aType.getAmmoType() == AmmoType.T_EXLRM)) {
                // shortName = shortName.replaceFirst(" ", " " +
                // aType.getRackSize() + " ");
                shortName = shortName.replaceFirst("  Artemis", " Artemis");
            }
            shortName = shortName.trim();

            if (ammoHash.containsKey(shortName)) {
                int currentAmmo = ammoHash.get(shortName);
                currentAmmo += ammo.getUsableShotsLeft();
                ammoHash.put(shortName, currentAmmo);
            } else {
                int currentAmmo = ammo.getUsableShotsLeft();
                ammoHash.put(shortName, currentAmmo);
            }
        }
        if (ammoHash.keySet().size() == 0) {
            return;
        }
        StringBuffer sb = new StringBuffer("Ammo: ");
        g2d.setFont(UnitUtil.getNewFont(g2d, sb.toString(), false, 20, 7.0f));

        sb = new StringBuffer("Ammo: ");
        int linecount = 0;
        for (String ammo : ammoHash.keySet()) {
            sb.append("(");
            sb.append(ammo);
            sb.append(") ");
            sb.append(ammoHash.get(ammo));
            sb.append(", ");
        }

        double stringLength = ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont());
        linecount = (int) Math.floor(stringLength / 160);

        sb.setLength(0);
        sb.append("Ammo: ");

        g2d.drawString(sb.toString(), pointX,
                pointY - ((linecount) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
        pointX += ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont());
        sb = new StringBuffer();
        int linesprinted = 0;
        int currentStringLength = 0;

        for (String ammo : ammoHash.keySet()) {
            currentStringLength = sb.length();
            sb.append("(");
            sb.append(ammo);
            sb.append(") ");
            sb.append(ammoHash.get(ammo));
            sb.append(", ");
            if ((ImageHelper.getStringWidth(g2d, sb.toString(), g2d.getFont()) > 160) && (linesprinted < linecount)) {
                sb.setLength(sb.length() - ((sb.length() - currentStringLength) + 2));
                g2d.drawString(sb.toString(), pointX, pointY - ((linecount - linesprinted)
                        * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
                linesprinted++;
                sb.setLength(0);
                sb.append("(");
                sb.append(ammo);
                sb.append(") ");
                sb.append(ammoHash.get(ammo));
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
            g2d.drawString(sb.toString(), pointX, pointY
                    - ((linecount - linesprinted) * ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont())));
            pointY += ImageHelper.getStringHeight(g2d, sb.toString(), g2d.getFont());
        }
    }

    public static void printProtomechWeaponsNEquipment(Protomech ba, Graphics2D g2d) {
        ImageHelperProto.printProtomechWeaponsNEquipment(ba, g2d, 0);
    }

    public static void printProtomechWeaponsNEquipment(Protomech proto, Graphics2D g2d, float offset) {
        int locationPoint = 124;
        int typePoint = 151;
        int damagePoint = 224;
        int minPoint = 242;
        int shtPoint = 260;
        int medPoint = 274;
        int longPoint = 290;
        float linePoint = 116f + offset;
        boolean torsoWeaponUsed = false;

        float lineFeed = 6.2f;

        boolean newLineNeeded = false;

        ArrayList<Vector<EquipmentInfo>> equipmentLocations = new ArrayList<Vector<EquipmentInfo>>(
                Protomech.LOC_MAINGUN + 1);

        for (int pos = 0; pos <= Protomech.LOC_MAINGUN; pos++) {
            equipmentLocations.add(pos, new Vector<EquipmentInfo>());
        }

        for (Mounted eq : proto.getEquipment()) {

            if ((eq.getType() instanceof AmmoType) || (eq.getLocation() == Entity.LOC_NONE)
                    || !UnitUtil.isPrintableEquipment(eq.getType())) {
                continue;
            }

            Vector<EquipmentInfo> eqHash = equipmentLocations.get(eq.getLocation());

            String equipmentName = eq.getName();
            if (eq.isRearMounted()) {
                equipmentName += "(R)";
            }

            EquipmentInfo eqi = new EquipmentInfo(proto, eq);
            eqi.name = equipmentName;
            eqHash.add(eqi);

        }

        Font font = UnitUtil.deriveFont(true, 10.0f);
        g2d.setFont(font);

        for (int pos = Protomech.LOC_HEAD; pos <= Protomech.LOC_MAINGUN; pos++) {

            Vector<EquipmentInfo> eqVector = equipmentLocations.get(pos);

            if (eqVector.size() < 1) {
                continue;
            }

            int count = 0;

            ArrayList<EquipmentInfo> equipmentList = new ArrayList<EquipmentInfo>();

            for (EquipmentInfo eqi : eqVector) {
                equipmentList.add(eqi);
            }

            Collections.sort(equipmentList, StringUtils.equipmentInfoComparator());

            for (EquipmentInfo eqi : equipmentList) {
                newLineNeeded = false;

                if (count >= 12) {
                    break;
                }

                font = UnitUtil.deriveFont(5.0f);
                g2d.setFont(font);

                if ((pos == Protomech.LOC_TORSO) && !torsoWeaponUsed) {
                    g2d.drawString(String.format("%1$9s A:", proto.getLocationName(pos)), locationPoint, linePoint);
                    torsoWeaponUsed = true;
                } else if ((pos == Protomech.LOC_TORSO) && torsoWeaponUsed) {
                    g2d.drawString(String.format("%1$9s B:", proto.getLocationName(pos)), locationPoint, linePoint);
                } else {
                    g2d.drawString(String.format("%1$9s:", proto.getLocationName(pos)), locationPoint, linePoint);
                }

                String name = eqi.name.trim();

                g2d.setFont(UnitUtil.getNewFont(g2d, name, false, 68, 7.0f));

                if (eqi.c3Level == EquipmentInfo.C3I) {
                    ImageHelper.printC3iName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3EM) {
                    ImageHelper.printC3EmName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3S) {
                    ImageHelper.printC3sName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3M) {
                    ImageHelper.printC3mName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3SB) {
                    ImageHelper.printC3sbName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3MB) {
                    ImageHelper.printC3mbName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else if (eqi.c3Level == EquipmentInfo.C3REMOTESENSOR) {
                    ImageHelper.printC3RemoteSensorName(g2d, typePoint, linePoint, font, false,
                            proto.isMixedTech() && TechConstants.isClan(proto.getTechLevel()));
                } else {
                    g2d.drawString(name, typePoint, linePoint);
                }
                font = UnitUtil.deriveFont(7.0f);
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
                        // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint -
                        // 2);
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
                        // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint -
                        // 2);
                        g2d.drawString("3", shtPoint, linePoint);
                        g2d.drawString("6", medPoint, linePoint);
                        g2d.drawString("9", longPoint, linePoint);

                    } else {
                        if (ImageHelper.getStringWidth(g2d, eqi.damage.trim(), font) > 22) {
                            font = UnitUtil.deriveFont(6.0f);
                            g2d.setFont(font);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(0, eqi.damage.indexOf('[')), font,
                                    damagePoint, linePoint);
                            ImageHelper.printCenterString(g2d, eqi.damage.substring(eqi.damage.indexOf('[')), font,
                                    damagePoint, (linePoint + lineFeed) - 1.0f);
                            newLineNeeded = true;
                        } else {
                            ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint, linePoint);
                        }
                        if (eqi.minRange > 0) {
                            g2d.drawString(Integer.toString(eqi.minRange), minPoint, linePoint);
                        } else {
                            g2d.drawString("\u2014", minPoint, linePoint);
                            // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint -
                            // 2);
                        }
                        g2d.drawString(Integer.toString(eqi.shtRange), shtPoint, linePoint);
                        g2d.drawString(Integer.toString(eqi.medRange), medPoint, linePoint);
                        if (eqi.longRange > 0) {
                            g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                        } else {
                            g2d.drawString("\u2014", longPoint, linePoint);
                            // g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint -
                            // 2);
                        }
                    }
                } else {
                    ImageHelper.printCenterString(g2d, eqi.damage, font, damagePoint - 2, linePoint);
                    g2d.drawString("\u2014", minPoint, linePoint);
                    g2d.drawString("\u2014", shtPoint, linePoint);
                    g2d.drawString("\u2014", medPoint, linePoint);
                    // g2d.drawLine(minPoint, (int) linePoint - 2, minPoint + 6, (int) linePoint -
                    // 2);
                    // g2d.drawLine(shtPoint, (int) linePoint - 2, shtPoint + 6, (int) linePoint -
                    // 2);
                    // g2d.drawLine(medPoint, (int) linePoint - 2, medPoint + 6, (int) linePoint -
                    // 2);
                    if (eqi.longRange > 0) {
                        g2d.drawString(Integer.toString(eqi.longRange), longPoint, (int) linePoint);
                    } else {
                        g2d.drawString("\u2014", longPoint, linePoint);
                        // g2d.drawLine(longPoint, (int) linePoint - 2, longPoint + 6, (int) linePoint -
                        // 2);
                    }
                }

                linePoint += lineFeed;
                if (newLineNeeded) {
                    linePoint += lineFeed;
                }
                count++;
            }
        }

        if (proto.hasMyomerBooster()) {
            g2d.drawString("Protomech Myomer Booster", 115, 155 + (int) offset);
        }

        ImageHelperProto.printProtoAmmo(proto, g2d, (int) offset);
    }

}
