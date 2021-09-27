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
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class SensorTemp extends AppCompatActivity implements SensorEventListener {
    private TextView textview;
    private SensorManager sensorManager;
    private Sensor tempSensor;
    private  Boolean TemperaturaSensorValida;

// Armazenamento

    Button btnEnviar, btnSalvar;
    EditText EditTextAr;
    TextView tvEnviar;
    String filename = "";
    String filepath = "";
    String filecontent = "";





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


        /*Armazenamento*/
        btnSalvar = findViewById(R.id.ButtonSalvar);
        btnEnviar = findViewById(R.id.ButtonEnviar);
        EditTextAr = findViewById(R.id.EditTextAr);
        tvEnviar = findViewById(R.id.tvEnviar);
        filename = "MeuArquivo.txt";
        filepath = "MeuArquivoDir";
        if(!isExternalStorageAvailableForRW()){
            btnSalvar.setEnabled(false);
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvEnviar.setText("");
                filecontent = EditTextAr.getText().toString().trim();
                if(!filecontent.equals("")){
                    File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(myExternalFile);
                        fos.write(filecontent.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    EditTextAr.setText("");
                    Toast.makeText(SensorTemp.this, "As informações foram salvas no cartão SD com sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SensorTemp.this, "O campo de texto não pode ser vazio!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileReader fr = null;
                File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    fr = new FileReader(myExternalFile);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    while(line != null){
                        stringBuilder.append(line).append('\n');
                        line = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    String fileContents = "File contents\n" + stringBuilder.toString();
                    tvEnviar.setText(fileContents);
                }
            }
        });


    }

    private boolean isExternalStorageAvailableForRW() {
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
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