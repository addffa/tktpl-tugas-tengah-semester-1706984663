package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;

import java.util.List;

public class ActivityPlanRecyclerViewAdapter extends RecyclerView.Adapter<ActivityPlanRecyclerViewAdapter.ViewHolder> {

    private final List<ActivityPlan> mValues;

    private Context context;

    public ActivityPlanRecyclerViewAdapter(List<ActivityPlan> items, Context context) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_activity_plan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ActivityPlan activityPlan = mValues.get(position);
        holder.mItem = activityPlan;
        holder.mTitleView.setText(activityPlan.getTitle());
        holder.mContentView.setText(activityPlan.getDescription());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fragmentTransaction.replace(R.id.fragment_activity_plan_detail,
                            ActivityDetailFragment.newInstance(activityPlan.getDescription()))
                            .commit();
                } else {
                    fragmentTransaction.replace(R.id.fragment_container_activity_plan,
                            ActivityDetailFragment.newInstance(activityPlan.getDescription()))
                            .addToBackStack(null)
                            .commit();
                }

            }
        });
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
        public final TextView mTitleView;
        public final TextView mContentView;
        public ActivityPlan mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.activity_title);
            mContentView = view.findViewById(R.id.activity_description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}