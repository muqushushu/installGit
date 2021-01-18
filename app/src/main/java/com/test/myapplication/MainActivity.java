package com.test.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private String TAG = "main";
//    private String pkname = "com.test.receiveprocess";
    private String pkname = "com.xzhisoft.chaoyanghygiene";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void tvOnClick(View view) {
        switch (view.getId()) {
            case R.id.tv:


                int packageUid = AppStateUtils.getPackageUid(this, pkname);
                if (packageUid > 0) {
                    boolean rstA = AppStateUtils.isAppRunning(this, pkname);
                    boolean rstB = AppStateUtils.isProcessRunning(this, packageUid);
                    if (rstA || rstB) {
                        Log.i(TAG, "指定包名的程序正在运行");
                    } else {
                        Log.i(TAG, "指定包名的程序未运行");
                        Intent intent = getPackageManager().getLaunchIntentForPackage(pkname);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("value", true);
                        if (intent != null) {
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                } else {
                    Log.i(TAG, "应用未安装");
                }


                break;
        }
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);

        tv.setText("判断另一个APP状态（前台还是后台）,并且传数据给该APP");
    }
}