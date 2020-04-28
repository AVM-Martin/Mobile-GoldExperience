package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.activity.adapter.PlanListAdapter;
import id.my.avmmartin.goldexperience.activity.dialog.DeletePlanDialog;
import id.my.avmmartin.goldexperience.data.model.Plan;

public class PlanListActivity extends AppCompatActivity implements DeletePlanDialog.Listener {
    private GoldExperience mainApp;
    private ListView lvPlanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_plan_list);
        super.onCreate(savedInstanceState);

        initComponents();
        getSupportActionBar().setTitle(R.string.title_plan_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadData();
    }

    private void initComponents() {
        mainApp = (GoldExperience)this.getApplication();
        lvPlanData = findViewById(R.id.planlist_lv_plandata);
    }

    private void loadData() {
        lvPlanData.setAdapter(new PlanListAdapter(this, mainApp.getUserPlans()));
    }

    public void planListAdapterBtnDeleteOnClick(View view) {
        DeletePlanDialog dialog = new DeletePlanDialog();
        dialog.setDatas((Plan) view.getTag());
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void btnSubmitOnClick(DeletePlanDialog dialog) {
        mainApp.deletePlan(dialog.getPlan().getId());
        loadData();
    }
}
