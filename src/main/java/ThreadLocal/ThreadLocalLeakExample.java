package ThreadLocal;


public class ThreadLocalLeakExample {
    private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // 创建一个UserInfo对象并设置到ThreadLocal中
        UserInfo userInfo = new UserInfo("");
        userThreadLocal.set(userInfo);

        // 模拟在一段时间后，UserInfo不再被使用
        // 在实际应用中，这可能是因为请求处理完毕等原因
        userThreadLocal.remove(); // 假设这一行被遗忘或漏掉了

        // 强制GC尝试回收
        System.gc();

        Thread.sleep(1000); // 等待GC完成，只是为了示例需要

        // 模拟线程继续运行
        System.out.println("线程继续执行");
    }
}

