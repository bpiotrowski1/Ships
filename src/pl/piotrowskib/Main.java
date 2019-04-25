package pl.piotrowskib;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board hidden = new Board();
        Scanner in = new Scanner(System.in);

        System.out.println("Sprobuj zestrzelic wszystkie statki ukryte na planszy!\nPamietaj ze poczatek planszy to punkt (0,0)");
        while (hidden.getSumOfShips() > 0) {
            int y, x;

            hidden.showArea();
            System.out.print("Podaj x: ");
            y = in.nextInt();
            System.out.print("Podaj y: ");
            x = in.nextInt();
            for (int i = 0; i < 3; i++) {
                System.out.println("\n\n");
            }
            if (x >= Board.getSizeX() || y >= Board.getSizeY() || x < 0 || y < 0) {
                System.out.println("Bledne koordynaty, sprobuj jeszcze raz");
            } else if (hidden.getBoard()[x][y] != null && hidden.getBoard()[x][y].getCondition() == 's') {
                System.out.println("Trafiony!");
                hidden.destroyShip(x, y);
            } else if (hidden.getBoard()[x][y] != null && hidden.getBoard()[x][y].getCondition() == 'x') {
                System.out.println("Tu już probowales sprobuj ponownie");
            } else {
                System.out.println("Pudło!");
            }
        }
        System.out.println("Gratulacje! Zestrzeliles wszystkie statki!");
    }
}
