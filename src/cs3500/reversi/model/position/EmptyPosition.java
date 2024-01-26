package cs3500.reversi.model.position;

/**
 * To represent a position that doesn't exist in our coordinate system, as created in the
 * PositionInterface and expanded on in the Position class.
 */

public class EmptyPosition implements Position {

  @Override
  public int getQ() {
    return 0;
  }

  @Override
  public void setQ(int newQ) {
    // empty because we will never be setting Q of an empty position
  }

  @Override
  public int getR() {
    return 0;
  }

  @Override
  public void setR(int newR) {
    // empty because we will never be setting R of an empty position
  }

  @Override
  public int getS() {
    return 0;
  }

  @Override
  public void setS(int newS) {
    // empty because we will never be setting S of an empty position
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof EmptyPosition;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  public String toString() {
    return "";
  }

  @Override
  public int getX() {
    return 0;
  }

  @Override
  public int getY() {
    return 0;
  }

  @Override
  public void setX(int newX) {
    // empty because there's nothing to set
  }

  @Override
  public void setY(int newY) {
    // empty because there's nothing to set
  }
}
