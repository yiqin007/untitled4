package item2.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 叶嘉卫
 * @date
 * @Description 线程池模拟售票
 */
public class test {
    /**车票*/
    public static  int tickets=100;
    public static boolean loop=true;
    /**同步锁对象*/
    public static Object object =new Object();

    public static void main(String[] args) {
        //线程池线程为3
        ExecutorService executorService = Executors.newFixedThreadPool(3);

       Thread thread = new Thread(()->{
           while(loop) {
               synchronized(object){
                   if(tickets>0){
                       tickets--;
                       System.out.println("车票站:"+Thread.currentThread().getName()+"卖出一张票,还剩:"+tickets);
                   }else{
                       //结束售票
                       loop=false;

                   }
               }
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
           System.out.println("票以卖完，暂不售票");
       });
        for (int i = 0; i <3 ; i++) {
            //售票
            executorService.submit(thread);
        }
        //线程结束后结束线程池
            executorService.shutdown();


    }
}

