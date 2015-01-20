package megameklab.com.ui.BattleArmor;

import megamek.common.BattleArmor;
import megamek.common.CriticalSlot;
import megamek.common.MiscType;
import megamek.common.Mounted;

/**
 * Since BattleArmor is setup in a squad, the standard CriticalSlot system
 * isn't used.  For construction purposes, we keep track of criticals.  In MM,
 * for purposes and dealing with hits, the "locations" for BattleArmor must 
 * correspond to the troopers in the squad.  This means that the standard 
 * Mounted.location can't really be used, and it causes problems with the
 * criticals as well.  Since these really only matter for constructions, a 
 * separate critical system is tracked in MML only for construction purposes.
 **/
public class CriticalSuit {
    
    //store critical slots just like an entity
    protected CriticalSlot[][] crits; // [loc][slot]
    
    BattleArmor ba;

    public CriticalSuit(BattleArmor ba) {
        this.ba = ba;
        crits = new CriticalSlot[locations()][];
        for (int i = 0; i < locations(); i++) {
            int numSlots;
            switch (i) {
            case BattleArmor.MOUNT_LOC_BODY:
                crits[i] = new CriticalSlot[ba.getBodyCrits()];
                break;
            case BattleArmor.MOUNT_LOC_RARM:
                numSlots = ba.getArmCrits();
                if (ba.getRightManipulator() != null) {
                    numSlots++;
                }
                crits[i] = new CriticalSlot[numSlots];
                break;
            case BattleArmor.MOUNT_LOC_LARM:
                numSlots = ba.getArmCrits();
                if (ba.getLeftManipulator() != null) {
                    numSlots++;
                }
                crits[i] = new CriticalSlot[numSlots];
                break;
            }

        }
    }
    
    public int locations() {
        return BattleArmor.MOUNT_NUM_LOCS;
    }

    public int getNumCriticals(int loc) {
        return crits[loc].length;
    }
    
    public boolean canAddMounted(int loc, Mounted m) {
        int critsToAdd;
        if (m.getType().isSpreadable()){
            critsToAdd = 1;
        } else {
            critsToAdd = m.getType().getCriticals(ba);
        }
        int critsAvailable = 0;
        for (int c = 0; c < getNumCriticals(loc); c++) {
            if (crits[loc][c] == null) {
                critsAvailable++;
            }
        }
        return critsAvailable >= critsToAdd;
    }
    
    public void addMounted(int loc, Mounted m){
        // Don't mount unmounted equipment
        if (loc == BattleArmor.MOUNT_LOC_NONE){
            return;
        }
        
        // AP Weapons that are mounted in an AP Mount don't take up slots
        if (m.isAPMMounted() && m.getLinkedBy() != null 
                && m.getLinkedBy().getType().hasFlag(MiscType.F_AP_MOUNT)){
            return;
        }
        
        // Manipulators will always go in the last slot in its location,
        //  as they get a special slot added for them
        if (m.getType().hasFlag(MiscType.F_BA_MANIPULATOR)){
            int slot = crits[loc].length - 1;
            crits[loc][slot] = new CriticalSlot(m);
        }
        
        int critsToAdd;
        if (m.getType().isSpreadable()){
            critsToAdd = 1;
        } else {
            critsToAdd = m.getType().getCriticals(ba);
        }
        if (critsToAdd == 0){
            return;
        }
        for (int slot = 0; slot < getNumCriticals(loc); slot++){
            if (crits[loc][slot] == null){
                crits[loc][slot] = new CriticalSlot(m);
                critsToAdd--;
                if (critsToAdd <= 0){
                    break;
                }
            }
        }
    }
    
    public CriticalSlot getCritical(int loc, int slot) {
        return crits[loc][slot];
    }
    
    
}