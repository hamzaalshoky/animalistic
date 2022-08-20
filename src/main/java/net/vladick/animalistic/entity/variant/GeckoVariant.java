package net.vladick.animalistic.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum GeckoVariant {
    BROWN(0),
    GOLDEN(1),
    AQUA(2);

    private static final GeckoVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(GeckoVariant::getId)).toArray(GeckoVariant[]::new);
    private final int id;

    GeckoVariant(int p_30984_) {
        this.id = p_30984_;
    }

    public int getId() {
        return this.id;
    }

    public static GeckoVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
