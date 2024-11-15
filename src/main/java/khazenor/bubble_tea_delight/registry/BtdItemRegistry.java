package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.data_gen.MyItemModelProvider;
import khazenor.bubble_tea_delight.definitions.Drink;
import org.codehaus.plexus.util.StringUtils;

import java.util.Map;

public class BtdItemRegistry {

  public static final String DRINK_TAB = "itemGroup.bubbleTeaDelightDrinks";
  public static final String OTHER_ITEMS_TAB = "itemGroup.bubbleTeaDelight";

  public static final Map<String, String> CREATIVE_TAB_NAMES = Map.ofEntries(
    Map.entry(DRINK_TAB, "Bubble Tea Delight Drinks"),
    Map.entry(OTHER_ITEMS_TAB, "Bubble Tea Delight")
  );

  public static final String[] OTHER_ITEMS = {
    "drink_cup",
    "rolled_dough",
    "dough_balls",
    "brown_sugar",
    "bubble_pearls",
    "rolling_pin"
  };

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

    // register tea leaves
    for (String teaId: Drink.TEAS) {
      itemRegistry.registerItem(
        teaId,
        StringUtils.capitaliseAllWords(Drink.TEA_NAMES.get(teaId)) + " Tea",
        OTHER_ITEMS_TAB
      );
    }

    // register simple items in the other tab
    for (String itemId: OTHER_ITEMS) {
      itemRegistry.registerItemWithDefaultName(itemId, OTHER_ITEMS_TAB);
      MyItemModelProvider.BASIC_ITEMS.add(RegisteredItems.getDeferredItem(itemId));
    }

    itemRegistry.finalizeRegistration();
  }
}
