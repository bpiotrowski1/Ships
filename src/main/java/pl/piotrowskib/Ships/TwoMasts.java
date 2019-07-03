package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Board.Board;
import pl.piotrowskib.Interfaces.IMultiMasts;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Statics.Constants;

import java.util.Random;

public class TwoMasts implements IShip, IMultiMasts {
    private final static int MASTS = 1;
    @Setter @Getter private IShip[] masts;
    @Setter @Getter private char condition = 's';

    public TwoMasts(Board board, int x, int y) {
        this.masts = new IShip[MASTS];
        masts[0] = new OneMast();
        placeShip(board, x, y);
    }

    private void placeShip(Board board, int x, int y) {
        Random rand = new Random();
        boolean placed = true;
        while (placed) {
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
                    board.putShip(firstMastCords, masts[0]);
                    placed = false;
                }
            }
        }
    }
}
