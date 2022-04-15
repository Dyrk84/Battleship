package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;

public class Battleship implements ShipBlueprints {

    private static final ShipTypes type = ShipTypes.BATTLESHIP;

    @Override
    public ShipTypes getType() {
        return type;
    }
}
