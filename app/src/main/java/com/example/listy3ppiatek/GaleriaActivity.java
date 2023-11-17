package com.example.listy3ppiatek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class GaleriaActivity extends AppCompatActivity {
    private ImageView imageView;
    private Spinner spinner;
    private Button butDalej, butWstecz, butWyswietl;
    private RadioButton rB1,rB2,rB3;
    private int aktualny = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        imageView = findViewById(R.id.imageView);
        butDalej = findViewById(R.id.buttonDalej);
        butWstecz = findViewById(R.id.buttonWstecz);
        butWyswietl = findViewById(R.id.buttonWyswietl);


        rB1 = findViewById(R.id.radioButton1);
        rB2 = findViewById(R.id.radioButton2);
        rB3 = findViewById(R.id.radioButton3);



        int obrazki [] =new int[]{
                R.drawable.pobrane,
                R.drawable.pobrane2,
                R.drawable.pobrane3
        };

        butWstecz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualny--;
                        if(aktualny < 0)
                            aktualny = obrazki.length-1;
                        imageView.setImageResource(obrazki[aktualny]);
                    }
                }
        );

        butDalej.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualny++;
                        if(aktualny > obrazki.length-1)
                            aktualny = 0;
                        imageView.setImageResource(obrazki[aktualny]);
                    }
                }
        );

        butWyswietl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioGroup radioGrupa = findViewById(R.id.radioGrupa);
                        int idRadioKlikniety = radioGrupa.getCheckedRadioButtonId();
                        if(idRadioKlikniety == R.id.radioButton1){
                            aktualny = 0;
                        }else if(idRadioKlikniety == R.id.radioButton2){
                            aktualny = 1;
                        }else{
                            aktualny = 2;
                        }
                        imageView.setImageResource(obrazki[aktualny]);
                    }
                }
        );


        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        int ktory =i;
                        imageView.setImageResource(obrazki[ktory]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

    }
}