package pl.piotrowskib.Interfaces;

import pl.piotrowskib.Board.Board;

public interface IShip {
    char getCondition();
    boolean placeShip(Board board, int x, int y);
    void setCondition(char condition);
}
