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

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome, editEmail, editSenha;
    private Button btnSalvar;
    private UsuarioController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);

        // Inicializa os componentes
        initComponents();

        // Inicializa o controller
        controller = new UsuarioController(this);

        // Configura o listener do botão salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    cadastrarUsuario();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initComponents() {
        editNome = findViewById(R.id.edt_nome);
        editEmail = findViewById(R.id.edt_email);
        editSenha = findViewById(R.id.edt_senha);
        btnSalvar = findViewById(R.id.btn_salvar);
    }

    private boolean validaCampos() {
        boolean camposValidados = true;

        if (editNome.getText().toString().equals("")) {
            camposValidados = false;
            editNome.setError("Digite o Nome!");
            editNome.requestFocus();
        }

        if (editEmail.getText().toString().equals("")) {
            camposValidados = false;
            editEmail.setError("Digite o Email!");
            editEmail.requestFocus();
        }

        if (editSenha.getText().toString().equals("")) {
            camposValidados = false;
            editSenha.setError("Digite a Senha!");
            editSenha.requestFocus();
        }

        return camposValidados;
    }

    private void cadastrarUsuario() {
        String nome = editNome.getText().toString();
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        // Verificar se o email já existe no banco de dados
        if (controller.usuario(email)) {
            Toast.makeText(this, "Este email já está cadastrado!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criar objeto Usuario
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);

        // Incluir usuário no banco de dados
        boolean sucesso = controller.incluir(novoUsuario);

        if (sucesso) {
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            // Navegar para a MainActivity e passar os dados
            Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
            intent.putExtra("EMAIL", email);
            intent.putExtra("SENHA", senha);
            intent.putExtra("NOVO_USUARIO", true);
            startActivity(intent);
            finish(); // Fechar activity atual
        } else {
            Toast.makeText(this, "Erro ao cadastrar usuário. Tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }
}