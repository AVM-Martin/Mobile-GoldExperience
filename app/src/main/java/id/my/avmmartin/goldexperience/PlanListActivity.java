package id.my.avmmartin.goldexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PlanListActivity extends AppCompatActivity {
    private GoldExperience main_app;
    private ListView lv_plandata;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_plan_list);
        super.onCreate(savedInstanceState);

        init_components();
        getSupportActionBar().setTitle(R.string.title_plan_list);
    }

    @Override protected void onStart() {
        super.onStart();

        load_data();
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
        lv_plandata = findViewById(R.id.planlist_lv_plandata);
    }

    private void load_data() {
        lv_plandata.setAdapter(new PlanListAdapter(this, main_app.get_user_plans()));
    }

    public void removePayOnClickHandler(View view) {
        Plan plan = (Plan)view.getTag();
        // TODO QUIZ: delete it from database
    }
}
