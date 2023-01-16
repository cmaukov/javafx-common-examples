package com.bmstechpro.javafxexamples.pooltime;
/* javafx-examples
 * @created 01/15/2023
 * @author Konstantin Staykov
 */

public enum SwimEvent {
    _50_FREE("50 Free",42.245),
    _100_FREE("100 Free",42.245),
    _200_FREE("200 Free",43.786),
    _400_FREE("400 Free",44.233),
    _800_FREE("800 Free",45.525),
    _1500_FREE("1500 Free",46.221),
    _50_BREAST("50 Breast",63.616),
    _100_BREAST("100 Breast",63.616),
    _200_BREAST("200 Breast",66.598),
    _50_FLY("50 Fly",38.269),
    _100_FLY("100 Fly",38.269),
    _200_FLY("200 Fly",39.76),
    _50_BACK("50 Back",40.5),
    _100_BACK("100 Back",40.5),
    _200_BACK("200 Back",41.98),
    _200_IM("200 IM",49.7),
    _400_IM("400 IM",55.366);


    private final String displayName;
    private final double turnFactor;


    SwimEvent(String name, double turnFactor) {
        displayName = name;
        this.turnFactor = turnFactor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getTurnFactor() {
        return turnFactor;
    }
}
