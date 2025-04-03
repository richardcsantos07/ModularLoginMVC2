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
import com.example.modularloginmvc.datamodel.UsuarioDataModel;

public class MainActivity extends AppCompatActivity {
    EditText email, senha;
    Button criar, entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponents();

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean camposValidados = validaCampos();

                if (camposValidados){
                    Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                    startActivity(intent);
                }else{
                    mensagem("Digite todos os campos!");
                }
            }
        });

        //UsuarioDataModel.criarTabela();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initComponents() {
        email  = findViewById(R.id.edt_email);
        senha  = findViewById(R.id.edt_senha);
        criar  = findViewById(R.id.btn_cadastrar);
        entrar = findViewById(R.id.btn_entrar);

    }

    private void mensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    private boolean validaCampos(){
        boolean camposValidados = true;
        if(email.getText().toString().equals("")){
            camposValidados = false;
            email.setError("Digite o Email!");
            email.requestFocus();
        }

        if(senha.getText().toString().equals("")){
            camposValidados = false;
            senha.setError("Digite a Senha!");
            senha.requestFocus();
        }
        return camposValidados;
    }

}