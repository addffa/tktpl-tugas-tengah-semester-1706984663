package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "activity")
public class ActivityPlan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int activityId;

    private String title;

    private String description;

    private int planId;

    public ActivityPlan(String title, String description, int planId) {
        this.title = title;
        this.description = description;
        this.planId = planId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
