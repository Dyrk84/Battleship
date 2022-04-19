package hu.battleship.tools;

import hu.battleship.utils.Colors;

public enum MapMarks {
    MAPFRAME_ROW(Colors.ANSI_BLUE + "~~~" + Colors.ANSI_RESET),
    MAPFRAME_COLUMN(Colors.ANSI_BLUE + "~" + Colors.ANSI_RESET),
    BLACK_CLOUDS(Colors.ANSI_BLACK_BACKGROUND + "[ ]" + Colors.ANSI_RESET),
    UNDISCOVERED_SHIP(Colors.ANSI_BLACK_BACKGROUND + "[ ]" + Colors.ANSI_RESET),
    BLUE_WATER(Colors.ANSI_CYAN_BACKGROUND + "[" + Colors.ANSI_BLUE + "~" + Colors.ANSI_RESET + Colors.ANSI_CYAN_BACKGROUND + "]" + Colors.ANSI_RESET),
    BURNING_SHIP(Colors.ANSI_RED_BACKGROUND + "[" + "S" + Colors.ANSI_RESET + Colors.ANSI_RED_BACKGROUND + "]" + Colors.ANSI_RESET),
    SUNKEN_SHIP(Colors.ANSI_BLUE_BACKGROUND + "[" + Colors.ANSI_BLACK + "X" + Colors.ANSI_RESET + Colors.ANSI_BLUE_BACKGROUND + "]" + Colors.ANSI_RESET);

    public final String markType;

    MapMarks(String markType) { this.markType = markType;}

}

//kellenek:
// fekete mezők, ezek lesznek a még nem felderített pályaterületek. név: BLACK_CLOUDS
// fekete mezők, még fel nem derített hajók a pályán, ugyanúgy néz ki mint a BLACK_CLOUDS, de a neve UNDISCOVERED_SHIP (ezeket rakják le a játékosok a játék elején)
// kék mezők, ezek lesznek a már felderített területek, amiken nincs semmi, csak víz. név: BLUE_WATER
// piros mezők, ezek lesznek az eltalált hajók, amik égnek de még nem süllyedtek el. név: BURNING_SHIP
// sötétkék mezők, ezek lesznek az elsüllyedt hajók. név: SUNKEN_SHIP
