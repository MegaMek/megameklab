/*
 * MegaMek - Copyright (C) 2000,2001,2002,2003,2004 Ben Mazur (bmazur@sev.org)
 * 
 *  This program is free software; you can redistribute it and/or modify it 
 *  under the terms of the GNU General Public License as published by the Free 
 *  Software Foundation; either version 2 of the License, or (at your option) 
 *  any later version.
 * 
 *  This program is distributed in the hope that it will be useful, but 
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 *  or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 *  for more details.
 */

/*
 * PlanetaryConditionsDialog.java
 */

package megamek.client.ui.AWT;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import megamek.common.PlanetaryConditions;

/**
 * A dialog that allows for customization of planetary conditions
 * 
 * @author Jay Lawson
 * @version
 */
public class PlanetaryConditionsDialog extends Dialog implements ActionListener {
	
	private static final long serialVersionUID = -4426594323169113468L;
	
	private ClientGUI client;
	private PlanetaryConditions conditions;
	private Label labLight = new Label(Messages
			.getString("PlanetaryConditionsDialog.labLight"), Label.CENTER); //$NON-NLS-1$
	private Choice choLight = new Choice();
	private Label labWeather = new Label(Messages
			.getString("PlanetaryConditionsDialog.labWeather"), Label.CENTER); //$NON-NLS-1$
	private Choice choWeather = new Choice();
	private Label labWind = new Label(Messages
			.getString("PlanetaryConditionsDialog.labWind"), Label.CENTER); //$NON-NLS-1$
	private Choice choWind = new Choice();
	private Checkbox cShiftWinds = new Checkbox(Messages
            .getString("PlanetaryConditionsDialog.shiftWinds"));
	    	
	private Panel panButtons = new Panel();	
	private Button butOkay = new Button(Messages.getString("Okay")); //$NON-NLS-1$
	private Button butCancel = new Button(Messages.getString("Cancel")); //$NON-NLS-1$
	
	private Panel panOptions = new Panel();
	
	/**
     * Initialize this dialog.
     * 
     * @param frame - the <code>Frame</code> parent of this dialog.
     * @param options - the <code>GameOptions</code> to be displayed.
     */
    private void init(Frame frame, PlanetaryConditions conditions) {
        this.conditions = (PlanetaryConditions)conditions.clone();
        
        setupConditions();
        setupButtons();
        
        //layout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        c.insets = new Insets(4, 4, 4, 4);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(panOptions, c);
        add(panOptions);

        c.weightx = 1.0;
        c.weighty = 0.0;

        gridbag.setConstraints(panButtons, c);
        add(panButtons);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        pack();
        setSize(getSize().width, Math.max(getSize().height, 400));
        setResizable(true);
        setLocation(frame.getLocation().x + frame.getSize().width / 2
                - getSize().width / 2, frame.getLocation().y
                + frame.getSize().height / 2 - getSize().height / 2);

        
    }
	
	
	/** Creates new PlanetaryConditionsDialog */
	public PlanetaryConditionsDialog(ClientGUI client) {
		super(client.frame, Messages.getString("PlanetaryConditionsDialog.title"), true); //$NON-NLS-1$
		this.client = client;
		this.init(client.frame, client.getClient().game.getPlanetaryConditions());
	}    
	
	private void setupButtons() {
        butOkay.addActionListener(this);
        butCancel.addActionListener(this);

        // layout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panButtons.setLayout(gridbag);

        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.VERTICAL;
        c.ipadx = 20;
        c.ipady = 5;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(butOkay, c);
        panButtons.add(butOkay);

        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(butCancel, c);
        panButtons.add(butCancel);
    }
	
	private void setupConditions() {
		
		refreshConditions();
		
		GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panOptions.setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(labLight, c);
        panOptions.add(labLight);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(choLight, c);
        panOptions.add(choLight);
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(labWeather, c);
        panOptions.add(labWeather);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(choWeather, c);
        panOptions.add(choWeather);
        
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        gridbag.setConstraints(labWind, c);
        panOptions.add(labWind);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(choWind, c);
        panOptions.add(choWind);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        gridbag.setConstraints(cShiftWinds, c);
        panOptions.add(cShiftWinds);

	}
	
	public void update(PlanetaryConditions conditions) {
        this.conditions = (PlanetaryConditions)conditions.clone();
        refreshConditions();
    }
	
	private void refreshConditions() {
		
		choLight.removeAll();
		for(int i = 0; i < PlanetaryConditions.L_SIZE; i++) {
			choLight.add(PlanetaryConditions.getLightDisplayableName(i));
		}
		choLight.select(conditions.getLight());	
		
		choWeather.removeAll();
		for(int i = 0; i < PlanetaryConditions.WE_SIZE; i++) {
			choWeather.add(PlanetaryConditions.getWeatherDisplayableName(i));
		}
		choWeather.select(conditions.getWeather());	
		
		choWind.removeAll();
		for(int i = 0; i < PlanetaryConditions.WI_SIZE; i++) {
			choWind.add(PlanetaryConditions.getWindDisplayableName(i));
		}
		choWind.select(conditions.getWindStrength());	
		
		cShiftWinds.setState(conditions.shiftingWinds());
	}
	
	public void send() {
		
		//make the changes to the planetary conditions
		conditions.setLight(choLight.getSelectedIndex());
		conditions.setWeather(choWeather.getSelectedIndex());
		conditions.setWindStrength(choWind.getSelectedIndex());
		conditions.setShiftingWinds(cShiftWinds.getState());
		
		client.getClient().sendPlanetaryConditions(conditions);
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butOkay) {
            if (client != null) {
                send();
            }
        } else if (e.getSource() == butCancel) {
            refreshConditions();
        }
        this.setVisible(false);
	}
}
    