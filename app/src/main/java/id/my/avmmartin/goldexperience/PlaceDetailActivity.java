package id.my.avmmartin.goldexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Date;

public class PlaceDetailActivity extends AppCompatActivity implements AddPlanDialog.AddPlanDialogListener {
    private GoldExperience main_app;
    private TextView tv_name;
    private RatingBar rb_rating;
    private TextView tv_description;
    private Button btn_addplace;
    private Place place;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_place_detail);
        super.onCreate(savedInstanceState);

        init_components();
        set_contents();
        set_events();
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
        tv_name = findViewById(R.id.placedetail_tv_name);
        rb_rating = findViewById(R.id.placedetail_rb_rating);
        tv_description = findViewById(R.id.placedetail_tv_description);
        btn_addplace = findViewById(R.id.placedetail_btn_addplace);
        place = main_app.get_place((int)getIntent().getLongExtra(GoldExperience.INTENT_PLACE_ID, -1));
    }

    private void set_contents() {
        tv_name.setText(place.name);
        rb_rating.setRating(place.rating);
        tv_description.setText(place.desc);
    }

    private void set_events() {
        btn_addplace.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_addplan_onclick(view);
            }
        });
    }

    private void btn_addplan_onclick(View view) {
        AddPlanDialog dialog = new AddPlanDialog();
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override public void addplandialog_btn_submit_onclick(AddPlanDialog dialog) {
        String name = dialog.et_name.getText().toString();
        Date date = dialog.calendar.getTime();
        Date time = dialog.calendar.getTime();
        String note = dialog.et_note.getText().toString();

        Plan plan = new Plan(-1, place.id, main_app.get_user_id(), name, date, time, note);
        main_app.add_new_plan(plan);
    }
}
