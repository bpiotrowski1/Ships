package pl.piotrowskib;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board hidden = new Board();
        Scanner in = new Scanner(System.in);

        hidden.generateShipsPosition(hidden.getBoard());
        System.out.println("Sprobuj zestrzelic wszystkie statki ukryte na planszy!\nPamietaj ze poczatek planszy to punkt (0,0)");
        while (hidden.getSumOfShips() > 0) {
            char[][] board = hidden.getBoard();
            int y, x;

            hidden.showBoard(board);
            System.out.print("Podaj x: ");
            y = in.nextInt();
            System.out.print("Podaj y: ");
            x = in.nextInt();
            for (int i = 0; i < 3; i++) {
                System.out.println("\n\n");
            }
            if (x >= Board.getSizeX() || y >= Board.getSizeY() || x < 0 || y < 0) {
                System.out.println("Bledne koordynaty, sprobuj jeszcze raz");
            } else if (board[y][x] == 's') {
                board[y][x] = 'x';
                System.out.println("Trafiony! Sprobuj trafic kolejny. Pozostalo: " + (hidden.getSumOfShips() - 1));
                hidden.destroidMast(x, y);
            } else if (board[y][x] == ' ') {
                board[y][x] = 'o';
                System.out.println("Pudlo! Sprobuj jeszcze raz. Pozostalo: " + hidden.getSumOfShips());
            } else {
                System.out.println("W tym miejscu juz probowales, sprobuj ponownie gdzie indziej");
            }
        }
        System.out.println("Gratulacje! Zestrzeliles wszystkie statki!");
    }
}
