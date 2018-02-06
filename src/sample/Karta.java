package sample;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Karta extends Parent{ //Parent - Klasa podstawowa dla wszystkich węzłów, które mają dzieci na wykresie sceny.


    private int Karta_szerokosc = 100;
    private int Karta_wysokosc = 140;


    public Kolor kolor;
    public Wartosc wartosc;
    public int wartoscKarty;

    //Wygląd karty
    public Karta(Kolor kolor, Wartosc wartosc) { //z Kolor i Wartosc enumów
        this.kolor = kolor;
        this.wartosc = wartosc;
        this.wartoscKarty = wartosc.wartosc;


        Rectangle wygladKarty = new Rectangle(Karta_szerokosc, Karta_wysokosc);
        wygladKarty.setFill(Color.BLACK); //tło karty


        Text tekst1 = new Text(wartosc.wyswietlWartosc()); //z enum Wartosc wyswietlWartosc()
        tekst1.setFill(Color.WHITE); tekst1.setFont(Font.font(30)); //czcionka na karcie prawy górny róg
        tekst1.setX(Karta_szerokosc- tekst1.getLayoutBounds().getWidth() - 10);
        tekst1.setY(tekst1.getLayoutBounds().getHeight());

        Text tekst2 = new Text(tekst1.getText());
        tekst2.setFont(Font.font(30)); //czcionka na karcie lewy dolny róg
        tekst2.setFill(Color.WHITE);
        tekst2.setX(10);
        tekst2.setY(Karta_wysokosc- 10);

        ImageView widok = new ImageView(kolor.obraz);
        widok.setRotate(180);//położenie obrazka na karcie
        widok.setX(Karta_szerokosc - 42);
        widok.setY(Karta_wysokosc- 42);

        //dołaczamy wygląd karty, obraz, widok, tekst1 i tekst2
        getChildren().addAll(wygladKarty, new ImageView(kolor.obraz), widok, tekst1, tekst2);

    }

}