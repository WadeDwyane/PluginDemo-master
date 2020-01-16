package plugin.wade.plugin_package;

import android.os.Bundle;
import android.widget.Toast;

public class PersonInfoActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personinfo);
        Toast.makeText(mActivity, "hahah", Toast.LENGTH_SHORT).show();
    }
}
