package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.about;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.service.RingtoneService;

public class AboutFragment extends Fragment {

    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_about, container, false);
        context = root.getContext();

        Button buttonPlay = root.findViewById(R.id.button_play_ringtone);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startService(new Intent(context, RingtoneService.class));
            }
        });

        Button buttonStop = root.findViewById(R.id.button_stop_ringtone);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.stopService(new Intent(context, RingtoneService.class));
            }
        });

        Button buttonPrime = root.findViewById(R.id.button_is_prime);
        buttonPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputNumber = root.findViewById(R.id.input_number_prime);
                boolean isPrime = isPrime(Integer.parseInt(inputNumber.getText().toString()));
                TextView displayAnswer = root.findViewById(R.id.text_answer);
                if (isPrime) {
                    displayAnswer.setText(context.getText(R.string.yes));
                } else {
                    displayAnswer.setText(context.getText(R.string.no));
                }
            }
        });

        return root;
    }

    public native boolean isPrime(int number);

    static {
        System.loadLibrary("native-lib");
    }
}