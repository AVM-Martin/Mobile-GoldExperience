package id.my.avmmartin.goldexperience.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.User;
import id.my.avmmartin.goldexperience.utils.Constants;

public class ProfileActivity extends ProfileForm {
    private Button btnUpdate;
    private Button btnLogout;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        super.onCreate(savedInstanceState);

        initComponents();
        setEvents();
        getSupportActionBar().setTitle(R.string.title_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        super.loadData(user);
    }

    private void initComponents() {
        btnUpdate = findViewById(R.id.profile_btn_update);
        btnLogout = findViewById(R.id.profile_btn_logout);
        user = mainApp.getProfile();
    }

    private void setEvents() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpdateOnClick(view);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogoutOnClick(view);
            }
        });
    }

    private void btnUpdateOnClick(View view) {
        try {
            user = super.getUser();

            mainApp.updateUser(user);

            Toast.makeText(ProfileActivity.this, R.string.success_update, Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            user = mainApp.getProfile();
        }
    }

    private void btnLogoutOnClick(View view) {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.INTENT_EMAIL, user.getEmail());

        mainApp.logout();
        startActivity(intent);
    }
}
