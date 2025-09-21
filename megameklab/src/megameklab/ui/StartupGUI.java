/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
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
package megameklab.ui;

import static javax.swing.JOptionPane.YES_NO_OPTION;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.VolatileImage;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import megamek.MegaMek;
import megamek.client.ui.dialogs.UnitLoadingDialog;
import megamek.client.ui.util.UIUtil;
import megamek.client.ui.widget.MegaMekButton;
import megamek.client.ui.widget.RawImagePanel;
import megamek.client.ui.widget.SkinSpecification;
import megamek.client.ui.widget.SkinSpecification.UIComponents;
import megamek.client.ui.widget.SkinXMLHandler;
import megamek.client.ui.widget.SkinnedJPanel;
import megamek.common.Configuration;
import megamek.common.annotations.Nullable;
import megamek.common.units.Entity;
import megamek.common.util.ImageUtil;
import megamek.common.util.ManagedVolatileImage;
import megamek.common.util.TipOfTheDay;
import megamek.common.util.fileUtils.MegaMekFile;
import megamek.logging.MMLogger;
import megameklab.MMLConstants;
import megameklab.ui.dialog.MegaMekLabUnitSelectorDialog;
import megameklab.ui.dialog.UiLoader;
import megameklab.ui.util.ExitOnWindowClosingListener;
import megameklab.ui.util.MegaMekLabFileSaver;
import megameklab.ui.util.TabUtil;
import megameklab.util.CConfig;
import megameklab.util.MMLFileDropTransferHandler;
import org.apache.commons.collections4.CollectionUtils;

/**
 * A startup splash screen for MegaMekLab
 *
 * @author Taharqa
 */
public class StartupGUI extends SkinnedJPanel implements MenuBarOwner {
    private static final MMLogger logger = MMLogger.create(MegaMek.class);
    JFrame frame;
    MenuBar mmlMenuBar;
    RawImagePanel splashPanel;
    private ManagedVolatileImage logoImage;
    private ManagedVolatileImage medalImage;
    private double lastDpiScaleFactor;
    private static volatile StartupGUI instance = null;
    private static final String FILENAME_MEGAMEK_SPLASH = "../misc/mml-background.jpg";
    private static final String FILENAME_MEDAL = "../misc/mml-medal.png";
    private static final String FILENAME_LOGO = "../misc/mml-logo.png";

    private final ResourceBundle resourceMap = ResourceBundle.getBundle("megameklab.resources.Splash");
    private final TipOfTheDay tipOfTheDay;

    private StartupGUI() {
        super(UIComponents.MainMenuBorder, 1);
        frame = new JFrame("MegaMekLab");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        lastDpiScaleFactor = UIUtil.getMonitorScaleFactor(frame);
        tipOfTheDay = new TipOfTheDay(resourceMap.getString("TipOfTheDay.title.text"),
              "megameklab.resources.TipOfTheDay",
              frame);
        setupDpiChangeListeners();
        initComponents();
    }

    /**
     * Sets up the DPI change listeners for multi-monitor support
     */
    private void setupDpiChangeListeners() {
        try {
            // This works on Windows 10+ for per-monitor DPI awareness
            Toolkit.getDefaultToolkit()
                  .addPropertyChangeListener("win.displayChange",
                        evt -> SwingUtilities.invokeLater(this::handleDpiChange));

            //For all other platforms
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent e) {
                    SwingUtilities.invokeLater(StartupGUI.this::handleDpiChange);
                }
            });

            // Display changes
            frame.addWindowStateListener(e -> SwingUtilities.invokeLater(this::handleDpiChange));
        } catch (Exception e) {
            logger.error("Per-monitor DPI awareness not supported: {}", e.getMessage());
        }
    }

    /**
     * Handles DPI changes
     */
    public void handleDpiChange() {
        final double newDpiScaleFactor = UIUtil.getMonitorScaleFactor(frame);
        if (Math.abs(lastDpiScaleFactor - newDpiScaleFactor) <= 0.01) {
            return;
        }
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::handleDpiChange);
            return;
        }
        lastDpiScaleFactor = newDpiScaleFactor;
        Rectangle oldBounds = frame.getBounds();
        Point oldCenter = new Point(oldBounds.x + oldBounds.width / 2,
              oldBounds.y + oldBounds.height / 2);
        Container container = frame.getContentPane();
        container.removeAll();
        removeAll();
        tipOfTheDay.updateScaleFactor(frame);
        initComponents();
        Dimension newSize = frame.getSize();
        Point newTopLeft = new Point(oldCenter.x - newSize.width / 2,
              oldCenter.y - newSize.height / 2);
        frame.setLocation(newTopLeft);
    }

    /**
     * Checks if the StartupGUI instance is already created.
     *
     */
    static public boolean hasInstance() {
        return instance != null;
    }

    /**
     * Returns the instance of the StartupGUI. If it does not exist, it creates a new one.
     *
     * @return The instance of the StartupGUI.
     */
    static public StartupGUI getInstance() {
        if (instance == null) {
            instance = new StartupGUI();
        }
        return instance;
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

    private void createNewUnit(long type) {
        getFrame().setVisible(false);
        CConfig.setParam(CConfig.GUI_FULLSCREEN, Integer.toString(getFrame().getExtendedState()));
        CConfig.saveConfig();
        UiLoader.loadUi(type, false, false);
        getFrame().dispose();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setOpaque(false);
        setBackground(UIManager.getColor("controlHighlight"));
        SkinSpecification skinSpec = SkinXMLHandler.getSkin(UIComponents.MainMenuBorder.getComp(), true);

        frame.setTitle("MegaMekLab");
        mmlMenuBar = new MenuBar(this);
        frame.setJMenuBar(mmlMenuBar);

        Dimension scaledMonitorSize = UIUtil.getScaledScreenSize(frame);
        Image splashImage = getImage(FILENAME_MEGAMEK_SPLASH, scaledMonitorSize.width, scaledMonitorSize.height);
        logoImage = new ManagedVolatileImage(getImage(FILENAME_LOGO, scaledMonitorSize.width, scaledMonitorSize.height),
              Transparency.TRANSLUCENT);
        medalImage = new ManagedVolatileImage(getImage(FILENAME_MEDAL,
              scaledMonitorSize.width,
              scaledMonitorSize.height), Transparency.TRANSLUCENT);
        Dimension splashPanelPreferredSize = calculateSplashPanelPreferredSize(scaledMonitorSize, splashImage);
        splashPanel = new RawImagePanel(splashImage) {
            @Override
            public void paint(Graphics g) {
                super.paint(g); // Draw background, border, and children first
                Graphics2D g2d = (Graphics2D) g.create();
                try {
                    int panelWidth = this.getWidth();
                    int panelHeight = this.getHeight();

                    // Draw Tip of the Day
                    if (tipOfTheDay != null) {
                        // Absolute drawing position
                        Rectangle bounds = this.getBounds();
                        bounds.x = 0;
                        bounds.y = 0;
                        tipOfTheDay.drawTipOfTheDay(g2d, bounds, TipOfTheDay.Position.BOTTOM_BORDER, false);
                    }

                    // Draw logoImage
                    int logoHeight = drawLogo(g2d, panelWidth, panelHeight);
                    // Draw medalImage
                    drawMedal(g2d, panelWidth, panelHeight, logoHeight);
                } finally {
                    g2d.dispose();
                }
            }
        };
        splashPanel.setPreferredSize(splashPanelPreferredSize);

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
        btnNewMek.addActionListener(evt -> createNewUnit(Entity.ETYPE_MEK));

        MegaMekButton btnNewVee = new MegaMekButton(resourceMap.getString("btnNewVee.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewVee.addActionListener(evt -> createNewUnit(Entity.ETYPE_TANK));

        MegaMekButton btnNewSupportVee = new MegaMekButton(resourceMap.getString("btnNewSupportVee.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewSupportVee.addActionListener(evt -> createNewUnit(Entity.ETYPE_SUPPORT_TANK));

        MegaMekButton btnNewBA = new MegaMekButton(resourceMap.getString("btnNewBA.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewBA.addActionListener(evt -> createNewUnit(Entity.ETYPE_BATTLEARMOR));

        MegaMekButton btnNewAero = new MegaMekButton(resourceMap.getString("btnNewAero.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewAero.addActionListener(evt -> createNewUnit(Entity.ETYPE_AERO));

        MegaMekButton btnNewDropper = new MegaMekButton(resourceMap.getString("btnNewDropper.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewDropper.addActionListener(evt -> createNewUnit(Entity.ETYPE_DROPSHIP));

        MegaMekButton btnNewLargeCraft = new MegaMekButton(resourceMap.getString("btnNewLargeCraft.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewLargeCraft.addActionListener(evt -> createNewUnit(Entity.ETYPE_JUMPSHIP));

        MegaMekButton btnNewProto = new MegaMekButton(resourceMap.getString("btnNewProto.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewProto.addActionListener(evt -> createNewUnit(Entity.ETYPE_PROTOMEK));

        MegaMekButton btnNewPbi = new MegaMekButton(resourceMap.getString("btnNewPbi.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnNewPbi.addActionListener(evt -> createNewUnit(Entity.ETYPE_INFANTRY));

        MegaMekButton btnQuit = new MegaMekButton(resourceMap.getString("btnQuit.text"),
              UIComponents.MainMenuButton.getComp(), true);
        btnQuit.addActionListener(evt -> System.exit(0));

        FontMetrics metrics = btnNewDropper.getFontMetrics(btnNewDropper.getFont());
        int width = metrics.stringWidth(btnNewDropper.getText());
        Dimension minButtonDim = getButtonDim(metrics, width, scaledMonitorSize);

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
        GridBagConstraints c = new GridBagConstraints();
        // Left Column (Splash Image)
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 0, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 3.0;
        c.weighty = 1.0;
        c.gridwidth = 1;
        c.gridheight = 12;
        add(splashPanel, c);

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

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.addWindowListener(new ExitOnWindowClosingListener(this));
        this.setTransferHandler(new MMLFileDropTransferHandler(this));
        frame.validate();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private Dimension getButtonDim(FontMetrics metrics, int width, Dimension scaledMonitorSize) {
        int height = metrics.getHeight();
        Dimension textDim = new Dimension(width + 50, height + 10);

        // Strive for no more than ~90% of the screen and use golden ratio to make
        // the button width "look" reasonable.
        int maximumWidth = (int) (0.9 * scaledMonitorSize.width) - splashPanel.getPreferredSize().width;

        // no more than 50% of image width
        if (maximumWidth > (int) (0.5 * splashPanel.getPreferredSize().width)) {
            maximumWidth = (int) (0.5 * splashPanel.getPreferredSize().width);
        }

        Dimension minButtonDim = new Dimension((int) (maximumWidth / 1.618), 25);
        if (textDim.getWidth() > minButtonDim.getWidth()) {
            minButtonDim = textDim;
        }
        return minButtonDim;
    }


    private @Nullable Image getImage(final String filename, final int screenWidth,
          final int screenHeight) {
        File file = new MegaMekFile(Configuration.widgetsDir(), filename).getFile();
        if (!file.exists()) {
            logger.error("MainMenu Error: Image doesn't exist: {}", file.getAbsolutePath());
            return null;
        }
        Image img = ImageUtil.loadImageFromFile(file.toString());
        // wait for image to load completely
        MediaTracker tracker = new MediaTracker(frame);
        tracker.addImage(img, 0);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException ignored) {
            // really should never come here
        }

        return img;
    }

    /**
     * Calculates the preferred size for the splash panel
     *
     * @param scaledMonitorSize the scaled monitor dimensions
     * @param splashImage       the reference image for the aspect ratio
     *
     * @return the calculated preferred size for the splash panel
     */
    private Dimension calculateSplashPanelPreferredSize(Dimension scaledMonitorSize, Image splashImage) {
        // Calculate max dimensions (75% of screen)
        int maxWidth = (int) (scaledMonitorSize.width * 0.75);
        int maxHeight = (int) (scaledMonitorSize.height * 0.75);

        if (splashImage != null && splashImage.getWidth(null) > 0 && splashImage.getHeight(null) > 0) {
            // Calculate aspect ratio preserving dimensions
            double imageWidth = splashImage.getWidth(null);
            double imageHeight = splashImage.getHeight(null);
            double imageAspectRatio = imageWidth / imageHeight;

            int targetWidth = maxWidth;
            int targetHeight = (int) (targetWidth / imageAspectRatio);

            if (targetHeight > maxHeight) {
                targetHeight = maxHeight;
                targetWidth = (int) (targetHeight * imageAspectRatio);
            }

            return new Dimension(targetWidth, targetHeight);
        } else {
            // Fallback to original calculation if image is not available
            return new Dimension(maxWidth, maxHeight);
        }
    }


    private void drawMedal(Graphics2D g2d, int panelWidth, int panelHeight, int logoHeight) {
        if (medalImage == null) {
            return; // Skip drawing if medalImage is not initialized
        }
        VolatileImage image = medalImage.getImage();
        if (image.getWidth(null) > 0 && image.getHeight(null) > 0) {
            double medalScalePercent = 0.10; // Medal height as % of panel

            int originalMedalWidth = image.getWidth(null);
            int originalMedalHeight = image.getHeight(null);

            int targetMedalWidth = (int) (panelWidth * medalScalePercent);
            if (targetMedalWidth < 1) {
                targetMedalWidth = 1; // Ensure minimum size
            }

            double scaleFactor = (double) targetMedalWidth / originalMedalWidth;
            int targetMedalHeight = (int) (originalMedalHeight * scaleFactor);
            if (targetMedalHeight < 1) {targetMedalHeight = 1;}

            // Position: center, under logo
            int medalX = (panelWidth - targetMedalWidth) / 2;
            // Adjust Y position to be below the logo, 4% overlap
            int medalY = ((((int) (panelHeight * 0.8f)) + logoHeight) / 2) - (int) (logoHeight * 0.04);

            if (medalX < 0) {medalX = 0;}
            if (medalY < 0) {medalY = 0;}

            g2d.drawImage(image, medalX, medalY, targetMedalWidth, targetMedalHeight, null);
        }
    }

    private int drawLogo(Graphics2D g2d, int panelWidth, int panelHeight) {
        if (logoImage == null) {
            return 0; // Skip drawing if logoImage is not initialized
        }
        VolatileImage image = logoImage.getImage();
        int targetLogoHeight = 0;
        if (image.getWidth(null) > 0 && image.getHeight(null) > 0) {
            double logoWidthScalePercent = 0.3f; // Logo width as 30% of panel width

            int originalLogoWidth = image.getWidth(null);
            int originalLogoHeight = image.getHeight(null);

            int targetLogoWidth = (int) (panelWidth * logoWidthScalePercent);
            if (targetLogoWidth < 1) {
                targetLogoWidth = 1; // Ensure minimum size
            }

            double scaleFactor = (double) targetLogoWidth / originalLogoWidth;
            targetLogoHeight = (int) (originalLogoHeight * scaleFactor);
            if (targetLogoHeight < 1) {targetLogoHeight = 1;}
            // Position: center of the panel
            int logoX = (panelWidth - targetLogoWidth) / 2;
            int logoY = (((int) (panelHeight * 0.8f)) - targetLogoHeight) / 2;

            if (logoX < 0) {logoX = 0;}
            if (logoY < 0) {logoY = 0;}

            g2d.drawImage(image, logoX, logoY, targetLogoWidth, targetLogoHeight, null);
        }
        return targetLogoHeight;
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
     * Shows the Unit Selector Window and loads the unit if the user selects one. When the chosen unit fits the
     * MageMekLabMainUI given as previousFrame this frame will be kept and updated to the chosen unit, otherwise, a new
     * UI will be created for the unit and previousFrame will be closed and disposed.
     *
     * @param previousFrame The active frame before loading a new unit; can be the StartupGUI or any MegaMekLabMainUI.
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
        try {
            if (CollectionUtils.isEmpty(viewer.getChosenEntities())) {
                return;
            }
            addUnits(viewer, previousFrame);
        } finally {
            unitLoadingDialog.dispose();
            viewer.dispose();
        }
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
