package net.vladick.animalistic.entity.custom.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.vladick.animalistic.entity.custom.HippoEntity;

import java.util.EnumSet;

public class HippoSleepGoal extends Goal {
    private final HippoEntity capy;

    public HippoSleepGoal(HippoEntity capy) {
        this.capy = capy;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return capy.isSleeping();
    }

    @Override
    public void start() {
        this.capy.getNavigation().stop();
    }
}