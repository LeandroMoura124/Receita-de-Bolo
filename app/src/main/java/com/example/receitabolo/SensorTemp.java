package com.example.receitabolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorTemp extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private  Boolean TemperaturaSensorValida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_temp);


        textview = findViewById(R.id.textsensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){

            tempSensor = sensorManager.getDefaultSensor((Sensor.TYPE_AMBIENT_TEMPERATURE));
            TemperaturaSensorValida = true;
        }else{
            textview.setText("Temperatura do sensor inválida");
            TemperaturaSensorValida = false;
        }

    }


   @Override
   public  void onSensorChanged(SensorEvent sensorEvent){
        textview.setText(sensorEvent.values[0]+"º C");
   }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(TemperaturaSensorValida){
            sensorManager.registerListener(this,tempSensor, sensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (TemperaturaSensorValida){
            sensorManager.unregisterListener(this);
        }
    }
}