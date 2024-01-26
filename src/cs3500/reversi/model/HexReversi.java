package cs3500.reversi.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.cell.EmptyCell;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.cell.Cell;
import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.piece.EmptyPiece;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.position.HexPosition;
import cs3500.reversi.model.position.Position;

/**
 * Reversi is a two-player game played on a regular grid of cells.
 * Each player has a color—black or white—and the game pieces are discs colored black on one side
 * and white on the other.
 * Game play begins with equal numbers of both colors of discs arranged in the middle of the grid.
 * In our game, our grid will be made up of hexagons.
 *
 * <p>Player Black moves first. On each turn, a player may do one of two things:
 *
 * <p>They may pass, and let the other player move.
 * They may select a legal empty cell and play a disc in that cell.
 * A play is legal for player A if the disc being played is adjacent1 (in at least one direction)
 * to a straight line of the opponent player B’s discs,
 * at the far end of which is another player A disc.
 * The result of a legal move is that all player B’s discs in all directions that are sandwiched
 * between two discs of player A get flipped to player A.
 * We say that player A has captured player B’s discs.
 * If a player has no legal moves, they are required to pass.
 * If both players pass in a row, the game ends.
 */

public class HexReversi implements ReversiModelOur {

  private final Player p1;
  private final Player p2;
  private final int boardSize;
  private boolean gameStarted;
  private final ArbitraryCell[][] cells; // Use a 2D array of Cells to represent the board
  private boolean gameOver;

  public PlayerListener listener;

  private List<PlayerListener> listeners;

  /**
   * To represent a game of Reversi.
   *
   * @param size the chosen size of the board.
   */
  public HexReversi(int size, Player p1, Player p2) {
    this.p1 = p1;
    this.p1.setPlayerColor("X");
    this.p2 = p2;
    this.p2.setPlayerColor("O");
    this.p1.setOpponent(this.p2);
    this.p2.setOpponent(this.p1);
    this.gameStarted = false;
    this.boardSize = size;
    this.listeners = new ArrayList<>();
    if (this.boardSize % 2 == 0) {
      throw new IllegalArgumentException("Even board size does not allow for "
              + "optimal hexagonal gameplay");
    }
    if (this.boardSize < 5) {
      throw new IllegalArgumentException("Board is too small");
    }
    cells = new Cell[this.boardSize][]; // Initialize the board with the given size
    initializeBoard(this.boardSize);
    gameOver = false;
  }


  /**
   * Method to notify the listener for use in the controller.
   */
  public void notifyListener() {
    for (PlayerListener listener : this.listeners) {
      listener.notifyYourTurn(getPlayer());
    }
  }

  public boolean isGameStarted() {
    return this.gameStarted;
  }


  public void setListener(PlayerListener listener) {
    listener.addListener(listener);
  }

  /**
   * Initialize the board with the given size.
   *
   * @param size the supplied size with which to make the board
   */

  private void initializeBoard(int size) {
    int center = size / 2;
    for (int i = 0; i < size; i++) {
      int rowSize;
      if (i < center) {
        rowSize = (int) Math.round(size / 2.0) + i;
      } else if (i == center) {
        rowSize = size;
      } else {
        rowSize = 3 * size / 2 - i;
      }
      cells[i] = new Cell[rowSize];
      for (int j = 0; j < rowSize; j++) {
        int q = j - center;
        int r = i - center;
        int s = -q - r;
        Position pos = new HexPosition(q, r, s);
        cells[i][j] = new Cell(pos);
      }
    }
  }

  @Override
  public ArbitraryCell getCell(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Coordinates can't be negative");
    }

    for (Map.Entry<Integer, Integer> entry : getCellCoordinatesFromGrid()) {
      if (entry.getKey() == x && entry.getValue() == y) {
        return cells[x][y];
      }
    }
    return new EmptyCell();
  }

  @Override
  public List<Map.Entry<Integer, Integer>> getCellCoordinatesFromGrid() {
    List<Map.Entry<Integer, Integer>> cellCoords = new ArrayList<>();
    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        cellCoords.add(new AbstractMap.SimpleEntry<>(i, j));
      }
    }
    return cellCoords;
  }

  @Override
  public List<Map.Entry<Integer, Integer>> getOccupiedCells() {
    List<Map.Entry<Integer, Integer>> cellCoords = new ArrayList<>();
    for (int i = 0; i < this.cells.length; i++) {
      for (int j = 0; j < this.cells[i].length; j++) {
        if (getCell(i, j).isOccupied()) {
          cellCoords.add(new AbstractMap.SimpleEntry<>(i, j));
        }
      }
    }
    return cellCoords;
  }

  @Override
  public void startGame() {
    if (this.gameStarted) {
      throw new IllegalStateException("Game already started");
    }

    int size = cells.length;
    int mid = size / 2;

    p1.setTurn();

    for (int i = 0; i < (size * 5 + size + size / 2) / 2; i++) {
      p1.addPieceToReserve();
      p2.addPieceToReserve();
    }

    // Define the positions for the pieces around the middle of the board
    int[][] positions = {{mid - 1, mid - 1}, {mid + 1, mid}, {mid, mid + 1},
        {mid, mid - 1}, {mid + 1, mid - 1}, {mid - 1, mid}};

    for (int[] pos : positions) {
      int i = pos[0];
      int j = pos[1];
      if (p1.getTurn() && !p1.getPiecesInReserve().isEmpty()) {
        p1.addPieceToBoardFromReserve(cells[i][j]);
        p1.setTurn();
        p2.setTurn();
      } else if (p2.getTurn() && !p2.getPiecesInReserve().isEmpty()) {
        p2.addPieceToBoardFromReserve(cells[i][j]);
        p2.setTurn();
        p1.setTurn();
      }
    }

    this.gameStarted = true;
  }

  @Override
  public void movePiece(Player player, ArbitraryCell newCell) {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    if (newCell == null || newCell instanceof EmptyCell) {
      throw new IllegalArgumentException("Cell cannot be null");
    }
    if (newCell.getPosition() == new HexPosition(0, 0, 0)) {
      throw new IllegalArgumentException("Illegal move");
    }
    if (player.getTurn() && this.isMoveLegal(player, newCell)) {
      if (player.getPassCount() == 1) {
        player.setPassCount();
      }
      player.addPieceToBoardFromReserve(newCell);
      capturePieces(player, newCell);
    } else if (!this.isMoveLegal(player, newCell)) {
      throw new IllegalStateException("Illegal move");
    } else {
      throw new IllegalStateException("Not your turn!");
    }
  }

  private void capturePieces(Player currentPlayer, ArbitraryCell startingCell) {
    int[][] directions = {{0, -1, 1},
        {1, -1, 0}, {1, 0, -1}, {0, 1, -1}, {-1, 1, 0}, {-1, 0, 1}};
    for (int[] direction : directions) {
      int q = startingCell.getPosition().getQ() + direction[0];
      int r = startingCell.getPosition().getR() + direction[1];
      int s = startingCell.getPosition().getS() + direction[2];
      for (Map.Entry<Integer, Integer> entry : getCellCoordinatesFromGrid()) {
        if (getCell(entry.getKey(), entry.getValue()).getPosition().getQ() == q
                && getCell(entry.getKey(), entry.getValue()).getPosition().getR() == r
                && getCell(entry.getKey(), entry.getValue()).getPosition().getS() == s) {
          int x = entry.getKey();
          int y = entry.getValue();
          List<ArbitraryPiece> piecesToCapture = new ArrayList<>();
          while (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            ArbitraryCell cell = getCell(x, y);
            if (cell.getPiece() instanceof EmptyPiece) {
              piecesToCapture.clear();
              // We've hit an empty cell before finding another one of the player's pieces
              break;
            } else if (cell.getPiece().getColor().equals(currentPlayer.getPlayerColor())) {
              // We've found another one of the player's pieces, capture all pieces in the list
              for (ArbitraryPiece piece : piecesToCapture) {
                capturePiece(currentPlayer, piece);
              }
              break;
            } else {
              // This is an opponent's piece, add it to the list of pieces to be captured
              piecesToCapture.add(cell.getPiece());
            }
            x += direction[0];
            y += direction[1];
          }
        }
      }
    }
  }

  /**
   * Determines if the move is legal for this basic game of Reversi.
   *
   * @param newCell the new position to move to.
   * @return whether the move is legal or not.
   */

  private boolean isMoveLegal(Player player, ArbitraryCell newCell) {
    if (player.getPiecesInReserve().isEmpty()) {
      return false;
    } else if (newCell.isOccupied()) { // Cell is not empty
      return false;
    }

    // Check all six directions
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}, {1, 1}, {-1, -1}};

    for (int[] direction : directions) {
      for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
          if (newCell == getCell(i, j) && i + direction[0] >= 0 && j + direction[1] >= 0
                  && i + direction[0] < boardSize && j + direction[1] < boardSize) {
            if (getCell(i + direction[0], j + direction[1]).isOccupied()) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  private boolean checkDirection(Player player, ArbitraryCell cell, int[] direction) {
    int offset = Math.abs(-boardSize / 2); // assuming boardSize is n in a -n to n grid
    int q = cell.getPosition().getQ() + direction[0];
    int r = cell.getPosition().getR() + direction[1] - 1;

    // Check if the cell in this direction exists and contains an opponent's piece
    if (q + offset < 0 || r + offset - 1 < 0) {
      return false;
    }
    ArbitraryCell nextCell = getCell(q + offset, r + offset - 1);
    if (nextCell == null || nextCell instanceof EmptyCell || nextCell.getPiece() == null
            || nextCell.getPiece() instanceof EmptyPiece || nextCell.getPiece().getColor()
            .equals(player.getPlayerColor())) {
      return true;
    }

    // Keep moving in this direction until we find another one of the player's pieces
    while (true) {
      q += direction[0];
      r += direction[1];

      if (q < -offset || q >= offset || r < -offset || r >= offset) {
        return false; // We've hit the edge of the board
      }

      nextCell = getCell(q + offset, r + offset - 1);

      if (nextCell == null || nextCell.getPiece() == null || nextCell instanceof EmptyCell
              || nextCell.getPiece() instanceof EmptyPiece) {
        // We've hit an empty cell before finding another one of the player's pieces
        return false;
      } else if (nextCell.getPiece().getColor().equals(player.getPlayerColor())) {
        // We've found another one of the player's pieces
        return true;
      }
    }
  }

  /**
   * Captures that piece from the opposing player.
   *
   * @param piece the piece to capture
   */

  private void capturePiece(Player player, ArbitraryPiece piece) {
    piece.changeColor(player.getPlayerColor());
    player.addPieceFromOtherPlayer(piece);
  }

  @Override
  public void passTurn() {
    if (p1.getTurn()) {
      p1.setPassCount();
    } else {
      p2.setPassCount();
    }

    p1.setTurn();
    p2.setTurn();
    if (p1.getPassCount() == 1 && p2.getPassCount() == 1) {
      gameOver = true;
    }
  }

  @Override
  public boolean isGameOver() {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    return this.isOutOfMoves(this.p1) || this.isOutOfMoves(this.p2) || gameOver;
  }

  /**
   * Checks if the given player is out of moves.
   *
   * @param player the player to check if they don't have any moves left
   * @return whether the player has moves left
   */

  private boolean isOutOfMoves(Player player) {
    for (Map.Entry<Integer, Integer> entry : getCellCoordinatesFromGrid()) {
      if (this.isMoveLegal(player, cells[entry.getKey()][entry.getValue()])) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Player getPlayer() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    if (this.p1.getTurn()) {
      p2.setTurn();
      return this.p1;
    }
    p2.setTurn();
    return this.p2;
  }

  @Override
  public String getBoardStringRepresentation() {

    StringBuilder boardString = new StringBuilder();
    List<Map.Entry<Integer, Integer>> validCoords = getCellCoordinatesFromGrid();
    int currentRow = -1;
    int size = getBoardSize();
    int center = size / 2;

    for (Map.Entry<Integer, Integer> entry : validCoords) {
      int i = entry.getKey();
      int k = entry.getValue();

      if (i != currentRow) {
        boardString.append("\n");
        int indentSize = Math.abs(center - i) * 2;
        for (int indent = 0; indent < indentSize; indent++) {
          boardString.append(" ");
        }
        currentRow = i;
      }

      if (!getCell(i, k).isOccupied()) {
        boardString.append(" _ ");
      } else if (getCell(i, k).getPiece().getColor().equals("X")) {
        boardString.append(" X ");
      } else if (getCell(i, k).getPiece().getColor().equals("O")) {
        boardString.append(" O ");
      }
    }
    return boardString.toString();
  }

  @Override
  public int getBoardSize() {
    return boardSize;
  }

  @Override
  public ReversiModelOur copy() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    ReversiModelOur copy = new ReadOnlyReversi(this);
    return copy;
  }

  @Override
  public List<ArbitraryCell> getLegalMoves(Player player) {
    if (!gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    List<ArbitraryCell> legalMoves = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : getCellCoordinatesFromGrid()) {
      if (isMoveLegal(player, cells[entry.getKey()][entry.getValue()])) {
        legalMoves.add(cells[entry.getKey()][entry.getValue()]);
      }
    }
    return legalMoves;
  }

  @Override
  public int countCapturedPieces(Player player) {
    if (!gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    return player.getCapturedPieces().size();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof HexReversi) {
      HexReversi r = (HexReversi) o;
      return this.p1.equals(r.p1) && this.p2.equals(r.p2) && this.gameStarted == r.gameStarted
              && this.boardSize == r.boardSize && Arrays.deepEquals(this.cells, r.cells);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.p1.hashCode() + this.p2.hashCode() + this.boardSize
            + Arrays.deepHashCode(this.cells);
  }

  @Override
  public String getWinner() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    if (p1.getScore() > p2.getScore()) {
      return "Black wins with a score of " + p1.getScore();
    }
    else if (p2.getScore() > p1.getScore()) {
      return "White wins with a score of " + p2.getScore();
    }
    return "Tie game! Both players had a score of " + p1.getScore();
  }

  @Override
  public void nextTurn() {
    p1.setTurn();
    p2.setTurn();
  }
}