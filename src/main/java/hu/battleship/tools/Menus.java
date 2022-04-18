package hu.battleship.tools;

import hu.battleship.utils.TextSeparator;

import java.util.Arrays;
import java.util.List;

public class Menus {

    public static void welcome() {
        List<Integer> validNumbers = Arrays.asList(1, 2, 3); //ezzel adom meg, hogy hány milyen inputokat szeretnék elfogadni
        printWelcomeMenu(); //ezzel irattatom ki a menühöz tartozó szöveget
        int chosenNumber = InputAndListHandlers.askForInt(validNumbers); //ezzel kérem be az inputot
        switch (chosenNumber) {
            case 1:
                gameRules();
                break;
            case 2:
                GameCode.gameStart();
                break;
            case 3:
                exit();
                break;
            default:
                System.out.println("Error with the switch in welcome(), used the default branch!");
        }
    }

    private static void gameRules(){
        TextSeparator.format("Játékszabályok");
        TextSeparator.formatSides("Ide jöhet a játékszabály leírása");
        TextSeparator.format("");
    }

    private static void exit() {
        System.out.println("Ha hajókra szeretnél lövöldözni, gyere vissza!");
        System.out.println();
        System.out.println("Készítette: Diriczi Dávid");
        System.exit(1);
    }

    private static void printWelcomeMenu(){
        TextSeparator.format("Üdvözöllek a Torpedó játékban!");
        TextSeparator.formatSides("Kérlek válassz az alábbi menüpontok közül:");
        TextSeparator.formatSides("1. Játékszabály");
        TextSeparator.formatSides("2. Kezdjük a játékot!");
        TextSeparator.formatSides("3. Ki szeretnék lépni a programból!");
        TextSeparator.format("");
    }

    public static int numberOfPlayersMenu() {
        List<Integer> validNumbers = Arrays.asList(2,3,4); //ezzel adom meg, hogy milyen inputokat szeretnék elfogadni
        printNumberOfPlayersMenu(); //ezzel irattatom ki a menühöz tartozó szöveget
        int chosenNumber = InputAndListHandlers.askForInt(validNumbers); //ezzel kérem be az inputot
        return chosenNumber;
    }

    private static void printNumberOfPlayersMenu() {
        TextSeparator.format("");
        TextSeparator.formatSides("Hány játékos legyen a játékban?");
        TextSeparator.format("");
    }

    public static int numberOfSize() {
        List<Integer> validNumbers = Arrays.asList(3,4,5,6,7,8,9,10); //ezzel adom meg, hogy milyen inputokat szeretnék elfogadni
        printNumberOfSizeMenu(); //ezzel irattatom ki a menühöz tartozó szöveget
        int chosenNumber = InputAndListHandlers.askForInt(validNumbers); //ezzel kérem be az inputot
        return chosenNumber;
    }

    private static void printNumberOfSizeMenu() {
        TextSeparator.format("");
        TextSeparator.formatSides("Mekkora mérete legyen a térképeknek?");
        TextSeparator.format("");
    }

    public static int rowInputRequester(int numberOfSize) { //bekér egy betüt, megnézi, megnézi van-e a térképhez ilyen koordináta
        List<String> abcList = InputAndListHandlers.createABCList(); //visszaad egy betü listát A-Z-ig
        List<String> validLettersList = InputAndListHandlers.validLettersListCreator(numberOfSize, abcList); //abc listát csinál, annyi betüvel, amekkora kell a választott pályamérethez
        printRowInputRequester();
        String rowCoordinateInString = InputAndListHandlers.askForLetter(validLettersList); //Ez kéri be a koordináta betüjét
        int rowCoordinateInInt =  validLettersList.indexOf(rowCoordinateInString); //beépített String metódus, visszaadja a zárójelben lévő Stringnek a helyét a listában.
        return rowCoordinateInInt + 2; // hozzáadok 1-et, mert a térképen a 0 sorban a betük vannak
    }

    private static void printRowInputRequester() {
        TextSeparator.format("");
        TextSeparator.formatSides("Add meg, hogy melyik betüjelű sort szeretnéd kiválasztani:");
        TextSeparator.format("");
    }

    public static int columnInputRequester(int numberOfSize) {
        List<Integer> validNumbers = InputAndListHandlers.numListCreator(numberOfSize); //visszaad egy listát 1-től numberOfSize-ig
        printColumnInputRequester();
        int columnCoordinate = InputAndListHandlers.askForInt(validNumbers);
        return columnCoordinate + 1;
    }

    private static void printColumnInputRequester() {
        TextSeparator.format("");
        TextSeparator.formatSides("Add meg, hogy melyik számjegyű oszlopot szeretnéd kiválasztani:");
        TextSeparator.format("");
    }
}
