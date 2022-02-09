//package com.example.chessapp.storage;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class DBZugriff extends SQLiteOpenHelper {
//    private SQLiteDatabase db;
//    private String tabellenSQL;
//    private String tabelle;
//
//    public DBZugriff(Context activity, String dbName, String tabellenSQL) {
//        super(activity, dbName, null, 1);
//        this.tabellenSQL = tabellenSQL;
//        bestimmeTabelle();
//        db = this.getWritableDatabase();
//        onUpgrade(db, 0, 1);
//    }
//
//    // fxs Tabellennamen extrahieren aus uebergebenen Strings
//    private void bestimmeTabelle() {
//        String sql = tabellenSQL.toUpperCase();
//        StringTokenizer tokenizer = new StringTokenizer(sql);
//// den Tabellennamen suchen
//        while (tokenizer.hasMoreTokens()) {
//            String token = tokenizer.nextToken();
//            if (token.equals("TABLE")) {
//                tabelle = tokenizer.nextToken();
//                break;
//            }
//        }
//    }
//
//    public void onCreate(SQLiteDatabase db) {
//        try {
//// Tabelle anlegen
//            db.execSQL(tabellenSQL);
//// int d = Log.d("gehtfxs", "tabelle wird angelegt");
//        } catch (Exception ex) {
//            Log.e("gehtfxs", ex.getMessage());
//        }
//    }
//
//    @Override
//// fxs falls DB bereits existiert, wird onUpgrade automatisch aufgerufen und geprüft,
//// welche Version der DB vorhanden ist
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + tabelle);
//        onCreate(db);
//    }
//
//    @Override
//// fxs Schliesst das DB-Objekt.
//    public synchronized void close() {
//        if (db != null) {
//            db.close();
//            db = null;
//        }
//        super.close();
//    }
//
//    // Datensatz = Zeile in DB einfuegen
//    public long datensatzEinfuegen(Datensatz datensatz) {
//        try {
//            ContentValues daten = erzeugeDatenObjekt(datensatz);
//            return db.insert(tabelle, null, daten); // id wird automatisch von SQLite
//        } catch (Exception ex) {
//            Log.d("gehtfxs", ex.getMessage());
//            return -1;
//        }
//    }
//    // fxs Fuehrt Abfrage durch und liefert geordneten Cursor zurueck, hier: orderBy
//
//    //public Cursor erzeugeListViewCursor() {
//    //String[] spalten = new String[]{"_id", "feld1_name", "feld2_name", "feld3_name"};
//// fxs .query = DB-Abfrage mit return eines "Cursor"
//    //return db.query(tabelle, spalten, null, null, null, null, "feld1_name");}
//    // fxs Liste mit allen Datensaetzen = Zeilen liefern
//    public List<Datensatz> leseDatensaetze() {
//        List<Datensatz> ergebnis = new ArrayList<Datensatz>();
//        Cursor cursor = null;
//        try {
//            cursor = db.query(tabelle, null, null, null, null, null, null);
//// fxs getCount() liefert Anzahl der gefundenen Datensaetze, der vorherigen
//            int anzahl = cursor.getCount();
//// fxs moveToFirst() geht an erste Position, return false, falls keine
//            cursor.moveToFirst();
//            for (int i = 0; i < anzahl; i++) {
//                Datensatz ds = erzeugeDatensatz(cursor);
//                ergebnis.add(ds);
//// fxs moveToNext() geht zur naechsten Position, return true, falls die exsistiert, false, falls nicht = Ende
//                cursor.moveToNext();
//            }
//        } catch (Exception ex) {
//            Log.e("gehtfxs", ex.getMessage());
//        } finally {
//// egal ob Erfolg oder Exception:: cursor schliessen
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
//        return ergebnis;
//    }
//
//    // Datensatz updaten mit neuer Version der DB
//    public void aktualisiereDatensatz(Datensatz ds) {
//        try {
//            ContentValues daten = erzeugeDatenObjekt(ds);
//            db.update(tabelle, daten, "_id = " + ds.id, null);
//        } catch (Exception ex) {
//            Log.e("gehtfxs", ex.getMessage());
//        }
//    }
//
//    // fxs Datensatz loeschen mit uebergebener id
//    public void loescheDatensatz(Datensatz ds) {
//        try {
//            db.delete(tabelle, "_id = " + ds.id, null);
//        } catch (Exception ex) {
//            Log.e("gehtfxs", ex.getMessage());
//        }
//    }
//
//    // fxs an aktueller Stelle in Datenbank = dort, wo der Cursor ist, eine neue Zeile =neuen Datensatz erzeugen
//    private Datensatz erzeugeDatensatz(Cursor cursor) {
//        Datensatz ds = new Datensatz();
//        ds.id = cursor.getLong(0);
//        ds.clubname = cursor.getString(1);
//        ds.adresse = cursor.getString(2);
//        ds.link = cursor.getString(3);
//        ds.longitude = cursor.getDouble(4);
//        ds.latitude = cursor.getDouble(5);
//        ds.kat = cursor.getString(6);
//        return ds;
//    }
//
//    // fxs neuer Datensatz wird erzeugt und die Felder werden mit Inhalt (values) gefuellt
//    private ContentValues erzeugeDatenObjekt(Datensatz datensatz) {
//        ContentValues daten = new ContentValues();
//// fxs put schreibt einen Wert in eine Spalte, z.B. Wert von feld1_name in Spaltename
//        daten.put("clubname", datensatz.clubname);
//        daten.put("adresse", datensatz.adresse);
//        daten.put("link", datensatz.link);
//        daten.put("longitude", datensatz.longitude);
//        daten.put("latitude", datensatz.latitude);
//        daten.put("kat", datensatz.kat);
//        return daten;
//    }
//}
//
