package com.thoughtworks.looperthreadexample;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.thoughtworks.looperthreadexample.MyHandler.TASK_A;
import static com.thoughtworks.looperthreadexample.MyHandler.TASK_B;

public class MainActivity extends AppCompatActivity {
    private LooperThread looperThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        looperThread = new LooperThread();
        looperThread.start();

        Button aBtn = findViewById(R.id.a_btn);
        Button bBtn = findViewById(R.id.b_btn);

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