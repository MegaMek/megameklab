package megameklab.com.printing;

import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.fop.configuration.Configuration;
import org.apache.fop.configuration.ConfigurationException;
import org.apache.fop.configuration.DefaultConfigurationBuilder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.xml.sax.SAXException;

import megamek.common.annotations.Nullable;

/**
 * Exports {@link RecordSheetBook} instances to a PDF.
 */
public class PdfRecordSheetExporter {
    private final MemoryUsageSetting memoryUsageSetting;
    private final Configuration cfg;

    /**
     * Creates a new exporter using temp files for memory management.
     */
    public PdfRecordSheetExporter() {
        this(MemoryUsageSetting.setupTempFileOnly(), null);
    }

    /**
     * Creates a new exporter using the supplied memory usage settings and optional configuration.
     * @param memoryUsageSetting The {@link MemoryUsageSetting} to use when exporting the PDF.
     * @param cfg The {@link Configuration} to use when exporting the PDF, or {@code null} if the
     *            default configuration should be used.
     */
    public PdfRecordSheetExporter(MemoryUsageSetting memoryUsageSetting, @Nullable Configuration cfg) {
        this.memoryUsageSetting = Objects.requireNonNull(memoryUsageSetting);
        this.cfg = cfg;
    }

    /**
     * Exports a {@link RecordSheetBook} to a PDF.
     * @param book The {@link RecordSheetBook} to export.
     * @param pageFormat The {@link PageFormat} to use with the resulting PDF.
     * @param fileName The file name to save the resulting PDF.
     * @throws IOException
     * @throws ConfigurationException
     * @throws TranscoderException
     * @throws SAXException
     */
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
        Iterator<PrintRecordSheet> sheets = book.takeSheets().iterator();
        Configuration configuration = getOrCreateConfiguration();
        while (sheets.hasNext()) {
            PrintRecordSheet rs = sheets.next();
            
            // Ensure we do not hold onto the PrintRecordSheet instance any longer than necessary
            sheets.remove();

            bookmarkNames.put(rs.getFirstPage(), rs.getBookmarkNames());
            for (int i = 0; i < rs.getPageCount(); i++) {
                merger.addSource(rs.exportPDF(i, pageFormat, configuration));
            }
        }

        merger.mergeDocuments(memoryUsageSetting);
    }

    private Configuration getOrCreateConfiguration()
            throws ConfigurationException, SAXException, IOException {
        if (cfg != null) {
            return cfg;
        } else {
            DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
            return cfgBuilder.build(getClass().getResourceAsStream("fop-config.xml"));
        }
    }
}
