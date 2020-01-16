package plugin.wade.com;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Constructor;

import plugin.wade.standard.ActivityInterface;

/**
 * 代理activity(占位)
 */
public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 通过代理activity,加载插件activity.如何加载插件包中的class全类名.
        String className = getIntent().getStringExtra("className");
        try {
            Class mPluginActivityClass = getClassLoader().loadClass(className);
            //实例化插件包中的activity;

            Constructor constructor = mPluginActivityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});

            ActivityInterface activityInterface = (ActivityInterface) instance;
            //注入宿主的上下文环境
            activityInterface.insertHostAppContext(this);
            //执行插件里面的方法
            Bundle bundle = new Bundle();
            bundle.putString("package", "我是宿主调用的");
            activityInterface.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getDexClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");

        Intent proxyIntent = new Intent(this, ProxyActivity.class);
        proxyIntent.putExtra("className", className); // 包名+TestActivity

        // 要给TestActivity 进栈
        super.startActivity(proxyIntent);
    }

    @Override
    public ComponentName startService(Intent intent) {
        String serviceName = intent.getStringExtra("className");
        Intent newIntent = new Intent(this, ProxyService.class);
        newIntent.putExtra("className", serviceName);
        return super.startService(newIntent);
    }

    //在宿主注册广播
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        String pluginReceiverName = receiver.getClass().getName();
        ProxyReceiver proxyReceiver = new ProxyReceiver(pluginReceiverName);
        Toast.makeText(this, "注册广播完成", Toast.LENGTH_SHORT).show();
        return super.registerReceiver(proxyReceiver, filter);
    }

    //发送广播
    @Override
    public void sendBroadcast(Intent intent) {
        super.sendBroadcast(intent);
    }
}
