package net.vladick.animalistic.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.custom.*;

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

    public static final RegistryObject<EntityType<KrillEntity>> KRILL = ENTITY_TYPES.register("krill",
            () -> EntityType.Builder.of(KrillEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "krill").toString()));

    public static final RegistryObject<EntityType<SeaSlugEntity>> SEA_SLUG = ENTITY_TYPES.register("sea_slug",
            () -> EntityType.Builder.of(SeaSlugEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "krill").toString()));

    public static final RegistryObject<EntityType<CockroachEntity>> COCKROACH = ENTITY_TYPES.register("cockroach",
            () -> EntityType.Builder.of(CockroachEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "cockroach").toString()));

    public static final RegistryObject<EntityType<TurkeyEntity>> TURKEY = ENTITY_TYPES.register("turkey",
            () -> EntityType.Builder.of(TurkeyEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "turkey").toString()));

    public static final RegistryObject<EntityType<SlothEntity>> SLOTH = ENTITY_TYPES.register("sloth",
            () -> EntityType.Builder.of(SlothEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "sloth").toString()));

    public static final RegistryObject<EntityType<EchidnaEntity>> ECHIDNA = ENTITY_TYPES.register("echidna",
            () -> EntityType.Builder.of(EchidnaEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "echidna").toString()));

    public static final RegistryObject<EntityType<OlmEntity>> OLM = ENTITY_TYPES.register("olm",
            () -> EntityType.Builder.of(OlmEntity::new, MobCategory.WATER_CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "olm").toString()));

    public static final RegistryObject<EntityType<BurrowingFrogEntity>> BURROWING_FROG = ENTITY_TYPES.register("burrowing_frog",
            () -> EntityType.Builder.of(BurrowingFrogEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "burrowing_frog").toString()));

    public static final RegistryObject<EntityType<CapybaraEntity>> CAPYBARA = ENTITY_TYPES.register("capybara",
            () -> EntityType.Builder.of(CapybaraEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.5f)
                    .build(new ResourceLocation(Animalistic.MOD_ID, "capybara").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
