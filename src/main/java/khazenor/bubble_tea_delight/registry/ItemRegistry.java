package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import khazenor.bubble_tea_delight.definitions.Drink;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {

  private String MODID;
  private DeferredRegister.Items ITEMS;
  private DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS;

  private List<DeferredItem<Item>> registeredItems = new ArrayList<>();
  private IEventBus modEventBus;

  public ItemRegistry (String MODID, IEventBus modEventBus) {
    this.MODID = MODID;
    this.ITEMS = DeferredRegister.createItems(this.MODID);
    this.CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, this.MODID);
    this.modEventBus = modEventBus;
  }
  public void register () {
    for(Drink drink: Drink.allDrinks()) {
      this.registeredItems.add(
        ITEMS.registerSimpleItem(
          drink.itemId(),
          new Item.Properties().food(
            new FoodProperties.Builder()
              .alwaysEdible()
              .nutrition(drink.nutrition())
              .saturationModifier(drink.saturationModifier())
              .build()
          )
        )
      );
    }
    this.ITEMS.register(this.modEventBus);
    this.registerCreativeTabs();
  }

  private void registerCreativeTabs() {
    this.CREATIVE_MODE_TABS.register(BubbleTeaDelight.MODID, () -> CreativeModeTab.builder()
      .title(Component.translatable("itemGroup.bubbleTeaDelight")) //The language key for the title of your CreativeModeTab
      .withTabsBefore(CreativeModeTabs.COMBAT)
      .icon(() -> this.registeredItems.getFirst().get().getDefaultInstance())
      .displayItems((parameters, output) -> {
        for (DeferredItem<Item> item: this.registeredItems) {
          output.accept(item.get());
        }
      }).build()
    );
    this.CREATIVE_MODE_TABS.register(modEventBus);

  }
}
