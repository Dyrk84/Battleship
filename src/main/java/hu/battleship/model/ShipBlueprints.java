package hu.battleship.model;

public interface ShipBlueprints {

    ShipTypes getType();
    int getShipSize();
    void setShipsToBeDeployed(int shipsToBeDeployed);
    int getShipsToBeDeployed();



}
