package pl.piotrowskib;

import java.util.Random;

class Board {
    private static final int SIZE_X = 10;
    private static final int SIZE_Y = 10;
    private int sumOfShips = 10;
    private Ship[][] board = new Ship[SIZE_Y][SIZE_X];

    Board() {
        generateShips();
    }

    private void generateShips() {
        Random rand = new Random();
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (rand.nextBoolean() && checkArea(i, j)) {
                    board[i][j] = new OneMast();
                } else {
                    board[i][j] = null;
                }
            }
        }
    }

    private boolean checkArea(int x, int y) {
        if (x == 0) {
            if (y == 0) {
                return true;
            } else {
                return y - 1 > 0 && (board[x][y - 1] == null || board[x][y - 1].getCondition() != 's');
            }
        } else {
            if (y == 0) {
                return x - 1 > 0 && (board[x - 1][y] == null || board[x - 1][y].getCondition() != 's');
            } else {
                return x - 1 > 0 && (board[x - 1][y] == null || board[x - 1][y].getCondition() != 's') &&
                        y - 1 > 0 && (board[x][y - 1] == null || board[x][y - 1].getCondition() != 's') &&
                        (board[x - 1][y - 1] == null || board[x - 1][y - 1].getCondition() != 's');
            }
        }
    }

    void showArea() {
        for (int i = -1; i <= SIZE_X; i++) {
            for (int j = -1; j <= SIZE_Y; j++) {
                if (i == -1 || i == SIZE_X) {
                    System.out.print("-");
                } else if (j == -1 || j == SIZE_Y) {
                    System.out.print("|");
                } else {
                    if (board[i][j] == null) {
                        System.out.print(" ");
                    } else if (board[i][j].getCondition() != 's') {
                        System.out.print(board[i][j].getCondition());
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    void destroyShip(int x, int y) {
        board[x][y].setCondition('x');
        sumOfShips--;
    }

    Ship[][] getBoard() {
        return board;
    }

    static int getSizeX() {
        return SIZE_X;
    }

    static int getSizeY() {
        return SIZE_Y;
    }

    int getSumOfShips() {
        return sumOfShips;
    }
}
