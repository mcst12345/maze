package miku.client.render;

import miku.entity.MazeShulker;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelShulker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class RenderMazeShulker extends RenderLiving<MazeShulker> {
    protected final ResourceLocation texture = new ResourceLocation("maze", "textures/entities/maze_shulker.png");
    public RenderMazeShulker(RenderManager p_i47194_1_)
    {
        super(p_i47194_1_, new ModelShulker(), 0.0F);
        this.addLayer(new RenderMazeShulker.HeadLayer());
    }

    @Nonnull
    public ModelShulker getMainModel()
    {
        return (ModelShulker)super.getMainModel();
    }

    public void doRender(MazeShulker entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        int i = entity.getClientTeleportInterp();

        if (i > 0 && entity.isAttachedToBlock())
        {
            BlockPos blockpos = entity.getAttachmentPos();
            BlockPos blockpos1 = entity.getOldAttachPos();
            double d0 = (double)((float)i - partialTicks) / 6.0D;
            d0 = d0 * d0;
            assert blockpos != null;
            double d1 = (double)(blockpos.getX() - blockpos1.getX()) * d0;
            double d2 = (double)(blockpos.getY() - blockpos1.getY()) * d0;
            double d3 = (double)(blockpos.getZ() - blockpos1.getZ()) * d0;
            super.doRender(entity, x - d1, y - d2, z - d3, entityYaw, partialTicks);
        }
        else
        {
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    public boolean shouldRender(@Nonnull MazeShulker livingEntity,@Nonnull ICamera camera, double camX, double camY, double camZ)
    {
        if (super.shouldRender(livingEntity, camera, camX, camY, camZ))
        {
            return true;
        }
        else
        {
            if (livingEntity.getClientTeleportInterp() > 0 && livingEntity.isAttachedToBlock())
            {
                BlockPos blockpos = livingEntity.getOldAttachPos();
                BlockPos blockpos1 = livingEntity.getAttachmentPos();
                assert blockpos1 != null;
                Vec3d vec3d = new Vec3d(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ());
                Vec3d vec3d1 = new Vec3d(blockpos.getX(), blockpos.getY(), blockpos.getZ());

                return camera.isBoundingBoxInFrustum(new AxisAlignedBB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z));
            }

            return false;
        }
    }

    protected ResourceLocation getEntityTexture(@Nonnull MazeShulker entity)
    {
        return texture;
    }

    protected void applyRotations(@Nonnull MazeShulker entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);

        switch (entityLiving.getAttachmentFacing())
        {
            case DOWN:
            default:
                break;
            case EAST:
                GlStateManager.translate(0.5F, 0.5F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case WEST:
                GlStateManager.translate(-0.5F, 0.5F, 0.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case NORTH:
                GlStateManager.translate(0.0F, 0.5F, -0.5F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case SOUTH:
                GlStateManager.translate(0.0F, 0.5F, 0.5F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case UP:
                GlStateManager.translate(0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        }
    }

    protected void preRenderCallback(@Nonnull MazeShulker entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(0.999F, 0.999F, 0.999F);
    }

    @SideOnly(Side.CLIENT)
    class HeadLayer implements LayerRenderer<MazeShulker>
    {
        private HeadLayer()
        {
        }

        public void doRenderLayer(MazeShulker entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
        {
            GlStateManager.pushMatrix();

            switch (entitylivingbaseIn.getAttachmentFacing())
            {
                case DOWN:
                default:
                    break;
                case EAST:
                    GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(1.0F, -1.0F, 0.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    break;
                case WEST:
                    GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(-1.0F, -1.0F, 0.0F);
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                    break;
                case NORTH:
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0F, -1.0F, -1.0F);
                    break;
                case SOUTH:
                    GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0F, -1.0F, 1.0F);
                    break;
                case UP:
                    GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
                    GlStateManager.translate(0.0F, -2.0F, 0.0F);
            }

            ModelRenderer modelrenderer = RenderMazeShulker.this.getMainModel().head;
            modelrenderer.rotateAngleY = netHeadYaw * 0.017453292F;
            modelrenderer.rotateAngleX = headPitch * 0.017453292F;
            RenderMazeShulker.this.bindTexture(texture);
            modelrenderer.render(scale);
            GlStateManager.popMatrix();
        }

        public boolean shouldCombineTextures()
        {
            return false;
        }
    }
}
