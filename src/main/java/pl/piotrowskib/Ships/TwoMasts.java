package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Interfaces.IMultiMasts;
import pl.piotrowskib.Interfaces.IShip;

public class TwoMasts implements IShip, IMultiMasts {
    private final static int MASTS = 1;
    @Setter @Getter private IShip[] masts;
    @Setter @Getter private char condition = 's';

    public TwoMasts(OneMast oneMast) {
        this.masts = new IShip[MASTS];
        this.masts[MASTS - 1] = oneMast;
    }
}
