package com.example.xmen;

import android.content.DialogInterface;
import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.usuario);
        senha = findViewById(R.id.senha);

    }

    public void onClickEntrar(View view){
        try{
            String usuarioStr = usuario.getText().toString();
            String senhaStr = senha.getText().toString();
            String url = "http://10.0.2.2:8000/api/login?usuario="+usuarioStr+"&senha="+senhaStr;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                usuarioValido = (boolean)(response.get("autorizado"));
                                TextView teste = findViewById(R.id.teste);
                                teste.setText(response.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                TextView teste = findViewById(R.id.teste);
                                teste.setText(e.getMessage());
                                System.out.println(e.getMessage() + "erro aqui 1");
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            TextView teste = findViewById(R.id.teste);
                            teste.setText(error.toString());
                            System.out.println("erro aqui 2");
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
//            AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext());
//            builder.setTitle("Autenticação");
//            if(usuarioValido){
//                builder.setMessage("Login autenticado.");
//                builder.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        Intent it = new Intent(getBaseContext(),DashboardActivity.class);
//                        startActivity(it);
//                        finish();
//                    }
//                });
//            }
//            else{
//                builder.setMessage("Usuário inválido.");
//                builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        alerta.cancel();
//                        finish();
//                    }
//                });
//            }
//            alerta = builder.create();
//            alerta.show();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage() + "erro aquiiiiiii");
            TextView teste = findViewById(R.id.teste);
            teste.setText(ex.getMessage());
        }
    }

    public void onClickTeste(View view){
        Intent it = new Intent(getBaseContext(), DashboardActivity.class);
        startActivity(it);
    }

}
