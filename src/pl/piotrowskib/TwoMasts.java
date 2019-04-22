package pl.piotrowskib;

public class TwoMasts implements Ship {
    private final int masts = 2;

    public void getMastsNumber() {
        System.out.println("Mam " + getMasts() + " maszty");
    }

    private int getMasts() {
        return masts;
    }
}
