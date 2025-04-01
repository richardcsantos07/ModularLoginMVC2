package com.example.modularloginmvc.datamodel;

import android.util.Log;

public class UserDataModel {
    /*
    Modelo objeto relacional
    Toda classe DataModel tem essa estutura?
    1- Atributo nome da tabela
    2- Atributos um para cada da tabela
    3- Variavel com Query (consulta) para criar a tabela no DB
    4- Metodo para gerar o script da criacao da tabela
     */

    //1
    public static final String TABLE = "users";
    //2
    public static final String ID     = "id";
    public static final String NOME   = "nome";
    public static final String EMAIL   = "email";
    public static final String PASSWORD  = "password";

    //3
    public static String queryCreateTable = "";

    //4
    public static String createTable(){
        queryCreateTable += "CREATE TABLE " + TABLE + "(";
        queryCreateTable += ID + " integer primary key autoincrement, ";
        queryCreateTable += NOME + " text, ";
        queryCreateTable += EMAIL + " text,";
        queryCreateTable += PASSWORD + " text";
        queryCreateTable += ")";

        Log.i("TAG", "createTable: " + queryCreateTable);

        return queryCreateTable;

    }



}
