package com.thoughtworks.looperthreadexample;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.thoughtworks.looperthreadexample.MyHandler.TASK_A;
import static com.thoughtworks.looperthreadexample.MyHandler.TASK_B;

public class MainActivity extends AppCompatActivity {
    private LooperThread looperThread;
    private Button threadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        looperThread = new LooperThread();
        looperThread.start();

        Button aBtn = findViewById(R.id.a_btn);
        Button bBtn = findViewById(R.id.b_btn);
        threadBtn = findViewById(R.id.thread_btn);

        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerToast(TASK_A);
            }
        });

        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triggerToast(TASK_B);
            }
        });

        threadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNumber();
            }
        });
    }

    private void changeNumber() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadBtn.setClickable(false);
                for (int i = 1; i <= 10; i++) {
                    threadBtn.setText(String.valueOf(i));
                    SystemClock.sleep(1000);
                }
                threadBtn.setText(R.string.thread_btn);
                threadBtn.setClickable(true);
            }
        }).start();
    }

    private void triggerToast(int what) {
        Message message = Message.obtain();
        message.what = what;
        message.obj = this.getBaseContext();
        looperThread.handler.sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        looperThread.looper.quit();
    }
}