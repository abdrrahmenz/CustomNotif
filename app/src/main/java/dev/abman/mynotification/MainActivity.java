package dev.abman.mynotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;
    private NotificationCompat.Builder notification;
    private Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        NotificationCompat.Builder notif = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_black_24dp))
//                .setContentTitle(getString(R.string.content_title))
//                .setContentText(getString(R.string.content_text))
//                .setSubText(getString(R.string.subtext))
//                .setAutoCancel(true);
//
//        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
//        managerCompat.notify(NOTIFICATION_ID, notif.build());
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(NOTIFICATION_ID, notification.build());
        }
    };

    public void senNotification(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dicoding.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_black_24dp))
                .setContentTitle(getString(R.string.content_title))
                .setContentText(getString(R.string.content_text))
                .setSubText(getString(R.string.subtext))
                .setAutoCancel(true);

        handler.postDelayed(runnable, 5000);
    }
}
