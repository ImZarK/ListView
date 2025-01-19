package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

private ListView listview1;
private ArrayList<String> nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
listview1 = findViewById(R.id.listview1);

nombres = new ArrayList<String>();
nombres.add("Yone");
nombres.add("Yasuo");
nombres.add("Katarina");
nombres.add("Irelia");
nombres.add("Akali");
nombres.add("Sylas");
nombres.add("Vladimir");
nombres.add("Vex");



        ArrayList<Integer> imagenes = new ArrayList<>();
        imagenes.add(R.drawable.icono_yone);
        imagenes.add(R.drawable.icono_yasuo);
        imagenes.add(R.drawable.icono_katarina);
        imagenes.add(R.drawable.icono_irelia);
        imagenes.add(R.drawable.icono_akali);
        imagenes.add(R.drawable.icono_sylas);
        imagenes.add(R.drawable.icono_vladimir);
        imagenes.add(R.drawable.icono_vex);
        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.list_item, nombres, imagenes);
        listview1.setAdapter(adapter);

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has pulsado: " + nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }
}