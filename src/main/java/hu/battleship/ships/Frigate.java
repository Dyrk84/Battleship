package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;

public class Frigate implements ShipBlueprints {

    private static final ShipTypes type = ShipTypes.FRIGATE;

    @Override
    public ShipTypes getType() {
        return type;
    }
}
