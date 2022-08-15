package miku.world;

import miku.world.biome.MazeBiome;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;

public class MazeWorld {
    public static final int id = 123454321;

    public static DimensionType MazeDimensionType;

    public static Biome MazeBiome = new MazeBiome();

    public static void initialization() {
        MazeDimensionType = DimensionType.register("maze_world", "new_miku_world", id, MazeWorldProvider.class, false);
        DimensionManager.registerDimension(id, MazeDimensionType);
    }
}
