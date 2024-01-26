package cs3500.reversi.view.hw6;

/**
 * The {@code ReversiView} interface represents the contract for a Reversi game view.
 * Implementing classes are responsible for rendering the game state and handling user interactions.
 */
public interface ReversiView {

  /**
   * Sets the visibility of the game view.
   *
   * @param isVisible {@code true} to make the view visible, {@code false} to hide it.
   */
  void setVisible(boolean isVisible);

  void updateView();

  Panel getPanel();
}