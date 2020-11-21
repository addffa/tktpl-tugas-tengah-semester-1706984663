package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.AppDatabase;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.dao.PlanDao;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;

public class PlanRepository {

    private PlanDao planDao;

    private LiveData<List<Plan>> allPlans;

    public PlanRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        planDao = database.planDao();
        allPlans = planDao.getAllPlans();
    }

    public LiveData<List<Plan>> getAllPlans() {
        return allPlans;
    }

    public void insert(Plan plan) {
        new InsertPlanAsyncTask(planDao).execute(plan);
    }

    private static class InsertPlanAsyncTask extends AsyncTask<Plan, Void, Void> {

        private PlanDao dao;

        private InsertPlanAsyncTask(PlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Plan... plans) {
            dao.insert(plans[0]);
            return null;
        }
    }
}
