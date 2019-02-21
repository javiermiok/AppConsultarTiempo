package com.example.a21752434.appconsultartiempo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21752434.appconsultartiempo.retrofitData.Weather;
import com.example.a21752434.appconsultartiempo.retrofitUtils.APIRestService;
import com.example.a21752434.appconsultartiempo.retrofitUtils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TiempoActivity extends AppCompatActivity {

    private String latitud;
    private String longitud;

    private Weather weather;

    private TextView tvZona;
    private ImageView ivFoto;
    private TextView tvHora;
    private TextView tvTemperatura;
    private TextView tvValorHumedad;
    private TextView tvValorLluvia;
    private TextView tvSumario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);

        /*Iniciar elementos View*/
        tvZona = findViewById(R.id.tvZona);
        ivFoto = findViewById(R.id.ivFoto);
        tvHora = findViewById(R.id.tvHora);
        tvTemperatura = findViewById(R.id.tvTemperatura);
        tvValorHumedad = findViewById(R.id.tvValorHumedad);
        tvValorLluvia = findViewById(R.id.tvValorLluvia);
        tvSumario = findViewById(R.id.tvSumario);

        /*Obtener datos coordenadas*/
        latitud = getIntent().getStringExtra("LAT");
        longitud = getIntent().getStringExtra("LON");


        /*Conectar al API*/
        cargarDatos();

    }

    private void cargarDatos() {
        Retrofit r = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = r.create(APIRestService.class);
        Call<Weather> call = ars.obtenerWeather(APIRestService.KEY, latitud, longitud, APIRestService.EXCLUDE, APIRestService.LANG);

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(response.isSuccessful()) {
                    weather = response.body();

                    tvZona.setText(weather.getTimezone());
                    //ivImagen.setImageDrawable(loc.getCurrently().getIcon());
                    tvHora.setText(String.valueOf(weather.getCurrently().getTime()));

                } else {
                    showMessage(R.string.toast_response_unsuccessful);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                showMessage(R.string.toast_failure);
            }
        });
    }

    public void showMessage(int id) {
        Toast.makeText(this, getString(id), Toast.LENGTH_LONG).show();
    }


}
