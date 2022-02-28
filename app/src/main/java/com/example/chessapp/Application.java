package com.example.chessapp;

import android.content.Context;
import android.util.Log;

import com.example.chessapp.helpers.AppHelpers;
import com.example.chessapp.storage.DBResourceManager;
import com.example.chessapp.storage.PersonTable;
import com.example.chessapp.storage.PlaceTable;
import com.example.chessapp.storage.PuzzlesTable;
import com.example.chessapp.storage.UserStateTable;
import com.example.chessapp.storage.model.Person;
import com.example.chessapp.storage.model.Place;
import com.example.chessapp.storage.model.Puzzle;
import com.example.chessapp.storage.model.UserState;

import java.util.Arrays;
import java.util.Objects;

public class Application {
    public static PlaceTable placeTable = null;
    public static PuzzlesTable puzzlesTable = null;
    public static UserStateTable stateTable = null;
    public static PersonTable personTable = null;
    public static final String DB_NAME = "db2203";

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
        personTable = new PersonTable(context, DB_NAME);

        if(recreate){
            puzzlesTable.recreate();
            placeTable.recreate();
            stateTable.recreate();
            personTable.recreate();
        }
        if(clearAndFill){
            puzzlesTable.clear();
            placeTable.clear();
            stateTable.clear();
            personTable.clear();
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
                            new Place( "Münchener Schachclub 1836 e. V."     ,      "Säbener Str. 49, 81547 München"                       ,      "Firouzja, Alireza ;2804 ; GM \n Eljanov, Pavel ;2683 ; GM \n Saric, Ivan ;2678 ; GM \n Jones, Gawain ;2671 ; GM\n Indjic, Aleksandar ;2604 ; GM"            ,  "https://www.msc1836.de/"                                                                                                                     ,            11.573733582006806                         ,                     48.10251685748074                     ,                 "club"                   ,                      "01_Munchner1836.jpg"              ,         false,    102 ),
                            new Place( "FC Bayern München"                   ,      "Schlierseestr. 47, 81539 München"                     ,      "Santos Latasa,Jaime ; 2640 ; GM \nHuschenbeth,Niclas ; 2611 ; GM \nSantos Ruiz,Miguel ; 2596 ; GM \nStuder,Noel ; 2582 ; GM \nBogner,Sebastian ; 2573 ; GM" ,  "https://fcbayern.com/schach/de"                                                                                                              ,           11.593774149840849                          ,                   48.113311418484045                      ,                 "club"                   ,                      "02_FCBayernMunchen.jpg"           ,         false,    273 ),
                            new Place( "SC Garching 1980 e.V."               ,      "Bürgerplatz 9, 85748 Garching"                        ,      "Stocek,Jiri ; 2556 ; GM \nKrivoborodov,Egor ; 2515 ; GM \nHess,Max ; 2395 ; FM \nLoetscher,Roland ; 2387 ; IM \nKoepke,Christian ; 2330 ; IM"               ,  "http://schachclub-garching.de/"                                                                                                              ,           11.650348189132727                          ,                    48.25039043234126                      ,                 "club"                   ,                      "03_Garching.jpg"                  ,         false,    223 ),
                            new Place( "MSA Zugzwang 82 e.V."                ,      "Tulbeckstraße 31, 80339 München"                      ,      "Short,Nigel ; 2620; GM \nMons,Leon ; 2537; GM \nShengelia,David ; 2503; GM \nHertneck,Gerald ; 2486; GM \nBromberger,Stefan ; 2483; GM"                     ,  "http://zugzwang-muenchen.de/"                                                                                                                ,           11.53888777055278                           ,                    48.13681488998318                      ,                 "club"                   ,                      "04_Zugzwang.jpg"                  ,         false,    119 ),
                            new Place( "SC  Tarrasch 1945 München e.V."      ,      "Kolumbusstraße 33, 81543 München"                     ,      "Maksimenko,AnDr.ei ; 2434 ; GM\nDas,Soham ;2398; \nLipinsky,Fabian ; 2351 ;IM \nJuptner,Jan ; 2266 ;FM \nMeier,Volker ;2254 ;FM"                            ,  "http://www.tarrasch-muenchen.de/"                                                                                                            ,           11.577138054104843                          ,                    48.1194364776457                       ,                 "club"                   ,                      "05_Tarrasch.jpg"                  ,         false,    139 ),
                            new Place( "SC Vaterstetten-Grasbrunn"           ,      "Saarlandstraße 10, 85630 Grasbrunn"                   ,      "Goldbeck,Lars ; 2259 ; FM \nXiong,Ferdinand ; 2217 ; \nSchackmann,Oliver ; 2172 ; \nThiele,Fabian ; 2154; \nNitsche-Hahn,Raphael; 2096 ;"                   ,  "http://www.scv-jugend.de/"                                                                                                                   ,           11.758975490605593                          ,                    48.096737901599475                     ,                 "club"                   ,                      "06_Vaterstetten.jpg"              ,         false,    144 ),
                            new Place( "SC Sendling e.V."                    ,      "Garmischer Str. 209, 81377 München"                   ,      "Kuenzner,Franz ;2306; FM \nPitschka,Claus ;2197; FM \nUnger,Michael ;2133 ; \nAwenius,AnDr.eas ; 2132; \nWatzenberger,Stefan; 2031;"                        ,  "http://www.scsendling.de/"                                                                                                                   ,           11.518151733461808                          ,                    48.12009380000876                      ,                 "club"                   ,                      "07_Sendling.jpg"                  ,         false,    71) ,
                            new Place( "SC Haar 1931"                        ,      "Kirchenplatz 1, 85540 Haar"                           ,      "Moufang,Rainer,Dr. ; 2266 ; \nWaelzel,Helmut ; 2247 ; \nLichtenstern,Christoph ; 2213 ; \nLuft,Martin ; 2114 ; \nLichtenstern,Elmar ; 2034 ;"               ,  "http://sc-haar1931.de/"                                                                                                                      ,           11.725855067938046                          ,                    48.10902619066947                      ,                 "club"                   ,                      "08_Haar.jpg"                      ,         false,    88) ,
                            new Place( "SC Unterhaching"                     ,      "Oskar-von-Miller-Straße 9, 82008 Unterhaching"        ,      "Schmidt,Georg ;2346 ; FM\nLenz,Thomas ; 2248;\nDengler,Peter ;2211 ; \nSargin,Anatol,Dr. ; 2156 ; \nJacob,Tobias ;2021;"                                    ,  "http://www.schachclub-unterhaching.de/"                                                                                                      ,           11.608438892976558                          ,                    48.072918608656764                     ,                 "club"                   ,                      "09_Unterhaching.jpg"              ,         false,    55) ,
                            new Place( "SC Gräfelfing"                       ,       "Lochhamer Str. 79, 82166 Gräfelfing"                   ,      "Ovsepyan,Arshak ; 2208 ; \nZur Lage,Julian ; 2152 ; \nSchreiner,Richard ; 2132 ; \nHofmann,Jürgen ;2130; \nGesing,Oliver ;2128;"                           ,    "http://sk-graefelfing.net/"                                                                                                               ,               11.431779346270156                      ,                        48.128601271696645                 ,                 "club"                   ,                      "10_Graefelfing.jpg"               ,         false,        79 ) ,
                            new Place( "SF Dachau 1932"                      ,      "Ernst-Reuter-Platz 1, 85221 Dachau"                   ,      "Zollbrecht,Josef ; 2295 ; FM \nIberl,Michael ;2079 ; \nBreitenfeld,Alexander ; 2023 ; \nWiest,Leonhard ;1999 ; \nMaier,Michael ;1966 ;"                     ,  "https://sfdachau.de/"                                                                                                                        ,           11.459248129447216                          ,                    48.25987068568828                      ,                 "club"                   ,                      "11_Dachau.jpg"                    ,         false,    101 ),
                            new Place( "SC Roter Turm Altstadt München"      ,      "Arcisstraße 45, 80799 München"                        ,      "Kraemer,Stefan ; 2149; \nJosch,Sylvio ; 2148 ; \nGrosshans,Ralf-Michael ; 2105 ; \nKhrapko,Marharyta ; 2102 ; \n Ritzka,Simon ; 2038 ;"                     ,  "http://www.scroterturm.de/"                                                                                                                  ,           11.571719465878234                          ,                    48.15356845960309                      ,                 "club"                   ,                      "12_RoterTurm.jpg"                 ,         false,    71) ,
                            new Place( "SF Deisenhofen"                      ,      "Alpenstr. 16, 82041 Oberhaching"                      ,      "Thiel,Fabian ; 2154 ; \nHoeller,Armin ; 2027 ; \nKoppen,Harald ; 2019 ; \n Westphal,Dieter ; 1994 ; \nGoebel,Ulrich,Dr. ; 1989;"                            ,  "http://www.schachfreunde-deisenhofen.de/"                                                                                                    ,           11.590902327641874                          ,                    48.02390522465029                      ,                 "club"                   ,                      "13_Deisenhofen.jpg"               ,         false,    48) ,
                            new Place( "SC Eching"                           ,      "Roßbergerstr. 4, 85386 Eching"                        ,      "De Carbonnel,Paul ;2045 ; \nNiedermeier,Ludwig Stefan ; 1956 ; \n Ebersbach,Rudolf ;1843 ; \nPeck,Malcolm ;1772 ; \nMenzel,Felix Gerhard ;1753 ;"           ,  "http://www.sceching.de/schach/"                                                                                                              ,           11.621938427650964                          ,                    48.298044325324945                     ,                 "club"                   ,                      "14_Eching.jpg"                    ,         false,    20) ,
                            new Place( "SC Kirchseeon"                       ,      "An der Brücke 4, 85614 Kirchseeon"                    ,      "Lausmann,Mike ; 1983 ;\nNiedergesaess,Herbert ; 1965 ;\nRaedler,Walter ; 1738 ;\nLanger,Christian ; 1738;\nKasims,Stefans ; 1676 ;"                         ,  "https://cristilungul.com/"                                                                                                                   ,           11.892015326525712                          ,                    48.06879566875555                      ,                 "club"                   ,                      "15_Kirchseeon.jpg"                ,         false,    38) ,
                            new Place( "Mehlfeld's"                          ,      "Guardinistr. 98a, 81375 München"                      ,        ""                                                                                                                                                          ,  "https://www.mehlfeld.com/"                                                                                                                   ,           11.490581831820519                          ,                    48.11949476046334                  ,                 "cafe"                   ,                      "01_Mehlfelds.jpg"                 ,      false,      -1) ,
                            new Place( "Café am Beethovenplatz"              ,      "Goethestraße 51, 80336 München"                       ,        ""                                                                                                                                                          ,  "https://discover.events.com/de/bavaria/munich/e/business/weekly-chess-evening-cafe-30092021-cafe-beethovenplatz-422064929"                   ,           11.558762946418963                          ,                    48.13334696875066                  ,                 "cafe"                   ,                      "02_CafeBeethoven.jpg"             ,      false,      -1) ,
                            new Place( "Schelling-Salon"                     ,      "Schellingstraße 54, 80799 München"                    ,        ""                                                                                                                                                          ,  "https://www.schelling-salon.de/spiel-sport.html"                                                                                             ,           11.5731287725254                            ,                    48.1511776279703                   ,                 "cafe"                   ,                      "03_Schelling.jpg"                 ,      false,      -1) ,
                            new Place( "Heiterwanger Schachplatz"            ,      "Heiterwanger Str./Fernpaßstraße, 81373 München"       ,        ""                                                                                                                                                          ,  "http://www.spielplaetze-in-muenchen.de/cgi-bin/details.pl?n=Heiterwanger%20Schachplatz"                                                      ,           11.521993986945072                          ,                    48.121625080099726                 ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Münchner Freiheit"                   ,      "Münchner Freiheit 16, 80802 München"                  ,        ""                                                                                                                                                          ,  "https://www.abendzeitung-muenchen.de/muenchen/spiel-ohne-damen-az-besuch-am-schachplatz-an-der-muenchner-freiheit-art-723575"                ,           11.587298310743575                          ,                    48.16301147601446                  ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Weissenseepark"                      ,      "Ecke Sintperstr./Weissenseestr., 81539 München"       ,        ""                                                                                                                                                          ,  "http://weissenseepark.de/staendige-angebote-im-park/"                                                                                        ,           11.5881588926881                            ,                    48.106791863439554                 ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Outdoor Schach und Mühle"            ,      "Stösserstraße 6, 80933 München"                       ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.557877499252461                         ,                     48.221162847281924             ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Theresienwiese"                      ,      "Ecke Bavariaring /Ecke Beethovenstr., 80336 München"  ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.55323915940155                          ,                     48.131847453571                ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Hans-Mielich-Platz"                  ,      "Hans-Mielich-Platz, 81543 München"                    ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.571011220827694                         ,                     48.11677447162393              ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Nußbaumpark"                         ,      "Ziemssenstr. 80336 München"                           ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.562848334895179                         ,                     48.13237162693798              ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Piusplatz"                           ,      "Piusplatz, 81671 München"                             ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.613494515714878                         ,                     48.12266929209225              ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Ostpark"                             ,      "Ostpark 81735 München"                                ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.642996103277037                         ,                     48.11242051134809              ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Pappelallee"                         ,      "Pappelallee/ Ecke Blütenanger, 80995 München"         ,        ""                                                                                                                                                           ,   ""                                                                                                                                             ,            11.512682516863702                         ,                     48.20063134340086              ,                 "outdoor"                ,                      "outdoorchess.jpg"                 ,      false,      -1) ,
                            new Place( "Bodenschach"                         ,      "Bad-Schachener-Straße 28, 81671 München"              ,        "" , "", 11.61739184609688,	48.12027762259076	,"outdoor"	, "outdoorchess.jpg", false,	-1)
                     };

            Arrays.stream(arr).forEach(placeTable::push);

        }

        if(stateTable.all().isEmpty()){
            UserState state = new UserState(false, "1234", "admin");
            stateTable.push(state);
        }

        if(personTable.all().isEmpty()){
            Person[] arr = new Person[]{
                    new Person( "Wilhelm Steinitz"			,			"Wilhelm Steinitz (1836-1900), World Chess Champion 1886-1894, Austria-Hungary, USA"							                                                                 ,     "Chess is not for the faint-hearted"										                                                                                          ,    "history_01_wilhelm.jpg" ),
                    new Person( "Emanuel Lasker" 			    ,			"Emanuel Lasker (1868–1941), World Chess Champion 1894–1921, German Empire"									                                                                 ,     "Without mistakes, there can be no brilliant victories"										                                                                      ,    "history_02_emanuel.jpg" ),
                    new Person( "José Raul Capablanca" 		,			"José Raul Capablanca (1888 - 1942) World Chess Champion 1921-1927, Cuba"										                                                                 ,     "You can learn a lot more from a lost game than from a won game. You have to lose hundreds of games before you can be a good player"							      ,    "history_03_jose.jpg" ),
                    new Person( "Alexander Alekhin" 		    ,			"Alexander Alekhin (1892-1946) World Chess Champion 1927-1935, 1937-1946, Russian Empire, RSFSR, France"		                                                                 ,     "Combination is the soul of chess"										                                                                                          ,    "history_04_alexander.jpg" ),
                    new Person( "Max Euwe" 					,			"Max Euwe (1901–1981), World Chess Champion 1935–1937, Netherlands"									    	                                                                 ,     "In blitz, the knight is stronger than the bishop"										                                                                          ,    "history_05_max.jpg" ),
                    new Person( "Mikhail Botvinnik" 		    ,			"Mikhail Botvinnik (1911–1995), World Chess Champion 1948–1957, 1958–196, 1961–1963, USSR"		                                                                                 ,     "Chess is not only a struggle of minds, but also a struggle of nerves"										                                                      ,    "history_06_mikhail_botvinnik.jpg" ),
                    new Person( "Vasily Smyslov" 		     	,			"Vasily Smyslov (1921–2010), World Chess Champion 1957–1958, USSR"								                                                                                 ,     "In order to try to step at least a little further, you must first of all understand to what extent the predecessors have reached"								      ,    "history_07_vasily_smyslov.jpg" ),
                    new Person( "Mikhail Tal" 				,			"Mikhail Tal (1936–1992) World Chess Champion 1960–1961, USSR"									                                                                                 ,     "There are two kinds of victims: correct and mine"										                                                                          ,    "history_08_mikhail_tal.jpg" ),
                    new Person( "Tigran Petrosyan" 			,			"Tigran Petrosyan (1929-1984), World Chess Champion 1963-1969, USSR"								                                                                             ,     "In chess, everything is based on tactics. If strategy is a block of marble, then tactics are the chisel with which the master operates, creating a work of art"	  ,    "history_09_tigran_petrosian.jpg" ),
                    new Person( "Boris Spassky" 			    ,			"Boris Spassky (born 1937) World Chess Champion 1969-1972, USSR, France, Russia"					                                                                             ,     "Chess is like life"										                                                                                                          ,    "history_10_boris_spassk.JPG" ),
                    new Person( "Robert Fischer" 			    ,			"Robert Fischer (1943-2008), World Chess Champion 1972-1975, USA"										                                                                         ,     "Computer is the only opponent that has no excuse when it loses to me"										                                                      ,    "history_11_robby_fischer.jpg" ),
                    new Person( "Anatoly Karpov" 			    ,			"Anatoly Karpov (born 1951) World Chess Champion 975-1985, USSR, Russia; FIDE World Chess Champion 1993-1999, Russia"										                     ,     "The main thing is the struggle, the education of one’s character, and success will definitely come if you constantly work on chess and truly love it"			      ,    "history_12_anatoly_karpov.jpeg" ),
                    new Person( "Garry Kasparov"			    ,			"Garry Kasparov (born 1963) World Chess Champion 1985-1993, USSR, Russia; World Chess Champion 1993-2000 according to the Professional Chess Association (PSA), Russia"	     ,     "The main thing in chess is not how many moves ahead you think, but how you analyze the current situation"										                  ,    "history_13_garri_kasparov.jpg" ),
                    new Person( "Vladimir Kramnik"			,			"Vladimir Kramnik (born 1975) World Chess Champion according to the Professional Chess Association (PSA) 2000-2006, Russia; World Chess Champion 2006-2007, Russia"		     ,     "Chess is like bodybuilding. If you train every day, you stay in great shape. It's the same with your brain - chess is a matter of daily practice"				  ,    "history_14_wladimir_kramnik.jpeg" ),
                    new Person( "Viswanathan Anand" 		    ,			"Viswanathan Anand (born 1969) FIDE World Chess Champion 2000-2002, India; World Chess Champion 2007-2013, India"										                         ,     "Confidence is very important. If you make a mistake but don't let your opponent see what you think, then he may not notice the mistake"						      ,    "history_15_wesselin_topalow.jpg" ),
                    new Person( "Veselin Topalov" 			,			"Veselin Topalov (born 1975) FIDE World Chess Champion 2005-2006, Bulgaria"										                                                             ,     "In between chess training sessions, chess players sometimes watch films. I like the movie The Godfather"										                      ,    "history_16_viswanathan_anand.jpg" ),
                    new Person( "Magnus Carlsen" 			    ,			"Magnus Carlsen (born 1990) World Chess Champion 2013–present. vr, Norway"										                                                                 ,     "If you want to climb to the top, you must always take into account the risk that it isolates you from other people"										          ,    "history_17_magnus_carlsen.jpg" ),

            };

            for (Person person : arr) {
                person.citate = "“" + person.citate + "”";
            }
            Arrays.stream(arr).forEach(personTable::push);
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
        printTable(personTable);
        Log.i("DB", "-----------------------------------");
    }

    public static void printTable(DBResourceManager<?> table){
        Log.i("DB", "Table Name :" +  table.getTableName());
        Log.i("DB", "Size:" +  table.all().size());

        table.all().forEach(it -> Log.i("DB", it.toString()));
    }

}
