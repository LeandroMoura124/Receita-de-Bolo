package com.example.receitabolo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SensorTemp extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private  Boolean TemperaturaSensorValida;

    private TextView textview2;
    private EditText editText2;
    private Button applyTextButton;
    private Button saveButton;
    private Switch switch1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private  String text;
    private Boolean switchOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_temp);

        // seta pra sair da activity sensor
        View setaInfo2 = findViewById(R.id.setaInfo2);

        setaInfo2.setOnClickListener((View.OnClickListener) v-> {
            Intent intent = new Intent(getApplicationContext(), InformacoesCakes.class);
            startActivity(intent);
        });

        /*SENSOR DE TEMPERATURA*/
        textview = findViewById(R.id.textsensor);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null){

            tempSensor = sensorManager.getDefaultSensor((Sensor.TYPE_AMBIENT_TEMPERATURE));
            TemperaturaSensorValida = true;
        }else{
            textview.setText("Temperatura do sensor inválida");
            TemperaturaSensorValida = false;
        }


        // Armazenamento
        textview2 = (TextView) findViewById(R.id.textviewAr);
        editText2 = (EditText) findViewById(R.id.edittextAr);
        applyTextButton = (Button) findViewById(R.id.enviar_buttonAr);
        saveButton = (Button) findViewById(R.id.ButtonSaveAr);
        switch1 = (Switch) findViewById(R.id.switchAr);

        applyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview2.setText(editText2.getText().toString());
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

            }
        });
        loadData();
        updateViews();

    }

    public  void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor  = sharedPreferences.edit();

        editor.putString(TEXT, textview2.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();
        Toast.makeText(this, "Dado salvo", Toast.LENGTH_SHORT).show();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text  = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }
    public  void updateViews(){
        textview2.setText(text);
        switch1.setChecked(switchOnOff);
    }




    //  sensorTemp
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