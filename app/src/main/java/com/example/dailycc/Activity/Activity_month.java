package com.example.dailycc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailycc.Class.Month_4;
import com.example.dailycc.Dao.Month_4Dao;
import com.example.dailycc.R;

import java.util.List;

public class Activity_month extends AppCompatActivity {

    private Month_4Dao dao;
    private List<Month_4> list;
    private MyMAdapter adapter;
    private ListView monthLV;
    private Button btn_m_sum;
    private double zhi;
    private double jing;
    private double tuan;
    private double wei;
    private double sum;
    private EditText e_zhi;
    private EditText e_jing;
    private EditText e_tuan;
    private EditText e_wei;
    private TextView tv_sum;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        initView();//控件初始化
        dao = new Month_4Dao(this);
        list = dao.queryAll();
        adapter = new MyMAdapter();
        adapter.notifyDataSetChanged();
        monthLV.setAdapter(adapter);

        btn_m_sum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zhi = Double.parseDouble(e_zhi.getText().toString());
                jing = Double.parseDouble(e_jing.getText().toString());
                tuan = Double.parseDouble(e_tuan.getText().toString());
                wei = Double.parseDouble(e_wei.getText().toString());
                sum=zhi+jing+tuan+wei;
                tv_sum.setText(sum+"");
                Month_4 m = new Month_4(zhi, jing, tuan, wei,sum);
                dao.insert(m);
                list.add(m);
                adapter.notifyDataSetChanged();
                monthLV.setAdapter(adapter);
            }
        });
    }

    private class MyMAdapter extends BaseAdapter {
        public int getCount() { // 获取条目总数
            return list.size();
        }

        public Object getItem(int position) { // 根据位置获取对象
            return list.get(position);
        }

        public long getItemId(int position) { // 根据位置获取id
            return position;
        }

        // 获取一个条目视图
        public View getView(int position, View convertView, ViewGroup parent) {
            // 重用convertView
            View item = convertView != null ? convertView : View.inflate(
                    getApplicationContext(), R.layout.list_month, null);
            // 获取该视图中的TextView
            TextView list_zhi = (TextView) item.findViewById(R.id.tv_zhi);
            TextView list_jing = (TextView) item.findViewById(R.id.tv_jing);
            TextView list_tuan = (TextView) item.findViewById(R.id.tv_tuan);
            TextView list_wei = (TextView) item.findViewById(R.id.tv_wei);

            // 根据当前位置获取Details对象
            final Month_4 a = list.get(position);

            // 把Details对象中的数据放到TextView中
            list_zhi.setText("" + a.getZhiFuBao());
            list_jing.setText("" + a.getJingDong());
            list_tuan.setText("" + a.getMeiTuan());
            list_wei.setText("" + a.getWeiXin());
            return item;
        }
    }

    // 初始化控件
    private void initView() {
        e_zhi = findViewById(R.id.editText);
        e_jing = findViewById(R.id.editText2);
        e_tuan = findViewById(R.id.editText3);
        e_wei = findViewById(R.id.editText4);
        tv_sum=findViewById(R.id.textView3);
        btn_m_sum = findViewById(R.id.btn_m_sum);
        monthLV = findViewById(R.id.month_LV);
    }
}
