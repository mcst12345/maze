package miku.block;

import miku.lib.common.util.Register;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;

public class BlockLoader {
    public static final Block MazeBlock = new MazeBlock();
    public static final Block MazeTrap = new MazeTrap();
    public static final Block MazePortal = new MazePortal();
    public static final Block MazeShulkerSpawner = new MazeShulkerSpawner();

    public static void Init(RegistryEvent.Register<Block> event){
        Register.RegisterBlock(event,MazeBlock,"maze","maze_block");
        Register.RegisterBlock(event,MazePortal,"maze","maze_portal");
        Register.RegisterBlock(event,MazeTrap,"maze","maze_trap");
        Register.RegisterBlock(event,MazeShulkerSpawner,"maze","maze_shulker_spawner");
    }
}
