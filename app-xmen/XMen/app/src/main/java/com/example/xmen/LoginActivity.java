package com.example.xmen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText usuario, senha;
    private AlertDialog alerta;
    boolean usuarioValido;
    public static String usuarioStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);

    }

    public void onClickEntrar(View view){
        usuarioStr = usuario.getText().toString();
        String senhaStr = senha.getText().toString();
        String url = "http://10.0.2.2:8000/api/login?usuario="+ usuarioStr +"&senha="+senhaStr;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            usuarioValido = (boolean)(response.get("autorizado"));
                            if (!usuarioValido) {
                                criaAlertDialog(response.get("mensagem").toString());
                                return;
                            }
                            JSONObject jsonUsuario = response.getJSONObject("usuario");
                            int usuarioId = (int) jsonUsuario.get("id");
                            salvaSessao(usuarioId);
                            abreDashboard();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        TextView teste = findViewById(R.id.teste);
                        teste.setText(error.toString());
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void criaAlertDialog(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(mensagem);
        builder.setMessage("Usuário " + LoginActivity.usuarioStr);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent it = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(it);
            }
        });
        alerta = builder.create();
        alerta.show();
    }

    public void salvaSessao(int usuarioId) {
        SharedPreferences preferences = getSharedPreferences("mutantes", Context.MODE_PRIVATE);
        preferences.edit().putInt("usuario_id", usuarioId).commit();
    }

    public void abreDashboard() {
        Intent it = new Intent(getBaseContext(), DashboardActivity.class);
        startActivity(it);
    }

}
