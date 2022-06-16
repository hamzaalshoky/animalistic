package net.vladick.animalistic.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.ModEntityTypes;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Animalistic.MOD_ID);

    public static final RegistryObject<Item> MUDPUPPY_SPAWN_EGG = ITEMS.register("mudpuppy_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.MUDPUPPY,5982784, 4797748,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> CARACAL_SPAWN_EGG = ITEMS.register("caracal_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CARACAL,10381639, 9064254,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> WOLVERINE_SPAWN_EGG = ITEMS.register("wolverine_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.WOLVERINE,10381639, 9064254,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> CAVY_SPAWN_EGG = ITEMS.register("cavy_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CAVY,16235412, 15180944,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> MUDPUPPY_BUCKET = ITEMS.register("mudpuppy_bucket",
            () -> new MobBucketItem(ModEntityTypes.MUDPUPPY, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> DUMPLINGS = ITEMS.register("dumplings",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.DUMPLINGS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
