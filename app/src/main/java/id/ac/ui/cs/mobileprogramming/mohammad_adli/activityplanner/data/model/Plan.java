package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "plan")
public class Plan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int planId;

    private String title;

    private String description;

    private long timeMillis;

    public Plan() {
    }

    public Plan(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getPlanId() {
        return planId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }
}
