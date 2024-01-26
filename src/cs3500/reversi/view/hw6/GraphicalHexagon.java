package cs3500.reversi.view.hw6;

import java.awt.geom.Path2D;

/**
 * Represents a graphical hexagon used for rendering in the Reversi game GUI.
 */
public class GraphicalHexagon implements GraphicalShape {

  /**
   * Creates and returns a hexagon as a Path2D.Double at the specified center coordinates.
   *
   * @param centerX the x-coordinate of the center.
   * @param centerY the y-coordinate of the center.
   * @param size    the size of the hexagon.
   * @return a Path2D.Double representing the hexagon.
   */
  public Path2D.Double createShape(double centerX, double centerY, int size) {
    Path2D.Double hexagon = new Path2D.Double();

    for (int i = 0; i < 6; i++) {
      double angle = 2.0 * Math.PI / 6 * i + Math.PI / 6;  // Rotate by 30 degrees (Math.PI / 6)
      double x = centerX + size * Math.cos(angle);
      double y = centerY + size * Math.sin(angle);

      if (i == 0) {
        hexagon.moveTo(x, y);
      } else {
        hexagon.lineTo(x, y);
      }
    }

    hexagon.closePath();
    return hexagon;
  }

  @Override
  public boolean isHexagon() {
    return true;
  }
}