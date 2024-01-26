package cs3500.reversi.model.cell;

import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.position.Position;

/**
 * To represent a generic/arbitrary Cell in a game of Reversi.
 */
public interface ArbitraryCell {

  /**
   * Get the position of the cell.
   *
   * @return The position of the cell.
   */

  Position getPosition();

  /**
   * Checks if the cell is occupied by a game piece.
   *
   * @return True if the cell is occupied, otherwise false.
   */

  boolean isOccupied();

  /**
   * Get the piece currently occupying the cell.
   *
   * @return The piece in the cell or null if unoccupied.
   */

  ArbitraryPiece getPiece();

  /**
   * Set a piece in the cell.
   *
   * @param piece The piece to be placed in the cell.
   */

  void setPiece(ArbitraryPiece piece);

  /**
   * Provides a string representation of the cell.
   *
   * @return A string representation of the cell's position.
   */

  String toString();

  /**
   * Copies this arbitrary cell.
   * @return this cell as a copy of itself
   */
  ArbitraryCell copy();

  boolean isEmpty();
}
