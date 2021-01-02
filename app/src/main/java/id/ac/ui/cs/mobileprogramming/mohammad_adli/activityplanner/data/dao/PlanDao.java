package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;

@Dao
public interface PlanDao {

    @Query("SELECT * FROM `plan`")
    LiveData<List<Plan>> getAllPlans();

    @Insert
    void insert(Plan plan);

    @Update
    void update(Plan plan);

    @Delete
    void delete(Plan plan);
}
