package com.example.projectfirebase;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextConfirmPassword, editTextBirthDate;
    private Button buttonRegister;
    private Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializando views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Exibir o DatePicker para selecionar a data de nascimento
        editTextBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Listener do botão de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();

                if (validateInputs(email, password, confirmPassword)) {
                    if (isValidAge()) {
                        Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Você deve ter 18 anos ou mais para se cadastrar.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                RegisterActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        editTextBirthDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private boolean validateInputs(String email, String password, String confirmPassword) {
        if (email.isEmpty()) {
            editTextEmail.setError("Campo obrigatório");
            return false;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Campo obrigatório");
            return false;
        }
        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Campo obrigatório");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("As senhas não coincidem");
            return false;
        }
        return true;
    }

    private boolean isValidAge() {
        if (selectedDate == null) {
            Toast.makeText(this, "Por favor, selecione sua data de nascimento", Toast.LENGTH_SHORT).show();
            return false;
        }

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - selectedDate.get(Calendar.YEAR);

        // Verifica se já fez aniversário este ano
        if (today.get(Calendar.DAY_OF_YEAR) < selectedDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age >= 18;
    }
}
