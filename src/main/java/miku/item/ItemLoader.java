package miku.item;

import miku.block.BlockLoader;
import miku.utils.RegisterUtil;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ItemLoader {
    public static final Item PortalCreator = new PortalCreator();
    public static void Init(RegistryEvent.Register<Item> event){
        RegisterUtil.RegisterItem(event, BlockLoader.MazeBlockItem,"maze_block");
        RegisterUtil.RegisterItem(event, BlockLoader.MazePortalItem,"maze_portal");
        RegisterUtil.RegisterItem(event , PortalCreator, "portal_creator");
    }

    public static void ClientInit(){
        RegisterUtil.RegisterItemModel(BlockLoader.MazeBlockItem);
        RegisterUtil.RegisterItemModel(BlockLoader.MazePortalItem);
        RegisterUtil.RegisterItemModel(PortalCreator);
    }
}
