package net.vladick.animalistic.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.CaracalEntity;
import net.vladick.animalistic.entity.custom.CavyEntity;
import net.vladick.animalistic.entity.custom.MudpuppyEntity;
import net.vladick.animalistic.entity.custom.WolverineEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Animalistic.MOD_ID);

    public static final RegistryObject<EntityType<MudpuppyEntity>> MUDPUPPY = ENTITY_TYPES.register("mudpuppy",
            () -> EntityType.Builder.of(MudpuppyEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "mudpuppy").toString()));

    public static final RegistryObject<EntityType<CaracalEntity>> CARACAL = ENTITY_TYPES.register("caracal",
            () -> EntityType.Builder.of(CaracalEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "caracal").toString()));

    public static final RegistryObject<EntityType<CavyEntity>> CAVY = ENTITY_TYPES.register("cavy",
            () -> EntityType.Builder.of(CavyEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "cavy").toString()));

    public static final RegistryObject<EntityType<WolverineEntity>> WOLVERINE = ENTITY_TYPES.register("wolverine",
            () -> EntityType.Builder.of(WolverineEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "wolverine").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}