package sample;

import javafx.scene.image.Image;

public enum Kolor {

    Kier,  //java automatycznie numeruje wartość enuma, jeśli nie ma (0,1,2,3)
    Karo,
    Trefl,
    Pik;

    final Image obraz;

    Kolor(){


        {

            this.obraz = new Image(this.getClass().getClassLoader().getResourceAsStream("obrazkiKarty/".concat(name().toLowerCase()).concat(".png")),

                    42, 42, true, true);
        }


    }


}
