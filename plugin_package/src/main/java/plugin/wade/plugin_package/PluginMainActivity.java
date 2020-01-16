package plugin.wade.plugin_package;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PluginMainActivity extends BaseActivity {


    private MyReceiver mMyReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pluginmain);

        mMyReceiver = new MyReceiver();

        //如果没有上下文,环境肯定会报错
        Toast.makeText(mActivity, "弹出吐司", Toast.LENGTH_SHORT).show();

        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, PersonInfoActivity.class));
            }
        });

        findViewById(R.id.btn_startservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(mActivity, TestService.class));
            }
        });

        findViewById(R.id.btn_registerbroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //动态注册广播
                IntentFilter filter = new IntentFilter();
                filter.addAction("plugin.wade.plugin_package.ACTION");
                registerReceiver(mMyReceiver, filter);
            }
        });

        findViewById(R.id.btn_sendbroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送广播
                Intent intent = new Intent();
                intent.setAction("plugin.wade.plugin_package.ACTION");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyReceiver);
    }
}
