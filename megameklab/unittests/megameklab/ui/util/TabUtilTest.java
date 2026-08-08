/*
 * Copyright (C) 2026 The MegaMek Team. All Rights Reserved.
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
 *
 * MechWarrior Copyright Microsoft Corporation. MegaMekLab was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import megameklab.ui.util.TabUtil.TabStateRecord;
import org.junit.jupiter.api.Test;

/** Verifies the tab-state db format (paired linked-asset records + legacy backward compatibility). */
class TabUtilTest {

    @Test
    void serializeParseRoundTripPreservesLinkedAndUnlinkedEditors() {
        List<TabStateRecord> records = List.of(
              new TabStateRecord("/tmp/a.blk.tmp", "units/A.blk", "/tmp/a.bfs.tmp", "units/A.bfs", false,
                  "units/B.blk"),
              new TabStateRecord("/tmp/b.mtf.tmp", "units/B.mtf", "", "", false, ""));

        List<TabStateRecord> parsed = TabUtil.parseTabStateDb(TabUtil.serializeTabStateDb(records));

        assertEquals(records, parsed);
    }

    @Test
    void parsePreservesTheLinkedAssetFields() {
        String db = TabUtil.serializeTabStateDb(List.of(
              new TabStateRecord("/t/base.blk.tmp", "Base.blk", "/t/asset.bfs.tmp", "Base.bfs", true,
                  "New Base.blk")));

        List<TabStateRecord> parsed = TabUtil.parseTabStateDb(db);

        assertEquals(1, parsed.size());
        assertEquals("/t/asset.bfs.tmp", parsed.get(0).assetTmpPath());
        assertEquals("Base.bfs", parsed.get(0).assetFilePath());
        assertEquals(true, parsed.get(0).assetEnabled());
        assertEquals("New Base.blk", parsed.get(0).pendingPairedSavePath());
    }

    @Test
    void parseReadsLegacyTwoFieldFormatWithoutAssets() {
        // Legacy format: no version marker, two \0-separated fields per record.
        String legacy = "/t/x.blk.tmp\0Unit X.blk\0/t/y.mtf.tmp\0Unit Y.mtf\0";

        List<TabStateRecord> parsed = TabUtil.parseTabStateDb(legacy);

        assertEquals(2, parsed.size());
        assertEquals(new TabStateRecord("/t/x.blk.tmp", "Unit X.blk", "", "", false, ""), parsed.get(0));
        assertEquals(new TabStateRecord("/t/y.mtf.tmp", "Unit Y.mtf", "", "", false, ""), parsed.get(1));
    }

    @Test
    void parseNormalizesBlankBaseFileName() {
        String db = TabUtil.serializeTabStateDb(List.of(
              new TabStateRecord("/t/x.blk.tmp", " ", "", "", false, "")));

        assertEquals("", TabUtil.parseTabStateDb(db).get(0).fileName());
    }

    @Test
    void parseV2TreatsPresentCarrierAsEnabled() {
        String v2 = "v2\0/t/base.blk.tmp\0Base.blk\0/t/asset.bfs.tmp\0Base.bfs\0";

        TabStateRecord parsed = TabUtil.parseTabStateDb(v2).get(0);

        assertEquals(true, parsed.assetEnabled());
        assertEquals("Base.bfs", parsed.assetFilePath());
        assertEquals("", parsed.pendingPairedSavePath());
    }

    @Test
    void parseV3DefaultsPendingPairedSavePath() {
        String v3 = "v3\0/t/base.blk.tmp\0Base.blk\0/t/asset.bfs.tmp\0Base.bfs\0true\0";

        TabStateRecord parsed = TabUtil.parseTabStateDb(v3).get(0);

        assertEquals(true, parsed.assetEnabled());
        assertEquals("", parsed.pendingPairedSavePath());
    }
}
