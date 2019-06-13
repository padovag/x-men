package com.example.xmen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        int id = (int) mutante.get("id");
        String imageUri = "http://10.0.2.2:8000/storage/mutante" + id + ".png";
        foto = (ImageView) findViewById(R.id.foto);
        Picasso.with(this).load(imageUri).into(foto);

        String habilidade = mutante.get("habilidade").toString();
        habilidadeView = (TextView) findViewById(R.id.habilidade);
        habilidadeView.setText(habilidade);
    }
}
