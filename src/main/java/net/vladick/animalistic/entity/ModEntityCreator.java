package net.vladick.animalistic.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.client.*;
import net.vladick.animalistic.entity.custom.*;

@Mod.EventBusSubscriber(modid = Animalistic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityCreator {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Animalistic.MOD_ID);

    // REGESTRIES

    public static final RegistryObject<EntityType<MudpuppyEntity>> MUDPUPPY = ENTITY_TYPES.register("mudpuppy", () -> EntityType.Builder.of(MudpuppyEntity::new, MobCategory.WATER_CREATURE).sized(0.7f, 0.2f).build(new ResourceLocation(Animalistic.MOD_ID, "mudpuppy").toString()));
    public static final RegistryObject<EntityType<CaracalEntity>> CARACAL = ENTITY_TYPES.register("caracal", () -> EntityType.Builder.of(CaracalEntity::new, MobCategory.CREATURE).sized(1.5f, 1.2f).build(new ResourceLocation(Animalistic.MOD_ID, "caracal").toString()));
    public static final RegistryObject<EntityType<CavyEntity>> CAVY = ENTITY_TYPES.register("cavy", () -> EntityType.Builder.of(CavyEntity::new, MobCategory.CREATURE).sized(0.5f, 0.8f).build(new ResourceLocation(Animalistic.MOD_ID, "cavy").toString()));
    public static final RegistryObject<EntityType<WolverineEntity>> WOLVERINE = ENTITY_TYPES.register("wolverine", () -> EntityType.Builder.of(WolverineEntity::new, MobCategory.CREATURE).sized(1.4f, 0.9f).build(new ResourceLocation(Animalistic.MOD_ID, "wolverine").toString()));
    public static final RegistryObject<EntityType<KrillEntity>> KRILL = ENTITY_TYPES.register("krill", () -> EntityType.Builder.of(KrillEntity::new, MobCategory.WATER_CREATURE).sized(0.9f, 0.3f).build(new ResourceLocation(Animalistic.MOD_ID, "krill").toString()));
    public static final RegistryObject<EntityType<SeaSlugEntity>> SEA_SLUG = ENTITY_TYPES.register("sea_slug", () -> EntityType.Builder.of(SeaSlugEntity::new, MobCategory.WATER_CREATURE).sized(0.6f, 1f).build(new ResourceLocation(Animalistic.MOD_ID, "krill").toString()));
    public static final RegistryObject<EntityType<CockroachEntity>> COCKROACH = ENTITY_TYPES.register("cockroach", () -> EntityType.Builder.of(CockroachEntity::new, MobCategory.CREATURE).sized(0.9f, 0.2f).build(new ResourceLocation(Animalistic.MOD_ID, "cockroach").toString()));
    public static final RegistryObject<EntityType<TurkeyEntity>> TURKEY = ENTITY_TYPES.register("turkey", () -> EntityType.Builder.of(TurkeyEntity::new, MobCategory.CREATURE).sized(1f, 1.1f).build(new ResourceLocation(Animalistic.MOD_ID, "turkey").toString()));
    public static final RegistryObject<EntityType<SlothEntity>> SLOTH = ENTITY_TYPES.register("sloth", () -> EntityType.Builder.of(SlothEntity::new, MobCategory.CREATURE).sized(1f, 0.8f).build(new ResourceLocation(Animalistic.MOD_ID, "sloth").toString()));
    public static final RegistryObject<EntityType<EchidnaEntity>> ECHIDNA = ENTITY_TYPES.register("echidna", () -> EntityType.Builder.of(EchidnaEntity::new, MobCategory.CREATURE).sized(1f, 0.6f).build(new ResourceLocation(Animalistic.MOD_ID, "echidna").toString()));
    public static final RegistryObject<EntityType<OlmEntity>> OLM = ENTITY_TYPES.register("olm", () -> EntityType.Builder.of(OlmEntity::new, MobCategory.WATER_CREATURE).sized(0.8f, 0.3f).build(new ResourceLocation(Animalistic.MOD_ID, "olm").toString()));
    public static final RegistryObject<EntityType<BurrowingFrogEntity>> BURROWING_FROG = ENTITY_TYPES.register("burrowing_frog", () -> EntityType.Builder.of(BurrowingFrogEntity::new, MobCategory.CREATURE).sized(0.8f, 0.8f).build(new ResourceLocation(Animalistic.MOD_ID, "burrowing_frog").toString()));
    public static final RegistryObject<EntityType<CapybaraEntity>> CAPYBARA = ENTITY_TYPES.register("capybara", () -> EntityType.Builder.of(CapybaraEntity::new, MobCategory.CREATURE).sized(1.5f, 1.1f).build(new ResourceLocation(Animalistic.MOD_ID, "capybara").toString()));
    public static final RegistryObject<EntityType<HippoEntity>> HIPPO = ENTITY_TYPES.register("hippo", () -> EntityType.Builder.of(HippoEntity::new, MobCategory.CREATURE).sized(0.9f, 1.5f).build(new ResourceLocation(Animalistic.MOD_ID, "hippo").toString()));

    // ATTRIBUTES

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityCreator.MUDPUPPY.get(), MudpuppyEntity.setAttributes());
        event.put(ModEntityCreator.CARACAL.get(), CaracalEntity.setAttributes());
        event.put(ModEntityCreator.CAVY.get(), CavyEntity.setAttributes());
        event.put(ModEntityCreator.WOLVERINE.get(), WolverineEntity.setAttributes());
        event.put(ModEntityCreator.KRILL.get(), KrillEntity.setAttributes());
        event.put(ModEntityCreator.SEA_SLUG.get(), SeaSlugEntity.setAttributes());
        event.put(ModEntityCreator.COCKROACH.get(), CockroachEntity.setAttributes());
        event.put(ModEntityCreator.TURKEY.get(), TurkeyEntity.setAttributes());
        event.put(ModEntityCreator.SLOTH.get(), SlothEntity.setAttributes());
        event.put(ModEntityCreator.ECHIDNA.get(), EchidnaEntity.setAttributes());
        event.put(ModEntityCreator.OLM.get(), OlmEntity.setAttributes());
        event.put(ModEntityCreator.BURROWING_FROG.get(), BurrowingFrogEntity.setAttributes());
        event.put(ModEntityCreator.CAPYBARA.get(), CapybaraEntity.setAttributes());
        event.put(ModEntityCreator.HIPPO.get(), HippoEntity.setAttributes());
    }

    // RENDERERS

    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityCreator.MUDPUPPY.get(), MudpuppyRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.CARACAL.get(), CaracalRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.CAVY.get(), CavyRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.WOLVERINE.get(), WolverineRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.KRILL.get(), KrillRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SEA_SLUG.get(), SeaSlugRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.COCKROACH.get(), CockroachRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.TURKEY.get(), TurkeyRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.SLOTH.get(), SlothRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.ECHIDNA.get(), EchidnaRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.OLM.get(), OlmRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.BURROWING_FROG.get(), BurrowingFrogRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.CAPYBARA.get(), CapybaraRenderer::new);
        event.registerEntityRenderer(ModEntityCreator.HIPPO.get(), HippoRenderer::new);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
