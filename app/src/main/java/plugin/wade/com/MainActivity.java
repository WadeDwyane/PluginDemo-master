package plugin.wade.com;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final int RC_PERMISSION = 0x001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取读写权限
        checkPermission();
    }

    private void checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, RC_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSION && grantResults.length == 2
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "权限申请成功");
        }else {
            Log.e(TAG, "权限申请失败");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void loadPlugin(View view) {
        PluginManager.getInstance(this).loadPlugin();
    }

    public void startPluginActivity(View view) {
        File file = new File(Environment.getExternalStorageDirectory() +
                File.separator + "plugin.apk");
        String path = file.getAbsolutePath();

        //动态获取插件包中的类的全类名
        PackageManager manager = getPackageManager();
        PackageInfo packageInfo = manager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
        ActivityInfo activityInfo = packageInfo.activities[0];

        //插桩(占位,或者叫代理)
        Intent intent = new Intent(this, ProxyActivity.class);
        //将插件的classname 携带过去
        intent.putExtra("className", activityInfo.name);
        startActivity(intent);
    }
}
