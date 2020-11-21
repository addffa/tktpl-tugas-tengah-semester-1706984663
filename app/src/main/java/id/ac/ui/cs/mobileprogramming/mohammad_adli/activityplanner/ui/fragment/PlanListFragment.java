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

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.viewmodel.PlanViewModel;

public class PlanListFragment extends Fragment {

    PlanViewModel viewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlanListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan_list, container, false);

        viewModel = ViewModelProviders.of(this).get(PlanViewModel.class);
        viewModel.init(getActivity().getApplication());

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            viewModel.getAllPlans().observe(this, new Observer<List<Plan>>() {
                @Override
                public void onChanged(List<Plan> plans) {
                    recyclerView.setAdapter(new PlanListRecyclerViewAdapter(plans));
                }
            });
        }
        return view;
    }
}