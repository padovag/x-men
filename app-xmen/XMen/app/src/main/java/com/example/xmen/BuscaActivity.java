package com.example.xmen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuscaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);
    }

    public void onClickBusca(View view) {
        EditText habilidadeCampo = findViewById(R.id.busca);
        String habilidade = habilidadeCampo.getText().toString();

        buscaMutantes(habilidade);
    }

    private void buscaMutantes(String habilidade) {
        String url = "http://10.0.2.2:8000/api/pesquisar?habilidade=" + habilidade;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<Mutante> mutantes = parseResponse(response);
                            ArrayList<String> textoMutantes = getMutanteTextToInsertOnList(mutantes);
                            criaListView(textoMutantes);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView teste = findViewById(R.id.teste);
                        teste.setText(error.toString());

                        System.out.print(error.toString());
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private ArrayList<Mutante> parseResponse(JSONObject response) throws JSONException {
        ArrayList<Mutante> mutantes = new ArrayList();
        JSONArray mutantesJson = response.getJSONArray("mutantes");

        for (int i = 0; i < mutantesJson.length(); i++) {
            JSONObject mutanteJson = mutantesJson.getJSONObject(i);
            Mutante mutante = new Mutante();
            mutante.nome = mutanteJson.get("nome").toString();
            mutante.habilidade = mutanteJson.get("habilidade").toString();
            mutante.foto = mutanteJson.get("foto").toString();
            mutante.usuario_id = Integer.parseInt(mutanteJson.get("usuario_id").toString());
            mutantes.add(mutante);
        }

        return mutantes;
    }

    private ArrayList<String> getMutanteTextToInsertOnList(ArrayList<Mutante> mutantes) {
        ArrayList<String> textoMutantes = new ArrayList<>();
        for (Mutante mutante : mutantes) {
            textoMutantes.add(mutante.nome);
        }
        return textoMutantes;
    }

    private void criaListView(ArrayList<String> mutantes) {
        final ListView listaMutantes = (ListView) findViewById(R.id.lista);
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mutantes);
        listaMutantes.setAdapter(array);

        listaMutantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent it = new Intent(getBaseContext(), MostraMutanteActivity.class);
                Bundle b = new Bundle();
                String nomeMutante = (String) listaMutantes.getAdapter().getItem(arg2);
                b.putString("nome", nomeMutante);
                it.putExtras(b);
                startActivity(it);
            }
        });
    }
}
