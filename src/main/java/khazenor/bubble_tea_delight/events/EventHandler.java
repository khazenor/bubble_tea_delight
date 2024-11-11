package khazenor.bubble_tea_delight.events;

import khazenor.bubble_tea_delight.registry.MyItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class EventHandler {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    System.out.println("gatherData");
    DataGenerator generator = event.getGenerator();
    PackOutput output = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

    // other providers here
    generator.addProvider(
      event.includeClient(),
      new MyItemModelProvider(output, existingFileHelper)
    );
  }
}
