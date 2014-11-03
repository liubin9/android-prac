package com.bxd.activity;



import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.bxd.R;

public class CopyOfMainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabhost = getTabHost();
		
		TabSpec tab1 = tabhost.newTabSpec("tab1").setIndicator("我的报销").setContent(R.id.tab01);
		tabhost.addTab(tab1);
		TabSpec tab2 = tabhost.newTabSpec("tab2").setIndicator("我的工资").setContent(R.id.tab02);
		tabhost.addTab(tab2);
		TabSpec tab3 = tabhost.newTabSpec("tab3").setIndicator("报表统计").setContent(R.id.tab03);
		tabhost.addTab(tab3);
		TabSpec tab4 = tabhost.newTabSpec("tab4").setIndicator("应用设置").setContent(R.id.tab04);
		tabhost.addTab(tab4);
	}
}
