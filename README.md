# Reversi (Othello) Game Implementation in Java

This repository contains a Java implementation of the Reversi (Othello) game. It includes various classes to model the game, players, pieces, and a textual view for rendering the game board.

## Overview

The Reversi game aims to provide a complete implementation of the classic board game. The codebase assumes a basic understanding of object-oriented programming in Java and familiarity with the rules of Reversi.

## Quick Start

To get started with the Reversi game:

1. Clone the repository.
2. Open the project in your favorite Java IDE.
3. Explore the various classes to understand the game's implementation and the textual view.
4. Instantiate the `BasicReversi` class to initialize a Reversi game and interact with it using the provided methods.


// Example code snippet to start a Reversi game
BasicReversi game = new BasicReversi(8);
game.startGame();
// Perform moves and interact with the game using the defined methods

## Key Components
BasicReversi (Class)
The BasicReversi class represents the core functionality of the Reversi game. It manages the game state, moves, captures, and determines game completion.

NEW MockReversi (Class)
The MockReversi class is an abstract representation of a game of Reversi, used for testing strategies.

AbstractPlayer (Abstract Class)
The AbstractPlayer class defines common methods for managing player-specific operations in the Reversi game.

PlayerBlack and PlayerWhite (Classes)
PlayerBlack and PlayerWhite are subclasses of AbstractPlayer, representing the Black and White players in the game, respectively.

NEW EmptyPiece (Class)
The EmptyPiece class defines an empty piece. That is, a piece that either doesn't exist or is not on the board.

Piece (Class)
The Piece class manages the game pieces, distinguishing between Black and White pieces.

PlayerCreator (Class)
The PlayerCreator class utilizes an enum to create Black or White players based on the specified color.

NEW EmptyPosition (Class)
The EmptyPosition class defines an empty position. That is, a position that doesn't exist, or (for example), the position of a piece that has not yet been placed on the board.

Position (Class)
The Position class manages the position and movement on the game board using an XY coordinate system.

NEW EmptyCell (Class)
The EmptyCell class defines an empty cell. That is, a cell that doesn't exist or is not shown on the board.

Cell (Class)
The Cell class represents a cell on the game board, storing a position and a piece if occupied.

NEW CaptureManyPieces (Class)
The CaptureManyPieces class represents the strategy a Player can use which makes the player pick the move which will capture the most moves in a single turn.

ReversiModel (Interface)
The ReversiModel interface defines the contract for the Reversi game model, including methods to control the game flow.

NEW ArbitraryCell (Interface)
The ArbitraryCell interface defines any type of Cell that exists, whether it makes up the board or doesn't. It includes methods to pull data from the cell, like its position or whether it's occupied. 

NEW ArbitraryPiece (Interface)
The ArbitraryPiece interface defines any type of Piece that exists, whether it is in use in-game or not. It includes methods to pull data from the piece, like its position on the board or its color.

NEW PositionInterface (Interface)
The PositionInterface interface defines any type of Position that exists. It serves to pull coordinate data as well as to compare positions to each other.

NEW Player (Interface)
The Player interface defines any type of Player that exists, be it Black, White, Human, or AI (to be implemented later). It contains methods for which the ReversiModel can use to compare player data, like player turns or scores.

NEW ReversiStrategy (Interface)
The ReversiStrategy interface defines any type of strategy that can be implemented by a player to play an optimal game.

ReversiTextualView and TextualView (Classes/Interface)
These classes offer a textual view of the game board, rendering it in a string format.

NEW Frame (Class)
The Frame class represents the main frame for the Reversi game GUI. It sets up the main frame, creates an instance of Panel, and adds it to the frame for rendering the game.

NEW GraphicalHexagon (Class)
The GraphicalHexagon class represents a graphical hexagon used for rendering in the Reversi game GUI. It includes methods to create a hexagon as a Path2D.Double at specified center coordinates.

NEW KeyObserver (Interface)
The KeyObserver interface provides methods to be implemented by classes that observe key events in the GUI, such as key presses.

NEW KeySubject (Interface)
The KeySubject interface represents a subject that notifies observers about key and mouse events. It includes methods to notify registered observers about key and mouse events.

NEW Panel (Class)
The Panel class represents the panel for rendering the Reversi game. It implements the KeySubject interface and includes methods for calculating dimensions and positions, painting components, and handling key and mouse events.

NEW HexagonMouseListener (Inner Class)
The HexagonMouseListener is an inner class of the Panel class, representing a mouse listener for hexagon selection. It handles mouse click events and updates the selected row and column.

NEW HexagonKeyListener (Inner Class)
The HexagonKeyListener is an inner class of the Panel class, representing a key listener for hexagon selection and actions. It handles key press events, updates the selected hexagon based on arrow key input, and handles specific key events.

NEW ReversiView (Interface)
The ReversiView interface represents the contract for a Reversi game view. Implementing classes are responsible for rendering the game state and handling user interactions.

NEW MyKeyObserver (Class)
The MyKeyObserver class is an example implementation of the KeyObserver interface. It prints information to the console when a key or mouse event is triggered.

Part 3
- **PlayerListener (Interface):** Defines methods for notifying players about their turn and other events.

- **HumanController (Class):** Represents a controller for playing Reversi with a human player. Implements the PlayerActions and PlayerListener interfaces.

- **MachineController (Class):** Represents a controller for playing Reversi with an AI player. Implements the PlayerActions and PlayerListener interfaces.

- **Player (Class):** Represents a player in the Reversi game. Implements the PlayerListener interface.

- - **ReadOnlyReversi (Class):** Represents a read-only view of the Reversi game model. It implements the ReversiModel interface, providing read-only access to the game state.

Assignment 8
ReversiViewAdapter (Class):

Adapter class to make Frame compatible with ReversiView.
ReversiFeaturesToPlayerActionsAdapter (Class):

Adapter class to convert ReversiFeatures to PlayerActions for key event handling.
ReversiModelAdapter (Class):

Adapter class to adapt the provided ReversiModel to the custom ReversiModelOur.
StrategyAdapter (Class):

Adapter class to adapt a ReversiStrategy to ReversiStrategyOurs.
HexCoordinateAdapter (Class):

Adapter class to make HexCoordinate compatible with the Position class.

## Updates to the model
We decided to implement a lot of new interfaces for our model to make the code easier to manage.
For example, Cell, Piece, and Position are now part of larger interfaces, each with accompanying "empty" classes.
This allows for fewer exceptions to be thrown and overall more readable code, so instead of null pointer exceptions getting thrown
all over the place, we now know that if one gets thrown, it doesn't have to do with poor class design.

Additionally, we made a few tweaks to the BasicReversi class. Previously, we did not have passTurn or copy
as methods, but they are newly implemented and working as needed. Additionally, nearly all bugs related to the game have
been squashed, except capturing pieces, which is still a WIP but is much closer than ever before.

Other changes we've made aside from the model include those for the View to visualize the game – and it displays as intended!

## Provider implementation
We adapted the given strategies and view to the best of our ability without modifying any code given to us.
While they may not work completely, we believe they'd work hand-in-hand with our model if the strategies were
written with our model in mind.

## Source Organization
The codebase is organized into multiple classes representing different components of the Reversi game. Each directory contains related classes:
cs3500.reversi.view.hw6: Contains classes related to the graphical view of the game board and key/mouse inputs.
src/: Contains the main source code files for the Reversi game implementation.
src/views/: Contains classes related to the textual view of the game board.
src/controller: contains classes related to the interactions and updating of the game board.
...
Feel free to explore the different directories and classes to understand the codebase better.

## Command Line
On paper, the command line *should* work. The following commands activate the corresponding combinations and strategies:
"human": registers the human player, as player 1 or player 2 depending on where in the CL it was typed (first or second).
"machine": registers the AI player, as player 1 or player 2 depending on where in the CL it was typed (first or second).
"1": Our only strategy we implemented, where the AI tries to capture as many pieces as possible.
"providerStrategy1": the "Greedy" strategy from our provider, where the AI tries to capture as many pieces as possible.
"providerStrategy2": the strategy in which the AI tries to avoid cells near corners.
"providerStrategy3": the strategy in which the AI tries to take corner cells.
"providerStrategy1+2": a combination strategy of providerStrategy1 and providerStrategy2.
"providerStrategy1+3": a combination strategy of providerStrategy1 and providerStrategy3.
"providerStrategy2+3": a combination strategy of providerStrategy2 and providerStrategy3.

# Keyboard Interactions

## Moving the Selection

- **Arrow Keys:** Move the selection to an adjacent hexagon.
  - **Up Arrow Key:** Move selection upwards.
  - **Down Arrow Key:** Move selection downwards.
  - **Left Arrow Key:** Move selection to the left.
  - **Right Arrow Key:** Move selection to the right.

## Performing Actions

- **Enter Key:** Confirm the current selection and place peice
- **P Key:** Pass the turn 

## Additional Information

- The selected hexagon is visually highlighted in magenta when selected by mouse or keyboard.
- Pressing the same arrow key twice deselects the current hexagon.

## Design Considerations for Future AI
For future extensions, consider introducing AI players by implementing an interface like Player and creating AI-specific player implementations, allowing the game model to interact with different player types without knowing their internal workings.

# Invariant: Game Initialization and Started State

This invariant ensures that the game initializes correctly, preparing both players to engage in the match. Furthermore, it guarantees a seamless transition from the not-started state to the started state, enabling legitimate moves by the active player. All methods altering the game state must adhere to this invariant.

## Initialization Process

- **Players Ready to Play**: Before the game starts, both players must be properly initialized and ready to participate.
- **Game State Transition**: The game begins in a not-started state, denoted by the `gameStarted` variable set to `false`. The transition to the started state occurs explicitly through the `startGame()` method.

## Starting the Game

- **Method: `public void startGame()`**: Initiates the game by placing initial pieces on the board for both players. Once started, the game state transitions to the started state (`gameStarted` becomes `true`), and subsequent moves can be made by the active player.

## Enforcement

- **Legitimate Moves**: Methods responsible for altering the game state, such as `movePiece()`, `passTurn()`, and others, should ensure that the game is in the started state before allowing player actions.
- **Invariant Check**: At the beginning of each method altering the game state, there should be a check to ensure the game has started. If not, an exception or appropriate handling mechanism should be in place to maintain the invariant.

## Final-ish Implementation
Features we have working:
Human players playing against each other
Piece movement
Keyboard/mouse tracking
Piece capturing -- it doesn't work according to the rules of the game, but pieces do get captured to an extent

Features we do not fully have working:
Strategy implementation
AI movement past the first move in the game

## EXTRA CREDIT (SQUARE REVERSI)

Functionally, Square Reversi is nearly identical to hexagonal reversi. The only difference
is that the board is represented with a square and square cells as opposed to a hexagon
and hexagonal cells.
The SquareReversi class implements the ReversiModelOur interface (formerly known simply as ReversiModel, changed to avoid confusion with our providers from the last assignment).
To avoid further confusion, we renamed our BasicReversi class to HexReversi.
Outside creating this new model representation, here are some other changes we made:

# POSITIONS
We created a new position class, XYPosition, which implements our larger Position interface.
This is because we found it redundant to use a hexagonal coordinate system when an XY system
would be so much easier for a square grid.

# COMMAND LINE
We updated our command line usage to account for the option of a square board.
For example, now in order to play a game, one would type "square" or "hexagon" first, and
then type in the type of player they want (human or machine). The default size for a square game
is 6x6, and for a hexagonal game it is one with a board of 11 rows.
EX: square human human produces a human v human game on a square grid.

We have the following levels completely working:
Level 1
Level 2
Level 3

## Example Usage

```java
// Example initialization of a Reversi game
Player player1 = new HumanPlayer();
Player player2 = new HumanPlayer();
ReversiModel reversiGame = new HexReversi(8, player1, player2);

// Ensure players are ready before starting
player1.initialize();
player2.initialize();

// Start the game
reversiGame.startGame();

// Make a move
ArbitraryCell cellToMove = reversiGame.getCell(3, 4);
reversiGame.movePiece(player1, cellToMove);

// Check if the game is over
if (reversiGame.isGameOver()) {
    System.out.println("Game Over!");
}
