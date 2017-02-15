package apps.tirloni.com.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by alexsandro-rs on 15/02/2017.
 */

public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Chegou um SMS",Toast.LENGTH_SHORT).show();
    }
}
