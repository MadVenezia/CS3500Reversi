package cs3500.reversi.model.position;

/**
 * Represents a position in the standard XY system using cube coordinates (q, r, s).
 * The q, r, s represents the direction of the cell outwards from the middle, which is
 * represented by (0, 0, 0). A q value of 0 indicates that it is diagonal from the center,
 * an r value of 0 indicates that it is horizontal from the center, and an s value of 0
 * indicates that it is vertical from the center.
 * It is important to note that while the position and position of a cell use (q, r, s)
 * coordinates, within the model, these are referred to with (x, y) coordinates.
 * For example, the cells field within the model is a 2D array which only takes in two dimension.,
 * Once the cell is applied to the model, it still retains its (q, r, s) coordinates, but it is
 * primarily referred to within the model with (x, y) coordinates corresponding to its position
 * within the cells array. i.e., a game with board size 9 would have a cell with position (0, 0, 0)
 * in the center, but locally within the model, it would have (x, y) coordinates of (4, 4).
 */
public class HexPosition implements Position {

  private int q;
  private int r;
  private int s;

  /**
   * Creates a position with the given q, r, and s coordinates.
   *
   * @param q The q coordinate.
   * @param r The r coordinate.
   * @param s The s coordinate.
   * @throws IllegalArgumentException if the sum of q, r, and s is not equal to 0.
   */
  public HexPosition(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
    if (q + r + s != 0) {
      throw new IllegalArgumentException("q + r + s must be 0");
    }
  }

  public int getQ() {
    return this.q;
  }

  public void setQ(int newQ) {
    this.q = newQ;
  }

  public int getR() {
    return this.r;
  }

  public void setR(int newR) {
    this.r = newR;
  }

  public int getS() {
    return this.s;
  }

  public void setS(int newS) {
    this.s = newS;
  }

  /**
   * Override the equals function to compare this non-empty Position to.
   *
   * @param o the object to compare this to
   * @return whether this Position is equal to that object
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof HexPosition) {
      HexPosition p = (HexPosition) o;
      return p.q == this.q && p.r == this.r && p.s == this.s;
    }
    return false;
  }

  /**
   * Override the hashCode function for this non-empty Position.
   *
   * @return the new hashCode for this Position
   */

  @Override
  public int hashCode() {
    return q + r + s;
  }

  public String toString() {
    return q + ", " + r + ", " + s;
  }

  @Override
  public int getX() {
    throw new IllegalStateException("No x coordinate for a hexagonal position");
  }

  @Override
  public int getY() {
    throw new IllegalStateException("No y coordinate for a hexagonal position");
  }

  @Override
  public void setX(int newX) {
    throw new IllegalStateException("No x coordinate for a hexagonal position");
  }

  @Override
  public void setY(int newY) {
    throw new IllegalStateException("No y coordinate for a hexagonal position");
  }
}