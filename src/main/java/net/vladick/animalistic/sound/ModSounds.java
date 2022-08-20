package net.vladick.animalistic.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Animalistic.MOD_ID);

    public static RegistryObject<SoundEvent> BURROWING_FROG_AMBIENT
            = registerSoundEvents("burrowing_frog_ambient");

    public static RegistryObject<SoundEvent> BURROWING_FROG_HURT
            = registerSoundEvents("burrowing_frog_hurt");

    public static RegistryObject<SoundEvent> BURROWING_FROG_DEATH
            = registerSoundEvents("burrowing_frog_death");

    public static RegistryObject<SoundEvent> CAPYBARA_HURT
            = registerSoundEvents("capybara_hurt");

    public static RegistryObject<SoundEvent> CAPYBARA_DEATH
            = registerSoundEvents("capybara_death");

    public static RegistryObject<SoundEvent> CAVY_AMBIENT
            = registerSoundEvents("cavy_ambient");

    public static RegistryObject<SoundEvent> CAVY_HURT
            = registerSoundEvents("cavy_hurt");

    public static RegistryObject<SoundEvent> CAVY_DEATH
            = registerSoundEvents("cavy_death");

    public static RegistryObject<SoundEvent> FAUNAFUL
            = registerSoundEvents("record_faunaful");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        ResourceLocation id = new ResourceLocation(Animalistic.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> new SoundEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
