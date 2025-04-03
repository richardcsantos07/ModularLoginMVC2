package com.example.modularloginmvc.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.modularloginmvc.R;
import com.example.modularloginmvc.controller.UsuarioController;
import com.example.modularloginmvc.model.Usuario;

public class MainActivity extends AppCompatActivity {
    EditText email, senha;
    Button criar, entrar;
    UsuarioController controller;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();
        controller = new UsuarioController(getApplicationContext());

        // Verificar se está recebendo dados de um novo cadastro
        verificarNovoUsuario();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    String userEmail = email.getText().toString();
                    String userSenha = senha.getText().toString();

                    boolean isCheckUser = controller.usuarioeSenha(userEmail, userSenha);

                    if (!isCheckUser) {
                        Toast.makeText(MainActivity.this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent home = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish(); // Fechar a activity de login
                    }
                }
            }
        });

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void verificarNovoUsuario() {
        Intent intent = getIntent();
        if (intent.hasExtra("NOVO_USUARIO") && intent.getBooleanExtra("NOVO_USUARIO", false)) {
            String userEmail = intent.getStringExtra("EMAIL");
            String userSenha = intent.getStringExtra("SENHA");

            if (userEmail != null && userSenha != null) {
                // Preencher os campos automaticamente
                email.setText(userEmail);
                senha.setText(userSenha);

                // Opcional: fazer login automático
                boolean isCheckUser = controller.usuarioeSenha(userEmail, userSenha);
                if (isCheckUser) {
                    Toast.makeText(MainActivity.this, "Bem-vindo! Usuário autenticado com sucesso.", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        }
    }

    private void initComponents() {
        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_senha);
        criar = findViewById(R.id.btn_cadastrar);
        entrar = findViewById(R.id.btn_entrar);
    }

    private void mensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    private boolean validaCampos() {
        boolean camposValidados = true;
        if (email.getText().toString().equals("")) {
            camposValidados = false;
            email.setError("Digite o Email!");
            email.requestFocus();
        }

        if (senha.getText().toString().equals("")) {
            camposValidados = false;
            senha.setError("Digite a Senha!");
            senha.requestFocus();
        }
        return camposValidados;
    }
}