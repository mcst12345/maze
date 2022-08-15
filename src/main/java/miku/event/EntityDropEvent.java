package miku.event;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EntityDropEvent {
    @SubscribeEvent
    public void EntityDrop(LivingDropsEvent event){
        if(event.getEntityLiving() != null&&!event.getEntityLiving().world.isRemote&&event.getEntityLiving().dimension==123454321){
            EntityLivingBase entity = event.getEntityLiving();
            final int amount = new Random().nextInt(3);
            event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.STONEBRICK, amount)));
        }
    }
}
