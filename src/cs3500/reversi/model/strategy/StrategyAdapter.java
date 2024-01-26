package cs3500.reversi.model.strategy;

import cs3500.reversi.model.ReversiModelAdapter;
import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.cell.EmptyCell;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.provider.HexCoordinate;
import cs3500.reversi.model.provider.PlayerSymbol;
import cs3500.reversi.model.provider.ReadOnlyReversiModel;
import cs3500.reversi.model.strategy.provider.ReversiStrategy;

import java.util.List;
import java.util.Map;

/**
 * An adapter class that adapts a ReversiStrategy to the ReversiStrategyOurs interface.
 */
public class StrategyAdapter implements ReversiStrategyOurs {

  private final ReversiStrategy providerStrategy;

  /**
   * Constructs a StrategyAdapter with the given ReversiStrategy.
   *
   * @param providerStrategy the ReversiStrategy to adapt
   */
  public StrategyAdapter(ReversiStrategy providerStrategy) {
    this.providerStrategy = providerStrategy;
  }

  /**
   * Chooses a move based on the adapted ReversiStrategy.
   *
   * @param model  the ReversiModelOur instance
   * @param player the player making the move
   * @return the chosen move as an ArbitraryCell
   */
  @Override
  public ArbitraryCell chooseMove(ReversiModelOur model, Player player) {
    PlayerSymbol symbol = null;
    if (player.getPlayerColor().equals("X")) {
      symbol = PlayerSymbol.BLACK;
    } else if (player.getPlayerColor().equals("O")) {
      symbol = PlayerSymbol.WHITE;
    }
    if (model instanceof ReadOnlyReversiModel && player.getStrategy() instanceof StrategyAdapter) {
      ReversiModelAdapter providedModel = (ReversiModelAdapter) model;
      List<HexCoordinate> hexList = providerStrategy.chooseHexCoord(providedModel, symbol);

      HexCoordinate theirHex = hexList.get(0);

      for (Map.Entry<Integer, Integer> entry : model.getCellCoordinatesFromGrid()) {
        if (model.getCell(entry.getKey(), entry.getValue()).getPosition().getQ()
                == theirHex.q
                && model.getCell(entry.getKey(), entry.getValue()).getPosition().getR()
                == theirHex.r
                && model.getCell(entry.getKey(), entry.getValue()).getPosition().getS()
                == theirHex.s) {
          return model.getCell(entry.getKey(), entry.getValue());
        }
      }
    }
    return new EmptyCell();
  }
}
