package id.my.avmmartin.goldexperience.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.activity.adapter.PlaceListAdapter;
import id.my.avmmartin.goldexperience.activity.adapter.PlaceListListener;
import id.my.avmmartin.goldexperience.utils.Constants;
import id.my.avmmartin.goldexperience.utils.LoadDataIndicator;

public class PlaceListActivity extends AppCompatActivity {
    private GoldExperience mainApp;
    private RecyclerView rvPlaceData;
    private BottomNavigationView bottomNav;

    private LoadDataIndicator loadDataIndicator;
    private PlaceListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_list);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initComponents();
        loadData();
        setEvents();
        getSupportActionBar().setTitle(R.string.title_place_list);

        if (!mainApp.getDataManager().isLoggedIn()) {
            Intent intent = new Intent(PlaceListActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadDataIndicator.execute();
    }

    private void initComponents() {
        mainApp = (GoldExperience) this.getApplication();
        rvPlaceData = findViewById(R.id.placelist_rv_placedata);
        bottomNav = findViewById(R.id.placelist_bottomnav);

        loadDataIndicator = new LoadDataIndicator(PlaceListActivity.this);
    }

    public void loadData() {
        loadDataIndicator.setListener(new LoadDataIndicator.Listener() {
            @Override
            public void loadData() {
                adapter.setResources(mainApp.getDataManager().getPlaces());
            }
        });

        adapter = new PlaceListAdapter(this);
        adapter.setListener(new PlaceListListener() {
            @Override
            public void onItemClick(int placeId) {
                rvPlaceDataItemOnClick(placeId);
            }
        });

        rvPlaceData.setLayoutManager(new LinearLayoutManager(this));
        rvPlaceData.setAdapter(adapter);
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

    private void rvPlaceDataItemOnClick(int placeId) {
        Intent intent = new Intent(PlaceListActivity.this, PlaceDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.INTENT_PLACE_ID, placeId);
        startActivity(intent);
    }
}
