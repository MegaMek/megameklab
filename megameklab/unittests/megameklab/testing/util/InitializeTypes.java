package megameklab.testing.util;

import megamek.common.EquipmentType;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class InitializeTypes implements BeforeAllCallback {
    private static boolean initialized = false;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!initialized) {
            initialized = true;
            EquipmentType.initializeTypes();
        }
    }
}
