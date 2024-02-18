package megameklab.ui.util;

import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.mockito.*;

class EquipmentTableModelTest {

    private ArrayList<EquipmentType> data = new ArrayList<>();
    private ITechManager techManager;

    @BeforeEach
    void setUp() {
        techManager = null;
    }

    @Test
    void testGetValueAt_null_weapon() {


    }
}