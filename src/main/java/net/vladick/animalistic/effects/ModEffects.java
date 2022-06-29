package net.vladick.animalistic.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Animalistic.MOD_ID);

    public static final RegistryObject<MobEffect> STICKY_TOXIN = MOB_EFFECTS.register("sticky_toxin",
            () -> new StickyEffect(MobEffectCategory.HARMFUL, 5396062));

    public static final RegistryObject<MobEffect> HURLING = MOB_EFFECTS.register("hurling",
            () -> new HurlingEffect(MobEffectCategory.HARMFUL, 5396062));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
