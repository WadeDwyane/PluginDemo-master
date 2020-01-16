package plugin.wade.com;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import plugin.wade.standard.ServiceInterface;

public class ProxyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String className = intent.getStringExtra("className");
        try {
            //注入上下文环境
            Class testServiceName = PluginManager.getInstance(this).getDexClassLoader().loadClass(className);
            Object instance = testServiceName.newInstance();
            ServiceInterface serviceInterface = (ServiceInterface) instance;
            serviceInterface.insertHostAppContext(this);
            serviceInterface.onStartCommand(intent, flags, startId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
