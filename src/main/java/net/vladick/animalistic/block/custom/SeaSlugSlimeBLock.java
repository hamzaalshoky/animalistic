package net.vladick.animalistic.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.vladick.animalistic.effects.ModEffects;

public class SeaSlugSlimeBLock extends Block {
    public SeaSlugSlimeBLock(Properties p_49795_) {
        super(p_49795_);
    }

    public void stepOn(Level world, BlockPos pos, BlockState state, Entity player, LivingEntity target) {
        double d0 = Math.abs(player.getDeltaMovement().y);
        if (d0 < 0.1D && !player.isSteppingCarefully()) {
            double d1 = 0.4D + d0 * 0.2D;
            player.setDeltaMovement(player.getDeltaMovement().multiply(d1, 1.0D, d1));
            target.addEffect(new MobEffectInstance(ModEffects.STICKY_TOXIN.get()));
        }

        super.stepOn(world, pos, state, player);
    }
}
