package miku.block;

import miku.entity.MazeShulker;
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

public class MazeShulkerSpawner extends Block {
    public MazeShulkerSpawner(){
        super(Material.PORTAL);
        this.setCreativeTab(Maze.MAZE_BLOCK);
        this.setHardness(10.0f);
        this.setTranslationKey("maze_shulker_spawner");
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nullable IBlockState state, @Nullable Random rand, int fortune){
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public void onEntityWalk(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Entity entityIn){
        if(entityIn.world.isRemote)return;
        MazeShulker entity = new MazeShulker(worldIn);
        entity.setLocationAndAngles(pos.getX(),pos.getY(),pos.getZ(), MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
        entity.rotationYawHead = entity.rotationYaw;
        entity.renderYawOffset = entity.rotationYaw;
        entity.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entity)), null);
        entityIn.world.spawnEntity(entity);
        worldIn.setBlockState(pos,BlockLoader.MazeBlock.getDefaultState());
    }
}
