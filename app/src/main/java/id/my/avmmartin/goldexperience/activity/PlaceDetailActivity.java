package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Date;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.data.model.Place;
import id.my.avmmartin.goldexperience.activity.dialog.AddPlanDialog;
import id.my.avmmartin.goldexperience.data.model.Plan;
import id.my.avmmartin.goldexperience.utils.Constants;

public class PlaceDetailActivity extends AppCompatActivity implements AddPlanDialog.Listener {
    private GoldExperience mainApp;
    private TextView tvName;
    private RatingBar rbRating;
    private TextView tvDescription;
    private Button btnAddPlace;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_detail);
        super.onCreate(savedInstanceState);

        initComponents();
        loadDatas();
        setEvents();
    }

    private void initComponents() {
        mainApp = (GoldExperience) this.getApplication();
        tvName = findViewById(R.id.placedetail_tv_name);
        rbRating = findViewById(R.id.placedetail_rb_rating);
        tvDescription = findViewById(R.id.placedetail_tv_description);
        btnAddPlace = findViewById(R.id.placedetail_btn_addplace);
        place = mainApp.getPlace(getIntent().getIntExtra(Constants.INTENT_PLACE_ID, -1));
    }

    private void loadDatas() {
        tvName.setText(place.getName());
        rbRating.setRating(place.getRating());
        tvDescription.setText(place.getDesc());
    }

    private void setEvents() {
        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddPlanOnClick(view);
            }
        });
    }

    private void btnAddPlanOnClick(View view) {
        AddPlanDialog dialog = new AddPlanDialog();
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void btnSubmitOnClick(AddPlanDialog dialog) {
        String name = dialog.getEtName().getText().toString();
        Date date = dialog.getCalendar().getTime();
        Date time = dialog.getCalendar().getTime();
        String note = dialog.getEtNote().getText().toString();

        Plan plan = new Plan(-1, place.getId(), mainApp.getAppUserId(), name, date, time, note);
        mainApp.addNewPlan(plan);
    }
}
