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
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Text;
import com.kitfox.svg.Tspan;
import com.kitfox.svg.animation.AnimationElement;

import megamek.common.AmmoType;
import megamek.common.EntityMovementMode;
import megamek.common.EquipmentType;
import megamek.common.Infantry;
import megamek.common.Mounted;
import megamek.common.WeaponType;
import megamek.common.weapons.artillery.ArtilleryWeapon;
import megamek.common.weapons.infantry.InfantryWeapon;
import megameklab.com.util.ImageHelper;

/**
 * @author Neoancient
 *
 */
public class PrintInfantry implements Printable {
	
	/* Id tags of elements in the SVG file */
//	private final static String ID_FLUFF_IMAGE = "imageFluff";
	private final static String ID_PLATOON_NAME = "platoon_name";
	private final static String ID_ARMOR_KIT = "armor_kit";
	private final static String ID_ARMOR_DIVISOR = "armor_divisor";
	private final static String ID_SOLDIER = "soldier_";
	private final static String ID_NO_SOLDIER = "no_soldier_";
	private final static String ID_DAMAGE = "damage_";
	private final static String ID_RANGE_MOD = "range_mod_";
	private final static String ID_UW_LABEL = "uw_range_modifier";
	private final static String ID_UW_RANGE_MOD = "uw_range_mod_";
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
	private final static String ID_DEST_MODS = "dest_mods";
	private final static String ID_SNEAK_CAMO_MODS = "sneak_camo_mods";
	private final static String ID_SNEAK_IR_MODS = "sneak_ir_mods";	
	private final static String ID_BV = "bv";
	private final static String ID_TRANSPORT_WT = "transport_wt";
	private final static String ID_MP_1 = "mp_1";
	private final static String ID_MODE_1 = "movement_mode_1";
	private final static String ID_MP_2 = "mp_2";
	private final static String ID_MODE_2 = "movement_mode_2";
	private final static String ID_NOTES = "notes";
	private final static String ID_NOTE_LINE = "note_line_";

    private Infantry infantry = null;
    private List<Infantry> infantryList;
    private int currentPosition;
    
    public PrintInfantry(List<Infantry> list) {
    	infantryList = list;
    	if (list.size() > 0) {
    	    infantry = list.get(0);
    	}
    }

    
	/* (non-Javadoc)
	 * @see java.awt.print.Printable#print(java.awt.Graphics, java.awt.print.PageFormat, int)
	 */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D) graphics;
        printImage(g2d, pageFormat);
        return Printable.PAGE_EXISTS;
	}

	public void printImage(Graphics2D g2d, PageFormat pageFormat) {
        if ((null == g2d) || (null == infantry)) {
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
        		String name = infantry.getChassis();
        		if (infantry.getModel().length() > 0) {
        		    name += " " + infantry.getModel();
        		}
        		if (name.length() > 48) {
        			tspan.setText(infantry.getChassis());
        		} else {
            		tspan.setText(name);
        		}
        		((Text)tspan.getParent()).rebuild();
        		
        		tspan = (Tspan)diagram.getElement(ID_ARMOR_KIT);
        		EquipmentType armor = infantry.getArmorKit();
        		if (armor != null) {
        			tspan.setText(armor.getName());
            		((Text)tspan.getParent()).rebuild();
        		} else if (infantry.hasDEST()) {
        			tspan.setText("DEST");
            		((Text)tspan.getParent()).rebuild();
        		} else {
            		StringJoiner sj = new StringJoiner("/");
            		if (infantry.hasSneakCamo()) {
            			sj.add("Camo");
            		}
            		if (infantry.hasSneakIR()) {
            			sj.add("IR");
            		}
            		if (infantry.hasSneakECM()) {
            			sj.add("ECM");
            		}
            		if (sj.length() > 0) {
            			tspan.setText("Sneak(" + sj.toString() + ")");
                		((Text)tspan.getParent()).rebuild();
            		}
        		}
        		tspan = (Tspan)diagram.getElement(ID_ARMOR_DIVISOR);
        		tspan.setText(String.valueOf(infantry.getDamageDivisor()
        				+ (infantry.isArmorEncumbering()? "E" : "")));
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
        		if (infantry.getSecondaryWeapon() != null && infantry.getSecondaryN() > 1
        				&& !infantry.getSecondaryWeapon().hasFlag(WeaponType.F_TAG)) {
        			rangeWeapon = infantry.getSecondaryWeapon();
        		}
        		boolean scuba = infantry.getMovementMode() == EntityMovementMode.INF_UMU
        				|| infantry.getMovementMode() == EntityMovementMode.SUBMARINE;
        		if (scuba) {
	        		diagram.getElement(ID_UW_LABEL).removeAttribute("display",
	        				AnimationElement.AT_XML);
        		}
        		InfantryWeapon singleSecondary = (infantry.getSecondaryN() == 1)? infantry.getSecondaryWeapon() : null;
        		for (int j = 0; j <= 21; j++) {
    				tspan = (Tspan)diagram.getElement(ID_RANGE_MOD + j);
    				tspan.setText(rangeMod(j, rangeWeapon, singleSecondary, false));
    				if (scuba) {
	    				tspan = (Tspan)diagram.getElement(ID_UW_RANGE_MOD + j);
	    				tspan.setText(rangeMod(j, rangeWeapon, singleSecondary, true));
    				}
    				((Text)tspan.getParent()).rebuild();
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
					 * string because gauss explosion flags do not apply, and switchable
					 * only applies for non-LBX.
					 */
					tspan = (Tspan)diagram.getElement(ID_FIELD_GUN_DMG);
					if (gun instanceof ArtilleryWeapon) {
	                    tspan.setText(gun.getRackSize() + " [AE,S,F]");
					} else {
						StringBuilder sb = new StringBuilder(Integer.toString(gun.getDamage()));
						switch (gun.getAmmoType()) {
                        case AmmoType.T_AC_ULTRA:
                        case AmmoType.T_AC_ULTRA_THB:
                            sb.append("/Sht, R2 [DB,R/S/C]");
                            break;
                        case AmmoType.T_AC_ROTARY:
                            sb.append("/Sht, R6 [DB,R/S/C]");
                            break;
                        case AmmoType.T_AC:
                        case AmmoType.T_AC_PRIMITIVE:
                        case AmmoType.T_LAC:
                            sb.append(" [DB,C/S/F]");
                            break;
                        case AmmoType.T_AC_LBX:
                        case AmmoType.T_AC_LBX_THB:
                            sb.append(" [DB,C/F]");
                            break;
                        default:
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
        		
        		if (infantry.hasDEST()) {
        			diagram.getElement(ID_DEST_MODS).removeAttribute("display", AnimationElement.AT_XML);
        			diagram.getElement(ID_SNEAK_IR_MODS).removeAttribute("display", AnimationElement.AT_XML);
        		} else if (infantry.hasSneakCamo()) {
        			diagram.getElement(ID_SNEAK_CAMO_MODS).removeAttribute("display", AnimationElement.AT_XML);        			
        		}
        		if (infantry.hasSneakIR()) {
        			diagram.getElement(ID_SNEAK_IR_MODS).removeAttribute("display", AnimationElement.AT_XML);
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
					mp1.setText(Integer.toString(infantry.getActiveUMUCount()));
					if (infantry.getOriginalJumpMP() > 1) {
						mode1.setText("SCUBA (Motorized)");
					} else {
						mode1.setText("SCUBA");
					}
					mp2.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode2.setText("Ground");
					((Text)mp2.getParent()).rebuild();
					((Text)mode2.getParent()).rebuild();
					break;
				case HOVER:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Mechanized Hover");
					break;
				case TRACKED:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Mechanized Tracked");
					break;
				case WHEELED:
					mp1.setText(Integer.toString(infantry.getWalkMP(true, true, false)));
					mode1.setText("Mechanized Wheeled");
					break;
				case VTOL:
					mp1.setText(Integer.toString(infantry.getJumpMP(false)));
					if (infantry.hasMicrolite()) {
						mode1.setText("VTOL (Microlite)");
					} else {
						mode1.setText("VTOL (Micro-copter)");
					}
					break;
				case SUBMARINE:
					mp1.setText(Integer.toString(infantry.getActiveUMUCount()));
					mode1.setText("Mechanized SCUBA");
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
				
        		List<String> notes = new ArrayList<>();
        		if (infantry.isMechanized() || infantry.isArmorEncumbering()) {
        			notes.add("Cannot make anti-'Mech attacks.");
        		}
        		if (infantry.hasSpaceSuit()) {
        			notes.add("Can operate in vacuum.");
        		}
        		if (rangeWeapon.hasFlag(WeaponType.F_INF_BURST)) {
        			notes.add("+1D6 damage vs. conventional infantry.");
        		}
        		if (rangeWeapon.hasFlag(WeaponType.F_INF_NONPENETRATING)) {
        			notes.add("Can only damage conventional infantry.");
        		}
        		if (infantry.getPrimaryWeapon().hasFlag(WeaponType.F_INFERNO)
        				|| (infantry.getSecondaryWeapon() != null 
        					&& infantry.getSecondaryWeapon().hasFlag(WeaponType.F_INFERNO))) {
    				notes.add("Flame-based weapon.");        			
        		} else {
	        		for (int i = 0; i < infantry.getPrimaryWeapon().getModesCount(); i++) {
	        			if (infantry.getPrimaryWeapon().getMode(i).equals("Heat")) {
	        				notes.add("Flame-based weapon.");
	        				break;
	        			}
	        		}
	        		if (infantry.getSecondaryWeapon() != null) {
		        		for (int i = 0; i < infantry.getSecondaryWeapon().getModesCount(); i++) {
		        			if (infantry.getSecondaryWeapon().getMode(i).equals("Heat")) {
		        				notes.add("Flame-based weapon.");
		        			}
		        		}
	        		}
        		}
        		if (infantry.getPrimaryWeapon().hasFlag(WeaponType.F_INF_AA)
        				|| (infantry.getSecondaryWeapon() != null
        						&& infantry.getSecondaryWeapon().hasFlag(WeaponType.F_INF_AA))) {
        			notes.add("Can attack airborn units.");
        		}
        		if (infantry.hasSpecialization(Infantry.BRIDGE_ENGINEERS)) {
        			notes.add("Bridge-building equipment");
        		}
        		if (infantry.hasSpecialization(Infantry.DEMO_ENGINEERS)) {
        			notes.add("Equipped with demolition gear");
        		}
        		if (infantry.hasSpecialization(Infantry.FIRE_ENGINEERS)) {
        			notes.add("Firefighting equipment");
        		}
        		if (infantry.hasSpecialization(Infantry.MINE_ENGINEERS)) {
        			notes.add("Minesweeper equipment");
        		}
        		if (infantry.hasSpecialization(Infantry.TRENCH_ENGINEERS)) {
        			notes.add("Trench/Fieldwork equipment");
        		}
        		if (infantry.hasSpecialization(Infantry.MARINES)) {
        			notes.add("No penalties for vacuum or zero-G");
        		}
        		if (infantry.hasSpecialization(Infantry.MOUNTAIN_TROOPS)) {
        			notes.add("Mountain climbing equipment");
        		}
        		if (infantry.hasSpecialization(Infantry.PARAMEDICS)) {
        			notes.add("Paramedic equipment.");
        		}
        		if (infantry.hasSpecialization(Infantry.PARATROOPS)) {
        			notes.add("Can make atmospheric drops.");
        		}
        		if (infantry.hasSpecialization(Infantry.SENSOR_ENGINEERS)) {
        			notes.add("Surveillance and communication equipment");
        		}
        		if (infantry.hasSpecialization(Infantry.TAG_TROOPS)) {
        			notes.add("Equipped with TAG (Range 3/6/9)");
        		}
        		if (infantry.hasSneakECM()) {
        			notes.add("Invisible to standard/light active probes.");
        		}
        		
        		for (int i = 0; i < Math.min(8, notes.size()); i++) {
        			tspan = (Tspan)diagram.getElement(ID_NOTE_LINE + i);
        			tspan.setText(notes.get(i));
        		}
    			((Text)diagram.getElement(ID_NOTES)).rebuild();
        		
        		diagram.updateTime(0);
        		
            	diagram.render(g2d);
        	}
        } catch (SVGException ex) {
        	ex.printStackTrace();
        }

        g2d.scale(pageFormat.getImageableWidth(), pageFormat.getImageableHeight());
    }

	private static final int[][] RANGE_MODS = {
			{0},
			{-2, 0, 2, 4},
			{-2, 0, 0, 2, 2, 4, 4},
			{-2, 0, 0, 0, 2, 2, 2, 4, 4, 4},
			{-2, 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4},
			{-1, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4},
			{-1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 4, 4, 4, 5, 5, 5},
			{-1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 6, 6, 6, 6}
	};
	
	/**
	 * Calculate range mod as a string value.
	 * @param range - the range to the target.
	 * @param weapon - the primary weapon if there are no more than one secondary, otherwise secondary
	 * @param singleSecondary - secondary weapon if there is exactly one, otherwise null. This is used
	 * 							to account for point blank or encumbering penalties when the secondary
	 * 							weapon is not the basis for range mods.
	 * @param underwater - whether the base range should be halved for underwater use by SCUBA platoons.
	 * @return - the range mod as a formatted String.
	 */
	private String rangeMod(int range, InfantryWeapon weapon, InfantryWeapon otherWeapon, boolean underwater) {
		int[] mods = RANGE_MODS[weapon.getInfantryRange()];
		if (underwater) {
			mods = RANGE_MODS[weapon.getInfantryRange() / 2];
		}
		
		if (range >= mods.length) {
			return "—";
		}
		int mod = mods[range];
		if (range == 0) {
			if (weapon.hasFlag(WeaponType.F_INF_BURST)) {
				mod--;
			}
			if (weapon.hasFlag(WeaponType.F_INF_POINT_BLANK)
					|| (otherWeapon != null && otherWeapon.hasFlag(WeaponType.F_INF_POINT_BLANK))) {
				mod++;
			}
			if (weapon.hasFlag(WeaponType.F_INF_ENCUMBER)
					|| (otherWeapon != null && otherWeapon.hasFlag(WeaponType.F_INF_ENCUMBER))) {
				mod++;
			}
		}
		if (mod > 0) {
			return "+" + mod;
		}
		return Integer.toString(mod);
	}

}
