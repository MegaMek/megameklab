/**
 * 
 */
package megameklab.com.ui.util;

import java.util.function.Function;

import megamek.common.ITechnology;

/**
 * ComboBox for equipment that implement ITechnology.
 * Has a boolean flag that can toggle prefixing "Clan" or "IS" to the equipment name.
 * 
 * @author Neoancient
 *
 */
public class TechComboBox<T extends ITechnology> extends CustomComboBox<T> {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4155090672011004423L;
    
    private boolean showTechBase = false;

    public TechComboBox(Function<T,String> toString) {
        super();
        setRenderer(new Renderer<T>(t -> getTechName(toString.apply(t), t.getTechBase())));
    }
    
    public TechComboBox(T[] values, Function<T,String> toString) {
        super(values);
        setRenderer(new Renderer<T>(t -> getTechName(toString.apply(t), t.getTechBase())));
    }
    
    public void showTechBase(boolean show) {
        showTechBase = show;
    }
    
    private String getTechName(String name, int techBase) {
        StringBuilder sb = new StringBuilder();
        if (showTechBase) {
            if (techBase == ITechnology.TECH_BASE_CLAN) {
                sb.append("Clan ");
            } else if (techBase == ITechnology.TECH_BASE_IS) {
                sb.append("IS ");
            }
        }
        sb.append(name);
        return sb.toString();
    }

}
