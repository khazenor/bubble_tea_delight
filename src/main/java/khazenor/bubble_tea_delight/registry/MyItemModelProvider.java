package khazenor.bubble_tea_delight.registry;

import khazenor.bubble_tea_delight.BubbleTeaDelight;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class MyItemModelProvider extends ItemModelProvider {
  public MyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, BubbleTeaDelight.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
  }
}