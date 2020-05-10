/**
 *
 */
package megameklab.com.printing;

import megamek.common.Entity;
import megamek.common.Mounted;
import megamek.common.Protomech;

/**
 * Lays out a record sheet block for a single protomech
 */
public class PrintProtomech extends PrintEntity {

    private final Protomech proto;
    private final int unitIndex;

    /**
     * Creates an SVG object for the record sheet
     *
     * @param proto        The protomech to print
     * @param unitIndex    The index of this unit on the page
     * @param startPage    The print job page number for this sheet
     * @param options      Overrides the global options for which elements are printed
     */
    public PrintProtomech(Protomech proto, int unitIndex, int startPage, RecordSheetOptions options) {
        super(startPage, options);
        this.proto = proto;
        this.unitIndex = unitIndex;
    }

    /**
     * Creates an SVG object for the record sheet using the global printing options
     *
     * @param proto        The protomech to print
     * @param unitIndex    The index of this unit on the page
     * @param startPage    The print job page number for this sheet
     */
    public PrintProtomech(Protomech proto, int unitIndex, int startPage) {
        this(proto, startPage, unitIndex, new RecordSheetOptions());
    }

    @Override
    protected String getSVGFileName(int pageNumber) {
        if (proto.isQuad()) {
            return "protomech_quad.svg";
        } else if (proto.isGlider()) {
            return "protomech_glider.svg";
        } else {
            return "protomech_biped.svg";
        }
    }

    @Override
    protected Entity getEntity() {
        return proto;
    }

    @Override
    protected boolean isCenterlineLocation(int loc) {
        return false;
    }

    @Override
    protected String getRecordSheetTitle() {
        // Not used
        return "";
    }

    @Override
    protected void writeTextFields() {
        super.writeTextFields();

        setTextField(PROTOMECH_INDEX, "PROTOMECH " + (unitIndex + 1));
        printTorsoCritChart();
        if (!proto.hasMainGun()) {
            hideElement(MAIN_GUN_ARMOR);
            hideElement(MAIN_GUN_SHADOW);
            hideElement(MAIN_GUN_TEXT);
        }
        if (proto.hasUMU()) {
            setTextField(LBL_JUMP, "Underwater");
        }
    }

    private void printTorsoCritChart() {
        int roll = 1;
        for (int i = 0; i < 6; i++) {
            Mounted weapon = proto.getTorsoWeapon(Protomech.SYSTEM_TORSO_WEAPON_A + i);
            StringBuilder sb = new StringBuilder();
            if (weapon != null) {
                sb.append(roll);
                roll++;
                if (!proto.isQuad()) {
                    sb.append("-").append(roll + 1);
                    roll++;
                }
                sb.append(": ").append(weapon.getName());
                setTextField(TORSO_WEAPON + i, sb.toString());
            } else {
                sb.append(roll);
                if (roll < 6) {
                    sb.append("-6");
                }
                sb.append(": No Effect");
                break;
            }
        }
    }
}
