package cs3500.reversi.model.piece;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.position.EmptyPosition;
import cs3500.reversi.model.position.Position;

/**
 * Class to represent a Piece in the game of Reversi.
 */

public class Piece implements ArbitraryPiece {
  private String color;
  private Position point;

  /**
   * To represent a Piece. Each Piece has a color and a point, but the point only gets initialized
   * once the piece is added to the board.
   */
  public Piece() {
    this.color = "";
    this.point = new EmptyPosition();
  }

  public void changeColor(String string) {
    this.color = string;
  }

  public void updatePosition(ArbitraryCell c) {
    this.point = c.getPosition();
    c.setPiece(this);
  }

  public Position getPoint() {
    return this.point;
  }

  public String getColor() {
    return this.color;
  }


  public String toString() {
    return color + point.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Piece) {
      Piece p = (Piece) o;
      return this.color.equals(p.color) && this.point.equals(p.point);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.color.hashCode() + this.point.hashCode();
  }
}