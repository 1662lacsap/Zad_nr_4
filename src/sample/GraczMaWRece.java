package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList; //Lista, która pozwala śledzić zmiany w momencie ich wystąpienia.
import javafx.scene.Node;  //klasa podstawowa dla węzłów wykresu sceny. Struktura drzewa.

public class GraczMaWRece {


    //definiuje zmienną do przechowywania właściwości
    private SimpleIntegerProperty wartosc = new SimpleIntegerProperty(0);

    //zwraca wartość właściwości - getter
    public  SimpleIntegerProperty getWartoscProperty() {
        return wartosc;
    }

    //ustawia wartość właściwości - setter
    public void setWartoscProperty(int wartosc) {
        this.wartosc.set(wartosc);
    }

    //zerowanie wartości, gdy startujemy grę
    public void czysc() {
        karty.clear();
        setWartoscProperty(0);
        as = 0;
    }

    private int as = 0;

    //pobieramy kartę i dodajemy do reki gracza
    public void PobierzKarte(Karta karta) {
        karty.add(karta);

        //liczymy wartość na ręcę - As liczony w zalezności, czy przekroczymy 21 czy nie
        if (karta.wartosc == Wartosc.As) {
            as++;
        }
        //AS liczony jako 1
        if (wartosc.get() + karta.wartoscKarty > 21 && as > 0) {

            //ustawia wartość właściwości
            setWartoscProperty(wartosc.get() + karta.wartoscKarty - 10);
            as--;
        }
        //w przeciwnym razie
        else {

            //ustawia wartość właściwości
            setWartoscProperty(wartosc.get() + karta.wartoscKarty);
        }
    }

    //ObservableList < Node >
    private ObservableList<Node> karty;

    //ile kart ma na ręce gracz
    public GraczMaWRece(ObservableList<Node> karty) {
        this.karty = karty;
    }

}
