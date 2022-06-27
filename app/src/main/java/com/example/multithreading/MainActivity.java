package com.example.multithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvMsg);

        System.out.println(Thread.currentThread().getName());


//        ThreadA threadA = new ThreadA();
//        threadA.start();
//
//        ThreadB threadB = new ThreadB();
//        threadB.start();
//
        MyHandler myHandler = new MyHandler();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 200; i++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = myHandler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putChar("ch", '=');
                    msg.setData(bundle);
                    myHandler.sendMessage(msg);
                }
            }
        }).start();


//        MyHandler myHandler = new MyHandler();
//        Message message = myHandler.obtainMessage();
//        Bundle bundle = new Bundle();
//        bundle.putChar("ch", 'c');
//        message.setData(bundle);
//        myHandler.sendMessage(message);

//        new AsyncTask<String, Integer, String>() {
//
//            @Override
//            protected void onProgressUpdate(Integer... values) {
//                super.onProgressUpdate(values);
//
//            }
//
//            @Override
//            protected String doInBackground(String... strings) {
//                publishProgress(new Integer[]{1, 2});
//                System.out.println(Thread.currentThread().getName());
//                return "returned value from async task";
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//
//            }
//        }.execute();

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            textView.append(msg.getData().getChar("ch") + "");
        }
    }

}

//class ThreadA extends Thread {
//    @Override
//    public void run() {
//        super.run();
//        while (true) {
//            Log.i("MultiThreading", "ThreadA");
//        }
//    }
//}
//
//class ThreadB extends Thread {
//    @Override
//    public void run() {
//        super.run();
//        while (true) {
//
//            Log.i("MultiThreading", "ThreadB");
//        }
//    }
//}