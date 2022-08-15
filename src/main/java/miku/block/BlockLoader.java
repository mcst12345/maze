package miku.block;

import miku.utils.RegisterUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class BlockLoader {
    public static final Block MazeBlock = new MazeBlock(Material.BARRIER);
    public static final ItemBlock MazeBlockItem = new ItemBlock(MazeBlock);

    public static final Block MazePortal = new MazePortal();
    public static final ItemBlock MazePortalItem = new ItemBlock(MazePortal);

    public static void Init(RegistryEvent.Register<Block> event){
        RegisterUtil.RegisterBlock(event,MazeBlock,"maze_block");
        RegisterUtil.RegisterBlock(event,MazePortal,"maze_portal");
    }
}
