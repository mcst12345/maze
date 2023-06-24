package miku.entity;

import miku.client.render.RenderMazeMonster;
import miku.client.render.RenderMazeShulker;
import miku.client.render.RenderMazeShulkerBullet;
import miku.entity.projectile.MazeShulkerBullet;
import miku.lib.util.Register;
import miku.maze.Maze;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
    public static void Init(RegistryEvent.Register<EntityEntry> event){
        //Register.RegisterEntity(event,"maze","maze_monster","Maze Monster",1, MazeMonster.class);
        //Register.RegisterEntity(event,"maze","maze_shulker","Maze Shulker",1, MazeShulker.class);
        //Register.RegisterEntity(event,"maze","maze_shulker_bullet","Maze Shulker Bullet",1, MazeShulkerBullet.class);
        EntityRegistry.registerModEntity(new ResourceLocation("maze","maze_monster"), MazeMonster.class,"Maze Monster",0, Maze.INSTANCE,80,3,true);
        EntityRegistry.registerModEntity(new ResourceLocation("maze","maze_shulker"), MazeShulker.class,"Maze Shulker",1, Maze.INSTANCE,80,3,true);
        EntityRegistry.registerModEntity(new ResourceLocation("maze","maze_shulker_bullet"), MazeShulkerBullet.class,"Maze Shulker Bullet",2, Maze.INSTANCE,80,3,true);
    }

    @SideOnly(Side.CLIENT)
    public static void InitRender(){
        RenderingRegistry.registerEntityRenderingHandler(MazeMonster.class, RenderMazeMonster::new);
        RenderingRegistry.registerEntityRenderingHandler(MazeShulker.class, RenderMazeShulker::new);
        RenderingRegistry.registerEntityRenderingHandler(MazeShulkerBullet.class, RenderMazeShulkerBullet::new);
    }
}
