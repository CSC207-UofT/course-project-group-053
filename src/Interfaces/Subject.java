package Interfaces;

import Entity.Token;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObserver(String position, Token token, String username);
    void notifyObserver(String position, String username);
    void notifyObserver(String position);
}
