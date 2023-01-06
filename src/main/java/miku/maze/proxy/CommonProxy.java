package miku.maze.proxy;

import miku.event.BreakBlockEvent;
import miku.event.EntityDropEvent;
import miku.world.MazeWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public CommonProxy(){}

    public void preInit(FMLPreInitializationEvent event) {
        MazeWorld.initialization();
        MinecraftForge.EVENT_BUS.register(new BreakBlockEvent());
        MinecraftForge.EVENT_BUS.register(new EntityDropEvent());
    }

    public void init(FMLInitializationEvent event) {
    }
}
