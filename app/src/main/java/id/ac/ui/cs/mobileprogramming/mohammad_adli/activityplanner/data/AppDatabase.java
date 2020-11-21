package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.dao.ActivityPlanDao;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.dao.PlanDao;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;

@Database(entities = {Plan.class, ActivityPlan.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PlanDao planDao();

    public abstract ActivityPlanDao activitPlanyDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
