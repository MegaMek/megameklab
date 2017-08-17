/**
 * 
 */
package megameklab.com.ui.util;

import java.awt.Component;
import java.util.function.Function;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Version of JComboBox that simplifies rendering custom data types by taking a toString method in its contructors.
 * 
 * @author Neoancient
 *
 */
public class CustomComboBox<T> extends JComboBox<T> {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3775138378658623671L;

    public CustomComboBox(T[] values) {
        super(values);
    }
    
    public CustomComboBox(Function<T,String> renderer) {
        super();
        setRenderer(new Renderer<T>(renderer));
    }
    
    public CustomComboBox(T[] values, Function<T,String> renderer) {
        this(values);
        setRenderer(new Renderer<T>(renderer));
    }
    
    static class Renderer<U> extends JLabel implements ListCellRenderer<U> {
        
        /**
         * 
         */
        private static final long serialVersionUID = -8452049273819893561L;
        
        private Function<U,String> toString;
        
        private Renderer(Function<U,String> toString) {
            super();
            this.toString = toString;
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends U> list, U value, int index, boolean isSelected,
                boolean cellHasFocus) {
            try {
                setText(toString.apply(value));
            } catch (Exception ex) {
                setText("-error-");
            }
            return this;
        }
        
    }
}
