package miku.entity;

import miku.lib.api.ProtectedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MazeGolem extends EntityGolem implements ProtectedEntity {
    protected int health = 300;

    public MazeGolem(World worldIn) {
        super(worldIn);
        super.setSize(0.7F, 3.0F);
    }

    protected void initEntityAI(){
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 2.0D, true));
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 2.0D, 64.0F));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 2.0D));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityLiving.class, 10, false, true, p_apply_1_ -> !(p_apply_1_ instanceof MazeGolem)));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
    }

    @Override
    protected int decreaseAirSupply(int air)
    {
        return 0;
    }

    @Override
    protected void collideWithEntity(@Nonnull Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase && !(entityIn instanceof MazeGolem))
        {
            this.setAttackTarget((EntityLivingBase) entityIn);
        }

        super.collideWithEntity(entityIn);
    }

    @Override
    public boolean canAttackClass(@Nonnull Class <? extends EntityLivingBase > cls){
        return !MazeGolem.class.isAssignableFrom(cls);
    }

    public boolean attackEntityAsMob(Entity entityIn){
        entityIn.motionY += 0.4000000059604645D;
        if(entityIn instanceof EntityLivingBase){
            ((EntityLivingBase)entityIn).setHealth(((EntityLivingBase)entityIn).getHealth()-10.0F);
        }
        return true;
    }

    @Override
    public void onDeath(@Nonnull DamageSource cause){
        if(!(super.getHealth()>0))super.onDeath(cause);
    }

    @Override
    public void setHealth(float value){
        if(super.getHealth()-value<=20)super.setHealth(value);
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount){
        if(super.getHealth()-amount<=20)return super.attackEntityFrom(source,amount);
        return false;
    }

    @Override
    protected int getExperiencePoints(@Nullable EntityPlayer player){
        return 10000;
    }

    @Override
    public boolean CanBeKilled() {
        return true;
    }

    @Override
    public boolean DEAD() {
        return health<=0;
    }

    @Override
    public void SetHealth(int i) {
        health=i;
    }

    @Override
    public int GetHealth() {
        return health;
    }

    @Override
    public void Hurt(int i) {
        health -= i;
    }
}
