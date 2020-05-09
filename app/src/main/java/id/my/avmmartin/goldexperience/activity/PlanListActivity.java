package id.my.avmmartin.goldexperience.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import id.my.avmmartin.goldexperience.GoldExperience;
import id.my.avmmartin.goldexperience.R;
import id.my.avmmartin.goldexperience.activity.adapter.PlanListAdapter;
import id.my.avmmartin.goldexperience.activity.adapter.PlanListListener;
import id.my.avmmartin.goldexperience.activity.dialog.DeletePlanDialog;
import id.my.avmmartin.goldexperience.data.model.Plan;

public class PlanListActivity extends AppCompatActivity implements DeletePlanDialog.Listener {
    private GoldExperience mainApp;
    private RecyclerView rvPlanData;

    private PlanListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_plan_list);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getSupportActionBar().setTitle(R.string.title_plan_list);
        initComponents();
        loadData();
    }

    private void initComponents() {
        mainApp = (GoldExperience) this.getApplication();
        rvPlanData = findViewById(R.id.planlist_rv_plandata);
    }

    private void loadData() {
        adapter = new PlanListAdapter(this);
        adapter.setItemListener(new PlanListListener() {
            @Override
            public void btnDeleteOnClick(Plan plan) {
                planListAdapterBtnDeleteOnClick(plan);
            }
        });

        rvPlanData.setLayoutManager(new LinearLayoutManager(this));
        rvPlanData.setAdapter(adapter);
    }

    public void planListAdapterBtnDeleteOnClick(Plan plan) {
        // TODO: please check this item
        DeletePlanDialog dialog = new DeletePlanDialog();
        dialog.setDatas(plan);
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void btnSubmitOnClick(DeletePlanDialog dialog) {
        mainApp.getDataManager().deletePlanById(dialog.getPlan().getId());
        loadData();
    }
}
