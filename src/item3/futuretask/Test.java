package item3.futuretask;

import item3.runnable.WareHouse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author 叶嘉卫
 * @date 2022/11/27/13:44
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse();
        Callable<String> consumer = ()->{
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int i = wareHouse.RemoveProducts();
                if(i==0){
                    break;
                }
            }
            return Thread.currentThread().getName()+"以卖完";
        };
        FutureTask<String> future = new FutureTask<String>(consumer);
        Callable<String> producer = ()->{
            while(true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int j = wareHouse.getProducts();
                if(j==0){
                    break;
                }
            }
            return Thread.currentThread().getName()+"不能再买";
        };
        FutureTask<String> future1 = new FutureTask<String>(producer);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(future);
        executorService.submit(future1);
        executorService.shutdown();
    }
}
