package miku.entity;

import miku.client.render.RenderMazeMonster;
import miku.client.render.RenderMazeShulker;
import miku.client.render.RenderMazeShulkerBullet;
import miku.entity.projectile.MazeShulkerBullet;
import miku.lib.util.Register;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
    public static void Init(RegistryEvent.Register<EntityEntry> event){
        Register.RegisterEntity(event,"maze","maze_monster","Maze Monster",1, MazeMonster.class);
        Register.RegisterEntity(event,"maze","maze_shulker","Maze Shulker",1, MazeShulker.class);
        Register.RegisterEntity(event,"maze","maze_shulker_bullet","Maze Shulker Bullet",1, MazeShulkerBullet.class);
    }

    @SideOnly(Side.CLIENT)
    public static void InitRender(){
        RenderingRegistry.registerEntityRenderingHandler(MazeMonster.class, RenderMazeMonster::new);
        RenderingRegistry.registerEntityRenderingHandler(MazeShulker.class, RenderMazeShulker::new);
        RenderingRegistry.registerEntityRenderingHandler(MazeShulkerBullet.class, RenderMazeShulkerBullet::new);
    }
}