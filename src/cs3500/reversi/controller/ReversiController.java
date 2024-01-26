package cs3500.reversi.controller;

import cs3500.reversi.model.PlayerListener;
import cs3500.reversi.view.hw6.PlayerActions;

/**
 * The {@code ReversiController} interface represents a controller for the game of Reversi.
 * It extends the {@code PlayerActions} and {@code PlayerListener} interfaces.
 */
public interface ReversiController extends PlayerActions, PlayerListener {

  /**
   * Method to run and play a game of Reversi.
   */
  void playGame();
}
