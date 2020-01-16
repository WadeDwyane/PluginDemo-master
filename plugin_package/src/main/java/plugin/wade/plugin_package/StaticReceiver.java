package plugin.wade.plugin_package;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * -----------------------------------------------------------
 * Copyright (C) 2018-2019, by tempus , All rights reserved.
 * -----------------------------------------------------------
 * Author: kui.liu
 * Time: 2019/12/1 20:01
 * Description: 静态广播,是在清单中注册的
 * -----------------------------------------------------------
 */
public class StaticReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "我是静态注册的广播, 我收到了广播", Toast.LENGTH_SHORT).show();
    }
}
