package dev.weexnes.esp;

import dev.weexnes.esp.util.ESP;
import dev.weexnes.esp.util.EntityRenderHook;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Sneaky.MODID, version = Sneaky.VERSION)
public class Sneaky
{
    public static final String MODID = "sneaky";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new ESP());
        MinecraftForge.EVENT_BUS.register(new EntityRenderHook());
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
