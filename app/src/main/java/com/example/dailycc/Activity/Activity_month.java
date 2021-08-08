package com.example.dailycc.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
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

import java.util.Date;
import java.util.List;

import static android.transition.Fade.OUT;

public class Activity_month extends AppCompatActivity {

    private static final int INPT = 001;
    private static final int OUPT = 003;
    private static final int CLEAR = 005;
    private Month_4Dao dao;
    private List<Month_4> list;
    private MyMAdapter adapter;
    private ListView monthLV;
    private Button btn_m_sum;
    private int y_m;
    private double zhi;
    private double jing;
    private double tuan;
    private double wei;
    private double yun;
    private double sum;

    private TextView tv_y_m;
    private EditText e_zhi;
    private EditText e_jing;
    private EditText e_tuan;
    private EditText e_wei;
    private EditText e_yun;
    private TextView tv_esum;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);


        final Date date = new Date(System.currentTimeMillis());
        initView();//控件初始化
        dao = new Month_4Dao(this);
        list = dao.queryAll();
        adapter = new MyMAdapter();
        adapter.notifyDataSetChanged();
        monthLV.setAdapter(adapter);
        //填充当月数据
        if (list.size() > 0) {
            Month_4 a = list.get(0);
            e_zhi.setText(a.getZhiFuBao() + "");
            e_jing.setText(a.getJingDong() + "");
            e_tuan.setText(a.getMeiTuan() + "");
            e_wei.setText(a.getWeiXin() + "");
            e_yun.setText(a.getYunShanFu()+"");
            tv_esum.setText(a.getSum() + "");
        } else {
            e_zhi.setText("0.00");
            e_jing.setText("0.00");
            e_tuan.setText("0.00");
            e_wei.setText("0.00");
            e_yun.setText("0.00");
        }
        btn_m_sum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                zhi = Double.parseDouble(e_zhi.getText().toString());
                jing = Double.parseDouble(e_jing.getText().toString());
                tuan = Double.parseDouble(e_tuan.getText().toString());
                wei = Double.parseDouble(e_wei.getText().toString());
                yun = Double.parseDouble(e_yun.getText().toString());
                sum = zhi + jing + tuan + wei + yun;
                y_m = (date.getYear() + 1900) * 100 + date.getMonth() + 1;
                System.out.println(y_m);
//                System.out.println(date.getYear() + 1900);
//                System.out.println(date.getMonth() + 1);
//                System.out.println(list.get(0));
//                System.out.println(list.get(0).getDate());
                tv_esum.setText(sum + "");
                if (list.size() > 0 && y_m == list.get(0).getDate()) {
                    System.out.println(sum);
                    Month_4 m = new Month_4(list.get(0).getDate(), zhi, jing, tuan, wei, yun, sum);
                    System.out.println(m.getSum()+"-----");
                    dao.update(m);
                    System.out.println(m.getSum()+"-----");
                } else {
                    Month_4 m = new Month_4(y_m, zhi, jing, tuan, wei, yun, sum);
                    dao.insert(m);
                }
                list = dao.queryAll();
                adapter.notifyDataSetChanged();
                monthLV.setAdapter(adapter);
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, INPT, 4, "导入");
        menu.add(1, OUPT, 3, "导出");
        menu.add(1, CLEAR, 5, "清空");
        menu.add(1, OUT, 2, "退出");
        return true;
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
        @SuppressLint("DefaultLocale")
        public View getView(int position, View convertView, ViewGroup parent) {
            // 重用convertView
            View item = convertView != null ? convertView : View.inflate(
                    getApplicationContext(), R.layout.list_month, null);
            // 获取该视图中的TextView
            TextView list_zhi = (TextView) item.findViewById(R.id.tv_zhi);
            TextView list_jing = (TextView) item.findViewById(R.id.tv_jing);
            TextView list_tuan = (TextView) item.findViewById(R.id.tv_tuan);
            TextView list_wei = (TextView) item.findViewById(R.id.tv_wei);
            TextView list_yun = item.findViewById((R.id.tv_yun));
            TextView list_date = item.findViewById(R.id.tv_y_m);
            TextView list_sum = item.findViewById(R.id.tv_sum);

            // 根据当前位置获取Details对象
            final Month_4 a = list.get(position);

            // 把Details对象中的数据放到TextView中
            list_zhi.setText(String.format("%s", a.getZhiFuBao()));
            list_jing.setText(String.format("%s", a.getJingDong()));
            list_tuan.setText(String.format("%s", a.getMeiTuan()));
            list_wei.setText(String.format("%s", a.getWeiXin()));
            list_yun.setText(String.format("%s", a.getYunShanFu()));
            list_date.setText(String.format("%d年%d月", a.getDate() / 100, a.getDate() % 100));
            System.out.println(a.getSum());
            list_sum.setText(String.format("%s", a.getSum()));
            return item;
        }
    }

    // 初始化控件
    private void initView() {
        e_zhi = findViewById(R.id.editText);
        e_jing = findViewById(R.id.editText2);
        e_tuan = findViewById(R.id.editText3);
        e_wei = findViewById(R.id.editText4);
        e_yun = findViewById(R.id.editText5);
        tv_esum = findViewById(R.id.tv_esum);
        tv_y_m = findViewById(R.id.tv_y_m);
        btn_m_sum = findViewById(R.id.btn_m_sum);
        monthLV = findViewById(R.id.month_LV);
    }
}
