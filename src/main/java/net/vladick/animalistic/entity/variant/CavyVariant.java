package net.vladick.animalistic.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CavyVariant {
    AMERICAN(0),
    MERINO(1),
    PERUVIAN(2);

    private static final CavyVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(CavyVariant::getId)).toArray(CavyVariant[]::new);
    private final int id;

    CavyVariant(int p_30984_) {
        this.id = p_30984_;
    }

    public int getId() {
        return this.id;
    }

    public static CavyVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
