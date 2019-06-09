package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Interfaces.IShip;

public class TwoMasts implements IShip {
    private final int MASTS = 2;
    @Setter @Getter private char condition = 's';
}
