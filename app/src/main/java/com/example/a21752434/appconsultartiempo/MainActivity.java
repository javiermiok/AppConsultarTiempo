package com.example.a21752434.appconsultartiempo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etLat;
    private EditText etLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLat = findViewById(R.id.etLatitud);
        etLong = findViewById(R.id.etLongitud);
    }

    public void consultarTiempo(View v) {
        String latitud = etLat.getText().toString().trim();
        String longitud = etLong.getText().toString().trim();

        if (latitud.isEmpty() || longitud.isEmpty()) {
            showMessage(R.string.toast_campos_vacios);

        } else {
            Intent i = new Intent(this, TiempoActivity.class);
            i.putExtra("LAT", latitud);
            i.putExtra("LON", longitud);
            startActivity(i);

        }

    }

    public void showMessage(int id) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show();
    }
}
