package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.ActivityPlan;

@Dao
public interface ActivityPlanDao {

    @Query("SELECT * FROM activity WHERE planId=:planId")
    LiveData<List<ActivityPlan>> getAllActivitiesByPlanId(int planId);

    @Insert
    void insert(ActivityPlan activity);

    @Query("SELECT COUNT(*) FROM activity WHERE planId=:planId")
    int getActivitiesPlanCount(int planId);
}
