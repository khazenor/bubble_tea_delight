package khazenor.bubble_tea_delight.events;

import khazenor.bubble_tea_delight.data_gen.MyItemModelProvider;
import khazenor.bubble_tea_delight.data_gen.MyLanguageProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class EventHandler {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput output = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

    DataProvider[] dataProviders = {
      new MyItemModelProvider(output, existingFileHelper),
      new MyLanguageProvider(output)
    };


    for (DataProvider dataProvider: dataProviders) {
      generator.addProvider(
        event.includeClient(),
        dataProvider
      );
    }
  }
}
