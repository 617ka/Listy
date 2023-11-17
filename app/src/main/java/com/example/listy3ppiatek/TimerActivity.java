package com.example.listy3ppiatek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TimerActivity extends AppCompatActivity {
    private Button butStart, butStop, butReset, butZapisz;
    private TextView textViewCzas;
    private ListView listViewCzasy;
    private int sekundy = 0;
    private boolean czyDziala = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        butStart = findViewById(R.id.buttonStart);
        butStop = findViewById(R.id.buttonStop);
        butReset = findViewById(R.id.buttonReset);
        butZapisz = findViewById(R.id.buttonZapisz);
        textViewCzas = findViewById(R.id.textViewCzas);
        listViewCzasy = findViewById(R.id.listViewCzasy);

        if(savedInstanceState != null){
            sekundy = savedInstanceState.getInt("SEKUNDY",0);
        }

        liczCzas();


        butStart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyDziala = true;
                    }
                }
        );


        butStop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyDziala = false;
                    }
                }
        );


        butReset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sekundy=0;
                        wyswietlCzas(sekundy);
                    }
                }
        );


        ArrayList<String> zapisaneCzasy = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                zapisaneCzasy
        );

        listViewCzasy.setAdapter(adapter);

        butZapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //String czas = textViewCzas.getText().toString();
                        zapisaneCzasy.add(wyswietlCzas(sekundy));
                        adapter.notifyDataSetChanged();
                    }
                }
        );

        listViewCzasy.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        zapisaneCzasy.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                }
        );


    }


    private void liczCzas(){
            Handler handler = new Handler();
            handler.post(
                    new Runnable() {
                        @Override
                        public void run() {
                            // co powtarzamy
                            if(czyDziala)
                                sekundy++;
                            //wyswietlanie czasu
                            wyswietlCzas(sekundy);
                            handler.postDelayed(this,1000);
                        }
                    }
            );
    }


    private String wyswietlCzas(int sek){
        int s = sek%60;
        int m = (sek/60)%60;
        int h = sek/3600;

        String czas = String.format("%02d:%02d:%02d",h,m,s);

        textViewCzas.setText(String.format("%02d:%02d:%02d",h,m,s));

        return czas;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
            outState.putInt("SEKUNDY",sekundy);

    }
}