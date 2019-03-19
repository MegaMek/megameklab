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

/**
 * A set of options for controlling what is displayed on the record sheet
 * 
 * @author neoancient
 *
 */
public class RecordSheetOptions {
    
    private boolean quirks = true;
    private boolean pilotData = true;
    private boolean eraIcon = true;
    private boolean role = true;
    
    public RecordSheetOptions() {
        this.quirks = CConfig.getBooleanParam(CConfig.RS_SHOW_QUIRKS);
        this.pilotData = CConfig.getBooleanParam(CConfig.RS_SHOW_PILOT_DATA);
        this.eraIcon = CConfig.getBooleanParam(CConfig.RS_SHOW_ERA);
        this.role = CConfig.getBooleanParam(CConfig.RS_SHOW_ROLE);
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
    
    public void setPilotData(boolean pilotData) {
        this.pilotData = pilotData;
    }
    
    public boolean showEraIcon() {
        return eraIcon;
    }
    
    public void setEraIcon(boolean eraIcon) {
        this.eraIcon = eraIcon;
    }

}
