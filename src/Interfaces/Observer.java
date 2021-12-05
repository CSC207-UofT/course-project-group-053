package Interfaces;
import Entity.Token;

public interface Observer {
    // for updating after player adds token to gameboard, assuming valid token placement
    void update(String position, Token playerToken);

    // for updating after player removes token from gameboard, assuming valid token removal
    void update(String position);
}