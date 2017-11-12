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
 * This class could use a more descriptive name.
 * 
 * @author Neoancient
 *
 */
public class CustomComboBox<T> extends JComboBox<T> {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3775138378658623671L;
    
    private String nullValue = "-error-";
    
    protected CustomComboBox() {
        super();
        setRenderer(new Renderer<T>(Object::toString));
    }
    
    protected CustomComboBox(T[] values) {
        super(values);
        setRenderer(new Renderer<T>(Object::toString));
    }

    public CustomComboBox(Function<T,String> renderer) {
        super();
        setRenderer(new Renderer<T>(renderer));
    }
    
    public CustomComboBox(T[] values, Function<T,String> renderer) {
        super(values);
        setRenderer(new Renderer<T>(renderer));
    }
    
    public void setNullValue(String val) {
        if (null != val) {
            nullValue = val;
        } else {
            val = "null";
        }
    }
    
    class Renderer<U> extends JLabel implements ListCellRenderer<U> {
        
        /**
         * 
         */
        private static final long serialVersionUID = -8452049273819893561L;
        
        private Function<U,String> toString;
        
        protected Renderer(Function<U,String> toString) {
            super();
            this.toString = toString;
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends U> list, U value, int index, boolean isSelected,
                boolean cellHasFocus) {
            try {
                setText(toString.apply(value));
            } catch (Exception ex) {
                setText(nullValue);
            }
            return this;
        }
        
    }
}
