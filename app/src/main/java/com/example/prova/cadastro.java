package com.example.prova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class cadastro extends AppCompatActivity {

    private Button btn_add;
    private EditText input_nome, input_idade, input_leucocito, input_glicemia, input_ast, input_ldh;
    private CheckBox isLitiase;
    private TextView scoreText, mortalidadeText, labelScore, labelMortalidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        input_nome = findViewById(R.id.input_nome);
        input_idade = findViewById(R.id.input_idade);
        input_leucocito = findViewById(R.id.input_leucocito);
        input_glicemia = findViewById(R.id.input_glicemia);
        input_ast = findViewById(R.id.input_ast);
        input_ldh = findViewById(R.id.input_ldh);
        btn_add = findViewById(R.id.btn_add);
        isLitiase = findViewById(R.id.litiase);
        scoreText = findViewById(R.id.scoreText);
        mortalidadeText = findViewById(R.id.mortalidadeText);
        labelMortalidade = findViewById(R.id.labelMortalidade);
        labelScore = findViewById(R.id.labelPontuacao);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLitiase.isChecked()) {
                    withLitiase();
                } else {
                    withoutLitiase();
                }
            }
        });
    }

    public void withoutLitiase() {
        int score = 0;
        if (Integer.parseInt(input_idade.getText().toString()) > 55) {
            score++;
        }

        if (Integer.parseInt(input_leucocito.getText().toString()) > 16000) {
            score++;
        }

        if (Integer.parseInt(input_idade.getText().toString()) > 11) {
            score++;
        }

        if (Integer.parseInt(input_ast.getText().toString()) > 250) {
            score++;
        }

        if (Integer.parseInt(input_ldh.getText().toString()) > 350) {
            score++;
        }

        if (score >= 0 && score <= 2) {
            mortalidadeText.setText("2%");
        } else if (score == 3 || score == 4) {
            mortalidadeText.setText("15%");
        } else if (score == 5 || score == 6) {
            mortalidadeText.setText("40%");
        } else if (score == 7 || score == 8) {
            mortalidadeText.setText("100%");
        }

        labelScore.setVisibility(View.VISIBLE);
        labelMortalidade.setVisibility(View.VISIBLE);
        mortalidadeText.setVisibility(View.VISIBLE);
        scoreText.setText(score + "");
        scoreText.setVisibility(View.VISIBLE);
    }

    public void withLitiase() {
        int score = 0;
        if (Integer.parseInt(input_idade.getText().toString()) > 70) {
            score++;
        }

        if (Integer.parseInt(input_leucocito.getText().toString()) > 18000) {
            score++;
        }

        if (Double.parseDouble(input_idade.getText().toString()) > 12.2) {
            score++;
        }

        if (Integer.parseInt(input_ast.getText().toString()) > 250) {
            score++;
        }

        if (Integer.parseInt(input_ldh.getText().toString()) > 400) {
            score++;
        }

        if (score >= 0 && score <= 2) {
            mortalidadeText.setText("2%");
        } else if (score == 3 || score == 4) {
            mortalidadeText.setText("15%");
        } else if (score == 5 || score == 6) {
            mortalidadeText.setText("40%");
        } else if (score == 7 || score == 8) {
            mortalidadeText.setText("100%");
        }

        labelScore.setVisibility(View.VISIBLE);
        labelMortalidade.setVisibility(View.VISIBLE);
        mortalidadeText.setVisibility(View.VISIBLE);
        scoreText.setText(score + "");
        scoreText.setVisibility(View.VISIBLE);

        DAL dal = new DAL(cadastro.this);
        Paciente p = new Paciente();
        p.setNome(input_nome.getText().toString());
        p.setIdade(Integer.parseInt(input_idade.getText().toString()));
        p.setMortalidade(mortalidadeText.getText().toString());
        dal.insert(p.getNome(), p.getIdade(), p.getMortalidade());
    }
}
