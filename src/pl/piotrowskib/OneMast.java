package pl.piotrowskib;

public class OneMast implements Ship {
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
