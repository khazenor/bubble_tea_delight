package khazenor.bubble_tea_delight.data_gen;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.definitions.Drink;
import khazenor.bubble_tea_delight.registry.RegisteredItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;

public class MyItemModelProvider extends ItemModelProvider {
  public MyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, BubbleTeaDelight.MODID, existingFileHelper);
  }

  public static final ArrayList<DeferredItem<Item>> BASIC_ITEMS = new ArrayList<>();

  @Override
  protected void registerModels() {
    String modelBase = "bubble_tea_delight:item";
    // register all drinks
    String drinkBase = "%s/drinks".formatted(modelBase);
    for(Drink drink: Drink.allDrinks()) {
      String milkKey = drink.hasCreamer()? "milk_": "";
      withExistingParent(drink.itemId, mcLoc("item/generated"))
        .texture("layer0", "%s/%stea/%s".formatted(drinkBase, milkKey, drink.tea))
        .texture("layer1", "%s/bubbles/%s".formatted(drinkBase, drink.bobaLevel))
        .texture("layer2", "%s/lid_sugar_level/%s".formatted(drinkBase, drink.sugarLevel))
        .texture("layer3", "%s/ice/%s".formatted(drinkBase, drink.iceLevel))
        .texture("layer4", "%s/rim".formatted(drinkBase));
    }
    // register all teas
    for (String teaId: Drink.TEAS) {
      withExistingParent(teaId, mcLoc("item/generated"))
        .texture("layer0", "%s/tea/%s".formatted(modelBase, teaId));
    }

    for (DeferredItem<Item> basicItem: BASIC_ITEMS) {
      basicItem(basicItem.get());
    }
  }
}