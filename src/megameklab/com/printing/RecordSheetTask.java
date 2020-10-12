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
package megameklab.com.printing;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;

import megamek.common.util.EncodeControl;
import megameklab.com.MegaMekLab;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

/**
 * Renders one or more record sheets as a background task. The task is created using
 * {@link #createPrintTask(List, PrinterJob, PrintRequestAttributeSet, PageFormat)} for output
 * to a printer and {@link #createExportTask(List, PageFormat, String)} for export to a PDF file.
 *
 * Executing the task with {@link #execute(boolean)} allows showing a popup dialog with a progress bar.
 */
public abstract class RecordSheetTask extends SwingWorker<Void, Integer> {
    
    private final ProgressPopup popup;
    protected final List<PrintRecordSheet> sheets;

    private RecordSheetTask(List<PrintRecordSheet> sheets) {
        this.sheets = sheets;
        int pages = 0;
        for (PrintRecordSheet sheet : sheets) {
            sheet.setCallback(this::publish);
            pages += sheet.getPageCount();
        }
        popup = new ProgressPopup(pages, popupLabel());
    }

    /**
     * Creates a task for rendering a list of record sheets as a print job
     *
     * @param sheets     The sheets to render
     * @param job        The print job
     * @param aset       A set of attributes to use for printing
     * @param pageFormat The page format
     * @return           A {@link SwingWorker} task
     */
    public static RecordSheetTask createPrintTask(List<PrintRecordSheet> sheets, PrinterJob job,
                                                  PrintRequestAttributeSet aset, PageFormat pageFormat) {
        return new PrintTask(sheets, job, aset, pageFormat);
    }

    /**
     * Creates a task for rendering a list of record sheets as a print job.
     *
     * @param sheets     The sheets to render. The contents are removed as each sheet is
     *                   processed to avoid running out of memory on large jobs.
     * @param pageFormat The page format
     * @param pathName   The path to the PDF output file
     * @return           A {@link SwingWorker} task
     */
    public static RecordSheetTask createExportTask(List<PrintRecordSheet> sheets, PageFormat pageFormat,
                                                   String pathName) {
        return new ExportTask(sheets, pageFormat, pathName);
    }

    /**
     * Begins execution with the option to show a progress bar.
     * @param showProgressBar Whether to show the progress popup dialog
     */
    public void execute(boolean showProgressBar) {
        popup.setVisible(showProgressBar);
        execute();
    }

    protected abstract String popupLabel();

    @Override
    protected void process(List<Integer> chunks) {
        if (!chunks.isEmpty()) {
            popup.progressBar.setValue(chunks.get(chunks.size() - 1));
        }
    }

    @Override
    protected void done() {
        try {
            get();
        } catch (ExecutionException e) {
            MegaMekLab.getLogger().error(e.getCause());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            popup.setVisible(false);
        }
    }


    private static class ProgressPopup extends JFrame {
        private final JProgressBar progressBar = new JProgressBar();

        ProgressPopup(int maximum, String popupLabel) {
            progressBar.setIndeterminate(false);
            progressBar.setMaximum(maximum);
            progressBar.setStringPainted(true);

            JPanel panel = new JPanel();
            panel.add(new JLabel(popupLabel));
            panel.add(progressBar);
            getContentPane().add(panel);
            pack();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    private static class PrintTask extends RecordSheetTask {
        private final PrinterJob job;
        private final PrintRequestAttributeSet aset;

        public PrintTask(List<PrintRecordSheet> sheets, PrinterJob job, PrintRequestAttributeSet aset,
                         PageFormat pageFormat) {
            super(sheets);
            this.job = job;
            this.aset = aset;

            Book book = new Book();
            for (PrintRecordSheet sheet : sheets) {
                book.append(sheet, pageFormat);
            }
            job.setPageable(book);
        }

        @Override
        protected String popupLabel() {
            ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs",
                    new EncodeControl());
            return resourceMap.getString("RecordSheetTask.printing");
        }

        @Override
        public Void doInBackground() throws Exception {
            job.print(aset);
            return null;
        }
    }

    private static class ExportTask extends RecordSheetTask {
        private final PageFormat pageFormat;
        private final String fileName;

        public ExportTask(List<PrintRecordSheet> sheets, PageFormat pageFormat, String fileName) {
            super(sheets);
            this.pageFormat = pageFormat;
            this.fileName = fileName;
        }

        @Override
        protected String popupLabel() {
            ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs",
                    new EncodeControl());
            return resourceMap.getString("RecordSheetTask.exporting");
        }

        @Override
        public Void doInBackground() throws Exception {
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
            return null;
        }
    }
}
