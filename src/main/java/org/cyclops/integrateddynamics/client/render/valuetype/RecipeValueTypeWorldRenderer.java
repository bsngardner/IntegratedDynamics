package org.cyclops.integrateddynamics.client.render.valuetype;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.EnumFacing;
import org.cyclops.commoncapabilities.api.capability.recipehandler.IRecipeDefinition;
import org.cyclops.commoncapabilities.api.ingredient.IngredientComponent;
import org.cyclops.commoncapabilities.api.ingredient.PrototypedIngredient;
import org.cyclops.cyclopscore.helper.Helpers;
import org.cyclops.cyclopscore.helper.L10NHelpers;
import org.cyclops.integrateddynamics.api.client.render.valuetype.IValueTypeWorldRenderer;
import org.cyclops.integrateddynamics.api.evaluate.variable.IValue;
import org.cyclops.integrateddynamics.api.ingredient.IIngredientComponentHandler;
import org.cyclops.integrateddynamics.api.part.IPartContainer;
import org.cyclops.integrateddynamics.api.part.IPartType;
import org.cyclops.integrateddynamics.client.render.part.DisplayPartOverlayRenderer;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueObjectTypeIngredients;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueObjectTypeRecipe;
import org.cyclops.integrateddynamics.core.evaluate.variable.ValueTypes;
import org.cyclops.integrateddynamics.core.ingredient.IngredientComponentHandlers;

import java.util.List;

/**
 * A value type world renderer for blocks.
 * @author rubensworks
 */
public class RecipeValueTypeWorldRenderer implements IValueTypeWorldRenderer {

    private static final IValueTypeWorldRenderer INGREDIENTS_RENDERER = ValueTypeWorldRenderers.REGISTRY
            .getRenderer(ValueTypes.OBJECT_INGREDIENTS);

    @Override
    public void renderValue(IPartContainer partContainer, double x, double y, double z, float partialTick,
                            int destroyStage, EnumFacing direction, IPartType partType, IValue value,
                            TileEntityRendererDispatcher rendererDispatcher, float alpha) {
        Optional<IRecipeDefinition> recipeOptional = ((ValueObjectTypeRecipe.ValueRecipe) value).getRawValue();
        if(recipeOptional.isPresent()) {
            IRecipeDefinition recipe = recipeOptional.get();

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5, 0.5, 1);

            GlStateManager.pushMatrix();
            GlStateManager.scale(0.3, 0.3, 1);
            rendererDispatcher.getFontRenderer().drawString(L10NHelpers.localize("gui.integrateddynamics.input_short"), 8, 15, Helpers.RGBToInt(255, 255, 255));
            rendererDispatcher.getFontRenderer().drawString(L10NHelpers.localize("gui.integrateddynamics.output_short"), 46, 15, Helpers.RGBToInt(255, 255, 255));
            GlStateManager.popMatrix();

            GlStateManager.translate(0, 2 * DisplayPartOverlayRenderer.MAX / 3, 0);
            renderInput(partContainer, x, y, z, partialTick,
                    destroyStage, direction, partType, recipe, rendererDispatcher, alpha);
            GlStateManager.translate(DisplayPartOverlayRenderer.MAX, 0, 0);
            INGREDIENTS_RENDERER.renderValue(partContainer, x, y, z, partialTick,
                    destroyStage, direction, partType, ValueObjectTypeIngredients.ValueIngredients.of(recipe.getOutput()), rendererDispatcher, alpha);

            GlStateManager.popMatrix();
        }
    }

    protected void renderInput(IPartContainer partContainer, double x, double y, double z, float partialTick,
                            int destroyStage, EnumFacing direction, IPartType partType, IRecipeDefinition recipe,
                            TileEntityRendererDispatcher rendererDispatcher, float alpha) {
        // Get a list of all values
        int ingredientCount = recipe.getInputComponents().stream().mapToInt((c) -> recipe.getInputs(c).size()).sum();
        List<IValue> values = Lists.newArrayListWithExpectedSize(ingredientCount);

        // For ingredients with multiple possibilities, vary them based on the current tick
        int tick = ((int) Minecraft.getMinecraft().world.getWorldTime()) / 30;
        for (IngredientComponent<?, ?> component : recipe.getInputComponents()) {
            IIngredientComponentHandler componentHandler = IngredientComponentHandlers.REGISTRY.getComponentHandler(component);
            recipe.getInputs(component).stream().forEach(element ->
                    values.add(componentHandler.toValue(IngredientsValueTypeWorldRenderer.prepareElementForTick(
                            element, tick, () -> new PrototypedIngredient(component, component.getEmptyInstance(), null)).getPrototype())));
        }

        // Render ingredients in a square matrix
        IngredientsValueTypeWorldRenderer.renderGrid(partContainer, x, y, z, partialTick, destroyStage, direction, partType, values, rendererDispatcher, alpha);
    }
}
