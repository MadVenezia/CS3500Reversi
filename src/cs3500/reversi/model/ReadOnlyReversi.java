package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.Player;

/**
 * ReadOnlyReversi model for copying and making hypothetical moves.
 */

public class ReadOnlyReversi implements ReversiModelOur {

  private final ReversiModelOur model;


  /**
   * To represent a ReadOnly Reversi model.
   * @param model the model to convert to ReadOnly
   */

  public ReadOnlyReversi(ReversiModelOur model) {
    this.model = model;
  }

  @Override
  public void startGame() {
    // nothing to implement
  }

  @Override
  public void movePiece(Player player, ArbitraryCell newCell) {
    // nothing to implement
  }

  @Override
  public void passTurn() {
    // nothing to implement
  }

  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  @Override
  public ArbitraryCell getCell(int x, int y) {
    return model.getCell(x, y);
  }

  @Override
  public List<Map.Entry<Integer, Integer>> getCellCoordinatesFromGrid() {
    return model.getCellCoordinatesFromGrid();
  }

  @Override
  public List<Map.Entry<Integer, Integer>> getOccupiedCells() {
    return model.getOccupiedCells();
  }

  @Override
  public Player getPlayer() {
    return model.getPlayer();
  }

  @Override
  public String getBoardStringRepresentation() {
    return model.getBoardStringRepresentation();
  }

  @Override
  public int getBoardSize() {
    return model.getBoardSize();
  }

  @Override
  public ReversiModelOur copy() {
    return new ReadOnlyReversi(model.copy());
  }

  @Override
  public List<ArbitraryCell> getLegalMoves(Player player) {
    return model.getLegalMoves(player);
  }

  @Override
  public int countCapturedPieces(Player player) {
    return model.countCapturedPieces(player);
  }

  @Override
  public boolean isGameStarted() {
    return model.isGameStarted();
  }

  @Override
  public void setListener(PlayerListener playerListener) {
    model.setListener(playerListener);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ReadOnlyReversi) {
      ReadOnlyReversi r = (ReadOnlyReversi) o;
      return r.model == this.model;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.model.hashCode();
  }

  @Override
  public String getWinner() {
    return "";
  }

  @Override
  public void nextTurn() {
    // not needed
  }
}
