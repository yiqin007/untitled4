package item1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 叶嘉卫
 * @date 2022/11/24/21:09
 * @Description: item1
 */
public class Main {
    private static ReentrantLock lock = new ReentrantLock();
    private static int state=0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 3; ) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("A");
                        state++;
                        i++;
                    }
                    } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }});
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 3; ) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }

            }});
        Thread t3 = new Thread(()->{
            for (int i = 0; i < 3; ) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }

            }});
        t1.start();
        t2.start();
        t3.start();


    }
}
