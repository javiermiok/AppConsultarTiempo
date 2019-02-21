package com.example.a21752434.appconsultartiempo.retrofitUtils;


import com.example.a21752434.appconsultartiempo.retrofitData.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRestService {

    public static final String BASE_URL = "https://api.darksky.net/";
    public static final String KEY = "11ce4328111023379e0fdc9d28c24a02";
    public static final String EXCLUDE = "minutely,hourly,daily,alerts,flags";
    public static final String LANG = "es";

    @GET("forecast/{key}/{latitude},{longitude}")
    Call<Weather> obtenerWeather(@Path("key") String key,
                                 @Path("latitude") String lat,
                                 @Path("longitude") String lon,
                                 @Query("exclude") String exclude,
                                 @Query("lang") String lang);

}
