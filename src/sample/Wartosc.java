package sample;

public enum Wartosc {

    Dwa(2), //przypisujemy wartości poszczególnym enumom
    Trzy(3),
    Cztery(4),
    Pięć(5),
    Sześć(6),
    Siedem(7),
    Osiem(8),
    Dziewięć(9),
    Dziesięć(10),
    Walet(10),
    Dama(10),
    Król(10),
    As(11);

    public final int wartosc;

    Wartosc (int wartosc){

        this.wartosc=wartosc;

    }

    String wyswietlWartosc() {
        return ordinal() < 9 ? String.valueOf(wartosc) : name().substring(0, 1);
    }


}
