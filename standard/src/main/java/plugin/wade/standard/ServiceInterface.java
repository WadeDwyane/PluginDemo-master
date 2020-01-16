package plugin.wade.standard;

import android.app.Service;
import android.content.Intent;

public interface ServiceInterface {

    //把宿主的环境给插件
    void insertHostAppContext(Service activity);

    void onCreate();

    int onStartCommand(Intent intent, int flags, int startId);

    void onDestroy();

}
