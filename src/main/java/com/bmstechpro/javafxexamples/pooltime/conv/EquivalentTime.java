package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

public class EquivalentTime {
    private final PoolCourse fromCourse;
    private final PoolCourse toCourse;
    private final SwimStyle swimStyle;

    private final Distance distance;

    private final double swimTime;

    private double convertedTime;

    public EquivalentTime(PoolCourse fromCourse, PoolCourse toCourse, SwimStyle swimStyle, Distance distance, SwimTime swimTime) {
        this.fromCourse = fromCourse;
        this.toCourse = toCourse;
        this.swimStyle = swimStyle;
        this.distance = distance;
        this.swimTime = swimTime.getTime();

    }

    public double convertTime() {
        if (fromCourse == PoolCourse.SHORT_COURSE_YARDS && toCourse == PoolCourse.SHORT_COURSE_YARDS) {
            convertedTime = swimTime;
        }
        if (fromCourse == PoolCourse.SHORT_COURSE_YARDS && toCourse == PoolCourse.SHORT_COURSE_METERS) {
            convertedTime = switch (distance) {
                case _50, _100, _200 -> swimTime * 1.11;
                case _400_500, _800_1000 -> swimTime * 0.8925;
                case _1500_1600 -> swimTime * 1.2;
            };
        }
        if (fromCourse == PoolCourse.SHORT_COURSE_YARDS && toCourse == PoolCourse.LONG_COURSE_METERS) {
            convertedTime = switch (distance) {
                case _50 -> swimTime * 1.11 + swimStyle.getValue();
                case _100 -> swimTime * 1.11 + 2 * swimStyle.getValue();
                case _200 -> swimTime * 1.11 + 4 * swimStyle.getValue();
                case _400_500, _800_1000 -> swimTime * 0.8925;
                case _1500_1600 -> swimTime * 1.2;
            };
        }
        if (fromCourse == PoolCourse.SHORT_COURSE_METERS && toCourse == PoolCourse.SHORT_COURSE_YARDS) {
            convertedTime = switch (distance) {
                case _50, _100, _200 -> swimTime / 1.11;
                case _400_500, _800_1000 -> swimTime / 0.8925;
                case _1500_1600 -> swimTime / 1.2;
            };
        }
        if (fromCourse == PoolCourse.SHORT_COURSE_METERS && toCourse == PoolCourse.SHORT_COURSE_METERS) {
            convertedTime = swimTime;
        }

        if (fromCourse == PoolCourse.SHORT_COURSE_METERS && toCourse == PoolCourse.LONG_COURSE_METERS) {
            convertedTime = switch (distance) {
                case _50 -> swimTime + swimStyle.getValue();
                case _100 -> swimTime + 2 * swimStyle.getValue();
                case _200 -> swimTime + 4 * swimStyle.getValue();
                case _400_500 -> swimTime + 6.4;
                case _800_1000 -> swimTime + 12.8;
                case _1500_1600 -> swimTime + 24;
            };
        }

        if (fromCourse == PoolCourse.LONG_COURSE_METERS && toCourse == PoolCourse.SHORT_COURSE_YARDS) {
            convertedTime = switch (distance) {
                case _50 -> (swimTime - swimStyle.getValue()) / 1.11;
                case _100 -> (swimTime - 2 * swimStyle.getValue()) / 1.11;
                case _200 -> (swimTime - 4 * swimStyle.getValue()) / 1.11;
                case _400_500, _800_1000 -> swimTime / 0.8925;
                case _1500_1600 -> swimTime / 1.2;
            };
        }

        if (fromCourse == PoolCourse.LONG_COURSE_METERS && toCourse == PoolCourse.SHORT_COURSE_METERS) {
            convertedTime = switch (distance) {
                case _50 -> swimTime - swimStyle.getValue();
                case _100 -> swimTime - 2 * swimStyle.getValue();
                case _200 -> swimTime - 4 * swimStyle.getValue();
                case _400_500 -> swimTime - 6.4;
                case _800_1000 -> swimTime - 12.8;
                case _1500_1600 -> swimTime - 24;
            };
        }
        if (fromCourse == PoolCourse.LONG_COURSE_METERS && toCourse == PoolCourse.LONG_COURSE_METERS) {
            convertedTime = swimTime;
        }
        return convertedTime;
    }

    public double getConvertedTime() {
        return convertedTime;
    }

    public String convTimeToString() {

        int iPart = (int) convertedTime;
        int min = iPart / 60;
        int sec = iPart - min * 60;
        var ms = Math.round((convertedTime - iPart) * 100);

        return "Converted Time: " + min + ":" + sec + ":" + ms;

    }

    @Override
    public String toString() {
        return String.format("SwimTimeConverter\nEvent: %s\nFrom: %s\nTo: %s\nSwim time: %.2f\n%s",
                swimStyle.getDisplayName(),
                fromCourse.getDisplayName(),
                toCourse.getDisplayName(),
                swimTime,
                convTimeToString());


    }

}
