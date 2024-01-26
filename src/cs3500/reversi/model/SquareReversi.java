package cs3500.reversi.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.cell.Cell;
import cs3500.reversi.model.cell.EmptyCell;
import cs3500.reversi.model.piece.ArbitraryPiece;
import cs3500.reversi.model.piece.EmptyPiece;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.position.Position;
import cs3500.reversi.model.position.XYPosition;

/**
 * An implementation of the more-commonly seen Square Reversi.
 */

public class SquareReversi implements ReversiModelOur {

  private final Player p1;
  private final Player p2;
  private final int boardSize;
  private boolean gameStarted;
  private final ArbitraryCell[][] cells;
  private boolean gameOver;

  /**
   * To represent a SquareReversi.
   * @param size the size of the square board
   * @param p1 the first player (X/Black) in the game
   * @param p2 the second player (O/White) in the game
   */

  public SquareReversi(int size, Player p1, Player p2) {
    this.p1 = p1;
    this.p1.setPlayerColor("X");
    this.p2 = p2;
    this.p2.setPlayerColor("O");
    this.p1.setOpponent(this.p2);
    this.p2.setOpponent(this.p1);
    this.gameStarted = false;
    this.boardSize = size;
    if (this.boardSize % 2 == 1 || this.boardSize < 0) {
      throw new IllegalArgumentException("Odd board size does not allow for optimal square "
              + "Reversi gameplay");
    }
    cells = new Cell[this.boardSize][]; // Initialize the board with the given size
    initializeBoard(this.boardSize);
    gameOver = false;
  }

  private void initializeBoard(int size) {
    for (int i = 0; i < size; i++) {
      cells[i] = new Cell[size];
      for (int j = 0; j < size; j++) {
        Position pos = new XYPosition(i, j);
        cells[i][j] = new Cell(pos);
      }
    }
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
    int[][] positions = {{mid, mid}, {mid, mid - 1}, {mid - 1, mid - 1}, {mid - 1, mid}};

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
      throw new IllegalArgumentException("Cell cannot be null or empty");
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

  private boolean isMoveLegal(Player player, ArbitraryCell cellToMoveTo) {
    if (player.getPiecesInReserve().isEmpty()) {
      return false;
    } else if (cellToMoveTo.isOccupied()) { // Cell is not empty
      return false;
    }
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}, {1, 1}, {-1, -1}};
    for (int[] direction : directions) {
      for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
          if (cellToMoveTo == getCell(i, j)
                  && i + direction[0] >= 0 && j + direction[1] >= 0
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

  private void capturePieces(Player currentPlayer, ArbitraryCell newCell) {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {1, -1}, {1, 1}, {-1, -1}};
    for (int[] direction : directions) {
      int x = newCell.getPosition().getX() + direction[0];
      int y = newCell.getPosition().getY() + direction[1];
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

  private void capturePiece(Player player, ArbitraryPiece piece) {
    piece.changeColor(player.getPlayerColor());
    player.addPieceFromOtherPlayer(piece);
  }

  @Override
  public void passTurn() {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
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
    int cellsFilled = 0;
    for (ArbitraryCell[] cellRow : cells) {
      for (ArbitraryCell cell : cellRow) {
        if (cell.isOccupied()) {
          cellsFilled++;
        }
      }
    }
    return gameOver || cellsFilled == boardSize * boardSize;
  }

  @Override
  public ArbitraryCell getCell(int x, int y) {
    return cells[x][y];
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
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    List<Map.Entry<Integer, Integer>> cellCoords = new ArrayList<>();
    for (Map.Entry<Integer, Integer> entry : getCellCoordinatesFromGrid()) {
      if (getCell(entry.getKey(), entry.getValue()).isOccupied()) {
        cellCoords.add(new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()));
      }
    }
    return cellCoords;
  }

  @Override
  public Player getPlayer() {
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    if (this.p1.getTurn()) {
      return this.p1;
    }
    return this.p2;
  }

  @Override
  public String getBoardStringRepresentation() {
    StringBuilder boardString = new StringBuilder();
    int size = getBoardSize();

    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        if (!getCell(x, y).isOccupied()) {
          boardString.append("_  ");
        } else if (getCell(x, y).getPiece().getColor().equals("X")) {
          boardString.append("X  ");
        } else if (getCell(x, y).getPiece().getColor().equals("O")) {
          boardString.append("O  ");
        }
      }
      boardString.append("\n");
    }
    return boardString.toString();
  }


  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  @Override
  public ReversiModelOur copy() {
    if (!gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    return new ReadOnlyReversi(this);
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
    if (!this.gameStarted) {
      throw new IllegalStateException("Game not yet started");
    }
    return player.getCapturedPieces().size();
  }

  @Override
  public boolean isGameStarted() {
    return this.gameStarted;
  }

  @Override
  public void setListener(PlayerListener playerListener) {
    // unneeded for this assignment
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