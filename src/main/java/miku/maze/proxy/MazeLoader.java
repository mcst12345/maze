package miku.maze.proxy;

import miku.block.BlockLoader;
import miku.entity.EntityLoader;
import miku.item.ItemLoader;
import miku.world.MazeWorld;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class MazeLoader {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModel(ModelRegistryEvent event){
        EntityLoader.InitRender();
        ItemLoader.ClientInit();
    }

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event){
        ItemLoader.Init(event);
    }

    @SubscribeEvent
    public static void RegisterBlock(RegistryEvent.Register<Block> event){
        BlockLoader.Init(event);
    }

    @SubscribeEvent
    public void onRegisterBiomeEvent(RegistryEvent.Register<Biome> event) {
        Biome.registerBiome(12345, "maze:maze", MazeWorld.MazeBiome);
    }

    @SubscribeEvent
    public static void RegisterEntity(RegistryEvent.Register<EntityEntry> event){
        EntityLoader.Init(event);
    }
}
