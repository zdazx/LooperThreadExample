package com.thoughtworks.looperthreadexample;

import android.os.Handler;
import android.os.Looper;

public class LooperThread extends Thread {
    public Handler handler;
    public Looper looper;

    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        handler = new MyHandler();
        Looper.loop();
    }
}
