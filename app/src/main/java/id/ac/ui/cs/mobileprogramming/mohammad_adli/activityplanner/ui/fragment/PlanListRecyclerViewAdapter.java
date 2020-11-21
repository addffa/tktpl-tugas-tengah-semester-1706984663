package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository.PlanRepository;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.PlanActivity;

import java.util.List;

public class PlanListRecyclerViewAdapter extends RecyclerView.Adapter<PlanListRecyclerViewAdapter.ViewHolder> {

    private final List<Plan> mValues;

    public PlanListRecyclerViewAdapter(List<Plan> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Plan plan = mValues.get(position);
        holder.mItem = plan;
        holder.mIdView.setText(plan.getTitle());
        holder.mContentView.setText("test");
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Plan mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.plan_title);
            mContentView = view.findViewById(R.id.plan_activity_number);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mPosition = getLayoutPosition();
                    Plan plan = mValues.get(mPosition);
                    Intent intent = new Intent(mView.getContext(), PlanActivity.class);
                    intent.putExtra("Plan", plan);
                    mView.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}