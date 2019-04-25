package pl.piotrowskib;

public class OneMast implements Ship {
    private final int MASTS = 1;
    private char condition = 's';

    @Override
    public char getCondition() {
        return condition;
    }

    @Override
    public void setCondition(char condition) {
        this.condition = condition;
    }
}
