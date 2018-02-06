package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;


public class Main extends Application {

    //tekst informacyjny wyświetlający się na środku
    private Text info_na_srodku = new Text();
    //nowa karta krupiera odstęp 20
    private HBox KartyKrupiera = new HBox(20);
    //nowa karta gracza odstęp 20
    private HBox KartyGracza = new HBox(20);

    //pobieranie kart krupiera do KartyKrupiera
    GraczMaWRece krupier = new GraczMaWRece(KartyKrupiera.getChildren()); //ObservableList<Node> karty
    //pobieranie kart gracza do KartyGracza
    GraczMaWRece gracz = new GraczMaWRece(KartyGracza.getChildren()); //ObservableList<Node> karty

    //definiuje zmienną do przechowywania właściwości początkowa wartosc false
    private SimpleBooleanProperty czygra = new SimpleBooleanProperty(false);

    //tworzymy stackPane() wczytywany do sceny w metodzie void start
    //Scene scene = new Scene(stackPane());
    //primaryStage.setScene(scene);
    private Parent stackPane() {

        //tworzymy stackPane
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(800, 700);

        //tworzymy Skład (Szablon) stackPane jako VBox - dwa prostokąty, górny i dolny
        VBox SkladstackPane = new VBox(0);
        SkladstackPane.setPadding(new Insets(5, 5, 5, 0));
        Rectangle gornyProstokat = new Rectangle(804, 550); //rozmiar gornego prostokąta
        gornyProstokat.setFill(Color.RED); //kolor gornego prostokata
        Rectangle dolnyProstokat = new Rectangle(804, 150); //rozmiar dolnego prostokata
        dolnyProstokat.setFill(Color.BLACK); //kolor dolnego prostokata

        //tworzymy dolny VBOX, składa się z czterech przycisków z tym,że dwa przyciski są w dwaPrzyciskHbox
        VBox dolnyVBox = new VBox(20);
        dolnyVBox.setAlignment(Pos.CENTER);
        Button Rozpocznij = new Button("ROZPOCZNIJ");
        Button Zamknij = new Button("ZAMKNIJ");
        Button DobierzKarte = new Button("DOBIERZ KARTĘ");
        Button NieDobierajKarty = new Button("NIE DOBIERAJ JUŻ KARTY");

        //HBox dwaPrzyciskHboxdolne
        HBox dwaPrzyciskHBox = new HBox(5, DobierzKarte, NieDobierajKarty);
        dwaPrzyciskHBox.setAlignment(Pos.CENTER);

        //dołaczamy do dolnegoVBoxa przycisk Rozpocznij, Zamknij i dwaPrzyciskHBox
        dolnyVBox.getChildren().addAll(Rozpocznij,Zamknij, dwaPrzyciskHBox);

        //tworzymy gornyVBOX, składa się on z krupierWynik, KartyKrupiera, info_na_srodku, KartyGracza i graczWynik
        VBox gornyVBox = new VBox(47);
        gornyVBox.setAlignment(Pos.CENTER);

        Text krupierWynik = new Text("KRUPIER: "); //wyswietla KRUPIER
        Text graczWynik = new Text("GRACZ: ");     //wyswietla GRACZ

        //dołaczamy do górnyVBox krupierWynik, KartyKrupiera(HBox), info_na_srodku, KartyGracza(HBox) i graczWynik
        gornyVBox.getChildren().addAll(krupierWynik,KartyKrupiera, info_na_srodku, KartyGracza,graczWynik);

        //tworzymy stackPane1 składający się z gornyProstokat i gornyVBox
        StackPane stackPane1 = new StackPane(gornyProstokat, gornyVBox);

        //tworzymy stackPane2 składający się z dolnyProstokat i dolnyVBox
        StackPane stackPane2 = new StackPane(dolnyProstokat, dolnyVBox);

        //tworzymy SkladstackPane(Layout) składający się z stackPane1,stackPane2
        SkladstackPane.getChildren().addAll(stackPane1,stackPane2);

        //tworzymy właściwy stackPane składający się z SkladstackPane
        stackPane.getChildren().addAll(SkladstackPane);

        // Powiązanie właściwości - tzw. bindowanie
        Rozpocznij.disableProperty().bind(czygra);
        DobierzKarte.disableProperty().bind(czygra.not());
        NieDobierajKarty.disableProperty().bind(czygra.not());
        //simpleStringProperty
        //wyświetla na bieżaco wyniki dla gracza i krupiera - getWartoscProperty()
        //z naszego pola tekstowego pobieramy textProperty i bindujemy jednokierunkowo (bind)
        graczWynik.textProperty().bind(new SimpleStringProperty("GRACZ: ").concat(gracz.getWartoscProperty().asString()));
        //z naszego pola tekstowego pobieramy textProperty i bindujemy jednokierunkowo (bind)
        krupierWynik.textProperty().bind(new SimpleStringProperty("KRUPIER: ").concat(krupier.getWartoscProperty().asString()));

        //getWartoscProperty() z GraczMaWRece
        //Korzystanie z ChangeListener obserwacja zmian
        //o - obiekt tego properties-a
        //oldVal stara wartość
        //newVal nowa wartość
        gracz.getWartoscProperty().addListener(( o, oldVal,newVal) -> {

            if (newVal.intValue() >= 21) {
                //jesli spełnimy warunek
                KoniecGry();
            }
        });

        //getWartoscProperty() z GraczMaWRece
        //Korzystanie z ChangeListener obserwacja zmian
        krupier.getWartoscProperty().addListener((o, oldVal,newVal) -> {
            if (newVal.intValue() >= 21) {
                //jesli spełnimy warunek
                KoniecGry();
            }
        });

        // Akcje przycisków

        //zamknij grę
        Zamknij.setOnAction(event -> {
            Platform.exit();
        });

        //rozpocznij grę
        Rozpocznij.setOnAction(event -> {
            RozpocznijGre();
        });
        //dobierz kartę
        DobierzKarte.setOnAction(event -> {
            gracz.PobierzKarte(talia.WezKarte());
        });
        //nie dobieraj karty - getWartoscProperty() z GraczMaWRece - strategia krupiera <17
        NieDobierajKarty.setOnAction(event -> {
            while (krupier.getWartoscProperty().get() < 17) {
                krupier.PobierzKarte(talia.WezKarte());
            }
            //koniec gry sprawdzenie.
            KoniecGry();
        });

        //zwracamy stackPane
        return stackPane;
    }

    private Talia talia = new Talia();


    // ROZPOCZNIJ GRĘ
    private void RozpocznijGre() {
        czygra.set(true);
        info_na_srodku.setText("TO BLACKJACK DOBIERZ LUB NIE BĄDŹ CZUJNY W GRZE !!!");

        //tasujemy talie
        talia.tasuj();
        //pusto na ręku wartośc zero i asów zero
        krupier.czysc();
        //pusto na ręku wartośc zero i asów zero
        gracz.czysc();
        // pobieramy poczatkowe dwie karty dla krupiera
        krupier.PobierzKarte(talia.WezKarte());
        krupier.PobierzKarte(talia.WezKarte());
        // pobieramy poczatkowe dwie karty dla gracza
        gracz.PobierzKarte(talia.WezKarte());
        gracz.PobierzKarte(talia.WezKarte());
    }

    //KONIEC GRY
    private void KoniecGry() {
        czygra.set(false);
        //getWartoscProperty() z GraczMaWRece
        int KrupierWynik = krupier.getWartoscProperty().get();
        int graczWynik = gracz.getWartoscProperty().get();
        String zwyciesca = " " + KrupierWynik + " " + graczWynik;


        // sprawdzamy - logika gry
        if (KrupierWynik == 21 || graczWynik > 21 || KrupierWynik == graczWynik
                || (KrupierWynik < 21 && KrupierWynik > graczWynik)) {
            zwyciesca = "NIESTETY KRUPIER";
        }
        else if (graczWynik == 21 || KrupierWynik > 21 || graczWynik > KrupierWynik) {
            zwyciesca = "DOBRZE GRACZ";
        }

        info_na_srodku.setText(zwyciesca + " WYGRAŁ!!!");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scene scene = new Scene(stackPane());
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Gra Black Jack");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
