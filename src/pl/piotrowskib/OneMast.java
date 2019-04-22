package pl.piotrowskib;

public class OneMast implements Ship {
    private final int masts = 1;

    public void getMastsNumber() {
        System.out.println("Mam " + getMasts() + " maszt");
    }

    private int getMasts() {
        return masts;
    }
}
