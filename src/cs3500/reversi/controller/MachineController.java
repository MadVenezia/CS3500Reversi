package cs3500.reversi.controller;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.cell.ArbitraryCell;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.PlayerListener;
import cs3500.reversi.model.strategy.ReversiStrategyOurs;
import cs3500.reversi.view.hw6.ReversiView;

/**
 * The {@code AIController} class represents a controller for playing Reversi with an AI player.
 */
public class MachineController implements ReversiController {
  private final ReversiModelOur model;

  private final ReversiView view;

  private boolean isYourTurn;

  private final Player player;

  /**
   * To represent a MachineController.
   * @param model the ReversiModelOur to take in and control
   * @param player the player this controller is representing
   * @param view to visualize the game
   */

  public MachineController(ReversiModelOur model, Player player, ReversiView view) {
    this.player = player;
    this.model = model;
    this.view = view;
    this.model.setListener(this);
  }

  /**
   * To run and play the game of Reversi.
   */

  public void playGame() {
    if (!model.isGameStarted()) {
      model.startGame();
    }
    while (!model.isGameOver()) {
      if (player.getTurn()) {
        if (getAIMove().isEmpty()) {
          model.passTurn();
        }
        else {
          model.movePiece(this.player, getAIMove());
        }
        view.updateView();
        notifyYourTurn(player.getOpponent());
      }
    }
    System.out.println("Game over");
  }

  private ArbitraryCell getAIMove() {
    ReversiStrategyOurs strategy = player.getStrategy();
    System.out.println(strategy.chooseMove(model, player));
    return strategy.chooseMove(model, player);
  }


  @Override
  public void notifyYourTurn(Player player) {
    String color = "";
    if (player.getPlayerColor().equals("X")) {
      color = "Black";
    }
    else if (player.getPlayerColor().equals("O")) {
      color = "White";
    }
    String message = "Your Turn - " + color + " Player";

    JOptionPane.showMessageDialog(null, message,
            "Turn Notification", JOptionPane.INFORMATION_MESSAGE);
    model.passTurn();
    view.updateView();
  }

  @Override
  public void addListener(PlayerListener listener) {
    // not needed
  }

  @Override
  public void keyPressed(int keyCode, int selectedRow, int selectedCol) {
    // not needed
  }

  @Override
  public void mousePressed(MouseEvent e, int row, int col) {
    // not needed
  }
}