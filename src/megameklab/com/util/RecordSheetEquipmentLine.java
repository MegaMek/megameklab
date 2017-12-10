/*
 * MegaMekLab - Copyright (C) 2017 - The MegaMek Team
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

import megamek.common.Mounted;

/**
 * Compiles and formats line(s) on weapons and equipment inventory section of record sheet
 * 
 * @author Neoancient
 *
 */
public class RecordSheetEquipmentLine {
    
    private EquipmentInfo eqInfo;
    private String location;
    private boolean rear;
    private boolean turret;
    
    private final static String DASH = "\u2014";

    private final static int MML_LRM = 1;
    private final static int MML_SRM = 2;
    
    private final static int ATM_STANDARD = 1;
    private final static int ATM_ER       = 2;
    private final static int ATM_HE       = 3;

    public RecordSheetEquipmentLine(Mounted m) {
        eqInfo = new EquipmentInfo(m.getEntity(), m);
        location = m.getEntity().getLocationAbbr(m.getLocation());
        rear = m.isRearMounted();
        turret = m.isMechTurretMounted();
        if (eqInfo.name.length() > 20) {
            eqInfo.name = eqInfo.name.replace(m.getType().getName(), m.getType().getShortName());
        }
    }
    
    public String getNameField(int row, boolean mixedTech) {
        if (row == 0) {
            String name = eqInfo.name;
            if (!mixedTech) {
                name = name.replace("[Clan]","").replace("(Clan)","").trim();
            }
            if (turret) {
                name += " (T)";
            }
            return name;
        }
        if (eqInfo.isMML) {
            if (row == MML_LRM) {
                return "LRM";
            } else if (row == MML_SRM) {
                return "SRM";
            }
        } else if (eqInfo.isATM) {
            if (row == ATM_STANDARD) {
                return "Standard";
            } else if (row == ATM_ER) {
                return "Extended Range";
            } else if (row == ATM_HE) {
                return "High Explosive";
            }
        } else if (eqInfo.hasArtemis) {
            return "w/Artemis IV";
        } else if (eqInfo.hasArtemisV) {
            return "w/Artemis V";
        } else if (eqInfo.hasApollo) {
            return "w/Apollo";
        }
        return "";
    }
    
    public String getLocationField(int row) {
        if (row == 0) {
            return location;
        } else {
            return "";
        }
    }
    
    public String getHeatField(int row) {
        if (row == 0) {
            if (eqInfo.isWeapon) {
                return Integer.toString(eqInfo.heat);
            } else {
                return DASH;
            }
        } else {
            return "";
        }
    }
    
    public String getDamageField(int row) {
        if (eqInfo.isMML) {
            if (row == MML_LRM) {
                return "1/Msl";
            } else if (row == MML_SRM){
                return "2/Msl";
            } else {
                return "[M,C,S]";
            }
        } else if (eqInfo.isATM) {
            if (row == ATM_STANDARD) {
                return "2/Msl";
            } else if (row == ATM_ER) {
                return "1/Msl";
            } else if (row == ATM_HE) {
                return "3/Msl";
            } else {
                return "[M,C,S]";
            }
        } else if (eqInfo.isAMS) {
            return "[PD]";
        } else if (eqInfo.isCenturion) {
            return "0";
        } else if (row == 0) {
            return eqInfo.damage;
        }
        return "";
    }
    
    public String getMinField(int row) {
        if (eqInfo.isMML) {
            if (row == MML_LRM) {
                return "6";
            } else if (row == MML_SRM) {
                return DASH;
            }
        } else if (eqInfo.isATM) {
            if (row == ATM_STANDARD) {
                return "4";
            } else if (row == ATM_ER) {
                return "4";
            } else if (row == ATM_HE) {
                return DASH;
            } else {
                return "";
            }
        } else if (row == 0) {
            if (eqInfo.isWeapon && eqInfo.minRange > 0) {
                return Integer.toString(eqInfo.minRange);
            } else {
                return DASH;
            }
        }
        return "";
    }
    
    public String getShortField(int row) {
        if (eqInfo.isMML) {
            if (row == MML_LRM) {
                return "7";
            } else if (row == MML_SRM) {
                return "3";
            } else {
                return "";
            }
        } else if (eqInfo.isATM) {
            if (row == ATM_STANDARD) {
                return "5";
            } else if (row == ATM_ER) {
                return "9";
            } else if (row == ATM_HE) {
                return "3";
            } else {
                return "";
            }
        } else if (eqInfo.isAMS) {
            return DASH;
        } else if (eqInfo.isCenturion) {
            return "6(1)";
        }
        if (row == 0) {
            if (eqInfo.isWeapon) {
                return Integer.toString(eqInfo.shtRange);
            } else {
                return DASH;
            }
        }
        return "";
    }
    
    public String getMediumField(int row) {
        if (eqInfo.isMML) {
            if (row == MML_LRM) {
                return "14";
            } else if (row == MML_SRM) {
                return "6";
            } else {
                return "";
            }
        } else if (eqInfo.isATM) {
            if (row == ATM_STANDARD) {
                return "10";
            } else if (row == ATM_ER) {
                return "18";
            } else if (row == ATM_HE) {
                return "6";
            } else {
                return "";
            }
        } else if (eqInfo.isAMS) {
            return DASH;
        } else if (eqInfo.isCenturion) {
            return "12(2)";
        }
        if (row == 0) {
            if (eqInfo.isWeapon) {
                return Integer.toString(eqInfo.medRange);
            } else {
                return DASH;
            }
        }
        return "";
    }
    
    public String getLongField(int row) {
        if (eqInfo.isMML) {
            if (row == MML_LRM) {
                return "21";
            } else if (row == MML_SRM) {
                return "9";
            } else {
                return "";
            }
        } else if (eqInfo.isATM) {
            if (row == ATM_STANDARD) {
                return "4";
            } else if (row == ATM_ER) {
                return "27";
            } else if (row == ATM_HE) {
                return "9";
            } else {
                return "";
            }
        } else if (eqInfo.isAMS) {
            return DASH;
        } else if (eqInfo.isCenturion) {
            return "18(3)";
        }
        if (row == 0) {
            if (eqInfo.isWeapon) {
                return Integer.toString(eqInfo.longRange);
            } else {
                return DASH;
            }
        }
        return "";
    }
    
    public String getExtremeField(int row) {
        if (row == 0) {
            if (eqInfo.isWeapon) {
                return Integer.toString(eqInfo.erRange);
            } else {
                return DASH;
            }
        }
        return "";
    }
    
    public int nRows() {
        if (eqInfo.isMML) {
            return 3;
        } else if (eqInfo.isATM) {
            return 4;
        } else if (eqInfo.hasArtemis || eqInfo.hasArtemisV || eqInfo.hasApollo) {
            return 2;
        }
        return 1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eqInfo == null) ? 0 : eqInfo.name.hashCode());
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + (rear ? 1231 : 1237);
        result = prime * result + (turret ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RecordSheetEquipmentLine other = (RecordSheetEquipmentLine) obj;
        if (eqInfo == null) {
            if (other.eqInfo != null)
                return false;
        } else if (!eqInfo.name.equals(other.eqInfo.name))
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (rear != other.rear)
            return false;
        if (turret != other.turret)
            return false;
        return true;
    }
}
