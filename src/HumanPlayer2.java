public class HumanPlayer2 extends Player {
    public HumanPlayer2(String username) {
        super(username);
    }

    @Override
    public boolean isHuman() {
        return false;
    }
}
