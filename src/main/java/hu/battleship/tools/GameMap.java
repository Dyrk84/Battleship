package hu.battleship.tools;

import lombok.Getter;

import java.util.List;

public class GameMap {

    //1. Kell tudnom, hogy mekkora a térkép. ezt később be lehet kérni felhasználótól mekkorát szeretne Ideális legnagyobb méret: 26x26-os (mivel 26 betü van), legkisebb méret: 5x5
    //2. Ha már tudom mekkora a térkép, hozzá kell adnom 1-et magasságra és szélességre, hogy kiférjen a számozás és betüzés
    //3. Létre kell hoznom egy multitömböt, aminek a row és column tényezői most 5 + 1
    //4. Meg kell oldanom, hogy a felső sor (row == 0) helyén betük legyenek.
    //4.1 Kell egy lista, amiben az angol abc betüi szerepelnek, és ahány oszlopot szeretnénk a játékba, annyi betüt vegyen ki ebből a listából, és írjon ki a felső sorba
    //5. A mezők legyenek 3 karakter szélesek és 1 karakter magasak. A mezők így nézzenek ki (csak szinesben): [ ]

    public static String[][] mapCreator(String[][] oceanMap) { //ezzel töltöm fel értékekkel a multitömböt az elején (még nem kerültek fel a hajók)
        List<String> abc = InputAndListHandlers.createABCList(); //csinál egy abc listát asciiból //TODO ezt átgondolni, lehet nem ide kellene
        for (int row = 0; row < oceanMap.length; row++) {
            for (int column = 0; column < oceanMap[row].length; column++) { //végigmegyek az oszlopokon
                if (row == 0 && column == 0) {
                    oceanMap[row][column] = "  "; //ezzel oldom meg, hogy [0][0]-nál ne legyen semmi
                } else if (column == 0) {
                    oceanMap[row][column] = abc.get(row - 1) + " "; //az előző ifnél a row 0. helye üres így itt már a row értéke 1, ami az abc listában már a B lenne, ezért kell egyet kivonni, hogy A-val kezdje
                } else if (row == 0 && column < 10) {
                    oceanMap[row][column] = " " + column + " "; // ezzel iratom a három mező közepére az egyszámjegyű számokat
                } else if (row == 0 && column >= 10) {
                    oceanMap[row][column] = " " + Integer.toString(column); //ezzel iratom a három mező közepére a kétszámjegyű számok első számjegyét
                } else if (oceanMap[row][column] == null) {
                    oceanMap[row][column] = MapMarks.BLUE_WATER.markType; //értéket adok az adott sor és oszlop által jelzett String változónak
                }
            }
        }
        return oceanMap;
    }

    public static void mapPrinter(String[][] oceanMap) {
        for (int row = 0; row < oceanMap.length; row++) { //szintén végigmegyek a sorokon
            for (int column = 0; column < oceanMap[row].length; column++) { //az adott sornak végigmegyek az oszlopain
                if (row == 0 && column == 0) {
                    System.out.print(oceanMap[row][column]);
                } else {
                    System.out.print(oceanMap[row][column]); // az előzőek mintájára kiíratom a consolra az adott értéket. Figyelem! a print nem töri meg a sort! így egymás mellé ír!
                }
            }
            System.out.println(); //a println-al töröm meg a sorokat. Itt amint végigmegy az oszlopokon, akkor töri!
        }
    }
}
