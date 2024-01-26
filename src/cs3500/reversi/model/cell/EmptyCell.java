package cs3500.reversi.model.cell;

import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.piece.EmptyPiece;
import cs3500.reversi.model.position.EmptyPosition;
import cs3500.reversi.model.position.Position;

/**
 * Class to represent an empty cell in the case of a cell not existing on the board in Reversi.
 */
public class EmptyCell implements ArbitraryCell {

  @Override
  public Position getPosition() {
    return new EmptyPosition();
  }

  @Override
  public boolean isOccupied() {
    return false;
  }

  @Override
  public ArbitraryPiece getPiece() {
    return new EmptyPiece();
  }

  @Override
  public void setPiece(ArbitraryPiece piece) {
    // no piece to set since the cell is empty
  }

  @Override
  public ArbitraryCell copy() {
    return new EmptyCell();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof EmptyCell;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
