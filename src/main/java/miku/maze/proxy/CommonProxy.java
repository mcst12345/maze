package miku.maze.proxy;

import miku.event.EntityUpdateEvent;
import miku.world.MazeWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public CommonProxy(){}

    public void preInit(FMLPreInitializationEvent event) {
        MazeWorld.initialization();
        MinecraftForge.EVENT_BUS.register(new EntityUpdateEvent());
    }

    public void init(FMLInitializationEvent event) {
    }
}
