public class HumanPlayer1 extends Player {
    public HumanPlayer1(String username, String colour) {
        super(username, colour);
    }

    @Override
    public boolean isHuman() {
        return true;
    }

}