package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.about;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;

public class PyramidFragment extends Fragment {

    Context context;
    GLSurfaceView glSurfaceView;

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        context = root.getContext();
        return new PyramidSurfaceView(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (glSurfaceView != null) {
            glSurfaceView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (glSurfaceView != null) {
            glSurfaceView.onResume();
        }
    }
}