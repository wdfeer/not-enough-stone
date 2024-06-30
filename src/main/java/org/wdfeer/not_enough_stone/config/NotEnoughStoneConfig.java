package org.wdfeer.not_enough_stone.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class NotEnoughStoneConfig extends MidnightConfig {
    @Entry(category = "numbers", min = 0.0)
    public static double logarithmicMultiplier = 1D;
    public static float getLogarithmicMultiplier() {
        return (float) logarithmicMultiplier;
    }

    @Entry(category = "numbers", min = 0.0)
    public static double smallLinearMultiplier = 1D;
    public static float getSmallLinearMultiplier() {
        return (float) smallLinearMultiplier;
    }
    @Entry(category = "numbers", min = 0.0)
    public static double bigLinearMultiplier = 1D;
    public static float getBigLinearMultiplier() {
        return (float) bigLinearMultiplier;
    }
}
