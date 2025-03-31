/*
 * MegaMekLab - Copyright (C) 2018, 2024 - The MegaMek Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package megameklab.ui.generalUnit;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.util.FluffImageHelper;
import megamek.common.Entity;
import megamek.common.EntityFluff;
import megamek.common.util.ImageUtil;
import megamek.logging.MMLogger;
import megameklab.ui.EntitySource;
import megameklab.ui.PopupMessages;
import megameklab.ui.dialog.MMLFileChooser;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Panel for editing unit fluff
 *
 * @author Neoancient
 */
public class FluffTab extends ITab implements FocusListener {
    private static final MMLogger logger = MMLogger.create(FluffTab.class);

    private final JTextArea txtCapabilities = new JTextArea(5, 35);
    private final JTextArea txtOverview = new JTextArea(5, 35);
    private final JTextArea txtDeployment = new JTextArea(5, 35);
    private final JTextArea txtHistory = new JTextArea(5, 35);
    private final JTextArea txtNotes = new JTextArea(5, 35);

    private final JTextField txtManufacturer = new JTextField(12);
    private final JTextField txtPrimaryFactory = new JTextField(12);
    private final JTextField txtUse = new JTextField(12);
    private final JTextField txtLength = new JTextField(8);
    private final JTextField txtWidth = new JTextField(8);
    private final JTextField txtHeight = new JTextField(8);

    private final JButton btnSetFluffImage = new JButton("Set Fluff Image from File");
    private final JButton btnImportFluffImage = new JButton("Import Fluff Image from Unit");
    private final JButton btnRemoveFluff = new JButton("Remove Fluff Image");

    private final JLabel lblFluffImage = new JLabel();
    private final JScrollPane imgScrollPane = new JScrollPane(lblFluffImage);
    private static final String TAG_MANUFACTURER = "manufacturer";
    private static final String TAG_MODEL = "model";
    private static final int MAX_CONTENT_WIDTH = 1400;
    private static final int MAX_FLUFF_IMG_WIDTH = 300;
    private static final int MAX_FLUFF_IMG_HEIGHT = 300;

    private RefreshListener refresh;

    public FluffTab(EntitySource esource) {
        super(esource);
        initUi();
    }

    private EntityFluff getFluff() {
        return eSource.getEntity().getFluff();
    }

    public void setRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    private void initUi() {
        ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Tabs");
        Insets elementInsets = new Insets(2, 5, 2, 5);
        Insets panelInsets = new Insets(5, 5, 5, 5);

        final boolean hasRightPanelEntries = !getEntity().hasETypeFlag(Entity.ETYPE_INFANTRY)
                || getEntity().hasETypeFlag(Entity.ETYPE_BATTLEARMOR);

        setLayout(new GridBagLayout());
        GridBagConstraints mainGbc = new GridBagConstraints();

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setMaximumSize(new Dimension(MAX_CONTENT_WIDTH, Integer.MAX_VALUE));

        // --- Left Panel ---
        JPanel panLeft = new JPanel(new GridBagLayout());
        panLeft.setBorder(new EmptyBorder(panelInsets)); // Add padding around the panel
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        gbcLeft.anchor = GridBagConstraints.NORTHWEST;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL; // Buttons only fill horizontally
        gbcLeft.weightx = 1.0; // Buttons take horizontal space
        gbcLeft.insets = elementInsets;

        btnSetFluffImage.addActionListener(evt -> chooseFluffImage());
        panLeft.add(btnSetFluffImage, gbcLeft);
        gbcLeft.gridy++;

        btnImportFluffImage.addActionListener(evt -> importFluffImage());
        panLeft.add(btnImportFluffImage, gbcLeft);
        gbcLeft.gridy++;

        btnRemoveFluff.addActionListener(evt -> removeFluffImage());
        panLeft.add(btnRemoveFluff, gbcLeft);
        gbcLeft.gridy++;

        // Add some space between buttons and text areas
        gbcLeft.insets = new Insets(10, 5, 2, 5);

        // Helper to add Label + TextArea pairs
        java.util.function.BiConsumer<String, JTextArea> addLabeledTextArea = (labelText, textArea) -> {
            gbcLeft.gridy++;
            gbcLeft.weighty = 0.0; // Label doesn't stretch vertically
            gbcLeft.fill = GridBagConstraints.HORIZONTAL;
            panLeft.add(new JLabel(labelText), gbcLeft);

            gbcLeft.gridy++;
            gbcLeft.fill = GridBagConstraints.BOTH;
            gbcLeft.weighty = 0.2; // Allow text areas some vertical stretchability, evenly distributed
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            // Use a scroll pane for potentially long text
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            panLeft.add(scrollPane, gbcLeft);
            textArea.addFocusListener(this);
            gbcLeft.insets = elementInsets; // Reset insets for next element
        };

        addLabeledTextArea.accept(resourceMap.getString("FluffTab.txtCapabilities"), txtCapabilities);
        txtCapabilities.setText(getFluff().getCapabilities());

        addLabeledTextArea.accept(resourceMap.getString("FluffTab.txtOverview"), txtOverview);
        txtOverview.setText(getFluff().getOverview());

        addLabeledTextArea.accept(resourceMap.getString("FluffTab.txtDeployment"), txtDeployment);
        txtDeployment.setText(getFluff().getDeployment());

        addLabeledTextArea.accept(resourceMap.getString("FluffTab.txtHistory"), txtHistory);
        txtHistory.setText(getFluff().getHistory());

        addLabeledTextArea.accept(resourceMap.getString("FluffTab.txtNotes"), txtNotes);
        txtNotes.setText(getFluff().getNotes());

        // Add a filler component at the bottom of panLeft to push content up
        gbcLeft.gridy++;
        gbcLeft.weighty = 1.0; // Takes up remaining vertical space
        gbcLeft.fill = GridBagConstraints.VERTICAL;
        panLeft.add(new JPanel(), gbcLeft); // Invisible spacer

        // --- Right Panel ---
        JPanel panRight = new JPanel(new GridBagLayout());
        panRight.setBorder(new EmptyBorder(panelInsets)); // Add padding around the panel
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.anchor = GridBagConstraints.WEST; // Align labels to the left
        gbcRight.insets = elementInsets;

        // --- Add Fluff Image Display ---
        lblFluffImage.setHorizontalAlignment(JLabel.CENTER);
        lblFluffImage.setVerticalAlignment(JLabel.CENTER);
        // Set a preferred size based on max dimensions to influence layout
        imgScrollPane.setPreferredSize(new Dimension(MAX_FLUFF_IMG_WIDTH + 20, MAX_FLUFF_IMG_HEIGHT + 20));
        imgScrollPane.setMinimumSize(new Dimension(100, 100)); // Ensure it doesn't collapse too small
        imgScrollPane.setBorder(BorderFactory.createEtchedBorder()); // Add border to image area
        imgScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        imgScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        gbcRight.gridwidth = GridBagConstraints.REMAINDER; // Image spans all columns initially
        gbcRight.weightx = 1.0;
        gbcRight.weighty = 0.0; // Image area doesn't stretch vertically initially
        gbcRight.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal fill up to preferred/max size
        gbcRight.anchor = GridBagConstraints.CENTER; // Center the image area
        panRight.add(imgScrollPane, gbcRight);
        gbcRight.gridy++;

        if (hasRightPanelEntries) {

            // Separator after image
            gbcRight.insets = new Insets(10, 5, 5, 5);
            gbcRight.fill = GridBagConstraints.HORIZONTAL;
            panRight.add(new JSeparator(), gbcRight);
            gbcRight.gridy++;
            gbcRight.insets = elementInsets; // Reset insets
            gbcRight.gridwidth = 1; // Reset gridwidth for subsequent rows
            gbcRight.anchor = GridBagConstraints.WEST; // Reset anchor for labels

            // Helper to add Label + TextField pairs
            java.util.function.BiConsumer<String, JTextField> addLabeledTextField = (labelText, textField) -> {
                gbcRight.gridx = 0;
                gbcRight.weightx = 0.0; // Label takes minimal width
                gbcRight.fill = GridBagConstraints.NONE;
                panRight.add(new JLabel(labelText), gbcRight);

                gbcRight.gridx = 1;
                gbcRight.weightx = 1.0; // TextField takes available width
                gbcRight.gridwidth = GridBagConstraints.REMAINDER; // Span remaining columns
                gbcRight.fill = GridBagConstraints.HORIZONTAL;
                panRight.add(textField, gbcRight);
                textField.addFocusListener(this);

                gbcRight.gridy++;
                gbcRight.gridwidth = 1; // Reset gridwidth
            };

            addLabeledTextField.accept(resourceMap.getString("FluffTab.txtManufacturer"), txtManufacturer);
            txtManufacturer.setText(getFluff().getManufacturer());

            addLabeledTextField.accept(resourceMap.getString("FluffTab.txtPrimaryFactory"), txtPrimaryFactory);
            txtPrimaryFactory.setText(getFluff().getPrimaryFactory());

            if (eSource.getEntity().hasETypeFlag(Entity.ETYPE_SMALL_CRAFT)
                    || eSource.getEntity().hasETypeFlag(Entity.ETYPE_JUMPSHIP)) {

                addLabeledTextField.accept(resourceMap.getString("FluffTab.txtUse"), txtUse);
                txtUse.setText(getFluff().getUse());

                // Length/Width/Height need special handling (3 columns on one row)
                gbcRight.gridx = 0;
                gbcRight.weightx = 0.0;
                gbcRight.fill = GridBagConstraints.NONE;
                panRight.add(new JLabel(resourceMap.getString("FluffTab.txtLength")), gbcRight);

                gbcRight.gridx = 1;
                gbcRight.weightx = 1.0 / 3.0; // Distribute width
                gbcRight.fill = GridBagConstraints.HORIZONTAL;
                txtLength.setText(getFluff().getLength());
                panRight.add(txtLength, gbcRight);
                txtLength.addFocusListener(this);

                gbcRight.gridx = 2;
                gbcRight.weightx = 0.0;
                gbcRight.fill = GridBagConstraints.NONE;
                // Add small gap before Width label
                gbcRight.insets = new Insets(elementInsets.top, elementInsets.left + 10, elementInsets.bottom,
                        elementInsets.right);
                panRight.add(new JLabel(resourceMap.getString("FluffTab.txtWidth")), gbcRight);
                gbcRight.insets = elementInsets; // Reset insets

                gbcRight.gridx = 3;
                gbcRight.weightx = 1.0 / 3.0; // Distribute width
                gbcRight.fill = GridBagConstraints.HORIZONTAL;
                txtWidth.setText(getFluff().getWidth());
                panRight.add(txtWidth, gbcRight);
                txtWidth.addFocusListener(this);

                gbcRight.gridx = 4;
                gbcRight.weightx = 0.0;
                gbcRight.fill = GridBagConstraints.NONE;
                // Add small gap before Height label
                gbcRight.insets = new Insets(elementInsets.top, elementInsets.left + 10, elementInsets.bottom,
                        elementInsets.right);
                panRight.add(new JLabel(resourceMap.getString("FluffTab.txtHeight")), gbcRight);
                gbcRight.insets = elementInsets; // Reset insets

                gbcRight.gridx = 5;
                gbcRight.weightx = 1.0 / 3.0; // Distribute width
                gbcRight.fill = GridBagConstraints.HORIZONTAL;
                txtHeight.setText(getFluff().getHeight());
                panRight.add(txtHeight, gbcRight);
                txtHeight.addFocusListener(this);

                gbcRight.gridy++;
            }

            // Add some space before the Systems section
            gbcRight.gridx = 0;
            gbcRight.gridwidth = GridBagConstraints.REMAINDER;
            gbcRight.insets = new Insets(10, 5, 2, 5);
            panRight.add(new JSeparator(), gbcRight); // Separator line
            gbcRight.gridwidth = 1; // Reset gridwidth
            gbcRight.insets = elementInsets; // Reset insets
            gbcRight.gridy++;

            // System/Manufacturer/Model Headers
            gbcRight.gridx = 0;
            gbcRight.weightx = 0.0;
            gbcRight.anchor = GridBagConstraints.WEST;
            panRight.add(new JLabel(resourceMap.getString("FluffTab.System")), gbcRight);
            gbcRight.gridx = 1;
            gbcRight.weightx = 0.5; // Give Manufacturer column some weight
            panRight.add(new JLabel(resourceMap.getString("FluffTab.Manufacturer")), gbcRight);
            gbcRight.gridx = 2;
            gbcRight.weightx = 0.5; // Give Model column some weight
            gbcRight.gridwidth = GridBagConstraints.REMAINDER; // Ensure it takes rest of line
            panRight.add(new JLabel(resourceMap.getString("FluffTab.Model")), gbcRight);
            gbcRight.gridy++;
            gbcRight.gridwidth = 1; // Reset gridwidth

            // Loop through Systems
            for (EntityFluff.System system : EntityFluff.System.values()) {
                if ((system == EntityFluff.System.JUMPJET)
                        && eSource.getEntity().hasETypeFlag(Entity.ETYPE_AERO)) {
                    continue;
                }

                // System Label
                gbcRight.gridx = 0;
                gbcRight.weightx = 0.0;
                gbcRight.fill = GridBagConstraints.NONE;
                panRight.add(new JLabel(resourceMap.getString("FluffTab.System." + system.toString())), gbcRight);

                // Manufacturer Text Field
                gbcRight.gridx = 1;
                gbcRight.weightx = 0.5; // Match header weight
                gbcRight.fill = GridBagConstraints.HORIZONTAL;
                JTextField txtMan = new JTextField(12);
                txtMan.setText(getFluff().getSystemManufacturer(system));
                txtMan.setName(system.name() + ":" + TAG_MANUFACTURER);
                txtMan.addFocusListener(this);
                panRight.add(txtMan, gbcRight);

                // Model Text Field
                gbcRight.gridx = 2;
                gbcRight.weightx = 0.5; // Match header weight
                gbcRight.gridwidth = GridBagConstraints.REMAINDER; // Take rest of line
                gbcRight.fill = GridBagConstraints.HORIZONTAL;
                JTextField txtMod = new JTextField(12);
                txtMod.setText(getFluff().getSystemModel(system));
                txtMod.setName(system.name() + ":" + TAG_MODEL);
                txtMod.addFocusListener(this);
                panRight.add(txtMod, gbcRight);

                gbcRight.gridy++;
                gbcRight.gridwidth = 1; // Reset gridwidth
            }
        }

        // We push the content up
        gbcRight.gridy++;
        gbcRight.weighty = 1.0;
        gbcRight.fill = GridBagConstraints.VERTICAL;
        gbcRight.gridwidth = GridBagConstraints.REMAINDER;
        panRight.add(new JPanel(), gbcRight);

        // --- Add Panels to Main Layout ---
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 0.6; // Give left panel slightly more weight
        mainGbc.weighty = 1.0;
        mainGbc.fill = GridBagConstraints.BOTH;
        mainGbc.anchor = GridBagConstraints.NORTHWEST;
        mainGbc.insets = new Insets(0, 0, 0, 2); // Gap between panels
        contentPanel.add(panLeft, mainGbc);

        mainGbc.gridx = 1;
        mainGbc.weightx = 0.4; // Give right panel less weight
        mainGbc.insets = new Insets(0, 2, 0, 0); // Gap between panels
        contentPanel.add(panRight, mainGbc);

        // --- Add Content Panel to Main Layout ---
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 1.0;
        mainGbc.anchor = GridBagConstraints.NORTH;
        mainGbc.fill = GridBagConstraints.VERTICAL;
        mainGbc.insets = new Insets(5, 5, 5, 5);

        add(contentPanel, mainGbc);

        refreshGUI();
    }

    @Override
    public void focusGained(FocusEvent e) {
        // do nothing
    }

    @Override
    public void focusLost(FocusEvent e) {
        boolean changed = false;
        Object source = e.getSource();

        if (source == txtCapabilities) {
            if (!getFluff().getCapabilities().equals(txtCapabilities.getText())) {
                getFluff().setCapabilities(txtCapabilities.getText());
                changed = true;
            }
        } else if (source == txtOverview) {
            if (!getFluff().getOverview().equals(txtOverview.getText())) {
                getFluff().setOverview(txtOverview.getText());
                changed = true;
            }
        } else if (source == txtDeployment) {
            if (!getFluff().getDeployment().equals(txtDeployment.getText())) {
                getFluff().setDeployment(txtDeployment.getText());
                changed = true;
            }
        } else if (source == txtHistory) {
            if (!getFluff().getHistory().equals(txtHistory.getText())) {
                getFluff().setHistory(txtHistory.getText());
                changed = true;
            }
        } else if (source == txtManufacturer) {
            if (!getFluff().getManufacturer().equals(txtManufacturer.getText())) {
                getFluff().setManufacturer(txtManufacturer.getText());
                changed = true;
            }
        } else if (source == txtPrimaryFactory) {
            if (!getFluff().getPrimaryFactory().equals(txtPrimaryFactory.getText())) {
                getFluff().setPrimaryFactory(txtPrimaryFactory.getText());
                changed = true;
            }
        } else if (source == txtUse) {
            if (!getFluff().getUse().equals(txtUse.getText())) {
                getFluff().setUse(txtUse.getText());
                changed = true;
            }
        } else if (source == txtLength) {
            if (!getFluff().getLength().equals(txtLength.getText())) {
                getFluff().setLength(txtLength.getText());
                changed = true;
            }
        } else if (source == txtWidth) {
            if (!getFluff().getWidth().equals(txtWidth.getText())) {
                getFluff().setWidth(txtWidth.getText());
                changed = true;
            }
        } else if (source == txtHeight) {
            if (!getFluff().getHeight().equals(txtHeight.getText())) {
                getFluff().setHeight(txtHeight.getText());
                changed = true;
            }
        } else if (source == txtNotes) {
            if (!getFluff().getNotes().equals(txtNotes.getText())) {
                getFluff().setNotes(txtNotes.getText());
                changed = true;
            }
        } else if (source instanceof JTextField textField) { // Use pattern matching
            String name = textField.getName();
            String text = textField.getText();
            if (name != null && name.contains(":")) {
                String[] fields = name.split(":");
                try {
                    EntityFluff.System system = EntityFluff.System.parse(fields[0]);
                    if (system != null) {
                        if (TAG_MANUFACTURER.equals(fields[1])) {
                            if (!getFluff().getSystemManufacturer(system).equals(text)) {
                                getFluff().setSystemManufacturer(system, text);
                                changed = true;
                            }
                        } else if (TAG_MODEL.equals(fields[1])) {
                            if (!getFluff().getSystemModel(system).equals(text)) {
                                getFluff().setSystemModel(system, text);
                                changed = true;
                            }
                        }
                    }
                } catch (IllegalArgumentException ex) {
                    logger.warn("Invalid system name found in JTextField name: {}", name, ex);
                }
            }
        }

        if (changed && refresh != null) {
            refresh.refreshPreview();
            refresh.markDirty();
        }
    }

    private void chooseFluffImage() {
        var imageChooser = new MMLFileChooser();
        int result = imageChooser.showOpenDialog(getParent());
        if ((result == JFileChooser.APPROVE_OPTION) && (imageChooser.getSelectedFile() != null)) {
            File imageFile = imageChooser.getSelectedFile();
            if (imageFile.isFile()) {
                String lowerCaseName = imageFile.toString();
                if (Arrays.stream(FluffImageHelper.EXTENSIONS_FLUFF_IMAGE_FORMATS).noneMatch(lowerCaseName::endsWith)) {
                    PopupMessages.showImproperFileType(getParent());
                    return;
                }
                try {
                    BufferedImage image = ImageIO.read(imageFile);
                    if (image == null) {
                        PopupMessages.showFileReadError(getParent(), imageFile.toString());
                        return;
                    }
                    getEntity().getFluff().setFluffImage(ImageUtil.base64TextEncodeImage(image));
                    refresh.refreshPreview();
                    refresh.markDirty();
                } catch (Exception ex) {
                    PopupMessages.showFileReadError(getParent(), imageFile.toString(), ex.getMessage());
                    logger.error("", ex);
                }
            }
        }
        refreshGUI();
    }

    private void importFluffImage() {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(null);
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer = new MegaMekLabUnitSelectorDialog(null, unitLoadingDialog, false);

        Entity chosenEntity = viewer.getChosenEntity();
        if (chosenEntity != null) {
            try {
                Image fluffImage = FluffImageHelper.getFluffImage(chosenEntity);
                if (fluffImage == null) {
                    PopupMessages.showNoFluffImage(getParent());
                    return;
                }
                eSource.getEntity().getFluff().setFluffImage(ImageUtil.base64TextEncodeImage(fluffImage));
                refresh.refreshPreview();
                refresh.markDirty();
            } catch (Exception ex) {
                PopupMessages.showFileReadError(getParent(), "", ex.getMessage());
                logger.error("Fluff could not be copied!", ex);
            }
        }
        viewer.dispose();
        unitLoadingDialog.dispose();
        refreshGUI();
    }

    private void removeFluffImage() {
        if (getEntity() != null && getEntity().getFluff().hasEmbeddedFluffImage()) {
            getEntity().getFluff().setFluffImage("");
            refresh.refreshPreview();
            refresh.markDirty();
        }
        refreshGUI();
    }

    /**
     * Scales an image while maintaining aspect ratio to fit within max dimensions.
     */
    private Image scaleImage(Image originalImage, int maxWidth, int maxHeight) {
        int originalWidth = originalImage.getWidth(null);
        int originalHeight = originalImage.getHeight(null);

        if (originalWidth <= maxWidth && originalHeight <= maxHeight) {
            return originalImage;
        }

        double widthRatio = (double) maxWidth / originalWidth;
        double heightRatio = (double) maxHeight / originalHeight;
        double scaleRatio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (originalWidth * scaleRatio);
        int newHeight = (int) (originalHeight * scaleRatio);

        return originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    private void refreshGUI() {

        EntityFluff fluff = getFluff();
        boolean hasImage = (fluff != null) && fluff.hasEmbeddedFluffImage();

        btnRemoveFluff.setEnabled(hasImage);

        ImageIcon icon = null;
        if (hasImage) {
            try {
                Image img = fluff.getFluffImage();
                if (img != null) {
                    // Scale the image if necessary
                    Image scaledImg = scaleImage(img, MAX_FLUFF_IMG_WIDTH, MAX_FLUFF_IMG_HEIGHT);
                    icon = new ImageIcon(scaledImg);
                }
            } catch (Exception e) {
                logger.error("Unexpected error displaying fluff image", e);
            }
        }

        lblFluffImage.setIcon(icon);
        lblFluffImage.setText(icon == null ? "(No Image)" : null);
        imgScrollPane.revalidate();
        imgScrollPane.repaint();
    }
}
