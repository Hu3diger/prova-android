package com.example.prova;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Cadastro extends AppCompatActivity {

    private Button btn_add;
    private EditText inputNome, inputIdade, inputLeucocito, inputGlicemia, inputAST, inputLDH;
    private CheckBox hasLitiase;
    private TextView scoreText, mortalidadeText, labelScore, labelMortalidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inputNome = findViewById(R.id.inputNome);
        inputIdade = findViewById(R.id.inputIdade);
        inputLeucocito = findViewById(R.id.inputLeucocito);
        inputGlicemia = findViewById(R.id.inputGlicemia);
        inputAST = findViewById(R.id.inputAST);
        inputLDH = findViewById(R.id.inputLDH);
        btn_add = findViewById(R.id.btn_add);
        hasLitiase = findViewById(R.id.litiase);
        scoreText = findViewById(R.id.scoreText);
        mortalidadeText = findViewById(R.id.mortalidadeText);
        labelMortalidade = findViewById(R.id.labelMortalidade);
        labelScore = findViewById(R.id.labelPontuacao);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasLitiase.isChecked()) {
                    withLitiase();
                } else {
                    withoutLitiase();
                }
            }
        });
    }

    public void withoutLitiase() {
        int score = 0;
        if (Integer.parseInt(inputIdade.getText().toString()) > 55) {
            score++;
        }

        if (Integer.parseInt(inputLeucocito.getText().toString()) > 16000) {
            score++;
        }

        if (Integer.parseInt(inputIdade.getText().toString()) > 11) {
            score++;
        }

        if (Integer.parseInt(inputAST.getText().toString()) > 250) {
            score++;
        }

        if (Integer.parseInt(inputLDH.getText().toString()) > 350) {
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
        if (Integer.parseInt(inputIdade.getText().toString()) > 70) {
            score++;
        }

        if (Integer.parseInt(inputLeucocito.getText().toString()) > 18000) {
            score++;
        }

        if (Double.parseDouble(inputIdade.getText().toString()) > 12.2) {
            score++;
        }

        if (Integer.parseInt(inputAST.getText().toString()) > 250) {
            score++;
        }

        if (Integer.parseInt(inputLDH.getText().toString()) > 400) {
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

        DAL dal = new DAL(Cadastro.this);
        String nome = inputNome.getText().toString();
        int idade = Integer.parseInt(inputIdade.getText().toString());
        String mortalidade = mortalidadeText.getText().toString();
        int leucocitos = Integer.parseInt(inputGlicemia.getText().toString());
        int glicemia = Integer.parseInt(inputGlicemia.getText().toString());
        int ast = Integer.parseInt(inputAST.getText().toString());
        int ldh = Integer.parseInt(inputLDH.getText().toString());
        int haslitiase = hasLitiase.isChecked() ? 1 : 0;

        dal.insert(nome, idade, mortalidade, leucocitos, glicemia, ast, ldh, haslitiase);
    }
}
