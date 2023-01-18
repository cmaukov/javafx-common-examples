package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

public enum SwimStyle implements HasDisplayName {
    FREESTYLE("Freestyle",0.8),
    BACKSTROKE("Backstroke",0.6),
    BUTTERFLY("Butterfly",0.7),
    IM("I.M.",0.8);

    private final String displayName;
    private final double value;

    SwimStyle(String displayName, double value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getValue() {
        return value;
    }
}
