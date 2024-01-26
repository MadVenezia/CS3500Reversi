package cs3500.reversi.view.hw6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;

import cs3500.reversi.model.ReversiModelOur;

/**
 * Represents the panel for rendering the Reversi game.
 */
public class Panel extends JPanel implements KeySubject {

  private static int selectedRow = -1;
  private static int selectedCol = -1;
  private final ReversiModelOur model;


  private final ArrayList<PlayerActions> observers = new ArrayList<>();

  private final GraphicalShape graphicalShape;

  /**
   * Constructs a Panel for the Reversi game.
   *
   * @param model the ReversiModelOur to be rendered.
   */
  public Panel(ReversiModelOur model, GraphicalShape graphicalShape) {
    this.model = model;
    this.graphicalShape = graphicalShape;
    this.setPreferredSize(new Dimension(800, 800));

    addMouseListener(new HexagonMouseListener());

    addKeyListener(new HexagonKeyListener());
    setFocusable(true);
    requestFocusInWindow();
  }

  public void updateView() {
    repaint();
  }

  /**
   * Notifies all registered observers of a key event.
   *
   * @param keyCode     the key code associated with the pressed key.
   * @param selectedRow the row index of the selected hexagon.
   * @param selectedCol the column index of the selected hexagon.
   */
  @Override
  public void notifyObservers(int keyCode, int selectedRow, int selectedCol) {
    for (PlayerActions observer : observers) {
      observer.keyPressed(keyCode, selectedRow, selectedCol);
    }
  }

  /**
   * Notifies all registered observers of a mouse event.
   *
   * @param e   the MouseEvent object representing the mouse event.
   * @param row the row index of the selected hexagon.
   * @param col the column index of the selected hexagon.
   */
  @Override
  public void mouseNotifyObservers(MouseEvent e, int row, int col) {
    for (PlayerActions observer : observers) {
      observer.mousePressed(e, row, col);
    }
  }

  /**
   * Adds a key observer to the list of registered observers.
   *
   * @param observer the key observer to be added.
   */
  @Override
  public void addKeyObserver(PlayerActions observer) {
    observers.add(observer);
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    String boardString = model.getBoardStringRepresentation();
    int hexSize = calculateHexSize();

    String[] rows = boardString.split(" \n");

    for (int row = 0; row < rows.length; row++) {
      String hexes = rows[row].trim();
      String[] cells = hexes.split("  ");

      for (int col = 0; col < cells.length; col++) {
        String cellValue = cells[col];
        double x;
        double y;
        if (graphicalShape.isHexagon()) {
          x = calculateHexX(col, row, hexSize);
          y = calculateHexY(row, hexSize);
        } else {
          x = 45 * col + 45 / 2;
          y = 45 * row + 45 / 2;
        }


        g2d.setColor(Color.LIGHT_GRAY);
        if (graphicalShape.isHexagon()) {
          drawHexagon(g2d, x, y, cellValue, row, col, hexSize);
        } else if (!graphicalShape.isHexagon()) {
          drawSquare(g2d, x, y, cellValue, hexSize);
        }
      }
    }
  }


  private void drawHexagon(Graphics2D g2d, double x, double y, String cellValue,
                           int row, int col, int hexSize) {

    g2d.setColor(Color.LIGHT_GRAY);
    GraphicalHexagon hex = new GraphicalHexagon();
    Path2D.Double hexagonPath = hex.createShape(x, y, hexSize);


    if (row == selectedRow && col == selectedCol) {
      System.out.print(":)");
      g2d.setColor(Color.MAGENTA);
      g2d.fill(hexagonPath);
    } else {
      g2d.setColor(Color.LIGHT_GRAY);
      g2d.fill(hexagonPath);
    }

    g2d.setColor(Color.DARK_GRAY);
    g2d.draw(hexagonPath);


    if (cellValue.equals("O")) {
      g2d.setColor(Color.WHITE);
      g2d.fillOval((int) (x - hexSize / 2), (int) (y - hexSize / 2), hexSize, hexSize);
    } else if (cellValue.equals("X")) {
      g2d.setColor(Color.BLACK);
      g2d.fillOval((int) (x - hexSize / 2), (int) (y - hexSize / 2), hexSize, hexSize);
    }

    String boardString = model.getBoardStringRepresentation();

    int shapeSize = calculateHexSize();

    String[] rows = boardString.split(" \n");

  }


  private void drawSquare(Graphics2D g2d, double x, double y, String cellValue, int hexSize) {
    // Set the color for the square
    g2d.setColor(Color.LIGHT_GRAY);

    // Create an instance of GraphicalSquare
    GraphicalSquare square = new GraphicalSquare();

    // Assuming createShape() method exists in your GraphicalSquare class
    Path2D.Double squarePath = square.createShape(x, y, 45);


    // Draw the outline of the square
    g2d.setColor(Color.LIGHT_GRAY);
    g2d.fill(squarePath);
    g2d.setColor(Color.DARK_GRAY);
    g2d.draw(squarePath);

    // Highlight the selected cell with magenta color
    if ((int) (y / 45) == selectedRow && (int) (x / 45) == selectedCol) {
      g2d.setColor(Color.MAGENTA);
      g2d.fill(squarePath);
    } else {
      // Fill the square with the specified color
      g2d.setColor(Color.LIGHT_GRAY);
      g2d.fill(squarePath);
    }

    // Draw the outline of the square
    g2d.setColor(Color.DARK_GRAY);
    g2d.draw(squarePath);

    if (Objects.equals(cellValue, "O")) {
      g2d.setColor(Color.WHITE);
      g2d.fillOval((int) x - 15, (int) y - 15, hexSize, hexSize);
    } else if (Objects.equals(cellValue, "X")) {
      g2d.setColor(Color.BLACK);
      g2d.fillOval((int) x - 15, (int) y - 15, hexSize, hexSize);
    }

  }


  // Helper methods for calculating dimensions and positions

  /**
   * Calculates the panel width.
   *
   * @return the panel width.
   */
  private int calculatePanelWidth() {
    return getWidth();
  }

  /**
   * Calculates the panel height.
   *
   * @return the panel height.
   */
  private int calculatePanelHeight() {
    return getHeight();
  }

  /**
   * Calculates the hexagon size.
   *
   * @return the hexagon size.
   */
  private int calculateHexSize() {
    return 30;
  }

  /**
   * Calculates the x-coordinate of a hexagon.
   *
   * @param col     the column index of the hexagon.
   * @param row     the row index of the hexagon.
   * @param hexSize the hexagon size.
   * @return the x-coordinate of the hexagon.
   */
  private double calculateHexX(int col, int row, int hexSize) {
    double offsetX = (calculatePanelWidth()
            - calculateHexWidth(model.getBoardSize(), hexSize)) / 1.5 + 100;
    double shiftX = 0;

    int distanceFromCenter = Math.abs(row - (model.getBoardSize() - 1) / 2);

    if (row % 2 != 0) {
      shiftX += hexSize * Math.sqrt(3.0) / 2 - 25;
    }

    shiftX += distanceFromCenter * hexSize * Math.sqrt(3.0) / 2;

    return col * hexSize * Math.sqrt(3.0) + offsetX + shiftX;
  }

  /**
   * Calculates the y-coordinate of a hexagon.
   *
   * @param row     the row index of the hexagon.
   * @param hexSize the hexagon size.
   * @return the y-coordinate of the hexagon.
   */
  private double calculateHexY(int row, int hexSize) {
    double offsetY = (calculatePanelHeight()
            - calculateHexHeight(model.getBoardSize(), hexSize)) / 2.0;

    double spacingFactor = 1.5;
    return row * hexSize * spacingFactor + offsetY;
  }

  /**
   * Calculates the width of a hexagon.
   *
   * @param numColumns the number of columns in the board.
   * @param hexSize    the hexagon size.
   * @return the width of a hexagon.
   */
  private double calculateHexWidth(int numColumns, int hexSize) {
    return numColumns * hexSize * Math.sqrt(3.0) + (numColumns - 1)
            * hexSize * Math.sqrt(3.0) / 2.0;
  }

  /**
   * Calculates the height of a hexagon.
   *
   * @param numRows the number of rows in the board.
   * @param hexSize the hexagon size.
   * @return the height of a hexagon.
   */
  private double calculateHexHeight(int numRows, int hexSize) {
    return numRows * hexSize * 3 / 2.0;
  }

  /**
   * Inner class representing a mouse listener for hexagon selection.
   */
  private class HexagonMouseListener extends MouseAdapter {

    /**
     * Handles the mouseClicked event, updating the selected row and column based on the mouse
     * click. Notifies registered observers if the selection changes.
     *
     * @param e the MouseEvent object representing the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      int row;
      int col;
      if (graphicalShape.isHexagon()) {
        col = calculateMouseCol(e.getX(), e.getY());
        row = calculateMouseRow(e.getY());
      } else {
        col = e.getX() / 45;
        row = e.getY() / 45;
      }

      if (row >= 0 && row < model.getBoardSize() && col >= 0 && col < model.getBoardSize()) {
        System.out.println("Logical Coordinates: Row=" + row + ", Col=" + col);

        if (selectedRow == row && selectedCol == col) {

          selectedRow = -1;
          selectedCol = -1;
        } else {

          selectedRow = row;
          selectedCol = col;

          mouseNotifyObservers(e, selectedRow, selectedCol);
        }
      } else {

        selectedRow = -1;
        selectedCol = -1;
      }

      repaint();
    }


    /**
     * Calculates the column index of the hexagon based on mouse coordinates.
     *
     * @param mouseX the x-coordinate of the mouse click.
     * @param mouseY the y-coordinate of the mouse click.
     * @return the column index of the hexagon clicked.
     */
    private int calculateMouseCol(int mouseX, int mouseY) {
      int closestCol = -1;
      double closestDistance = Double.MAX_VALUE;

      // Iterate through all hexagons to find the closest one to the mouse click
      for (int c = 0; c < model.getBoardSize(); c++) {
        for (int r = 0; r < model.getBoardSize(); r++) {
          double x = calculateHexX(c, r, calculateHexSize());
          double y = calculateHexY(r, calculateHexSize());
          double distance = Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));

          if (distance < closestDistance) {
            closestDistance = distance;
            closestCol = c;
          }
        }
      }

      return closestCol;
    }

    /**
     * Calculates the row index of the hexagon based on mouse coordinates.
     *
     * @param mouseY the y-coordinate of the mouse click.
     * @return the row index of the hexagon clicked.
     */
    private int calculateMouseRow(int mouseY) {
      int row = -1;

      // Iterate through all rows to find the row containing the mouse click
      for (int r = 0; r < model.getBoardSize(); r++) {
        double y = calculateHexY(r, calculateHexSize());
        if (mouseY >= y - calculateHexSize() / 2.0
                && mouseY <= y + calculateHexSize() * 3 / 2.0 - calculateHexSize() / 2.0) {
          row = r;
          break;
        }
      }

      return row;
    }
  }

  /**
   * Inner class representing a key listener for hexagon selection and actions.
   */
  class HexagonKeyListener implements KeyListener {
    /**
     * Invoked when a key is typed. This method is not used in the current implementation.
     *
     * @param e the KeyEvent object representing the key typed event currently not needed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
      // Implement if needed
    }

    /**
     * Invoked when a key is pressed. Notifies observers of the key press event and
     * handles specific key events such as arrow keys, 'P' for pass, and 'Enter' for confirmation.
     *
     * @param e the KeyEvent object representing the key press event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
      // Get the key code of the pressed key
      int keyCode = e.getKeyCode();

      // Notify observers of the key press event
      notifyObservers(keyCode, selectedRow, selectedCol);

      // Handle specific key events
      switch (keyCode) {
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_RIGHT:
          updateSelectionBasedOnKey(keyCode);
          break;
        case KeyEvent.VK_P:
          pass();
          break;
        case KeyEvent.VK_ENTER:
          enterPressed();
          break;
        default:
          break;
      }
    }

    /**
     * Invoked when a key is released. This method is not used in the current implementation.
     *
     * @param e the KeyEvent object representing the key released event currently not needed.
     */
    @Override
    public void keyReleased(KeyEvent e) {
      // Implement if needed
    }

    /**
     * Updates the selected hexagon based on arrow key input.
     *
     * @param keyCode the key code representing the arrow key pressed.
     */
    private void updateSelectionBasedOnKey(int keyCode) {
      int newRow = selectedRow;
      int newCol = selectedCol;

      // Update the row and column based on the arrow key pressed
      switch (keyCode) {
        case KeyEvent.VK_UP:
          newRow = (selectedRow - 1 + model.getBoardSize()) % model.getBoardSize();
          break;
        case KeyEvent.VK_DOWN:
          newRow = (selectedRow + 1) % model.getBoardSize();
          break;
        case KeyEvent.VK_LEFT:
          newCol = (selectedCol - 1 + model.getBoardSize()) % model.getBoardSize();
          break;
        case KeyEvent.VK_RIGHT:
          newCol = (selectedCol + 1) % model.getBoardSize();
          break;
        default:
          break;
      }

      // Update the selected row and column
      selectedRow = newRow;
      selectedCol = newCol;

      // Repaint the panel to reflect the updated selection
      repaint();
    }

    /**
     * Handles the "Enter" key press event.
     * Notifies observers of the "Enter" key press for debugging will remove.
     */
    private void enterPressed() {
      notifyObservers(KeyEvent.VK_ENTER, selectedRow, selectedCol);
      System.out.println("Enter key pressed");
    }

    /**
     * Handles the "P" key press event.
     * Notifies observers of the "P" key press for debugging purposes will remove.
     */
    private void pass() {
      notifyObservers(KeyEvent.VK_P, 0, 0);
      System.out.println("Turn passed");
    }
  }
}