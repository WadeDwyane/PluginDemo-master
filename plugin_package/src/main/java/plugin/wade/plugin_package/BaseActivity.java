package plugin.wade.plugin_package;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import plugin.wade.standard.ActivityInterface;

public class BaseActivity extends Activity implements ActivityInterface {

    //宿主的环境
    public Activity mActivity;

    @Override
    public void insertHostAppContext(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * 不想加载super的onCreate()方法
     *
     * @param savedInstanceState
     */
    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
    }

    public void setContentView(int resId) {
        mActivity.setContentView(resId);
    }

    public View findViewById(int resId) {
        return mActivity.findViewById(resId);
    }

    @Override
    public void startActivity(Intent intent) {
        Intent intentNew = new Intent();
        intentNew.putExtra("className", intent.getComponent().getClassName());
        mActivity.startActivity(intentNew);
    }

    @Override
    public ComponentName startService(Intent intent) {
        Intent newIntent = new Intent();
        newIntent.putExtra("className", intent.getComponent().getClassName());
        return mActivity.startService(newIntent);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return mActivity.registerReceiver(receiver, filter);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        mActivity.sendBroadcast(intent);
    }

}
