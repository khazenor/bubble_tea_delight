package khazenor.bubble_tea_delight;

import khazenor.bubble_tea_delight.events.EventHandler;
import khazenor.bubble_tea_delight.registry.ItemRegistry;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BubbleTeaDelight.MODID)
public class BubbleTeaDelight
{
    public static final String MODID = "bubble_tea_delight";

    public BubbleTeaDelight(IEventBus modEventBus)
    {
      ItemRegistry itemRegistry = new ItemRegistry(MODID, modEventBus);
      itemRegistry.register();
      modEventBus.register(EventHandler.class);
    }
}
