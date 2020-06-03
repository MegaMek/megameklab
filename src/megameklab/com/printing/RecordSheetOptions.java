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
package megameklab.com.printing;

import megameklab.com.util.CConfig;

import java.awt.print.Paper;

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
    private boolean pilotData;
    private boolean eraIcon;
    private boolean role;
    private boolean heatProfile;
    private boolean tacOpsHeat;

    public RecordSheetOptions() {
        String paper = CConfig.getParam(CConfig.RS_PAPER_SIZE, PaperSize.US_LETTER.name());
        try {
            this.paperSize = PaperSize.valueOf(paper);
        } catch (Exception ex) {
            this.paperSize = PaperSize.US_LETTER;
        }
        this.color = CConfig.getBooleanParam(CConfig.RS_COLOR);
        this.quirks = CConfig.getBooleanParam(CConfig.RS_SHOW_QUIRKS);
        this.pilotData = CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA);
        this.eraIcon = CConfig.getBooleanParam(CConfig.RS_SHOW_ERA);
        this.role = CConfig.getBooleanParam(CConfig.RS_SHOW_ROLE);
        this.heatProfile = CConfig.getBooleanParam(CConfig.RS_HEAT_PROFILE);
        this.tacOpsHeat = CConfig.getBooleanParam(CConfig.RS_TAC_OPS_HEAT);
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
    public void setQuirks(boolean quirks) {
        this.quirks = quirks;
    }
    public boolean showPilotData() {
        return pilotData;
    }
    public boolean showRole() {
        return role;
    }
    public boolean showHeatProfile() {
        return heatProfile;
    }
    
    public void setPilotData(boolean pilotData) {
        this.pilotData = pilotData;
    }
    
    public boolean showEraIcon() {
        return eraIcon;
    }

    public boolean useTacOpsHeat() {
        return tacOpsHeat;
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

}
