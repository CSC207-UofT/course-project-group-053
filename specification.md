# Project domain: 9 men morris multiplayer game

# Project description:
Launching the project opens a GUI of a 9 men morris game board. The GUI starts with an intro screen where the user may choose whether to play with another player or against the computer. After this, the board is loaded and the game starts. The game starts, and goes through the following 2 phases:

Phase 1: Placing the chips
Phase 2: Sliding the chips

Players (1 or more) may click on the board to place their chips during the first phase, and slide their chips along the board during the second phase. When a player gets three of their chips horizontally or vertically in a row (a “house”), they may remove one of the other player’s chips. The game ends when one player is reduced to two chips on the board, as they can no longer make a house.

The project so far is a command-line version of the Nine Men's Morris board game, for two players to play. Launching the program prompts the first player to enter their player name, and what color they want their game pieces (tokens) to be. The program then prompts the same information from the second player. After this, the game starts, and players are shown an ASCII graphic of the gameboard. Players take turns typing in the command line, specifying the coordinates where they want to place their game pieces.

# Project classes

## Entity classes
### Player
    Keep track of player’s chips (i.e: how many they have right now?)
Subclasses: Human Player 1, Human Player 2, Computer (Next step)

### Gameboard
    Grid for placing player’s chips

### Game (Abstract class)
Human vs Human
Computer vs Human (Next step)
Computer vs Computer (Next Step)

## Case/use classes:
### GameBoardManager
    Keeps track of current state of game board
    Keeps track of player’s chips on board
    clears/sets chips on the game board
    Checks for a valid move
    Checks for valid house states on board
    Helps free space when a chip is removed once opponent forms a house
    Checks if the removal was valid or not

### PlayerManager
    Keeps records of player game history (wins, losses)
    Keep track of player’s chips on board (either PlayerManager or GameBoardManager
    does this)
    Allows players to make moves on a game board -> slide or place chip
    Create user accounts and give them unique usernames so that we can can calculate the
    probability of winning/losing as per the users. (Next Step)

## Controller classes:
### NineMenMorrisGame
    Initializes game board
    Initializes players
    Runs GameBoardManager and PlayerManager to start the game (?)
