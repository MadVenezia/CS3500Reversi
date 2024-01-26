package cs3500.reversi.model.strategy;

import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.Player;

/**
 * Strategy interface to be utilized by any Player in picking their optimal strategy,
 * as specified by a class which implements this interface.
 */

public interface ReversiStrategyOurs {
  /**
   * Chooses the next move for the specified player in the given Reversi game model.
   *
   * @param model  the Reversi game model representing the current state of the game.
   * @param player the player for whom the move is to be chosen.
   * @return an {@code ArbitraryCell} representing the chosen move for the player.
   */
  ArbitraryCell chooseMove(ReversiModelOur model, Player player);
}
