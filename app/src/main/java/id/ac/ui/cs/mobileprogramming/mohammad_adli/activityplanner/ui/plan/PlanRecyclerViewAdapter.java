package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.plan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.PlanDetailActivity;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;

public class PlanRecyclerViewAdapter extends RecyclerView.Adapter<PlanRecyclerViewAdapter.ViewHolder> {
    private List<Plan> mPlans;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View planView;
        public ViewHolder(View v) {
            super(v);
            planView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PlanRecyclerViewAdapter(List<Plan> listPlan) {
        mPlans = listPlan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plan_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Plan plan = mPlans.get(position);

        TextView planTitle = holder.planView.findViewById(R.id.plan_title);
        String displayTitle = plan.getTitle() != null ? plan.getTitle() : "";
        if (displayTitle.length() > 30) {
            displayTitle = displayTitle.substring(0, 30) + "...";
        }
        if (displayTitle.equals("")) {
            planTitle.setText(R.string.empty_plan_title);
        } else {
            planTitle.setText(displayTitle);
        }

        TextView planDescription = holder.planView.findViewById(R.id.plan_description);
        planDescription.setText(plan.getDescription());

        holder.planView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PlanDetailActivity.class);
                intent.putExtra("Plan", plan);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mPlans != null) {
            return mPlans.size();
        }
        return 0;
    }
}
