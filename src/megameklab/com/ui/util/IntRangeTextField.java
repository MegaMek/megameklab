/**
 * 
 */
package megameklab.com.ui.util;

import java.text.NumberFormat;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;

/**
 * A text field for integer values that can specify a minimum and maximum value. Attempting to release
 * focus with an illegal value will set the value to the minimum or maximum as appropriate rather than
 * allowing the focus to be released.
 * 
 * Also provides a ChangeListener for when the focus is lost.
 * 
 * @author Neoancient
 *
 */
public class IntRangeTextField extends JFormattedTextField {

    /**
     * 
     */
    private static final long serialVersionUID = -6477694991883737040L;
    
    private Integer minimum = null;
    private Integer maximum = null;

    public IntRangeTextField(int columns) {
        super();
        setColumns(columns);
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        setFormatter(new NumberFormatter(format));
        setInputVerifier(inputVerifier);
        if (getDocument() instanceof AbstractDocument) {
            ((AbstractDocument)getDocument()).setDocumentFilter(docFilter);
        }
    }
    
    public Integer getMinimum() {
        return minimum;
    }
    
    public void setMinimum(Integer min) {
        minimum = min;
    }
    
    public Integer getMaximum() {
        return maximum;
    }
    
    public void setMaximum(Integer max) {
        maximum = max;
    }

    private InputVerifier inputVerifier = new InputVerifier() {
        @Override
        public boolean verify(JComponent input) {
            try {
                return ((minimum == null || (getIntVal() >= minimum))
                        && (maximum == null || (getIntVal() <= maximum)));
            } catch (NumberFormatException ex) {
                return false;
            }
        }

        @Override
        public boolean shouldYieldFocus(JComponent input) {
            if (!super.shouldYieldFocus(input)) {
                int val = getIntVal();
                if (minimum != null && val < minimum) {
                    setIntVal(minimum);
                } else if (maximum != null && val > maximum) {
                    setIntVal(maximum);
                }
            }
            return true;
        }
    };

    private DocumentFilter docFilter = new DocumentFilter() {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            for (int i = 0; i < string.length(); i++) {
                if (!Character.isDigit(string.charAt(i))) {
                    return;
                }
            }
            super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            // TODO Auto-generated method stub
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i))) {
                    return;
                }
            }
            super.replace(fb, offset, length, text, attrs);
        }

    };

    public int getIntVal() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void setIntVal(int val) {
        setText(String.valueOf(val));
    }

}
