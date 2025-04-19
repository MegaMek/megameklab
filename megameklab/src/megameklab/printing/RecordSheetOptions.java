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

import megameklab.util.CConfig;

/**
 * A set of options for controlling what is displayed on the record sheet
 *
 * @author neoancient
 *
 */
public class RecordSheetOptions {

    private PaperSize paperSize;
    private boolean color;
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

    public RecordSheetOptions() {
        String paper = CConfig.getParam(CConfig.RS_PAPER_SIZE, PaperSize.US_LETTER.name());
        try {
            this.paperSize = PaperSize.valueOf(paper);
        } catch (Exception ex) {
            this.paperSize = PaperSize.US_LETTER;
        }
        this.color = CConfig.getBooleanParam(CConfig.RS_COLOR);
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
    }

    public RecordSheetOptions(RecordSheetOptions options) {
        paperSize = options.paperSize;
        color = options.color;
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
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public boolean useColor() {
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

    public void setColor(boolean color) {
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
}
