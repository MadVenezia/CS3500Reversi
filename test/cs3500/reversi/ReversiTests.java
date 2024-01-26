package cs3500.reversi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import cs3500.reversi.model.ReadOnlyReversi;
import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.model.player.AbstractPlayer;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.HexReversi;
import cs3500.reversi.model.player.HumanPlayer;
import cs3500.reversi.model.player.MachinePlayer;
import cs3500.reversi.model.strategy.CaptureManyPieces;
import cs3500.reversi.model.cell.Cell;
import cs3500.reversi.model.cell.EmptyCell;
import cs3500.reversi.model.piece.EmptyPiece;
import cs3500.reversi.model.position.EmptyPosition;
import cs3500.reversi.model.strategy.ReversiStrategyOurs;
import cs3500.reversi.model.MockHexReversi;
import cs3500.reversi.model.piece.Piece;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.position.HexPosition;
import cs3500.reversi.model.position.Position;
import cs3500.reversi.view.ReversiTextualViewHex;
import cs3500.reversi.view.ReversiTextualViewSquare;
import cs3500.reversi.view.TextualView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Reversi and all associated classes.
 */
public class ReversiTests {
  Player black;
  Player white;
  ArbitraryPiece piece1;
  ArbitraryPiece piece2;
  Position pos;
  ArbitraryCell cell;
  TextualView hexView;
  TextualView squareView;
  ReversiModelOur hexModel;
  ReversiModelOur squareModel;
  ArbitraryCell emptyCell;
  ArbitraryPiece emptyPiece;
  Position emptyPosition;

  @Before
  public void initReversi() {
    piece1 = new Piece();
    piece2 = new Piece();
    black = new HumanPlayer();
    white = new MachinePlayer(new CaptureManyPieces());
    pos = new HexPosition(0, 0, 0);
    cell = new Cell(pos);
    hexModel = new HexReversi(11, black, white);
    squareModel = new SquareReversi(12, black, white);
    hexView = new ReversiTextualViewHex(hexModel, new StringBuilder());
    squareView = new ReversiTextualViewSquare(squareModel, new StringBuilder());
    emptyCell = new EmptyCell();
    emptyPiece = new EmptyPiece();
    emptyPosition = new EmptyPosition();
  }

  @Test (expected = IllegalStateException.class)
  public void testSquareMovePieceNotStarted() {
    squareModel.movePiece(black, squareModel.getCell(0, 0));
  }

  @Test
  public void testSquareGameOver() {
    ReversiModelOur smallSquare = new SquareReversi(4, black, white);
    smallSquare.startGame();
    assertFalse(smallSquare.isGameOver());
    smallSquare.movePiece(black, smallSquare.getCell(0, 0));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(white, smallSquare.getCell(0, 1));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(black, smallSquare.getCell(0, 2));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(white, smallSquare.getCell(0, 3));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(black, smallSquare.getCell(1, 0));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(white, smallSquare.getCell(1, 3));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(black, smallSquare.getCell(2, 0));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(white, smallSquare.getCell(2, 3));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(black, smallSquare.getCell(3, 0));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(white, smallSquare.getCell(3, 1));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(black, smallSquare.getCell(3, 2));
    black.setTurn();
    white.setTurn();
    smallSquare.movePiece(white, smallSquare.getCell(3, 3));
    assertTrue(smallSquare.isGameOver());
  }

  @Test
  public void testSquareMovePiece() {
    squareModel.startGame();
    assertFalse(squareModel.getCell(5, 7).isOccupied());
    squareModel.movePiece(black, squareModel.getCell(5, 7));
    assertTrue(squareModel.getCell(5, 7).isOccupied());
  }

  @Test
  public void testSquareCapture() {
    squareModel.startGame();
    assertEquals(white.getPlayerColor(), squareModel.getCell(5, 6).getPiece().getColor());
    squareModel.movePiece(black, squareModel.getCell(5, 7));
    assertNotEquals(white.getPlayerColor(), squareModel.getCell(5, 6).getPiece().getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSquareMovePieceNullPlayer() {
    squareModel.startGame();
    squareModel.movePiece(null, squareModel.getCell(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSquareModelWithOddCoordinates() {
    ReversiModelOur badSquare = new SquareReversi(11, black, white);
  }

  @Test(expected = IllegalStateException.class)
  public void testSquareGetWinnerNoStart() {
    squareModel.getWinner();
  }

  @Test
  public void testSquareGetWinnerTieGame() {
    squareModel.startGame();
    assertTrue(squareModel.getWinner().contains("Tie game"));
  }

  @Test
  public void testSquareGetWinner() {
    squareModel.startGame();
    squareModel.movePiece(black, squareModel.getCell(5, 7));
    assertTrue(squareModel.getWinner().contains("Black"));
  }

  @Test
  public void testSquareView() {
    squareModel.startGame();
    assertEquals("_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  X  O  _  _  _  _  _  \n"
            + "_  _  _  _  _  O  X  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n"
            + "_  _  _  _  _  _  _  _  _  _  _  _  \n", squareView.toString());
  }

  @Test
  public void testHexView() {
    hexModel.startGame();
    assertEquals("\n"
            + "          _  _  _  _  _  _  \n"
            + "        _  _  _  _  _  _  _  \n"
            + "      _  _  _  _  _  _  _  _  \n"
            + "    _  _  _  _  _  _  _  _  _  \n"
            + "  _  _  _  _  X  O  _  _  _  _  \n"
            + "_  _  _  _  O  _  X  _  _  _  _  \n"
            + "  _  _  _  _  X  O  _  _  _  _  \n"
            + "    _  _  _  _  _  _  _  _  _  \n"
            + "      _  _  _  _  _  _  _  _  \n"
            + "        _  _  _  _  _  _  _  \n"
            + "          _  _  _  _  _  _  ", hexView.toString());
  }

  @Test
  public void testAddPieceToReserveAndAddPieceToBoardAndGetScoreBlack() {
    // since the list of pieces in reserve/on board is private, we can test both at the same time.
    assertEquals(0, black.getScore());
    black.addPieceToReserve();
    black.addPieceToBoardFromReserve(cell);
    assertEquals(1, black.getScore());
  }

  @Test
  public void testAddPieceToReserveAndAddPieceToBoardAndGetScoreWhite() {
    // since the list of pieces in reserve/on board is private, we can test both at the same time.
    // also works for updating the score's tests.
    assertEquals(0, white.getScore());
    white.addPieceToReserve();
    white.addPieceToBoardFromReserve(cell);
    assertEquals(1, white.getScore());
  }

  @Test
  public void testAddPieceFromOtherPlayer() {
    white.addPieceToReserve();
    white.addPieceToBoardFromReserve(cell);
    assertEquals(1, white.getScore());
    assertEquals(0, black.getScore());
    black.addPieceFromOtherPlayer(white.getPiecesOnBoard().get(0));
    assertEquals(0, white.getScore());
    assertEquals(1, black.getScore());
  }

  @Test
  public void testChangeColor() {
    // should be equal because both are uninitialized
    assertEquals(piece1, piece2);
    piece1.changeColor("X");
    // should not be equal anymore
    assertNotEquals(piece1, piece2);
  }

  @Test
  public void testUpdatePosition() {
    // should be equal because both are uninitialized
    assertEquals(piece1, piece2);

    piece1.updatePosition(cell);

    assertNotEquals(piece1, piece2);
  }

  @Test
  public void testGetPoint() {
    // not on board and therefore empty
    assertEquals(piece1.getPoint(), new EmptyPosition());

    piece1.updatePosition(cell);

    assertEquals(new HexPosition(0, 0, 0), piece1.getPoint());
  }

  @Test
  public void testGetQPosition() {
    assertEquals(0, pos.getQ());
  }

  @Test
  public void testGetRPosition() {
    assertEquals(0, pos.getR());
  }

  @Test
  public void testGetSPosition() {
    assertEquals(0, pos.getS());
  }

  @Test
  public void testSetQPosition() {
    assertEquals(0, pos.getQ());
    pos.setQ(1);
    assertEquals(1, pos.getQ());
  }

  @Test
  public void testSetRPosition() {
    assertEquals(0, pos.getR());
    pos.setR(1);
    assertEquals(1, pos.getR());
  }

  @Test
  public void testSetSPosition() {
    assertEquals(0, pos.getS());
    pos.setS(1);
    assertEquals(1, pos.getS());
  }


  @Test
  public void testPositionEqualsAndHashCode() {
    HexPosition newPos = new HexPosition(0, 0, 0);
    assertEquals(pos, newPos);
    assertEquals(pos.hashCode(), newPos.hashCode());
  }

  @Test
  public void testPieceEqualsAndHashCode() {
    assertEquals(piece1, piece2);
    assertEquals(piece1.hashCode(), piece2.hashCode());
  }

  @Test
  public void testCellIsOccupied() {
    Assert.assertFalse(cell.isOccupied());
    piece1.updatePosition(cell);
    assertTrue(cell.isOccupied());
  }

  @Test(expected = IllegalStateException.class)
  public void testGameStartedExceptions() {
    hexModel.getCell(0, 0);
    hexModel.isGameOver();
    hexModel.movePiece(black, cell);
  }

  @Test(expected = IllegalStateException.class)
  public void testGameAlreadyStartedException() {
    hexModel.startGame();
    hexModel.startGame();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePieceArgumentExceptions() {
    hexModel.startGame();
    hexModel.movePiece(null, cell);
    hexModel.movePiece(black, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testMovePieceIllegalMove() {
    hexModel.startGame();
    hexModel.movePiece(new HumanPlayer(), cell);
  }


  @Test
  public void testMovePieceWorksCorrectly() {
    HexReversi game = new HexReversi(9, black, white);
    game.startGame();
    assertTrue(game.getCell(4, 3).isOccupied());
    assertFalse(game.getCell(5, 2).isOccupied());

    // change the player whose turn it is
    game.getPlayer();
    game.movePiece(game.getPlayer(), game.getCell(5, 2));

    assertTrue(game.getCell(5, 2).isOccupied());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelConstructorExceptions() {
    ReversiModelOur badModel1 = new HexReversi(8, black, white);
    ReversiModelOur badModel2 = new HexReversi(3, black, white);
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidMovePieceGameNotStarted() {
    HexReversi game = new HexReversi(9, black, white);
    AbstractPlayer player = new HumanPlayer();
    game.movePiece(player, game.getCell(3, 4));
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidMovePieceIllegalMove() {
    HexReversi game = new HexReversi(9, black, white);
    game.startGame();

    AbstractPlayer player = new HumanPlayer();
    // Make a move to an already occupied cell
    game.movePiece(player, game.getCell(3, 4));
    game.movePiece(player, game.getCell(3, 4));
  }

  @Test
  public void testHexValidCapturePiece() {
    hexModel.startGame();
    // Setting up the captured piece in an adjacent cell
    assertEquals("O", hexModel.getCell(6, 5).getPiece().getColor());
    hexModel.movePiece(hexModel.getPlayer(), hexModel.getCell(6, 6));

    // Check the board state after the capture
    assertEquals("X", hexModel.getCell(6, 5).getPiece().getColor());
    // The captured piece is a different color
    assertNotNull(hexModel.getCell(6, 6).getPiece());
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveBeforeGameStart() {
    ReversiModelOur game = new HexReversi(9, black, white);
    game.movePiece(black, new Cell(new HexPosition(0, 0, 0)));
  }

  @Test(expected = IllegalStateException.class)
  public void testDuplicateGameStart() {
    ReversiModelOur game = new HexReversi(9, black, white);
    game.startGame();
    game.startGame();
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGetCellWithInvalidPosition() {
    hexModel.startGame();
    hexModel.getCell(-1, 12);
  }

  @Test(expected = java.lang.IllegalArgumentException.class)
  public void testMovePieceWhenGameNotStarted() {
    hexModel.movePiece(black, new Cell(new HexPosition(3, 3, 2)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePieceWithNullPlayer() {
    hexModel.startGame();
    hexModel.movePiece(null, new Cell(new HexPosition(3, 3, 2)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMovePieceWithNullPosition() {
    hexModel.startGame();
    hexModel.movePiece(white, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testStartGameWhenAlreadyStarted() {
    hexModel.startGame();
    hexModel.startGame();
  }

  @Test
  public void testGetScoreInitiallyZero() {
    HumanPlayer player = new HumanPlayer();
    assertEquals(0, player.getScore());
  }

  @Test
  public void testGetTurnInitiallyFalse() {
    assertFalse(black.getTurn());
  }

  @Test
  public void testSetTurn() {
    HumanPlayer player = new HumanPlayer();

    // Initially, the turn should be false
    assertFalse(player.getTurn());

    // Set the turn to true
    player.setTurn();
    assertTrue(player.getTurn());

    // Set the turn back to false
    player.setTurn();
    assertFalse(player.getTurn());
  }

  @Test
  public void testGetPosition() {
    HexPosition position = new HexPosition(0, 0, 0);
    Cell cell = new Cell(position);
    assertEquals(position, cell.getPosition());
  }

  @Test
  public void testIsOccupiedWhenEmpty() {
    Cell cell = new Cell(new HexPosition(0, 0, 0));
    assertFalse(cell.isOccupied());
  }

  @Test
  public void testIsOccupiedAfterSettingPiece() {
    Cell cell = new Cell(new HexPosition(0, 0, 0));
    cell.setPiece(new Piece());
    assertTrue(cell.isOccupied());
  }

  @Test
  public void testGetPieceAfterSettingPiece() {
    Cell cell = new Cell(new HexPosition(0, 0, 0));
    Piece piece = new Piece();
    cell.setPiece(piece);
    assertEquals(piece, cell.getPiece());
  }

  @Test
  public void testGetPieceWhenNotSet() {
    Cell cell = new Cell(new HexPosition(0, 0, 0));
    assertEquals(new EmptyPiece(), cell.getPiece());
  }

  @Test
  public void testSetPiece() {
    Cell cell = new Cell(new HexPosition(0, 0, 0));
    Piece piece = new Piece();
    cell.setPiece(piece);
    assertEquals(piece, cell.getPiece());
  }

  @Test
  public void testToString() {
    HexPosition position = new HexPosition(0, 0, 0);
    Cell cell = new Cell(position);
    assertEquals(position.toString(), cell.toString());
  }

  @Test
  public void testGetColor() {
    Piece piece = new Piece();
    piece.changeColor("X");
    assertEquals("X", piece.getColor());
  }

  /**
   * Anything below this line was added after Part 1.
   */

  @Test
  public void testStartGame() {
    HexReversi game = new HexReversi(9, black, white);
    assertEquals(0, black.getPiecesOnBoard().size());
    assertEquals(0, white.getPiecesOnBoard().size());
    assertEquals(0, black.getPiecesInReserve().size());
    assertEquals(0, white.getPiecesInReserve().size());
    game.startGame();
    assertEquals(3, black.getPiecesOnBoard().size());
    assertEquals(3, white.getPiecesOnBoard().size());
    assertEquals(26, black.getPiecesInReserve().size());
    assertEquals(26, white.getPiecesInReserve().size());
    assertTrue(black.getTurn());
    assertFalse(white.getTurn());
  }

  @Test
  public void testIsGameOverNoItsNot() {
    ReversiModelOur game = new HexReversi(9, black, white);
    game.startGame();
    assertFalse(game.isGameOver());
  }

  @Test
  public void testReversiModelCopy() {
    ReversiModelOur game = new HexReversi(9, black, white);
    game.startGame();
    assertEquals(new ReadOnlyReversi(game), game.copy());
  }

  @Test
  public void testCellCopy() {
    ArbitraryCell cell = new Cell(pos);
    assertEquals(cell, cell.copy());
    ArbitraryCell emptyCell = new EmptyCell();
    assertEquals(emptyCell, emptyCell.copy());
  }

  @Test
  public void testPass() {
    hexModel.startGame();
    assertEquals(hexModel.getPlayer(), black);
    hexModel.passTurn();
    assertEquals(hexModel.getPlayer(), white);
  }

  @Test(expected = IllegalStateException.class)
  public void testCopyGameNotStarted() {
    hexModel.copy();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetPlayerGameNotStarted() {
    hexModel.getPlayer();
  }

  @Test
  public void testGetPlayer() {
    hexModel.startGame();
    assertEquals(black, hexModel.getPlayer());
    hexModel.passTurn();
    assertEquals(white, hexModel.getPlayer());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(11, hexModel.getBoardSize());
  }

  @Test(expected = IllegalStateException.class)
  public void testCountCapturedPiecesGameNotStarted() {
    hexModel.countCapturedPieces(black);
  }

  @Test
  public void testCountCapturedPieces() {
    hexModel.startGame();
    assertEquals(0, hexModel.countCapturedPieces(black));
    hexModel.movePiece(hexModel.getPlayer(), hexModel.getCell(5, 5));
    assertEquals(1, hexModel.countCapturedPieces(black));
  }

  @Test
  public void testDoublePassEndsGame() {
    hexModel.startGame();
    assertFalse(hexModel.isGameOver());
    hexModel.passTurn();
    hexModel.passTurn();
    assertTrue(hexModel.isGameOver());
  }

  @Test
  public void testEmptyCellGetPosition() {
    assertEquals(new EmptyPosition(), emptyCell.getPosition());
  }

  @Test
  public void testEmptyCellIsOccupied() {
    assertFalse(emptyCell.isOccupied());
  }

  @Test
  public void testEmptyCellGetPiece() {
    assertEquals(new EmptyPiece(), emptyCell.getPiece());
  }

  @Test
  public void testEmptyCellCopy() {
    assertEquals(emptyCell, emptyCell.copy());
    assertEquals(new EmptyCell(), emptyCell.copy());
  }

  @Test
  public void testEmptyCellEquals() {
    assertEquals(emptyCell, new EmptyCell());
  }

  @Test
  public void testEmptyCellHashCode() {
    assertEquals(1, emptyCell.hashCode());
  }

  @Test
  public void testCellEquals() {
    assertEquals(cell, new Cell(pos));
    assertNotEquals(cell, new Cell(new EmptyPosition()));
  }

  @Test
  public void testCellHashCode() {
    assertEquals(1, cell.hashCode());
  }

  @Test
  public void testEmptyPieceGetPoint() {
    assertEquals(new EmptyPosition(), emptyPiece.getPoint());
  }

  @Test
  public void testEmptyPieceGetColor() {
    assertTrue(emptyPiece.getColor().isEmpty());
  }

  @Test
  public void testEmptyPieceEquals() {
    assertEquals(emptyPiece, new EmptyPiece());
  }

  @Test
  public void testEmptyPieceHashCode() {
    assertEquals(1, emptyPiece.hashCode());
  }

  @Test
  public void testPlayerHashCode() {
    assertEquals(88, black.hashCode());
  }

  @Test
  public void testPlayerGetOpponent() {
    assertEquals(white, black.getOpponent());
  }

  @Test
  public void testPlayerSetOpponent() {
    Player newHumanPlayer = new HumanPlayer();
    assertNull(newHumanPlayer.getOpponent());
    newHumanPlayer.setOpponent(white);
    assertEquals(white, newHumanPlayer.getOpponent());
  }

  @Test
  public void testPlayerGetCapturedPieces() {
    hexModel.startGame();
    assertEquals(0, black.getCapturedPieces().size());
    hexModel.movePiece(black, hexModel.getCell(6, 6));
    assertEquals(1, black.getCapturedPieces().size());
  }

  @Test
  public void testEmptyPositionGetQRS() {
    assertEquals(0, emptyPosition.getQ());
    assertEquals(0, emptyPosition.getR());
    assertEquals(0, emptyPosition.getS());
  }

  @Test
  public void testEmptyPositionEquals() {
    assertEquals(emptyPosition, new EmptyPosition());
  }

  @Test
  public void testEmptyPositionHashCode() {
    assertEquals(1, emptyPosition.hashCode());
  }

  @Test
  public void testEmptyPositionToString() {
    assertTrue(emptyPosition.toString().isEmpty());
  }

  @Test
  public void testPositionEquals() {
    Position newPos = new HexPosition(0, 0, 0);
    assertEquals(pos, newPos);
  }

  @Test
  public void testPositionHashCode() {
    assertEquals(0, pos.hashCode());
  }

  @Test
  public void testPositionToString() {
    assertEquals("0, 0, 0", pos.toString());
  }

  @Test
  public void testPieceEquals() {
    ArbitraryPiece newPiece = new Piece();
    newPiece.changeColor("X");
    newPiece.updatePosition(cell);
    piece1.changeColor("X");
    piece1.updatePosition(cell);
    assertEquals(piece1, newPiece);
  }

  @Test
  public void testPieceHashCode() {
    assertEquals(piece1.getColor().hashCode()
            + piece1.getPoint().hashCode(), piece1.hashCode());
  }

  @Test
  public void testBasicReversiEquals() {
    ReversiModelOur newModel = new HexReversi(11, black, white);
    assertEquals(hexModel, newModel);
    assertNotEquals(hexModel, new HexReversi(11, black, black));
    assertNotEquals(hexModel, new HexReversi(13, black, white));
    assertNotEquals(hexModel, new HexReversi(11, white, black));
  }

  @Test
  public void testBasicReversiHashCode() {
    assertEquals(black.hashCode() + white.hashCode() + 11 + 1564499877, hexModel.hashCode());
  }

  @Test
  public void testStrategy1() {
    ReversiStrategyOurs strat = new CaptureManyPieces();
    ArbitraryCell move1 = new Cell(new HexPosition(0, 0, 0));
    ArbitraryCell move2 = new Cell(new HexPosition(-1, 0, 1));
    ArbitraryCell move3 = new Cell(new HexPosition(1, -1, 0));
    StringBuilder log = new StringBuilder();
    ReversiModelOur mock = new MockHexReversi(log, Arrays.asList(move1, move2, move3), 3);
    assertEquals(move1, strat.chooseMove(mock, black));
    assertTrue(log.toString().contains("Moves the black piece to (0, 0, 0) as specified"
            + " by CaptureManyPieces and MockReversi"));
  }
}