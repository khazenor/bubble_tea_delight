package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.data_gen.MyLanguageProvider;
import khazenor.bubble_tea_delight.data_structs.HashMapList;
import khazenor.bubble_tea_delight.definitions.FoodWrapper;
import khazenor.bubble_tea_delight.definitions.ItemWrapper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Map;

public class ItemRegistry {
  public void registerItem(ItemWrapper itemWrapper, String creativeTabNameTrans) {
    DeferredItem<Item> item = this.itemRegistry.registerSimpleItem(
      itemWrapper.itemId
    );
    postRegisterItem(itemWrapper, item, creativeTabNameTrans);
  }

  public void registerFood(FoodWrapper foodWrapper, String creativeTabNameTrans) {
    DeferredItem<Item> foodItem = this.itemRegistry.registerSimpleItem(
      foodWrapper.itemId,
      new Item.Properties().food(
        new FoodProperties.Builder()
          .nutrition(foodWrapper.nutrition)
          .saturationModifier(foodWrapper.saturationModifier)
          .build()
      )
    );
    this.postRegisterItem(foodWrapper, foodItem, creativeTabNameTrans);
  }

  public void finalizeRegistration () {
    this.registerCreativeTabs();
    this.itemRegistry.register(this.modEventBus);
    this.creativeTabRegistry.register(this.modEventBus);
  }

  public ItemRegistry (String MODID, Map<String, String> creativeTabNames, IEventBus modEventBus) {
    this.MODID = MODID;
    this.creativeTabRegistry =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    this.itemRegistry =
      DeferredRegister.createItems(MODID);
    this.modEventBus = modEventBus;

    for (String creativeTabNameTrans: creativeTabNames.keySet()) {
      MyLanguageProvider.defineTrans(
        creativeTabNameTrans,
        creativeTabNames.get(creativeTabNameTrans)
      );
    }
  }

  private String MODID;
  private DeferredRegister.Items itemRegistry;
  private DeferredRegister<CreativeModeTab> creativeTabRegistry;
  private IEventBus modEventBus;
  private final HashMapList<String, DeferredItem<Item>> creativeTabItems = new HashMapList<>();

  private void postRegisterItem(
    ItemWrapper itemWrapper,
    DeferredItem<Item> item,
    String creativeTabNameTrans
  ) {
    RegisteredItems.addItem(itemWrapper.itemId, item);
    MyLanguageProvider.defineItem(item, itemWrapper.nameEnUs);
    this.creativeTabItems.addToList(creativeTabNameTrans, item);
  }

  private void registerCreativeTabs() {
    for (String creativeTabNameTrans: this.creativeTabItems.keySet()) {
      ArrayList<DeferredItem<Item>> tabItems = creativeTabItems.get(creativeTabNameTrans);
      DeferredItem<Item> iconItem = tabItems.getFirst();

      this.creativeTabRegistry.register(this.MODID, () -> CreativeModeTab.builder()
        .title(Component.translatable(creativeTabNameTrans))
        .icon(() -> iconItem.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
          for (DeferredItem<Item> item: tabItems) {
            output.accept(item.get());
          }
        }).build()
      );
    }
  }
}
