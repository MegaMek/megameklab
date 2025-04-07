/*
 * MegaMekLab
 * Copyright (c) 2019-2022 - The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import megamek.client.ui.swing.UnitLoadingDialog;
import megamek.client.ui.swing.util.UIUtil;
import megamek.client.ui.swing.widget.MegaMekButton;
import megamek.client.ui.swing.widget.SkinSpecification;
import megamek.client.ui.swing.widget.SkinSpecification.UIComponents;
import megamek.client.ui.swing.widget.SkinXMLHandler;
import megamek.client.ui.swing.widget.SkinnedJPanel;
import megamek.common.Configuration;
import megamek.common.Entity;
import megameklab.MMLConstants;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.ui.util.TabUtil;
import megameklab.ui.util.TipOfTheDay; // Import the new class
import megameklab.util.CConfig;
import megameklab.util.MMLFileDropTransferHandler;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeMap;

import static javax.swing.JOptionPane.YES_NO_OPTION;

/**
 * A startup splash screen for MegaMekLab
 * 
 * @author Taharqa
 */
public class StartupGUI extends SkinnedJPanel implements MenuBarOwner {
    JFrame frame;
    MenuBar mmlMenuBar;
    JLabel splash;

    /** A map of resolution widths to file names for the startup screen */
    private final TreeMap<Integer, String> startupScreenImages = new TreeMap<>();
    {
        startupScreenImages.put(0, Configuration.miscImagesDir() + "/mml_start_hd.jpg"); // TODO : Remove inline filename
        startupScreenImages.put(1441, Configuration.miscImagesDir() + "/mml_start_fhd.jpg"); // TODO : Remove inline filename
        startupScreenImages.put(1921, Configuration.miscImagesDir() + "/mml_start_uhd.jpg"); // TODO : Remove inline filename
    }

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Splash");

    // Tip of the Day variables
    private final String tipOfTheDay;
    private final String tipLabel = "Tip of the Day:";
    private Font tipFont;
    private Font tipLabelFont;
    private static final int TIP_BOTTOM_MARGIN = 60;
    private static final int TIP_SIDE_PADDING = 20;
    private static final float TIP_TITLE_FONT_SIZE = 24f;
    private static final float TIP_FONT_SIZE = 32f;
    private static final float STROKE_WIDTH = 4.0f;
    private static final Color TIP_STROKE_COLOR = Color.BLACK;
    private static final Color TIP_TITLE_FONT_COLOR = Color.WHITE;
    private static final Color TIP_FONT_COLOR = Color.WHITE;

    public StartupGUI() {
        super(UIComponents.MainMenuBorder, 1);
        this.tipOfTheDay = TipOfTheDay.getRandomTip();
        initComponents();
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag && CConfig.getBooleanParam(CConfig.NAG_IMPORT_SETTINGS)) {
            showImportSettingsDialog();
        }
    }

    private void showImportSettingsDialog() {
        CConfig.setParam(CConfig.NAG_IMPORT_SETTINGS, Boolean.toString(false));
        int choice = JOptionPane.showConfirmDialog(this,
                "Do you wish to import settings from another MML" +
                        "? You can also do this later from the main menu.",
                "Import Settings?", YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            mmlMenuBar.importSettings();
        }
    }

    private void initComponents() {
        SkinSpecification skinSpec = SkinXMLHandler.getSkin(UIComponents.MainMenuBorder.getComp(), true);

        frame = new JFrame("MegaMekLab");
        setBackground(UIManager.getColor("controlHighlight"));
        mmlMenuBar = new MenuBar(this);
        frame.setJMenuBar(mmlMenuBar);

        Dimension scaledMonitorSize = UIUtil.getScaledScreenSize(frame);
        splash = UIUtil.createSplashComponent(startupScreenImages, frame);
        splash.setOpaque(false);
        add(splash, BorderLayout.CENTER);

        Font baseFont = new Font(skinSpec.fontName, Font.PLAIN, skinSpec.fontSize); // getFont();
        if (baseFont == null) {
            baseFont = new Font(Font.SANS_SERIF, Font.BOLD, 12); //fallback
        }
        tipLabelFont = baseFont.deriveFont(Font.BOLD, TIP_TITLE_FONT_SIZE); // Tip title font
        tipFont = baseFont.deriveFont(Font.BOLD, TIP_FONT_SIZE); // Tip font

        JLabel labVersion = new JLabel(resourceMap.getString("version.text") + MMLConstants.VERSION, JLabel.CENTER);
        labVersion.setPreferredSize(new Dimension(250, 15));
        if (!skinSpec.fontColors.isEmpty()) {
            labVersion.setForeground(skinSpec.fontColors.get(0));
        }

        MegaMekButton btnLoadUnit = new MegaMekButton(resourceMap.getString("btnLoadUnit.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnLoadUnit.addActionListener(evt -> selectAndLoadUnitFromCache(this));

        MegaMekButton btnNewMek = new MegaMekButton(resourceMap.getString("btnNewMek.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewMek.addActionListener(evt -> newUnit(Entity.ETYPE_MEK));

        MegaMekButton btnNewVee = new MegaMekButton(resourceMap.getString("btnNewVee.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewVee.addActionListener(evt -> newUnit(Entity.ETYPE_TANK));

        MegaMekButton btnNewSupportVee = new MegaMekButton(resourceMap.getString("btnNewSupportVee.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewSupportVee.addActionListener(evt -> newUnit(Entity.ETYPE_SUPPORT_TANK));

        MegaMekButton btnNewBA = new MegaMekButton(resourceMap.getString("btnNewBA.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewBA.addActionListener(evt -> newUnit(Entity.ETYPE_BATTLEARMOR));

        MegaMekButton btnNewAero = new MegaMekButton(resourceMap.getString("btnNewAero.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewAero.addActionListener(evt -> newUnit(Entity.ETYPE_AERO));

        MegaMekButton btnNewDropper = new MegaMekButton(resourceMap.getString("btnNewDropper.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewDropper.addActionListener(evt -> newUnit(Entity.ETYPE_DROPSHIP));

        MegaMekButton btnNewLargeCraft = new MegaMekButton(resourceMap.getString("btnNewLargeCraft.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewLargeCraft.addActionListener(evt -> newUnit(Entity.ETYPE_JUMPSHIP));

        MegaMekButton btnNewProto = new MegaMekButton(resourceMap.getString("btnNewProto.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewProto.addActionListener(evt -> newUnit(Entity.ETYPE_PROTOMEK));

        MegaMekButton btnNewPbi = new MegaMekButton(resourceMap.getString("btnNewPbi.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnNewPbi.addActionListener(evt -> newUnit(Entity.ETYPE_INFANTRY));

        MegaMekButton btnQuit = new MegaMekButton(resourceMap.getString("btnQuit.text"),
                UIComponents.MainMenuButton.getComp(), true);
        btnQuit.addActionListener(evt -> System.exit(0));

        FontMetrics metrics = btnNewDropper.getFontMetrics(btnNewDropper.getFont());
        int width = metrics.stringWidth(btnNewDropper.getText());
        int height = metrics.getHeight();
        Dimension textDim = new Dimension(width + 50, height + 10);

        // Strive for no more than ~90% of the screen and use golden ratio to make
        // the button width "look" reasonable.
        int maximumWidth = (int) (0.9 * scaledMonitorSize.width) - splash.getPreferredSize().width;

        // no more than 50% of image width
        if (maximumWidth > (int) (0.5 * splash.getPreferredSize().width)) {
            maximumWidth = (int) (0.5 * splash.getPreferredSize().width);
        }

        Dimension minButtonDim = new Dimension((int) (maximumWidth / 1.618), 25);
        if (textDim.getWidth() > minButtonDim.getWidth()) {
            minButtonDim = textDim;
        }

        btnLoadUnit.setMinimumSize(minButtonDim);
        btnLoadUnit.setPreferredSize(minButtonDim);
        btnNewMek.setMinimumSize(minButtonDim);
        btnNewMek.setPreferredSize(minButtonDim);
        btnNewVee.setMinimumSize(minButtonDim);
        btnNewVee.setPreferredSize(minButtonDim);
        btnNewSupportVee.setMinimumSize(minButtonDim);
        btnNewSupportVee.setPreferredSize(minButtonDim);
        btnNewBA.setMinimumSize(minButtonDim);
        btnNewBA.setPreferredSize(minButtonDim);
        btnNewAero.setMinimumSize(minButtonDim);
        btnNewAero.setPreferredSize(minButtonDim);
        btnNewDropper.setMinimumSize(minButtonDim);
        btnNewDropper.setPreferredSize(minButtonDim);
        btnNewLargeCraft.setMinimumSize(minButtonDim);
        btnNewLargeCraft.setPreferredSize(minButtonDim);
        btnNewPbi.setMinimumSize(minButtonDim);
        btnNewPbi.setPreferredSize(minButtonDim);
        btnNewProto.setMinimumSize(minButtonDim);
        btnNewProto.setPreferredSize(minButtonDim);
        btnQuit.setMinimumSize(minButtonDim);
        btnQuit.setPreferredSize(minButtonDim);

        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Left Column (Splash Image)
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 5, 10, 10);
        c.ipadx = 10;
        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 12;
        add(splash, c);

        // Right Column (Buttons)
        c.insets = new Insets(2, 2, 2, 10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;
        add(labVersion, c);

        c.gridy++;
        add(btnLoadUnit, c);
        c.gridy++;
        add(btnNewMek, c);
        c.gridy++;
        add(btnNewVee, c);
        c.gridy++;
        add(btnNewSupportVee, c);
        c.gridy++;
        add(btnNewProto, c);
        c.gridy++;
        add(btnNewBA, c);
        c.gridy++;
        add(btnNewPbi, c);
        c.gridy++;
        add(btnNewAero, c);
        c.gridy++;
        add(btnNewDropper, c);
        c.gridy++;
        add(btnNewLargeCraft, c);
        c.gridy++;
        add(btnQuit, c);

        setOpaque(false);

        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.addWindowListener(new ExitOnWindowClosingListener(this));
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.setTransferHandler(new MMLFileDropTransferHandler(this));
    }

    /**
     * Override paint to draw the tip *after* children are painted.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Draw background, border, and children first

        // Now draw the tip on top
        if (splash != null && splash.isVisible() && splash.getWidth() > 0 && splash.getHeight() > 0) {
            drawTipOfTheDay((Graphics2D) g);
        }
    }

    /**
     * Draws the Tip of the Day text with word wrap and styling.
     */
    private void drawTipOfTheDay(Graphics2D g2d) {
        if (tipOfTheDay == null || tipOfTheDay.isEmpty() || tipLabelFont == null || tipFont == null) {
            return;
        }

        Graphics2D g = (Graphics2D) g2d.create();

        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            // Get splash bounds relative to this panel
            Rectangle splashBounds = splash.getBounds();
            if (splashBounds == null || splashBounds.width <= 0 || splashBounds.height <= 0) {
                return; // Cannot draw if splash has no size/location yet
            }

            // Calculate available width for text inside splash bounds with padding
            float availableWidth = splashBounds.width - (TIP_SIDE_PADDING * 2);
            if (availableWidth <= 0)
                return; // Not enough space

            FontRenderContext frc = g.getFontRenderContext();

            // "Tip of the Day:" label
            AttributedString labelAS = new AttributedString(tipLabel);
            labelAS.addAttribute(TextAttribute.FONT, tipLabelFont);
            TextLayout labelLayout = new TextLayout(labelAS.getIterator(), frc);
            float labelHeight = labelLayout.getAscent() + labelLayout.getDescent() + labelLayout.getLeading();
            float labelWidth = (float) labelLayout.getBounds().getWidth();

            // Actual tip text with word wrapping
            AttributedString tipAS = new AttributedString(tipOfTheDay);
            tipAS.addAttribute(TextAttribute.FONT, tipFont);
            LineBreakMeasurer measurer = new LineBreakMeasurer(tipAS.getIterator(), frc);
            List<TextLayout> tipLayouts = new ArrayList<>();
            float totalTipHeight = 0;
            measurer.setPosition(0);
            while (measurer.getPosition() < tipAS.getIterator().getEndIndex()) {
                TextLayout layout = measurer.nextLayout(availableWidth);
                if (layout != null) {
                    tipLayouts.add(layout);
                    totalTipHeight += layout.getAscent() + layout.getDescent() + layout.getLeading();
                } else {
                    break; // Should not happen with LineBreakMeasurer unless width is tiny
                }
                if (measurer.getPosition() == layout.getCharacterCount() + measurer.getPosition()
                        && measurer.getPosition() < tipAS.getIterator().getEndIndex()) {
                    break;
                }
            }

            // Positioning
            float totalBlockHeight = labelHeight + totalTipHeight;
            float startY = splashBounds.y + splashBounds.height - TIP_BOTTOM_MARGIN - totalBlockHeight;
            float startX = splashBounds.x + TIP_SIDE_PADDING;

            // Draw the text (outline then fill)
            BasicStroke outlineStroke = new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g.setStroke(outlineStroke);

            // Draw Label
            float labelDrawX = startX + (availableWidth - labelWidth) / 2; // Center label
            float labelDrawY = startY + labelLayout.getAscent();
            Shape labelShape = labelLayout.getOutline(null);

            g.translate(labelDrawX, labelDrawY);
            g.setColor(TIP_STROKE_COLOR);
            g.draw(labelShape); // Draw outline
            g.setColor(TIP_TITLE_FONT_COLOR); // Fill color
            g.fill(labelShape); // Draw fill
            g.translate(-labelDrawX, -labelDrawY); // Translate back

            // Draw Tip Lines
            float currentY = startY + labelHeight; // Start drawing tips below the label
            for (TextLayout tipLayout : tipLayouts) {
                float lineAscent = tipLayout.getAscent();
                float lineHeight = lineAscent + tipLayout.getDescent() + tipLayout.getLeading();
                float lineDrawY = currentY + lineAscent; // Baseline for this line
                float lineWidth = (float) tipLayout.getBounds().getWidth();

                float lineDrawX = startX + (availableWidth - lineWidth) / 2f; // Center line
                lineDrawX = Math.max(startX, lineDrawX); // Ensure it doesn't go out of bounds
                Shape tipShape = tipLayout.getOutline(AffineTransform.getTranslateInstance(lineDrawX, lineDrawY));
                g.setColor(TIP_STROKE_COLOR); // Outline color
                g.draw(tipShape);      // Draw outline
                g.setColor(TIP_FONT_COLOR); // Fill color
                g.fill(tipShape);      // Draw fill


                currentY += lineHeight;
            }

        } finally {
            g.dispose();
        }
    }

    private static String processFileName(File file, Entity newUnit) {
        String fileName = file.toString();
        if (fileName.toLowerCase().endsWith(".zip")) {
            fileName = file.getAbsolutePath();
            fileName = fileName.substring(0, fileName.lastIndexOf(File.separatorChar) + 1);
            fileName = fileName + MegaMekLabFileSaver.createUnitFilename(newUnit);
        }
        return fileName;
    }

    private static void addUnits(MegaMekLabUnitSelectorDialog viewer, MenuBarOwner owner) {
        var entities = viewer.getChosenEntities();
        var mekSummaries = viewer.getSelectedMekSummaries();
        var fileNames = new ArrayList<String>();
        for (int i = 0; i < entities.size(); i++) {
            fileNames.add(processFileName(mekSummaries.get(i).getSourceFile(), entities.get(i)));
        }
        TabUtil.loadMany(entities, fileNames, owner);
    }

    /**
     * Shows the Unit Selector Window and loads the unit if the user selects one.
     * When the chosen
     * unit fits the MageMekLabMainUI given as previousFrame this frame will be kept
     * and updated
     * to the chosen unit, otherwise, a new UI will be created for the unit and
     * previousFrame will
     * be closed and disposed.
     *
     * @param previousFrame The active frame before loading a new unit; can be the
     *                      StartupGUI or any
     *                      MegaMekLabMainUI.
     */
    public static void selectAndLoadUnitFromCache(MenuBarOwner previousFrame) {
        UnitLoadingDialog unitLoadingDialog = new UnitLoadingDialog(previousFrame.getFrame());
        unitLoadingDialog.setVisible(true);
        MegaMekLabUnitSelectorDialog viewer;
        if (previousFrame instanceof MegaMekLabTabbedUI tabbedUI) {
            viewer = new MegaMekLabUnitSelectorDialog(previousFrame.getFrame(), unitLoadingDialog,
                    dialog -> addUnits(dialog, tabbedUI));
        } else {
            viewer = new MegaMekLabUnitSelectorDialog(previousFrame.getFrame(), unitLoadingDialog, true);
        }
        viewer.dispose();
        if (CollectionUtils.isEmpty(viewer.getChosenEntities())) {
            return;
        }

        addUnits(viewer, previousFrame);
    }

    @Override
    public JFrame getFrame() {
        return frame;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public boolean hasEntityNameChanged() {
        return false;
    }

    @Override
    public void refreshMenuBar() {
        if (mmlMenuBar != null) {
            mmlMenuBar.refreshMenuBar();
        }
    }

    @Override
    public MenuBar getMMLMenuBar() {
        return mmlMenuBar;
    }
}
