package com.example.prova;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class DAL {
    private static final String TAG = "DAL";

    private SQLiteDatabase db;
    private CreateDatabase database;

    DAL(Context context) {
        database = new CreateDatabase(context);
    }

    boolean insert(String nome, int idade, String mortalidade, int leucocitos, int glicemia, int ast, int ldh, int hasLitiase) {
        ContentValues values;
        long result;

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.IDADE, idade);
        values.put(CreateDatabase.MORTALIDADE, mortalidade);
        values.put(CreateDatabase.LEUCOCITOS, leucocitos);
        values.put(CreateDatabase.GLICEMIA, glicemia);
        values.put(CreateDatabase.AST, ast);
        values.put(CreateDatabase.LDH, ldh);
        values.put(CreateDatabase.HASLITIASE, hasLitiase);


        result = db.insert(CreateDatabase.TABLE, null, values);
        db.close();

        if (result == -1) {
            Log.e(TAG, "insert: Erro inserindo registro");
            return false;
        }

        return true;
    }

    boolean delete(int id) {
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        result = db.delete(CreateDatabase.TABLE, where, args);
        db.close();

        if (result == -1) {
            Log.e(TAG, "insert: Erro removendo registro");
            return false;
        }

        return true;
    }

    Cursor findById(int id) {
        Cursor cursor;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getReadableDatabase();

        cursor = db.query(CreateDatabase.TABLE, null,
                where, args, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    boolean update(int id, String nome, int idade, String mortalidade, String leucocitos, String glicemia, String ast, String ldh, Boolean hasLitiase) {
        ContentValues values;
        long result;

        String where = "_id = ?";
        String[] args = { String.valueOf(id) };

        db = database.getWritableDatabase();

        values = new ContentValues();
        values.put(CreateDatabase.NOME, nome);
        values.put(CreateDatabase.IDADE, idade);
        values.put(CreateDatabase.MORTALIDADE, mortalidade);
        values.put(CreateDatabase.LEUCOCITOS, leucocitos);
        values.put(CreateDatabase.GLICEMIA, glicemia);
        values.put(CreateDatabase.AST, ast);
        values.put(CreateDatabase.LDH, ldh);
        values.put(CreateDatabase.HASLITIASE, hasLitiase);

        result = db.update(CreateDatabase.TABLE, values, where, args);
        db.close();

        if (result == -1) {
            Log.e(TAG, "insert: Erro atualizando registro");
            return false;
        }

        return true;
    }

    Cursor loadAll() {
        Cursor cursor;
        String[] fields = {CreateDatabase.ID, CreateDatabase.NOME, CreateDatabase.IDADE, CreateDatabase.MORTALIDADE, CreateDatabase.LEUCOCITOS,
        CreateDatabase.GLICEMIA, CreateDatabase.AST, CreateDatabase.LDH, CreateDatabase.HASLITIASE};
        db = database.getReadableDatabase();

        cursor = db.query(CreateDatabase.TABLE, fields, null,
                null, null, null,
                null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}