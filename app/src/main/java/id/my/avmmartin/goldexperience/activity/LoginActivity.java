package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.utils.Constants;

public class LoginActivity extends AppCompatActivity {
    private GoldExperience mainApp;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        initComponents();
        loadDatas();
        setEvents();
    }

    private void initComponents() {
        mainApp = (GoldExperience) this.getApplication();
        etEmail = findViewById(R.id.login_et_email);
        etPassword = findViewById(R.id.login_et_password);
        btnLogin = findViewById(R.id.login_btn_login);
        btnRegister = findViewById(R.id.login_btn_register);
    }

    private void loadDatas() {
        etEmail.setText(getIntent().getStringExtra(Constants.INTENT_EMAIL));
    }

    private void setEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLoginOnClick(view);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegisterOnClick(view);
            }
        });
    }

    private void btnLoginOnClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.warning_email_filled, Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.warning_password_filled, Toast.LENGTH_SHORT).show();
        } else if (!mainApp.login(email, password)) {
            Toast.makeText(LoginActivity.this, R.string.warning_not_match_account, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, PlaceListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void btnRegisterOnClick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
