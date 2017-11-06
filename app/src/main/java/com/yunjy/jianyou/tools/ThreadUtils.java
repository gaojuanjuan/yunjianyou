package com.yunjy.jianyou.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zt on 2017/9/18.
 */

public class ThreadUtils {

    private final static int SUCCESS = 200;
    private final static int FAIL = 400;

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mTempBundle mb = (mTempBundle) msg.obj;
            switch (msg.arg1) {
                case SUCCESS:
                    mb.mtask.success(mb.o);
                    break;

                case FAIL:
                    mb.mtask.error((Exception) mb.o);
                    break;

            }
            mb.mtask.after();
            if (!TextUtils.isEmpty(mb.tag)) {
                stringFutureHashMap.remove(mb.tag);
            }
        }
    };

    private ThreadUtils() {

    }

    private static ThreadUtils threadUtils;

    public static ThreadUtils getInstance() {
        if (threadUtils == null) {
            threadUtils = new ThreadUtils();
        }
        return threadUtils;
    }

    public <C extends TaskCallBack> void GO(C yourTask) {
        executorService.execute(new mThread(yourTask, null));
    }

    private HashMap<String, Future> stringFutureHashMap = new HashMap<>();

    public <C extends TaskCallBack> void Go(@NonNull String tag, C yourTask) {


        if (TextUtils.isEmpty(tag)) {
            executorService.execute(new mThread(yourTask, null));
        } else {
            Future<?> submit = executorService.submit(new mThread(yourTask, tag));
            stringFutureHashMap.put(tag, submit);
        }
    }

    public void cancel(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            Future future = stringFutureHashMap.get(tag);
            if (future != null) {
                future.cancel(true);
            }
            stringFutureHashMap.remove(tag);
        }
    }

    public void cancelAll() {
        stringFutureHashMap.clear();
        executorService.shutdownNow();
    }

    private class mThread implements Runnable {
        TaskCallBack mtask;
        String tag;

        public mThread(TaskCallBack mtask, String tag) {
            this.mtask = mtask;
            this.tag = tag;
            mtask.before();
        }


        @Override
        public void run() {
            try {
                Object o = mtask.inBackGround();
                Message message = handler.obtainMessage();
                message.arg1 = SUCCESS;
                message.obj = new mTempBundle(mtask, o, tag);
                handler.sendMessage(message);

            } catch (Exception e) {
                Message message = handler.obtainMessage();
                message.arg1 = FAIL;
                message.obj = new mTempBundle(mtask, e, tag);
                handler.sendMessage(message);
            } finally {

            }
        }
    }

    public interface TaskCallBack<T> {

        public void before();

        public T inBackGround();

        public void success(T result);

        public void error(Exception e);

        public void after();


    }


    private class mTempBundle {
        public TaskCallBack mtask;
        public Object o;
        public String tag;

        public mTempBundle(TaskCallBack mtask, Object o, String tag) {
            this.mtask = mtask;
            this.o = o;
            this.tag = tag;
        }
    }


}
