package com.example.modularloginmvc.controller;

import android.content.ContentValues;
import android.content.Context;

import com.example.modularloginmvc.datamodel.UsuarioDataModel;
import com.example.modularloginmvc.datasource.AppDataBase;
import com.example.modularloginmvc.model.Usuario;

import java.util.Collections;
import java.util.List;

public class UsuarioController extends AppDataBase implements ICRUD <Usuario> {

    ContentValues dadosDoUsuario;

    //metodo construtor de alguma classe, que devolve as informaçoes para o lugar certo;

    public UsuarioController(Context context) {
        super(context);
    }

//definição dos metodos AppDataBase que incluem o ICRUD;
    @Override
    public boolean incluir(Usuario object) {
        dadosDoUsuario = new ContentValues();
        dadosDoUsuario.put(UsuarioDataModel.NOME, object.getNome() );
        dadosDoUsuario.put(UsuarioDataModel.EMAIL, object.getEmail() );
        dadosDoUsuario.put(UsuarioDataModel.SENHA, object.getSenha() );
        return insert(UsuarioDataModel.TABELA, dadosDoUsuario);

    }

    @Override
    public boolean alterar(Usuario object) {
        return false;
    }

    @Override
    public boolean deletar(Usuario object) {
        return false;
    }

    @Override
    public List Listar() {
        return Collections.emptyList();
    }
}
