package com.bxd.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.bxd.R;
import com.bxd.view.XListView;
import com.bxd.view.XListView.IXListViewListener;

/**
 * @author <a href="mailto:liubin9@asiainfo.com">Bin.Lau</a>
 * @description 通过XListView组件的方式实现下拉刷新效果
 * @version 1.0
 */
public class BaoxiaoActivity_bak1 extends Activity implements
        IXListViewListener {
    private XListView mListView;

    private ArrayList<String> items = new ArrayList<String>();

    private Handler mHandler;

    private int start = 0;

    private static int refreshCnt = 0;

    private SimpleAdapter mAdapter;

    private String[] serialValues = new String[] { "123123", "23455", "34456",
            "5677" };

    private String[] amountValues = new String[] { "300", "400", "500", "600" };

    private String[] feeTypesValues = new String[] { "手机费用", "日常零星费用", "差旅费",
            "交通费" };

    String[] mapkey = new String[] { "serial", "amount", "feeType" };

    int[] componetKey = new int[] { R.id.serialText, R.id.amount,
            R.id.feeTypeText };

    List<Map<String, Object>> maps;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baoxiao_bak1);

        geneItems();
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);

        maps = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < serialValues.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("serial", serialValues[i]);
            listItem.put("amount", amountValues[i]);
            listItem.put("feeType", feeTypesValues[i]);
            maps.add(listItem);
        }

        mAdapter = new SimpleAdapter(this, maps, R.layout.list_item, mapkey,
                componetKey);
        mListView.setAdapter(mAdapter);
        mListView.setXListViewListener(this);
        mHandler = new Handler();
    }

    private void geneItems() {
        for (int i = 0; i != 5; ++i) {
            items.add("refresh cnt " + (++start));
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++refreshCnt;
                items.clear();
                geneItems();
                mAdapter = new SimpleAdapter(BaoxiaoActivity_bak1.this, maps,
                        R.layout.list_item, mapkey, componetKey);
                mListView.setAdapter(mAdapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                mAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }
}
