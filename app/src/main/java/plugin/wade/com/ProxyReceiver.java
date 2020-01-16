package plugin.wade.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import plugin.wade.standard.ReceiverInterface;

//使用Proxy里面的ProxyReceiver去接受广播
public class ProxyReceiver extends BroadcastReceiver {

    private String pluginReceiverName;

    public ProxyReceiver(String name) {
        this.pluginReceiverName = name;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 加载插件里面的Receiver
        try {
            Class myReciverClass = PluginManager.getInstance(context).getDexClassLoader().loadClass(pluginReceiverName);
            Object instance = myReciverClass.newInstance();
            ReceiverInterface receiverInterface = (ReceiverInterface) instance;
            receiverInterface.onReceive(context, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
