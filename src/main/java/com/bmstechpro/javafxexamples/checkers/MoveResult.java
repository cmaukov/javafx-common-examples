package com.bmstechpro.javafxexamples.checkers;
/* javafx-examples
 * @created 01/08/2023
 * @author Konstantin Staykov
 */

public class MoveResult {
    private MoveType type;

    public MoveType getType() {
        return type;
    }

    private Piece piece;

    public Piece getPiece() {
        return piece;
    }

    public MoveResult(MoveType type) {
        this(type, null);
    }

    public MoveResult(MoveType type, Piece piece) {
        this.type = type;
        this.piece = piece;
    }
}
