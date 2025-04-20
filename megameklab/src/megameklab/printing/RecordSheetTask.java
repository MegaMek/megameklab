/*
 * Copyright (C) 2017-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMekLab is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MegaMekLab is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 */
package megameklab.printing;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import megamek.logging.MMLogger;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

/**
 * Renders one or more record sheets as a background task. The task is created using
 * {@link #createPrintTask(List, PrinterJob, PrintRequestAttributeSet, PageFormat)} for output to a printer and
 * {@link #createExportTask(List, PageFormat, String)} for export to a PDF file.
 * <p>
 * Executing the task with {@link #execute(boolean)} allows showing a popup dialog with a progress bar.
 */
public abstract class RecordSheetTask extends SwingWorker<Void, Integer> {
    private static final MMLogger logger = MMLogger.create(RecordSheetTask.class);

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
     * @param sheets                 The sheets to render The contents are removed as each sheet is processed to avoid
     *                               running out of memory on large jobs.
     * @param job                    The print job
     * @param printRequestAttributes A set of attributes to use for printing
     * @param pageFormat             The page format
     *
     * @return A {@link SwingWorker} task
     */
    public static RecordSheetTask createPrintTask(List<PrintRecordSheet> sheets, PrinterJob job,
          PrintRequestAttributeSet printRequestAttributes, PageFormat pageFormat) {
        return new PrintTask(sheets, job, printRequestAttributes, pageFormat);
    }

    /**
     * Creates a task for rendering a list of record sheets as a print job.
     *
     * @param sheets     The sheets to render. The contents are removed as each sheet is processed to avoid running out
     *                   of memory on large jobs.
     * @param pageFormat The page format
     * @param pathName   The path to the PDF output file
     *
     * @return A {@link SwingWorker} task
     */
    public static RecordSheetTask createExportTask(List<PrintRecordSheet> sheets, PageFormat pageFormat,
          String pathName) {
        return new ExportTask(sheets, pageFormat, pathName);
    }

    /**
     * Begins execution with the option to show a progress bar.
     *
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "A problem has occurred", JOptionPane.ERROR_MESSAGE);
            logger.error("", e);
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
        private final PrintRequestAttributeSet printRequestAttributes;

        public PrintTask(List<PrintRecordSheet> sheets, PrinterJob job, PrintRequestAttributeSet printRequestAttributes,
              PageFormat pageFormat) {
            super(sheets);
            this.job = job;
            this.printRequestAttributes = printRequestAttributes;

            RSBook book = new RSBook(sheets, pageFormat);
            sheets.clear();
            job.setPageable(book);
        }

        @Override
        protected String popupLabel() {
            ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");
            return resourceMap.getString("RecordSheetTask.printing");
        }

        @Override
        public Void doInBackground() throws Exception {
            job.print(printRequestAttributes);
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
            ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Dialogs");
            return resourceMap.getString("RecordSheetTask.exporting");
        }

        @Override
        public Void doInBackground() throws Exception {
            PDFMergerUtility merger = new PDFMergerUtility();
            merger.setDestinationFileName(fileName);
            Map<Integer, List<String>> bookmarkNames = new HashMap<>();
            Iterator<PrintRecordSheet> iter = sheets.iterator();
            while (iter.hasNext()) {
                final PrintRecordSheet rs = iter.next();
                bookmarkNames.put(rs.getFirstPage(), rs.getBookmarkNames());
                for (int i = 0; i < rs.getPageCount(); i++) {
                    final InputStream is = rs.exportPDF(i, pageFormat);
                    if (is != null) {
                        merger.addSource(is);
                    }
                }
                iter.remove();
            }
            merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());

            // Load a newly created document, add an outline, then write back to the file.
            File file = new File(fileName);
            try (PDDocument doc = PDDocument.load(file)) {
                PDDocumentOutline outline = new PDDocumentOutline();
                doc.getDocumentCatalog().setDocumentOutline(outline);
                for (Entry<Integer, List<String>> entry : bookmarkNames.entrySet()) {
                    for (String name : entry.getValue()) {
                        PDOutlineItem bookmark = new PDOutlineItem();
                        bookmark.setDestination(doc.getPage(entry.getKey()));
                        bookmark.setTitle(name);
                        outline.addLast(bookmark);
                    }
                }
                outline.openNode();
                doc.save(file);
            }
            return null;
        }
    }

    /**
     * Implementation of Pageable that removes the record sheet objects as they are processed (when the next one is
     * accessed) to conserve memory.
     */
    private static class RSBook implements Pageable {
        private final TreeMap<Integer, PrintRecordSheet> pages = new TreeMap<>();
        private final PageFormat pageFormat;

        RSBook(List<PrintRecordSheet> sheets, PageFormat pageFormat) {
            this.pageFormat = pageFormat;
            for (PrintRecordSheet rs : sheets) {
                for (int p = rs.getFirstPage(); p < rs.getFirstPage() + rs.getPageCount(); p++) {
                    pages.put(p, rs);
                }
            }
        }

        @Override
        public int getNumberOfPages() {
            return pages.values().stream().mapToInt(PrintRecordSheet::getPageCount).sum();
        }

        @Override
        public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
            return pageFormat;
        }

        @Override
        public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
            PrintRecordSheet rs = pages.get(pageIndex);
            while (pages.firstKey() < rs.getFirstPage()) {
                pages.remove(pages.firstKey());
            }
            return rs;
        }
    }
}
