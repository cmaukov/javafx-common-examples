package com.bmstechpro.javafxexamples.pooltime.conv;
/* javafx-examples
 * @created 01/16/2023
 * @author Konstantin Staykov
 */

public class SwimTime {
    private final double time;
    private final int m;
    private final int s;
    private final int ms;

    public SwimTime(int s, int ms) {
        this(0, s, ms);
    }

    public SwimTime(int m, int s, int ms) {
        this.m = m;
        this.s = s;
        this.ms = ms;
        this.time = m * 60 + s + (double)ms / 100;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Swim Time: "+m+":"+s+":"+ms;
    }
}
