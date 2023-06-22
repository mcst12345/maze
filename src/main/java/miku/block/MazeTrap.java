package miku.block;

import miku.entity.MazeMonster;
import miku.maze.Maze;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class MazeTrap extends TrapBase<MazeMonster> {
    public MazeTrap() {
        super(Material.AIR);
        this.setTranslationKey("maze_trap");
    }
}
