package com.example.dailycc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.dailycc.DetailsDao;
import com.example.dailycc.R;

import java.util.Calendar;

public class Activity_vision extends AppCompatActivity {
    private Button btn;
    private TextView tvShowDialog1;
    private TextView tvShowDialog2;
    private Calendar cal;
    private DetailsDao dao;
    private int year, month, day;
    private TextView tv_sum;
    private TextView tv_startime;
    private TextView tv_stoptime;
    private Button btn_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);
        initView();//控件初始化
        getDate();
        dao = new DetailsDao(this);
        tvShowDialog1 = (TextView) findViewById(R.id.tv_startime);
        //选择日期按钮
        tvShowDialog1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_startime:
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                                String Smonth = month + "";
                                String Sday = day + "";
                                if (++month < 10) {
                                    Smonth = "0" + month;
                                }
                                if (day < 10) {
                                    Sday = "0" + day;
                                }
                                tvShowDialog1.setText(year + "-" + Smonth + "-" + Sday);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                            }
                        };
                        DatePickerDialog dialog = new DatePickerDialog(Activity_vision.this, DatePickerDialog.THEME_HOLO_LIGHT, listener, year, month, day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();
                        break;

                    default:
                        break;
                }
            }

        });//开始时间

        tvShowDialog2 = (TextView) findViewById(R.id.tv_stoptime);
        //选择日期按钮
        tvShowDialog2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_stoptime:
                        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                                String Smonth = month + "";
                                String Sday = day + "";
                                if (++month < 10) {
                                    Smonth = "0" + month;
                                }
                                if (day < 10) {
                                    Sday = "0" + day;
                                }
                                tvShowDialog2.setText(year + "-" + Smonth + "-" + Sday);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                            }
                        };
                        DatePickerDialog dialog = new DatePickerDialog(Activity_vision.this, DatePickerDialog.THEME_HOLO_LIGHT, listener, year, month, day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();
                        break;

                    default:
                        break;
                }
            }

        });//结束时间

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(Activity_vision.this, image.class);
                //startActivityForResult(intent, 1);
                Activity_vision.this.finish();
            }
        });

        btn_s = findViewById(R.id.button_s);
        btn_s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tv_sum.setText(dao.sltT2T(tv_startime.getText().toString(), tv_stoptime.getText().toString()) + "");
            }
        });//查看
    }

    //获取当前日期
    private void getDate() {
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy", "year" + year);
        month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day = cal.get(Calendar.DAY_OF_MONTH);
    }

    // 初始化控件
    private void initView() {

        tv_sum = findViewById(R.id.tv_sum);
        tv_startime = findViewById(R.id.tv_startime);
        tv_stoptime = findViewById(R.id.tv_stoptime);

    }
}
