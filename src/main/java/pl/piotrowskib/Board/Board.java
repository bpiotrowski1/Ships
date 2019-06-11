package pl.piotrowskib.Board;

import lombok.Getter;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Ships.NoShip;
import pl.piotrowskib.Ships.OneMast;
import pl.piotrowskib.Statics.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    @Getter private int sumOfShips = 10;
    @Getter private Map<String, IShip> board = new HashMap<>();
    private int[] shipsToInit = {0, 4, 0, 0, 0};    //0x 0mast, 4x 1mast, 3x 2mast, 2x 3mast, 1x 4mast

    public Board() {
        generateShips();
    }

    private void generateShips() {
        Random rand = new Random();
        for (int i = 1; i < shipsToInit.length; i++) {
            for (int j = 0; j < shipsToInit[i]; j++) {
                boolean placed = true;
                while (placed) {
                    int x = rand.nextInt(Constants.SIZE_X - 2) + 1, y = rand.nextInt(Constants.SIZE_Y - 2) + 1;
                    String cords = String.valueOf(Constants.CORDS[x]) + y;
                    if (rand.nextBoolean() && checkArea(x, y)) {
                        board.put(cords, new OneMast());
                        placed = false;
                    }
                }
            }
        }
        fillEmpty();
        /*
        for (int i = 1; i < Constants.SIZE_X; i++) {
            for (int j = 1; j < Constants.SIZE_Y; j++) {
                String cords = String.valueOf(Constants.CORDS[i]) + j;
                if (rand.nextBoolean() && checkArea(i, j)) {
                    board.put(cords, new OneMast());
                } else {
                    board.put(cords, new NoShip());
                }
            }
        }
         */
    }

    private void fillEmpty() {
        for (int i = 1; i < Constants.SIZE_X; i++) {
            for (int j = 1; j < Constants.SIZE_Y; j++) {
                String cords = String.valueOf(Constants.CORDS[i]) + j;
                if (board.get(cords) == null) {
                    board.put(cords, new NoShip());
                }
            }
        }
    }

    private boolean checkArea(int x, int y) {
        if (x == 0) {
            if (y == 0) {
                return true;
            } else {
                String cords = String.valueOf(Constants.CORDS[x]) + (y - 1);
                return y - 1 > 0 && (board.get(cords) == null || board.get(cords).getCondition() != 's');
            }
        } else {
            String cords = String.valueOf(Constants.CORDS[x]) + y;
            if (y == 0) {
                return x - 1 > 0 && (board.get(cords) == null || board.get(cords).getCondition() != 's');
            } else {
                String cords2 = String.valueOf(Constants.CORDS[x]) + (y - 1);
                String cords3 = String.valueOf(Constants.CORDS[x - 1]) + (y - 1);
                return x - 1 > 0 && (board.get(cords) == null || board.get(cords).getCondition() != 's') &&
                        y - 1 > 0 && (board.get(cords2) == null || board.get(cords2).getCondition() != 's') &&
                        (board.get(cords3) == null || board.get(cords3).getCondition() != 's');
            }
        }
    }

    public void showAreaNoHidden() {
        for (int i = 0; i <= Constants.SIZE_X; i++) {
            for (int j = 0; j <= Constants.SIZE_Y; j++) {
                if (i == 0 || i == Constants.SIZE_X) {
                    System.out.print("-");
                } else if (j == 0 || j == Constants.SIZE_Y) {
                    System.out.print("|");
                } else {
                    String cords = String.valueOf(Constants.CORDS[i]) + j;
                    System.out.print(board.get(cords).getCondition());
                }
            }
            System.out.println();
        }
    }

    public void showArea() {
        for (int i = 0; i <= Constants.SIZE_X; i++) {
            for (int j = 0; j <= Constants.SIZE_Y; j++) {
                if (i == 0 || i == Constants.SIZE_X) {
                    System.out.print("-");
                } else if (j == 0 || j == Constants.SIZE_Y) {
                    System.out.print("|");
                } else {
                    String cords = String.valueOf(Constants.CORDS[i]) + j;
                    if (board.get(cords).getCondition() != 's') {
                        System.out.print(board.get(cords).getCondition());
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void destroyShip(String cords) {
        board.get(cords.toUpperCase()).setCondition('x');
        sumOfShips--;
    }

    public void miss(String cords) {
        board.get(cords).setCondition('o');
    }

    public IShip getShip(String cords) {
        return board.get(cords);
    }
}
