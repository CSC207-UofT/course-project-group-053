package Interfaces;
import Entity.Token;
import Exceptions.RemoveSelfTokenException;

public interface Observer {
    // for updating after player adds token to gameboard, assuming valid token placement
    void update(String position, String username, Token playerToken);

    // for updating after player removes token from gameboard, assuming valid token removal
    void update(String position, String username);

    // for updating after player slides token from gameboard, assuming valid token slide
    void update(String[] oldToNewPosition);
}