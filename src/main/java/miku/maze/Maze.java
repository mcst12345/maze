package miku.maze;

import miku.block.BlockLoader;
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
        version = Maze.VERSION,
        dependencies = "required-after:mikulib@[1.11,)"
)
public class Maze {

    public static final String MOD_ID = "maze";
    public static final String MOD_NAME = "Maze";
    public static final String VERSION = "1.6";

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

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    public static final CreativeTabs MAZE_ITEM = new CreativeTabs("maze_item") {
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(ItemLoader.PortalCreator);
        }
    };

    public static final CreativeTabs MAZE_BLOCK = new CreativeTabs("maze_block") {
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(BlockLoader.MazeBlock);
        }
    };
}
