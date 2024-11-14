package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.definitions.ItemWrapper;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisteredItems {
  public static DeferredItem<Item> getDeferredItem (String itemId) {
    return REGISTERED_ITEM_MAP.get(itemId);
  }
  public static DeferredItem<Item> getDeferredItem (ItemWrapper itemWrapper) {
    return REGISTERED_ITEM_MAP.get(itemWrapper.itemId);
  }

  public static void addItem (String itemId, DeferredItem<Item> deferredItem) {
    REGISTERED_ITEM_MAP.put(itemId, deferredItem);
  }

  public static ArrayList<DeferredItem<Item>> getAllRegisteredItems () {
    return new ArrayList<>(REGISTERED_ITEM_MAP.values());
  }
  private static final HashMap<String, DeferredItem<Item>> REGISTERED_ITEM_MAP = new HashMap<>();
}
