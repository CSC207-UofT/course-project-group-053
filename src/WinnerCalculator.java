import java.util.HashSet;
import java.util.List;

public class WinnerCalculator {

    // Creates and stores a GameBoard instance, and processes Player moves on the GameBoard
    private final GameBoard gb = new GameBoard();
    private final CheckMill cm = new CheckMill(gb);

    public static Player player1;
    public static Player player2;

    public String who_won() {
        Player first = option1(cm.getPlayerHouses(1), cm.getPlayerHouses(2));
        Player second = option2();
        if (first != null) {
            return first.get_username();
        }else if (second != null){
            return second.get_username();
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

    private Player option2(){
        if (player1.get_numchipsonboard() > player2.get_numchipsleft()){
            return player1;
        }else if(player1.get_numchipsonboard() < player2.get_numchipsonboard()){
            return player2;
        }
        return null;
    }


}
