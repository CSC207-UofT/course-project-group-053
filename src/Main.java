import java.util.*;


public class Main {
    public static String Black = "B";
    public static String White = "W";
    public static Player player1;
    public static Player player2;


    public static void main(String[] args) throws InvalidPositionException, RemoveEmptySlotException, RemoveSelfTokenException, RemoveMillException {
        List<Player> lst = Start();
        new GamePlay1(lst);
    }



    /**
     * Setting name and color for players
     *
     * @return Player[]{player1, player2}
     */
    public static List<Player> Start(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Initializing Nine Men Morris \r\n");

        //Setting for player
        System.out.println("1. Type name of the Human Player: \r\n");
        String name_firstinput = sc.nextLine();
        System.out.print("Choose Colour for: " + name_firstinput + ". Type B for Black or W for white \r\n");
        String color_firstinput = sc.nextLine();

        //if user types wrong form
        while (!color_firstinput.equals(Black) & !color_firstinput.equals(White)){
            System.out.print("Choose Colour for: " + name_firstinput + ". Type B for Black or W for white \r\n");
            color_firstinput = sc.nextLine();
        }

        //Setting for other player
        System.out.print("2. Type name of another Human Player: \r\n");
        String name_secondinput = sc.nextLine();

        String color_secondinput;

        //user w/ white is player1
        if (color_firstinput.equals(White)) {
            color_secondinput = Black;
            // done initializing player 1
            player1 = new Player(name_firstinput, White);
            player2 = new Player(name_secondinput, Black);
        }else {
            color_secondinput = White;
            player1 = new Player(name_secondinput, White);
            player2 = new Player(name_firstinput, Black);
        }

        System.out.print("The colour for " + name_secondinput + " is " + color_secondinput + ". ");
        System.out.print(player1.get_username()+" will be playing first.\n\n");

        return List.of(new Player[]{player1, player2});
    }

}

//TODO: Fix output strings for copied blocks
