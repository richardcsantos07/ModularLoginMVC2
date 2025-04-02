package com.example.modularloginmvc.datamodel;

public class UsuarioDataModel {
    /*  passo 1 Atributo nome da tabela
        passo 2 Atributos um para um com os nomes dos campos da tabela
        passo 3 Query ( consulta ) para criar a tabela no banco de dados
        passo 4 Metodo para gerar o script para criar a tabela
     */

    // passo 1
    public static final String TABELA = "usuario";

    // passo 2
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";
    public static String consultaCriarTabela = "";

    // passo 3
    public static String criarTabela(){
        consultaCriarTabela += "CREATE TABLE " + TABELA + "(";
        consultaCriarTabela += ID + " integer primary key autoincrement, ";
        consultaCriarTabela += NOME + " text, ";
        consultaCriarTabela += EMAIL + " text, ";
        consultaCriarTabela += SENHA + " text ";
        consultaCriarTabela += ")";

        return consultaCriarTabela;

    }


}
