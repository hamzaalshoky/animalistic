package net.vladick.animalistic.entity.variant;

import net.minecraft.Util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public enum OlmVariant {
    NORMAL(0, true),
    PINK(1, true),
    GRAY(2, true),
    BLUE(3, false);


    private static final OlmVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(OlmVariant::getId)).toArray(OlmVariant[]::new);
    private final int id;
    private final boolean common;

    OlmVariant(int p_30984_, boolean common) {
        this.id = p_30984_;
        this.common = common;
    }

    public int getId() {
        return this.id;
    }

    public static OlmVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public static OlmVariant getCommonSpawnVariant(Random p_149246_) {
        return getSpawnVariant(p_149246_, true);
    }

    public static OlmVariant getRareSpawnVariant(Random p_149257_) {
        return getSpawnVariant(p_149257_, false);
    }

    private static OlmVariant getSpawnVariant(Random p_149248_, boolean p_149249_) {
        OlmVariant[] olm$variant = Arrays.stream(BY_ID).filter((p_149252_) -> {
            return p_149252_.common == p_149249_;
        }).toArray((p_149244_) -> {
            return new OlmVariant[p_149244_];
        });
        return Util.getRandom(olm$variant, p_149248_);
    }
}
