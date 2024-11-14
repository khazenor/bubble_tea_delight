package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.definitions.Drink;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemRegistry {

  private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
    DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BubbleTeaDelight.MODID);
  private static final HashMap<String, DeferredItem<Item>> registeredItemMap = new HashMap<>();
  private static final HashMap<DeferredItem<Item>, String> itemNameMap = new HashMap<>();
  private static final DeferredRegister.Items ITEMS_REGISTER =
    DeferredRegister.createItems(BubbleTeaDelight.MODID);

  public static DeferredItem<Item> getDeferredItem (String itemId) {
    return registeredItemMap.get(itemId);
  }

  public static DeferredItem<Item> getDeferredItem (Drink drink) {
    return getDeferredItem(drink.itemId);
  }

  public static String getItemEnUs (DeferredItem<Item> item) {
    return itemNameMap.get(item);
  }

  public static void register () {
    registerDrinks();

    ITEMS_REGISTER.register(BubbleTeaDelight.modEventBus);
    registerCreativeTabs();
  }

  private static void registerDrinks () {
    for(Drink drink: Drink.allDrinks()) {
      DeferredItem<Item> drinkItem = ITEMS_REGISTER.registerSimpleItem(
        drink.itemId,
        new Item.Properties().food(
          new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(drink.nutrition)
            .saturationModifier(drink.saturationModifier)
            .build()
        )
      );

      registeredItemMap.put(
        drink.itemId,
        drinkItem
      );

      itemNameMap.put(
        drinkItem,
        drink.nameEnUs
      );
    }
  }

  private static void registerCreativeTabs() {
    ArrayList<DeferredItem<Item>> registeredItemList = new ArrayList<>(registeredItemMap.values());

    CREATIVE_MODE_TABS.register(BubbleTeaDelight.MODID, () -> CreativeModeTab.builder()
      .title(Component.translatable("itemGroup.bubbleTeaDelight")) //The language key for the title of your CreativeModeTab
      .withTabsBefore(CreativeModeTabs.COMBAT)
      .icon(() -> registeredItemList.getFirst().get().getDefaultInstance())
      .displayItems((parameters, output) -> {
        for (DeferredItem<Item> item: registeredItemList) {
          output.accept(item.get());
        }
      }).build()
    );
    CREATIVE_MODE_TABS.register(BubbleTeaDelight.modEventBus);

  }
}
