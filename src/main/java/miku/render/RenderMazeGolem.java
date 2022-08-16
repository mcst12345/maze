package miku.render;

import miku.entity.MazeGolem;
import miku.model.ModelMazeGolem;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMazeGolem extends RenderLiving<MazeGolem> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("maze", "textures/entities/maze_golem.png");

    public RenderMazeGolem(RenderManager rendermanager) {
        super(rendermanager, new ModelMazeGolem(), 0.3f);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nullable MazeGolem entity) {
        return TEXTURE;
    }
}
