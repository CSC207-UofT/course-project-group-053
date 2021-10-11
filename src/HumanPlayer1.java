public class HumanPlayer1 extends Player{
    public HumanPlayer1(String username) {
        super(username);
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
