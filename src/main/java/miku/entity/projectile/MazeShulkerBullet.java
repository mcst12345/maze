package miku.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class MazeShulkerBullet extends EntityShulkerBullet {
    public MazeShulkerBullet(World worldIn) {
        super(worldIn);
    }
    public MazeShulkerBullet(World worldIn, EntityLivingBase ownerIn, Entity targetIn, EnumFacing.Axis p_i46772_4_){
        super(worldIn, ownerIn, targetIn, p_i46772_4_);
    }

}
