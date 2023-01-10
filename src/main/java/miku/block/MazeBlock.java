package miku.block;

import miku.maze.Maze;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MazeBlock extends Block {

    public MazeBlock() {
        super(Material.BARRIER);
        this.setHardness(-Float.MAX_VALUE);
        this.setCreativeTab(Maze.MAZE_BLOCK);
        this.setTranslationKey("maze_block");
    }

    @Override
    public void harvestBlock(@Nullable World worldIn,@Nullable EntityPlayer player,@Nullable BlockPos pos,@Nullable IBlockState state, @Nullable TileEntity te,@Nullable ItemStack stack){}

    @Override
    public void onEntityWalk(@Nullable World worldIn,@Nullable BlockPos pos,@Nonnull Entity entityIn)
    {
        if(entityIn.world.isRemote)return;
        if(entityIn instanceof EntityLivingBase){
            EntityLivingBase entity = (EntityLivingBase) entityIn;
            entity.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.SPEED,10,5)));
        }
    }


}
