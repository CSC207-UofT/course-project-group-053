public interface Observer {
    // Subject calls notifyObserver(), which calls update for each Observer it is responsible for
    void update(String newState);
    void update(String playerNewTokenPosition, Player otherPlayer);
    void update(String[] newMill, Player otherPlayer);
}
