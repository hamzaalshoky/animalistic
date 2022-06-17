package net.vladick.animalistic.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties DUMPLINGS = new FoodProperties.Builder().nutrition(4).saturationMod(0.4f)
            .build();

    public static final FoodProperties RAW_CAVY_MEAT = new FoodProperties.Builder().nutrition(2).saturationMod(0.2f)
            .effect(new MobEffectInstance(MobEffects.POISON, 600, 0), 0.3F).meat().build();

    public static final FoodProperties COOKED_CAVY_MEAT = new FoodProperties.Builder().nutrition(4).saturationMod(0.4f).meat()
            .build();
}
