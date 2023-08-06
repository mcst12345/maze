package miku.item;

import miku.block.BlockLoader;
import miku.lib.common.util.Register;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class ItemLoader {
    public static final Item PortalCreator = new PortalCreator();
    public static final Item MazeSword = new MazeSword();
    public static final ItemBlock MazeBlockItem = new ItemBlock(BlockLoader.MazeBlock);
    public static final ItemBlock MazeTrapItem = new ItemBlock(BlockLoader.MazeTrap);
    public static final ItemBlock MazePortalItem = new ItemBlock(BlockLoader.MazePortal);
    public static final ItemBlock MazeShulkerSpawnerItem = new ItemBlock(BlockLoader.MazeShulkerSpawner);

    public static void Init(RegistryEvent.Register<Item> event){
        Register.RegisterItem(event, MazeBlockItem,"maze","maze_block");
        Register.RegisterItem(event, MazePortalItem,"maze","maze_portal");
        Register.RegisterItem(event , PortalCreator,"maze", "portal_creator");
        Register.RegisterItem(event, MazeSword,"maze", "maze_sword");
        Register.RegisterItem(event, MazeTrapItem,"maze","maze_trap");
        Register.RegisterItem(event,MazeShulkerSpawnerItem,"maze","maze_shulker_spawner");
    }

    public static void ClientInit(){
        Register.RegisterItemModel(MazeBlockItem);
        Register.RegisterItemModel(MazePortalItem);
        Register.RegisterItemModel(PortalCreator);
        Register.RegisterItemModel(MazeSword);
        Register.RegisterItemModel(MazeTrapItem);
        Register.RegisterItemModel(MazeShulkerSpawnerItem);
    }
}
