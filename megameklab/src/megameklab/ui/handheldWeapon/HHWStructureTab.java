package megameklab.ui.handheldWeapon;

import megamek.common.*;
import megameklab.ui.EntitySource;
import megameklab.ui.generalUnit.BasicInfoView;
import megameklab.ui.listeners.BuildListener;
import megameklab.ui.listeners.HHWBuildListener;
import megameklab.ui.util.ITab;
import megameklab.ui.util.RefreshListener;
import megameklab.util.UnitUtil;

import javax.swing.*;
import java.awt.*;

public class HHWStructureTab extends ITab implements HHWBuildListener, BuildListener {
    private BasicInfoView panBasicInfo;
    private HHWChassisView panChassisView;
    private HHWEquipmentView panEquipmentView;

    RefreshListener refresh = null;
    JPanel masterPanel;

    public HHWStructureTab(EntitySource eSource, RefreshListener refresh) {
        super(eSource);
        this.refresh = refresh;
        setLayout(new BorderLayout());
        setUpPanels();
        this.add(masterPanel, BorderLayout.CENTER);
        refresh();
    }

    private void setUpPanels() {
        masterPanel = new JPanel(new GridBagLayout());
        panBasicInfo = new BasicInfoView(getEntity().getConstructionTechAdvancement());
        panChassisView = new HHWChassisView();
        panEquipmentView = new HHWEquipmentView(eSource, refresh);

        panBasicInfo.setFromEntity(getEntity());
        panChassisView.setFromEntity(getEntity());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        leftPanel.add(panBasicInfo);
        leftPanel.add(panChassisView);
        leftPanel.add(panEquipmentView);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        masterPanel.add(leftPanel, gbc);

        panBasicInfo.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        panChassisView.setBorder(BorderFactory.createTitledBorder("Structure"));
        panEquipmentView.setBorder(BorderFactory.createTitledBorder("Equipment"));
        panEquipmentView.refresh();
    }

    public void refresh() {
        removeAllListeners();
        panBasicInfo.setFromEntity(getEntity());
        panChassisView.setFromEntity(getEntity());
        panEquipmentView.refresh();
        addALlListeners();
    }

    public void removeAllListeners() {
        panBasicInfo.removeListener(this);
        panChassisView.removeListener(this);
    }

    public void addALlListeners() {
        panBasicInfo.addListener(this);
        panChassisView.addListener(this);
    }

    public void addRefreshedListener(RefreshListener l) {
        refresh = l;
    }

    public ITechManager getTechManager() {
        return panBasicInfo;
    }

    @Override
    public void weightChanged(double weight) {
        getEntity().setWeight(weight);
        refresh.refreshStatus();
        refresh.refreshPreview();
        refresh.refreshStructure();
    }

    @Override
    public void armorChanged(int armor) {
        getEntity().initializeArmor(armor, HandheldWeapon.LOC_GUN);
        getEntity().setArmorTonnage(getEntity().getArmorWeight());
        refresh();
        refresh.refreshArmor();
        refresh.refreshStatus();
        refresh.refreshPreview();
    }

    @Override
    public void refreshSummary() {
        // todo panSummary.refresh()
    }

    @Override
    public void chassisChanged(String chassis) {
        getEntity().setChassis(chassis);
        refresh.refreshPreview();
        refresh.refreshHeader();
    }

    @Override
    public void modelChanged(String model) {
        getEntity().setModel(model);
        refresh.refreshPreview();
        refresh.refreshHeader();
    }

    @Override
    public void yearChanged(int year) {
        getEntity().setYear(year);
        updateTechLevel();
    }

    @Override
    public void updateTechLevel() {
        // todo implement
    }

    @Override
    public void sourceChanged(String source) {
        getEntity().setSource(source);
    }

    @Override
    public void mulIdChanged(int mulId) {
        getEntity().setMulId(mulId);
    }

    @Override
    public void techBaseChanged(boolean clan, boolean mixed) {
        if ((clan != getEntity().isClan()) || (mixed != getEntity().isMixedTech())) {
            getEntity().setMixedTech(mixed);
            updateTechLevel();
        }
    }

    @Override
    public void techLevelChanged(SimpleTechLevel techLevel) {
        updateTechLevel();
    }

    @Override
    public void roleChanged(UnitRole role) {
        getEntity().setUnitRole(role);
        refresh.refreshPreview();
    }

    @Override
    public void manualBVChanged(int manualBV) {
        UnitUtil.setManualBV(manualBV, getEntity());
        refresh.refreshStatus();
    }

    @Override
    public void walkChanged(int walkMP) { }

    @Override
    public void jumpChanged(int jumpMP, EquipmentType jumpJet) { }

    @Override
    public void jumpTypeChanged(EquipmentType jumpJet) { }
}
