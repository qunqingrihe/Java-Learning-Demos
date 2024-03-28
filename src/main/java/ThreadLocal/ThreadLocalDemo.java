package ThreadLocal;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocalDemo类实现了Runnable接口，用于演示ThreadLocal的使用。
 */
public class ThreadLocalDemo implements Runnable {

    // 使用ThreadLocal来保存一个线程安全的SimpleDateFormat实例
    private static final ThreadLocal<SimpleDateFormat> formatter=ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    /**
     * 程序的主入口函数。
     * @param args 命令行参数（未使用）
     * @throws InterruptedException 如果线程在睡眠时被中断可能会抛出此异常
     */
    public static void main(String[] args) throws InterruptedException{
        ThreadLocalDemo obj =new ThreadLocalDemo();
        // 创建并启动10个线程，每个线程延迟随机时间后执行
        for(int i=0;i<10;i++){
            Thread t=new Thread(obj,""+i);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }

    /**
     * Runnable接口的run方法，每个线程执行的操作。
     */
    @Override
    public void run(){
        // 输出当前线程名和格式化的时间
        System.out.println(Thread.currentThread().getName()+" "+formatter.get().format(System.currentTimeMillis()));
        try{
            // 线程睡眠随机时间
            Thread.sleep(new Random().nextInt(1000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // 重置ThreadLocal变量为新的SimpleDateFormat实例
        formatter.set(new SimpleDateFormat());
        // 再次输出当前线程名和格式化的时间，展示ThreadLocal的线程隔离特性
        System.out.println( Thread.currentThread().getName()+" "+formatter.get().format(System.currentTimeMillis()));
    }
}
