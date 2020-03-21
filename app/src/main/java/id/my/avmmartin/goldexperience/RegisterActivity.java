package id.my.avmmartin.goldexperience;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class RegisterActivity extends ProfileForm {
    private CheckBox cb_tnc;
    private Button btn_login;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        super.onCreate(savedInstanceState);

        init_components();
        set_events();
    }

    private void init_components() {
        cb_tnc = findViewById(R.id.register_cb_tnc);
        btn_login = findViewById(R.id.register_btn_login);
        btn_register = findViewById(R.id.register_btn_register);
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
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void btn_register_onclick(View view) {
        boolean tnc = cb_tnc.isChecked();

        try {
            String email = super.get_email();
            UserProfile profile = super.get_profile();

            if (!tnc) {
                throw new Exception("Please check the Terms and Condition");
            }

            // TODO: welcome message via SMS
            main_app.register_user(email, profile);
            main_app.login(email, profile.password);

            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
