package megameklab.printing;

import megamek.client.ui.swing.calculationReport.CalculationReport;
import megamek.common.*;
import megamek.common.enums.AimingMode;

import java.util.Vector;

/**
 * Dummy entity that indicates that the page should be broken when printing.
 *
 * @author pavelbraginskiy
 */
public class PageBreak extends Entity {
    public PageBreak() {
        setChassis("-PAGEBREAK-");
        setModel("");
    }

    @Override
    public int getUnitType() {
        return 0;
    }

    @Override
    public TechAdvancement getConstructionTechAdvancement() {
        return null;
    }

    @Override
    public int locations() {
        return 0;
    }

    @Override
    public boolean canChangeSecondaryFacing() {
        return false;
    }

    @Override
    public boolean isValidSecondaryFacing(int dir) {
        return false;
    }

    @Override
    public int clipSecondaryFacing(int dir) {
        return 0;
    }

    @Override
    public String getMovementString(EntityMovementType mtype) {
        return "";
    }

    @Override
    public String getMovementAbbr(EntityMovementType mtype) {
        return "";
    }

    @Override
    public String[] getLocationNames() {
        return new String[0];
    }

    @Override
    public String[] getLocationAbbrs() {
        return new String[0];
    }

    @Override
    public HitData rollHitLocation(int table, int side, int aimedLocation, AimingMode aimingMode, int cover) {
        return null;
    }

    @Override
    public HitData rollHitLocation(int table, int side) {
        return null;
    }

    @Override
    public HitData getTransferLocation(HitData hit) {
        return null;
    }

    @Override
    public int getDependentLocation(int loc) {
        return 0;
    }

    @Override
    public boolean hasRearArmor(int loc) {
        return false;
    }

    @Override
    public void autoSetInternal() {

    }

    @Override
    public int getWeaponArc(int wn) {
        return 0;
    }

    @Override
    public boolean isSecondaryArcWeapon(int weaponId) {
        return false;
    }

    @Override
    public int getHeatCapacity(boolean radicalHeatSink) {
        return 0;
    }

    @Override
    public int getHeatCapacityWithWater() {
        return 0;
    }

    @Override
    public int getEngineCritHeat() {
        return 0;
    }

    @Override
    protected int[] getNoOfSlots() {
        return new int[0];
    }

    @Override
    public Vector<Report> victoryReport() {
        return null;
    }

    @Override
    public PilotingRollData addEntityBonuses(PilotingRollData roll) {
        return null;
    }

    @Override
    public int getMaxElevationChange() {
        return 0;
    }

    @Override
    public double getCost(CalculationReport calcReport, boolean ignoreAmmo) {
        return 0;
    }

    @Override
    public boolean doomedInExtremeTemp() {
        return false;
    }

    @Override
    public boolean doomedInVacuum() {
        return false;
    }

    @Override
    public boolean doomedOnGround() {
        return false;
    }

    @Override
    public boolean doomedInAtmosphere() {
        return false;
    }

    @Override
    public boolean doomedInSpace() {
        return false;
    }

    @Override
    public boolean isNuclearHardened() {
        return false;
    }

    @Override
    public int getTotalCommGearTons() {
        return 0;
    }

    @Override
    public int getEngineHits() {
        return 0;
    }

    @Override
    public String getLocationDamage(int loc) {
        return "";
    }

    @Override
    public boolean isCrippled() {
        return false;
    }

    @Override
    public boolean isCrippled(boolean checkCrew) {
        return false;
    }

    @Override
    public boolean isDmgHeavy() {
        return false;
    }

    @Override
    public boolean isDmgModerate() {
        return false;
    }

    @Override
    public boolean isDmgLight() {
        return false;
    }

    @Override
    public long getEntityType() {
        return 0;
    }

    @Override
    protected void initTechAdvancement() {
    }
}