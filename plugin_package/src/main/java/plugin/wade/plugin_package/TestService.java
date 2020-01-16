package plugin.wade.plugin_package;

import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class TestService extends BaseService {

    public static final String TAG = TestService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开启子线程,执行耗时任务

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                    Log.d(TAG, "正在执行中....");
                }
            }
        }.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
