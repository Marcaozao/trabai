package com.example.trabalhoaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView nome_view, email_view, idade_view, disc_view, nota1_view, nota2_view, res_view;
    private EditText nome_edit, email_edit, idade_edit, disc_edit, nota1_edit, nota2_edit;
    private Button enviar_bt, limpar_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Views
        nome_view = findViewById(R.id.nome_view);
        email_view = findViewById(R.id.email_view);
        idade_view = findViewById(R.id.idade_view);
        disc_view = findViewById(R.id.disc_view);
        nota1_view = findViewById(R.id.nota1_view);
        nota2_view = findViewById(R.id.nota2_view);
        res_view = findViewById(R.id.res_view);

        //Edits
        nome_edit = findViewById(R.id.nome_edit);
        email_edit = findViewById(R.id.email_edit);
        idade_edit = findViewById(R.id.idade_edit);
        disc_edit = findViewById(R.id.disc_edit);
        nota1_edit = findViewById(R.id.nota1_edit);
        nota2_edit = findViewById(R.id.nota2_edit);

        //Buttons
        enviar_bt = findViewById(R.id.enviar_bt);
        limpar_bt = findViewById(R.id.limpar_bt);

        enviar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                res_view.setText("");

                if(checar_nome() && checar_email() && checar_idade() && checar_disc() && checar_notas())
                {

                    res_view.setText(textualizar());

                }

            }
        });

        limpar_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome_edit.setText("");
                email_edit.setText("");
                idade_edit.setText("");
                disc_edit.setText("");
                nota1_edit.setText("");
                nota2_edit.setText("");
                res_view.setText("");

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    boolean checar_nome()
    {

        if(nome_edit.getText().toString().isEmpty())
        {

            res_view.setText("O campo de nome deve ser preenchido\n");

            return false;

        }
        else
        {

            return true;

        }

    }

    boolean checar_email()
    {

        if(email_edit.getText().toString().contains("@"))
        {

            return true;

        }
        else
        {

            res_view.setText(res_view.getText().toString() + "O campo de email deve conter uma endereço de email válido");

            return false;

        }

    }

    boolean checar_idade()
    {

        if(!(idade_edit.getText().toString().isEmpty()) && !(contem_string(idade_edit.getText().toString())) && Integer.parseInt(idade_edit.getText().toString()) >= 0)
        {

            return true;

        }
        else
        {

            res_view.setText(res_view.getText().toString() + "O campo de idade deve ser numérico e positivo\n");

            return false;

        }

    }

    boolean checar_disc()
    {

        if(disc_edit.getText().toString().isEmpty())
        {

            res_view.setText("O campo de disciplina deve ser preenchido\n");

            return false;

        }
        else
        {

            return true;

        }

    }

    boolean checar_notas()
    {

        if((!(nota1_edit.getText().toString().isEmpty()) && (!(contem_string(nota1_edit.getText().toString()))) && (Double.parseDouble(nota1_edit.getText().toString()) >= 0 && Double.parseDouble(nota1_edit.getText().toString()) <= 10)) && (!(nota2_edit.getText().toString().isEmpty()) && (!(contem_string(nota2_edit.getText().toString()))) && (Double.parseDouble(nota2_edit.getText().toString()) >= 0 && Double.parseDouble(nota2_edit.getText().toString()) <= 10)))
        {

            return true;

        }
        else
        {

            res_view.setText("O campo de notas deve conter um valor entre 0 e 10");

            return false;

        }

    }

    public boolean contem_string(String str) {

        return !str.matches("\\d+");

    }

    double calcular_media()
    {

        double n1 = Double.parseDouble(nota1_edit.getText().toString());
        double n2 = Double.parseDouble(nota2_edit.getText().toString());

        double media = (n1 + n2)/2;

        return media;

    }

    String verificar_situacao()
    {

        if(calcular_media() >= 6)
        {

            return "Aprovado";

        }
        else
        {

            return "Reprovado";

        }

    }

    String textualizar()
    {

        String nome = "Nome: " + nome_edit.getText().toString();
        String email = "Email: " + email_edit.getText().toString();
        String idade = "Idade: " + idade_edit.getText().toString();
        String disc = "Disciplina: " + disc_edit.getText().toString();
        String notas = "Notas do 1º e 2º Bimestres: " + nota1_edit.getText().toString() + " e " + nota2_edit.getText().toString();
        String media = "Média: " + calcular_media();

        return nome + "\n" + email + "\n" + idade + "\n" + disc + "\n" + notas + "\n" + media + "\n" + verificar_situacao();

    }

}