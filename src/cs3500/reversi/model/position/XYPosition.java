package cs3500.reversi.model.position;

/**
 * To represent the XY coordinate system used in a game of square Reversi.
 */
public class XYPosition implements Position {
  private int x;
  private int y;

  /**
   * To represent an XYPosition.
   * @param x the x-coordinate
   * @param y the y-coordinate
   */
  public XYPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getQ() {
    throw new IllegalStateException("No Q coordinate in an XY position");
  }

  @Override
  public void setQ(int newQ) {
    throw new IllegalStateException("No Q coordinate in an XY position");
  }

  @Override
  public int getR() {
    throw new IllegalStateException("No R coordinate in an XY position");
  }

  @Override
  public void setR(int newR) {
    throw new IllegalStateException("No R coordinate in an XY position");
  }

  @Override
  public int getS() {
    throw new IllegalStateException("No S coordinate in an XY position");
  }

  @Override
  public void setS(int newS) {
    throw new IllegalStateException("No S coordinate in an XY position");
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setX(int newX) {
    this.x = newX;
  }

  @Override
  public void setY(int newY) {
    this.y = newY;
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
