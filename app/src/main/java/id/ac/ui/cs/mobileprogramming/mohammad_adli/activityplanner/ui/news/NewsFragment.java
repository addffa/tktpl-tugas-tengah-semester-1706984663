package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.news;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.News;

public class NewsFragment extends Fragment {

    final String GET_NEWS = "GetNewsTag";
    final String NEWS_API = "https://www.news.developeridn.com/";

    NewsViewModel newsViewModel;
    ConnectivityManager connectivityMgr;
    RequestQueue queue;

    NewsRecyclerViewAdapter adapter;
    List<News> listNews;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);

        connectivityMgr = (ConnectivityManager) root.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        queue = Volley.newRequestQueue(root.getContext());

        if (!isConnectedToInternet()) {
            newsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });

            AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
            builder.setMessage(R.string.need_internet_connection)
                    .setTitle(R.string.no_connection)
                    .setCancelable(false)
                    .setPositiveButton(R.string.settings,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    startActivity(intent);
                                }
                            }
                    )
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().getFragmentManager().popBackStack();
                                }
                            }
                    );
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            root = inflater.inflate(R.layout.fragment_news_recyclerview, container, false);
            listNews = new ArrayList<>();
            listNews.add(new News("judul", "link", "link poster", "tipe"));
            adapter = new NewsRecyclerViewAdapter(root.getContext(), listNews);
            final RecyclerView recyclerView = root.findViewById(R.id.news_recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
            recyclerView.setAdapter(adapter);

            getNewsFromApi();
        }

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(GET_NEWS);
        }
    }

    private boolean isConnectedToInternet() {
        return connectivityMgr.getActiveNetworkInfo() != null &&
                connectivityMgr.getActiveNetworkInfo().isConnected();
    }

    private void getNewsFromApi() {
        JsonObjectRequest newsRequest = new JsonObjectRequest(Request.Method.GET, NEWS_API, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listNews.clear();
                            JSONArray dataNews = response.getJSONArray("data");
                            for(int i = 0; i < dataNews.length(); i++) {
                                JSONObject jsonNews = (JSONObject) dataNews.get(i);
                                listNews.add(new News(
                                            jsonNews.getString("judul"),
                                            jsonNews.getString("link"),
                                            jsonNews.getString("poster"),
                                            jsonNews.getString("tipe")
                                        )
                                );
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(newsRequest);
    }
}