package miku.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityUpdateEvent {
    @SubscribeEvent
    public void EntityUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.getEntity().world.isRemote)return;
        EntityLivingBase entity = event.getEntityLiving();
        if(entity instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) entity;
            if(!player.isCreative()){
                if(player.posY>2)player.posY=2;
            }
        }
    }
}
