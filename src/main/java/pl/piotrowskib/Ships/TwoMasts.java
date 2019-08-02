package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Board.Board;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Statics.Constants;

import java.util.Random;

public class TwoMasts implements IShip {
    @Setter @Getter private char condition = 's';

    public boolean placeShip(Board board, int x, int y) {
        Random rand = new Random();
        int firstMastX, firstMastY;

        if (rand.nextInt(2) == 0) {
            firstMastX = board.destination(x);
            firstMastY = y;
        } else {
            firstMastX = x;
            firstMastY = board.destination(y);
        }

        if (board.isPointInBoard(firstMastX, firstMastY)) {
            String firstMastCords = String.valueOf(Constants.CORDS[firstMastX]) + firstMastY;
            if (rand.nextBoolean() && board.checkArea(x, y) && board.checkArea(firstMastX, firstMastY)) {
                board.putShip(firstMastCords, new OneMast());
                return true;
            }
        }
        return false;
    }
}
