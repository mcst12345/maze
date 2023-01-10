package miku.item;

import miku.block.BlockLoader;
import miku.block.MazePortal;
import miku.maze.Maze;
import miku.world.MazeWorldTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;

public class PortalCreator extends Item {
    public PortalCreator(){
        this.setMaxStackSize(1);
        this.canRepair=false;
        this.setCreativeTab(Maze.MAZE_ITEM);
        this.setTranslationKey("portal_creator");
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(@Nonnull EntityPlayer player, @Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!player.world.isRemote) {
            if(player.dimension==123454321){
                player.changeDimension(0, new MazeWorldTeleporter(FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(0)));
            }
            pos = pos.offset(facing);
            if(((MazePortal) BlockLoader.MazePortal).trySpawnPortal(worldIn, pos))return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
