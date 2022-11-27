package item2.futuretask;

import java.util.concurrent.*;

/**
 * @author 叶嘉卫
 * @date 2022/11/26/19:39
 * @Description: futureTask
 */
public class Main {
    public static  int tickets=100;
    public static boolean loop=true;
    /**同步锁对象*/
    public static Object object =new Object();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = ()->{
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
            return Thread.currentThread().getName()+"以卖完";
        };
        FutureTask<String> future = new FutureTask<String>(callable);
        FutureTask<String> future1 = new FutureTask<String>(callable);
        FutureTask<String> future2 = new FutureTask<String>(callable);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(future);
       executorService.submit(future1);
       executorService.submit(future2);
        System.out.println(future.get());
        System.out.println(future1.get());
        System.out.println(future2.get());
        executorService.shutdown();
    }

}
