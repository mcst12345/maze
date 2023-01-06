package miku.item;

import jdk.nashorn.internal.ir.Block;
import miku.block.BlockLoader;
import miku.utils.RegisterUtil;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;

public class ItemLoader {
    public static final Item PortalCreator = new PortalCreator();
    public static final Item MazeSword = new MazeSword();

    public static void Init(RegistryEvent.Register<Item> event){
        RegisterUtil.RegisterItem(event, BlockLoader.MazeBlockItem,"maze_block");
        RegisterUtil.RegisterItem(event, BlockLoader.MazePortalItem,"maze_portal");
        RegisterUtil.RegisterItem(event , PortalCreator, "portal_creator");
        RegisterUtil.RegisterItem(event, MazeSword, "maze_sword");
        RegisterUtil.RegisterItem(event, BlockLoader.MazeTrapItem,"maze_trap");
    }

    public static void ClientInit(){
        RegisterUtil.RegisterItemModel(BlockLoader.MazeBlockItem);
        RegisterUtil.RegisterItemModel(BlockLoader.MazePortalItem);
        RegisterUtil.RegisterItemModel(PortalCreator);
        RegisterUtil.RegisterItemModel(MazeSword);
        RegisterUtil.RegisterItemModel(BlockLoader.MazeTrapItem);
    }
}
