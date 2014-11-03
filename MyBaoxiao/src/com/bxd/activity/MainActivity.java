package com.bxd.activity;

import java.util.ArrayList;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.bxd.R;
import com.bxd.view.XListView;
import com.bxd.view.XListView.IXListViewListener;

public class MainActivity extends TabActivity {
    private TabHost tabHost;

    private ImageView topbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = this.getTabHost();

        TabSpec baoxiaoSpec = tabHost.newTabSpec("baoxiao")
                .setIndicator("baoxiao")
                .setContent(new Intent(this, BaoxiaoActivity.class));
        TabSpec reportSpec = tabHost.newTabSpec("report")
                .setIndicator("report")
                .setContent(new Intent(this, ReportActivity.class));
        TabSpec settingSpec = tabHost.newTabSpec("setting")
                .setIndicator("setting")
                .setContent(new Intent(this, SettingActivity.class));

        tabHost.addTab(baoxiaoSpec);
        tabHost.addTab(reportSpec);
        tabHost.addTab(settingSpec);
        tabHost.setCurrentTab(R.id.myBaoxiao);

        RadioGroup rg = (RadioGroup) this.findViewById(R.id.radioGroup);
        topbar = (ImageView) this.findViewById(R.id.topbar);

        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                case R.id.myBaoxiao:
                    tabHost.setCurrentTabByTag("baoxiao");
                    topbar.setImageResource(R.drawable.topbar);
                    break;
                case R.id.report:
                    tabHost.setCurrentTabByTag("report");
                    topbar.setImageResource(R.drawable.bg);
                    break;
                case R.id.setting:
                    tabHost.setCurrentTabByTag("setting");
                    topbar.setImageResource(R.drawable.bg);
                    break;
                default:
                    break;
                }
            }
        });

    }

}