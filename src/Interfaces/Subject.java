package Interfaces;
import Entity.Token;

public interface Subject {
    void register(Observer o);
    void notifyObservers(String position, Token playerToken);
    void notifyObservers(String position);
}
