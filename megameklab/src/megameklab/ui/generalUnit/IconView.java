/*
 * Copyright (c) 2024 - The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MegaMekLab.
 *
 * MegaMek is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MegaMek is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MegaMek. If not, see <http://www.gnu.org/licenses/>.
 */
package megameklab.ui.generalUnit;

import megamek.client.ui.panels.EntityImagePanel;
import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.tileset.MMStaticDirectoryManager;
import megamek.client.ui.swing.util.FluffImageHelper;
import megamek.client.ui.swing.util.PlayerColour;
import megamek.common.Entity;
import megamek.common.icons.Camouflage;
import megamek.common.util.ImageUtil;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import org.apache.logging.log4j.LogManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

/**
 * This view displays the icon that the unit will use in MM and MHQ and allows selecting a different icon
 * from file or from another unit from the cache. If an icon is selected, it will be stored as part of the
 * unit file in base64 encoding when saved. Note that this is for custom units, as canon units will
 * use the mechset to identify and load icons and will not store them in the unit file.
 */
public class IconView extends BuildView {

    private static final Camouflage CAMO_MECHSET = Camouflage.of(PlayerColour.GRAY);
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
            entityImage.updateDisplayedEntity(entity, entity.hasEmbeddedIcon() ? CAMO_EMBEDDED : CAMO_MECHSET);
            if (entity.hasEmbeddedIcon()) {
                entityImage.setToolTipText("This icon will be saved with the unit. The unit will use this icon in MM " +
                        "and MHQ. The original image file, if this was chosen from file, is not needed.");
            } else {
                entityImage.setToolTipText("This icon will not be saved with the unit but the unit will use this icon " +
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
                    LogManager.getLogger().error("", ex);
                }
            }
        }
        refresh();
    }

    private void chooseIconFromUnitCache() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(null);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(null, unitLoadingDialog);

        Entity chosenEntity = viewer.getChosenEntity();
        if (chosenEntity != null) {
            final Image image = MMStaticDirectoryManager.getMechTileset().imageFor(chosenEntity);
            entity.setIcon(ImageUtil.base64TextEncodeImage(image));
        }
        viewer.dispose();
        refresh();
    }

    private void removeIcon() {
        entity.setIcon("");
        refresh();
    }
}