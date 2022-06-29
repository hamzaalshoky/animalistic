package net.vladick.animalistic.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.effects.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Animalistic.MOD_ID);

    public static RegistryObject<Potion> HURLING_POTION = POTIONS.register("hurling_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.HURLING.get(), 100, 0)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
