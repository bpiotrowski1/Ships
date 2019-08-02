package pl.piotrowskib.Board;

import lombok.Getter;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Ships.NoShip;
import pl.piotrowskib.Ships.OneMast;
import pl.piotrowskib.Ships.ThreeMasts;
import pl.piotrowskib.Ships.TwoMasts;
import pl.piotrowskib.Statics.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    @Getter
    private int sumOfShips = 0;
    @Getter
    private Map<String, IShip> board = new HashMap<>();
    private int[] shipsToInit = {4, 3, 2, 0};    //0x 0mast, 4x 1mast, 3x 2mast, 2x 3mast, 1x 4mast

    public Board() {
        generateShips();
    }

    private void generateShips() {
        for (int i = 0; i < shipsToInit.length; i++) {
            for (int j = 0; j < shipsToInit[i]; j++) {
                placeShip(i + 1);
            }
        }
        fillEmpty();
    }

    private void placeShip(int masts) {
        Random rand = new Random();
        boolean placed = false;
        while (!placed) {
            int x = rand.nextInt(Constants.SIZE_X - 2) + 1, y = rand.nextInt(Constants.SIZE_Y - 2) + 1;
            String cords = convertToCords(x, y);
            if (checkArea(x, y)) {
                switch (masts) {
                    case 1:
                        board.put(cords, new OneMast());
                        break;
                    case 2:
                        board.put(cords, new TwoMasts(this, x, y));
                        break;
                    case 3:
                        board.put(cords, new ThreeMasts(this, x, y));
                        break;
                }
                sumOfShips++;
                placed = true;
            }
        }

    }

    public void putShip(String cords, IShip mast) {
        board.put(cords, mast);
        sumOfShips++;
    }

    public int destination(int cord) {
        Random rand = new Random();
        int[] toRand = {-1, 1};
        return cord + toRand[rand.nextInt(2)];
    }

    private String convertToCords(int x, int y) {
        return String.valueOf(Constants.CORDS[x]) + y;
    }

    public boolean checkArea(int x, int y) {
        if (isPointInBoard(x + 1, y) && board.containsKey(convertToCords(x + 1, y))) {
            return false;
        } else if (isPointInBoard(x + 1, y + 1) && board.containsKey(convertToCords(x + 1, y + 1))) {
            return false;
        } else if (isPointInBoard(x, y + 1) && board.containsKey(convertToCords(x, y + 1))) {
            return false;
        } else if (isPointInBoard(x - 1, y + 1) && board.containsKey(convertToCords(x - 1, y + 1))) {
            return false;
        } else if (isPointInBoard(x - 1, y) && board.containsKey(convertToCords(x - 1, y))) {
            return false;
        } else if (isPointInBoard(x - 1, y - 1) && board.containsKey(convertToCords(x - 1, (y - 1)))) {
            return false;
        } else if (isPointInBoard(x, y - 1) && board.containsKey(convertToCords(x, (y - 1)))) {
            return false;
        } else if (isPointInBoard(x + 1, y - 1) && board.containsKey(convertToCords(x + 1, (y - 1)))) {
            return false;
        }
        return true;
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

    public boolean isPointInBoard(int x, int y) {
        return x > 0 && y > 0 && x < Constants.SIZE_X && y < Constants.SIZE_Y;
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
