package net.vladick.animalistic.world;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.vladick.animalistic.entity.ModEntityTypes;

public class ModEntityGeneration {
    public static void onEntitySpawn(final BiomeLoadingEvent event) {

        if(doesBiomeMatch(event.getName(), Biomes.RIVER)) {
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.MUDPUPPY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.LUKEWARM_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.MUDPUPPY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CARACAL.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA_PLATEAU)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CARACAL.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WINDSWEPT_SAVANNA)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CARACAL.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CARACAL.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WINDSWEPT_GRAVELLY_HILLS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CAVY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WINDSWEPT_HILLS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CAVY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.JAGGED_PEAKS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CAVY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.STONY_PEAKS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.CAVY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_SLOPES)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.WOLVERINE.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_BEACH)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.WOLVERINE.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_PLAINS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.WOLVERINE.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SNOWY_TAIGA)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.WOLVERINE.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.FROZEN_PEAKS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.WOLVERINE.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.COLD_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.KRILL.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DEEP_FROZEN_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.KRILL.get(), 3, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.RIVER)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.KRILL.get(), 5, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.STONY_SHORE)) {
            event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SEA_SLUG.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.LUSH_CAVES)) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.COCKROACH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.PLAINS)) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.COCKROACH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DESERT)) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.COCKROACH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.FOREST)) {
            event.getSpawns().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.COCKROACH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.TURKEY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SAVANNA_PLATEAU)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.TURKEY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WINDSWEPT_SAVANNA)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.TURKEY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.PLAINS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.TURKEY.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.JUNGLE)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SLOTH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SPARSE_JUNGLE)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SLOTH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BAMBOO_JUNGLE)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SLOTH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.LUSH_CAVES)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.SLOTH.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BADLANDS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.ECHIDNA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.ERODED_BADLANDS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.ECHIDNA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.WOODED_BADLANDS)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.ECHIDNA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DESERT)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.ECHIDNA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.OLM.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.RIVER)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.OLM.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DEEP_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.OLM.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DESERT)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.BURROWING_FROG.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DESERT)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.BURROWING_FROG.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BEACH)) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntityTypes.BURROWING_FROG.get(), 6, 1, 3));
        }
    }

    public static boolean doesBiomeMatch(ResourceLocation biomeNameIn, ResourceKey<Biome> biomeIn) {
        return biomeNameIn.getPath().matches(biomeIn.location().getPath());
    }
}
