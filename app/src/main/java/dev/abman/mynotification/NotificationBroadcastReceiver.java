package dev.abman.mynotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import static dev.abman.mynotification.NotificationService.REPLY_ACTION;

/**
 * Created by adul on 03/10/17.
 */

public class NotificationBroadcastReceiver extends BroadcastReceiver{

    private static String KEY_NOTIFICATION_ID = "key_notif_id";
    private static String KEY_MESSAGE_ID = "key_message_id";
    
    public static Intent getReplyMessageIntent(Context context, int notifId, int messageId){
        Intent intent = new Intent(context, NotificationBroadcastReceiver.class);
        intent.setAction(REPLY_ACTION);
        intent.putExtra(KEY_NOTIFICATION_ID, notifId);
        intent.putExtra(KEY_MESSAGE_ID, messageId);
        return intent;
    }

    public NotificationBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (REPLY_ACTION.equals(intent.getAction())){
            
            CharSequence message = NotificationService.getReplyMessage(intent);
            
            int messageId = intent.getIntExtra(KEY_MESSAGE_ID, 0);

            Toast.makeText(context, "Message ID : " + messageId + "\nMessage : " + message, Toast.LENGTH_SHORT).show();
            
            int notifyId = intent.getIntExtra(KEY_NOTIFICATION_ID, 1);
            
            updateNotification(context, notifyId);
        }
    }

    private void updateNotification(Context context, int notifyId) {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(context.getString(R.string.notif_title))
                .setContentText(context.getString(R.string.notif_content));

        managerCompat.notify(notifyId, builder.build());
    }
}
