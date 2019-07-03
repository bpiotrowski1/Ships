package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Board.Board;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Statics.Constants;

import java.util.Random;

public class ThreeMasts implements IShip {
    @Setter
    @Getter
    private char condition = 's';

    public ThreeMasts(Board board, int x, int y) {
        placeShip(board, x, y);
    }

    private void placeShip(Board board, int x, int y) {
        Random rand = new Random();
        boolean placed = false;
        while (!placed) {
            int firstMastX, firstMastY, secondMastX, secondMastY;

            if (rand.nextInt(2) == 0) {
                firstMastX = board.destination(x);
                firstMastY = y;
            } else {
                firstMastX = x;
                firstMastY = board.destination(y);
            }
            if (rand.nextInt(2) == 0) {
                secondMastX = board.destination(firstMastX);
                secondMastY = firstMastY;
            } else {
                secondMastX = firstMastX;
                secondMastY = board.destination(firstMastY);
            }

            if ((x == firstMastX && y == firstMastY) || (x == secondMastX && y == secondMastY)) {
                break;
            }

            if (board.isPointInBoard(firstMastX, firstMastY) && board.isPointInBoard(secondMastX, secondMastY)) {
                String firstMastCords = String.valueOf(Constants.CORDS[firstMastX]) + firstMastY;
                String secondMastCords = String.valueOf(Constants.CORDS[secondMastX]) + secondMastY;
                if (rand.nextBoolean() && board.checkArea(x, y) && board.checkArea(firstMastX, firstMastY) && board.checkArea(secondMastX, secondMastY)) {
                    board.putShip(firstMastCords, new OneMast());
                    board.putShip(secondMastCords, new OneMast());
                    placed = true;
                }
            }
        }
    }
}
