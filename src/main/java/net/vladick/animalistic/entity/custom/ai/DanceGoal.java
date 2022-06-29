package net.vladick.animalistic.entity.custom.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.vladick.animalistic.entity.custom.CockroachEntity;

import java.util.EnumSet;

public class DanceGoal extends Goal {
    private final CockroachEntity cock;

    public DanceGoal(CockroachEntity cock) {
        this.cock = cock;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return cock.isDancing();
    }

    @Override
    public void start() {
        this.cock.getNavigation().stop();
    }
}