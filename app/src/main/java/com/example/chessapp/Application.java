package com.example.chessapp;

import android.content.Context;
import android.util.Log;

import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.DBResourceManager;
import com.example.chessapp.storage.PlaceTable;
import com.example.chessapp.storage.PuzzlesTable;
import com.example.chessapp.storage.UserStateTable;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzle;
import com.example.chessapp.storage.model.UserState;

import java.util.Arrays;
import java.util.Objects;

public class Application {
    public static PlaceTable placeTable = null;
    public static PuzzlesTable puzzlesTable = null;
    public static UserStateTable stateTable = null;
    public static final String DB_NAME = "db2202";

    public static final int DISTANCE_METERS = 1000;
    public static final int DISTANCE_TO_ADD_TO_VISITED_METES = 100;

    public static final int DISTANCE_TO_PLACE_NEAR_METERS = 200;
    public static final int DISTANCE_TO_PLACE_MIDDLE_METERS = 700;
    public static final int DISTANCE_SET_AS_VISITED_BY_RADIUS_METERS = 10000;


    public static UserState getCurrentUserState(){
        return stateTable.all().get(0);
    }


    // todo: if db not exists -> recreate()
    public static void init(Context context, boolean clearAndFill, boolean recreate){
        puzzlesTable = new PuzzlesTable(context, DB_NAME);
        placeTable = new PlaceTable(context, DB_NAME);
        stateTable = new UserStateTable(context, DB_NAME);

        if(recreate){
            puzzlesTable.recreate();
            placeTable.recreate();
            stateTable.recreate();
        }
        if(clearAndFill){
            puzzlesTable.clear();
            placeTable.clear();
            stateTable.clear();
        }
        fillDB();
    }


    private static void fillDB(){
        if(puzzlesTable.all().isEmpty()){
            Puzzle[] arr = new Puzzle[]
                    {
                            new Puzzle("puzzle6", "solution1", null,  "N - N", "Game info 1", true, "aufgabe2.jpeg"),
                            new Puzzle("puzzle1", "solution1", null,  AppHelpers.createPlayerText("Ivanov", "Petrov", 2010), "Game info 2", true, "aufgabe3.jpeg"),
                            new Puzzle("puzzle2", "solution2", null, AppHelpers.createPlayerText("Vasilenko", "Petrov", 2012) , "Game info 3", true, "im2.jpeg"),
                            new Puzzle("puzzle3", "solution3", "Garching 1980 e.V.", AppHelpers.createPlayerText("Kosparov", "Petrov", 3010) , "Game info 4",  true, "bonus1.jpeg"),
                            new Puzzle("Etude 1", "solution4", "Gräfelfing", AppHelpers.createPlayerText("Ivanov", "Aleksandrovskiy", 2012) , "Game info 5", false, "bonus2.jpeg"),
                            new Puzzle("Etude 10", "solution4", "Gräfelfing", AppHelpers.createPlayerText("Ivanov", "Robin", 2011) , "Game info 6",  false, "im4.jpeg"),
                            new Puzzle("Etude 12", "solution4", "Garching 1980 e.V.", AppHelpers.createPlayerText("Batman", "Petrov", 2000) ,  "Game info 7", false, "im4.jpeg"),
                    };

            Arrays.stream(arr).forEach(puzzlesTable::push);

        }
        if(placeTable.all().isEmpty()){

            String dummyInfoText = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec";

            Place[] arr = new Place[]
                    {
                            new Place("Gräfelfing","Lochhamer Str. 79, 82166 Gräfelfing",dummyInfoText,"http://sk-graefelfing.net/",11.429469356249529,48.135776711629575,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("SC Sendling e.V.","Garmischer Str. 209, 81377 München",dummyInfoText,"http://www.scsendling.de/",11.518245523204255,48.123082924815165,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Garching 1980 e.V.","Dieselstraße 9, 85748 Garching bei München",dummyInfoText,"http://schachclub-garching.de/",11.626499001544795,48.25512060605097,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("MSA Zugzwang 82 e.V.","Tulbeckstraße 31, 80339 München",dummyInfoText,"http://zugzwang-muenchen.de/",11.538454959810988,48.13887902230834,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Roter Turm Altstadt München","Arcisstraße 45, 80799 München",dummyInfoText,"http://www.scroterturm.de/",11.571423821042076,48.15482428522702,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Münchener Schachclub 1836 e. V.","Säbener Str. 49, 81547 München",dummyInfoText,"https://www.msc1836.de/",11.573359019955397,48.10365904910711,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Schachklub Tarrasch München","Kolumbusstraße 33, 81543 München",dummyInfoText,"http://www.tarrasch-muenchen.de/",11.57697293298894,48.12014245471684,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Unterhaching","Oskar-von-Miller-Straße 9, 82008 Unterhaching",dummyInfoText,"http://www.sc-unterhaching.de/",11.608330571345775,48.0758997885917,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Vaterstetten-Grasbrunn","Saarlandstraße 10, 85630 Grasbrunn",dummyInfoText,"http://www.scv-jugend.de/",11.758684588290174,48.09865499311554,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("FC Bayern München","Anton-Fingerle-Zentrum, Schlierseestr. 47, München",dummyInfoText,"https://fcbayern.com/schach/de",11.593774149840849,48.113311418484045,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("SF Dachau 1932","Adolf-Hälzel-Haus am Ernst-Reuter-Platz, Dachau",dummyInfoText,"https://sfdachau.de/",11.458966397086122,48.260095337911416,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("SC Eching","Bürgerhaus Eching, Roßbergerstr. 4, 85386 Eching",dummyInfoText,"http://www.sceching.de/schach/",11.622468303222288,48.297882024815266,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("SF Deisenhofen","Evang. Gemeindehaus, Alpenstr. 16, 82041 Oberhaching",dummyInfoText,"http://www.schachfreunde-deisenhofen.de/",11.590259603822975,48.024679573629214,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("SC Kirchseeon","An der Brücke 4, 85614 Kirchseeon",dummyInfoText,"https://cristilungul.com/",11.892015326525712,48.06879566875555,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("SC Haar 1931","Kirchenplatz 1, 85540 Haar",dummyInfoText,"http://www.sc-haar1931.de/index.php?title=Hauptseite",11.725855067938046,48.10902619066947,Place.CATEGORY_CLUB, "logo1.jpeg", false),
                            new Place("Heiterwanger Schachplatz","Heiterwanger Str./Fernpaßstraße",dummyInfoText,"http://www.spielplaetze-in-muenchen.de/cgi-bin/details.pl?n=Heiterwanger%20Schachplatz",11.725855067938046,48.121625080099726,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Münchner Freiheit","Münchner Freiheit 16, 80802 München",dummyInfoText,"https://www.abendzeitung-muenchen.de/muenchen/spiel-ohne-damen-az-besuch-am-schachplatz-an-der-muenchner-freiheit-art-723575", 11.587298310743575,48.16301147601446,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Weissenseepark","Ecke Sintperstr. / Weissenseestr.",dummyInfoText,"http://weissenseepark.de/staendige-angebote-im-park/",11.5881588926881,48.106791863439554,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Outdoor Schach und Mühle","Stässerstraße 6, 80933 München",dummyInfoText,"",11.557877499252461,48.221162847281924,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Freiluftschach","Ecke Bavariaring /Ecke Beethovenstr.",dummyInfoText,"",11.55323915940155,48.131847453571,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Freiluftschach","Hans-Mielich-Platz, 81543 München",dummyInfoText,"",11.571011220827694,48.11677447162393,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Freiluftschach","Ziemssenstr.",dummyInfoText,"",11.562848334895179,48.13237162693798,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Freiluftschach","Piusplatz",dummyInfoText,"",11.613494515714878,48.12266929209225,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Freiluftschach","Ostpark",dummyInfoText,"",11.642996103277037,48.11242051134809,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Freiluftschach","Pappelallee, 80995 München",dummyInfoText,"",11.512682516863702,48.20063134340086,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Bodenschach","Bad-Schachener-Straße 28, 81671 München", dummyInfoText,"",11.61739184609688,48.12027762259076,Place.CATEGORY_OUTDOOR, "logo1.jpeg", false),
                            new Place("Mehlfeld's","Guardinistr. 98a, 81375 München",dummyInfoText,"https://www.mehlfeld.com/",11.490581831820519,48.11949476046334,Place.CATEGORY_CAFE, "logo1.jpeg", false),
                            new Place("Cafe am Beethovenplatz","Goethestraße 51, München",dummyInfoText,"https://discover.events.com/de/bavaria/munich/e/business/weekly-chess-evening-cafe-30092021-cafe-beethovenplatz-422064929",11.558762946418963,48.13334696875066,Place.CATEGORY_CAFE, "logo1.jpeg", false),
                           new Place("Schelling-Salon","Schellingstraße 54, 80799 München",dummyInfoText,"https://www.schelling-salon.de/spiel-sport.html",11.5731287725254,48.1511776279703,Place.CATEGORY_CAFE, "logo1.jpeg", false),
        };

            Arrays.stream(arr).forEach(placeTable::push);

        }

        if(stateTable.all().isEmpty()){
            UserState state = new UserState(false, "1234", "admin");
            stateTable.push(state);
        }
    }

    public static void printAllTables(){
        Log.i("DB", "-----------------------------------");
        printTable(placeTable);
        Log.i("DB", "-----------------------------------");
        printTable(puzzlesTable);
        Log.i("DB", "-----------------------------------");
        printTable(stateTable);
        Log.i("DB", "-----------------------------------");
    }

    public static void printTable(DBResourceManager<?> table){
        Log.i("DB", "Table Name :" +  table.getTableName());
        Log.i("DB", "Size:" +  table.all().size());

        table.all().forEach(it -> Log.i("DB", it.toString()));
    }

}
