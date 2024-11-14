package khazenor.bubble_tea_delight.definitions;

import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemWrapper {
  public ItemWrapper(String itemId, String nameEnUs) {
    this.itemId = itemId;
    this.nameEnUs = nameEnUs;
  }
  public ItemWrapper() {}

  public String itemId;
  public String nameEnUs;
}
