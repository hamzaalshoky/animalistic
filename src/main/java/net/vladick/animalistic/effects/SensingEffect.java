package net.vladick.animalistic.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.vladick.animalistic.util.BlindAndGlow;

public class SensingEffect extends MobEffect {
    public SensingEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(MobEffectCategory.NEUTRAL, 3124687);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        BlindAndGlow.make(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
