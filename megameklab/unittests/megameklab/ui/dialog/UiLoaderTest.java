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
 * MechWarrior Copyright Microsoft Corporation. MegaMek was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package megameklab.ui.dialog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.SwingUtilities;

import megamek.common.loaders.MekSummaryCache;
import org.junit.jupiter.api.Test;

class UiLoaderTest {

    @Test
    void retriesLinkedAssetDiscoveryAfterCacheCompletes() throws Exception {
        MekSummaryCache cache = newLoadingCache();
        AtomicInteger retries = new AtomicInteger();

        UiLoader.retryAfterCacheLoad(cache, retries::incrementAndGet);

        assertEquals(0, retries.get());
        List<MekSummaryCache.Listener> listeners = listenersOf(cache);
        assertEquals(1, listeners.size());

        listeners.getFirst().doneLoading();
        SwingUtilities.invokeAndWait(() -> { });

        assertEquals(1, retries.get());
        assertTrue(listenersOf(cache).isEmpty(), "The cache-completion listener must be one-shot");
    }

    @Test
    void doesNotDeferLinkedAssetDiscoveryWhenCacheIsReady() throws Exception {
        MekSummaryCache cache = newLoadingCache();
        setField(cache, "initializing", false);
        setField(cache, "initialized", true);

        UiLoader.retryAfterCacheLoad(cache, () -> {
            throw new AssertionError("Ready cache must not schedule a retry");
        });

        assertTrue(listenersOf(cache).isEmpty());
    }

    private static MekSummaryCache newLoadingCache() throws Exception {
        Constructor<MekSummaryCache> constructor = MekSummaryCache.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MekSummaryCache cache = constructor.newInstance();
        setField(cache, "initializing", true);
        return cache;
    }

    private static void setField(MekSummaryCache cache, String name, Object value) throws Exception {
        Field field = MekSummaryCache.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(cache, value);
    }

    @SuppressWarnings("unchecked")
    private static List<MekSummaryCache.Listener> listenersOf(MekSummaryCache cache) throws Exception {
        Field listeners = MekSummaryCache.class.getDeclaredField("listeners");
        listeners.setAccessible(true);
        return (List<MekSummaryCache.Listener>) listeners.get(cache);
    }
}