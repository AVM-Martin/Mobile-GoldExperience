package id.my.avmmartin.goldexperience.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.utils.Constants;

public class RegisterActivity extends ProfileForm {
    private CheckBox cbTNC;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        initComponents();
        setEvents();
    }

    private void initComponents() {
        cbTNC = findViewById(R.id.register_cb_tnc);
        btnLogin = findViewById(R.id.register_btn_login);
        btnRegister = findViewById(R.id.register_btn_register);
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
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void btnRegisterOnClick(View view) {
        boolean tnc = cbTNC.isChecked();

        try {
            User user = super.getUser();

            if (!tnc) {
                throw new Exception(getString(R.string.warning_tnc_checked));
            }

            // TODO: welcome message via SMS
            mainApp.registerUser(user);

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Constants.INTENT_EMAIL, user.getEmail());
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
