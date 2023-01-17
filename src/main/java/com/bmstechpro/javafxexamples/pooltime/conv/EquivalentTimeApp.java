package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

public class EquivalentTimeApp {
    public static void main(String[] args) {
        SwimTime swimTime = new SwimTime(25, 10);
        EquivalentTime equivalentTime = new EquivalentTime(PoolCourse.SHORT_COURSE_YARDS
        ,PoolCourse.LONG_COURSE_METERS,SwimStyle.FREESTYLE,Distance._50,swimTime);
        System.out.println(equivalentTime.convertTime());
        System.out.println(equivalentTime);





    }
}
