package hu.battleship.tools;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputAndListHandlers {

    public static int askForInt(List<Integer> validNumbers) { //a paraméterben lévő lista tartalma adja meg, hogy mi a valid Integer tartomány.
        while (true) { //addig fut a loop, amíg nem fut hibába (vagyis amíg nem azt írják be, amit szeretnénk)
            try {
                Scanner reader = new Scanner(System.in);
                System.out.print("Válassz egy számot az alábbiak közül " + validNumbers + ":"); //eng: System.out.print("Please provide a number " + validNumbers + ":");
                System.out.println();
                int inputInteger = reader.nextInt();
                if (validNumbers.contains(inputInteger)) { //ez az if megnézi, a validOperators listában megtalálható-e az inputban megadott érték.
                    return inputInteger;
                } else {
                    System.out.println("Ilyen szám nincs a választható számok között!"); //eng: System.out.println("Invalid number!");
                }
            } catch (InputMismatchException exceptionAskForInt) {
                System.out.println("exception in askForInt(): " + exceptionAskForInt); //ez nem tudom mikor tudna felugrani...
            }
        }
    }

    public static List numListCreator(int numberOfSize) { //Célja: legyen egy listánk 1-től numberOfSize-ig
        List<Integer> numList = new ArrayList<>();
        for (int i = 1; i <= numberOfSize; i++){
            numList.add(i);
        }
        return numList;
    }

    public static List<String> createABCList() {
        List<String> ascii = new ArrayList<>(26);
        for (char c = 'A'; c <= 'Z'; c++)
            ascii.add(String.valueOf(c));
        return ascii;
    }

    public static List<String> validLettersListCreator(int numberOfSize, List<String> abcList){
        List<String> validLettersList = new ArrayList<>();
        for (int i = 0; i < numberOfSize; i++){
            validLettersList.add(abcList.get(i)); //rakd bele a validLettersList-be az abcList-ben lévő betüt. Az i a térkép méretéhez van igazítva.
        }
        return validLettersList;
    }

    public static String askForLetter(List<String> validLettersList) {
        while (true) { //addig fut a loop, amíg nem fut hibába (vagyis amíg nem azt írják be, amit szeretnénk)
            try {
                Scanner reader = new Scanner(System.in);
                System.out.print("Válassz egy betüt az alábbiak közül " + validLettersList + ":");
                System.out.println();
                String inputLetter = reader.nextLine();
                if (validLettersList.contains(inputLetter)) { //ez az if megnézi, a listában megtalálható-e az inputban megadott érték.
                    return inputLetter;
                } else {
                    System.out.println("Ilyen betű nincs a választhatóak között!");
                }
            } catch (InputMismatchException exceptionAskForLetter) {
                System.out.println("exception in askForLetter: " + exceptionAskForLetter); //ez nem tudom mikor tudna felugrani, hiszen jól behatároltam, hogy milyen betüket fogadhat el...
            }
        }
    }
}
