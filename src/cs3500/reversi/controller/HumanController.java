package cs3500.reversi.controller;

import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.PlayerListener;
import cs3500.reversi.view.hw6.ReversiView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * The {@code HumanController} class represents a controller for playing
 * Reversi with a human player.
 */
public class HumanController implements ReversiController {

  private final ReversiModelOur model;

  private final Player player;

  private ReversiView view;

  /**
   * To represent a HumanController.
   *
   * @param model  the ReversiModelOur to take in
   * @param player the player the controller is representing
   * @param view   a visualization of the game
   */

  public HumanController(ReversiModelOur model, Player player, ReversiView view) {
    this.model = model;
    this.player = player;
    this.view = view;
    this.model.setListener(this);
  }

  /**
   * Method to run and play a game of Reversi.
   */
  public void playGame() {
    if (!model.isGameStarted()) {
      model.startGame();
    }
    if (model.isGameOver()) {
      System.out.println("Game Over");
    }
  }

  @Override
  public void keyPressed(int keyCode, int selectedRow, int selectedCol) {
    if (player.getTurn()) {
      try {
        if (keyCode == KeyEvent.VK_P) {
          this.model.passTurn();
          view.updateView();
          if (model.isGameOver()) {
            JOptionPane.showMessageDialog(null, "Results: "
                            + model.getWinner(),
                    "Game over!", JOptionPane.INFORMATION_MESSAGE);
            return;
          }
          notifyYourTurn(player.getOpponent());

        } else if (keyCode == KeyEvent.VK_ENTER) {
          if (selectedRow >= model.getBoardSize() || selectedRow < 0
                  || selectedCol >= model.getBoardSize() || selectedCol < 0) {
            return;
          }
          this.model.movePiece(this.player, model.getCell(selectedRow, selectedCol));
          model.nextTurn();
          view.updateView();
          if (model.isGameOver()) {
            JOptionPane.showMessageDialog(null, "Results: "
                            + model.getWinner(),
                    "Game over!", JOptionPane.INFORMATION_MESSAGE);
            return;
          }
          notifyYourTurn(player.getOpponent());
        }
      } catch (IllegalStateException e) {
        JOptionPane.showMessageDialog(null, "Try that again!",
                "Illegal Move", JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e, int row, int col) {
    //not needed
  }

  @Override
  public void notifyYourTurn(Player player) {
    String color = "";
    if (player.getPlayerColor().equals("X")) {
      color = "Black";
    } else if (player.getPlayerColor().equals("O")) {
      color = "White";
    }
    String message = "Your Turn - " + color + " Player";

    JOptionPane.showMessageDialog(null, message,
            "Turn Notification", JOptionPane.INFORMATION_MESSAGE);

    view.updateView();
  }

  @Override
  public void addListener(PlayerListener listener) {
    // not needed?
  }
}