package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;

public class AircraftCarrier implements ShipBlueprints {

    private static final ShipTypes type = ShipTypes.AIRCRAFT_CARRIER;

    @Override
    public ShipTypes getType() {
        return type;
    }

    public AircraftCarrier(){
        System.out.println("A " + type.shipType + " vízre bocsátva!");
    }
}
