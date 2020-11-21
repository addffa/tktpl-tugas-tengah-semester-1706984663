package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "plan")
public class Plan implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int planId;

    private String title;

    public Plan(String title) {
        this.title = title;
    }

    public int getPlanId() {
        return planId;
    }

    public String getTitle() {
        return title;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
