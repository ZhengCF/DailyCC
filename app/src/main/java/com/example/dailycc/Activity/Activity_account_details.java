package com.example.dailycc.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dailycc.Class.A_Details;
import com.example.dailycc.Dao.A_DetailsDao;
import com.example.dailycc.R;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import static android.transition.Fade.OUT;

public class Activity_account_details extends AppCompatActivity {

    private static final int INPT = 001;
    private static final int OUPT = 003;
    private static final int CLEAR = 005;

    private A_DetailsDao dao;
    private List<A_Details> list_p;
    private List<A_Details> list_n;
    private MyMAdapter_n adapter_n;
    private MyMAdapter_p adapter_p;
    private ListView positive_lv;
    private ListView negative_lv;
    private EditText et_n_item;
    private EditText et_n_value;
    private EditText et_p_item;
    private EditText et_p_value;
    private TextView assert_repo_sum;
    private TextView tv_negative;
    private TextView tv_positive;
    private Button btn_negative;
    private Button btn_positive;
    private String n_item;
    private double n_value;
    private String p_item;
    private double p_value;

    private DecimalFormat df;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assets_report);

        df = new DecimalFormat("######0.00");

        initView();

        dao = new A_DetailsDao(this);
//        System.out.println("-------8------");
        list_n = dao.queryAll_n();
        list_p = dao.queryAll_p();
        adapter_n = new MyMAdapter_n();
        adapter_p = new MyMAdapter_p();
        adapter_n.notifyDataSetChanged();
        adapter_p.notifyDataSetChanged();
        negative_lv.setAdapter(adapter_n);
        positive_lv.setAdapter(adapter_p);

        Calculate_total();

        btn_negative.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                //0 = negative, 1 = positive
                                                int type = 0;
                                                n_item = et_n_item.getText().toString();
                                                n_value = Double.parseDouble(et_n_value.getText().toString());

                                                A_Details m = new A_Details(type, n_item, n_value);
                                                if (dao.isExist(n_item,type)) {
//                                                    System.out.println(m.getSum() + "-----");
                                                    dao.update(m);
//                                                    System.out.println(m.getSum() + "-----");
                                                } else {
                                                    dao.insert(m);
                                                }
                                                list_n = dao.queryAll_n();
                                                adapter_n.notifyDataSetChanged();
                                                negative_lv.setAdapter(adapter_n);
                                                Calculate_total();
                                            }
                                        }

        );

        btn_positive.setOnClickListener(new View.OnClickListener() {
                                            public void onClick(View v) {
                                                //0 = negative, 1 = positive
                                                int type = 1;
                                                p_item = et_p_item.getText().toString();
                                                p_value = Double.parseDouble(et_p_value.getText().toString());

                                                A_Details m = new A_Details(type, p_item, p_value);
                                                if (dao.isExist(p_item, type)) {
//                                                    System.out.println(m.getSum() + "-----");
                                                    dao.update(m);
//                                                    System.out.println(m.getSum() + "-----");
                                                } else {
                                                    dao.insert(m);
                                                }
                                                list_p = dao.queryAll_p();
                                                adapter_p.notifyDataSetChanged();
                                                positive_lv.setAdapter(adapter_p);
                                                Calculate_total();
                                            }
                                        }

        );

    }

    private class MyMAdapter_n extends BaseAdapter {
        public int getCount() { // 获取条目总数
            return list_n.size();
        }

        public Object getItem(int position) { // 根据位置获取对象
            return list_n.get(position);
        }

        public long getItemId(int position) { // 根据位置获取id
            return position;
        }

        // 获取一个条目视图
        @SuppressLint("SetTextI18n")
        public View getView(int position, View convertView, ViewGroup parent) {
            // 重用convertView
            View item = convertView != null ? convertView : View.inflate(
                    getApplicationContext(), R.layout.list_accout_detail, null);
            // 获取该视图中的TextView
            TextView list_item = (TextView) item.findViewById(R.id.tv_item);
            TextView list_value = (TextView) item.findViewById(R.id.tv_value);

            // 根据当前位置获取Details对象
            final A_Details a = list_n.get(position);

            // 把Details对象中的数据放到TextView中
            list_item.setText("" + a.getNote());
            list_value.setText("" + a.getValue());
            return item;
        }
    }

    private class MyMAdapter_p extends BaseAdapter {
        public int getCount() { // 获取条目总数
            return list_p.size();
        }

        public Object getItem(int position) { // 根据位置获取对象
            return list_p.get(position);
        }

        public long getItemId(int position) { // 根据位置获取id
            return position;
        }

        // 获取一个条目视图
        @SuppressLint("SetTextI18n")
        public View getView(int position, View convertView, ViewGroup parent) {
            // 重用convertView
            View item = convertView != null ? convertView : View.inflate(
                    getApplicationContext(), R.layout.list_accout_detail, null);
            // 获取该视图中的TextView
            TextView list_item = (TextView) item.findViewById(R.id.tv_item);
            TextView list_value = (TextView) item.findViewById(R.id.tv_value);

            // 根据当前位置获取Details对象
            final A_Details a = list_p.get(position);

            // 把Details对象中的数据放到TextView中
            list_item.setText("" + a.getNote());
            list_value.setText("" + a.getValue());
            return item;
        }
    }

    private void initView() {
        //item and value
        et_n_item = findViewById(R.id.et_n_item);
        et_n_value = findViewById(R.id.et_n_value);
        et_p_item = findViewById(R.id.et_p_item);
        et_p_value = findViewById(R.id.et_p_value);
        //recycle view
        negative_lv = findViewById(R.id.lv_negative);
        positive_lv = findViewById(R.id.lv_positive);
        //sum
        assert_repo_sum = findViewById(R.id.assert_repo_sum);
        //value sum
        tv_negative = findViewById(R.id.tv_negative);
        tv_positive = findViewById(R.id.tv_positive);
        //button
        btn_negative = findViewById(R.id.btn_negative);
        btn_positive = findViewById(R.id.btn_positive);
    }

    public void Calculate_total() {
        double[] totals = dao.total();

        tv_negative.setText(String.format("%s", totals[0]));
        tv_positive.setText(String.format("%s", totals[1]));
        assert_repo_sum.setText(String.format("%s", df.format(totals[1]-totals[0])));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, INPT, 4, "导入");
        menu.add(1, OUPT, 3, "导出");
        menu.add(1, CLEAR, 5, "清空");
        menu.add(1, OUT, 2, "退出");
        return true;
    }

    //菜单
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        switch (id) {
//            case INPT:
//                INPT();
////                Toast.makeText(getApplicationContext(), "导入成功",
////                        Toast.LENGTH_SHORT).show();
//                break;
//            case OUPT:
//                try {
//                    OUPT();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                Toast.makeText(getApplicationContext(), "导出成功，路径:",
////                        Toast.LENGTH_SHORT).show();
            case CLEAR:
                CLEAR();
                break;
            case OUT:
                Activity_account_details.this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void CLEAR() {
        dao.deleteAll();
        list_n = dao.queryAll_n();
        list_p = dao.queryAll_p();
        adapter_n = new MyMAdapter_n();
        adapter_p = new MyMAdapter_p();
        adapter_n.notifyDataSetChanged();
        adapter_p.notifyDataSetChanged();
        negative_lv.setAdapter(adapter_n);
        positive_lv.setAdapter(adapter_p);

        Calculate_total();
    }
}
