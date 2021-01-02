package id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.receiver;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.ui.PlanDetailActivity;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.R;
import id.ac.ui.cs.mobileprogramming.mohammad_adli.activityplanner.data.model.Plan;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    Plan mPlan;

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Deliver the notification.
        mPlan = (Plan) intent.getSerializableExtra("Plan");
        deliverNotification(context);
    }

    private void deliverNotification(Context context) {
        // Create the content intent for the notification, which launches
        // this activity
        Intent contentIntent = new Intent(context, PlanDetailActivity.class);
        contentIntent.putExtra("Plan", mPlan);

        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, mPlan.getPlanId(), contentIntent, PendingIntent
                        .FLAG_CANCEL_CURRENT);
        // Build the notification

        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_plan_notify_alarm)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        String title = mPlan.getTitle() != null ? mPlan.getTitle() : "";
        if (title.equals("")) {
            title = context.getString(R.string.empty_plan_title);
        }
        builder.setContentText(title);
        // Deliver the notification
        mNotificationManager.notify(mPlan.getPlanId(), builder.build());
    }
}