package cs3500.reversi.model.player;

import java.util.List;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.strategy.ReversiStrategyOurs;

/**
 * Interface to represent a non-specific Player in the game of Reversi.
 */

public interface Player {

  /**
   * Add a piece to the specified Player's reserve – used when initializing the game.
   */
  void addPieceToReserve();

  /**
   * Add a piece to the board from the Player's reserve pieces at the specified point.
   *
   * @param cellOnBoard the point on the board to add the piece.
   */

  void addPieceToBoardFromReserve(ArbitraryCell cellOnBoard);

  /**
   * "Captures" a piece from the other player – removes it from That's board pieces, but adds
   * to This's board pieces.
   *
   * @param piece the piece to add.
   */

  void addPieceFromOtherPlayer(ArbitraryPiece piece);

  /**
   * Get this Player's score based on the number of pieces they have on the board.
   *
   * @return the Player's score.
   */

  int getScore();

  /**
   * Gets whether it is this Player's turn.
   * @return the Player's turn state.
   */

  boolean getTurn();

  /**
   * Sets the player's turn; if it was the Player's turn, it no longer is (and vice versa).
   */

  void setTurn();

  /**
   * Gets this player's color (i.e. "X" or "O").
   * @return the player's color.
   */

  String getPlayerColor();

  /**
   * Gets the pieces this Player has on the board.
   * @return the pieces this Player has on the board.
   */

  List<ArbitraryPiece> getPiecesOnBoard();

  /**
   * Gets the pieces this Player has in reserve.
   * @return the pieces this Player has in reserve.
   */
  List<ArbitraryPiece> getPiecesInReserve();

  /**
   * Gets the pieces this Player has captured.
   * @return a list of captured pieces
   */

  List<ArbitraryPiece> getCapturedPieces();

  /**
   * Gets this player's opponent in the Reversi game.
   * @return the opposing Player in the corresponding Reversi game
   */

  Player getOpponent();

  /**
   * Sets the player's opponent. To be used in a game of Reversi.
   * @param opponent the new opponent to assign to this player
   */

  void setOpponent(Player opponent);

  /**
   * Adds 1 to the player's pass count if it's empty.
   * If it's not empty, it resets it to 0.
   */
  void setPassCount();

  /**
   * Gets this player's pass count. It should be no more than 1. It resets if the next player
   * doesn't pass.
   * @return the player's pass count.
   */
  int getPassCount();

  /**
   * Sets this player's color.
   * @param color the color to change to
   */
  void setPlayerColor(String color);


  ReversiStrategyOurs getStrategy();

  void setStrategy(ReversiStrategyOurs strategy);


}