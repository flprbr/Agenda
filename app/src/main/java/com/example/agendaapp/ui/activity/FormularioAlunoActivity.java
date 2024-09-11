package com.example.agendaapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaapp.R;
import com.example.agendaapp.dao.AlunoDAO;
import com.example.agendaapp.model.Aluno;

import java.io.Serializable;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        Intent dados = getIntent();
        Aluno aluno = (Aluno) dados.getSerializableExtra("alunos");
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail()f);
    }



    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.activity_formaulario_aluno_botao_salvar);
        botaoSalvar.setOnClickListener(view -> {
            Aluno alunoCriado = criaAluno();
            salva(alunoCriado);
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formaulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formaulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formaulario_aluno_email);
    }

    private void salva(Aluno alunoCriado) {
        dao.salva(alunoCriado);
        finish();
    }

    private @NonNull Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        Aluno alunoCriado = new Aluno(nome, telefone, email);
        return alunoCriado;
    }
}