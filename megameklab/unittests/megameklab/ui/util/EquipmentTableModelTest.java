package megameklab.ui.util;

import megamek.common.EquipmentType;
import megamek.common.ITechManager;
import megameklab.ui.generalUnit.BasicInfoView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;

class EquipmentTableModelTest {

    private ArrayList<EquipmentType> data = new ArrayList<>();
    private BasicInfoView techManager;

    @BeforeEach
    void setUp() {
        techManager = mock(BasicInfoView.class);
    }

    @Test
    void testGetValueAt_null_weapon() {

    }
}