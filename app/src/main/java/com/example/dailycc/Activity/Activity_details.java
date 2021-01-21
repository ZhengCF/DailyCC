package com.example.dailycc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailycc.R;

import java.text.DecimalFormat;
import java.util.Calendar;


public class Activity_details extends AppCompatActivity {
    private EditText EditT_count;
    private Button btn_save;
    private EditText EditT_note;
    private RadioGroup rbGroup;
    private TextView tv_date;
    private TextView tvShowDialog;
    private Calendar cal;
    private int year, month, day;
    private String count;
    private String note;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();//控件初始化
        getDate();
        final Intent intent = new Intent();
        final Intent i = getIntent();

        tvShowDialog = (TextView) findViewById(R.id.tv_date);
        tvShowDialog.setText(year + "-" + (++month) + "-" + day);
        month--;//恢复

        //修改
        if (i.hasExtra("count")) {
//            System.out.println(i.getStringExtra("count"));
//            System.out.println(i.getStringExtra("note"));
            EditT_count.setText(i.getStringExtra("count"));
            EditT_note.setText(i.getStringExtra("note"));
            tv_date.setText(i.getStringExtra("date"));
            if (i.getStringExtra("type") == "0") {
                rbGroup.check(R.id.radioB_gz);
            } else {
                rbGroup.check(R.id.radioB_shf);
            }
//                rbGroup.setSelected(true);
        }

        //选择日期按钮
        tvShowDialog.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_date:
                        OnDateSetListener listener = new OnDateSetListener() {

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
                                tvShowDialog.setText(year + "-" + Smonth + "-" + Sday);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                            }
                        };
                        DatePickerDialog dialog = new DatePickerDialog(Activity_details.this, DatePickerDialog.THEME_HOLO_LIGHT, listener, year, month, day);//主题在这里！后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();
                        break;

                    default:
                        break;
                }
            }

        });//

        btn_save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final DecimalFormat df = new DecimalFormat("######0.00");

                count = EditT_count.getText().toString();
                if (count.equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入金额",
                            Toast.LENGTH_SHORT).show();
                } else {
                    count = df.format(Double.parseDouble(count));
                    note = EditT_note.getText().toString();
                    date = tv_date.getText().toString();
                    String type;
                    //= rbGroup.getCheckedRadioButtonId()+"";
                    switch (rbGroup.getCheckedRadioButtonId()) {
                        case R.id.radioB_gz:
                            type = "0";
                            intent.putExtra("type", type);
                            break;
                        case R.id.radioB_shf:
                            type = "1";
                            intent.putExtra("type", type);
                            break;
                        default:
                            break;
                    }


                    intent.putExtra("count", count);
                    intent.putExtra("note", note);
                    intent.putExtra("date", date);

                    if (i.hasExtra("count")) {
                        intent.putExtra("id", i.getStringExtra("id"));
                    }
                    setResult(1, intent);
                    Activity_details.this.finish();
                    EditT_count.setText("");
                    EditT_note.setText("");
                }
            }
        });//

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

        btn_save = findViewById(R.id.btn_sa);
        EditT_count = findViewById(R.id.EditT_count);
        EditT_note = findViewById(R.id.editT_note);
        tv_date = findViewById(R.id.tv_date);
        rbGroup = findViewById(R.id.rbGroup);

    }
}
