package com.example.xmen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        Mutante mutante1 = new Mutante("giulia", "aaa", "aa", 1);
        Mutante mutante2 = new Mutante("aaaa", "aaa", "aa", 1);

        criaListView(mutante1, mutante2);
    }

    private void criaListView(Mutante mutante1, Mutante mutante2) {
        ListView listaMutantes = (ListView) findViewById(R.id.lista);
        String[] mutantes = new String[]{mutante1.toString(), mutante2.toString()};
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mutantes);
        listaMutantes.setAdapter(array);
    }

    public void buscaMutantes() {
        String url = "http://10.0.2.2:8000/api/mutantes";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mutantes = response.getJSONArray("mutantes");
                            for(Object mutante : mutantes) {

                            }
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
}
