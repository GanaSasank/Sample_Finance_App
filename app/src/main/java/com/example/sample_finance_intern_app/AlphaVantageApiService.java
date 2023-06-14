package com.example.sample_finance_intern_app;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AlphaVantageApiService {
    private static final String BASE_URL = "https://www.alphavantage.co/query   ";
    private static final String API_KEY = "MHBRUIZTOGMG78JM";

    public interface AlphaVantageCallback {
        void onSuccess(JsonObject data);
        void onFailure(String errorMessage);
    }

    public static void getStockData(String symbol, AlphaVantageCallback callback) {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + "?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String errorMessage = e.getMessage();
                callback.onFailure(errorMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonData = response.body().string();
                    JsonObject data = JsonParser.parseString(jsonData).getAsJsonObject();
                    callback.onSuccess(data);
                } else {
                    String errorMessage = "Error: " + response.code() + " - " + response.message();
                    callback.onFailure(errorMessage);
                }
            }
        });
    }
}
