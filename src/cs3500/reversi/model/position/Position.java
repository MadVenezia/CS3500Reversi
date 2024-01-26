package cs3500.reversi.model.position;

/**
 * PositionInterface to represent position coordinates for cells and pieces in a game of Reversi.
 * It could be either empty or non-empty, empty meaning it doesn't exist, non-empty meaning it does.
 */

public interface Position {

  /**
   * Get the Q coordinate for this Position.
   * @return the Q coordinate as specified in the constructor
   */
  int getQ();

  /**
   * Sets the new Q coordinate for this Position.
   * @param newQ the new Q coordinate to set this Position to
   */
  void setQ(int newQ);

  /**
   * Get the R coordinate for this Position.
   * @return the R coordinate as specified in the constructor
   */

  int getR();

  /**
   * Sets the new R coordinate for this Position.
   * @param newR the new R coordinate to set this Position to
   */

  void setR(int newR);

  /**
   * Gets the S coordinate for this Position.
   * @return the S coordinate for this Position as specified in the constructor
   */

  int getS();

  /**
   * Sets the new S coordinate for this Position.
   * @param newS the new S coordinate to set this Position to
   */

  void setS(int newS);

  /**
   * Converts this Position into a readable String format.
   * @return the Position in String form
   */

  String toString();

  /**
   * Gets the X Coordinate for an XY position.
   * @return the X coordinate for this position as specified in the constructor
   */
  int getX();

  /**
   * Gets the Y Coordinate for an XY position.
   * @return the Y coordinate for this position as specified in the constructor
   */

  int getY();

  /**
   * Sets the X coordinate for an XY position.
   * @param newX the new X coordinate to set this XY position to
   */

  void setX(int newX);

  /**
   * Sets the Y coordinate for an XY position.
   * @param newY the new Y coordinate to set this XY position to
   */

  void setY(int newY);
}
