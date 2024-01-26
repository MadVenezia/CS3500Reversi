package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.model.ReversiModelOur;


/**
 * Represents a textual view for the Reversi game, providing a string
 * representation of the game board.
 */
public class ReversiTextualViewSquare implements TextualView {

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
  public ReversiTextualViewSquare(ReversiModelOur model, Appendable appendable) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Generates a string representation of the Reversi game board based on the current game state.
   *
   * @return A string representing the current state of the game board.
   */
  public String toString() {
    StringBuilder boardString = new StringBuilder();
    int size = model.getBoardSize();
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        if (!model.getCell(x, y).isOccupied()) {
          boardString.append("_  ");
        } else if (model.getCell(x, y).getPiece().getColor().equals("X")) {
          boardString.append("X  ");
        } else if (model.getCell(x, y).getPiece().getColor().equals("O")) {
          boardString.append("O  ");
        }
      }
      boardString.append("\n");
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