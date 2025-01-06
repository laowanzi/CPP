public class ThreadLocalExample {

    // 定义一个 ThreadLocal 变量，存储 String 类型
    private static ThreadLocal<String> threadLocalVariable = ThreadLocal.withInitial(() -> "Initial Value");

    public static void main(String[] args) {
        // 设置当前线程的局部变量值
        threadLocalVariable.set("Value set in main method");

        // 创建两个线程并启动
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task());
        System.out.println(threadLocalVariable.get());

        thread1.start();
        thread2.start();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            // 获取当前线程的局部变量值
            String value = threadLocalVariable.get();
            System.out.println("Thread-local variable value: " + value);

            // 设置当前线程的局部变量值
            threadLocalVariable.set("New value set in task");

            // 获取更新后的局部变量值
            value = threadLocalVariable.get();
            System.out.println("Updated thread-local variable value: " + value);
        }

    }
}
