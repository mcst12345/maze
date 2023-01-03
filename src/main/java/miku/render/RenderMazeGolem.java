package miku.render;

import miku.entity.MazeMonster;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMazeGolem extends RenderLiving<MazeMonster> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("maze", "textures/entities/maze_monster.png");

    public RenderMazeGolem(RenderManager rendermanager) {
        super(rendermanager, new ModelZombie(), 0.3f);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nullable MazeMonster entity) {
        return TEXTURE;
    }
}
