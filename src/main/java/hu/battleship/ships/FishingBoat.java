package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;

public class FishingBoat implements ShipBlueprints {

    private static final ShipTypes type = ShipTypes.FISHING_BOAT;

    @Override
    public ShipTypes getType() {
        return type;
    }
}
