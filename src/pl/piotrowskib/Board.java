package pl.piotrowskib;

import java.util.Random;

public class Board {
    private static final int SIZE_X = 10;
    private static final int SIZE_Y = 10;
    private int sumOfShips = 10;
    private char[][] board = new char[SIZE_Y][SIZE_X];

    public void showBoard(char[][] board) {
        for (int i = -1; i <= SIZE_X; i++) {
            for (int j = -1; j <= SIZE_Y; j++) {
                if (i == -1 || i == SIZE_X) {
                    System.out.print("=");
                } else if (j == -1 || j == SIZE_Y) {
                    System.out.print("|");
                } else if (board[i][j] != 's') {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static boolean checkArea(char[][] board, int x, int y) {
        if (x == 0) {
            if (y == 0) {
                return true;
            } else {
                if (board[x][y - 1] == 's') {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            if (y == 0) {
                if (board[x - 1][y] == 's') {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (board[x - 1][y] == 's' || board[x][y - 1] == 's') {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    public void generateShipsPosition(char[][] board) {
        Random rand = new Random();
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (rand.nextBoolean() && checkArea(board, i, j)) {
                    board[i][j] = 's';
                } else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public void destroidMast(int x, int y) {
        sumOfShips--;
    }

    public char[][] getBoard() {
        return board;
    }

    public static int getSizeX() {
        return SIZE_X;
    }

    public static int getSizeY() {
        return SIZE_Y;
    }

    public int getSumOfShips() {
        return sumOfShips;
    }
}
