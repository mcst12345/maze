package miku.entity;

import miku.entity.projectile.MazeShulkerBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MazeShulker extends EntityShulker implements IMob {

    public MazeShulker(World worldIn)
    {
        super(worldIn);
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new MazeShulker.AIAttack());
        this.tasks.addTask(7, new MazeShulker.AIPeek());
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new MazeShulker.AIAttackNearest(this));
        this.targetTasks.addTask(3, new MazeShulker.AIDefenseAttack(this));
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return null;
        //return LootTableList.ENTITIES_SHULKER;
    }

    class AIAttack extends EntityAIBase
    {
        private int attackTime;

        public AIAttack()
        {
            this.setMutexBits(3);
        }

        public boolean shouldExecute()
        {
            EntityLivingBase entitylivingbase = MazeShulker.this.getAttackTarget();

            if (entitylivingbase != null && entitylivingbase.isEntityAlive())
            {
                return MazeShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL;
            }
            else
            {
                return false;
            }
        }

        public void startExecuting()
        {
            this.attackTime = 20;
            MazeShulker.this.updateArmorModifier(100);
        }

        public void resetTask()
        {
            MazeShulker.this.updateArmorModifier(0);
        }

        public void updateTask()
        {
            if (MazeShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
            {
                --this.attackTime;
                EntityLivingBase entitylivingbase = MazeShulker.this.getAttackTarget();
                if (entitylivingbase != null) {
                    MazeShulker.this.getLookHelper().setLookPositionWithEntity(entitylivingbase, 180.0F, 180.0F);
                }
                double d0 = 0;
                if (entitylivingbase != null) {
                    d0 = MazeShulker.this.getDistanceSq(entitylivingbase);
                }

                if (d0 < 400.0D)
                {
                    if (this.attackTime <= 0)
                    {
                        this.attackTime = 20 + MazeShulker.this.rand.nextInt(10) * 20 / 2;
                        MazeShulkerBullet entityshulkerbullet = new MazeShulkerBullet(MazeShulker.this.world, MazeShulker.this, entitylivingbase, MazeShulker.this.getAttachmentFacing().getAxis());
                        if (entitylivingbase != null) {
                            MazeShulker.this.world.spawnEntity(entityshulkerbullet);
                        }
                        MazeShulker.this.playSound(SoundEvents.ENTITY_SHULKER_SHOOT, 2.0F, (MazeShulker.this.rand.nextFloat() - MazeShulker.this.rand.nextFloat()) * 0.2F + 1.0F);
                    }
                }
                else
                {
                    MazeShulker.this.setAttackTarget(null);
                }

                super.updateTask();
            }
        }
    }

    class AIAttackNearest extends EntityAINearestAttackableTarget<EntityPlayer>
    {
        public AIAttackNearest(MazeShulker shulker)
        {
            super(shulker, EntityPlayer.class, true);
        }

        public boolean shouldExecute()
        {
            return MazeShulker.this.world.getDifficulty() != EnumDifficulty.PEACEFUL && super.shouldExecute();
        }

        @Nonnull
        protected AxisAlignedBB getTargetableArea(double targetDistance)
        {
            EnumFacing enumfacing = ((MazeShulker)this.taskOwner).getAttachmentFacing();

            if (enumfacing.getAxis() == EnumFacing.Axis.X)
            {
                return this.taskOwner.getEntityBoundingBox().grow(4.0D, targetDistance, targetDistance);
            }
            else
            {
                return enumfacing.getAxis() == EnumFacing.Axis.Z ? this.taskOwner.getEntityBoundingBox().grow(targetDistance, targetDistance, 4.0D) : this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
            }
        }
    }

    static class AIDefenseAttack extends EntityAINearestAttackableTarget<EntityLivingBase>
    {
        public AIDefenseAttack(MazeShulker shulker)
        {
            super(shulker, EntityLivingBase.class, 10, true, false, p_apply_1_ -> p_apply_1_ instanceof IMob);
        }

        public boolean shouldExecute()
        {
            return this.taskOwner.getTeam() != null && super.shouldExecute();
        }

        @Nonnull
        protected AxisAlignedBB getTargetableArea(double targetDistance)
        {
            EnumFacing enumfacing = ((MazeShulker)this.taskOwner).getAttachmentFacing();

            if (enumfacing.getAxis() == EnumFacing.Axis.X)
            {
                return this.taskOwner.getEntityBoundingBox().grow(4.0D, targetDistance, targetDistance);
            }
            else
            {
                return enumfacing.getAxis() == EnumFacing.Axis.Z ? this.taskOwner.getEntityBoundingBox().grow(targetDistance, targetDistance, 4.0D) : this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0D, targetDistance);
            }
        }
    }

    class AIPeek extends EntityAIBase
    {
        private int peekTime;

        private AIPeek()
        {
        }

        public boolean shouldExecute()
        {
            return MazeShulker.this.getAttackTarget() == null && MazeShulker.this.rand.nextInt(40) == 0;
        }

        public boolean shouldContinueExecuting()
        {
            return MazeShulker.this.getAttackTarget() == null && this.peekTime > 0;
        }

        public void startExecuting()
        {
            this.peekTime = 20 * (1 + MazeShulker.this.rand.nextInt(3));
            MazeShulker.this.updateArmorModifier(30);
        }

        public void resetTask()
        {
            if (MazeShulker.this.getAttackTarget() == null)
            {
                MazeShulker.this.updateArmorModifier(0);
            }
        }

        public void updateTask()
        {
            --this.peekTime;
        }
    }

}
