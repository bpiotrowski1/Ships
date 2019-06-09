package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Interfaces.IShip;

public class NoShip implements IShip {
    @Setter @Getter private char condition = ' ';
}
