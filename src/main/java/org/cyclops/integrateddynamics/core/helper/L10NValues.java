package org.cyclops.integrateddynamics.core.helper;

import org.cyclops.integrateddynamics.api.APIReference;

/**
 * Collection of generic L10N entries.
 * @author rubensworks
 */
public class L10NValues {

    public static final String NS = APIReference.API_OWNER;

    public static final String GENERAL_ENERGY_UNIT = "general." + NS + ".energyUnit.name";

    public static final String PART_PANEL_ERROR_INVALIDTYPE = "parttype.parttypes." + NS + ".dataDrivenPanel.error.invalidType";

    public static final String GUI_LOGICPROGRAMMER_FILTER = "gui." + NS + ".logicprogrammer.filter";

    public static final String VALUETYPE_TOOLTIP_TYPENAME = "valuetype." + NS + ".tooltip.typeName";
    public static final String VALUETYPE_TOOLTIP_VALUE = "valuetype." + NS + ".tooltip.value";
    public static final String VALUETYPE_ERROR_INVALIDINPUT = "valuetype." + NS + ".error.invalidInput";
    public static final String VALUETYPE_ERROR_INVALIDINPUTITEM = "valuetype." + NS + ".error.invalidInputItem";
    public static final String VALUETYPE_OBJECT_BLOCK_ERROR_NOBLOCK = "valuetype." + NS + ".error.block.noBlock";

    public static final String ASPECT_TOOLTIP_PARTID = "aspect." + NS + ".tooltip.partId";
    public static final String ASPECT_TOOLTIP_ASPECTNAME = "aspect." + NS + ".tooltip.aspectName";
    public static final String ASPECT_TOOLTIP_VALUETYPENAME = "aspect." + NS + ".tooltip.valueTypeName";
    public static final String ASPECT_ERROR_PARTNOTINNETWORK = "variable." + NS + ".error.partNotInNetwork";
    public static final String ASPECT_ERROR_INVALIDTYPE = "aspect." + NS + ".error.invalidType";

    public static final String VARIABLE_ERROR_INVALIDITEM = "variable." + NS + ".error.invalidItem";
    public static final String VARIABLE_ERROR_PARTNOTINNETWORK = "variable." + NS + ".error.partNotInNetwork";

    public static final String OPERATOR_TOOLTIP_OPERATORNAME = "operator." + NS + ".tooltip.operatorName";
    public static final String OPERATOR_TOOLTIP_OPERATORCATEGORY = "operator." + NS + ".tooltip.operatorCategory";
    public static final String OPERATOR_TOOLTIP_INPUTTYPENAME = "operator." + NS + ".tooltip.inputTypeName";
    public static final String OPERATOR_TOOLTIP_OUTPUTTYPENAME = "operator." + NS + ".tooltip.outputTypeName";
    public static final String OPERATOR_TOOLTIP_VARIABLEIDS = "operator." + NS + ".tooltip.variableIds";
    public static final String OPERATOR_ERROR_VARIABLENOTINNETWORK = "operator." + NS + ".error.variableNotInNetwork";
    public static final String OPERATOR_ERROR_CYCLICREFERENCE = "operator." + NS + ".error.cyclicReference";
    public static final String OPERATOR_ERROR_WRONGINPUTLENGTH = "operator." + NS + ".error.wrongInputLength";
    public static final String OPERATOR_ERROR_NULLTYPE = "operator." + NS + ".error.nullType";
    public static final String OPERATOR_ERROR_WRONGTYPE = "operator." + NS + ".error.wrongType";

}
