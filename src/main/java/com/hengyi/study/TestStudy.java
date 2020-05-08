package com.hengyi.study;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TestStudy {
    private Timer
            mRunTimer;

    @Test
    public void  test1(){
        Observable.interval(3,2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        System.out.println("aa"+aLong);

                    }
                });
    }
    int timeSec = 0 ;
    @Test
    public  void  test2(){
        setLatLng();
    }
    private void setLatLng() {
        TimerTask mTask = new TimerTask() {
            @Override
            public void run() {
                timeSec++;
                System.out.println("first"+timeSec);
                if (timeSec %2 == 0) {
                    System.out.println("aaa"+timeSec);
                }


            }
        };
        mRunTimer = new Timer();
        // 每隔1s更新一下时间
        mRunTimer.schedule(mTask, 1000, 1000);
    }
}
