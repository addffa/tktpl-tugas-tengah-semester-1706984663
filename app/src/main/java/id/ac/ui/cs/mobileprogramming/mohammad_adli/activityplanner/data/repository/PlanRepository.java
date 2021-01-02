package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.datasource.AppDatabase;
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

    public void update(Plan plan) {
        new UpdatePlanAsyncTask(planDao).execute(plan);
    }

    public void delete(Plan plan) {
        new DeletePlanAsyncTask(planDao).execute(plan);
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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    private static class UpdatePlanAsyncTask extends AsyncTask<Plan, Void, Void> {
        private PlanDao dao;
        private UpdatePlanAsyncTask(PlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Plan... plans) {
            dao.update(plans[0]);
            return null;
        }
    }

    private static class DeletePlanAsyncTask extends AsyncTask<Plan, Void, Void> {
        private PlanDao dao;
        private DeletePlanAsyncTask(PlanDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Plan... plans) {
            dao.delete(plans[0]);
            return null;
        }
    }
}
