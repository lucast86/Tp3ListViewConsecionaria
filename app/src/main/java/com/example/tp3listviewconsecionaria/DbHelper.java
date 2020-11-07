package com.example.tp3listviewconsecionaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private final static String NAME_DB = "autos.sqlite";
    private final static int VERSION_DB = 1;

   /* private final String sqlCreateAutos = "CREATE TABLE autos (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " marca	TEXT NOT NULL,precio NUMERIC NOT NULL,modelo INTEGER NOT NULL, " +
            "km	NUMERIC, descipcion TEXT);";
*/

    private final String sqlCreateAutos = "CREATE TABLE autos ( " +
            "id	INTEGER NOT NULL,  " +
            " marca	TEXT NOT NULL, " +
            "precio	NUMERIC(11,2) NOT NULL, " +
            "modelo	INTEGER NOT NULL, " +
            "km	NUMERIC(11,2), " +
            "descipcion TEXT, " +
            "PRIMARY KEY (id  AUTOINCREMENT) " +
            ");";


    public DbHelper(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateAutos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //se elimina version anterior de tabla
        db.execSQL("DROP TABLE IF EXISTS Autos");
        //se crea nueva version de tabla
        db.execSQL(sqlCreateAutos);
    }
}
