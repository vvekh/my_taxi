package com.example.my_taxi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.PointOfView;
import com.yandex.mapkit.mapview.MapView;

public class Mapkit extends AppCompatActivity {
    MapView MyMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("9e9cb67c-eee0-441a-b8a3-e6baf7aef7ad");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_mapkit);

        MyMap = findViewById(R.id.mapview);
        MyMap.getMap().move(
                new CameraPosition(new Point(59.939090, 30.315831), 11.0f, 0.0f,0.0f),
                new Animation(Animation.Type.SMOOTH, 3), null);
    }

    @Override
    protected void onStop() {
        MyMap.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        MyMap.onStart();
    }
}