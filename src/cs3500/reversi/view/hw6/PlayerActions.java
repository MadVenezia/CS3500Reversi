package cs3500.reversi.view.hw6;

import java.awt.event.MouseEvent;

/**
 * Interface to represent an observer for key and mouse events in the Reversi GUI.
 * Implementations of the interface can respond to key and mouse events that have been triggered
 * by the GUI.
 */
public interface PlayerActions {

  /**
   * Checks which key was pressed.
   * @param keyCode the key that was pressed
   * @param selectedRow row in the Reversi board that was triggered
   * @param selectedCol column in the Reversi board that was triggered
   */
  void keyPressed(int keyCode, int selectedRow, int selectedCol);

  /**
   * Checks if the mouse was pressed.
   * @param e the mouse click
   * @param row row in the Reversi board that was triggered
   * @param col column in the Reversi board that was triggered
   */
  void mousePressed(MouseEvent e, int row, int col);
}