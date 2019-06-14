package com.example.xmen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onClickListar(View view) {
        Intent it = new Intent(this, ListarActivity.class);
        startActivity(it);
    }

    public void onClickCadastrar(View view) {
        Intent it = new Intent(this, CadastrarActivity.class);
        startActivity(it);
    }

    public void onClickBuscar(View view) {
        Intent it = new Intent(this, BuscaActivity.class);
        startActivity(it);
    }

    public void onClickSair(View view) {
        finishAffinity();
    }
}
