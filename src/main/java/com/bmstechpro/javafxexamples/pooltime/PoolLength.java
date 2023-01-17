package com.bmstechpro.javafxexamples.pooltime;
/* javafx-examples
 * @created 01/15/2023
 * @author Konstantin Staykov
 */

public enum PoolLength {
    _50m("50 meters"),
    _33_1_3m("33-1/3 meters"),
    _25m("25 meters"),
    _20m("20 meters"),
    _36_2_3y("36-2/3 yards"),
    _27_1_2y("27-1/2 yards"),
    _33_1_3y("33-1/3 yards"),
    _25y("25 yards"),
    _20y("20 yards");
private final String displayName;


    PoolLength(String name) {
        displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
