package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.News;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<News> listNews;

    public NewsRecyclerViewAdapter(Context context, List<News> list) {
        this.context = context;
        this.listNews = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View newsView;
        public ViewHolder(View v) {
            super(v);
            newsView = v;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item, parent, false);
        return new NewsRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = listNews.get(position);

        TextView titleView = holder.newsView.findViewById(R.id.news_title);
        titleView.setText(news.getTitle());
        TextView typeView = holder.newsView.findViewById(R.id.news_type);
        typeView.setText(news.getType());
        TextView linkView = holder.newsView.findViewById(R.id.news_link);
        linkView.setText(news.getLink());
    }

    @Override
    public int getItemCount() {
        if (listNews != null) {
            return listNews.size();
        }
        return 0;
    }
}
