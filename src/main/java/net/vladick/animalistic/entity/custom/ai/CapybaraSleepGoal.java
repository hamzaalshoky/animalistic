package net.vladick.animalistic.entity.custom.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.vladick.animalistic.entity.custom.CapybaraEntity;

import java.util.EnumSet;

public class CapybaraSleepGoal extends Goal {
    private final CapybaraEntity capy;

    public CapybaraSleepGoal(CapybaraEntity capy) {
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