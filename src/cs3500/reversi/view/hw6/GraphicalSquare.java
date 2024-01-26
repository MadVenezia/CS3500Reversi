package cs3500.reversi.view.hw6;

import java.awt.geom.Path2D;

/**
 * Represents a graphical square that can be rendered on a game board.
 */
public class GraphicalSquare implements GraphicalShape {

  /**
   * Creates a graphical square at the specified center coordinates and size.
   *
   * @param centerX the x-coordinate of the center of the square.
   * @param centerY the y-coordinate of the center of the square.
   * @param size    the size of the square.
   * @return a {@code Path2D.Double} representing the graphical square.
   */
  @Override
  public Path2D.Double createShape(double centerX, double centerY, int size) {
    Path2D.Double square = new Path2D.Double();

    double halfSize = size / 2.0;
    square.moveTo(centerX - halfSize, centerY - halfSize);
    square.lineTo(centerX + halfSize, centerY - halfSize);
    square.lineTo(centerX + halfSize, centerY + halfSize);
    square.lineTo(centerX - halfSize, centerY + halfSize);
    square.closePath();

    return square;
  }

  /**
   * Checks if the graphical shape is a hexagon.
   *
   * @return {@code true} if the shape is a hexagon, {@code false} otherwise.
   */
  @Override
  public boolean isHexagon() {
    return false;
  }
}

