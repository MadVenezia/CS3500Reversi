package cs3500.reversi.model.cell;

import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.piece.EmptyPiece;
import cs3500.reversi.model.piece.Piece;
import cs3500.reversi.model.position.Position;

/**
 * Represents a cell in the Reversi game board.
 */
public class Cell implements ArbitraryCell {
  private final Position pos;
  private ArbitraryPiece piece;

  /**
   * Constructs a Cell object with a given position.
   *
   * @param pos The position of the cell on the game board.
   */
  public Cell(Position pos) {
    this.pos = pos;
    this.piece = new EmptyPiece();
  }

  public Position getPosition() {
    return this.pos;
  }

  public boolean isOccupied() {
    return this.piece instanceof Piece;
  }

  public ArbitraryPiece getPiece() {
    return this.piece;
  }

  public void setPiece(ArbitraryPiece piece) {
    this.piece = piece;
  }

  public String toString() {
    return this.pos.toString();
  }

  @Override
  public ArbitraryCell copy() {
    Cell copy = new Cell(this.pos);
    copy.setPiece(this.piece);
    return copy;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Cell) {
      Cell c = (Cell) o;
      return this.pos.equals(c.pos) && this.piece.equals(c.piece);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.pos.hashCode() + this.piece.hashCode();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}
