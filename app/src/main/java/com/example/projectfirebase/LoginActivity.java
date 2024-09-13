package com.example.projectfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.text.InputType;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Declaração dos componentes
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private ImageButton buttonTogglePasswordVisibility;
    private TextView textViewRegister; // Adicione o TextView para o cadastro
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializando as views (campos e botões)
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonTogglePasswordVisibility = findViewById(R.id.buttonTogglePasswordVisibility);
        textViewRegister = findViewById(R.id.textViewRegister); // Inicialize o TextView

        // Listener para o botão de alternar visibilidade da senha
        buttonTogglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;

                if (isPasswordVisible) {
                    // Mostra a senha
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    buttonTogglePasswordVisibility.setImageResource(R.drawable.visibilityon2); // Ícone de olho aberto
                } else {
                    // Oculta a senha
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    buttonTogglePasswordVisibility.setImageResource(R.drawable.visibility2); // Ícone de olho fechado
                }

                // Mover o cursor para o final do texto
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        });

        // Listener para o botão de login (adapte para sua lógica de autenticação)
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pega o texto dos campos de email e senha
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // Simples validação de campos (adapte conforme a necessidade)
                if (!email.isEmpty() && !password.isEmpty()) {
                    // Logica de autenticação aqui
                    Toast.makeText(LoginActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                } else {
                    // Exibe mensagem de erro se os campos estiverem vazios
                    editTextEmail.setError("Campo obrigatório");
                    editTextPassword.setError("Campo obrigatório");
                }
            }
        });

        // Listener para o texto de cadastro
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecionar para a RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
