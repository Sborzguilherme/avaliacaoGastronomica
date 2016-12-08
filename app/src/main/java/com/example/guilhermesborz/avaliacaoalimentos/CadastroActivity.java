package com.example.guilhermesborz.avaliacaoalimentos;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilhermesborz.avaliacaoalimentos.DAO.GastronomiaDAO;
import com.example.guilhermesborz.avaliacaoalimentos.Modelo.Gastronomia;

public class CadastroActivity extends AppCompatActivity {

    private EditText edDescricao;
    private RadioGroup rgCategoria;
    private RatingBar ratingNota;
    private ImageView ivFoto;
    private Bitmap recebeImagem;
    //private double latitude;
    //private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edDescricao = (EditText) findViewById(R.id.edDescricao);
        rgCategoria = (RadioGroup) findViewById(R.id.rgCategorias);
        ratingNota = (RatingBar) findViewById(R.id.ratingNota);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //voltando do app de camera
        if (requestCode == 3) if (resultCode == RESULT_OK) {
            ivFoto = (ImageView) findViewById(R.id.ivFoto);
            //recebimento da imagem através de bitmap
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            recebeImagem = imageBitmap;
            ivFoto.setImageBitmap(imageBitmap);
        }
    }
    public void cliqueBotao2(View view) { //BOTÃO SALVAR

        int rbSelecinado = rgCategoria.getCheckedRadioButtonId();

        if (rbSelecinado == -1) {       // VALIDAÇÃO RADIO GROUP
            Toast.makeText(CadastroActivity.this.getApplicationContext(), R.string.Selecione_uma_opcao, Toast.LENGTH_SHORT).show();
        } else if(edDescricao.getText().toString().isEmpty()){      // VALIDAÇÃO DESCRIÇÃO
            Toast.makeText(CadastroActivity.this.getApplicationContext(), R.string.Escreva_algo_na_descricao, Toast.LENGTH_SHORT).show();
        }
        else{

            //salvarLocal(view);        //OBTEM LOCALIZAÇÃO

            Gastronomia comida = new Gastronomia();

            String textoDescricao = edDescricao.getText().toString();


            RadioButton opcaoSelecionada = (RadioButton)findViewById(rbSelecinado);

            if(opcaoSelecionada.getText().toString().equals(getString(R.string.Entrada))){
                comida.setCategoria("Entrada");
            }else if(opcaoSelecionada.getText().toString().equals(getString(R.string.Prato_principal))){
                comida.setCategoria("Prato Principal");
            }else if(opcaoSelecionada.getText().toString().equals(getString(R.string.Sobremesa))){
                comida.setCategoria("Sobremesa");
            }else if(opcaoSelecionada.getText().toString().equals(getString(R.string.Lanche))){
                comida.setCategoria("Lanche");
            }

            comida.setDescricao(textoDescricao);
            comida.setNotaRatingBar(ratingNota.getRating());
            //comida.setLatitude(latitude);
            //comida.setLongitude(longitude);

            comida.setImagemCapturada(recebeImagem);

            Intent abridor = new Intent(this, ListaPrincipalActivity.class);
            GastronomiaDAO.salvar(comida);
            abridor.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(abridor);
        }
    }

    public void clicouNoBotaoTirarFoto(View v){
        //abertura da câmera para recebimento da imagem através de bitmap
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, 3);

    }

    /*public void salvarLocal(View v) {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //nao tem permissão, então solicita
            String[] permissoes = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissoes, 1);
            return;
        }
        //se chegou aqui é porque há permissão
        obterCoordenada();

    }*/

    /*@Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        //se chegou aqui é porque a permissão foi solicitada, é hora de avaliar se foi concedida ou não
        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i] == Manifest.permission.ACCESS_FINE_LOCATION && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                //permissão concedida
                obterCoordenada();
                return;
            }
        }
        Toast.makeText(this.getApplicationContext(), "Sem permissão", Toast.LENGTH_SHORT).show();
    }
    private Location location;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @SuppressWarnings({"ResourceType"})
    private void obterCoordenada() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //pbLoader.setVisibility(View.VISIBLE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("GPS desligado")
                    .setCancelable(false)
                    .setPositiveButton("Ligar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    })
                    .setNegativeButton("Não Ligar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            //pbLoader.setVisibility(View.INVISIBLE);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        }
    }*/
}
