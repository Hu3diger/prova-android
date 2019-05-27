package com.example.prova;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listaPacientes;
    private ListView lvPacientes;
    private SimpleCursorAdapter adapter;
    private FloatingActionButton bbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPacientes = findViewById(R.id.lista_pacientes);
        lvPacientes = findViewById(R.id.lista_pacientes);
        bbtn = findViewById(R.id.floatingButton);


        DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();

        String[] fields = new String[]{CreateDatabase.NOME, CreateDatabase.IDADE, CreateDatabase.MORTALIDADE};
        int[] ids = {R.id.lvName, R.id.lvIdade, R.id.lvMortalidade};

        adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.list_pacientes, cursor, fields, ids, 0);

        lvPacientes.setAdapter(adapter);

        bbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cadastro.class));
            }
        });
    }
}
