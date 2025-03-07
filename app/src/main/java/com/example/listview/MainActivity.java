package com.example.listview;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    private Button btnInsertar, btnBorrar, btnListar;


    private ArrayList<String> nombres;
    private ArrayList<Integer> imagenes;
    private DatabaseHelper dbHelper;

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
        btnInsertar = findViewById(R.id.btnInsertar);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnListar = findViewById(R.id.btnListar);


        dbHelper = new DatabaseHelper(this);


        nombres = new ArrayList<>();
        imagenes = new ArrayList<>();


        btnInsertar.setOnClickListener(v -> insertarDatos());
        btnBorrar.setOnClickListener(v -> borrarDatos());
        btnListar.setOnClickListener(v -> listarDatos());


        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Has pulsado: " + nombres.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void insertarDatos() {
        dbHelper.deleteAllPersonajes();


        dbHelper.insertPersonaje("Yone", R.drawable.icono_yone);
        dbHelper.insertPersonaje("Yasuo", R.drawable.icono_yasuo);
        dbHelper.insertPersonaje("Katarina", R.drawable.icono_katarina);
        dbHelper.insertPersonaje("Irelia", R.drawable.icono_irelia);
        dbHelper.insertPersonaje("Akali", R.drawable.icono_akali);
        dbHelper.insertPersonaje("Sylas", R.drawable.icono_sylas);
        dbHelper.insertPersonaje("Vladimir", R.drawable.icono_vladimir);
        dbHelper.insertPersonaje("Vex", R.drawable.icono_vex);

        Toast.makeText(this, "¡Datos insertados en la DB! ", Toast.LENGTH_SHORT).show();
    }


    private void borrarDatos() {
        dbHelper.deleteAllPersonajes();
        nombres.clear();
        imagenes.clear();
        listview1.setAdapter(null);
        Toast.makeText(this, "¡Datos eliminados! 🗑", Toast.LENGTH_SHORT).show();
    }


    private void listarDatos() {
        nombres.clear();
        ArrayList<Integer> imagenes = new ArrayList<>();

        Cursor cursor = dbHelper.getAllPersonajes();


        int nombreIndex = cursor.getColumnIndex(DatabaseHelper.KEY_NOMBRE);
        int imagenIndex = cursor.getColumnIndex(DatabaseHelper.KEY_IMAGEN);

        if (nombreIndex == -1 || imagenIndex == -1) {
            Toast.makeText(this, "Error: Columnas no encontradas", Toast.LENGTH_SHORT).show();
            return;
        }


        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(nombreIndex);
                int imagen = cursor.getInt(imagenIndex);

                nombres.add(nombre);
                imagenes.add(imagen);
            } while (cursor.moveToNext());

            Toast.makeText(this, "Datos cargados: " + nombres.size() + " elementos ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No hay datos en la DB ", Toast.LENGTH_SHORT).show();
        }
        cursor.close();


        if (!nombres.isEmpty()) {
            MyAdapter adapter = new MyAdapter(
                    this,
                    R.layout.list_item,
                    nombres,
                    imagenes
            );
            listview1.setAdapter(adapter);
        } else {
            listview1.setAdapter(null);
        }
    }
}