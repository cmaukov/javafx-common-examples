package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

public enum Distance {
    _50("50", 50),
    _100("100", 100),
    _200("200", 200),
    _400_500("400/500", 400),
    _800_1000("800/1000", 800),
    _1500_1600("1500", 1500);
    private final String displayName;
    private final int distance;

    Distance(String displayName, int distance) {
        this.displayName = displayName;
        this.distance = distance;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDistance() {
        return distance;
    }
}
