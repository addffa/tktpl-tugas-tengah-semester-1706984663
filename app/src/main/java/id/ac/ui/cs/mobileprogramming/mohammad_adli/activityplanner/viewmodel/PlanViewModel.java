package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository.PlanRepository;

public class PlanViewModel extends ViewModel {

    private PlanRepository repository;

    private LiveData<List<Plan>> allPlans;

    public void init(Application application) {
        repository = new PlanRepository(application);
        allPlans = repository.getAllPlans();
    }

    public LiveData<List<Plan>> getAllPlans() {
        return allPlans;
    }

    public void insert(Plan plan) {
        repository.insert(plan);
    }
}
