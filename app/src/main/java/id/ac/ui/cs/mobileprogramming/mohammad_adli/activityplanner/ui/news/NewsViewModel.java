package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.news;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Something wrong with your connection or the source server:(");
    }

    public LiveData<String> getText() {
        return mText;
    }
}