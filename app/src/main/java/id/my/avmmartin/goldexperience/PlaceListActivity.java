package id.my.avmmartin.goldexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class PlaceListActivity extends AppCompatActivity {
    private GoldExperience main_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_list);
        super.onCreate(savedInstanceState);

        init_components();
        if (!main_app.is_logged_in()) {
            Intent intent = new Intent(PlaceListActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
    }
}
