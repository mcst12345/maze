package miku.block;

import miku.maze.Maze;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MazeShulkerSpawner extends Block {
    public MazeShulkerSpawner(){
        super(Material.PORTAL);
        this.setCreativeTab(Maze.MAZE_BLOCK);
        this.setHardness(10.0f);
        this.setTranslationKey("maze_shulker_spawner");
    }
}
