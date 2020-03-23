package id.my.avmmartin.goldexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlanListActivity extends AppCompatActivity {
    private GoldExperience main_app;
    private ListView lv_plandata;

    @Override protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_plan_list);
        super.onCreate(savedInstanceState);

        init_components();

        load_data();

        getSupportActionBar().setTitle(R.string.title_plan_list);
    }

    private void init_components() {
        main_app = (GoldExperience)this.getApplication();
        lv_plandata = findViewById(R.id.planlist_lv_plandata);
    }

    private void load_data() {
        lv_plandata.setAdapter(new ArrayAdapter<>(
            this, android.R.layout.simple_list_item_1, main_app.get_user_plans()
        ));
        // TODO QUIZ: conditional background color
        // TODO QUIZ: delete data
//        lv_plandata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(PlanListActivity.this, PlaceDetailActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("PLACE_ID", id);
//                startActivity(intent);
//            }
//        });
    }
}
