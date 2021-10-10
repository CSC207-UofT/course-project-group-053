import java.util.*;

public class Main {

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
        while (!col1.equals("B") & !col1.equals("W")){
            System.out.print("Choose Colour for: " + pla1 + ". Type B for Black or W for white \r\n");
            col1 = sc.nextLine();
        }
        // Player player1 = null;
        // p1 = new HumanPlayer(name = pla1, colour = col1);
        String color;
        String col2;
        if (col1.equals("W")){
            color = "Black";
            col2 = "B";
        }else{
            color = "White";
            col2 = "W";
        }
        System.out.print("Type name of the Human Player 2: \r\n");
        String pla2 = sc.nextLine();
        System.out.print("The colour for " + pla2 + " is " + color + "\r\n");
        // Player player2 = null;
        // p2 = new HumanPlayer(name = pla2, colour = col2);
    }

    public static void IniGameBoard(){
        Scanner sc = new Scanner(System.in);
    }
}
