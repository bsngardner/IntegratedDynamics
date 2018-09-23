package org.cyclops.integrateddynamics.core.ingredient;

import org.cyclops.commoncapabilities.api.ingredient.IMixedIngredients;
import org.cyclops.commoncapabilities.api.ingredient.IngredientComponent;

import java.util.AbstractList;
import java.util.List;

/**
 * A lazy extension for ingredients.
 * @param <T> The instance type.
 * @param <R> The ingredient recipe target type, may be Void.
 * @param <M> The matching condition parameter, may be Void. Instances MUST properly implement the equals method.
 * @author rubensworks
 */
public class ExtendedIngredients<T, R, M> extends WrappedIngredients {

    private final int targetIndex;

    private final IngredientComponent<T, R, M> component;
    private final T instance;

    public ExtendedIngredients(IMixedIngredients base, int targetIndex,
                               IngredientComponent<T, R, M> component, T instance) {
        super(base);
        this.targetIndex = targetIndex;
        this.component = component;
        this.instance = instance;
    }

    protected boolean forComponent(IngredientComponent<?, ?, ?> component) {
        return component == this.component;
    }

    @Override
    public <T2> List<T2> getInstances(IngredientComponent<T2, ?, ?> ingredientComponent) {
        List<T2> superList = super.getInstances(ingredientComponent);
        return forComponent(ingredientComponent) ? new AbstractList<T2>() {
            @Override
            public T2 get(int index) {
                if (index == targetIndex) {
                    return (T2) instance;
                } else if (index < targetIndex && index >= superList.size()) {
                    return (T2) component.getEmptyInstance();
                }
                return superList.get(index);
            }

            @Override
            public int size() {
                int superSize = superList.size();
                if (targetIndex >= superSize) {
                    return targetIndex + 1;
                }
                return superSize;
            }
        } : superList;
    }
}
