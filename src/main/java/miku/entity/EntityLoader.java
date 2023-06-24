package miku.entity;

import miku.client.render.RenderMazeMonster;
import miku.client.render.RenderMazeShulker;
import miku.client.render.RenderMazeShulkerBullet;
import miku.entity.projectile.MazeShulkerBullet;
import miku.lib.util.Register;
import miku.maze.Maze;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
    public static void Init(RegistryEvent.Register<EntityEntry> event){
        int id = 0;
        Register.RegisterEntity("maze","maze_monster", MazeMonster.class,"Maze Monster",id++,Maze.INSTANCE);
        Register.RegisterEntity("maze","maze_shulker", MazeShulker.class,"Maze Shulker",id++,Maze.INSTANCE);
        Register.RegisterEntity("maze","maze_shulker_bullet", MazeShulkerBullet.class,"Maze Shulker Bullet",id++,Maze.INSTANCE);
    }

    @SideOnly(Side.CLIENT)
    public static void InitRender(){
        RenderingRegistry.registerEntityRenderingHandler(MazeMonster.class, RenderMazeMonster::new);
        RenderingRegistry.registerEntityRenderingHandler(MazeShulker.class, RenderMazeShulker::new);
        RenderingRegistry.registerEntityRenderingHandler(MazeShulkerBullet.class, RenderMazeShulkerBullet::new);
    }
}
