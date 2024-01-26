package cs3500.reversi.model.player;

import cs3500.reversi.model.strategy.ReversiStrategyOurs;

/**
 * Class to represent a Human player in a game of Reversi.
 */

public class HumanPlayer extends AbstractPlayer {

  /**
   * To represent a Human player.
   */
  public HumanPlayer() {
    super();
  }

  @Override
  public ReversiStrategyOurs getStrategy() {
    throw new IllegalStateException("Human players don't utilize AI strategies");
  }

  @Override
  public void setStrategy(ReversiStrategyOurs strategy) {
    throw new IllegalStateException("Human players don't utilize AI strategies");
  }
}