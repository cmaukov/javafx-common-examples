package com.bmstechpro.javafxexamples.equipment;
/* javafx-examples
 * @created 01/13/2023
 * @author Konstantin Staykov
 */

import java.util.Optional;

public class EquipmentParser {
    public static Optional<Equipment> parse(String line) {

        try {
            String[] split = line.split("\t");
            String id = split[0];
            String tag = split[1].trim();
            int qty = Integer.parseInt(split[2].trim());
            String description = split[3];
            String modelNumber = split[4].trim();

            return   Optional.of(new Equipment(tag,qty, modelNumber));

        } catch (Exception e) {
            return Optional.empty();
        }


    }
}
