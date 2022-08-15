package miku.maze;

import miku.item.ItemLoader;
import miku.maze.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.annotation.Nonnull;

@Mod(
        modid = Maze.MOD_ID,
        name = Maze.MOD_NAME,
        version = Maze.VERSION
)
public class Maze {

    public static final String MOD_ID = "maze";
    public static final String MOD_NAME = "Maze";
    public static final String VERSION = "1.0";

    @SidedProxy(
            clientSide = "miku.maze.proxy.ClientProxy",
            serverSide = "miku.maze.proxy.CommonProxy"
    )
    public static CommonProxy proxy;


    @Mod.Instance(MOD_ID)
    public static Maze INSTANCE;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static final CreativeTabs MAZE_TAB = new CreativeTabs("maze") {
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(ItemLoader.PortalCreator);
        }
    };

}
