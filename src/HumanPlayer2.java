public class HumanPlayer2 extends Player {
    public HumanPlayer2(String username, String colour) {
        super(username, colour);
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
