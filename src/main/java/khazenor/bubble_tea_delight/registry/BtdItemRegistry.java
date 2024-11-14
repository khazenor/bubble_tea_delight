package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.data_gen.MyLanguageProvider;
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

public class BtdItemRegistry {

  private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
    DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BubbleTeaDelight.MODID);
  private static final DeferredRegister.Items ITEMS_REGISTER =
    DeferredRegister.createItems(BubbleTeaDelight.MODID);

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

      RegisteredItems.addItem(drink.itemId, drinkItem);

      MyLanguageProvider.defineItem(drinkItem, drink.nameEnUs);
    }
  }

  private static void registerCreativeTabs() {
    ArrayList<DeferredItem<Item>> registeredItemList = RegisteredItems.getAllRegisteredItems();

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
