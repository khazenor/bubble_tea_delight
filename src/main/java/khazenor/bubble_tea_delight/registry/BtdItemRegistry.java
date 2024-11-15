package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.definitions.Drink;
import java.util.Map;

public class BtdItemRegistry {

  public static final String DRINK_TAB = "itemGroup.bubbleTeaDelightDrinks";

  public static final Map<String, String> CREATIVE_TAB_NAMES = Map.ofEntries(
    Map.entry(DRINK_TAB, "Bubble Tea Delight Drinks")
  );

  public static void register () {
    ItemRegistry itemRegistry = new ItemRegistry(
      BubbleTeaDelight.MODID,
      CREATIVE_TAB_NAMES,
      BubbleTeaDelight.modEventBus
    );

    // register drinks
    for (Drink drink: Drink.allDrinks()) {
      itemRegistry.registerFood(drink, DRINK_TAB);
    }

    itemRegistry.finalizeRegistration();
  }
}
