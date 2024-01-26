package cs3500.reversi.model.piece;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.position.EmptyPosition;
import cs3500.reversi.model.position.Position;

/**
 * Class to represent an empty piece in a game of Reversi (that is, a non-existent piece)
 * to avoid null pointer exceptions.
 */

public class EmptyPiece implements ArbitraryPiece {

  @Override
  public void changeColor(String string) {
    // empty because we will never need to change the color of an empty piece.
  }

  @Override
  public void updatePosition(ArbitraryCell c) {
    // empty because we will never need to update the position of an empty piece.
  }

  @Override
  public Position getPoint() {
    return new EmptyPosition();
  }

  @Override
  public String getColor() {
    return "";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof EmptyPiece;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public String toString() {
    return "empty piece";
  }
}