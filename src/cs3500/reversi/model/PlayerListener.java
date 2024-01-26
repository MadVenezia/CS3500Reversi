package cs3500.reversi.model;


import cs3500.reversi.model.player.Player;

/**
 * Interface to represent a Player Listener for the Player to listen to Controller actions.
 */
public interface PlayerListener {
  void notifyYourTurn(Player player);

  void addListener(PlayerListener listener);
}
