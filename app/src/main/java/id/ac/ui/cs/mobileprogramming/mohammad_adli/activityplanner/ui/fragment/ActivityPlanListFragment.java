package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.viewmodel.ActivityPlanViewModel;

public class ActivityPlanListFragment extends Fragment {

    ActivityPlanViewModel viewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivityPlanListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_plan_list, container, false);

        int planId = getArguments().getInt("planId");
        viewModel = ViewModelProviders.of(this).get(ActivityPlanViewModel.class);
        viewModel.init(getActivity().getApplication());

        // Set the adapter
        if (view instanceof RecyclerView) {
            final Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            viewModel.getAllActivitiesPlans(planId).observe(this, new Observer<List<ActivityPlan>>() {
                @Override
                public void onChanged(List<ActivityPlan> activityPlans) {
                    recyclerView.setAdapter(new ActivityPlanRecyclerViewAdapter(activityPlans, context));
                }
            });
        }
        return view;
    }
}