package pl.piotrowskib.Ships;

import pl.piotrowskib.Interfaces.Ship;

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
