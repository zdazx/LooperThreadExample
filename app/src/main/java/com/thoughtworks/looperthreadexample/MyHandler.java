package com.thoughtworks.looperthreadexample;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyHandler extends Handler {
    public static final int TASK_A = 1;
    public static final int TASK_B = 2;
    public static final String TASK_A_TEXT = "Task A";
    public static final String TASK_B_TEXT = "Task B";
    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case TASK_A:
                Toast.makeText((Context) msg.obj, TASK_A_TEXT, Toast.LENGTH_LONG).show();
                break;
            case TASK_B:
                Toast.makeText((Context) msg.obj, TASK_B_TEXT, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
