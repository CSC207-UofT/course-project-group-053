public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObserver(String playerName, String position, boolean addedToken);
    void notifyObserver(String playerName, String[] newMill);
}
