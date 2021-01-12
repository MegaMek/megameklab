package megameklab.com.printing;

import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.xml.sax.SAXException;

import megamek.common.annotations.Nullable;

public class PdfRecordSheetExporter {
    private final MemoryUsageSetting memoryUsageSetting;
    private final Configuration cfg;

    public PdfRecordSheetExporter() {
        this(MemoryUsageSetting.setupTempFileOnly(), null);
    }

    public PdfRecordSheetExporter(MemoryUsageSetting memoryUsageSetting, @Nullable Configuration cfg) {
        this.memoryUsageSetting = Objects.requireNonNull(memoryUsageSetting);
        this.cfg = cfg;
    }

    public void exportToFile(RecordSheetBook book, PageFormat pageFormat, String fileName)
            throws IOException, ConfigurationException, TranscoderException, SAXException {
        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(fileName);

        Map<Integer, List<String>> bookmarkNames = new HashMap<>();
        addSheetsToPdf(merger, book, pageFormat, bookmarkNames);

        // Load newly created document, add an outline, then write back to the file.
        File file = new File(fileName);
        try (PDDocument doc = PDDocument.load(file)) {
            addBookmarksToDocument(bookmarkNames, doc);
            doc.save(file);
        }
    }

    private void addBookmarksToDocument(Map<Integer, List<String>> bookmarkNames, PDDocument doc) {
        PDDocumentOutline outline = new PDDocumentOutline();
        doc.getDocumentCatalog().setDocumentOutline(outline);
        for (Map.Entry<Integer, List<String>> entry : bookmarkNames.entrySet()) {
            for (String name : entry.getValue()) {
                PDOutlineItem bookmark = new PDOutlineItem();
                bookmark.setDestination(doc.getPage(entry.getKey()));
                bookmark.setTitle(name);
                outline.addLast(bookmark);
            }
        }

        outline.openNode();
    }

    private void addSheetsToPdf(PDFMergerUtility merger, RecordSheetBook book, PageFormat pageFormat, 
            Map<Integer, List<String>> bookmarkNames)
            throws TranscoderException, SAXException, IOException, ConfigurationException {
        List<PrintRecordSheet> sheets = book.getSheets();
        for (PrintRecordSheet rs : sheets) {
            bookmarkNames.put(rs.getFirstPage(), rs.getBookmarkNames());
            for (int i = 0; i < rs.getPageCount(); i++) {
                merger.addSource(rs.exportPDF(i, pageFormat, getOrCreateConfiguration(rs)));
            }
        }

        merger.mergeDocuments(memoryUsageSetting);
    }

    private Configuration getOrCreateConfiguration(PrintRecordSheet sheet)
            throws ConfigurationException, SAXException, IOException {
        if (cfg != null) {
            return cfg;
        } else {
            DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
            return cfgBuilder.build(sheet.getClass().getResourceAsStream("fop-config.xml"));
        }
    }
}
