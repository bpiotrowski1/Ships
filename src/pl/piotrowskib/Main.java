package pl.piotrowskib;

public class Main {

    public static void main(String[] args) {
        Ship[] ships = new Ship[2];

        ships[0] = new OneMast();
        ships[1] = new TwoMasts();

        for(Ship ship : ships) {
            ship.getMastsNumber();
        }
    }
}
