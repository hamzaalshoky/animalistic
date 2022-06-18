package net.vladick.animalistic.entity.client;

import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.ModEntityTypes;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Animalistic.MOD_ID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.MUDPUPPY.get(), MudpuppyRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CARACAL.get(), CaracalRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.CAVY.get(), CavyRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.WOLVERINE.get(), WolverineRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.KRILL.get(), KrillRenderer::new);
        event.registerEntityRenderer(ModEntityTypes.SEA_SLUG.get(), SeaSlugRenderer::new);
    }
}
