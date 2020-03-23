package id.my.avmmartin.goldexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText et_email;
    private EditText et_password;
    private Button btn_login;
    private Button btn_register;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);

        init_components();
        set_events();

        String email = getIntent().getStringExtra(GoldExperience.INTENT_EMAIL);
        et_email.setText(email);
    }

    private void init_components() {
        et_email = findViewById(R.id.login_et_email);
        et_password = findViewById(R.id.login_et_password);
        btn_login = findViewById(R.id.login_btn_login);
        btn_register = findViewById(R.id.login_btn_register);
    }

    private void set_events() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_login_onclick(view);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_register_onclick(view);
            }
        });
    }

    private void btn_login_onclick(View view) {
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.warning_email_filled, Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(LoginActivity.this, R.string.warning_password_filled, Toast.LENGTH_SHORT).show();
        } else if (!((GoldExperience) this.getApplication()).login(email, password)) {
            Toast.makeText(LoginActivity.this, R.string.warning_not_match_account, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(LoginActivity.this, PlaceListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void btn_register_onclick(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
