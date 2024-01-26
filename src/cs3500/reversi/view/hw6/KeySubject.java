package cs3500.reversi.view.hw6;

import java.awt.event.MouseEvent;

/**
 * Represents a subject that notifies observers about key and mouse events.
 */
public interface KeySubject {

  /**
   * Notifies the registered observers about a key event.
   *
   * @param keyCode     the code of the pressed key.
   * @param selectedRow the selected row (if applicable).
   * @param selectedCol the selected column (if applicable).
   */
  void notifyObservers(int keyCode, int selectedRow, int selectedCol);

  /**
   * Notifies the registered observers about a mouse event.
   *
   * @param e   the MouseEvent containing information about the event.
   * @param row the row associated with the mouse event.
   * @param col the column associated with the mouse event.
   */
  void mouseNotifyObservers(MouseEvent e, int row, int col);

  /**
   * Adds a key observer to the list of registered observers.
   *
   * @param observer the key observer to be added.
   */
  void addKeyObserver(PlayerActions observer);

}