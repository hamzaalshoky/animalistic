package net.vladick.animalistic.entity.custom.ai;

public interface SemiAquaticMob {

    boolean shouldEnterWater();

    boolean shouldLeaveWater();

    boolean shouldStopMoving();

    int getWaterSearchRange();
}
