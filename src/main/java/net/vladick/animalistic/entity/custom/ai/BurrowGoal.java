package net.vladick.animalistic.entity.custom.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.vladick.animalistic.entity.custom.BurrowingFrogEntity;

import java.util.EnumSet;

public class BurrowGoal extends Goal {
    private final BurrowingFrogEntity forg;

    public BurrowGoal(BurrowingFrogEntity forg) {
        this.forg = forg;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return forg.isBurrowed();
    }

    @Override
    public void start() {
        this.forg.getNavigation().stop();
    }
}