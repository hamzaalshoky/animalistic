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
import net.vladick.animalistic.item.custom.TurkeyEgg;

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
            () -> new ForgeSpawnEggItem(ModEntityTypes.WOLVERINE,7690567, 4274744,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> CAVY_SPAWN_EGG = ITEMS.register("cavy_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CAVY,16235412, 15180944,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> KRILL_SPAWN_EGG = ITEMS.register("krill_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.KRILL,14932690, 15704185,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> SEA_SLUG_SPAWN_EGG = ITEMS.register("sea_slug_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SEA_SLUG,5396062, 3618884,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> COCKROACH_SPAWN_EGG = ITEMS.register("cockroach_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.COCKROACH,5396062, 3618884,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> TURKEY_SPAWN_EGG = ITEMS.register("turkey_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.TURKEY,5396062, 3618884,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> MUDPUPPY_BUCKET = ITEMS.register("mudpuppy_bucket",
            () -> new MobBucketItem(ModEntityTypes.MUDPUPPY, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> KRILL_BUCKET = ITEMS.register("krill_bucket",
            () -> new MobBucketItem(ModEntityTypes.KRILL, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH,
                    (new Item.Properties()).stacksTo(1).tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> DUMPLINGS = ITEMS.register("dumplings",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.DUMPLINGS)));

    public static final RegistryObject<Item> RAW_CAVY_MEAT = ITEMS.register("raw_cavy_meat",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.RAW_CAVY_MEAT)));

    public static final RegistryObject<Item> COOKED_CAVY_MEAT = ITEMS.register("cooked_cavy_meat",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.COOKED_CAVY_MEAT)));

    public static final RegistryObject<Item> RAW_KRILL = ITEMS.register("raw_krill",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.RAW_KRILL)));

    public static final RegistryObject<Item> COOKED_KRILL = ITEMS.register("cooked_krill",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.COOKED_KRILL)));

    public static final RegistryObject<Item> KRILL_SANDWICH = ITEMS.register("krill_sandwich",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.KRILL_SANDWICH)));

    public static final RegistryObject<Item> SEA_SLUG_SLIME = ITEMS.register("sea_slug_slime",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> ROACH_ANTENNA = ITEMS.register("roach_antenna",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> TURKEY_EGG = ITEMS.register("turkey_egg",
            () -> new TurkeyEgg(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
