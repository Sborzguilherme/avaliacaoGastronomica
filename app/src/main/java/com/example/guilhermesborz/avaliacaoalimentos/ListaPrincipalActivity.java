package com.example.guilhermesborz.avaliacaoalimentos;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilhermesborz.avaliacaoalimentos.Adapter.GastronomiaAdapter;
import com.example.guilhermesborz.avaliacaoalimentos.DAO.GastronomiaDAO;
import com.example.guilhermesborz.avaliacaoalimentos.Modelo.Gastronomia;

import java.util.ArrayList;


public class ListaPrincipalActivity extends AppCompatActivity {

    TextView tvAviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_principal);
        tvAviso = (TextView) findViewById(R.id.tvAviso);

        ArrayList<Gastronomia> lista = GastronomiaDAO.obterLista();

        if (lista.size() < 1) {
            tvAviso.setText("NÂO HÁ AVALIAÇÕES CADASTRADAS");
        }

        RecyclerView rvListaGastronomia = (RecyclerView) findViewById(R.id.rvListaGastronomia);

        RecyclerView.LayoutManager formaApresentacao = new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        rvListaGastronomia.setLayoutManager(formaApresentacao);
        GastronomiaAdapter adaptador = new GastronomiaAdapter(this.getApplicationContext(), lista);
        rvListaGastronomia.setAdapter(adaptador);
    }

    public void cliqueBotao(View view) {
        Intent abridor = new Intent(this, CadastroActivity.class);
        startActivity(abridor);
    }



}

