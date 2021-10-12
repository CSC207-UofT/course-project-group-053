import java.util.*;


public class Main {
    public static String Black = "B";
    public static String White = "W";

    public static void main(String[] args){
        List<Player> lst = Start();
        GamePlay(lst);
    }

    public static List<Player> Start(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Initializing Nine Men Morris \r\n");
        System.out.print("Type name of the Human Player 1: \r\n");
        String pla1 = sc.nextLine();
        System.out.print("Choose Colour for: " + pla1 + ". Type B for Black or W for white \r\n");
        String col1 = sc.nextLine();
        while (!col1.equals(Black) & !col1.equals(White)){
            System.out.print("Choose Colour for: " + pla1 + ". Type B for Black or W for white \r\n");
            col1 = sc.nextLine();
        }
        HumanPlayer1 player1 = new HumanPlayer1(pla1, col1);
        String color;
        String col2;
        if (col1.equals(White)){
            color = "Black";
            col2 = Black;
        }else{
            color = "White";
            col2 = White;
        }
        System.out.print("Type name of the Human Player 2: \r\n");
        String pla2 = sc.nextLine();
        System.out.print("The colour for " + pla2 + " is " + color + "\r\n");

        HumanPlayer2 player2 = new HumanPlayer2(pla2, col2);
        return List.of(new Player[]{player1, player2});
    }

    public static void GamePlay(List<Player> lst){
        GameBoardManager gbManager = new GameBoardManager();
        Scanner sc = new Scanner(System.in);

        HumanPlayer1 player1 = new HumanPlayer1(lst.get(0).str_player_username, lst.get(0).str_player_tokencolour);
        HumanPlayer2 player2 = new HumanPlayer2(lst.get(1).str_player_username, lst.get(1).str_player_tokencolour);

        String name1 = player1.str_player_username;
        String name2 = player2.str_player_username;
        String col1 = player1.str_player_tokencolour;
        String col2 = player2.str_player_tokencolour;

        System.out.println("Starting Game between " + name1 + " and " + name2);

        System.out.println(gbManager.getGameBoardState());

        boolean b = !gbManager.checkPhaseOneEnd();

        while (b) {

            while(true){
                try{
                    System.out.println(name1 + "'s turn. Place " + col1 + " token. Choose an empty place");
                    // in gameboard manager add a function that returns a lst of positions available
                    String t1 = sc.nextLine();
                    gbManager.processPlayerMove(player1.get_tokencolour(), t1);
                    break;
                }catch(InvalidPositionException | ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid, try again.Choose empty space");
                    continue;
                    // t1 = sc.next(); // skip the invalid token
                    // continue; is not required
                }
            }
            System.out.println(gbManager.getGameBoardState());


            // Now check if the player1 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass
            // Then show the current state again


            while(true){
                try{
                    System.out.println(name2 + "'s turn. Place " + col2 + " token. Choose an empty place");
                    // in gameboard manager add a function that returns a lst of positions available
                    String t2 = sc.nextLine();
                    gbManager.processPlayerMove(player2.get_tokencolour(), t2);
                    break;
                }catch(InvalidPositionException | ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid, try again.Choose empty space");
                    continue;
                    // t1 = sc.next(); // skip the invalid token
                    //is not required
                }
            }
            System.out.println(gbManager.getGameBoardState());

            // Now check if the player1 has created a mill
            // Then let the player1 remove player2's token
            // Do it until the move is valid. If not valid pass

            b = !gbManager.checkPhaseOneEnd();
        }
    }
}
