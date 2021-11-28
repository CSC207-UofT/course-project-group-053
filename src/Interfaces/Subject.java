package Interfaces;
import Entity.Token;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers(String position, String username, Token playerToken);
    void notifyObservers(String position, String username);
    void notifyObservers(String[] oldToNewPosition);
}
