package cs3500.reversi.model.player;

import cs3500.reversi.model.strategy.ReversiStrategyOurs;

/**
 * Class to represent a Machine player in a game of Reversi.
 */

public class MachinePlayer extends AbstractPlayer {

  private ReversiStrategyOurs strategy;

  /**
   * Class to represent a Machine player in a game of Reversi.
   */

  public MachinePlayer(ReversiStrategyOurs strategy) {
    super();
    this.strategy = strategy;
  }



  @Override
  public ReversiStrategyOurs getStrategy() {
    return this.strategy;
  }

  @Override
  public void setStrategy(ReversiStrategyOurs strategy) {
    this.strategy = strategy;
  }
}
