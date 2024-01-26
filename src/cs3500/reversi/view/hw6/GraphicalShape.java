package cs3500.reversi.view.hw6;

import java.awt.geom.Path2D;


/**
* Represents a graphical shape that can be rendered on a game board.
*/
public interface GraphicalShape {

    /**
     * Creates a graphical shape at the specified center coordinates and size.
     *
     * @param centerX the x-coordinate of the center of the shape.
     * @param centerY the y-coordinate of the center of the shape.
     * @param size    the size of the shape.
     * @return a {@code Path2D.Double} representing the graphical shape.
     */
    Path2D.Double createShape(double centerX, double centerY, int size);

  /**
   * Checks if the graphical shape is a hexagon.
   *
   * @return {@code true} if the shape is a hexagon, {@code false} otherwise.
   */
  boolean isHexagon();
}
