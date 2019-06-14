package com.example.xmen;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CadastrarActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    ImageView foto;
    Button botao;
    Uri imageUri;
    EditText nomeCampo, habilidadeCampo;
    private AlertDialog alerta;
    public static String nomeMutante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        foto = (ImageView) findViewById(R.id.foto);
        botao = (Button) findViewById(R.id.escolherImagem);
        nomeCampo = (EditText) findViewById(R.id.nome);
        habilidadeCampo = (EditText) findViewById(R.id.habilidade);
    }

    public void onClickCadastrar(View view) {
        String nome = nomeCampo.getText().toString();
        String habilidade = habilidadeCampo.getText().toString();

        cadastrarMutante(nome, habilidade, "foto não pode ser cadastrada", 1);
    }

    private void cadastrarMutante(String nome, String habilidade, String fotoBase64, int usuario_id) {
        String url = getUrl(nome, habilidade, fotoBase64, usuario_id);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject mutante = null;
                        try {
                            mutante = response.getJSONObject("mutante");
                            CadastrarActivity.nomeMutante = mutante.get("nome").toString();

                            criaAlertDialog();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private String getUrl(String nome, String habilidade, String fotoBase64, int usuario_id) {
        return "http://10.0.2.2:8000/api/mutantes?nome="
                + nome
                + "&habilidade="
                + habilidade
                + "&foto="
                + fotoBase64
                + "&usuario_id="
                + usuario_id;
    }

    public void criaAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastrar Mutante");
        builder.setMessage("O mutante foi cadastrado com sucesso");

        builder.setPositiveButton("Ver mutante", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(getBaseContext(), MostraMutanteActivity.class);
                Bundle b = new Bundle();
                b.putString("nome", CadastrarActivity.nomeMutante);
                it.putExtras(b);
                startActivity(it);
            }
        });

        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(getBaseContext(), DashboardActivity.class);
                startActivity(it);
            }
        });
        alerta = builder.create();
        alerta.show();
    }

    public void onClickEscolherImagem(View view) {
        abreGaleria();
    }

    private void abreGaleria() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            foto.setImageURI(imageUri);
        }
    }
}
