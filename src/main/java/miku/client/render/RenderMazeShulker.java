package miku.client.render;

import miku.entity.MazeShulker;
import net.minecraft.client.model.ModelShulker;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMazeShulker extends RenderLiving<MazeShulker> {
    protected final ResourceLocation texture = new ResourceLocation("maze", "textures/entities/maze_monster.png");
    public RenderMazeShulker(RenderManager rendermanager) {
        super(rendermanager, new ModelShulker(), 0.3f);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nullable MazeShulker entity) {
        return texture;
    }
}
