package plugin.wade.com;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    public static final String TAG = PluginManager.class.getSimpleName();

    public static volatile PluginManager INSTANCE;

    private Context mContext;
    private DexClassLoader mDexClassLoader;
    private Resources mResources;

    private PluginManager(Context context) {
        mContext = context;
    }

    public static PluginManager getInstance(Context context) {
        if (null == INSTANCE) {
            synchronized (PluginManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new PluginManager(context);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 加载插件
     */
    public void loadPlugin() {
        try {
            //插件包的apk文件
            File file = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "plugin.apk");

            if (!file.exists()) {
                Log.e(TAG, "插件包不存在");
                return;
            }

            // 加载插件里的class文件
            String pluginPath = file.getAbsolutePath();
            //dexclassloader需要一个缓存目录,以下方式创建的目录为:/data/data/当前应用的包名/dex
            File cacheFile = mContext.getDir("dex", Context.MODE_PRIVATE);
            mDexClassLoader = new DexClassLoader(pluginPath, cacheFile.getAbsolutePath(),
                    null, mContext.getClassLoader());


            // 加载插件里面的layout

            //通过反射去获取manager
            AssetManager assetManager = AssetManager.class.newInstance();
            //为了把插件包路径添加进去,反射调用:
            // private int addAssetPathInternal(String path, boolean overlay, boolean appAsLib)
            // 传入的boolean,均为false
            Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
            method.invoke(assetManager, pluginPath);

            Resources resources = mContext.getResources();//宿主的资源配置信息

            //参数一:AssetManager; 参数二:资源参数配置信息
            //需要加载插件的资源信息
            mResources = new Resources(assetManager,
                    resources.getDisplayMetrics(), resources.getConfiguration());



        } catch (Exception e) {

        }
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }
}
