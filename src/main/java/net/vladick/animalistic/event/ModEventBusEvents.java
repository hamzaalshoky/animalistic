package net.vladick.animalistic.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.ModEntityTypes;
import net.vladick.animalistic.entity.custom.*;

@Mod.EventBusSubscriber(modid = Animalistic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.MUDPUPPY.get(), MudpuppyEntity.setAttributes());
        event.put(ModEntityTypes.CARACAL.get(), CaracalEntity.setAttributes());
        event.put(ModEntityTypes.CAVY.get(), CavyEntity.setAttributes());
        event.put(ModEntityTypes.WOLVERINE.get(), WolverineEntity.setAttributes());
        event.put(ModEntityTypes.KRILL.get(), KrillEntity.setAttributes());
        event.put(ModEntityTypes.SEA_SLUG.get(), SeaSlugEntity.setAttributes());
        event.put(ModEntityTypes.COCKROACH.get(), CockroachEntity.setAttributes());
        event.put(ModEntityTypes.TURKEY.get(), TurkeyEntity.setAttributes());
    }
}
