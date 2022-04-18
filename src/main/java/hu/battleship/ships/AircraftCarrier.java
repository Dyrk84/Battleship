package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;
import lombok.Getter;
import lombok.Setter;

public class AircraftCarrier implements ShipBlueprints {

    @Getter
    private int shipSize = 6;

    @Getter
    @Setter
    private int shipsToBeDeployed;

    private static final ShipTypes type = ShipTypes.AIRCRAFT_CARRIER;

    @Override
    public ShipTypes getType() {
        return type;
    }

}
