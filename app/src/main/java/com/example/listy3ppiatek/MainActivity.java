package com.example.listy3ppiatek;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewMysli;
    private ArrayList<String> mysli = new ArrayList<>();
    private Button button;
    private EditText editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mysli.add("Kto jada ostatki ten tłusty i gładki");
        mysli.add("Lepszy rydz niż nic");
        mysli.add("Miej odwagę żyć, umrzeć potrafi każdy");
        mysli.add("Nie nauczysz się pływać, nie wchodząc do wody");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                mysli
        );

        listViewMysli = findViewById(R.id.listView2);
        listViewMysli.setAdapter(adapter);

        button = findViewById(R.id.button1);
        editText = findViewById(R.id.editTextMysl);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!editText.getText().toString().equals("🐢") && !editText.getText().toString().equals("")){
                            String mysl = editText.getText().toString();
                            mysli.add(mysl);
                            adapter.notifyDataSetChanged();
                            editText.setText("");
                        }
                    }
                }
        );
        listViewMysli.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mysli.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                }
        );

    }


}