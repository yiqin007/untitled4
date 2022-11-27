package item2.runnable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 叶嘉卫
 * @date 2022/11/26/14:50
 * @Description: 实现Runnable
 */
public class Main {

    private static ReentrantLock lock = new ReentrantLock();
    private static int ticket=100;
    public static void  main(String[] args) {
        Thread t1 = new Thread(()->{
              while(ticket>0) {
                  lock.lock();
                  try {
                      if(ticket>0){
                          ticket--;
                          System.out.println(Thread.currentThread().getName()+"买了一张票,还剩"+ticket);
                      }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                     }
                  try {
                      Thread.sleep(100);
                  } catch (InterruptedException e) {
                      throw new RuntimeException(e);
                  }
              }
        });
        Thread t2 = new Thread(()->{
            while(ticket>0) {
                lock.lock();
                try {
                    if(ticket>0){
                        ticket--;
                        System.out.println(Thread.currentThread().getName()+"买了一张票,还剩"+ticket);
                    }
            } finally {
                lock.unlock();// unlock()操作必须放在finally块中
            }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        Thread t3 = new Thread(()->{
            while(ticket>0) {
                lock.lock();
                try {
                    if(ticket>0){
                        ticket--;
                        System.out.println(Thread.currentThread().getName()+"买了一张票,还剩"+ticket);
                    }

            } finally {
                lock.unlock();// unlock()操作必须放在finally块中
            }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }

}
