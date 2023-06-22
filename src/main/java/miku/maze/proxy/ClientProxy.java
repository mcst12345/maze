package miku.maze.proxy;

import miku.item.ItemLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    public ClientProxy(){}

    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ItemLoader.ClientInit();
    }

    public void init(FMLInitializationEvent event){
        super.init(event);
    }
}
