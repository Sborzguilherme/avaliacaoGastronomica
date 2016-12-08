package com.example.guilhermesborz.avaliacaoalimentos.DAO;

/**
 * Created by Micro Solution on 07/12/2016.
 */
import com.example.guilhermesborz.avaliacaoalimentos.Modelo.Gastronomia;

import java.util.ArrayList;

public class GastronomiaDAO {

    private static ArrayList<Gastronomia> listaGastronomia;

    private static void inicializarLista(){
        if(GastronomiaDAO.listaGastronomia == null){
            GastronomiaDAO.listaGastronomia = new ArrayList<>();
        }
    }
    public static void salvar(Gastronomia gastronomia){
        inicializarLista();
        listaGastronomia.add(gastronomia);

    }

    public static ArrayList<Gastronomia> obterLista(){
        inicializarLista();
        return listaGastronomia;
    }
}