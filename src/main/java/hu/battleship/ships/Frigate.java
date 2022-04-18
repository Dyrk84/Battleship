package hu.battleship.ships;

import hu.battleship.model.ShipBlueprints;
import hu.battleship.model.ShipTypes;
import lombok.Getter;
import lombok.Setter;

public class Frigate implements ShipBlueprints {

    @Getter
    private int shipSize = 2;

    @Getter
    @Setter
    private int shipsToBeDeployed;

    private static final ShipTypes type = ShipTypes.FRIGATE;

    @Override
    public ShipTypes getType() {
        return type;
    }
}
