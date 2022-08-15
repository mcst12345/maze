package miku.event;

import miku.block.BlockLoader;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreakBlockEvent {
    @SubscribeEvent
    public void BreakBlock(BlockEvent.BreakEvent event){
        if(!event.getPlayer().world.isRemote){
            if(event.getPlayer().world.getBlockState(event.getPos()).getBlock() == BlockLoader.MazeBlock)event.setCanceled(true);
        }
    }
}
