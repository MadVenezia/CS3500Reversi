package cs3500.reversi;

import java.util.List;

import cs3500.reversi.controller.HumanController;
import cs3500.reversi.controller.MachineController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.HexReversi;
import cs3500.reversi.model.ReversiModelAdapter;
import cs3500.reversi.model.ReversiModelOur;
import cs3500.reversi.model.SquareReversi;
import cs3500.reversi.model.player.HumanPlayer;
import cs3500.reversi.model.player.MachinePlayer;
import cs3500.reversi.model.player.Player;
import cs3500.reversi.model.strategy.CaptureManyPieces;
import cs3500.reversi.model.strategy.StrategyAdapter;
import cs3500.reversi.model.strategy.provider.AvoidHexNearCornersStrategy;
import cs3500.reversi.model.strategy.provider.CombinedStrategy;
import cs3500.reversi.model.strategy.provider.GreedyStrategy;
import cs3500.reversi.model.strategy.provider.TakeCornerStrategy;
import cs3500.reversi.view.hw6.Frame;
import cs3500.reversi.view.hw6.GraphicalHexagon;
import cs3500.reversi.view.hw6.GraphicalShape;
import cs3500.reversi.view.hw6.GraphicalSquare;
import cs3500.reversi.view.hw6.ReversiView;


//comment
/**
 * Main class to run and visually test a game of Reversi.
 */
public final class Reversi {

  /**
   * Main method to test the game of Reversi.
   *
   * @param args the parameters passed into and read by the computer
   *             the provider view cannot be implemented into the controller
   *             untill further implementation is done hence the errors
   */
  public static void main(String[] args) {
    Player humanPlayer1 = new HumanPlayer();
    Player humanPlayer2 = new HumanPlayer();
    Player machinePlayer1 = new MachinePlayer(new CaptureManyPieces());
    Player machinePlayer2 = new MachinePlayer(new CaptureManyPieces());

    ReversiModelOur providedModel;
    ReversiModelOur model;

    ReversiController controller1;
    ReversiController controller2;
    GraphicalShape square = new GraphicalSquare();
    GraphicalShape hex = new GraphicalHexagon();

    String strategy1Type = args.length >= 4 ? args[3] : "";
    String strategy2Type = args.length >= 5 ? args[4] : "";


    if (args[0].contains("hexagon")) {
      if (args[1].contains("human") && args[2].contains("machine")) {
        configureMachinePlayer(machinePlayer2, strategy1Type);
        providedModel = new HexReversi(11, humanPlayer1, machinePlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, hex);
        ReversiView viewPlayer2 = new Frame(model, hex);
        controller1 = new HumanController(model, humanPlayer1, viewPlayer1);
        controller2 = new MachineController(model, machinePlayer2, viewPlayer2);
        viewPlayer1.getPanel().addKeyObserver(controller1);
        controller1.playGame();
        controller2.playGame();
      } else if (args[1].contains("machine") && args[2].contains("human")) {
        configureMachinePlayer(machinePlayer1, strategy1Type);
        providedModel = new HexReversi(11, machinePlayer1, humanPlayer2);
        model = providedModel;
        view.SimpleReversiView providedView = new view.SimpleReversiView(
                new ReversiModelAdapter(model, machinePlayer1, humanPlayer2));
        ReversiView viewPlayer1 = new Frame(model, hex);
        controller1 = new MachineController(model, machinePlayer1,
                viewPlayer1);
        ReversiView viewPlayer2 = new Frame(model, hex);
        controller2 = new HumanController(model, humanPlayer2, viewPlayer2);
        viewPlayer2.getPanel().addKeyObserver(controller2);
        controller1.playGame();
        controller2.playGame();
      } else if (args[1].contains("machine") && args[2].contains("machine")) {
        configureMachinePlayer(machinePlayer1, strategy1Type);
        configureMachinePlayer(machinePlayer2, strategy2Type);
        providedModel = new HexReversi(11, machinePlayer1, machinePlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, hex);
        ReversiView viewPlayer2 = new Frame(model, hex);
        controller1 = new MachineController(model, machinePlayer1,
                viewPlayer1);
        controller2 = new MachineController(model, machinePlayer2,
                viewPlayer2);
        controller1.playGame();
        controller2.playGame();
      } else {
        providedModel = new HexReversi(11, humanPlayer1, humanPlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, hex);
        ReversiView viewPlayer2 = new Frame(model, hex);
        controller1 = new HumanController(model, humanPlayer1, viewPlayer1);
        controller2 = new HumanController(model, humanPlayer2, viewPlayer2);
        viewPlayer1.getPanel().addKeyObserver(controller1);
        viewPlayer2.getPanel().addKeyObserver(controller2);
        controller1.playGame();
        controller2.playGame();
      }
    }
    else if (args[0].contains("square")) {
      if (args[1].contains("human") && args[2].contains("machine")) {
        configureMachinePlayer(machinePlayer2, strategy1Type);
        providedModel = new SquareReversi(6, humanPlayer1, machinePlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, square);
        ReversiView viewPlayer2 = new Frame(model, square);
        controller1 = new HumanController(model, humanPlayer1, viewPlayer1);
        controller2 = new MachineController(model, machinePlayer2, viewPlayer2);
        viewPlayer1.getPanel().addKeyObserver(controller1);
        controller1.playGame();
        controller2.playGame();
      } else if (args[1].contains("machine") && args[2].contains("human")) {
        configureMachinePlayer(machinePlayer1, strategy1Type);
        providedModel = new SquareReversi(6, machinePlayer1, humanPlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, square);
        controller1 = new MachineController(model, machinePlayer1,
                viewPlayer1);
        ReversiView viewPlayer2 = new Frame(model, square);
        controller2 = new HumanController(model, humanPlayer2, viewPlayer2);
        viewPlayer2.getPanel().addKeyObserver(controller2);
        controller1.playGame();
        controller2.playGame();
      } else if (args[1].contains("machine") && args[2].contains("machine")) {
        configureMachinePlayer(machinePlayer1, strategy1Type);
        configureMachinePlayer(machinePlayer2, strategy2Type);
        providedModel = new SquareReversi(6, machinePlayer1, machinePlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, square);
        ReversiView viewPlayer2 = new Frame(model, square);
        controller1 = new MachineController(model, machinePlayer1,
                viewPlayer1);
        controller2 = new MachineController(model, machinePlayer2,
                viewPlayer2);
        controller1.playGame();
        controller2.playGame();
      } else {
        providedModel = new SquareReversi(6, humanPlayer1, humanPlayer2);
        model = providedModel;
        ReversiView viewPlayer1 = new Frame(model, square);
        ReversiView viewPlayer2 = new Frame(model, square);
        controller1 = new HumanController(model, humanPlayer1, viewPlayer1);
        controller2 = new HumanController(model, humanPlayer2, viewPlayer2);
        viewPlayer1.getPanel().addKeyObserver(controller1);
        viewPlayer2.getPanel().addKeyObserver(controller2);
        controller1.playGame();
        controller2.playGame();
      }
    }
  }

  private static void configureMachinePlayer(Player player, String strategyType) {
    switch (strategyType) {
      case "1":
        player.setStrategy(new CaptureManyPieces());
        break;
      case "providerStrategy1":
        player.setStrategy(new StrategyAdapter(new GreedyStrategy()));
        break;
      case "providerStrategy2":
        player.setStrategy(new StrategyAdapter(new AvoidHexNearCornersStrategy()));
        break;
      case "providerStrategy3":
        player.setStrategy(new StrategyAdapter(new TakeCornerStrategy()));
        break;
      case "providerStrategy1+2":
        player.setStrategy(new StrategyAdapter(new CombinedStrategy(
                List.of(new GreedyStrategy(), new AvoidHexNearCornersStrategy()))));
        break;
      case "providerStrategy1+3":
        player.setStrategy(new StrategyAdapter(new CombinedStrategy(
                List.of(new GreedyStrategy(), new TakeCornerStrategy()))));
        break;
      case "providerStrategy2+3":
        player.setStrategy(new StrategyAdapter(new CombinedStrategy(
                List.of(new AvoidHexNearCornersStrategy(), new TakeCornerStrategy()))));
        break;
      default:
        break;
    }
  }
}
