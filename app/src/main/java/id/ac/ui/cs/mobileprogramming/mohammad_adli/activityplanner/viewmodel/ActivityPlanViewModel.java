package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository.ActivityPlanRepository;


public class ActivityPlanViewModel extends ViewModel {

    private ActivityPlanRepository repository;

    public void init(Application application) {
        repository = new ActivityPlanRepository(application);
    }

    public LiveData<List<ActivityPlan>> getAllActivitiesPlans(int planId) {
        return repository.getAllActivitiesPlan(planId);
    }

    public void insert(ActivityPlan activityPlan) {
        repository.insert(activityPlan);
    }
}
