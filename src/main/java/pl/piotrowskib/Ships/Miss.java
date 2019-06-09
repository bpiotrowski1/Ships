package pl.piotrowskib.Ships;

import lombok.Getter;
import lombok.Setter;
import pl.piotrowskib.Interfaces.IShip;

public class Miss implements IShip {
    @Setter @Getter private char condition = 'o';
}
