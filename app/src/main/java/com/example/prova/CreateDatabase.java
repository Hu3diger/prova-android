package com.example.prova;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "maps.db";
    public static final String ID = "_id";
    public static final String TABLE = "pacientes";
    public static final String NOME = "nome";
    public static final String IDADE = "idade";
    public static final String MORTALIDADE = "mortalidade";
    public static final String LEUCOCITOS = "leucocitos";
    public static final String GLICEMIA = "glicemia";
    public static final String AST = "ast";
    public static final String LDH = "ldh";
    public static final String HASLITIASE = "hasLitiase";

    private static final int VERSION = 1;

    public CreateDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ( "
                + "_id integer primary key autoincrement, " +
                "nome text, " +
                "idade numeric, " +
                "mortalidade text," +
                "leucocitos numeric," +
                "glicemia numeric," +
                "ast numeric," +
                "ldh numeric," +
                "hasLitiase numeric" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}