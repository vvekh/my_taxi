package com.example.my_taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Destination extends AppCompatActivity {
    Button SaveBtn;
    EditText City1, City2, Street1, Street2, House1, House2;
    private static String TAG = "маршрут";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        SaveBtn = findViewById(R.id.save_btn);
        City1 = findViewById(R.id.dest_city1);
        Street1 = findViewById(R.id.dest_street1);
        House1 = findViewById(R.id.dest_house1);
        City2 = findViewById(R.id.dest_city2);
        Street2 = findViewById(R.id.dest_street2);
        House2 = findViewById(R.id.dest_house2);
        Log.d(TAG, "в маршруте запущен onCreate");
    }

    //СОХРАНЕНИЕ ДАННЫХ О МАРШРУТЕ
    public void onSaveDestClick(View view) {
        Log.d(TAG, "в маршруте запущен onSaveDestClick");

        Intent intent = new Intent();
        intent.putExtra("city1", City1.getText().toString());
        intent.putExtra("city2", City2.getText().toString());
        intent.putExtra("street1", Street1.getText().toString());
        intent.putExtra("street2", Street2.getText().toString());
        intent.putExtra("house1", House1.getText().toString());
        intent.putExtra("house2", House2.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}