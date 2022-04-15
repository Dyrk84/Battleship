package hu.battleship.model;

import hu.battleship.utils.Colors;

public enum ShipTypes {
    FISHING_BOAT(Colors.ANSI_BLUE + "Halászhajó" + Colors.ANSI_RESET),
    FRIGATE(Colors.ANSI_BLUE + "Fregatt" + Colors.ANSI_RESET),
    DESTROYER(Colors.ANSI_BLUE + "Romboló" + Colors.ANSI_RESET),
    BATTLESHIP(Colors.ANSI_BLUE + "Csatahajó" + Colors.ANSI_RESET),
    AIRCRAFT_CARRIER(Colors.ANSI_BLUE + "Repülőgép-hordozó" + Colors.ANSI_RESET);

    public final String shipType;

    ShipTypes(String shipType) {
        this.shipType = shipType;
    }
}
