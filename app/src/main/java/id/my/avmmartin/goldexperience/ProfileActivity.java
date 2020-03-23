package id.my.avmmartin.goldexperience;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends ProfileForm {
    private Button btn_update;
    private Button btn_logout;
    private UserProfile user;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        init_components();
        set_events();
        getSupportActionBar().setTitle(R.string.title_profile);
    }

    @Override protected void onStart() {
        super.onStart();
        super.load_data(user);
    }

    private void init_components() {
        btn_update = findViewById(R.id.profile_btn_update);
        btn_logout = findViewById(R.id.profile_btn_logout);
        user = main_app.get_profile();
    }

    private void set_events() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_update_onclick(view);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_logout_onclick(view);
            }
        });
    }

    private void btn_update_onclick(View view) {
        try {
            user = super.get_user_profile();

            main_app.update_user(user);

            Toast.makeText(ProfileActivity.this, R.string.success_update, Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            user = main_app.get_profile();
        }
    }

    private void btn_logout_onclick(View view) {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(GoldExperience.INTENT_EMAIL, user.email);

        main_app.logout();
        startActivity(intent);
    }
}
