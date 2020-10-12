/*
 * MegaMekLab - Copyright (C) 2020 - The MegaMek Team
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
package megameklab.com.printing;

import megamek.common.Entity;
import megameklab.com.MegaMekLab;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.xml.sax.SAXException;

import javax.swing.*;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Background task for exporting record sheets as PDF
 */
public class ExportTask extends SwingWorker<Void, Integer> {

    private final List<PrintRecordSheet> sheets;
    private final PageFormat pageFormat;
    private final String fileName;

    public ExportTask(List<PrintRecordSheet> sheets, PageFormat pageFormat, String fileName) {
        this.sheets = sheets;
        this.pageFormat = pageFormat;
        this.fileName = fileName;
    }

    @Override
    public Void doInBackground() {
        try {
            PDFMergerUtility merger = new PDFMergerUtility();
            merger.setDestinationFileName(fileName);
            Iterator<PrintRecordSheet> iter = sheets.iterator();
            while (iter.hasNext()) {
                final PrintRecordSheet rs = iter.next();
                for (int i = 0; i < rs.getPageCount(); i++) {
                    merger.addSource(rs.exportPDF(i, pageFormat));
                }
                iter.remove();
            }
            merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
        } catch (TranscoderException | SAXException | IOException | ConfigurationException e) {
            MegaMekLab.getLogger().error(e);
        }
        return null;
    }


    @Override
    protected void done() {
        try {
            get();
        } catch (ExecutionException e) {
            MegaMekLab.getLogger().error(e.getCause());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
