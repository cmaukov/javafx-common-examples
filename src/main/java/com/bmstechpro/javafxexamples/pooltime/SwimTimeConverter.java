package com.bmstechpro.javafxexamples.pooltime;
/* javafx-examples
 * @created 01/15/2023
 * @author Konstantin Staykov
 */

public class SwimTimeConverter {
    private final PoolLength sourcePool;
    private final PoolLength targetPool;
    private final SwimEvent swimEvent;
    private final double longCourseTime;
    private double distanceTime;

    private final double sourceTime;
    private double convertedTime;
    private double turnTime;

    public SwimTimeConverter(double sourceTime, PoolLength sourcePool, PoolLength targetPool, SwimEvent swimEvent) {
        this.sourceTime = sourceTime;
        this.sourcePool = sourcePool;
        this.targetPool = targetPool;
        this.swimEvent = swimEvent;
        longCourseTime = getLongCourseTime();
        convert();
    }

    /**
     * @return Conversion to 50m time
     */
    private double getLongCourseTime() {

        if (getNumberOfTurnsPer100(sourcePool) == 1) return sourceTime;

        return (sourceTime + Math.sqrt(Math.pow(sourceTime, 2) + 4 * getPoolMeasure(sourcePool)
                * swimEvent.getTurnFactor() * getNumberTurnFactor())) / (2 * getPoolMeasure(sourcePool));
    }


    private int getNumberOfTurnsPer100(PoolLength poolLength) {
        return switch (poolLength) {
            case _50m -> 1;
            case _33_1_3m, _36_2_3y, _33_1_3y -> 2;
            case _25m, _27_1_2y, _25y -> 3;
            case _20m, _20y -> 4;
        };
    }

    /**
     * @return PoolMeasure – Factor to allow for distance covered
     */
    private double getPoolMeasure(PoolLength poolLength) {
        return switch (poolLength) {
            case _50m, _33_1_3m, _25m, _20m -> 1;
            case _36_2_3y, _27_1_2y -> 1.006041;
            case _33_1_3y, _25y, _20y -> switch (swimEvent) {
                case _50_FREE, _50_BREAST, _50_FLY, _50_BACK -> 0.91147;
                case _100_FREE -> 0.91087;
                case _200_FREE -> 0.91157;
                case _400_FREE -> 0.91197;
                case _800_FREE -> 0.91217;
                case _1500_FREE -> 1.004155;
                case _100_BREAST -> 0.90895;
                case _200_BREAST, _100_FLY -> 0.91097;
                case _200_FLY -> 0.91177;
                case _100_BACK -> 0.91187;
                case _200_BACK -> 0.91247;
                case _200_IM -> 0.90443;
                case _400_IM -> 0.91046;
            };
        };
    }


    /**
     * @return Swim distance
     */
    private int getDistance() {
        return switch (swimEvent) {
            case _50_FREE, _50_BREAST, _50_FLY, _50_BACK -> 50;
            case _100_FREE, _100_BREAST, _100_FLY, _100_BACK -> 100;
            case _200_FREE, _200_BREAST, _200_FLY, _200_BACK, _200_IM -> 200;
            case _400_FREE, _400_IM -> 400;
            case _800_FREE -> 800;
            case _1500_FREE -> 1500;
        };
    }

    private double getNumberTurnFactor() {
        double D1 = getImperialDistance();
        double numberTurnFactor = getDistance() / 100 * (D1 / 100) * (getNumberOfTurnsPer100(targetPool) - 1);
        System.out.println("numberTurnFactor = " + numberTurnFactor);
        return numberTurnFactor;
    }

    private int getImperialDistance() {
        if (getDistance() == 1500 && (sourcePool == PoolLength._33_1_3y || sourcePool == PoolLength._25y || sourcePool == PoolLength._20y)) {
            return 1650;
        } else {
            return getDistance();
        }
    }

    public double convert() {
        double turnValue = swimEvent.getTurnFactor() / longCourseTime * getDistance() / 100;
        distanceTime = longCourseTime * getPoolMeasure(targetPool);
        turnTime = (turnValue * getImperialDistance() / 100) * (getNumberOfTurnsPer100(targetPool) - 1);
        convertedTime = distanceTime - turnTime + 0.05;
        convertedTime = Math.floor(convertedTime*10)/10;
        return convertedTime;

    }

    @Override
    public String toString() {
        return String.format("SwimTimeConverter\nEvent: %s\nFrom Pool Length: %s\nTo Pool Length: %s\nSource Time: %.2f seconds" +
                        "\nLong Course Time: %.2f seconds\nDistance Time: %.2f\nTurn Time: %.2f\nConverted Time: %.2f",
                swimEvent.getDisplayName(),
                sourcePool.getDisplayName(),
                targetPool.getDisplayName(),
                sourceTime,
                longCourseTime,
                distanceTime,
                turnTime,
                convertedTime);


    }
}
