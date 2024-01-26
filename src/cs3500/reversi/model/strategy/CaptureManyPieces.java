package cs3500.reversi.model.strategy;

import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.Player;

/**
 * The CaptureManyPieces strategy of a game of Reversi which the AI Player will utilize
 * to capture as many pieces as possible in one turn.
 */

public class CaptureManyPieces implements ReversiStrategyOurs {

  /**
   * To represent the CaptureManyPieces strategy.
   */

  @Override
  public ArbitraryCell chooseMove(ReversiModelOur model, Player player) {
    ReversiModelOur hypotheticalBoard = model.copy();
    ArbitraryCell bestMove = model.getLegalMoves(player).get(0);
    int maxCaptures = 0;
    for (ArbitraryCell move : model.getLegalMoves(player)) {
      hypotheticalBoard.movePiece(player, move);
      int captures = model.countCapturedPieces(player);

      if (captures > maxCaptures) {
        maxCaptures = captures;
        bestMove = move;
      }
    }
    return bestMove;
  }
}
