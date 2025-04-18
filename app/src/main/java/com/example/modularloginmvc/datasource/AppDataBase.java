package com.example.modularloginmvc.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.modularloginmvc.datamodel.UsuarioDataModel;

public class
AppDataBase extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public static String name = "mvc.sqlite";
    public static int version = 1;

    public AppDataBase(Context context) {
        super(context, name, null, version);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuarioDataModel.criarTabela());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String tabela, ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;
//try = tentar inserir;
//catch = se nao for ele vai ser como verdadeiro;
        try{
            retorno = db.insert(tabela, null, dados) > 0;
        }catch (Exception e){
            e.getMessage();
        }

        return retorno;
    }

    public boolean chkUser(String userName){
        boolean retorno = false;
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "
        + UsuarioDataModel.TABELA + " WHERE email = ?",
                new String[] {userName});

        return cursor.getCount() > 0;
    }

    public boolean chkUserPassword(String userName, String password){
        boolean retorno = false;
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "
                        + UsuarioDataModel.TABELA + " WHERE email = ?"
                        +" AND senha = ?",
                new String[] {userName, password});

        return cursor.getCount() > 0;
    }

}
