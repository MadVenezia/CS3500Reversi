package cs3500.reversi.model.position;

import cs3500.reversi.model.provider.HexCoordinate;

/**
 * Adapter class to make HexCoordinate compatible with the Position class.
 */
public class HexCoordinateAdapter implements Position {

  private final HexCoordinate hexCoordinate;

  public HexCoordinateAdapter(HexCoordinate hexCoordinate) {
    this.hexCoordinate = hexCoordinate;
  }

  @Override
  public int getQ() {
    return hexCoordinate.q;
  }

  @Override
  public void setQ(int newQ) {
    throw new UnsupportedOperationException("Setting Q is not supported for HexCoordinate");
  }

  @Override
  public int getR() {
    return hexCoordinate.r;
  }

  @Override
  public void setR(int newR) {
    throw new UnsupportedOperationException("Setting R is not supported for HexCoordinate");
  }

  @Override
  public int getS() {
    return hexCoordinate.s;
  }

  @Override
  public void setS(int newS) {
    throw new UnsupportedOperationException("Setting S is not supported for HexCoordinate");
  }


  @Override
  public String toString() {
    return hexCoordinate.toString();
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
    // unneeded here
  }

  @Override
  public void setY(int newY) {
    // unneeded here
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o == null || getClass() != o.getClass()) {
      return false;
    }

    HexCoordinateAdapter that = (HexCoordinateAdapter) o;
    return hexCoordinate.equals(that.hexCoordinate);
  }


  @Override
  public int hashCode() {
    return hexCoordinate.hashCode();
  }
}
