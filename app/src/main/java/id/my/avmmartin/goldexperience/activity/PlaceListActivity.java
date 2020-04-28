package id.my.avmmartin.goldexperience.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.activity.adapter.PlaceListAdapter;
import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.utils.Constants;
import id.my.avmmartin.goldexperience.utils.LoadDataIndicator;

public class PlaceListActivity extends AppCompatActivity implements LoadDataIndicator.Listener {
    private GoldExperience mainApp;
    private ListView lvPlaceData;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_list);
        super.onCreate(savedInstanceState);

        initComponents();
        setEvents();
        getSupportActionBar().setTitle(R.string.title_place_list);

        if (!mainApp.isLoggedIn()) {
            Intent intent = new Intent(PlaceListActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            new LoadDataIndicator(PlaceListActivity.this).execute();
        }
    }

    private void initComponents() {
        mainApp = (GoldExperience) this.getApplication();
        lvPlaceData = findViewById(R.id.placelist_lv_placedata);
        bottomNav = findViewById(R.id.placelist_bottomnav);
    }

    private void setEvents() {
        bottomNav.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.bottomnavigation_myplan) {
                        bottomNavMyPlanOnClick();
                        return true;
                    } else if (item.getItemId() == R.id.bottomnavigation_profile){
                        bottomNavProfileOnClick();
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        );
    }

    @Override
    public void loadData() {
        lvPlaceData.setAdapter(new PlaceListAdapter(this, mainApp.getPlaces()));
        lvPlaceData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = (Place) parent.getItemAtPosition(position);

                Intent intent = new Intent(PlaceListActivity.this, PlaceDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.INTENT_PLACE_ID, place.getId());
                startActivity(intent);
            }
        });
    }

    private void bottomNavMyPlanOnClick() {
        Intent intent = new Intent(PlaceListActivity.this, PlanListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void bottomNavProfileOnClick() {
        Intent intent = new Intent(PlaceListActivity.this, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
