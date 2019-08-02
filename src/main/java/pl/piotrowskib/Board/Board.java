package pl.piotrowskib.Board;

import lombok.Getter;
import pl.piotrowskib.Interfaces.IShip;
import pl.piotrowskib.Ships.*;
import pl.piotrowskib.Statics.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static pl.piotrowskib.Statics.Constants.SHIPS_TO_INIT;

public class Board {
    @Getter private int sumOfShips = 0;
    @Getter private Map<String, IShip> board = new HashMap<>();

    public Board() {
        for (int i = 0; i < SHIPS_TO_INIT.length; i++) {
            for (int j = 0; j < SHIPS_TO_INIT[i]; j++) {
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
            IShip ship = new NoShip();
            if (checkArea(x, y)) {
                switch (masts) {
                    case 1:
                        ship = new OneMast();
                        break;
                    case 2:
                        ship = new TwoMasts();
                        break;
                    case 3:
                        ship = new ThreeMasts();
                        break;
                    case 4:
                        ship = new FourMasts();
                        break;
                }
                placed = ship.placeShip(this, x, y);
                if(placed) {
                    board.put(cords, ship);
                }
                sumOfShips++;
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

    public String convertToCords(int x, int y) {
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
