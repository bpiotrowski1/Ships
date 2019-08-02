package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Board.Board;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Statics.Constants;

import java.util.Random;

public class FourMasts implements IShip {
    @Setter @Getter private char condition = 's';

    public boolean placeShip(Board board, int x, int y) {
        Random rand = new Random();
        int firstMastX, firstMastY, secondMastX, secondMastY, thirdMastX, thirdMastY;

        if (rand.nextInt(2) == 0) {
            firstMastX = board.destination(x);
            firstMastY = y;
            secondMastX = (x < firstMastX) ? firstMastX + 1 : firstMastX - 1;
            secondMastY = firstMastY;
            thirdMastX = (x < firstMastX) ? secondMastX + 1 : secondMastX - 1;
            thirdMastY = firstMastY;
        } else {
            firstMastX = x;
            firstMastY = board.destination(y);
            secondMastX = firstMastX;
            secondMastY = (y < firstMastY) ? firstMastY + 1 : firstMastY - 1;
            thirdMastX = firstMastX;
            thirdMastY = (y < firstMastY) ? secondMastY + 1 : secondMastY - 1;
        }
        if (board.isPointInBoard(firstMastX, firstMastY) && board.isPointInBoard(secondMastX, secondMastY)
                && board.isPointInBoard(thirdMastX, thirdMastY)) {
            String firstMastCords = board.convertToCords(firstMastX, firstMastY);
            String secondMastCords = board.convertToCords(secondMastX, secondMastY);
            String thirdMastCords = board.convertToCords(thirdMastX, thirdMastY);
            if (rand.nextBoolean() && board.checkArea(x, y) && board.checkArea(firstMastX, firstMastY) && board.checkArea(secondMastX, secondMastY)
                    && board.checkArea(thirdMastX, thirdMastY)) {
                board.putShip(firstMastCords, new OneMast());
                board.putShip(secondMastCords, new OneMast());
                board.putShip(thirdMastCords, new OneMast());
                return true;
            }
        }
        return false;
    }
}
