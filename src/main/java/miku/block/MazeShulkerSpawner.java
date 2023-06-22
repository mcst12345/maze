package miku.block;

import miku.entity.MazeShulker;
import miku.maze.Maze;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class MazeShulkerSpawner extends TrapBase<MazeShulker> {
    public MazeShulkerSpawner(){
        super(Material.PORTAL);
        this.setTranslationKey("maze_shulker_spawner");
    }
}
