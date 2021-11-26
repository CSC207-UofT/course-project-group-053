package UseCases;

import Controller.GamePlay;
import Entity.Player;
import Controller.GamePlay1;

public class WinnerCalculator {
    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final CheckMill cm;

    public static Player player1;
    public static Player player2;

    public WinnerCalculator(GamePlay gp, Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        cm = gp.checkMill;
    }

    public String who_won() {
        Player first = option1(cm.getPlayerHouses(1), cm.getPlayerHouses(2));
        if (first != null) {
            return first.get_username() + " won";
        }
        else{
            return "It's a Tie";
        }
    }

    private Player option1(int p1Houses, int p2Houses){
        if (p1Houses > p2Houses){
            return player1;
        }else if(p1Houses < p2Houses){
            return player2;
        }
        return null;
    }
}
