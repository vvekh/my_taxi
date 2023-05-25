package com.example.my_taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText MainName, MainSurname, MainNumber;
    Button RegistrBtn;
    final String sMainName = "saved_name";
    final String sMainSurname = "saved_surname";
    final String sMainNumber = "saved_number";
    private static String TAG = "main";
    SharedPreferences Pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainName = findViewById(R.id.main_name);
        MainSurname = findViewById(R.id.main_surname);
        MainNumber = findViewById(R.id.main_number);
        RegistrBtn = findViewById(R.id.registr_btn);

        Log.d(TAG, "в мэйне запущен onCreate");
        LoadText();
    }

    //РЕАЛИЗАЦИЯ РЕГИСТРАЦИИ (СОХРАНЕНИЕ ДАННЫХ, ВЫЗОВ ВТОРОГО АКТИВИТИ И ПЕРЕДАЧА ДАННЫХ)
    public void onRegistrClick(View view) {
        SaveText();

        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);

        intent.putExtra("Name", MainName.getText().toString());
        intent.putExtra("Surname", MainSurname.getText().toString());
        intent.putExtra("Phone number", MainNumber.getText().toString());
        startActivity(intent);
        Log.d(TAG, "в мэйне запущен onRegistrClick");
    }

    //СОХРАНЕНИЕ ДАННЫХ О ПОЛЬЗОВАТЕЛЕ ПРИ РЕГИСТРАЦИИ
    private void SaveText() {
        Pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = Pref.edit();
        editor.putString(sMainName, MainName.getText().toString());
        editor.putString(sMainSurname, MainSurname.getText().toString());
        editor.putString(sMainNumber, MainNumber.getText().toString());

        editor.commit();
        Toast.makeText(this, "сохранено", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "в мэйне запущен SaveText");
    }

    //ЗАГРУЗКА ДАННЫХ ПОЛЬЗОВАТЕЛЯ ПРИ ПОВТОРНОМ ОТКРЫТИИ ПРИЛОЖЕНИЯ
    private void LoadText() {
        Pref = getPreferences(MODE_PRIVATE);
        if(Pref.getString(sMainName, "") != "") {
            String sname = Pref.getString(sMainName,"");
            String ssurname = Pref.getString(sMainSurname,"");
            String snumber = Pref.getString(sMainNumber,"");

            MainName.setText(sname);
            MainSurname.setText(ssurname);
            MainNumber.setText(snumber);

            RegistrBtn.setText("Войти");
        }
    }
}