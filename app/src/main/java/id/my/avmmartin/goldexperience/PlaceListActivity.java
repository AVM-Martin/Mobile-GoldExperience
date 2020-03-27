package id.my.avmmartin.goldexperience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PlaceListActivity extends AppCompatActivity implements LoadDataTask.Listener {
    private GoldExperience main_app;
    private ListView lv_placedata;
    private BottomNavigationView bottomnav;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_list);
        super.onCreate(savedInstanceState);

        init_components();
        set_events();
        getSupportActionBar().setTitle(R.string.title_place_list);

        if (!main_app.is_logged_in()) {
            Intent intent = new Intent(PlaceListActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            new LoadDataTask(PlaceListActivity.this).execute();
        }
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
        lv_placedata = findViewById(R.id.placelist_lv_placedata);
        bottomnav = findViewById(R.id.placelist_bottomnav);
    }

    private void set_events() {
        bottomnav.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.bottomnavigation_myplan) {
                        bottomnavigation_myplan_onclick();
                        return true;
                    } else if (item.getItemId() == R.id.bottomnavigation_profile){
                        bottomnavigation_profile_onclick();
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        );
    }

    @Override public void load_data() {
        lv_placedata.setAdapter(new PlaceListAdapter(this, main_app.get_places()));
        lv_placedata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = (Place)parent.getItemAtPosition(position);

                Intent intent = new Intent(PlaceListActivity.this, PlaceDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(GoldExperience.INTENT_PLACE_ID, place.id);
                startActivity(intent);
            }
        });
    }

    private void bottomnavigation_myplan_onclick() {
        Intent intent = new Intent(PlaceListActivity.this, PlanListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void bottomnavigation_profile_onclick() {
        Intent intent = new Intent(PlaceListActivity.this, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
