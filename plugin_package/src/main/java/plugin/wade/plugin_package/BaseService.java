package plugin.wade.plugin_package;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import plugin.wade.standard.ServiceInterface;

public class BaseService extends Service implements ServiceInterface {

    public Service mService;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void insertHostAppContext(Service activity) {
        this.mService = activity;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy() {

    }
}
