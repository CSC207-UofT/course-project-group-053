# Project domain: 9 men morris multiplayer game

# Project description:

The project so far is a command-line version of the Nine Men's Morris board game, for two players to play. Launching the program prompts the first player to enter their player name, and what color they want their game pieces (tokens) to be. The program then prompts the same information from the second player. After this, the game starts, and players are shown an ASCII graphic of the gameboard. Players take turns typing in the command line, specifying the coordinates where they want to place their game pieces. When players get their game tokens to form horizontal or vertical stacks on the gameboard (a "house"), they can remove one of their oponenent's tokens from the board if they are not also in a house. The game ends when both players run out of tokens to place.

# Project classes

## Entity classes
### Player
    Stores player username
    Tracks color of player's game tokens (chips), and how many tokens they have left
    
### HumanPlayer1/2
    Child classes to Player
    Stores information about the player being a human-controlled player, as opposed to a computer player

### Gameboard
    Grid for placing player’s chips
    Keep track of number of free spaces on gameboard
    Retrieve tokens at coordinates on the grid
    Add/remove tokens from the grid


## Case/use classes:
### GameBoardManager
    Keeps track of current state of game board
    Keeps track of player’s chips on board
    Instructs gameboard to add/remove tokens from the grid
    Checks if a player move (adding or removing a token) is valid
    Checks for valid house states on board
    Helps free space when a chip is removed once opponent forms a house

### PlayerManager
    Keeps records of player game history (wins, losses)
    Create user accounts and give them unique usernames so that we can can calculate the
    probability of winning/losing as per the users. (Next Step)

## Controller classes:
### GamePlay (?)
    Initializes game board
    Initializes players
    Runs GameBoardManager and PlayerManager to start the game (?)
    
## Interface classes
### Main
    Displays gameboard to players
    Prompts players to make moves in game
    Informs users of whether or not moves were made successfully
