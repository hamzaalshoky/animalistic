package net.vladick.animalistic.entity.custom.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.vladick.animalistic.entity.custom.ChinchillaEntity;

import java.util.EnumSet;

public class ChinchillaBeg extends Goal {
    private static final TargetingConditions ENTITY_PREDICATE = TargetingConditions.forNonCombat().range(32D);
    protected final ChinchillaEntity chinny;
    private final double speed;
    protected Player closestPlayer;
    private int delayTemptCounter;
    private boolean isRunning;

    public ChinchillaBeg(ChinchillaEntity chinny, double speed) {
        this.chinny = chinny;
        this.speed = speed;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if (this.delayTemptCounter > 0) {
            --this.delayTemptCounter;
            return false;
        } else {
            if(this.chinny.isInLove()){
                return false;
            }
            this.closestPlayer = this.chinny.level.getNearestPlayer(ENTITY_PREDICATE, this.chinny);
            if (this.closestPlayer == null) {
                return false;
            } else {
                boolean food = isFood(this.closestPlayer.getMainHandItem()) || isFood(this.closestPlayer.getOffhandItem());
                return food;
            }
        }
    }

    private boolean isFood(ItemStack stack) {
        return stack.is(Tags.Items.SEEDS) || stack.is(Tags.Items.CROPS) ||chinny.isFood(stack);
    }


    public boolean canContinueToUse() {
        return this.chinny.getMainHandItem().isEmpty() && this.canUse() && !this.chinny.isInLove();
    }

    public void start() {
        this.isRunning = true;
    }

    public void stop() {
        this.closestPlayer = null;
        this.chinny.getNavigation().stop();
        this.delayTemptCounter = 100;
        this.chinny.setBegging(false);
        this.isRunning = false;
    }

    public void tick() {
        this.chinny.getLookControl().setLookAt(this.closestPlayer, (float)(this.chinny.getMaxHeadYRot() + 20), (float)this.chinny.getMaxHeadXRot());
        if (this.chinny.distanceToSqr(this.closestPlayer) < 12D) {
            this.chinny.getNavigation().stop();
            this.chinny.setBegging(true);
        } else {
            this.chinny.getNavigation().moveTo(this.closestPlayer, this.speed);
        }

    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
