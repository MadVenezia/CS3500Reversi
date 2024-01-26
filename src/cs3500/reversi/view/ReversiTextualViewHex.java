package cs3500.reversi.view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cs3500.reversi.model.ReversiModelOur;

/**
 * Represents a textual view for the Reversi game, providing a string
 * representation of the game board.
 */
public class ReversiTextualViewHex implements TextualView {

  private final ReversiModelOur model;
  private final Appendable appendable;

  /**
   * Constructs a textual view for the Reversi game with the provided model
   * and appendable output.
   *
   * @param model      The ReversiModelOur instance representing the game state.
   * @param appendable An Appendable object to output the textual representation.
   * @throws IllegalArgumentException if the model is null.
   */
  public ReversiTextualViewHex(ReversiModelOur model, Appendable appendable) {
    this.appendable = appendable;
    this.model = model;
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
  }



  /**
   * Generates a string representation of the Reversi game board based on the current game state.
   *
   * @return A string representing the current state of the game board.
   */
  public String toString() {

    StringBuilder boardString = new StringBuilder();
    List<Map.Entry<Integer, Integer>> validCoords = model.getCellCoordinatesFromGrid();
    int currentRow = -1;
    int size = model.getBoardSize();
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

      if (!model.getCell(i, k).isOccupied()) {
        boardString.append("_  ");
      } else if (model.getCell(i, k).getPiece().getColor().equals("X")) {
        boardString.append("X  ");
      } else if (model.getCell(i, k).getPiece().getColor().equals("O")) {
        boardString.append("O  ");
      }
    }
    return boardString.toString();
  }


  @Override
  public void render() {
    try {
      this.appendable.append(this.toString());
    } catch (IOException e) {
      throw new IllegalStateException("IOException caught");
    }
  }
}