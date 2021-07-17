package com.example.dailycc.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.dailycc.Class.Details;
import com.example.dailycc.Dao.DetailsDao;
import com.example.dailycc.R;
import com.example.dailycc.Helper.SDFileHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static android.transition.Fade.OUT;

public class MainActivity extends AppCompatActivity {

    private static final int INPT = 001;
    private static final int OUPT = 003;
    private static final int CGCL = 004;
    private static final int CLEAR = 005;
    private DetailsDao dao;
    private List<Details> list;
    private double[] totals;
    private ListView detailsLV;
    private MyAdapter adapter;
    private TextView gz;
    private TextView shf;
    private String date;
    private String count;
    private String note;
    private Integer type;

    private Context mContext;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private MainActivity tContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(MainActivity.this);
        dao = new DetailsDao(this);
        list = dao.queryAll();

        totals = dao.total();

        adapter = new MyAdapter();
        gz = findViewById(R.id.tv_gz);
        shf = findViewById(R.id.tv_shf);
        detailsLV = findViewById(R.id.detailsLV);

        gz.setText(String.valueOf(totals[0]));
        shf.setText(totals[1] + "");
        detailsLV.setAdapter(adapter);// add adapter

        // 添加监听器, 监听条目点击事件
        //detailsLV.setOnItemClickListener(new MyOnItemClickListener());
        detailsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view,
                                           final int position, long id) {
                //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
//                - setTitle：设置对话框的标题，比如“提示”、“警告”等； 
//                - setMessage：设置对话框要传达的具体信息； 
//                - setIcon： 设置对话框的图标； 
//                - setCancelable： 点击对话框以外的区域是否让对话框消失，默认为true； 
//                - setPositiveButton：设置正面按钮，表示“积极”、“确认”的意思，第一个参数为按钮上显示的文字，下同； 
//                - setNegativeButton：设置反面按钮，表示“消极”、“否认”、“取消”的意思； 
//                - setNeutralButton：设置中立按钮； 
//                - setOnShowListener：对话框显示时触发的事件； 
//                - setOnCancelListener：对话框消失时触发的事件

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setMessage("确定删除?");

                //添加AlertDialog.Builder对象的setPositiveButton()方法
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long _id = list.get(position).getId();
                        //TextView _id = view.findViewById(R.id.tv_id_r);
                        //int count = dao.delete(Long.valueOf(_id.toString()));
                        int count = dao.delete(_id);
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getBaseContext(), count + "条记录已删除", Toast.LENGTH_SHORT).show();
                        Calculate_total();
                    }
                });
                //添加AlertDialog.Builder对象的setNeutralButton()方法
                builder.setNeutralButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long _id = list.get(position).getId();
                        //TextView _id = view.findViewById(R.id.tv_id_r);
                        //int count = dao.delete(Long.valueOf(_id.toString()));
                        Details d = dao.select(_id);
                        Intent intent = new Intent(MainActivity.this, Activity_details.class);
                        intent.putExtra("id", String.valueOf(d.getId()));
                        intent.putExtra("date", d.getDate());
                        intent.putExtra("count", String.valueOf(d.getCount()));
                        intent.putExtra("note", d.getNote());
                        intent.putExtra("type", d.isType());
                        startActivityForResult(intent, 1);
                        //跳转页面
                    }
                });

                //添加AlertDialog.Builder对象的setNegativeButton()方法
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });


                builder.create().show();
                return false;
            }
        });//长按删除按钮

    }

    //合计
    public void Calculate_total() {
        totals = dao.total();

        gz.setText(totals[0] + "");
        shf.setText(totals[1] + "");
    }

    public void click(View v) {//记一笔
        Intent intent = new Intent(MainActivity.this, Activity_details.class);
        startActivityForResult(intent, 1);
    }

    public void click_vision(View v) {//总览
        Intent intent = new Intent(MainActivity.this, Activity_account_details.class);
        startActivityForResult(intent, 3);
    }

    public void click_month(View v) {//月视图
        Intent intent = new Intent(MainActivity.this, Activity_month.class);
        startActivityForResult(intent, 3);
    }

    //接受信息处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            //date = sDateFormat.format(new Date());
            date = data.getStringExtra("date");
            count = data.getStringExtra("count");
            note = data.getStringExtra("note");
            type = Integer.parseInt(data.getStringExtra("type"));


            if (data.hasExtra("id")) {
                //修改
                Long id = Long.parseLong(data.getStringExtra("id"));
                Details a = new Details(id, type, count.equals("") ? 0
                        : Double.parseDouble(count), date, note);
                dao.update(a);
            } else {
                Details a = new Details(type, count.equals("") ? 0
                        : Double.parseDouble(count), date, note);
                dao.insert(a);
                list.add(a);
            }

            Calculate_total();

            list = dao.queryAll();
            detailsLV = (ListView) findViewById(R.id.detailsLV);
            adapter.notifyDataSetChanged(); // 刷新界面
            detailsLV.setAdapter(adapter);// add adapter
            // 选中最后一个
            // detailsLV.setSelection(detailsLV.getCount() - 2);
        }
    }

    private class MyAdapter extends BaseAdapter {
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
                    getApplicationContext(), R.layout.activity_list, null);
            // 获取该视图中的TextView
            TextView list_note = (TextView) item.findViewById(R.id.list_note);
            TextView list_count = (TextView) item.findViewById(R.id.list_count);
            TextView list_date = (TextView) item.findViewById(R.id.list_date);
            TextView list_type = (TextView) item.findViewById(R.id.list_type);
            ImageView image = item.findViewById(R.id.imageView);

            // 根据当前位置获取Details对象
            final Details a = list.get(position);
            String tv_type;
            if (a.isType() == 0) {
                tv_type = "工资/外快:" + a.getCount() + " 生活费:0.00";
            } else {
                tv_type = "工资/外快:0.00 生活费:" + a.getCount();
            }

            // 把Details对象中的数据放到TextView中
            list_note.setText(a.getNote());
            list_count.setText("+" + a.getCount() + "元");
            list_date.setText(a.getDate());
            list_type.setText(tv_type);
            image.setImageResource(R.drawable.icon);
            return item;
        }
    }

    // ListView的Item点击事件
//    private class MyOnItemClickListener implements OnItemClickListener {
//        public void onItemClick(AdapterView<?> parent, View view, int position,
//                                long id) {
//            // 获取点击位置上的数据
//            Details a = (Details) parent.getItemAtPosition(position);
//            Toast.makeText(getApplicationContext(), a.getNote(),
//                    Toast.LENGTH_SHORT).show();
//        }
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, INPT, 4, "导入账单");
        menu.add(1, OUPT, 3, "导出账单");
        menu.add(1, CGCL, 4, "换色");
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
            case INPT:
                INPT();
//                Toast.makeText(getApplicationContext(), "导入成功",
//                        Toast.LENGTH_SHORT).show();
                break;
            case OUPT:
                try {
                    OUPT();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(getApplicationContext(), "导出成功，路径:",
//                        Toast.LENGTH_SHORT).show();
            case CLEAR:
                CLEAR();
                break;
            case OUT:
                MainActivity.this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void CLEAR() {
        dao.deleteAll();
        list = dao.queryAll();
        detailsLV = (ListView) findViewById(R.id.detailsLV);
        adapter.notifyDataSetChanged(); // 刷新界面
        detailsLV.setAdapter(adapter);// add adapter
    }

    public void INPT() {
        dao.deleteAll();
        String detail = "";
        SDFileHelper sdHelper2 = new SDFileHelper(mContext);
        try {
            verifyStoragePermissions(MainActivity.this);
            detail = sdHelper2.readFromSD("db.json");
            try {
                JSONObject jsonObject1 = new JSONObject(detail);
                Log.e("Json", detail);
                JSONArray jsonArray = jsonObject1.getJSONArray("list");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    //取出name
                    Long id = Long.parseLong(jsonObject.getString("id"));
                    String date = jsonObject.getString("date");
                    String count = jsonObject.getString("count");
                    String note = jsonObject.getString("note");
                    int type = Integer.parseInt(jsonObject.getString("type"));
                    Details a = new Details(id, type, count.equals("") ? 0
                            : Double.parseDouble(count), date, note);
                    dao.insert(a);
                    list.add(a);
                    Calculate_total();

                    list = dao.queryAll();
                    detailsLV = (ListView) findViewById(R.id.detailsLV);
                    adapter.notifyDataSetChanged(); // 刷新界面
                    detailsLV.setAdapter(adapter);// add adapter


                }
                Toast.makeText(getApplicationContext(), "导入成功",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "导入失败1" + e.toString(),
                        Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "导入失败2" + e.toString(),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void OUPT() throws IOException {

        //Json数据的创建
        try {
            JSONObject root = new JSONObject();
            root.put("id", "Zheng");

            JSONArray array = new JSONArray();

            int s = list.size();
            for (int i = 0; i < s; i++) {
                JSONObject lan = new JSONObject();
                lan.put("id", list.get(i).getId());
                lan.put("date", list.get(i).getDate());
                lan.put("count", list.get(i).getCount());
                lan.put("note", list.get(i).getNote());
                lan.put("type", list.get(i).isType());
                array.put(lan);
            }

            root.put("list", array);

            Log.d("info", root.toString());

            verifyStoragePermissions(MainActivity.this);
            mContext = getApplicationContext();
            System.out.println(mContext.getAssets().toString());

            SDFileHelper SfHelper = new SDFileHelper(mContext);
            String filename = "db.json";
            String fileDetail = root.toString();
            try {
                SfHelper.savaFileToSD(filename, fileDetail);
                Toast.makeText(getApplicationContext(), "导出成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "导出失败" + e.toString(), Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the u
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}
