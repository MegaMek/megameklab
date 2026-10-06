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
package megameklab.ui.battlefieldSupport;

import java.util.Arrays;
import java.util.function.Supplier;

import megamek.common.annotations.Nullable;
import megamek.common.battleArmor.BattleArmor;
import megamek.common.battlefieldSupport.BFSAssetType;
import megamek.common.battlefieldSupport.BattlefieldSupportAsset;
import megamek.common.battlefieldSupport.BattlefieldSupportAssetData;
import megamek.common.equipment.GunEmplacement;
import megamek.common.units.Entity;
import megamek.common.units.EntityMovementMode;
import megamek.common.units.Infantry;
import megamek.common.units.Tank;

/**
 * The reusable state and logic for a linked Battlefield Support Asset in an eligible base-unit editor. Each eligible
 * {@code MainUI} holds one of these and delegates its {@link BFSAssetSource} implementation to it, so the carrier +
 * enable flag + base-derived seeding/sync are written once rather than copy-pasted across the five editors.
 * <p>
 * The carrier is created lazily, seeded from the base unit, and kept for the session even when the asset is disabled.
 * The base-derived identity fields (chassis, model, role, year, tech base, source, asset type, movement mode) are
 * re-synced from the base at commit time via {@link #applySharedIdentity(Entity, BattlefieldSupportAsset)} so the saved
 * {@code .bfs} stays consistent with the base unit.
 */
public class BFSLinkedAssetSupport implements BFSAssetSource {

    private final Supplier<Entity> baseEntitySupplier;
    private BattlefieldSupportAsset carrier;
    private boolean enabled;
    private String assetFilePath;

    /**
     * @param baseEntitySupplier supplies the current base unit entity (typically {@code mainUI::getEntity})
     */
    public BFSLinkedAssetSupport(Supplier<Entity> baseEntitySupplier) {
        this.baseEntitySupplier = baseEntitySupplier;
    }

    @Override
    public BattlefieldSupportAsset getBattlefieldSupportAssetCarrier() {
        if (carrier == null) {
            carrier = createCarrierFromBase();
        }
        return carrier;
    }

    @Override
    public @Nullable BattlefieldSupportAsset getExistingBattlefieldSupportAssetCarrier() {
        return carrier;
    }

    @Override
    public boolean isBattlefieldSupportAssetEnabled() {
        return enabled;
    }

    @Override
    public void setBattlefieldSupportAssetEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            getBattlefieldSupportAssetCarrier();
        }
    }

    @Override
    public @Nullable BattlefieldSupportAsset getEnabledAsset() {
        return enabled ? carrier : null;
    }

    @Override
    public @Nullable String getAssetFilePath() {
        return assetFilePath;
    }

    @Override
    public void setAssetFilePath(@Nullable String assetFilePath) {
        this.assetFilePath = assetFilePath;
    }

    /**
     * Adopts an asset carrier loaded or restored from a file/memento, enabling the linkage when the asset is present.
     *
     * @param asset the carrier to adopt, or {@code null} to clear it (leaving no linked asset)
     */
    @Override
    public void adoptAsset(@Nullable BattlefieldSupportAsset asset) {
        this.carrier = asset;
        this.enabled = (asset != null);
    }

    private BattlefieldSupportAsset createCarrierFromBase() {
        BattlefieldSupportAsset asset = new BattlefieldSupportAsset();
        Entity base = baseEntitySupplier.get();
        if (base != null) {
            applySharedIdentity(base, asset);
            asset.setMp(Math.max(0, base.getOriginalWalkMP()));
        }
        return asset;
    }

    /**
    * Copies the base-derived, shared identity fields from the base unit onto the asset carrier: chassis, model, role,
    * intro year, tech base, source, asset type, movement mode, the linked-unit ID (the base unit's UUID) and card art
    * (fluff image + sprite icon). These are locked in the linked Asset tab (they belong to the base unit), so they are
     * re-applied at commit time to keep the saved {@code .bfs} consistent. The asset's own stats (MP, TMM, range,
     * damage, skill, cost, specials, card overrides) are not touched.
     *
     * @param base    the base unit
     * @param carrier the asset carrier to update
     */
    public static void applySharedIdentity(Entity base, BattlefieldSupportAsset carrier) {
        carrier.setChassis(base.getChassis());
        carrier.setModel(base.getModel());
        carrier.setUnitRole(base.getRole());
        carrier.setYear(base.getYear());
        carrier.setSource(base.getSource());
        carrier.setAssetTechBase(techBaseForBase(base));
        carrier.setAssetType(assetTypeForBase(base));
        carrier.setMovementMode(base.getMovementMode());
        // Link to the base unit by its unit-file UUID (names are not reliable identifiers).
        carrier.setLinkedUnitId(base.getUnitFileUUID());
        copyArtFromBase(base, carrier);
    }

    /**
     * Copies the base unit's card art (fluff image and sprite icon) onto the carrier, so a linked asset always shows
     * the same art as its base unit, just like the shared chassis/model. Embedded custom art is copied directly; when
     * the base has none, the carrier's embedded art is cleared so both fall back to the shared chassis/model art in the
     * fluff-image and tileset lookups.
     *
     * @param base    the base unit
     * @param carrier the asset carrier to update
     */
    private static void copyArtFromBase(Entity base, BattlefieldSupportAsset carrier) {
        if (base.getFluff().hasEmbeddedFluffImage()) {
            carrier.getFluff().setFluffImage(base.getFluff().getBase64FluffImage().getBase64String());
        } else {
            carrier.getFluff().setFluffImage("");
        }
        if (base.hasEmbeddedIcon()) {
            carrier.setIcon(base.getBase64Icon().getBase64String());
        } else {
            carrier.setIcon("");
        }
    }

    /** @return the {@link BFSAssetType} corresponding to the given base unit's class */
    public static BFSAssetType assetTypeForBase(Entity base) {
        // Order matters: GunEmplacement extends Tank, BattleArmor extends Infantry.
        if (base instanceof GunEmplacement) {
            return BFSAssetType.EMPLACEMENT;
        } else if (base instanceof BattleArmor) {
            return BFSAssetType.BATTLE_ARMOR;
        } else if (base instanceof Tank) {
            return BFSAssetType.VEHICLE;
        } else if (base instanceof Infantry) {
            return BFSAssetType.CONV_INFANTRY;
        }
        return BFSAssetType.VEHICLE;
    }

    private static String techBaseForBase(Entity base) {
        if (base.isMixedTech()) {
            return base.isClan() ? BattlefieldSupportAssetData.TECH_BASE_MIXED_CLAN
                  : BattlefieldSupportAssetData.TECH_BASE_MIXED_IS;
        }
        return base.isClan() ? BattlefieldSupportAssetData.TECH_BASE_CLAN : BattlefieldSupportAssetData.TECH_BASE_IS;
    }

    // region Motive tables (single source of truth, shared with BFSStructureTab)

    /** @return the movement modes an asset of the given type may have (the editor's motive dropdown options). */
    public static EntityMovementMode[] allowedMotives(BFSAssetType type) {
        return switch (type) {
            case VEHICLE -> new EntityMovementMode[] {
                  EntityMovementMode.TRACKED, EntityMovementMode.WHEELED, EntityMovementMode.HOVER,
                  EntityMovementMode.VTOL, EntityMovementMode.WIGE };
            case CONV_INFANTRY -> new EntityMovementMode[] {
                  EntityMovementMode.INF_LEG, EntityMovementMode.INF_JUMP, EntityMovementMode.INF_MOTORIZED,
                  EntityMovementMode.TRACKED, EntityMovementMode.WHEELED, EntityMovementMode.HOVER,
                  EntityMovementMode.VTOL, EntityMovementMode.WIGE, EntityMovementMode.NONE };
            case BATTLE_ARMOR -> new EntityMovementMode[] {
                  EntityMovementMode.INF_LEG, EntityMovementMode.INF_JUMP, EntityMovementMode.VTOL };
            // EMPLACEMENT is None only.
            default -> new EntityMovementMode[] { EntityMovementMode.NONE };
        };
    }

    /** @return the default motive type for a newly-typed asset. */
    public static EntityMovementMode defaultMotive(BFSAssetType type) {
        return switch (type) {
            case VEHICLE -> EntityMovementMode.TRACKED;
            case CONV_INFANTRY, BATTLE_ARMOR -> EntityMovementMode.INF_LEG;
            default -> EntityMovementMode.NONE;
        };
    }

    /**
     * @return {@code true} if the given base unit's movement mode is a legal asset motive for its type. A base whose
     *       motive is illegal for an asset (for example a Naval or Submarine vehicle) cannot have a linked asset.
     */
    public static boolean isMotiveEligible(Entity base) {
        return Arrays.asList(allowedMotives(assetTypeForBase(base))).contains(base.getMovementMode());
    }

    // endregion
}
