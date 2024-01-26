package cs3500.reversi.view.hw6;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.provider.HexCoordinate;
import cs3500.reversi.view.hw6.provider.ReversiFeatures;

/**
 * An adapter class that adapts ReversiFeatures to the PlayerActions interface.
 */
public class ReversiFeaturesToPlayerActionsAdapter implements PlayerActions {

  private final ReversiFeatures features;
  private final ReversiModelOur model;

  /**
   * Constructs a ReversiFeaturesToPlayerActionsAdapter with the given ReversiFeatures and model.
   *
   * @param features the ReversiFeatures to adapt
   * @param model    the ReversiModelOur instance
   */
  public ReversiFeaturesToPlayerActionsAdapter(ReversiFeatures features, ReversiModelOur model) {
    this.features = features;
    this.model = model;
  }

  /**
   * Responds to a key press event.
   *
   * @param keyCode       the key code associated with the pressed key
   * @param selectedRow   the selected row on the game board
   * @param selectedCol   the selected column on the game board
   */
  @Override
  public void keyPressed(int keyCode, int selectedRow, int selectedCol) {
    HexCoordinate location = convertToHexCoordinate(selectedRow, selectedCol);

    switch (keyCode) {
      case KeyEvent.VK_P:
        features.passTurn();
        break;
      case KeyEvent.VK_ENTER:
        features.placeDisc(location);
        break;
      default:
        break;
    }
  }

  /**
   * Responds to a mouse press event.
   *
   * @param e   the MouseEvent associated with the mouse press
   * @param row the selected row on the game board
   * @param col the selected column on the game board
   */
  @Override
  public void mousePressed(MouseEvent e, int row, int col) {
    // If the ReversiFeatures interface has no methods that respond to mouse events
  }

  private HexCoordinate convertToHexCoordinate(int row, int col) {
    List<Map.Entry<Integer, Integer>> cellCoords = model.getCellCoordinatesFromGrid();

    for (Map.Entry<Integer, Integer> cellCoord : cellCoords) {
      if (cellCoord.getKey() == row && cellCoord.getValue() == col) {

        return new HexCoordinate(model.getCell(row, col).getPosition().getQ(),
                model.getCell(row, col).getPosition().getR(),
                model.getCell(row, col).getPosition().getS());
      }
    }

    return null;
  }
}
