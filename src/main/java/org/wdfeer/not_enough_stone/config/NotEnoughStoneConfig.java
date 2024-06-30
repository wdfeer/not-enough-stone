package org.wdfeer.not_enough_stone.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class NotEnoughStoneConfig extends MidnightConfig {
    @Entry(category = "numbers")
    public static double logarithmicSummandMultiplier = 1D;
    public static float getLogarithmicMultiplier() {
        return (float)logarithmicSummandMultiplier;
    }

    @Entry(category = "numbers")
    public static double smallLinearSummandMultiplier = 1D;
    public static float getSmallLinearMultiplier() {
        return (float)smallLinearSummandMultiplier;
    }
    @Entry(category = "numbers")
    public static double bigLinearSummandMultiplier = 1D;
    public static float getBigLinearMultiplier() {
        return (float)bigLinearSummandMultiplier;
    }
}
