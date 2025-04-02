package com.example.modularloginmvc.controller;

import java.util.List;

public interface ICRUD <T> {
    public boolean incluir(T object);
    public boolean alterar(T object);
    public boolean deletar(T object);
    public List <T> Listar();
}
