package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;

public class Destroyer implements ShipBlueprints {

    private static final ShipTypes type = ShipTypes.DESTROYER;

    @Override
    public ShipTypes getType() {
        return type;
    }
}
