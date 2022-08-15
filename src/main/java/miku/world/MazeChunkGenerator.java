package miku.world;

import miku.block.BlockLoader;
import miku.utils.Maze;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MazeChunkGenerator implements IChunkGenerator {
    public MazeChunkGenerator(World world){
        this.world=world;
    }

    private final World world;

    @Override
    @Nonnull
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkPrimer = new ChunkPrimer();
        setBlocksInChunk(chunkPrimer);

        Chunk chunk = new Chunk(this.world, chunkPrimer, x, z);
        chunk.generateSkylightMap();

        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(@Nullable Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    @Nonnull
    public List<Biome.SpawnListEntry> getPossibleCreatures(@Nonnull EnumCreatureType creatureType,@Nonnull BlockPos pos) {
        Biome Biome = world.getBiome(pos);
        return Biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(@Nullable World worldIn,@Nullable String structureName,@Nullable BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(@Nullable Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(@Nullable World worldIn,@Nullable String structureName,@Nullable BlockPos pos) {
        return false;
    }

    public void setBlocksInChunk(ChunkPrimer chunkPrimer){
        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                chunkPrimer.setBlockState(j, 0, k, Blocks.AIR.getDefaultState());
            }
        }
        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                chunkPrimer.setBlockState(j, 1, k, BlockLoader.MazeBlock.getDefaultState());
            }
        }
        Maze Maze = new Maze();
        Maze.init();
        Maze.makeMaze();
        boolean[][] maze = Maze.maze;
        for(int i = 1 ; i<=16;i++){
            for(int j=1;j<=16;j++){
                if(maze[i][j]){
                    chunkPrimer.setBlockState(i-1, 2, j-1, BlockLoader.MazeBlock.getDefaultState());
                    chunkPrimer.setBlockState(i-1, 3, j-1, BlockLoader.MazeBlock.getDefaultState());
                    chunkPrimer.setBlockState(i-1, 4, j-1, BlockLoader.MazeBlock.getDefaultState());
                    chunkPrimer.setBlockState(i-1, 5, j-1, BlockLoader.MazeBlock.getDefaultState());
                    chunkPrimer.setBlockState(i-1, 6, j-1, BlockLoader.MazeBlock.getDefaultState());
                }
            }
        }
        chunkPrimer.setBlockState(0, 2, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(0, 3, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(0, 4, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(0, 5, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(0, 6, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(15, 2, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(15, 3, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(15, 4, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(15, 5, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(15, 6, 7, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 2, 0, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 3, 0, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 4, 0, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 5, 0, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 6, 0, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 2, 15, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 3, 15, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 4, 15, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 5, 15, Blocks.AIR.getDefaultState());
        chunkPrimer.setBlockState(8, 6, 15, Blocks.AIR.getDefaultState());
    }
}
