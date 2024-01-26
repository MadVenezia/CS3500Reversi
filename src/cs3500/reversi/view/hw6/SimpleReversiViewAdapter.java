package cs3500.reversi.view.hw6;

import cs3500.reversi.model.provider.GameOutcome;
import cs3500.reversi.view.hw6.provider.ReversiFeatures;


/**
 * Adapter class to adapt {@code SimpleReversiView} to the {@code ReversiView} interface.
 * should be sent to controller in order for provider code to run in main
 */
public class SimpleReversiViewAdapter implements ReversiView {

  private final view.SimpleReversiView simpleReversiView;

  /**
   * Constructs a {@code SimpleReversiViewAdapter} with the provided {@code SimpleReversiView}.
   *
   * @param simpleReversiView the SimpleReversiView to be adapted.
   */
  public SimpleReversiViewAdapter(view.SimpleReversiView simpleReversiView) {
    this.simpleReversiView = simpleReversiView;
  }

  public void display(boolean activate) {
    simpleReversiView.display(activate);
  }


  public void addFeatureListener(ReversiFeatures features) {
    simpleReversiView.addFeatureListener(features);
  }


  public void displayError(String msg) {
    simpleReversiView.displayError(msg);
  }


  public void refresh() {
    simpleReversiView.refresh();
  }

  public void setGameOver(GameOutcome outcome) {
    simpleReversiView.setGameOver(outcome);
  }

  @Override
  public void setVisible(boolean isVisible) {
    //necessity is TBD
  }

  @Override
  public void updateView() {
   //necessity is TBD
  }

  @Override
  public Panel getPanel() {
    return null;
  }


}
