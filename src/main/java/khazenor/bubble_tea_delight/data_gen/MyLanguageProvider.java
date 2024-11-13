package khazenor.bubble_tea_delight.data_gen;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.definitions.Drink;
import khazenor.bubble_tea_delight.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class MyLanguageProvider extends LanguageProvider {
  public MyLanguageProvider(PackOutput output) {
    super(
      output,
      BubbleTeaDelight.MODID,
      "en_us"
    );
  }

  @Override
  protected void addTranslations() {
    for (Drink drink: Drink.allDrinks()) {
      addItem(
        ItemRegistry.getDeferredItem(drink),
        drink.nameEnUs()
      );
    }
  }
}
