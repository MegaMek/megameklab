package megameklab.ui.handheldWeapon;

import megamek.common.*;
import megameklab.ui.MegaMekLabMainUI;
import megameklab.ui.generalUnit.PreviewTab;
import megameklab.ui.util.TabScrollPane;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HHWMainUI extends MegaMekLabMainUI {
    private final JTabbedPane configPane = new JTabbedPane(SwingConstants.TOP);
    private HHWStructureTab structureTab;
    private PreviewTab previewTab;
    private HHWStatusBar statusbar;

    public HHWMainUI() {
        super();
        createNewUnit(Entity.ETYPE_HANDHELD_WEAPON);
        finishSetup();
    }

    @Override
    public void reloadTabs() {
        configPane.removeAll();
        getContentPane().removeAll();

        structureTab = new HHWStructureTab(this, this);
        previewTab = new PreviewTab(this);
        structureTab.addRefreshedListener(this);

        configPane.addTab("Structure", new TabScrollPane(structureTab));
        configPane.addTab("Preview", new TabScrollPane(previewTab));

        statusbar = new HHWStatusBar(this);
        statusbar.addRefreshedListener(this);

        add(configPane, BorderLayout.CENTER);
        add(statusbar, BorderLayout.SOUTH);

        refreshHeader();
        validate();
    }

    @Override
    public void refreshAll() {
        // todo Refresh everything
        structureTab.refresh();
        previewTab.refresh();
        statusbar.refresh();
        refreshHeader();
    }

    @Override
    public void refreshArmor() {

    }

    @Override
    public void refreshBuild() {

    }

    @Override
    public void refreshEquipment() {
        structureTab.refresh();
        previewTab.refresh();
        statusbar.refresh();
    }

    @Override
    public void refreshTransport() { }

    @Override
    public void refreshStatus() {
        statusbar.refresh();
    }

    @Override
    public void refreshStructure() {

    }

    @Override
    public void refreshWeapons() {

    }

    @Override
    public void refreshPreview() {
        previewTab.refresh();
    }

    @Override
    public void refreshSummary() {

    }

    @Override
    public void refreshEquipmentTable() {

    }

    @Override
    public JDialog getFloatingEquipmentDatabase() {
        return null;
    }

    @Override
    public List<Mounted<?>> getUnallocatedMounted() {
        return List.of();
    }

    @Override
    public void createNewUnit(long entitytype, boolean isPrimitive, boolean isIndustrial, Entity oldUnit) {
        HandheldWeapon hhw = new HandheldWeapon();
        setEntity(hhw);
        hhw.setWeight(6);
        hhw.setTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        hhw.autoSetInternal();
        hhw.setArmorType(EquipmentType.T_ARMOR_STANDARD);
        hhw.setArmorTechLevel(TechConstants.T_IS_EXPERIMENTAL);
        hhw.initializeArmor(0, HandheldWeapon.LOC_GUN);

        if (null == oldUnit) {
            hhw.setChassis("New");
            hhw.setModel("Handheld Weapon");
            hhw.setYear(3145);
        } else {
            hhw.setChassis(oldUnit.getChassis());
            hhw.setModel(oldUnit.getModel());
            hhw.setYear(Math.max(oldUnit.getYear(), hhw.getConstructionTechAdvancement().getIntroductionDate()));
            hhw.setSource(oldUnit.getSource());
            hhw.setManualBV(oldUnit.getManualBV());
            hhw.setTechLevel(oldUnit.getTechLevel());
            hhw.setMixedTech(oldUnit.isMixedTech());
        }
    }

    @Override
    public ITechManager getTechManager() {
        return structureTab.getTechManager();
    }
}
