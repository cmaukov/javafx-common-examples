package com.bmstechpro.javafxexamples.reflect;
/* javafx-examples
 * @created 01/12/2023
 * @author Konstantin Staykov
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectApp {

    public static void main(String[] args) throws Exception {
//        String s = "Hello world";
//
//        Field field = String.class.getDeclaredField("value");
//        field.setAccessible(true);
//        field.set(s, "Bye world".toCharArray());
//
//        System.out.println("Begin");
//        System.out.println(s);
//        System.out.println("End");
        Method method = Class1.class.getDeclaredMethod("someMethod");
        method.setAccessible(true);
        method.invoke(null);
    }










    /*
    Method method = Class1.class.getDeclaredMethod("someMethod");
        method.setAccessible(true);
        method.invoke(null);
     */
}
