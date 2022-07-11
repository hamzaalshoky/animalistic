package net.vladick.animalistic.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties DUMPLINGS = new FoodProperties.Builder().nutrition(4).saturationMod(0.4f)
            .build();

    public static final FoodProperties AVOCADO = new FoodProperties.Builder().nutrition(2).saturationMod(0.4f)
            .build();

    public static final FoodProperties RAW_CAVY_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f)
            .effect(new MobEffectInstance(MobEffects.POISON, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_CAVY_MEAT = new FoodProperties.Builder().nutrition(4).saturationMod(0.4f).meat()
            .build();

    public static final FoodProperties ROACH_ON_A_STICK = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f)
            .effect(new MobEffectInstance(MobEffects.POISON, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_ROACH_ON_A_STICK = new FoodProperties.Builder().nutrition(4).saturationMod(0.4f).meat()
            .build();

    public static final FoodProperties RAW_TURKEY = new FoodProperties.Builder().nutrition(4).saturationMod(0.2f)
            .meat().build();

    public static final FoodProperties COOKED_TURKEY = new FoodProperties.Builder().nutrition(10).saturationMod(0.6f).meat()
            .build();

    public static final FoodProperties RAW_KRILL = new FoodProperties.Builder().nutrition(1).saturationMod(0.2f)
            .meat().build();

    public static final FoodProperties COOKED_KRILL = new FoodProperties.Builder().nutrition(5).saturationMod(0.5f).meat()
            .build();

    public static final FoodProperties KRILL_SANDWICH = new FoodProperties.Builder().nutrition(7).saturationMod(0.7f).meat()
            .build();
}
