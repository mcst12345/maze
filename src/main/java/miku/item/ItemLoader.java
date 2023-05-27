package miku.item;

import miku.block.BlockLoader;
import miku.lib.util.Register;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ItemLoader {
    public static final Item PortalCreator = new PortalCreator();
    public static final Item MazeSword = new MazeSword();

    public static void Init(RegistryEvent.Register<Item> event){
        Register.RegisterItem(event, BlockLoader.MazeBlockItem,"maze","maze_block");
        Register.RegisterItem(event, BlockLoader.MazePortalItem,"maze","maze_portal");
        Register.RegisterItem(event , PortalCreator,"maze", "portal_creator");
        Register.RegisterItem(event, MazeSword,"maze", "maze_sword");
        Register.RegisterItem(event, BlockLoader.MazeTrapItem,"maze","maze_trap");
    }

    public static void ClientInit(){
        Register.RegisterItemModel(BlockLoader.MazeBlockItem);
        Register.RegisterItemModel(BlockLoader.MazePortalItem);
        Register.RegisterItemModel(PortalCreator);
        Register.RegisterItemModel(MazeSword);
        Register.RegisterItemModel(BlockLoader.MazeTrapItem);
    }
}
