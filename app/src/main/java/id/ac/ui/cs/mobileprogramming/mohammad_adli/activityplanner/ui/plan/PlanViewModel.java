package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.plan;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository.PlanRepository;

public class PlanViewModel extends AndroidViewModel {

    private PlanRepository repository;
    private LiveData<List<Plan>> mPlans;

    public PlanViewModel(Application application) {
        super(application);
        repository = new PlanRepository(application);
        mPlans = repository.getAllPlans();
    }

    public LiveData<List<Plan>> getAllPlans() {
        return mPlans;
    }

    public void insert(Plan plan) {
        repository.insert(plan);
    }

    public void update(Plan plan) {
        repository.update(plan);
    }

    public void delete(Plan plan) {
        repository.delete(plan);
    }
}