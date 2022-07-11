package net.vladick.animalistic.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.vladick.animalistic.Animalistic;
import net.vladick.animalistic.entity.ModEntityTypes;
import net.vladick.animalistic.item.custom.HairAlgaeItem;
import net.vladick.animalistic.item.custom.SpikeSwordItem;

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
            () -> new ForgeSpawnEggItem(ModEntityTypes.COCKROACH,9786206, 11168092,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> TURKEY_SPAWN_EGG = ITEMS.register("turkey_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.TURKEY,5850701, 14765935,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> SLOTH_SPAWN_EGG = ITEMS.register("sloth_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SLOTH,8082773, 9528411,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> OLM_SPAWN_EGG = ITEMS.register("olm_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.OLM,14208714, 16742308,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> ECHIDNA_SPAWN_EGG = ITEMS.register("echidna_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.ECHIDNA,12299432, 8414555,
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

    public static final RegistryObject<Item> DECEASED_ROACH = ITEMS.register("deceased_roach",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> TURKEY_FEATHER = ITEMS.register("turkey_feather",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> ROACH_ON_A_STICK = ITEMS.register("roach_on_a_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.ROACH_ON_A_STICK).stacksTo(1)));

    public static final RegistryObject<Item> COOKED_ROACH_ON_A_STICK = ITEMS.register("cooked_roach_on_a_stick",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.COOKED_ROACH_ON_A_STICK).stacksTo(1)));

    public static final RegistryObject<Item> RAW_TURKEY = ITEMS.register("raw_turkey",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.RAW_TURKEY)));

    public static final RegistryObject<Item> COOKED_TURKEY = ITEMS.register("cooked_turkey",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.COOKED_TURKEY)));

    public static final RegistryObject<Item> AVOCADO = ITEMS.register("avocado",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB).food(ModFoods.AVOCADO)));

    public static final RegistryObject<Item> HAIR_ALGAE = ITEMS.register("hair_algae",
            () -> new HairAlgaeItem(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> ECHIDNA_SPIKES = ITEMS.register("echidna_spikes",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static final RegistryObject<Item> SPIKY_SWORD = ITEMS.register("spiky_sword",
            () -> new SpikeSwordItem(Tiers.WOOD, 2, 3f,
                    new Item.Properties().tab(ModCreativeModeTabs.ANIMALISTIC_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
