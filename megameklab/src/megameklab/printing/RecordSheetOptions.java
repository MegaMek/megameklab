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
    }

    public PaperSize getPaperSize() {
        return paperSize;
    }

    public Boolean useColor() {
        return color;
    }

    public Boolean showQuirks() {
        return quirks;
    }
    public void setQuirks(Boolean quirks) {
        this.quirks = quirks;
    }
    public Boolean showPilotData() {
        return pilotData;
    }

    public Boolean getIncludeC3inBV() {
        return c3bv;
    }

    public Boolean showRole() {
        return role;
    }
    public Boolean showHeatProfile() {
        return heatProfile;
    }

    public void setIncludeC3inBv(Boolean enable) {
        this.c3bv = enable;
    }

    public void setPilotData(Boolean pilotData) {
        this.pilotData = pilotData;
    }

    public Boolean showEraIcon() {
        return eraIcon;
    }

    public Boolean useTacOpsHeat() {
        return tacOpsHeat;
    }

    public Boolean useEraBaseProgression() {
        return eraBasedProgression;
    }

    public Boolean showReferenceCharts() {
        return referenceCharts;
    }

    public Boolean showCondensedReferenceCharts() {
        return condensedReferenceCharts;
    }

    public Boolean useRowShading() {
        return rowShading;
    }

    public Boolean useAlternateArmorGrouping() {
        return alternateArmorGrouping;
    }

    public Boolean isFrameless() {
        return frameless;
    }

    public Boolean useBoldType() {
        return boldType;
    }

    public void setPaperSize(PaperSize paperSize) {
        this.paperSize = paperSize;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public void setEraIcon(Boolean eraIcon) {
        this.eraIcon = eraIcon;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }

    public void setHeatProfile(Boolean heatProfile) {
        this.heatProfile = heatProfile;
    }

    public void setTacOpsHeat(Boolean tacOpsHeat) {
        this.tacOpsHeat = tacOpsHeat;
    }

    public void setEraBasedProgression(Boolean eraBased) {
        eraBasedProgression = eraBased;
    }

    public void setReferenceCharts(Boolean charts) {
        this.referenceCharts = charts;
    }

    public void setCondensedReferenceCharts(Boolean charts) {
        this.condensedReferenceCharts = charts;
    }

    public void setRowShading(Boolean rowShading) {
        this.rowShading = rowShading;
    }

    public void setAlternateArmorGrouping(Boolean alternateArmorGrouping) {
        this.alternateArmorGrouping = alternateArmorGrouping;
    }

    public void setFrameless(Boolean frameless) {
        this.frameless = frameless;
    }

    public void setBoldType(Boolean boldType) {
        this.boldType = boldType;
    }
}
