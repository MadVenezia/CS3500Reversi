package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.position.Position;
import cs3500.reversi.model.provider.HexCoordinate;
import cs3500.reversi.model.provider.PlayerSymbol;
import cs3500.reversi.model.provider.ReadOnlyReversiModel;

/**
 * Adapter class to adapt the provided {@code ReversiModel} to our {@code ReversiModelOur}.
 */
public class ReversiModelAdapter implements ReadOnlyReversiModel {

  private final ReversiModelOur reversiModel;
  private final Player p1;
  private final Player p2;
  private final ArbitraryCell[][] cells;

  /**
   * Constructs a {@code ReversiModelAdapter} with the provided {@code ReversiModel}.
   *
   * @param reversiModel the provided Reversi model to be adapted.
   */
  public ReversiModelAdapter(ReversiModelOur reversiModel, Player p1, Player p2) {
    this.p1 = p1;
    this.p1.setPlayerColor("X");
    this.p2 = p2;
    this.p2.setPlayerColor("O");
    this.p1.setOpponent(this.p2);
    this.p2.setOpponent(this.p1);
    boolean gameStarted = false;
    this.reversiModel = reversiModel;
    this.cells = new ArbitraryCell[reversiModel.getBoardSize()][];
  }


  private HexCoordinate convertToProviderCoordinates(ArbitraryCell cell) {
    return new HexCoordinate(cell.getPosition().getQ(),
            cell.getPosition().getR(), cell.getPosition().getS());
  }


  @Override
  public Optional<PlayerSymbol> getPlayerOnTile(HexCoordinate location)
          throws IllegalArgumentException {
    return Optional.empty();
  }

  @Override
  public PlayerSymbol getTurn() throws IllegalStateException {
    if (reversiModel.getPlayer() == this.p1) {
      return PlayerSymbol.BLACK;
    } else {
      return PlayerSymbol.WHITE;
    }
  }

  @Override
  public boolean isGameOver() {
    return reversiModel.isGameOver();
  }

  private PlayerSymbol getPlayerSymbol() {
    if (this.p1.getTurn()) {
      return PlayerSymbol.BLACK;
    } else {
      return PlayerSymbol.WHITE;
    }
  }

  @Override
  public int getBoardSize() {
    return reversiModel.getBoardSize();
  }

  @Override
  public boolean moveValid(HexCoordinate location, PlayerSymbol player)
          throws IllegalArgumentException {
    return false;
  }

  @Override
  public int potentialDiscsToGain(HexCoordinate location, PlayerSymbol player) {
    return 0;
  }

  @Override
  public int getScore(PlayerSymbol player) {
    if (player == PlayerSymbol.BLACK) {
      return this.p1.getScore();
    } else {
      return this.p2.getScore();
    }
  }

  @Override
  public boolean doesPlayerHaveMoves(PlayerSymbol player) {
    if (player == PlayerSymbol.BLACK) {
      return reversiModel.getLegalMoves(this.p1).isEmpty();
    } else {
      return reversiModel.getLegalMoves(this.p2).isEmpty();
    }
  }

  @Override
  public List<HexCoordinate> getNeighbors(HexCoordinate location) {
    List<HexCoordinate> neighbors = new ArrayList<>();
    int[][] neighborDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1},
        {1, -1}, {1, 1}, {-1, -1}};
    int q = location.q;
    int r = location.r;
    int s = location.s;
    for (int[] neighborDirection : neighborDirections) {
      for (Map.Entry<Integer, Integer> entry : reversiModel.getCellCoordinatesFromGrid()) {
        if (reversiModel.getCell(entry.getKey(), entry.getValue()).getPosition().getQ() == q
                && reversiModel.getCell(entry.getKey(), entry.getValue()).getPosition().getR() == r
                && reversiModel.getCell(entry.getKey(), entry.getValue()).getPosition().getS() == s
                && !reversiModel.getCell(entry.getKey(), entry.getValue()).isEmpty()) {
          Position posToConvert = cells[entry.getKey() + neighborDirection[0]]
                  [entry.getValue() + neighborDirection[1]].getPosition();
          HexCoordinate neighborCoord = new HexCoordinate(posToConvert.getQ(),
                  posToConvert.getR(), posToConvert.getS());
          neighbors.add(neighborCoord);
        }
      }
    }
    return neighbors;
  }


}
