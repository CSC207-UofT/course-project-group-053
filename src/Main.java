import java.util.*;


public class Main {
    public static String Black = "B";
    public static String White = "W";

    public static void main(String[] args){
        List<Player> lst = Start();
        GameBoardManager gbManager = new GameBoardManager();
        GamePlay(lst, gbManager);

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
        List<Player> lst = List.of(new Player[]{player1, player2});
        return lst;
    }

    public static void GamePlay(List<Player> lst, GameBoardManager game){
        Scanner sc = new Scanner(System.in);

        HumanPlayer1 player1 = new HumanPlayer1(lst.get(0).str_player_username, lst.get(0).str_player_tokencolour);
        HumanPlayer2 player2 = new HumanPlayer2(lst.get(1).str_player_username, lst.get(1).str_player_tokencolour);

        String name1 = player1.str_player_username;
        String name2 = player2.str_player_username;
        String col1 = player1.str_player_tokencolour;
        String col2 = player1.str_player_tokencolour;

        System.out.print("Starting Game between " + name1 + " and " + name2);

        GameBoardManager gbManager = new GameBoardManager();
        System.out.println(gbManager.getGameBoardState());

        //while !game_finished(){ // this function needs to be made in GameboardManager and can be named anything
            //int ini_count_p1 = player1.int_player_numchipsleft;
            //int ini_count_p2 = player2.int_player_numchipsleft;



            //System.out.println(name1 + "'s turn. Place " + col1 + "token. Choose a place from "); // + lst of places to
            // choose from
            // in gameboard manager add a function that returns a lst of positions available
            //String t1 = sc.nextLine();

            // while t1 is not in the lst:
                // System.out.println(name1 + "'s turn. Place " + col1 + "token. Choose a place from ");
                // String t1 = sc.nextLine();

            //game.processPlayerMove(confirm what goes in here)

            //if the exception is thrown, loop the last line again and again

            // next just show what the gameboard looks like

            //System.out.println(gbManager.getGameBoardState());
        //}
    }
}
