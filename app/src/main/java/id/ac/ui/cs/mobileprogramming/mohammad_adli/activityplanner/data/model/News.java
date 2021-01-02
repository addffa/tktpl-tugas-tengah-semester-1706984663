package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model;

import java.io.Serializable;

public class News implements Serializable {
    private String title;
    private String link;
    private String posterLink;
    private String type;

    public News() {
    }

    public News(String title, String link, String posterLink, String type) {
        this.title = title;
        this.link = link;
        this.posterLink = posterLink;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
