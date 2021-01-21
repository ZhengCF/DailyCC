package com.example.dailycc.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.dailycc.R;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_welcome);

        final Intent intent = new Intent(this, MainActivity.class); //你要转向的Activity
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent); //执行
                finish();
            }
        };
        timer.schedule(task, 1000 * 1); //2秒后

    }

}
