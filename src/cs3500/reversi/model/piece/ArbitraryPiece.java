package cs3500.reversi.model.piece;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.position.Position;

/**
 * Interface to represent a generic/arbitrary piece in a game of Reversi.
 */

public interface ArbitraryPiece {

  /**
   * Changes the "color" of this Piece (X or O for black or white).
   *
   * @param string the "color" to change to.
   */

  void changeColor(String string);

  /**
   * Updates the position of this Piece.
   *
   * @param c the new cell position of this Piece.
   */

  void updatePosition(ArbitraryCell c);

  /**
   * Gets the position this piece is at.
   *
   * @return the xy coordinate the piece is at.
   */

  Position getPoint();

  /**
   * Gets the color of this piece.
   * @return the color of this piece (X for black, O for white).
   */

  String getColor();

  /**
   * Converts the piece into a readable String.
   * @return the piece as a String
   */

  String toString();

  /**
   * Overrides the equals function for arbitrary Pieces.
   * @param o the object to compare the piece to
   * @return whether this arbitrary piece equals that object
   */
  @Override
  boolean equals(Object o);

  /**
   * Overrides the hashCode function for arbitrary Pieces.
   * @return the new assigned hashCode for this arbitrary Piece
   */
  @Override
  int hashCode();
}