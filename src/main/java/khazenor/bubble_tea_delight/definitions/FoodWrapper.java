package khazenor.bubble_tea_delight.definitions;

import net.minecraft.world.food.FoodProperties;
import net.neoforged.neoforge.common.extensions.IItemPropertiesExtensions;

import java.util.Properties;


public class FoodWrapper extends ItemWrapper {
  public FoodWrapper(String itemId, String nameEnUs, int nutrition, float saturationModifier) {
    super(itemId, nameEnUs);
    this.nutrition = nutrition;
    this.saturationModifier = saturationModifier;
  }
  public FoodWrapper() {
    super();
  }
  public int nutrition;
  public float saturationModifier;
}
