package id.my.avmmartin.goldexperience.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.exception.EmptyEntryException;
import id.my.avmmartin.goldexperience.exception.InvalidEntryException;
import id.my.avmmartin.goldexperience.utils.Constants;

public class RegisterActivity extends ProfileForm {
    private CheckBox cbTNC;
    private Button btnLogin;
    private Button btnRegister;

    // event activity

    private void btnLoginOnClick() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void btnRegisterOnClick() {
        boolean tnc = cbTNC.isChecked();

        try {
            User user = super.getUserFromEntry();

            if (!tnc) {
                throw new EmptyEntryException(R.string.warning_tnc_checked);
            }

            mainApp.getDataManager().register(user);

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Constants.INTENT_EMAIL, user.getEmail());
            startActivity(intent);

        } catch (EmptyEntryException e) {
            Toast.makeText(RegisterActivity.this, getString(e.getErrorId()), Toast.LENGTH_SHORT).show();
        } catch (InvalidEntryException e) {
            Toast.makeText(RegisterActivity.this, getString(e.getErrorId()), Toast.LENGTH_SHORT).show();
        }
    }

    // overridden method

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        cbTNC = findViewById(R.id.register_cb_tnc);
        btnLogin = findViewById(R.id.register_btn_login);
        btnRegister = findViewById(R.id.register_btn_register);
    }

    @Override
    protected void setEvents() {
        super.setEvents();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLoginOnClick();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegisterOnClick();
            }
        });
    }
}
