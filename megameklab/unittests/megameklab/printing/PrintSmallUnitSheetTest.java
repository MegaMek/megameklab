package megameklab.printing;

import megamek.common.*;
import megameklab.testing.util.InitializeTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value = InitializeTypes.class)
class PrintSmallUnitSheetTest {
    private RecordSheetOptions noTables, yesTables;

    @BeforeEach
    void setUp() {
        noTables = new RecordSheetOptions();
        noTables.setReferenceCharts(false);
        yesTables = new RecordSheetOptions();
        yesTables.setReferenceCharts(true);
    }

    @Test
    void fillsSheetProtomech() {
        var entities = new ArrayList<>(List.of(new Protomech(), new Protomech(), new Protomech(), new Protomech()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new Protomech());
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetBA() {
        var entities = new ArrayList<>(List.of(new BattleArmor(), new BattleArmor(), new BattleArmor(), new BattleArmor()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new BattleArmor());
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetCI() {
        var entities = new ArrayList<>(List.of(new Infantry(), new Infantry()));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new Infantry());
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
        entities.add(new Infantry());
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertTrue(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetEmpty() {
        var entities = List.<Entity>of();
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, noTables));
        assertFalse(PrintSmallUnitSheet.fillsSheet(entities, yesTables));
    }

    @Test
    void fillsSheetIllegal() {
        var heterogeneousEntities = List.of(new Infantry(), new BattleArmor());
        assertThrows(IllegalArgumentException.class, () -> PrintSmallUnitSheet.fillsSheet(heterogeneousEntities, noTables));

        var unsupportedEntities = List.of(new SupportTank());
        assertThrows(IllegalArgumentException.class, () -> PrintSmallUnitSheet.fillsSheet(unsupportedEntities, noTables));
    }

    /**
     * Verify that we can process the image (basically, create the record sheet output) for an Aero with a null Engine object
     * without throwing an NPE.
     */
    @Test
    void testAeroWithoutEngineDoesNotThrowNPE() {
        // Required setting objects
        PageFormat pf = new PageFormat();
        RecordSheetOptions rso = new RecordSheetOptions();

        // Set up DS entity with required attributes
        Dropship testDS = new Dropship();
        testDS.setChassis("Test Dropship");
        testDS.setModel("TDS-999");

        // Create print object
        PrintAero pa = new PrintDropship(testDS, 1, rso);

        // Test A) Document is created, B) Engine is null, C) processImage() doesn't throw.
        assertTrue(pa.createDocument(1, pf, false));
        assertNull(testDS.getEngine());
        pa.processImage(1, pf);
    }
}
