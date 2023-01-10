package miku.item;

import miku.entity.MazeMonster;
import miku.lib.api.ProtectedEntity;
import miku.maze.Maze;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class MazeSword extends Item {
    public MazeSword(){
        this.setCreativeTab(Maze.MAZE_ITEM);
        this.setTranslationKey("maze_sword");
    }

    @Override
    public boolean onLeftClickEntity(@Nonnull ItemStack stack, @Nonnull EntityPlayer player, @Nonnull Entity entity){
        if(entity instanceof MazeMonster){
            ((ProtectedEntity)entity).Hurt(15);
        }
        return false;
    }
}
