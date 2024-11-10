package khazenor.bubble_tea_delight;

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
    this.registeredItems.add(
      ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
        .alwaysEdible().nutrition(1).saturationModifier(2f).build()))
    );
    this.ITEMS.register(this.modEventBus);

    for (DeferredItem<Item> item: this.registeredItems) {
      CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
              .title(Component.translatable("itemGroup.examplemod")) //The language key for the title of your CreativeModeTab
              .withTabsBefore(CreativeModeTabs.COMBAT)
              .icon(() -> item.get().getDefaultInstance())
              .displayItems((parameters, output) -> {
                output.accept(item.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
              }).build());
      CREATIVE_MODE_TABS.register(modEventBus);
    }
  }
}
