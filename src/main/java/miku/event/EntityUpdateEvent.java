package miku.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EntityUpdateEvent {
    protected static boolean reset_spawn;

    @SubscribeEvent
    public void EntityUpdate(LivingEvent.LivingUpdateEvent event) {
        if ((event.getEntity()).world.isRemote) return;
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!reset_spawn && player.dimension == 123454321 && new Random().nextInt(250) == 25) {
                Entity mazeMonster = ItemMonsterPlacer.spawnCreature(player.world, new ResourceLocation("maze", "maze_monster"), player.posX, player.posY + 10.0D, player.posZ);
                if (mazeMonster != null)
                    player.world.spawnEntity(mazeMonster);
                reset_spawn = true;
                remove remove = new remove();
                remove.start();
            }
        }
    }

    static class remove extends Thread{
        @Override
        public void run(){
            try {
                sleep(300000);
                reset_spawn=false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
