package item3.threadtool;

import item3.runnable.WareHouse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 叶嘉卫
 * @date 2022/11/27/13:39
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Thread consumer = new Thread(()->{
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int i = wareHouse.RemoveProducts();
                if(i==0){
                    return;
                }
            }
        });
        Thread producer = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int j = wareHouse.getProducts();
                if(j==0){
                    return;
                }
            }
        });
        executorService.submit(consumer);
        executorService.submit(producer);
        //线程结束后结束线程池
        executorService.shutdown();
    }
}
