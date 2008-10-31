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

package megameklab.com.util;

import java.io.FileOutputStream;
import java.io.PrintStream;

import megamek.common.Mech;

public class SaveMechToMTF{
    
    private Mech mek;
    private String fileName;
    private static SaveMechToMTF newInstance;
    
    public SaveMechToMTF(){
        
    }
    
    public SaveMechToMTF(Mech mek, String fileName){
        this.mek = mek;
        this.fileName = fileName;
        this.save();
    }
    
    public static SaveMechToMTF getInstance(Mech mek, String fileName){
        newInstance = new SaveMechToMTF(mek,fileName); 
        
        return newInstance;
        
    }
    
    public void save(){
        try{
            FileOutputStream out = new FileOutputStream("./data/mechfiles/"+fileName);
            PrintStream p = new PrintStream(out);
            p.println(mek.getMtf());
            p.close();
            out.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
}