package sample;

import java.util.ArrayList;
import java.util.Collections;

public class Talia { //reprezentuję talię kart w grze, lista zawierająca wszystkie obiekty klasy Karta

    private ArrayList<Karta> karty; //lista przechowująca karty w grze


    public Talia() {

        karty = new ArrayList<>(); //wykorzystujemy arraylistę (mozna tablicę)

        // tworzę talię 52 karty
        for( int kolor = 0; kolor < 4; kolor++){ //4 razy uruchomi się kolor karty

            for (int wartosc = 0; wartosc < 13; wartosc++) //uruchomi się 13 razy - reprezentuje wartość karty
            {

                karty.add(new Karta(Kolor.values() [kolor],Wartosc.values()[wartosc])); //dodajemy nowy obiekt do listy karty,
                // uruchamiamy obiekt klasy Karta - wydobywamy kolory z Kolor i wartości z Wartosc
            }

        }
    }
    //tasuje karty z naszej listy karty
    public void tasuj() {

        Collections.shuffle(karty); //klasa Arraylist ma metodę do tasowania listy karty

    }

    //pobiera jedną kartę i usuwa z Talii
    public Karta WezKarte() {

        return karty.remove(0); //bierze pierwszą kartę i usuwa z listy
    }

}