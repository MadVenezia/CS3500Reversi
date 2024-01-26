package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cs3500.reversi.model.cell.EmptyCell;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.MachinePlayer;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.strategy.CaptureManyPieces;

/**
 * Mock Reversi model to use for testing strategies.
 */

public class MockHexReversi implements ReversiModelOur {

  final StringBuilder log;
  private List<ArbitraryCell> legalMoves;
  private int capturedPieces;

  /**
   * To represent a mock Reversi game.
   * @param log to display that the functions are being called
   * @param legalMoves list of legal moves as specified in testing
   * @param capturedPieces list of captured pieces as specified in testing
   */
  public MockHexReversi(StringBuilder log, List<ArbitraryCell> legalMoves, int capturedPieces) {
    this.log = Objects.requireNonNull(log);
    this.legalMoves = legalMoves;
    this.capturedPieces = capturedPieces;
  }

  @Override
  public void startGame() {
    // no need to append log since any other method running means the game started
  }

  @Override
  public void movePiece(Player player, ArbitraryCell newCell) {
    log.append(String.format("Player moved to " + newCell.getPosition().toString()) + "\n");
  }

  @Override
  public void passTurn() {
    log.append("Turn passed\n");
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public ArbitraryCell getCell(int x, int y) {
    log.append(String.format("Getting cell at x = %d, y = %d\n", x, y));
    return new EmptyCell();
  }

  @Override
  public List<Map.Entry<Integer, Integer>> getCellCoordinatesFromGrid() {
    return new ArrayList<>();
  }

  @Override
  public List<Map.Entry<Integer, Integer>> getOccupiedCells() {
    return new ArrayList<>();
  }

  @Override
  public Player getPlayer() {
    return new MachinePlayer(new CaptureManyPieces());
  }

  @Override
  public String getBoardStringRepresentation() {
    return "";
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public ReversiModelOur copy() {
    return this;
  }

  @Override
  public List<ArbitraryCell> getLegalMoves(Player player) {
    log.append(String.format("Moves the black piece to (%d, %d, %d) as specified"
            + " by CaptureManyPieces and MockReversi\n", legalMoves.get(0).getPosition().getQ(),
            legalMoves.get(0).getPosition().getR(), legalMoves.get(0).getPosition().getS()));
    return legalMoves;
  }

  @Override
  public int countCapturedPieces(Player player) {
    return capturedPieces;
  }

  @Override
  public boolean isGameStarted() {
    return false;
  }

  @Override
  public void setListener(PlayerListener playerListener) {
    //not supported
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
