package com.example.my_taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    TextView ProfName, ProfNumber, ProfPlace;
    Button DestBtn, CallBtn;
    int i = 0;
    private static String TAG = "profile";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ProfName = findViewById(R.id.prof_name);
        ProfNumber = findViewById(R.id.prof_number);
        ProfPlace = findViewById(R.id.prof_place);
        DestBtn = findViewById(R.id.dest_btn);
        CallBtn = findViewById(R.id.call_btn);

        CallBtn.setEnabled(true);
        //ВЫВОД ДАННЫХ О ПОЛЬЗОВАТЕЛЕ ИЗ ПЕРВОГО АКТИВИТИ
        Intent intent = getIntent();
        ProfName.setText(intent.getStringExtra("Name") +  " " +
                intent.getStringExtra("Surname"));
        ProfNumber.setText(intent.getStringExtra("Phone number"));
        Log.d(TAG, "в профиле запущен onCreate");
    }

    //РЕАЛИЗАЦИЯ КНОПОК "ВЫБРАТЬ МАРШРУТ" И "ВЫЗВАТЬ ТАКСИ"
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.dest_btn:
                Log.d(TAG, "в профиле нажали DestBtn");
                Intent intent = new Intent(Profile.this, Destination.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.call_btn:
                Log.d(TAG, "в профиле нажали CallBtn");
                if(i == 1){
                    CallBtn.setEnabled(false);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "машина вызвана", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "выберите детали маршрута", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    //ПОЛУЧАНИЕ ДАННЫХ О МАРШРУТЕ ИЗ ТРЕТЬЕГО АКТИВИТИ И ВЫВОД ДАННЫХ О КОНЕЧНОЙ ТОЧКЕ
    protected void onActivityResult(int RequestCode, int ResultCode, Intent data) {
        super.onActivityResult(RequestCode, ResultCode, data);
        Log.d(TAG, "в профиле запущен onActivityResult");

        if(data == null) {
            return;
        }
        String city1 = data.getStringExtra("city1");
        String city2 = data.getStringExtra("city2");
        String street1 = data.getStringExtra("street1");
        String street2 = data.getStringExtra("street2");
        String house1 = data.getStringExtra("house1");
        String house2 = data.getStringExtra("house2");

        ProfPlace.setText("г." + city2 + ", ул." + street2 + ", дом " + house2);
        i = 1;
    }
}