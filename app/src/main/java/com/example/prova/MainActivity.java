package com.example.prova;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listaPacientes;
    private Button btnCadastro;
    private ListView lvPacientes;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaPacientes = findViewById(R.id.lista_pacientes);
        btnCadastro = findViewById(R.id.btn_cadastrar);
        lvPacientes = findViewById(R.id.lista_pacientes);

        DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();

        String[] fields = new String[] {CreateDatabase.NOME, CreateDatabase.IDADE, CreateDatabase.MORTALIDADE};
        int[] ids = {R.id.lvnome, R.id.lvidade, R.id.lvmortalidade};

        adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.list_pacientes, cursor, fields, ids, 0);

        lvPacientes.setAdapter(adapter);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, cadastro.class));
            }
        });
    }
}
