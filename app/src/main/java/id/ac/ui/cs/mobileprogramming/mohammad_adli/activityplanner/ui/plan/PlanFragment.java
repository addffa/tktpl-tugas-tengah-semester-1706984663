package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.plan;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;

public class PlanFragment extends Fragment {

    private PlanViewModel planViewModel;

    public PlanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        planViewModel = ViewModelProviders.of(this).get(PlanViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_plan, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = root.getContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                builder.setTitle(R.string.hint_plan_title);

                final EditText input = new EditText(root.getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton(context.getString(R.string.text_okay), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Plan newPlan = new Plan();
                        newPlan.setTitle(input.getText().toString());
                        planViewModel.insert(newPlan);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        final RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.plans);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        planViewModel.getAllPlans().observe(getViewLifecycleOwner(), new Observer<List<Plan>>() {
            @Override
            public void onChanged(List<Plan> plans) {
                recyclerView.setAdapter(new PlanRecyclerViewAdapter(plans));
            }
        });

        return root;
    }
}