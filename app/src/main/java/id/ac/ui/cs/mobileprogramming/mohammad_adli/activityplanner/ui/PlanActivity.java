package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository.ActivityPlanRepository;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository.PlanRepository;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.fragment.ActivityPlanListFragment;

public class PlanActivity extends AppCompatActivity {

    private Plan plan;

    TextView planTitle;

    ActivityPlanRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        repository = new ActivityPlanRepository(getApplication());

        Intent intent = getIntent();
        plan = (Plan) intent.getSerializableExtra("Plan");

        planTitle = findViewById(R.id.title_plan_detail);
        planTitle.setText(plan.getTitle() + planTitle.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putInt("planId", plan.getPlanId());
        ActivityPlanListFragment fragment = new ActivityPlanListFragment();
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_activity_plan, fragment).commit();
    }

    public void addActivity(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText activityTitle = new EditText(PlanActivity.this);
        builder.setTitle("New Activity").setMessage("Title:")
                .setView(activityTitle)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        repository.insert(new ActivityPlan(
                                activityTitle.getText().toString(),
                                activityTitle.getText().toString() + " this is activity description",
                                plan.getPlanId()
                        ));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}