/*
 * MegaMekLab - Copyright (C) 2011
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
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

package megameklab.com.ui;

import javax.swing.JFrame;

import megameklab.com.util.RefreshListener;

public abstract class MegaMekLabMainUI extends JFrame implements RefreshListener {

    /**
     * 
     */
    private static final long serialVersionUID = 3971760390511127766L;

    public abstract void reloadTabs();

    public abstract void createNewUnit(boolean isNew);

    public abstract void createNewUnit(boolean isNew, boolean isAlsoNew);

    public abstract void refreshAll();

    public abstract void refreshArmor();

    public abstract void refreshBuild();

    public abstract void refreshEquipment();

    public abstract void refreshHeader();

    public abstract void refreshStatus();

    public abstract void refreshStructure();

    public abstract void refreshWeapons();

}