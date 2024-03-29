package miku.world;

import miku.block.BlockLoader;
import miku.utils.Maze1;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MazeChunkGenerator implements IChunkGenerator {
    public MazeChunkGenerator(World world){
        this.world=world;
        this.rand = new Random(world.getSeed());
    }

    private final World world;

    private final Random rand;

    @Override
    @Nonnull
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkPrimer = new ChunkPrimer();
        setBlocksInChunk(chunkPrimer,x,z);

        Chunk chunk = new Chunk(this.world, chunkPrimer, x, z);
        chunk.generateSkylightMap();

        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        int i = x * 16;
        int j = z * 16;
        performWorldGenSpawning(this.world,  MazeWorld.MazeBiome, i + 8, j + 8, 16, 16, this.rand);

    }

    public static void performWorldGenSpawning(World worldIn, Biome biomeIn, int centerX, int centerZ, int diameterX, int diameterZ, Random randomIn)
    {
        List<Biome.SpawnListEntry> list = biomeIn.getSpawnableList(EnumCreatureType.CREATURE);

        if (!list.isEmpty())
        {
            while (randomIn.nextFloat() < biomeIn.getSpawningChance())
            {
                Biome.SpawnListEntry biome$spawnlistentry = WeightedRandom.getRandomItem(worldIn.rand, list);
                int i = biome$spawnlistentry.minGroupCount + randomIn.nextInt(1 + biome$spawnlistentry.maxGroupCount - biome$spawnlistentry.minGroupCount);
                IEntityLivingData ientitylivingdata = null;
                int j = centerX + randomIn.nextInt(diameterX);
                int k = centerZ + randomIn.nextInt(diameterZ);
                int l = j;
                int i1 = k;

                for (int j1 = 0; j1 < i; ++j1)
                {
                    boolean flag = false;

                    for (int k1 = 0; !flag && k1 < 4; ++k1)
                    {
                        BlockPos blockpos = getTopSolidOrLiquidBlock(new BlockPos(j, 0, k),worldIn);
                        EntityLiving entityliving;
                        try
                        {
                            entityliving = biome$spawnlistentry.newInstance(worldIn);
                        }
                        catch (Exception exception)
                        {
                            exception.printStackTrace();
                            continue;
                        }

                        if (ForgeEventFactory.canEntitySpawn(entityliving, worldIn, j + 0.5f, (float) blockpos.getY(), k + 0.5f, false) == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) continue;
                        entityliving.setLocationAndAngles((float)j + 0.5F, blockpos.getY(), (float)k + 0.5F, randomIn.nextFloat() * 360.0F, 0.0F);
                        worldIn.spawnEntity(entityliving);
                        ientitylivingdata = entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), ientitylivingdata);
                        flag = true;

                        j += randomIn.nextInt(5) - randomIn.nextInt(5);

                        for (k += randomIn.nextInt(5) - randomIn.nextInt(5); j < centerX || j >= centerX + diameterX || k < centerZ || k >= centerZ + diameterX; k = i1 + randomIn.nextInt(5) - randomIn.nextInt(5))
                        {
                            j = l + randomIn.nextInt(5) - randomIn.nextInt(5);
                        }
                    }
                }
            }
        }
    }

    public static BlockPos getTopSolidOrLiquidBlock(BlockPos pos,World world){
        Chunk chunk = world.getChunk(pos);
        BlockPos blockpos;
        BlockPos blockpos1;

        for (blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1)
        {
            blockpos1 = blockpos.down();
            IBlockState state = chunk.getBlockState(blockpos1);

            if (state.getBlock() == BlockLoader.MazeBlock)
            {
                break;
            }
        }

        return blockpos;
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

    public void setBlocksInChunk(ChunkPrimer chunkPrimer, int x , int z){
        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                chunkPrimer.setBlockState(j, 0, k, Blocks.BEDROCK.getDefaultState());
            }
        }
        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                chunkPrimer.setBlockState(j, 1, k, BlockLoader.MazeBlock.getDefaultState());
            }
        }
        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                chunkPrimer.setBlockState(j, 256, k, Blocks.BARRIER.getDefaultState());
            }
        }
        Maze1 Maze1 = new Maze1();


        Maze1.init();
        Maze1.makeMaze();
        boolean[][] maze = Maze1.maze;
        for(int i = 1 ; i<=16;i++){
            for(int j=1;j<=16;j++){
                if(maze[i][j]){
                    BuildWall(i-1,j-1,chunkPrimer,2);
                } else {
                    int r = new Random().nextInt(99);
                    if(r == 50){
                        chunkPrimer.setBlockState(i-1,1,j-1,Blocks.TNT.getDefaultState());
                        chunkPrimer.setBlockState(i-1,2,j-1,Blocks.STONE_PRESSURE_PLATE.getDefaultState());
                    } else {
                        if(r == 10){
                            if(new Random(i+j).nextInt(50)==39){
                                chunkPrimer.setBlockState(i-1,1,j-1,BlockLoader.MazeShulkerSpawner.getDefaultState());
                            }
                            else chunkPrimer.setBlockState(i-1,1,j-1,BlockLoader.MazeTrap.getDefaultState());
                        }
                    }
                }
            }
        }

        for (int j = 0; j < 16; ++j)
        {
            for (int k = 0; k < 16; ++k)
            {
                if(!(new Random().nextInt(50)==39))chunkPrimer.setBlockState(j, 21, k, BlockLoader.MazeBlock.getDefaultState());
            }
        }

        Maze1.init();
        Maze1.makeMaze();
        maze = Maze1.maze;
        for(int i = 1 ; i<=16;i++){
            for(int j=1;j<=16;j++){
                if(maze[i][j]){
                    BuildWall(i-1,j-1,chunkPrimer,22);
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

    public static void BuildWall(int x,int z,ChunkPrimer chunk , int start){
        for(int y=start;y<start+5;y++){
            if(!(new Random().nextInt(20)==5)){
                if(y==start+2){
                    chunk.setBlockState(x, y, z, Blocks.SEA_LANTERN.getDefaultState());
                }
                else chunk.setBlockState(x,y,z, BlockLoader.MazeBlock.getDefaultState());
            }
        }
    }
}