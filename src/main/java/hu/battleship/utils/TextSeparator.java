package hu.battleship.utils;

import java.util.ArrayList;
import java.util.List;

public class TextSeparator {
    private static final String ANSI_REGEX = "\\x1b\\[[0-9;]+m";  //Ez egy regex, minden szín szövegre ráillik. Bővebben: Megnézni: https://www.youtube.com/watch?v=zamFTizZAuw&list=PL1WwhU4dv6tG8C8aGca5IxZliVTjvtweh&index=61

    public static void formatSides(String text) { //ezt hívom meg, ha a menü szövegét írom
        if (text.length() != 0)
            text = " " + text + " ";
        int textLength = colorRemoveTextLength(text) + 2;
        int starsNumber;
        int lineLength = 100;
        int lineFirstHalf = ((lineLength - textLength) / 2) + (textLength % 2);
        int lineLastHalf = ((lineLength - textLength) / 2);

        System.out.print("*");
        // first stars line
        for (starsNumber = 1; starsNumber < lineFirstHalf; starsNumber++) {
            System.out.print(" ");
        }
        // text on middle of the line
        System.out.print(text);
        // last stars line
        for (starsNumber = 0; starsNumber < lineLastHalf; starsNumber++) {
            System.out.print(" ");
        }
        // the line ending star
        System.out.println("*");
    }

    public static void format(String text) { //ezt hívom meg, ha a menü felső és alsó vonalát akarom meghívni.
        if (text.length() != 0)
            text = " " + text + " ";
        int textLength = colorRemoveTextLength(text) + 2;
        int starsNumber;
        int lineLength = 100;
        int lineFirstHalf = ((lineLength - textLength) / 2) + (textLength % 2);
        int lineLastHalf = ((lineLength - textLength) / 2);

        // first stars line
        for (starsNumber = 0; starsNumber < lineFirstHalf; starsNumber++) {
            System.out.print("*");
        }
        // text on middle of the line
        System.out.print(text);
        // last stars line
        for (starsNumber = 0; starsNumber < lineLastHalf; starsNumber++) {
            System.out.print("*");
        }
        // the line ending star
        System.out.println("*");
    }

    private static int colorRemoveTextLength(String colorText) {

        List<String> colorList = new ArrayList<>();
        colorList.add(Colors.ANSI_RED);
        colorList.add(Colors.ANSI_BLACK);
        colorList.add(Colors.ANSI_BLUE);
        colorList.add(Colors.ANSI_PURPLE);
        colorList.add(Colors.ANSI_WHITE);
        colorList.add(Colors.ANSI_CYAN);
        colorList.add(Colors.ANSI_GREEN);
        colorList.add(Colors.ANSI_YELLOW);
        colorList.add(Colors.ANSI_RESET);
        colorList.add(Colors.ANSI_BLACK_BACKGROUND);
        colorList.add(Colors.ANSI_CYAN_BACKGROUND);
        colorList.add(Colors.ANSI_BLUE_BACKGROUND);
        colorList.add(Colors.ANSI_RED_BACKGROUND);
        colorList.add(Colors.ANSI_GREEN_BACKGROUND);
        colorList.add(Colors.ANSI_PURPLE_BACKGROUND);
        colorList.add(Colors.ANSI_WHITE_BACKGROUND);
        colorList.add(Colors.ANSI_YELLOW_BACKGROUND);

        //regexes megoldás
        return colorText.replaceAll(ANSI_REGEX, "").length();
    }
}
