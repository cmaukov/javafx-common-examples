package com.bmstechpro.javafxexamples.pooltime;
/* javafx-examples
 * @created 01/15/2023
 * @author Konstantin Staykov
 */

public class PoolTimeConverterApp {

    public static void main(String[] args) {
        SwimTimeConverter timeConverter = new SwimTimeConverter(56.6,PoolLength._25y,PoolLength._50m, SwimEvent._100_BACK );

        System.out.println(timeConverter);

    }


}
