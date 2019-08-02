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
                secondMastX = (x < firstMastX) ? firstMastX + 1 : firstMastX - 1;
                secondMastY = firstMastY;
            } else {
                firstMastX = x;
                firstMastY = board.destination(y);
                secondMastX = firstMastX;
                secondMastY = (y < firstMastY) ? firstMastY + 1 : firstMastY - 1;
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
