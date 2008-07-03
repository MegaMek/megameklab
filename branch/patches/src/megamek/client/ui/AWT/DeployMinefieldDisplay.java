/*
 * MegaMek - Copyright (C) 2003, 2004 Ben Mazur (bmazur@sev.org)
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

package megamek.client.ui.AWT;

import java.awt.Button;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import megamek.client.Client;
import megamek.client.event.BoardViewEvent;
import megamek.client.event.BoardViewListener;
import megamek.common.Coords;
import megamek.common.IGame;
import megamek.common.Minefield;
import megamek.common.Player;
import megamek.common.event.GameListener;
import megamek.common.event.GameTurnChangeEvent;
import megamek.common.util.Distractable;
import megamek.common.util.DistractableAdapter;

public class DeployMinefieldDisplay extends StatusBarPhaseDisplay implements
        BoardViewListener, ActionListener, DoneButtoned, KeyListener,
        GameListener, Distractable {
    /**
     * 
     */
    private static final long serialVersionUID = 1471981663393324556L;

    // Distraction implementation.
    private DistractableAdapter distracted = new DistractableAdapter();

    // Action command names
    public static final String DEPLOY_MINE_CONV = "deployMineConv"; //$NON-NLS-1$
    public static final String DEPLOY_MINE_COM = "deployMineCom"; //$NON-NLS-1$
    public static final String DEPLOY_MINE_VIBRA = "deployMineVibra"; //$NON-NLS-1$
    public static final String DEPLOY_MINE_ACTIVE = "deployMineActive"; //$NON-NLS-1$
    public static final String DEPLOY_MINE_INFERNO = "deployMineInferno"; //$NON-NLS-1$

    // parent game
    public ClientGUI clientgui;
    private Client client;

    // buttons
    private Panel panButtons;

    private Button butC;
    private Button butM;
    // private Button butSpace;
    // private Button butSpace1;
    // private Button butSpace2;
    // private Button butSpace3;
    private Button butV;
    private Button butA;
    private Button butI;
    private Button butUnload;
    private Button butDone;

    // is the shift key held?
    private boolean deployM = false;
    private boolean deployC = false;
    private boolean deployV = false;
    private boolean deployA = false;
    private boolean deployI = false;

    private Player p;
    private Vector<Minefield> deployedMinefields = new Vector<Minefield>();

    /**
     * Creates and lays out a new deployment phase display for the specified
     * client.
     */
    public DeployMinefieldDisplay(ClientGUI clientgui) {
        this.clientgui = clientgui;
        this.client = clientgui.getClient();
        client.game.addGameListener(this);

        // Listener is added in the ClientGUI#switchPanel
        // clientgui.getBoardView().addBoardViewListener(this);

        setupStatusBar(Messages
                .getString("DeployMinefieldDisplay.waitingForDeployMinefieldPhase")); //$NON-NLS-1$

        p = client.getLocalPlayer();

        butM = new Button(
                Messages
                        .getString(
                                "DeploymentDisplay.buttonMinefield", new Object[] { new Integer(p.getNbrMFConventional()) })); //$NON-NLS-1$
        butM.addActionListener(this);
        butM.setActionCommand(DEPLOY_MINE_CONV);
        butM.setEnabled(false);

        // butSpace = new Button(".");
        // butSpace.setEnabled(false);
        // butSpace1 = new Button(".");
        // butSpace1.setEnabled(false);
        // butSpace2 = new Button(".");
        // butSpace2.setEnabled(false);
        // butSpace3 = new Button(".");
        // butSpace3.setEnabled(false);

        butC = new Button(
                Messages
                        .getString(
                                "DeploymentDisplay.buttonCommand", new Object[] { new Integer(p.getNbrMFCommand()) })); //$NON-NLS-1$
        butC.addActionListener(this);
        butC.setActionCommand(DEPLOY_MINE_COM);
        butC.setEnabled(false);

        butUnload = new Button("."); //$NON-NLS-1$
        butUnload.addActionListener(this);
        butUnload.setEnabled(false);

        butV = new Button(
                Messages
                        .getString(
                                "DeploymentDisplay.buttonVibrabomb", new Object[] { new Integer(p.getNbrMFVibra()) })); //$NON-NLS-1$
        butV.addActionListener(this);
        butV.setActionCommand(DEPLOY_MINE_VIBRA);
        butV.setEnabled(false);
        
        butA = new Button(
                Messages
                        .getString(
                                "DeploymentDisplay.buttonActive", new Object[] { new Integer(p.getNbrMFActive()) })); //$NON-NLS-1$
        butA.addActionListener(this);
        butA.setActionCommand(DEPLOY_MINE_ACTIVE);
        butA.setEnabled(false);

        butI = new Button(
                Messages
                        .getString(
                                "DeploymentDisplay.buttonInferno", new Object[] { new Integer(p.getNbrMFInferno()) })); //$NON-NLS-1$
        butI.addActionListener(this);
        butI.setActionCommand(DEPLOY_MINE_INFERNO);
        butI.setEnabled(false);
        
        butDone = new Button(Messages.getString("DeployMinefieldDisplay.Done")); //$NON-NLS-1$
        butDone.addActionListener(this);
        butDone.setEnabled(false);

        // layout button grid
        panButtons = new Panel();
        panButtons.setLayout(new GridLayout(0, 8));
        panButtons.add(butM);
        panButtons.add(butC);
        panButtons.add(butV);
        panButtons.add(butA);
        panButtons.add(butI);
        // panButtons.add(butSpace);
        // panButtons.add(butSpace1);
        // panButtons.add(butSpace2);
        // panButtons.add(butSpace3);
        // panButtons.add(butDone);

        // layout screen
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(1, 1, 1, 1);
        // c.gridwidth = GridBagConstraints.REMAINDER;
        // addBag(clientgui.bv, gridbag, c);

        // c.weightx = 1.0; c.weighty = 0;
        // c.gridwidth = 1;
        // addBag(client.cb.getComponent(), gridbag, c);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        addBag(panButtons, gridbag, c);

        c.weightx = 1.0;
        c.weighty = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        addBag(panStatus, gridbag, c);

        clientgui.bv.addKeyListener(this);
        addKeyListener(this);
    }

    private void addBag(Component comp, GridBagLayout gridbag,
            GridBagConstraints c) {
        gridbag.setConstraints(comp, c);
        add(comp);
        comp.addKeyListener(this);
    }

    /**
     * Enables relevant buttons and sets up for your turn.
     */
    private void beginMyTurn() {
        p = client.getLocalPlayer();// necessary to make it work after resets.
        setConventionalEnabled(p.getNbrMFConventional());
        setCommandEnabled(p.getNbrMFCommand());
        setVibrabombEnabled(p.getNbrMFVibra());
        setActiveEnabled(p.getNbrMFActive());
        setInfernoEnabled(p.getNbrMFInferno());
        if (!p.hasMinefields()) {
            butDone.setEnabled(true);
        }
    }

    /**
     * Clears out old deployment data and disables relevant buttons.
     */
    private void endMyTurn() {
        // end my turn, then.
        disableButtons();
        clientgui.getBoardView().select(null);
        clientgui.getBoardView().highlight(null);
        clientgui.getBoardView().cursor(null);

    }

    /**
     * Disables all buttons in the interface
     */
    private void disableButtons() {
        setConventionalEnabled(0);
        setCommandEnabled(0);
        setVibrabombEnabled(0);
        setActiveEnabled(0);
        setInfernoEnabled(0);

        butDone.setEnabled(false);
        butUnload.setEnabled(false);
    }

    private void deployMinefield(Coords coords) {
        if (!client.game.getBoard().contains(coords)) {
            return;
        }

        if (client.game.containsMinefield(coords)) {
            Minefield mf = client.game.getMinefields(coords).elementAt(0);
            if (mf.getPlayerId() == client.getLocalPlayer().getId()) {
                butDone.setEnabled(false);
                client.game.removeMinefield(mf);
                deployedMinefields.removeElement(mf);
                switch (mf.getType()) {
                    case (Minefield.TYPE_CONVENTIONAL):
                        deployM = true;
                        deployC = false;
                        deployV = false;
                        deployA = false;
                        deployI = false;
                        p.setNbrMFConventional(p.getNbrMFConventional() + 1);
                        break;
                    case (Minefield.TYPE_COMMAND_DETONATED):
                        deployM = false;
                        deployC = true;
                        deployV = false;
                        deployA = false;
                        deployI = false;
                        p.setNbrMFCommand(p.getNbrMFCommand() + 1);
                        break;
                    case (Minefield.TYPE_VIBRABOMB):
                        deployM = false;
                        deployC = false;
                        deployV = true;
                        deployA = false;
                        deployI = false;
                        p.setNbrMFVibra(p.getNbrMFVibra() + 1);
                        break;
                    case (Minefield.TYPE_ACTIVE):
                        deployM = false;
                        deployC = false;
                        deployV = false;
                        deployA = true;
                        deployI = false;
                        p.setNbrMFActive(p.getNbrMFActive() + 1);
                        break;
                    case (Minefield.TYPE_INFERNO):
                        deployM = false;
                        deployC = false;
                        deployV = false;
                        deployA = false;
                        deployI = true;
                        p.setNbrMFInferno(p.getNbrMFInferno() + 1);
                        break;
                }
            }
        } else {
            Minefield mf;
            if (deployM) {        	
            	MineDensityDialog mfd = new MineDensityDialog(clientgui.frame);
                mfd.setVisible(true);
                //              Hack warning...
                clientgui.bv.stopScrolling();
                mf = Minefield.createMinefield(coords, p.getId(), Minefield.TYPE_CONVENTIONAL, mfd.getDensity());
                p.setNbrMFConventional(p.getNbrMFConventional() - 1);
            } else if (deployC) {
            	MineDensityDialog mfd = new MineDensityDialog(clientgui.frame);
                mfd.setVisible(true);
                //              Hack warning...
                clientgui.bv.stopScrolling();
                mf = Minefield.createMinefield(coords, p.getId(), Minefield.TYPE_COMMAND_DETONATED, mfd.getDensity());
                p.setNbrMFCommand(p.getNbrMFCommand() - 1);
            } else if (deployA) {
            	MineDensityDialog mfd = new MineDensityDialog(clientgui.frame);
                mfd.setVisible(true);
                //              Hack warning...
                clientgui.bv.stopScrolling();
                mf = Minefield.createMinefield(coords, p.getId(), Minefield.TYPE_ACTIVE, mfd.getDensity());
                p.setNbrMFActive(p.getNbrMFActive() - 1);
            } else if (deployI) {
            	MineDensityDialog mfd = new MineDensityDialog(clientgui.frame);
                mfd.setVisible(true);
                //              Hack warning...
                clientgui.bv.stopScrolling();
                mf = Minefield.createMinefield(coords, p.getId(), Minefield.TYPE_INFERNO, mfd.getDensity());
                p.setNbrMFInferno(p.getNbrMFInferno() - 1);
            } else if (deployV) {
            	MineDensityDialog mfd = new MineDensityDialog(clientgui.frame);
                mfd.setVisible(true);
                //              Hack warning...
                clientgui.bv.stopScrolling();
                VibrabombSettingDialog vsd = new VibrabombSettingDialog(
                        clientgui.frame);
                vsd.setVisible(true);

                // Hack warning...
                clientgui.bv.stopScrolling();
                mf = Minefield.createMinefield(coords, p.getId(), Minefield.TYPE_VIBRABOMB, mfd.getDensity(), vsd
                        .getSetting());
                p.setNbrMFVibra(p.getNbrMFVibra() - 1);
            } else {
                return;
            }
            client.game.addMinefield(mf);
            deployedMinefields.addElement(mf);
            clientgui.bv.repaint();
        }

        if (p.getNbrMFConventional() == 0 && p.getNbrMFCommand() == 0
                && p.getNbrMFVibra() == 0 && p.getNbrMFActive() == 0 && p.getNbrMFInferno() == 0) {
            butDone.setEnabled(true);
        }

        setConventionalEnabled(p.getNbrMFConventional());
        setCommandEnabled(p.getNbrMFCommand());
        setVibrabombEnabled(p.getNbrMFVibra());
        setActiveEnabled(p.getNbrMFActive());
        setInfernoEnabled(p.getNbrMFInferno());


        if (p.getNbrMFConventional() == 0) {
            deployM = false;
        }
        if (p.getNbrMFCommand() == 0) {
            deployC = false;
        }
        if (p.getNbrMFVibra() == 0) {
            deployV = false;
        }
        if (p.getNbrMFActive() == 0) {
            deployA = false;
        }
        if (p.getNbrMFInferno() == 0) {
            deployI = false;
        }

    }

    //
    // BoardListener
    //
    public void hexMoused(BoardViewEvent b) {

        // Are we ignoring events?
        if (this.isIgnoringEvents()) {
            return;
        }

        if (b.getType() != BoardViewEvent.BOARD_HEX_DRAGGED) {
            return;
        }

        // ignore buttons other than 1
        if (!client.isMyTurn()
                || (b.getModifiers() & InputEvent.BUTTON1_MASK) == 0) {
            return;
        }

        // check for a deployment
        clientgui.getBoardView().select(b.getCoords());
        deployMinefield(b.getCoords());
    }

    //
    // GameListener
    //
    public void gameTurnChange(GameTurnChangeEvent e) {

        // Are we ignoring events?
        if (this.isIgnoringEvents()) {
            return;
        }

        endMyTurn();

        if (client.isMyTurn()) {
            beginMyTurn();
            setStatusBarText(Messages
                    .getString("DeployMinefieldDisplay.its_your_turn")); //$NON-NLS-1$
        } else {
            setStatusBarText(Messages
                    .getString(
                            "DeployMinefieldDisplay.its_others_turn", new Object[] { e.getPlayer().getName() })); //$NON-NLS-1$
        }
    }

    public void gamePhaseChange(GameTurnChangeEvent e) {

        // Are we ignoring events?
        if (this.isIgnoringEvents()) {
            return;
        }

        if (client.isMyTurn()
                && client.game.getPhase() != IGame.Phase.PHASE_DEPLOY_MINEFIELDS) {
            endMyTurn();
        }
        if (client.game.getPhase() == IGame.Phase.PHASE_DEPLOY_MINEFIELDS) {
            setStatusBarText(Messages
                    .getString("DeployMinefieldDisplay.waitingForDeploymentPhase")); //$NON-NLS-1$
        }
    }

    //
    // ActionListener
    //
    public void actionPerformed(ActionEvent ev) {

        // Are we ignoring events?
        if (this.isIgnoringEvents()) {
            return;
        }

        if (statusBarActionPerformed(ev, client))
            return;

        if (!client.isMyTurn()) {
            // odd...
            return;
        }

        if (ev.getSource().equals(butDone)) {
            endMyTurn();
            client.sendDeployMinefields(deployedMinefields);
            client.sendPlayerInfo();
        }
        if (ev.getActionCommand().equals(DEPLOY_MINE_CONV)) {
            deployM = true;
            deployC = false;
            deployV = false;
            deployA = false;
            deployI = false;
        }
        if (ev.getActionCommand().equals(DEPLOY_MINE_COM)) {
            deployM = false;
            deployC = true;
            deployV = false;
            deployA = false;
            deployI = false;
        }
        if (ev.getActionCommand().equals(DEPLOY_MINE_VIBRA)) {
            deployM = false;
            deployC = false;
            deployV = true;
            deployA = false;
            deployI = false;
        }
        if (ev.getActionCommand().equals(DEPLOY_MINE_ACTIVE)) {
            deployM = false;
            deployC = false;
            deployV = false;
            deployA = true;
            deployI = false;
        }
        if (ev.getActionCommand().equals(DEPLOY_MINE_INFERNO)) {
            deployM = false;
            deployC = false;
            deployV = false;
            deployA = false;
            deployI = true;
        }
    } // End public void actionPerformed(ActionEvent ev)

    //
    // KeyListener
    //
    public void keyPressed(KeyEvent ev) {
    }

    public void keyReleased(KeyEvent ev) {
    }

    public void keyTyped(KeyEvent ev) {
    }

    private void setConventionalEnabled(int nbr) {
        butM
                .setLabel(Messages
                        .getString(
                                "DeploymentDisplay.buttonMinefield", new Object[] { new Integer(nbr) })); //$NON-NLS-1$
        butM.setEnabled(nbr > 0);
        clientgui.getMenuBar().setDeployConventionalEnabled(nbr);
    }

    private void setCommandEnabled(int nbr) {
        butC
                .setLabel(Messages
                        .getString(
                                "DeploymentDisplay.buttonCommand", new Object[] { new Integer(nbr) })); //$NON-NLS-1$
        butC.setEnabled(nbr > 0);
        clientgui.getMenuBar().setDeployCommandEnabled(nbr);
    }

    private void setVibrabombEnabled(int nbr) {
        butV
                .setLabel(Messages
                        .getString(
                                "DeploymentDisplay.buttonVibrabomb", new Object[] { new Integer(nbr) })); //$NON-NLS-1$
        butV.setEnabled(nbr > 0);
        clientgui.getMenuBar().setDeployVibrabombEnabled(nbr);
    }
    
    private void setActiveEnabled(int nbr) {
        butA
                .setLabel(Messages
                        .getString(
                                "DeploymentDisplay.buttonActive", new Object[] { new Integer(nbr) })); //$NON-NLS-1$
        butA.setEnabled(nbr > 0);
        clientgui.getMenuBar().setDeployActiveEnabled(nbr);
    }
    private void setInfernoEnabled(int nbr) {
        butI
                .setLabel(Messages
                        .getString(
                                "DeploymentDisplay.buttonInferno", new Object[] { new Integer(nbr) })); //$NON-NLS-1$
        butI.setEnabled(nbr > 0);
        clientgui.getMenuBar().setDeployInfernoEnabled(nbr);
    }

    /**
     * Determine if the listener is currently distracted.
     * 
     * @return <code>true</code> if the listener is ignoring events.
     */
    public boolean isIgnoringEvents() {
        return this.distracted.isIgnoringEvents();
    }

    /**
     * Specify if the listener should be distracted.
     * 
     * @param distract <code>true</code> if the listener should ignore events
     *            <code>false</code> if the listener should pay attention
     *            again. Events that occured while the listener was distracted
     *            NOT going to be processed.
     */
    public void setIgnoringEvents(boolean distracted) {
        this.distracted.setIgnoringEvents(distracted);
    }

    /**
     * Retrieve the "Done" button of this object.
     * 
     * @return the <code>java.awt.Button</code> that activates this object's
     *         "Done" action.
     */
    public Button getDoneButton() {
        return butDone;
    }

    /**
     * Stop just ignoring events and actually stop listening to them.
     */
    public void removeAllListeners() {
        client.game.removeGameListener(this);
        clientgui.getBoardView().removeBoardViewListener(this);
    }

}
