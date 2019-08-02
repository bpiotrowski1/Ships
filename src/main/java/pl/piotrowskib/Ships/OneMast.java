package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Board.Board;
import pl.piotrowskib.Interfaces.IShip;

public class OneMast implements IShip {
    @Setter @Getter private char condition = 's';

    @Override
    public boolean placeShip(Board board, int x, int y) {
        return true;
    }
}
