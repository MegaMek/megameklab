/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui.generalUnit;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.dialogs.iconChooser.EntityImagePanel;
import megamek.client.ui.tileset.MMStaticDirectoryManager;
import megamek.client.ui.util.FluffImageHelper;
import megamek.client.ui.util.PlayerColour;
import megamek.common.units.Entity;
import megamek.common.icons.Camouflage;
import megamek.common.util.ImageUtil;
import megamek.logging.MMLogger;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;

/**
 * This view displays the icon that the unit will use in MM and MHQ and allows selecting a different icon from file or
 * from another unit from the cache. If an icon is selected, it will be stored as part of the unit file in base64
 * encoding when saved. Note that this is for custom units, as canon units will use the mekset to identify and load
 * icons and will not store them in the unit file.
 */
public class IconView extends BuildView {
    private static final MMLogger logger = MMLogger.create(IconView.class);

    private static final Camouflage CAMO_MEKSET = Camouflage.of(PlayerColour.GRAY);
    private static final Camouflage CAMO_EMBEDDED = Camouflage.of(PlayerColour.GOLD);

    private final EntityImagePanel entityImage = new EntityImagePanel(null, null);
    private final JButton fileIconButton = new JButton("Choose file");
    private final JButton cacheIconButton = new JButton("Import from cache");
    private final JButton removeIconButton = new JButton("Remove");

    private Entity entity;

    public IconView() {
        initUI();
    }

    private void initUI() {
        setBorder(BorderFactory.createTitledBorder("Icon"));

        fileIconButton.addActionListener(e -> chooseIconFromFile());
        fileIconButton.setToolTipText("Choose an image to use as the icon for the present unit. The chosen image will" +
              " be saved in the unit file and the icon is shown with a gold camo in MML.");
        cacheIconButton.addActionListener(e -> chooseIconFromUnitCache());
        cacheIconButton.setToolTipText("Choose another unit from the unit cache to use that unit's icon for the " +
              "present unit. The chosen image will be saved in the unit file and the icon is shown with a gold camo in MML.");
        removeIconButton.addActionListener(e -> removeIcon());
        removeIconButton.setToolTipText("Removes the current icon from this unit, reverting to the standard " +
              "icon shown for its type and name; this icon is shown in gray.");

        var iconButtonPanel = new JPanel(new GridLayout(3, 1, 0, 4));
        iconButtonPanel.add(fileIconButton);
        iconButtonPanel.add(cacheIconButton);
        iconButtonPanel.add(removeIconButton);

        var outerIconButtonPanel = new JPanel();
        outerIconButtonPanel.add(iconButtonPanel);

        JPanel iconPanel = new JPanel();
        iconPanel.add(entityImage);

        setLayout(new GridLayout(1, 2));
        add(outerIconButtonPanel);
        add(iconPanel);
    }

    public void setFromEntity(Entity en) {
        entity = en;
        refresh();
    }

    public void refresh() {
        if (entity != null) {
            entityImage.updateDisplayedEntity(entity, entity.hasEmbeddedIcon() ? CAMO_EMBEDDED : CAMO_MEKSET);
            if (entity.hasEmbeddedIcon()) {
                entityImage.setToolTipText("This icon will be saved with the unit. The unit will use this icon in MM " +
                      "and MHQ. The original image file, if this was chosen from file, is not needed.");
            } else {
                entityImage
                      .setToolTipText("This icon will not be saved with the unit but the unit will use this icon " +
                            "automatically in MM or MHQ.");
            }
        }
        fileIconButton.setEnabled(entity != null);
        cacheIconButton.setEnabled(entity != null);
        removeIconButton.setEnabled((entity != null) && entity.hasEmbeddedIcon());
    }

    private void chooseIconFromFile() {
        var imageChooser = new MMLFileChooser();
        int result = imageChooser.showOpenDialog(this);
        if ((result == JFileChooser.APPROVE_OPTION) && (imageChooser.getSelectedFile() != null)) {
            File imageFile = imageChooser.getSelectedFile();
            if (imageFile.isFile()) {
                String lowerCaseName = imageFile.toString();
                if (Arrays.stream(FluffImageHelper.EXTENSIONS_FLUFF_IMAGE_FORMATS).noneMatch(lowerCaseName::endsWith)) {
                    PopupMessages.showImproperFileType(this);
                    return;
                }
                try {
                    BufferedImage image = ImageIO.read(imageFile);
                    if (image == null) {
                        PopupMessages.showFileReadError(this, imageFile.toString());
                        return;
                    }
                    if ((image.getWidth() != 84) || (image.getHeight() != 72)) {
                        PopupMessages.showWrongIconSize(this);
                        return;
                    }
                    entity.setIcon(ImageUtil.base64TextEncodeImage(image));
                } catch (Exception ex) {
                    PopupMessages.showFileReadError(this, imageFile.toString(), ex.getMessage());
                    logger.error("", ex);
                }
            }
        }
        refresh();
    }

    private void chooseIconFromUnitCache() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(null);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(null, unitLoadingDialog, false);
        try {
            Entity chosenEntity = viewer.getChosenEntity();
            if (chosenEntity != null) {
                final Image image = MMStaticDirectoryManager.getMekTileset().imageFor(chosenEntity);
                entity.setIcon(ImageUtil.base64TextEncodeImage(image));
            }
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
            refresh();
        }
    }

    private void removeIcon() {
        entity.setIcon("");
        refresh();
    }
}
