package cs3500.reversi.model.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.piece.Piece;
import cs3500.reversi.model.strategy.ReversiStrategyOurs;


/**
 * Abstract Player class to represent the broader Player methods/fields and make
 * the code more readable.
 */

public abstract class AbstractPlayer implements Player {
  protected boolean myTurn;
  protected List<ArbitraryPiece> piecesOnBoard;
  protected List<ArbitraryPiece> piecesInReserve;
  protected List<ArbitraryCell> cellsOccupied;
  protected Player opponent;
  protected int passCount;

  protected String color;



  /**
   * To represent the generalized Player.
   */
  public AbstractPlayer() {
    this.myTurn = false;
    this.piecesOnBoard = new ArrayList<>();
    this.piecesInReserve = new ArrayList<>();
    this.cellsOccupied = new ArrayList<>();
    this.opponent = null;
    this.color = "";
  }

  @Override
  public void addPieceToReserve() {
    this.piecesInReserve.add(new Piece());
    for (ArbitraryPiece p : this.piecesInReserve) {
      p.changeColor(this.getPlayerColor());
    }
  }

  @Override
  public void addPieceToBoardFromReserve(ArbitraryCell cellOnBoard) {
    this.piecesOnBoard.add(this.piecesInReserve.remove(0));
    this.piecesOnBoard.get(this.piecesOnBoard.size() - 1).updatePosition(cellOnBoard);
    cellOnBoard.setPiece(this.piecesOnBoard.get(this.piecesOnBoard.size() - 1));
  }

  @Override
  public void addPieceFromOtherPlayer(ArbitraryPiece piece) {
    int indexOfPiece = getOpponent().getPiecesOnBoard().indexOf(piece);
    this.getCapturedPieces().add(piece);
    this.piecesOnBoard.add(getOpponent().getPiecesOnBoard().remove(indexOfPiece));
    this.piecesOnBoard.get(this.piecesOnBoard.size() - 1).changeColor(this.getPlayerColor());
  }

  public int getScore() {
    return this.piecesOnBoard.size();
  }

  public boolean getTurn() {
    return this.myTurn;
  }

  public void setTurn() {
    this.myTurn = !this.getTurn();
  }

  public String getPlayerColor() {
    return this.color;
  }

  public List<ArbitraryPiece> getPiecesOnBoard() {
    return this.piecesOnBoard;
  }

  public List<ArbitraryPiece> getPiecesInReserve() {
    return this.piecesInReserve;
  }

  public List<ArbitraryPiece> getCapturedPieces() {
    return new ArrayList<>();
  }

  public Player getOpponent() {
    return this.opponent;
  }

  public void setOpponent(Player opponent) {
    this.opponent = opponent;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractPlayer) {
      AbstractPlayer player = (AbstractPlayer) other;
      return this.myTurn == player.myTurn && this.piecesOnBoard == player.piecesOnBoard
              && this.piecesInReserve.equals(player.piecesInReserve)
              && this.opponent == player.opponent
              && this.cellsOccupied.equals(player.cellsOccupied)
              && this.passCount == player.passCount
              && this.color.equals(player.color);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.piecesInReserve.size() + this.piecesOnBoard.size()
            + this.cellsOccupied.size() + this.getPlayerColor().hashCode();
  }

  @Override
  public int getPassCount() {
    return this.passCount;
  }

  @Override
  public void setPassCount() {
    if (this.passCount == 0) {
      this.passCount = 1;
    } else {
      this.passCount = 0;
    }
  }

  @Override
  public void setPlayerColor(String color) {
    this.color = color;
  }

  public abstract ReversiStrategyOurs getStrategy();

  public abstract void setStrategy(ReversiStrategyOurs strategy);
}