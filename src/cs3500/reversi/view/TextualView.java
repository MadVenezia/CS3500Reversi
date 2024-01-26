package cs3500.reversi.view;

/**
 * Interface to represent the physical rendition of the Reversi game.
 */
public interface TextualView {

  /**
   * Converts the view to a visible String.
   *
   * @return the view as a String
   */
  String toString();

  /**
   * Renders a model in some mann er (e.g. as text, or as graphics, etc.).
   *
   * @throws IllegalStateException if an IOException is caught.
   */
  void render();
}
