package hu.battleship.tools;

public class PlacingShipsOnTheMap {
    /*
    1. Hogy kérem be a hajókat? -kell scanner, kérek betüt (abc lista alapján adhatja csak meg az inputot, és a lista indexe alapján fogok hivatkozni) és kérek számot. Ezekkel meg tudom adni, hogy hova rakja a hajót, a hajó bal felső sarka lesz a koordinátán.
    2. Mekkora hajók legyenek? -pályamérethez igazodó hajómennyiségre lenne szükség.
    3x3: 2-1 1-2 (44%)
    4x4 3-1 2-1 1-3 (50%)
    5x5 3-1 2-2 1-3 1-4 (56%)
    6x6 4-1 3-2 2-3 1-4 (55%)
    7x7 5-1 4-2 3-3 2-4 (61%)
    8x8 5-1 4-2 3-3 2-4 1-6 (56%)
    9x9 6-1 5-2 4-3 3-4 2-6 (57%)
    10x10: 7-1 6-2 5-3 4-4 3-6 (62%)
    3. Hogy oldjam meg, hogy a hajókat teljes alakjuk szerint rakja a mapra? - basic game féle lépés enumok lehetnének, bejárná a hajó területének koordinátáit, és ha üres a koordináta, és a közvetlen mellette lévők is(de pályaszéle lehet ott) akkor odarakhatja.
    4. Megoldani, hogy a hajó ne lógjon ki a pályáról, vagy ne lógjon bele másik hajóba

    */

}
