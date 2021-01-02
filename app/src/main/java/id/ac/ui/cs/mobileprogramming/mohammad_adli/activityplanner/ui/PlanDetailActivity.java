package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.receiver.AlarmReceiver;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.plan.PlanViewModel;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class PlanDetailActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    final int TIME_SPINNER_THEME_ID = 3;
    final int CALENDAR_PERMISSION_CODE = 0;

    boolean isFocus = false;

    CheckBox checkBox;
    EditText editTextDesc;
    EditText editTextTitle;
    TextView textDateTime;
    Plan mPlan;
    PlanViewModel planViewModel;

    AlarmManager alarmMgr;
    Calendar calendar;
    NotificationManager mNotificationManager;
    PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);
        setTitle(getString(R.string.text_title_plan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        planViewModel = new ViewModelProvider(this).get(PlanViewModel.class);

        checkBox = findViewById(R.id.checkbox_set_plan_datetime);
        editTextTitle = findViewById(R.id.text_input_plan_title);
        editTextTitle.setOnFocusChangeListener(focusChangeListener);
        editTextDesc = findViewById(R.id.text_input_plan_description);
        editTextDesc.setOnFocusChangeListener(focusChangeListener);
        textDateTime = findViewById(R.id.text_date_time_plan);


        Intent intent = getIntent();
        mPlan = (Plan) intent.getSerializableExtra("Plan");

        editTextTitle.setText(mPlan.getTitle());
        editTextDesc.setText(mPlan.getDescription());
        if (mPlan.getTimeMillis() != 0) {
            Date date = new Date(mPlan.getTimeMillis());
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            textDateTime.setText(df.format(date));
            checkBox.setChecked(true);
        } else {
            textDateTime.setText(R.string.not_set);
        }

        Button deleteBtn = findViewById(R.id.button_delete_plan);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planViewModel.delete(mPlan);
                onSupportNavigateUp();
                if (alarmMgr != null && alarmIntent != null) {
                    alarmMgr.cancel(alarmIntent);
                }
            }
        });

        Button okayBtn = findViewById(R.id.button_okay_plan);
        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFocus) {
                    if (v != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        editTextTitle.clearFocus();
                        editTextDesc.clearFocus();
                    }
                } else {
                    onBackPressed();
                }
            }
        });

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        checkPermissions(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPlan.setTitle(editTextTitle.getText().toString());
        mPlan.setDescription(editTextDesc.getText().toString());
        planViewModel.update(mPlan);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            isFocus = hasFocus;
            mPlan.setTitle(editTextTitle.getText().toString());
            mPlan.setDescription(editTextDesc.getText().toString());
            planViewModel.update(mPlan);
        }
    };

    public void onCheckBox(View view) {
        if(checkBox.isChecked()) {
            setPlanDate();
        } else {
            mPlan.setTimeMillis(0);
            planViewModel.update(mPlan);
            textDateTime.setText(R.string.set_datetime);
            if (alarmMgr != null && alarmIntent != null) {
                alarmMgr.cancel(alarmIntent);
            }
        }
    }

    public void setPlanDate() {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dateDialog = new DatePickerDialog(this, this, year, month, day);
        dateDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        setPlanTime();
    }

    public void setPlanTime() {
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(this, TIME_SPINNER_THEME_ID,
                this, hour, minute, true);
        timePicker.show();
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        mPlan.setTimeMillis(calendar.getTimeInMillis());
        Date date = new Date(mPlan.getTimeMillis());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        textDateTime.setText(df.format(date));

        mPlan.setTitle(editTextTitle.getText().toString());
        mPlan.setDescription(editTextDesc.getText().toString());
        planViewModel.update(mPlan);
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        notifyIntent.putExtra("Plan", mPlan);
        alarmIntent = PendingIntent.getBroadcast(this, mPlan.getPlanId(), notifyIntent, PendingIntent.FLAG_IMMUTABLE);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
    }

    private void checkPermissions(String... permissionsId) {
        boolean permissions = true;
        for (String p : permissionsId) {
            permissions = permissions && ContextCompat.checkSelfPermission(this, p) == PERMISSION_GRANTED;
        }

        if (!permissions)
            ActivityCompat.requestPermissions(this, permissionsId, CALENDAR_PERMISSION_CODE);
    }
}