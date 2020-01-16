package plugin.wade.standard;

import android.app.Activity;
import android.os.Bundle;

public interface ActivityInterface {


    //把宿主的上下文环境传递给插件
    void insertHostAppContext(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onStop();

    void onPause();

    void onDestroy();

}
