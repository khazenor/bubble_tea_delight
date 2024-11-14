package khazenor.bubble_tea_delight.data_gen;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.definitions.Drink;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MyItemModelProvider extends ItemModelProvider {
  public MyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, BubbleTeaDelight.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    String modelBase = "bubble_tea_delight:item/drinks";
    for(Drink drink: Drink.allDrinks()) {
      String milkKey = drink.hasCreamer()? "milk_": "";
      withExistingParent(drink.itemId, mcLoc("item/generated"))
        .texture("layer0", "%s/%stea/%s".formatted(modelBase, milkKey, drink.tea))
        .texture("layer1", "%s/bubbles/%s".formatted(modelBase, drink.bobaLevel))
        .texture("layer2", "%s/lid_sugar_level/%s".formatted(modelBase, drink.sugarLevel))
        .texture("layer3", "%s/ice/%s".formatted(modelBase, drink.iceLevel))
        .texture("layer4", "%s/rim".formatted(modelBase));
    }
  }
}