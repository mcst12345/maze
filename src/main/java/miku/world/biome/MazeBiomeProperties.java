package miku.world.biome;

import net.minecraft.world.biome.Biome;

public class MazeBiomeProperties extends Biome.BiomeProperties {
    public MazeBiomeProperties() {
        super("Maze1");
        this.setBaseBiome("maze");
        this.setWaterColor(0x39c55b);
        this.setTemperature(0);
    }
}
