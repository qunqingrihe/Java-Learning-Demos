package ThreadLocal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {
    public ThreadPoolExecutor(int maxPoolSize,
                              int corePoolSize,
                              long keepAliveTime,
                              BlockingQueue<Runnable> workQueue,
                              TimeUnit unit,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
    {

    }
}
