/*
 * MegaMekLab - Copyright (C) 2017 The MegaMek Team
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
package megameklab.com.ui.Infantry.Printing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringJoiner;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.PrintQuality;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.AmmoType;
import megamek.common.Compute;
import megamek.common.Infantry;
import megamek.common.Mounted;
import megamek.common.TargetRollModifier;
import megamek.common.WeaponType;
import megamek.common.weapons.ArtilleryWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.util.ImageHelper;

/**
 * @author Neoancient
 *
 */
public class PrintInfantry implements Printable {
	
	/* Id tags of elements in the SVG file */
	private final static String ID_FLUFF_IMAGE = "imageFluff";
	private final static String ID_PLATOON_NAME = "platoon_name";
	private final static String ID_ARMOR_KIT = "armor_kit";
	private final static String ID_ARMOR_DIVISOR = "armor_divisor";
	private final static String ID_SOLDIER = "soldier_";
	private final static String ID_NO_SOLDIER = "no_soldier_";
	private final static String ID_DAMAGE = "damage_";
	private final static String ID_RANGE_MOD = "range_mod_";
	private final static String ID_FIELD_GUN_COLUMNS = "field_gun_columns";
	private final static String ID_FIELD_GUN_QTY = "field_gun_qty";
	private final static String ID_FIELD_GUN_TYPE = "field_gun_type";
	private final static String ID_FIELD_GUN_DMG = "field_gun_dmg";
	private final static String ID_FIELD_GUN_MIN_RANGE = "field_gun_min_range";
	private final static String ID_FIELD_GUN_SHORT = "field_gun_short";
	private final static String ID_FIELD_GUN_MED = "field_gun_med";
	private final static String ID_FIELD_GUN_LONG = "field_gun_long";
	private final static String ID_FIELD_GUN_AMMO = "field_gun_ammo";
	private final static String ID_FIELD_GUN_CREW = "field_gun_crew";
	private final static String ID_BV = "bv";
	private final static String ID_TRANSPORT_WT = "transport_wt";
	private final static String ID_MP_1 = "mp_1";
	private final static String ID_MODE_1 = "movement_mode_1";
	private final static String ID_MP_2 = "mp_2";
	private final static String ID_MODE_2 = "movement_mode_2";

    private Infantry infantry = null;
    private ArrayList<Infantry> infantryList;
    PrinterJob masterPrintJob;
    private int currentPosition;
    
    public PrintInfantry(ArrayList<Infantry> list, PrinterJob masterPrintJob) {
    	infantryList = list;
        this.masterPrintJob = masterPrintJob;
    }

    
	/* (non-Javadoc)
	 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
	 */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex != 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
	}

	public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if (g2d == null) {
            return;
        }
        
        SVGDiagram diagram;
        
        int stop = Math.min(4, infantryList.size() - currentPosition);
    	if (stop > 3) {
    		diagram = ImageHelper.loadSVGImage(new File("data/images/recordsheets/Conventional_Infantry_no_tables.svg"));
    	} else {
    		diagram = ImageHelper.loadSVGImage(new File("data/images/recordsheets/Conventional_Infantry_tables.svg"));
    	}

        try {
        	Tspan tspan = (Tspan)diagram.getElement("text_copyright");
        	tspan.setText(String.format(tspan.getText(), Calendar.getInstance().get(Calendar.YEAR)));
        	((Text)tspan.getParent()).rebuild();
            diagram.render(g2d);

            for (int pos = 0; pos < stop; pos++) {
                diagram = ImageHelper.loadSVGImage(new File("data/images/recordsheets/Conventional_Infantry_platoon_"
                		+ (pos + 1) + ".svg"));
        		infantry = infantryList.get(pos + currentPosition);
        		tspan = (Tspan)diagram.getElement(ID_PLATOON_NAME);
        		tspan.setText(infantry.getShortName());
        		((Text)tspan.getParent()).rebuild();
        		
        		tspan = (Tspan)diagram.getElement(ID_ARMOR_KIT);
        		StringJoiner sj = new StringJoiner("/");
        		if (infantry.hasSpaceSuit()) {
        			sj.add("Space Suit");
        		}
        		if (infantry.hasDEST()) {
        			sj.add("DEST");
        		}
        		if (infantry.hasSneakCamo()) {
        			sj.add("Sneak (Camo)");
        		}
        		if (infantry.hasSneakIR()) {
        			sj.add("Sneak (IR)");
        		}
        		if (infantry.hasSneakECM()) {
        			sj.add("Sneak (ECM)");
        		}
        		if (infantry.isArmorEncumbering()) {
        			sj.add("Encumbering");
        		}
        		if (sj.length() == 0) {
        			sj.add("None");
        		}
        		tspan.setText(sj.toString());
        		((Text)tspan.getParent()).rebuild();
        		tspan = (Tspan)diagram.getElement(ID_ARMOR_DIVISOR);
        		tspan.setText(String.valueOf(infantry.getDamageDivisor()));
        		((Text)tspan.getParent()).rebuild();

        		for (int j = 1; j <= 30; j++) {
        			if (j > infantry.getShootingStrength()) {
        				diagram.getElement(ID_SOLDIER + j)
        					.addAttribute("display", AnimationElement.AT_XML, "none");
        				diagram.getElement(ID_NO_SOLDIER + j)
    						.removeAttribute("display", AnimationElement.AT_XML);
        			} else {
        				tspan = (Tspan)diagram.getElement(ID_DAMAGE + j);
        				tspan.setText(String.valueOf((int)Math.round(infantry.getDamagePerTrooper()
                				* j)));
        				((Text)tspan.getParent()).rebuild();
        			}
        		}
        		diagram.updateTime(0);
        		InfantryWeapon rangeWeapon = infantry.getPrimaryWeapon();
        		if (infantry.getSecondaryWeapon() != null && infantry.getSecondaryN() > 1) {
        			rangeWeapon = infantry.getSecondaryWeapon();
        		}
        		for (int j = 0; j <= 21; j++) {
        			if (rangeWeapon.getInfantryRange() * 3 >= j) {
	        			int mod = Compute.getInfantryRangeMods(j, rangeWeapon)
	        					.getModifiers().stream().mapToInt(TargetRollModifier::getValue).sum();
	    				tspan = (Tspan)diagram.getElement(ID_RANGE_MOD + j);
        				tspan.setText(String.format("%+d", mod));
        				((Text)tspan.getParent()).rebuild();
        			}
        		}
        		
        		int numGuns = 0;
        		int numShots = 0;
        		WeaponType gun = null;
        		for (Mounted m : infantry.getEquipment()) {
        			if (m.getLocation() == Infantry.LOC_FIELD_GUNS) {
        				if (m.getType() instanceof WeaponType) {
        					gun = (WeaponType)m.getType();
        					numGuns++;
        				} else if (m.getType() instanceof AmmoType) {
        					numShots += ((AmmoType)m.getType()).getShots();
        				}
        			}
        		}
        		if (gun == null) {
        			diagram.getElement(ID_FIELD_GUN_COLUMNS).addAttribute("display",
        					AnimationElement.AT_XML, "none");
        		} else {
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_QTY);
					tspan.setText(Integer.toString(numGuns));
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_TYPE);
					tspan.setText(gun.getName());
					((Text)tspan.getParent()).rebuild();
					/* We don't use StringUnits.getEquipmentInfo() to format the damage
					 * string because switchable ammo and gauss explosion flags do not apply.
					 */
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_DMG);
					if (gun instanceof ArtilleryWeapon) {
	                    tspan.setText(gun.getRackSize() + " [AE,S,F]");
					} else {
						StringBuilder sb = new StringBuilder(Integer.toString(gun.getDamage()));
						if (gun.getAmmoType() == AmmoType.T_AC_ULTRA) {
							sb.append("/Sht, R2 [DB,R/C]");
						} else if (gun.getAmmoType() == AmmoType.T_AC_ROTARY) {
							sb.append("/Sht, R6 [DB,R/C]");
						} else if (gun.getAmmoType() == AmmoType.T_AC_LBX) {
							sb.append(" [DB,C,F]");
						} else {
							sb.append(" [DB]");
						}
						tspan.setText(sb.toString());
					}
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_MIN_RANGE);
					if (gun.getMinimumRange() > 0) {
						tspan.setText(Integer.toString(gun.getMinimumRange()));
					} else {
						tspan.setText("—");
					}
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_SHORT);
					tspan.setText(Integer.toString(gun.getShortRange()));
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_MED);
					tspan.setText(Integer.toString(gun.getMediumRange()));
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_LONG);
					tspan.setText(Integer.toString(gun.getLongRange()));
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_AMMO);
					tspan.setText(Integer.toString(numShots));
					((Text)tspan.getParent()).rebuild();
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_CREW);
					tspan.setText(Integer.toString((int)Math.ceil(gun.getTonnage(infantry))));
					((Text)tspan.getParent()).rebuild();
        		}
        		
        		tspan = (Tspan)diagram.getElement(ID_BV);
        		tspan.setText(Integer.toString(infantry.calculateBattleValue()));
        		((Text)tspan.getParent()).rebuild();
        		tspan = (Tspan)diagram.getElement(ID_TRANSPORT_WT);
        		tspan.setText(String.format("%.1f tons", infantry.getWeight()));
        		((Text)tspan.getParent()).rebuild();
        		
        		Tspan mp1 = (Tspan)diagram.getElement(ID_MP_1);
        		Tspan mode1 = (Tspan)diagram.getElement(ID_MODE_1);
        		Tspan mp2 = (Tspan)diagram.getElement(ID_MP_2);
        		Tspan mode2 = (Tspan)diagram.getElement(ID_MODE_2);
        		switch(infantry.getMovementMode()) {
				case INF_JUMP:
					mp1.setText(Integer.toString(infantry.getJumpMP(false)));
					mode1.setText("Jump");
					mp2.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode2.setText("Ground");
					((Text)mp2.getParent()).rebuild();
					((Text)mode2.getParent()).rebuild();
					break;
				case INF_UMU:
					mp1.setText(Integer.toString(infantry.getJumpMP(false)));
					mode1.setText("UMU");
					mp2.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode2.setText("Ground");
					((Text)mp2.getParent()).rebuild();
					((Text)mode2.getParent()).rebuild();
					break;
				case HOVER:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Hover");
					break;
				case TRACKED:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Tracked");
					break;
				case WHEELED:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Wheeled");
					break;
				case VTOL:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("VTOL");
					break;
				case INF_MOTORIZED:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Motorized");
					break;
				case INF_LEG:
				default:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Ground");
					break;
        		}
        		if (mp1.getText().equals("0")) {
        			mp1.setText("0*");
        		}
				((Text)mp1.getParent()).rebuild();
				((Text)mode1.getParent()).rebuild();
        		diagram.updateTime(0);
        		
            	diagram.render(g2d);
        	}
        } catch (SVGException ex) {
        	ex.printStackTrace();
        }

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }
    
    public void print(HashPrintRequestAttributeSet aset) {

        try {
            for (; currentPosition < infantryList.size(); currentPosition += 4) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintService(masterPrintJob.getPrintService());

                aset.add(PrintQuality.HIGH);

                PageFormat pageFormat = new PageFormat();
                pageFormat = pj.getPageFormat(null);

                Paper p = pageFormat.getPaper();
                p.setImageableArea(0, 0, p.getWidth(), p.getHeight());
                pageFormat.setPaper(p);

                pj.setPrintable(this, pageFormat);

                infantry = infantryList.get(currentPosition);
                pj.setJobName(infantry.getChassis() + " " + infantry.getModel());

                try {
                    pj.print(aset);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.gc();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
