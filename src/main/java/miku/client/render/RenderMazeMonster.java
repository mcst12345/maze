package miku.client.render;

import miku.entity.MazeMonster;
import miku.maze.Maze;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderMazeMonster extends RenderLiving<MazeMonster> {
    protected final ResourceLocation texture = new ResourceLocation("maze", "textures/entities/maze_monster.png");
    public RenderMazeMonster(RenderManager rendermanager) {
        super(rendermanager, new ModelZombie(), 0.3f);
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nullable MazeMonster entity) {
        return texture;
    }
}
