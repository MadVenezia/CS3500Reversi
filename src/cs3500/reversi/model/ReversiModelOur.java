package cs3500.reversi.model;

import java.util.List;
import java.util.Map;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.Player;

/**
 * Model Interface to represent the broader Reversi games.
 */
public interface ReversiModelOur {

  /**
   * Starts a new game of Reversi, based upon the given boardSize.
   * Depending on how big the board is, add that divided by 2 many pieces to each player.
   *
   * @throws IllegalStateException    if the game is already started
   * @throws IllegalArgumentException if the board size is invalid
   */
  void startGame();

  /**
   * Moves a piece from whomever turn it is to the board at the specified position.
   *
   * @param newCell the new position to add the piece to
   * @throws IllegalStateException    if the game isn't started
   * @throws IllegalStateException    if the move is invalid
   * @throws IllegalArgumentException if the position isn't on the board
   */
  void movePiece(Player player, ArbitraryCell newCell);

  /**
   * Passes the current player's turn. I.e., skips this player and makes it the next player's turn.
   * @throws IllegalStateException if the game isn't started
   * @throws IllegalArgumentException if the player isn't valid
   */
  void passTurn();

  /**
   * Tells whether the game is over or not based on if there are no legal moves left.
   *
   * @return whether the game is over
   * @throws IllegalStateException if the game hasn't been started yet
   */
  boolean isGameOver();

  /**
   * Gets the cell at the supplied coordinate.
   *
   * @param x x coordinate for the cell.
   * @param y y coordinate for the cell.
   * @return the cell at (x, y)
   * @throws IllegalStateException if the game hasn't started
   * @throws IllegalStateException if the cell at (x, y) is invalid
   */

  ArbitraryCell getCell(int x, int y);

  /**
   * Get the list of cells on the grid listed as their coordinates as we define them.
   * @return the list of cells on our grid's coordinates
   */

  List<Map.Entry<Integer, Integer>> getCellCoordinatesFromGrid();

  /**
   * Get the list of cells on the grid that are occupied as their coordinates, as we define them.
   * @return the list of occupied cells' coordinates
   */
  List<Map.Entry<Integer, Integer>> getOccupiedCells();

  /**
   * Gets the player whose turn it is.
   *
   * @return the player whose turn it is
   */
  Player getPlayer();

  /**
   * Displays the board as a textual String view.
   * @return the board as a textual view
   */

  String getBoardStringRepresentation();

  /**
   * Gets the board size of this game of Reversi. Also represents the number of rows and columns.
   * @return the board size of the game
   */

  int getBoardSize();

  /**
   * Makes a copy of this Reversi game at any point.
   * @return the current game of Reversi
   */
  ReversiModelOur copy();

  /**
   * Gets a list of legal moves for the specified player on the current game board.
   *
   * @param player the player for whom legal moves are to be determined.
   * @return a list of {@code ArbitraryCell} representing legal moves for the player.
   */
  List<ArbitraryCell> getLegalMoves(Player player);

  /**
   * Counts the number of pieces captured by the specified player on the current game board.
   *
   * @param player the player for whom captured pieces are to be counted.
   * @return the number of pieces captured by the player.
   */
  int countCapturedPieces(Player player);

  boolean isGameStarted();

  void setListener(PlayerListener playerListener);


  /**
   * Get the winner for the game and also their score.
   * @return the winner of the game of Reversi
   */

  String getWinner();

  /**
   * Mark the next turn for the player.
   */

  void nextTurn();

}
