package miku.world;

import miku.block.BlockLoader;
import miku.block.MazePortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;

public class MazeWorldTeleporter extends Teleporter {
    private final WorldServer worldServerInstance;

    public MazeWorldTeleporter(WorldServer world){
        super(world);
        this.worldServerInstance = world;
    }

    @Override
    public void placeInPortal(@Nonnull Entity entityIn, float rotationYaw) {
        if (!this.placeInExistingPortal(entityIn, rotationYaw)) {
            this.moveToSafeCoords(entityIn);
        }
    }

    private void moveToSafeCoords(Entity entity) {
        entity.posY=256;
        if(entity instanceof EntityLivingBase){
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,300,255,false,false));
        }
    }


}
