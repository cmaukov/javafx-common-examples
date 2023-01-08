package com.bmstechpro.javafxexamples.checkers;
/* javafx-examples
 * @created 01/08/2023
 * @author Konstantin Staykov
 */

public enum PieceType {
    RED(1), WHITE(-1);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}
