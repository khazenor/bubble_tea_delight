package khazenor.bubble_tea_delight;

import khazenor.bubble_tea_delight.events.EventHandler;
import khazenor.bubble_tea_delight.registry.ItemRegistry;
import khazenor.bubble_tea_delight.registry.MyItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BubbleTeaDelight.MODID)
public class BubbleTeaDelight
{
    public static final String MODID = "bubble_tea_delight";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BubbleTeaDelight(IEventBus modEventBus)
    {
      ItemRegistry itemRegistry = new ItemRegistry(MODID, modEventBus);
      itemRegistry.register();
      //modEventBus.register(EventHandler.class);
      modEventBus.addListener(EventHandler::gatherData);
      System.out.println("BubbleTeaDelightClass");
    }
}
