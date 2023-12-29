package megameklab.ui.generalUnit.summary;

import megamek.common.Entity;
import megamek.common.EquipmentType;
import megamek.common.TechConstants;
import megamek.common.verifier.TestEntity;
import megameklab.util.UnitUtil;

public class StructureSummaryItem extends AbstractSummaryItem{

    @Override
    public String getName() {
        return "Structure";
    }

    @Override
    public void refresh(Entity entity) {
        TestEntity testEntity = UnitUtil.getEntityVerifier(entity);
        int type = entity.getStructureType();
        if ((type >= 0) && (type < EquipmentType.structureNames.length)) {
            String structName = EquipmentType.getStructureTypeName(type,
                    TechConstants.isClan(entity.getStructureTechLevel()));
            EquipmentType structureType = EquipmentType.get(structName);
            availabilityLabel.setText(structureType.getFullRatingName(entity.isClan()));
            weightLabel.setText(formatWeight(testEntity.getWeightStructure()));
            critLabel.setText(formatCrits(structureType.getCriticals(entity)));
        } else {
            availabilityLabel.setText("-");
            weightLabel.setText("-");
            critLabel.setText("-");
        }
    }
}