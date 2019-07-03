package pl.piotrowskib.Main;

import pl.piotrowskib.Board.Board;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board hidden = new Board();
        Scanner in = new Scanner(System.in);

        System.out.println("Sprobuj zestrzelic wszystkie statki ukryte na planszy!");
        while (hidden.getSumOfShips() > 0) {
            String cords;

            //hidden.showArea();
            hidden.showAreaNoHidden();
            System.out.print("Podaj koordynaty: ");
            cords = in.nextLine();
            for (int i = 0; i < 3; i++) {
                System.out.println("\n\n");
            }

            if (cords.length() != 2 && cords.length() != 3) {
                System.out.println("Błędne koordynaty, sprobuj ponownie");
            } else if (hidden.getBoard().containsKey(cords.toUpperCase())) {
                if (hidden.getShip(cords.toUpperCase()).getCondition() == 's') {
                    System.out.println("Trafiony!");
                    hidden.destroyShip(cords.toUpperCase());
                } else if (hidden.getShip(cords.toUpperCase()).getCondition() == 'x' || hidden.getShip(cords.toUpperCase()).getCondition() == 'o') {
                    System.out.println("Tu już probowales sprobuj ponownie");
                } else {
                    System.out.println("Pudło!");
                    hidden.miss(cords.toUpperCase());
                }
            }
        }
        System.out.println("Gratulacje! Zestrzeliles wszystkie statki!");
    }
}
