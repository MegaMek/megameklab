/*
 * MegaMekLab - Copyright (C) 2008 
 * 
 * Original author - jtighe (torren@users.sourceforge.net)
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 */

package megameklab.com.ui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

/**
 * Class for Client's configuration.
 */
public class CConfig {
	
	//VARIABLES
	public static final String CONFIG_FILE = "./data/config.txt";
    public static final String CONFIG_BACKUP_FILE = "./data/config.txt.bak";
	
	private Properties config;//config. player values.
	
	//CONSTRUCTOR
	public CConfig() {
		
		config = setDefaults();		
		//check to see if a config is present. if not, make one.
        if ( !(new File(CONFIG_FILE).exists()) && !(new File(CONFIG_BACKUP_FILE).exists()) )
			createConfig();
		
		//load the saved config.txt file 
		try {
			File configfile = new File(CONFIG_FILE);
			FileInputStream fis = new FileInputStream(configfile);
            File backupfile = new File(CONFIG_BACKUP_FILE);
            if ( backupfile.exists() ){
                FileInputStream backupStream = new FileInputStream(backupfile);
                if ( fis.available() < backupStream.available() ){
                    try {
                        config.load(backupStream);
                        backupStream.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                }else
                    config.load(fis);
            }else
                config.load(fis);
            fis.close();
		} catch (IOException ie){
            try {
                File configfile = new File(CONFIG_BACKUP_FILE);
                FileInputStream fis = new FileInputStream(configfile);
                config.load(fis);
                fis.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
		}
		
		//check for a serverdata.dat
		try {
			File configfile = new File("serverdata.dat");
			FileInputStream fis = new FileInputStream(configfile);
			config.load(fis);
			fis.close();
			configfile.delete();
			this.saveConfig();
		} catch (FileNotFoundException fnfe) {
			//Exception simply means serverdata.dat is not present.
		}catch ( Exception ex){
		    ex.printStackTrace();
		    
		}
	}
	
	//METHODS
	/**
	 * Private method that loads hardcoded defaults. These are loaded
	 * before the players config values, adding any new configs in their
	 * default position and ensuring that no config value is even missing.
	 */
	private Properties setDefaults() {
		Properties defaults = new Properties();
		
		//Window Locations
        defaults.setProperty("WINDOWSTATE", "0");
        defaults.setProperty("WINDOWHEIGHT", "600");
        defaults.setProperty("WINDOWWIDTH", "800");
        defaults.setProperty("WINDOWLEFT", "0");
        defaults.setProperty("WINDOWTOP", "0");
        
        
        return defaults;
	}
	
	//Creates a new config file
	public void createConfig() {
		try {
			FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
			PrintStream ps = new PrintStream(fos);

			ps.close();
			fos.close();
		} catch (Exception ex) {
			System.exit(0);
		}
	}
	
	/**
	 * Get a config value.
	 */
	public String getParam(String param) {
		String tparam = null;
		
		if (param.endsWith(":")) {
			param = param.substring(0, param.lastIndexOf(":"));
		}
		tparam = config.getProperty(param);
		if (tparam == null) {
			tparam = "";
		}
		return tparam;
	}	
	
	/**
	 * Set a config value.
	 */
	public void setParam(String param, String value) {
		config.setProperty(param, value);
	}
	
	/**
	 * See if a paramater is enabled (YES, TRUE or ON).
	 */
	public boolean isParam(String param) {
		String tparam = getParam(param);
		if (tparam.equalsIgnoreCase("YES") || tparam.equalsIgnoreCase("TRUE") || tparam.equalsIgnoreCase("ON"))
			return true;
		return false;
	}
	
	/**
	 * Return the int value of a given config property. Return
	 * a 0 if the property is a non-number. Used mostly by the
	 * misc. mail tab checks.
	 */
	public int getIntParam(String param) {
		int toReturn;
		try {
			toReturn = Integer.parseInt(getParam(param));
		} catch (Exception ex) {
			return 0;
		}
		return toReturn;
	}
		
	/**
	 * Write the config file out to ./data/mwconfig.txt.
	 */
	public void saveConfig() {

        try {
            
            FileOutputStream fos = new FileOutputStream(CONFIG_BACKUP_FILE);
            PrintStream ps = new PrintStream(fos);
            config.store(ps,"Client Config Backup");
            fos.close();
            ps.close();
        }catch (FileNotFoundException fnfe){
            
        }catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        try {
			FileOutputStream fos = new FileOutputStream(CONFIG_FILE);
			PrintStream ps = new PrintStream(fos);
			config.store(ps,"Client Config");
			fos.close();
			ps.close();
		}catch (FileNotFoundException fnfe){
            
        } catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	
}
