package cs3500.reversi.view.hw6;

import java.awt.Color;

import javax.swing.JFrame;

import cs3500.reversi.model.ReversiModelOur;

/**
 * Represents the main frame for the Reversi game GUI.
 */
public class Frame extends JFrame implements ReversiView {

  private final Panel panel;

  /**
   * Constructs a new frame for the Reversi game GUI.
   *
   * @param model          the Reversi model to be associated with the frame.
   * @param graphicalShape the graphical shape to be used (hexagon or square).
   */
  public Frame(ReversiModelOur model, GraphicalShape graphicalShape) {
    // Set up the main frame
    setTitle("Reversi Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(true);

    // Create an instance of Panel and pass the model and graphical shape
    Panel panel = new Panel(model, graphicalShape);
    this.panel = panel;

    // Customize the appearance (optional)
    panel.setBackground(Color.DARK_GRAY);

    // Add Panel to the frame
    add(panel);

    // Pack and set the frame visible
    pack();
    setLocationRelativeTo(null); // Center the frame
    setVisible(true);
  }

  public void updateView() {
    repaint();
  }

  @Override
  public Panel getPanel() {
    return this.panel;
  }
}
