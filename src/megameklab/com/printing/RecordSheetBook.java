package megameklab.com.printing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import megamek.common.Entity;

public class RecordSheetBook {
    private final List<PrintRecordSheet> sheets = new ArrayList<>();
    private final List<Entity> unprintable = new ArrayList<>();

    public List<PrintRecordSheet> getSheets() {
        return Collections.unmodifiableList(sheets);
    }

	public void addSheet(PrintRecordSheet recordSheet) {
        sheets.add(Objects.requireNonNull(recordSheet));
    }
    
    public boolean hasUnprintableEntities() {
        return !unprintable.isEmpty();
    }

    public List<Entity> getUnprintableEntities() {
        return Collections.unmodifiableList(unprintable);
    }

    public void addUnprintableEntity(Entity entity) {
        unprintable.add(Objects.requireNonNull(entity));
    }
}
