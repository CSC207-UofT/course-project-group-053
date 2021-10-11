import java.util.*;


public class Main {
    public static String Black = "B";
    public static String White = "W";

    public static void main(String[] args){
        Start();
        IniGameBoard();
    }

    public static void Start(){
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
        HumanPlayer1 player2 = new HumanPlayer1(pla2, col2);
    }

    public static void IniGameBoard(){
        // initialize a new, empty GameBoard
        GameBoard gb = GameBoardManager.makeNewGameBoard();

        // print the state of the new GameBoard to the screen
        System.out.println(GameBoardManager.getGameBoardState(gb));
    }
}
