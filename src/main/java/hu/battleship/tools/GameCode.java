package hu.battleship.tools;

import hu.battleship.model.ShipTypes;
import hu.battleship.utils.TextSeparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameCode {

    /*
    -kell egy menü, ahol bekéri a játékosok számát (ez most nem fontos, 2 lesz alapból beállítva)
    -kell egy menü, ahol bekéri, hogy mekkora térképeken menjen a játék.
    -kell egy térkép factory, ami annyi térképet gyárt le, ahány játékos lett beállítva. Ezeket a térképeket amikor használjuk, fel kell tüntetni, hogy melyik játékos térképe.
    -hajókat kell tudni lerakni. Ezt lehetne úgy, hogy kiadja, hogy milyen hajót kellene letenni, és arra kér egy inputot, hogy melyik korrdinátára akarom letenni. Az inputot validálni kell úgy, hogy csak olyan helyre lehessen letenni hajót, amin még nincs hajó, nincs a szomszédjában hajó, és a pályán van.
    -
     */
    private static List<Map> mapList = new ArrayList<>(); //ebben a listában vannak a játékosok térképei

    public static void gameStart(){
        int numberOfPlayers = Menus.numberOfPlayersMenu(); //ezzel bekérem, hogy hány játékos akar játszani
        int numberOfSize = Menus.numberOfSize(); //inputot kérek be, hogy hány X hányas legyen a map, 3-10-ig.

        int mapCounter = 0; //ennek segítségével kapják meg a map-ok a [0][0] koordinátára stringben, hogy az adott map hányadik játékosé
        for (int i = 0; i < numberOfPlayers; i++){ //ez a játékosok száma alapján lepéldányosítja a map.okat.
            mapCounter++; //ez segít a mapokat a [0][0] koordinátáikban bejelölni, hogy hányadik játékoshoz tartoznak.
            Map map = new Map(numberOfSize,numberOfSize);
            map.map[0][0] = mapCounter + ". játékos térképe:\n  ";
            mapList.add(map);
        }

        //hajó lerakási mechanizmusa innen kezdődik.
        //úgy lenne jó, hogy bejönne egy hajó fajta (legnagyobb), és azt kellene lerakni.
        //hogyan oldjam meg, hogy legyen egy listám, amiben benne vannak a hajók lehetőleg méret szerint sorba rakva, és a hajókhoz kell egy szám, ami azt mutatja, hogy az adott hajóból mennyit kell még letenni.
        //-kell egy olyan map, aminek key-jeit én választhatom meg, hogy hogy következzenek sorrendben. Vagy csinálok két listát, és összekötöm őket indexük szerint.
        // Egyelőre legyen a lerakás módja az, hogy meg kell adni egy koordinátát, és az lesz a bal(felső) kockája a hajónak.
        //Meg kell nézni a térképen, hogy arra a koordinátára az adott hajót be lehet-e tenni. Hogy?
        //a kapott koordinátát meg kell vizsgálni, hogy oda tehető-e a hajó.
        // Aztán ugyanezeket meg kell nézni a hajó többi kockájával is.
        // Aztán meg kell nézni, hogy van-e másik hajó a hajó közvetlen szomszédságában (sarkok nem számítanak)
        // ha ezek a feltételek megfelelnek, le kell rakni a hajót. hogyan?
        // a hajó most már valid koordinátáit át kell adni a mapnak, ahol UNDISCOVERED_SHIP-re kell átállítani a BLACK_CLOUDS-okat.
        for (int j = 0; j < numberOfPlayers; j++){
            TextSeparator.format(j+1 + ". játékos rakja le a hajóit!");
            mapList.get(j).mapPrinter();
            switch (numberOfSize){
                case 3:
                    System.out.println("Lerakható hajó fajták:\n" + ShipTypes.FISHING_BOAT.shipType + ":\n 2x [ ] \nFregatt:\n 1x [ ][ ]");
                    break;
                case 4:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 3x [ ] \nFregatt:\n 2x [ ][ ] \nRomboló:\n 1x [ ][ ][ ]");
                    break;
                case 5:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 3x [ ] \nFregatt:\n 2x [ ][ ] \nRomboló:\n 1x [ ][ ][ ] \nCsatahajó:\n 1x [ ][ ] \n    [ ][ ]");
                    break;
                case 6:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 5x [ ] \nFregatt:\n 4x [ ][ ] \nRomboló:\n 2x [ ][ ][ ] \nCsatahajó:\n 1x [ ][ ] \n    [ ][ ]");
                    break;
                case 7:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 5x [ ] \nFregatt:\n 4x [ ][ ] \nRomboló:\n 3x [ ][ ][ ] \nCsatahajó:\n 2x [ ][ ] \n    [ ][ ]");
                    break;
                case 8:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 5x [ ] \nFregatt:\n 4x [ ][ ] \nRomboló:\n 3x [ ][ ][ ] \nCsatahajó:\n 2x [ ][ ] \n    [ ][ ] \nRepülőgép-hordozó:\n 1x [ ][ ][ ] \n    [ ][ ][ ]");
                    break;
                case 9:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 6x [ ] \nFregatt:\n 5x [ ][ ] \nRomboló:\n 4x [ ][ ][ ] \nCsatahajó:\n 3x [ ][ ] \n    [ ][ ] \nRepülőgép-hordozó:\n 2x [ ][ ][ ] \n    [ ][ ][ ]");
                    break;
                case 10:
                    System.out.println("Lerakható hajó fajták:\nHalászhajó:\n 7x [ ] \nFregatt:\n 6x [ ][ ] \nRomboló:\n 5x [ ][ ][ ] \nCsatahajó:\n 4x [ ][ ] \n    [ ][ ] \nRepülőgép-hordozó:\n 3x [ ][ ][ ] \n    [ ][ ][ ]");
                    break;
                default:
                    System.out.println("nem választhatsz ilyen méretű pályát!");
            }

            int rowInput = Menus.rowInputRequestor(numberOfSize); //bekérem a sorok koordinátájának betüjelét (függőleges)
            int cloumnInput = Menus.columnInputRequestor(numberOfSize); //bekérem az oszlopok koordinátájának számát

            System.out.println(rowInput);
            System.out.println(cloumnInput);

            //írja ki hogy mekkora hajókat kell letenni.
            //kérje be inputnak a koordinátákat. a bekérendő koordináták min és max értéke a választott map oldalaihoz igazodjon. pl 10x10-esnél:
        }




    }
}
