package com.example.xmen;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MostraMutanteActivity extends AppCompatActivity {

    TextView nomeView, habilidadeView;
    ImageView foto;
    int id;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_mutante);
        String nome = getNomeFromIntent();

        buscaMutante(nome);
    }

    private String getNomeFromIntent() {
        Bundle b = getIntent().getExtras();
        String nome = "nao achou nome";

        if(b != null)
            nome = b.getString("nome");

        return nome;
    }

    private void buscaMutante(String nome) {
        String url = "http://10.0.2.2:8000/api/buscaMutantePorNome?nome=" + nome;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            populaTela(response.getJSONObject("mutante"));
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

    private void populaTela(JSONObject mutante) throws JSONException {
        nomeView = (TextView) findViewById(R.id.nome);
        String nome = mutante.get("nome").toString();
        nomeView.setText(nome);

        id = (int) mutante.get("id");
        String imageUri = "http://10.0.2.2:8000/storage/mutante" + id + ".png";
        foto = (ImageView) findViewById(R.id.foto);
        Picasso.with(this).load(imageUri).into(foto);

        String habilidade = mutante.get("habilidade").toString();
        habilidadeView = (TextView) findViewById(R.id.habilidade);
        habilidadeView.setText(habilidade);
    }

    public void onClickDeletar(View view) {
        deletar(id);
    }

    private void deletar(int id) {
        String url = "http://10.0.2.2:8000/api/deletar?id=" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        criaAlertDialog("Deletado!", "Mutante deletado com sucesso");
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        criaAlertDialog("Não foi possível deletar", error.getMessage());
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void criaAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(getBaseContext(), ListarActivity.class);
                startActivity(it);
            }
        });
        alerta = builder.create();
        alerta.show();
    }
}
