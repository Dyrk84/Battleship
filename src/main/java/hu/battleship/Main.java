package hu.battleship;

import hu.battleship.tools.Map;
import hu.battleship.tools.Menus;
import hu.battleship.utils.ASCIIArtService;
import hu.battleship.utils.MapMarks;

public class Main {


    public static void main(String[] args) {
        ASCIIArtService.textPrinter();
        Menus.welcome();
    }
}