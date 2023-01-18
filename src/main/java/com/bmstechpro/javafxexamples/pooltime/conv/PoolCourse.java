package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

public enum PoolCourse implements HasDisplayName {
    LONG_COURSE_METERS("Long Course Meters"),
    SHORT_COURSE_YARDS("Short Course Yards"),
    SHORT_COURSE_METERS("Short Course Meters");

    private final String displayName;

    PoolCourse(String displayName) {
        this.displayName = displayName;
    }


    public String getDisplayName() {
        return displayName;
    }
}
