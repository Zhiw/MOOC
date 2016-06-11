package com.zhiw.mooc;

import com.zhiw.mooc.ui.Activity.SignInActivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import cn.bmob.push.PushConstants;

/**
 * ClassName: BmobPushReceiver
 * Desc:
 * Created by zhiw on 16/5/8.
 */
public class BmobPushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().contains(PushConstants.ACTION_MESSAGE)){
            String data=intent.getStringExtra("msg");
            String code= null;
            String title= null;
            String message= null;
            try {
                JSONObject jsonObject=new JSONObject(data);
                code = jsonObject.getString("code");
                title = jsonObject.getString("title");
                message = jsonObject.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            int notificationId = getClass().hashCode();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

            Intent intentSign = new Intent(context, SignInActivity.class);
            intent.putExtra("code",code);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    intentSign,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            builder.setSmallIcon(R.drawable.book_open_page_variant)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL
                    );
            manager.notify(notificationId, builder.build());
        }
    }
}
