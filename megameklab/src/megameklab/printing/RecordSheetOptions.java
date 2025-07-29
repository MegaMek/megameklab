/*
 * MegaMekLab - Copyright (C) 2019 - The MegaMek Team
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

import megamek.common.enums.WeaponSortOrder;
import megameklab.util.CConfig;

/**
 * A set of options for controlling what is displayed on the record sheet
 *
 * @author neoancient
 *
 */
public class RecordSheetOptions {

    public enum ColorMode {
        ALL("All"),
        LOGO_ONLY("Logo Only"),
        NONE("B&W");

        private final String displayName;
        ColorMode(String displayName) {
            this.displayName = displayName;
        }
        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum HeatScaleMarker {
        ASTERISK("Asterisk"),
        ARROW("Arrow");
        private final String displayName;
        HeatScaleMarker(String displayName) {
            this.displayName = displayName;
        }
        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum HitModStyle {
        NONE("Not visible"),
        COLUMN("Column"),
        EDGE("Edge");
        private final String displayName;
        HitModStyle(String displayName) {
            this.displayName = displayName;
        }
        @Override
        public String toString() {
            return displayName;
        }
    }

    public enum IntrinsicPhysicalAttacksStyle {
        NONE("Not visible"),
        EQUIPMENT("Equipment"),
        FOOTER("Footer");
        private final String displayName;
        IntrinsicPhysicalAttacksStyle(String displayName) {
            this.displayName = displayName;
        }
        @Override
        public String toString() {
            return displayName;
        }
    }

    private PaperSize paperSize;
    private ColorMode color;
    private HeatScaleMarker heatScaleMarker;
    private boolean quirks;
    private boolean c3bv;
    private boolean pilotData;
    private boolean eraIcon;
    private boolean role;
    private boolean heatProfile;
    private boolean tacOpsHeat;
    private boolean eraBasedProgression;
    private boolean referenceCharts;
    private boolean condensedReferenceCharts;
    private boolean rowShading;
    private boolean alternateArmorGrouping;
    private boolean frameless;
    private boolean boldType;
    private boolean damage;
    private String damageColor;
    private WeaponSortOrder weaponsOrder;
    private boolean mergeIdenticalEquipment;
    private HitModStyle includeHitMod;
    private IntrinsicPhysicalAttacksStyle intrinsicPhysicalAttacks;

    public RecordSheetOptions() {
        String paper = CConfig.getParam(CConfig.RS_PAPER_SIZE, PaperSize.US_LETTER.name());
        try {
            this.paperSize = PaperSize.valueOf(paper);
        } catch (Exception ex) {
            this.paperSize = PaperSize.US_LETTER;
        }
        this.color = CConfig.getEnumParam(CConfig.RS_COLOR, ColorMode.class, ColorMode.ALL);
        this.heatScaleMarker = CConfig.getEnumParam(CConfig.RS_HEAT_SCALE_MARKER, HeatScaleMarker.class,
              HeatScaleMarker.ASTERISK);
        this.quirks = CConfig.getBooleanParam(CConfig.RS_SHOW_QUIRKS);
        this.c3bv = CConfig.getBooleanParam(CConfig.RS_SHOW_C3BV);
        this.pilotData = CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA);
        this.eraIcon = CConfig.getBooleanParam(CConfig.RS_SHOW_ERA);
        this.role = CConfig.getBooleanParam(CConfig.RS_SHOW_ROLE);
        this.heatProfile = CConfig.getBooleanParam(CConfig.RS_HEAT_PROFILE);
        this.tacOpsHeat = CConfig.getBooleanParam(CConfig.RS_TAC_OPS_HEAT);
        this.eraBasedProgression = CConfig.getBooleanParam(CConfig.TECH_PROGRESSION);
        this.referenceCharts = CConfig.getBooleanParam(CConfig.RS_REFERENCE);
        this.condensedReferenceCharts = CConfig.getBooleanParam(CConfig.RS_CONDENSED_REFERENCE);
        this.rowShading = CConfig.getBooleanParam(CConfig.RS_ROW_SHADING);
        this.alternateArmorGrouping = CConfig.getBooleanParam(CConfig.RS_ARMOR_GROUPING);
        this.frameless = CConfig.getBooleanParam(CConfig.RS_FRAMELESS);
        this.boldType = CConfig.getBooleanParam(CConfig.RS_BOLD_TYPE);
        this.damage = CConfig.getBooleanParam(CConfig.RS_DAMAGE);
        this.damageColor = CConfig.getParam(CConfig.RS_DAMAGE_COLOR, PrintEntity.FILL_RED);
        this.weaponsOrder = CConfig.getEnumParam(CConfig.RS_WEAPONS_ORDER, WeaponSortOrder.class, WeaponSortOrder.DEFAULT);
        this.mergeIdenticalEquipment = CConfig.getBooleanParam(CConfig.RS_MERGE_IDENTICAL_EQUIPMENT);
        this.includeHitMod = CConfig.getEnumParam(CConfig.RS_HIT_MOD, HitModStyle.class, HitModStyle.NONE);
        this.intrinsicPhysicalAttacks = CConfig.getEnumParam(CConfig.RS_INTRINSIC_PHYSICALS, IntrinsicPhysicalAttacksStyle.class, IntrinsicPhysicalAttacksStyle.NONE);
    }

    public RecordSheetOptions(RecordSheetOptions options) {
        paperSize = options.paperSize;
        color = options.color;
        heatScaleMarker = options.heatScaleMarker;
        quirks = options.quirks;
        c3bv = options.c3bv;
        pilotData = options.pilotData;
        eraIcon = options.eraIcon;
        role = options.role;
        heatProfile = options.heatProfile;
        tacOpsHeat = options.tacOpsHeat;
        eraBasedProgression = options.eraBasedProgression;
        referenceCharts = options.referenceCharts;
        condensedReferenceCharts = options.condensedReferenceCharts;
        rowShading = options.rowShading;
        alternateArmorGrouping = options.alternateArmorGrouping;
        frameless = options.frameless;
        boldType = options.boldType;
        damage = options.damage;
        damageColor = options.damageColor;
        weaponsOrder = options.weaponsOrder;
        mergeIdenticalEquipment = options.mergeIdenticalEquipment;
        includeHitMod = options.includeHitMod;
        intrinsicPhysicalAttacks = options.intrinsicPhysicalAttacks;
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public ColorMode useColor() {
        return color;
    }

    public boolean showQuirks() {
        return quirks;
    }

    public void setQuirks(boolean enabled) {
        this.quirks = enabled;
    }

    public boolean showPilotData() {
        return pilotData;
    }

    public boolean showDamage() {
        return damage;
    }

    public boolean showC3inBV() {
        return c3bv;
    }

    public boolean showRole() {
        return role;
    }
    
    public boolean showHeatProfile() {
        return heatProfile;
    }

    public void setC3inBV(boolean enabled) {
        this.c3bv = enabled;
    }

    public void setPilotData(boolean enabled) {
        this.pilotData = enabled;
    }

    public void setDamage(boolean enabled) {
        this.damage = enabled;
    }

    public boolean showEraIcon() {
        return eraIcon;
    }

    public boolean useTacOpsHeat() {
        return tacOpsHeat;
    }

    public boolean useEraBaseProgression() {
        return eraBasedProgression;
    }

    public boolean showReferenceCharts() {
        return referenceCharts;
    }

    public boolean showCondensedReferenceCharts() {
        return condensedReferenceCharts;
    }

    public boolean useRowShading() {
        return rowShading;
    }

    public boolean useAlternateArmorGrouping() {
        return alternateArmorGrouping;
    }

    public boolean isFrameless() {
        return frameless;
    }

    public boolean useBoldType() {
        return boldType;
    }

    public void setPaperSize(PaperSize paperSize) {
        this.paperSize = paperSize;
    }

    public void setColor(ColorMode color) {
        this.color = color;
    }

    public void setEraIcon(boolean eraIcon) {
        this.eraIcon = eraIcon;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public void setHeatProfile(boolean heatProfile) {
        this.heatProfile = heatProfile;
    }

    public void setTacOpsHeat(boolean tacOpsHeat) {
        this.tacOpsHeat = tacOpsHeat;
    }

    public void setEraBasedProgression(boolean eraBased) {
        eraBasedProgression = eraBased;
    }

    public void setReferenceCharts(boolean charts) {
        this.referenceCharts = charts;
    }

    public void setCondensedReferenceCharts(boolean charts) {
        this.condensedReferenceCharts = charts;
    }

    public void setRowShading(boolean rowShading) {
        this.rowShading = rowShading;
    }

    public void setAlternateArmorGrouping(boolean alternateArmorGrouping) {
        this.alternateArmorGrouping = alternateArmorGrouping;
    }

    public void setFrameless(boolean frameless) {
        this.frameless = frameless;
    }

    public void setBoldType(boolean enabled) {
        this.boldType = enabled;
    }

    public String getDamageColor() {
        return damageColor;
    }
    
    public WeaponSortOrder getWeaponsOrder() {
        return weaponsOrder;
    }
    
    public void setWeaponsOrder(WeaponSortOrder order) {
        this.weaponsOrder = order;
    }

    public boolean mergeIdenticalEquipment() {
        return mergeIdenticalEquipment;
    }

    public void setMergeIdenticalEquipment(boolean mergeIdenticalEquipment) {
        this.mergeIdenticalEquipment = mergeIdenticalEquipment;
    }

    public HitModStyle includeHitMod() {
        return includeHitMod;
    }

    public void setIncludeHitMod(HitModStyle includeHitMod) {
        this.includeHitMod = includeHitMod;
    }

    public IntrinsicPhysicalAttacksStyle intrinsicPhysicalAttacks() {
        return intrinsicPhysicalAttacks;
    }

    public void setIntrinsicPhysicalAttacks(IntrinsicPhysicalAttacksStyle intrinsicPhysicalAttacks) {
        this.intrinsicPhysicalAttacks = intrinsicPhysicalAttacks;
    }

    public HeatScaleMarker getHeatScaleMarker() {
        return heatScaleMarker;
    }

    public void setHeatScaleMarker(HeatScaleMarker heatScaleMarker) {
        this.heatScaleMarker = heatScaleMarker;
    }
}
