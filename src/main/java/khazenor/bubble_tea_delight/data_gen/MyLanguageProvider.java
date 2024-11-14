package khazenor.bubble_tea_delight.data_gen;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.HashMap;

public class MyLanguageProvider extends LanguageProvider {
  public MyLanguageProvider(PackOutput output) {
    super(
      output,
      BubbleTeaDelight.MODID,
      "en_us"
    );
  }

  public static void defineItem(DeferredItem<Item> item, String name) {
    ITEM_NAMES.put(item, name);
  }

  public static void defineTrans(String key, String val) {
    TRANS.put(key, val);
  }

  @Override
  protected void addTranslations() {
    for (DeferredItem<Item> item: ITEM_NAMES.keySet()) {
      addItem(item, ITEM_NAMES.get(item));
    }
    for (String transKey: TRANS.keySet()) {
      add(transKey, TRANS.get(transKey));
    }
  }

  private static final HashMap<DeferredItem<Item>, String> ITEM_NAMES = new HashMap<>();
  private static final HashMap<String, String> TRANS = new HashMap<>();
}
