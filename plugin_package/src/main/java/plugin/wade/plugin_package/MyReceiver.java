package plugin.wade.plugin_package;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import plugin.wade.standard.ReceiverInterface;

public class MyReceiver extends BroadcastReceiver implements ReceiverInterface {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是插件里面的广播接受者", Toast.LENGTH_SHORT).show();
    }
}
