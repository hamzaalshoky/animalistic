package net.vladick.animalistic.entity.variant;

import net.minecraft.Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public enum MudpuppyVariant {
    NORMAL(0, true),
    GOLDEN(1, false);


    private static final MudpuppyVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(MudpuppyVariant::getId)).toArray(MudpuppyVariant[]::new);
    private final int id;
    private final boolean common;

    MudpuppyVariant(int p_30984_, boolean common) {
        this.id = p_30984_;
        this.common = common;
    }

    public int getId() {
        return this.id;
    }

    public static MudpuppyVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public static MudpuppyVariant getCommonSpawnVariant(Random p_149246_) {
        return getSpawnVariant(p_149246_, true);
    }

    public static MudpuppyVariant getRareSpawnVariant(Random p_149257_) {
        return getSpawnVariant(p_149257_, false);
    }

    private static MudpuppyVariant getSpawnVariant(Random p_149248_, boolean p_149249_) {
        MudpuppyVariant[] mudpuppy$variant = Arrays.stream(BY_ID).filter((p_149252_) -> {
            return p_149252_.common == p_149249_;
        }).toArray((p_149244_) -> {
            return new MudpuppyVariant[p_149244_];
        });
        return Util.getRandom(mudpuppy$variant, p_149248_);
    }
}
