package megameklab.com.printing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import megamek.common.Entity;

/**
 * Represents a book of {@link PrintRecordSheet} instances.
 */
public class RecordSheetBook {
    private List<PrintRecordSheet> sheets = new ArrayList<>();
    private List<Entity> unprintable = new ArrayList<>();
    private int pageCount;

    /**
     * Takes the {@link PrintRecordSheet} entries out of the book
     * and resets the sheets and unprintable entities. Callers
     * should save the list of unprintable entities prior to
     * calling {@code takeSheets}.
     * 
     * @return The list of {@link PrintRecordSheet} entries from this
     *         book. The caller now owns this reference.
     */
    public List<PrintRecordSheet> takeSheets() {
        List<PrintRecordSheet> taken = sheets;

        sheets = new ArrayList<>();
        unprintable = new ArrayList<>();
        pageCount = 0;

        return taken;
    }

    /**
     * Executes an action on each {@link PrintRecordSheet} in the book.
     * 
     * @param consumer The action to execute on each sheet in the book.
     */
    public void forEachSheet(Consumer<PrintRecordSheet> consumer) {
        for (PrintRecordSheet sheet : sheets) {
            consumer.accept(sheet);
        }
    }

    /**
     * Gets the total page count.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * Adds a record sheet to the book.
     * @param recordSheet The {@link PrintRecordSheet} to add to the book.
     */
	public void addSheet(PrintRecordSheet recordSheet) {
        sheets.add(Objects.requireNonNull(recordSheet));
        pageCount += recordSheet.getPageCount();
    }
    
    /**
     * Gets a value indicating whether or not the book
     * included at least one unprintable entity.
     */
    public boolean hasUnprintableEntities() {
        return !unprintable.isEmpty();
    }

    /**
     * Gets a list of the unprintable entities.
     * @return A list of unprintable entities.
     */
    public List<Entity> getUnprintableEntities() {
        return Collections.unmodifiableList(unprintable);
    }

    /**
     * Adds an unprintable entity to the book.
     * @param entity An {@link Entity} which could not be
     *               converted into a {@link PrintRecordSheet}.
     */
    public void addUnprintableEntity(Entity entity) {
        unprintable.add(Objects.requireNonNull(entity));
    }
}
