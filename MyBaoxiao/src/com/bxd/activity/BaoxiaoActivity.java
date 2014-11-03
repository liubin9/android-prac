package com.bxd.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.cookie.DateUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bxd.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * @author <a href="mailto:liubinyesman@gmail.com">Bin.Lau</a>
 * @description 通过PullToRefreshListView组件的方式实现下拉刷新效果
 * @version 1.0
 */
public class BaoxiaoActivity extends Activity {
    private PullToRefreshListView lv;

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
        setContentView(R.layout.activity_baoxiao);

        lv = (PullToRefreshListView) findViewById(R.id.xListView);

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
        lv.setAdapter(mAdapter);

        lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

            @SuppressWarnings("unchecked")
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                new AsyncTask() {

                    @Override
                    protected Object doInBackground(Object... params) {

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return null;
                    }
                    
                    protected void onPostExecute(Object result) {
                        Map<String, Object> listItem = new HashMap<String, Object>();
                        listItem.put("serial", "shalala"+ DateUtils.formatDate(new Date(), "HH:mm:ss") );
                        listItem.put("amount", "120");
                        listItem.put("feeType", "交通费");
                        maps.add(0,listItem);
                        
                        mAdapter = new SimpleAdapter(BaoxiaoActivity.this, maps, R.layout.list_item, mapkey, componetKey);
                        lv.setAdapter(mAdapter);
                        lv.onRefreshComplete();
                    }
                }.execute();

            }
            
        });

    }

}
