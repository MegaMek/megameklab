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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import megamek.common.Mech;

public class SaveMechToMTF {

    private Mech mek;
    private String fileName;
    private static SaveMechToMTF newInstance;

    public SaveMechToMTF() {

    }

    public SaveMechToMTF(Mech mek, String fileName) {
        this.mek = mek;
        this.fileName = fileName;
        save();
    }

    public static SaveMechToMTF getInstance(Mech mek, String fileName) {
        newInstance = new SaveMechToMTF(mek, fileName);

        return newInstance;

    }

    public void save() {

        Writer out;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./data/mechfiles/" + fileName), "UTF-8"));
            try {
                out.write(mek.getMtf());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
