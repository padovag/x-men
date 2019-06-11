package com.example.xmen;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CadastrarActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    ImageView foto;
    Button botao;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        foto = (ImageView) findViewById(R.id.foto);
        botao = (Button) findViewById(R.id.escolherImagem);
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
