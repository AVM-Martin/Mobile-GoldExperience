package id.my.avmmartin.goldexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlaceDetailActivity extends AppCompatActivity {
    private GoldExperience main_app;
    private TextView tv_name;
    private TextView tv_rating;
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
        tv_rating = findViewById(R.id.placedetail_tv_rating);
        tv_description = findViewById(R.id.placedetail_tv_description);
        btn_addplace = findViewById(R.id.placedetail_btn_addplace);
        place = main_app.get_place((int)getIntent().getLongExtra("PLACE_ID", -1));
    }

    private void set_contents() {
        tv_name.setText(place.name);
        tv_rating.setText(Integer.toString(place.rating));
        tv_description.setText(place.desc);
    }

    private void set_events() {
        btn_addplace.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                btn_addplace_onclick(view);
            }
        });
    }

    private void btn_addplace_onclick(View view) {
        // TODO QUIZ
    }

}
