package com.example.sample_finance_intern_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

public class GetAPIData extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_apidata);
        EditText symbol= findViewById(R.id.companysymbol);
        TextView tvApiResponse = findViewById(R.id.stockdata);
        Button search=(Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlphaVantageApiService.getStockData(symbol.toString(), new AlphaVantageApiService.AlphaVantageCallback() {
                    @Override
                    public void onSuccess(JsonObject data) {
                        // Handle successful response here
                        //Log.d("AlphaVantage", "Data: " + data.toString());
                        tvApiResponse.setText(data.toString());
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        // Handle error here
                        //Log.e("AlphaVantage", "Error: " + errorMessage);
                        Toast.makeText(GetAPIData.this, "Failed to Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}