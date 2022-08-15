package miku.world.biome;

import net.minecraft.entity.monster.*;
import net.minecraft.world.biome.Biome;

public class MazeBiome extends Biome {
    public MazeBiome() {
        super(new MazeBiomeProperties());
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 5));
        spawnableCreatureList.add(new SpawnListEntry(EntityZombie.class, 10, 1, 20));
        spawnableCreatureList.add(new SpawnListEntry(EntitySkeleton.class, 10, 1, 20));
        spawnableCreatureList.add(new SpawnListEntry(EntityWitherSkeleton.class, 10, 1, 20));
        spawnableCreatureList.add(new SpawnListEntry(EntityCreeper.class, 10, 1, 3));
    }
}
