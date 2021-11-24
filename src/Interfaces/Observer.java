package Interfaces;
import Entity.Token;

public interface Observer {
    // for updating about added token to gameboard
    void update(String position, Token token, String username);

    // for updating about removed token from gameboard
    void update(String position, String username);

    // for updating about slided token on gameboard
    void update(String position);
}
