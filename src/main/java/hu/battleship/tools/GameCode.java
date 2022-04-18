package hu.battleship.tools;

import hu.battleship.model.ShipTypes;
import hu.battleship.ships.*;
import hu.battleship.utils.Colors;
import hu.battleship.utils.TextSeparator;

import java.util.*;

public class GameCode {

    /*
    -kell egy menü, ahol bekéri a játékosok számát
    -kell egy menü, ahol bekéri, hogy mekkora térképeken menjen a játék.
    -kell egy térkép factory, ami annyi térképet gyárt le, ahány játékos lett beállítva. Ezeket a térképeket amikor használjuk, fel kell tüntetni, hogy melyik játékos térképe.
    -hajókat kell tudni lerakni. Ezt lehetne úgy, hogy kiadja, hogy milyen hajót kellene letenni, és arra kér egy inputot, hogy melyik korrdinátára akarom letenni. Az inputot validálni kell úgy, hogy csak olyan helyre lehessen letenni hajót, amin még nincs hajó, nincs a szomszédjában hajó, és a pályán van.
     */
    private static FishingBoat fishingBoat;
    private static Frigate frigate;
    private static Destroyer destroyer;
    private static Battleship battleship;
    private static AircraftCarrier aircraftCarrier;

    // = new GameMap(numberOfSize, numberOfSize); //TODO hogy lehet objecteket generálni és letárolni listába?

    public static String[][] oceanMapPlayer1;
    public static String[][] oceanMapPlayer2;
    public static String[][] oceanMapPlayer3;
    public static String[][] oceanMapPlayer4;

    private static List<String[][]> arrayMapsForPlayersList = new ArrayList<>();

    public static void gameStart() {
        int numberOfPlayers = Menus.numberOfPlayersMenu(); //ezzel bekérem, hogy hány játékos akar játszani
        int numberOfSize = Menus.numberOfSize();  //inputot kérek be, hogy hányszor hányas legyen a map, 3-10-ig.

        oceanMapCreatorWithoutValues(numberOfSize + 3); //a +1 azért kell, mert a multi map 0. sorait betükkel és számokkal tölti fel a koordináták miatt.
        writerOfMapOfPlayers(numberOfPlayers); //a multitömb 0-0 koordinátájára beírja, hogy melyik játékosé a térkép
        toSetSail(numberOfPlayers, numberOfSize); //itt lehet letenni a hajókat

        //hajó lerakási mechanizmusa innen kezdődik.
        //úgy lenne jó, hogy bejönne egy hajó fajta (legnagyobb), és azt kellene lerakni.
        //hogyan oldjam meg, hogy legyen egy listám, amiben benne vannak a hajók lehetőleg méret szerint sorba rakva, és a hajókhoz kell egy szám, ami azt mutatja, hogy az adott hajóból mennyit kell még letenni.
        // csinálok egy treemap-et, key-nek sorszám, value-nak hajótípusok és a map key alapján tudom meghívni a hajókat az általam megadott sorrendben (nagyobbak elől), és setterrel beállíthatok egy osztályváltozót hogy az adott pályamérethez hány darab hajót lehessen letenni. Ezt az osztályváltozót használhatnám később hajóvásárláskor is.
        // Egyelőre legyen a lerakás módja az, hogy meg kell adni egy koordinátát, és az lesz a bal(felső) kockája a hajónak.
        //Meg kell nézni a térképen, hogy arra a koordinátára az adott hajót be lehet-e tenni. Hogy?
        //a kapott koordinátát meg kell vizsgálni, hogy oda tehető-e a hajó.
        // Aztán ugyanezeket meg kell nézni a hajó többi kockájával is.
        // Aztán meg kell nézni, hogy van-e másik hajó a hajó közvetlen szomszédságában (sarkok nem számítanak)
        // ha ezek a feltételek megfelelnek, le kell rakni a hajót. hogyan?
        // a hajó most már valid koordinátáit át kell adni a mapnak, ahol UNDISCOVERED_SHIP-re kell átállítani a BLUE_WATER-okat.
    }

    private static void writerOfMapOfPlayers(int numberOfPlayers) {
        int mapCounter = 0; //ennek segítségével kapják meg a map-ok a [0][0] koordinátára stringben, hogy az adott map hányadik játékosé
        for (int i = 0; i < numberOfPlayers; i++) { //ez a játékosok száma alapján lepéldányosítja a map.okat.
            mapCounter++;
            GameMap.mapCreator(arrayMapsForPlayersList.get(i));
            arrayMapsForPlayersList.get(i)[0][0] = mapCounter + ". játékos térképe:\n   ";
        }
    }

    private static void oceanMapCreatorWithoutValues(int numberOfSize) { //
        oceanMapPlayer1 = new String[numberOfSize][numberOfSize];
        oceanMapPlayer2 = new String[numberOfSize][numberOfSize];
        oceanMapPlayer3 = new String[numberOfSize][numberOfSize];
        oceanMapPlayer4 = new String[numberOfSize][numberOfSize];
        arrayMapsForPlayersList.add(oceanMapPlayer1);
        arrayMapsForPlayersList.add(oceanMapPlayer2);
        arrayMapsForPlayersList.add(oceanMapPlayer3);
        arrayMapsForPlayersList.add(oceanMapPlayer4);
    }

    private static void toSetSail(int numberOfPlayers, int numberOfSize) { //ezzel a metódussal tudják a játékosok lerakni a hajóka a térképekre
        for (int i = 0; i < numberOfPlayers; i++) { //így minden játékos sorra kerül
            TextSeparator.format(i + 1 + ". játékos rakja le a hajóit!");
            GameMap.mapPrinter(arrayMapsForPlayersList.get(i));

            switch (numberOfSize) {
                case 3:
                    fromSwitchSetShipsToBeDeployed(2, 1, 0, 0, 0); //beállítja, hogy melyik típusú hajóból mennyit fogunk tudni letenni
                    printGetShipsTypeAndHowMany(); //kiiratja hogy mennyi és milyen típusú hajókat kell letenni
                    shipsToBeDeployed(numberOfSize, i); //ebben lehet letenni a hajókat
                    break;
                case 4:
                    fromSwitchSetShipsToBeDeployed(3, 2, 0, 0, 0);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                case 5:
                    fromSwitchSetShipsToBeDeployed(3, 2, 1, 0, 0);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                case 6:
                    fromSwitchSetShipsToBeDeployed(5, 4, 2, 1, 0);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                case 7:
                    fromSwitchSetShipsToBeDeployed(5, 4, 3, 2, 0);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                case 8:
                    fromSwitchSetShipsToBeDeployed(5, 4, 3, 2, 1);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                case 9:
                    fromSwitchSetShipsToBeDeployed(6, 5, 4, 3, 2);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                case 10:
                    fromSwitchSetShipsToBeDeployed(7, 6, 5, 4, 3);
                    printGetShipsTypeAndHowMany();
                    shipsToBeDeployed(numberOfSize, i);
                    break;
                default:
                    System.out.println("nem választhatsz ilyen méretű pályát!");
            }
        }
    }

    private static void shipsToBeDeployed(int numberOfSize, int i) {
        if (aircraftCarrier.getShipsToBeDeployed() > 0) {
            while (0 < aircraftCarrier.getShipsToBeDeployed()) {
                System.out.println("Válaszd ki a " + ShipTypes.AIRCRAFT_CARRIER.shipType + " bal felső sarkának koordinátáját: ");
                int[] coordinates = inputForCoordinates(numberOfSize);
                if ((arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1]].equals(MapMarks.BLUE_WATER.markType)) &&
                        (arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1]].equals(MapMarks.BLUE_WATER.markType)) &&
                        (arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 1].equals(MapMarks.BLUE_WATER.markType)) &&
                        (arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1] + 1].equals(MapMarks.BLUE_WATER.markType)) &&
                        (arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 2].equals(MapMarks.BLUE_WATER.markType)) &&
                        (arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1] + 2].equals(MapMarks.BLUE_WATER.markType))) {
                    System.out.println("bla");
                }
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 1] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1] + 1] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 2] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1] + 2] = MapMarks.UNDISCOVERED_SHIP.markType;
                GameMap.mapPrinter(arrayMapsForPlayersList.get(i));
                aircraftCarrier.setShipsToBeDeployed(aircraftCarrier.getShipsToBeDeployed() - 1);
                if (aircraftCarrier.getShipsToBeDeployed() > 0) {
                    System.out.println("Még mindig ki kell rakni " + aircraftCarrier.getShipsToBeDeployed() + "db " + ShipTypes.AIRCRAFT_CARRIER.shipType + "t!");
                }
            }
        }
        if (battleship.getShipsToBeDeployed() > 0) {
            while (0 < battleship.getShipsToBeDeployed()) {
                System.out.println("Válaszd ki a " + ShipTypes.BATTLESHIP.shipType + " bal felső sarkának koordinátáját: ");
                int[] coordinates = inputForCoordinates(numberOfSize);
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 1] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0] + 1][coordinates[1] + 1] = MapMarks.UNDISCOVERED_SHIP.markType;
                GameMap.mapPrinter(arrayMapsForPlayersList.get(i));
                battleship.setShipsToBeDeployed(battleship.getShipsToBeDeployed() - 1);
                if (battleship.getShipsToBeDeployed() > 0) {
                    System.out.println("Még mindig ki kell rakni " + battleship.getShipsToBeDeployed() + "db " + ShipTypes.BATTLESHIP.shipType + "t!");
                }
            }
        }
        if (destroyer.getShipsToBeDeployed() > 0) {
            while (0 < destroyer.getShipsToBeDeployed()) {
                System.out.println("Válaszd ki a " + ShipTypes.DESTROYER.shipType + " bal sarkának koordinátáját: ");
                int[] coordinates = inputForCoordinates(numberOfSize);
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 1] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 2] = MapMarks.UNDISCOVERED_SHIP.markType;
                GameMap.mapPrinter(arrayMapsForPlayersList.get(i));
                destroyer.setShipsToBeDeployed(destroyer.getShipsToBeDeployed() - 1);
                if (destroyer.getShipsToBeDeployed() > 0) {
                    System.out.println("Még mindig ki kell rakni " + destroyer.getShipsToBeDeployed() + "db " + ShipTypes.DESTROYER.shipType + "t!");
                }
            }
        }
        if (frigate.getShipsToBeDeployed() > 0) {
            while (0 < frigate.getShipsToBeDeployed()) {
                System.out.println("Válaszd ki a " + ShipTypes.FRIGATE.shipType + " bal sarkának koordinátáját: ");
                int[] coordinates = inputForCoordinates(numberOfSize);
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1] + 1] = MapMarks.UNDISCOVERED_SHIP.markType;
                GameMap.mapPrinter(arrayMapsForPlayersList.get(i));
                frigate.setShipsToBeDeployed(frigate.getShipsToBeDeployed() - 1);
                if (frigate.getShipsToBeDeployed() > 0) {
                    System.out.println("Még mindig ki kell rakni " + frigate.getShipsToBeDeployed() + "db " + ShipTypes.FRIGATE.shipType + "ot!");
                }
            }
        }
        if (fishingBoat.getShipsToBeDeployed() > 0) {
            while (0 < fishingBoat.getShipsToBeDeployed()) {
                System.out.println("Válaszd ki a " + ShipTypes.FISHING_BOAT.shipType + " bal sarkának koordinátáját: ");
                int[] coordinates = inputForCoordinates(numberOfSize);
                arrayMapsForPlayersList.get(i)[coordinates[0]][coordinates[1]] = MapMarks.UNDISCOVERED_SHIP.markType;
                GameMap.mapPrinter(arrayMapsForPlayersList.get(i));
                fishingBoat.setShipsToBeDeployed(fishingBoat.getShipsToBeDeployed() - 1);
                if (fishingBoat.getShipsToBeDeployed() > 0) {
                    System.out.println("Még mindig ki kell rakni " + fishingBoat.getShipsToBeDeployed() + "db " + ShipTypes.FISHING_BOAT.shipType + "t!");
                }
            }
        }
    }

    private static int[] inputForCoordinates(int numberOfSize) {
        //ezekkel lehet bekérni a koordinátát
        int rowInput = Menus.rowInputRequester(numberOfSize); //bekérem a sorok koordinátájának betüjelét (függőleges)
        int columnInput = Menus.columnInputRequester(numberOfSize); //bekérem az oszlopok koordinátájának számát
        return new int[]{rowInput, columnInput};
    }

    private static void fromSwitchSetShipsToBeDeployed(int fi, int fr, int de, int ba, int ai) {
        shipFactory();
        fishingBoat.setShipsToBeDeployed(fi);
        frigate.setShipsToBeDeployed(fr);
        destroyer.setShipsToBeDeployed(de);
        battleship.setShipsToBeDeployed(ba);
        aircraftCarrier.setShipsToBeDeployed(ai);
    }

    private static void shipFactory() {
        fishingBoat = new FishingBoat();
        frigate = new Frigate();
        destroyer = new Destroyer();
        battleship = new Battleship();
        aircraftCarrier = new AircraftCarrier();
    }

    private static void printGetShipsTypeAndHowMany() {
        System.out.println("Lerakható hajó fajták:");
        if (aircraftCarrier.getShipsToBeDeployed() > 0) {
            System.out.println(ShipTypes.AIRCRAFT_CARRIER.shipType + ":\n " + Colors.ANSI_BLUE + aircraftCarrier.getShipsToBeDeployed() + Colors.ANSI_RESET + "x [ ][ ][ ] \n    [ ][ ][ ] ");
        }
        if (battleship.getShipsToBeDeployed() > 0) {
            System.out.println(ShipTypes.BATTLESHIP.shipType + ":\n " + Colors.ANSI_BLUE + battleship.getShipsToBeDeployed() + Colors.ANSI_RESET + "x [ ][ ] \n    [ ][ ] ");
        }
        if (destroyer.getShipsToBeDeployed() > 0) {
            System.out.println(ShipTypes.DESTROYER.shipType + ":\n " + Colors.ANSI_BLUE + destroyer.getShipsToBeDeployed() + Colors.ANSI_RESET + "x [ ][ ][ ] ");
        }
        if (frigate.getShipsToBeDeployed() > 0) {
            System.out.println(ShipTypes.FRIGATE.shipType + ":\n " + Colors.ANSI_BLUE + frigate.getShipsToBeDeployed() + Colors.ANSI_RESET + "x [ ][ ] ");
        }
        if (fishingBoat.getShipsToBeDeployed() > 0) {
            System.out.println(ShipTypes.FISHING_BOAT.shipType + ":\n " + Colors.ANSI_BLUE + fishingBoat.getShipsToBeDeployed() + Colors.ANSI_RESET + "x [ ] ");
        }
    }
}
