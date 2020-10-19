package com.resjz.common.utils;

import java.util.concurrent.*;

public class ThreadPoolUtil {
    public static final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(3);

    public static void exeute(Runnable runnable,long delay,TimeUnit unit){

        scheduled.schedule(runnable,delay,unit);
    }
}
