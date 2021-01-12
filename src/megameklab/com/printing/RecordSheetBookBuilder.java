package megameklab.com.printing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import megamek.common.*;
import megameklab.com.util.UnitUtil;

public class RecordSheetBookBuilder {
    private final List<Entity> entities = new ArrayList<>();
    private boolean isSinglePrint;
    private RecordSheetOptions recordSheetOptions;

    public RecordSheetBookBuilder setSinglePrint(boolean singlePrint) {
        isSinglePrint = singlePrint;
        return this;
    }

    public RecordSheetBookBuilder setRecordSheetOptions(RecordSheetOptions options) {
        this.recordSheetOptions = options;
        return this;
    }

    public RecordSheetBookBuilder addEntity(Entity entity) {
        entities.add(entity);
        return this;
    }

    public RecordSheetBookBuilder addEntities(Collection<Entity> entities) {
        entities.addAll(entities);
        return this;
    }

    public RecordSheetBook build() {
        final boolean singlePrint = this.isSinglePrint;
        final RecordSheetOptions options = (this.recordSheetOptions != null)
                ? this.recordSheetOptions : new RecordSheetOptions();

        RecordSheetBook book = new RecordSheetBook();

        List<Infantry> infList = new ArrayList<>();
        List<BattleArmor> baList = new ArrayList<>();
        List<Protomech> protoList = new ArrayList<>();
        Tank tank1 = null;

        int pageCount = 0;
        for (Entity unit : entities) {
            if (unit instanceof Mech) {
                UnitUtil.removeOneShotAmmo(unit);
                UnitUtil.expandUnitMounts((Mech) unit);
                book.addSheet(new PrintMech((Mech) unit, pageCount++, options));
            } else if ((unit instanceof Tank) && isSingleTankRecordSheet(unit)) {
                book.addSheet(new PrintTank((Tank) unit, pageCount++, options));
            } else if (unit instanceof Tank) {
                if (singlePrint || options.showReferenceCharts()) {
                    book.addSheet(new PrintCompositeTankSheet((Tank) unit, null, pageCount++, options));
                } else if (null != tank1) {
                    book.addSheet(new PrintCompositeTankSheet(tank1, (Tank) unit, pageCount++, options));
                    tank1 = null;
                } else {
                    tank1 = (Tank) unit;
                }
            } else if (unit.hasETypeFlag(Entity.ETYPE_AERO)) {
                if (unit instanceof Jumpship) {
                    PrintCapitalShip pcs = new PrintCapitalShip((Jumpship) unit, pageCount, options);
                    pageCount += pcs.getPageCount();
                    book.addSheet(pcs);
                } else if (unit instanceof Dropship) {
                    PrintDropship pds = new PrintDropship((Aero) unit, pageCount, options);
                    pageCount += pds.getPageCount();
                    book.addSheet(pds);
                } else {
                    book.addSheet(new PrintAero((Aero) unit, pageCount++, options));
                }
            } else if (unit instanceof BattleArmor) {
                baList.add((BattleArmor) unit);
                if (singlePrint || baList.size() > 4) {
                    PrintRecordSheet prs = new PrintSmallUnitSheet(baList, pageCount, options);
                    pageCount += prs.getPageCount();
                    book.addSheet(prs);
                    baList = new ArrayList<>();
                }
            } else if (unit instanceof Infantry) {
                infList.add((Infantry) unit);
                if (singlePrint || infList.size() > (options.showReferenceCharts() ? 2 : 3)) {
                    PrintRecordSheet prs = new PrintSmallUnitSheet(infList, pageCount, options);
                    pageCount += prs.getPageCount();
                    book.addSheet(prs);
                    infList = new ArrayList<>();
                }
            } else if (unit instanceof Protomech) {
                protoList.add((Protomech) unit);
                if (singlePrint || protoList.size() > 4) {
                    PrintRecordSheet prs = new PrintSmallUnitSheet(protoList, pageCount, options);
                    pageCount += prs.getPageCount();
                    book.addSheet(prs);
                    protoList = new ArrayList<>();
                }
            } else if (unit != null) {
                book.addUnprintableEntity(unit);
            }
        }

        if (null != tank1) {
            book.addSheet(new PrintCompositeTankSheet(tank1, null, pageCount++));
        }
        if (baList.size() > 0) {
            book.addSheet(new PrintSmallUnitSheet(baList, pageCount++));
        }
        if (infList.size() > 0) {
            book.addSheet(new PrintSmallUnitSheet(infList, pageCount++));
        }
        if (protoList.size() > 0) {
            book.addSheet(new PrintSmallUnitSheet(protoList, pageCount));
        }

        return book;
    }

    private static boolean isSingleTankRecordSheet(Entity unit) {
        return (unit instanceof Tank)
                && ((unit.getMovementMode() == EntityMovementMode.NAVAL)
                        || (unit.getMovementMode() == EntityMovementMode.SUBMARINE) 
                        || (unit.getMovementMode() == EntityMovementMode.HYDROFOIL));
    }
}
