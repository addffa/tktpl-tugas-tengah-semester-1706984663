package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.AppDatabase;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.dao.ActivityPlanDao;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;

public class ActivityPlanRepository {

    private ActivityPlanDao activityPlanDao;


    public ActivityPlanRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        activityPlanDao = database.activitPlanyDao();
    }

    public LiveData<List<ActivityPlan>> getAllActivitiesPlan(int planId) {
        return activityPlanDao.getAllActivitiesByPlanId(planId);
    }

    public void insert(ActivityPlan activityPlan) {
        new ActivityPlanRepository.InsertActivityPlanAsyncTask(activityPlanDao).execute(activityPlan);
    }

    private static class InsertActivityPlanAsyncTask extends AsyncTask<ActivityPlan, Void, Void> {

        private ActivityPlanDao dao;

        private InsertActivityPlanAsyncTask(ActivityPlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ActivityPlan... activityPlans) {
            dao.insert(activityPlans[0]);
            return null;
        }
    }
}
