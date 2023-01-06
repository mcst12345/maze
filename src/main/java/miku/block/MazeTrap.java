package miku.block;

import miku.entity.MazeMonster;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class MazeTrap extends Block {
    public MazeTrap() {
        super(Material.AIR);
        this.setHardness(10);
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nullable IBlockState state,@Nullable Random rand, int fortune){
        return ItemStack.EMPTY.getItem();
    }

    @Override
    public void onEntityWalk(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull Entity entityIn){
        if(entityIn.world.isRemote)return;
        MazeMonster entity = new MazeMonster(worldIn);
        entity.posX = pos.getX();
        entity.posY = pos.getY();
        entity.posZ = pos.getZ();
        entityIn.world.spawnEntity(entity);
        worldIn.setBlockState(pos,BlockLoader.MazeBlock.getDefaultState());
    }
}
