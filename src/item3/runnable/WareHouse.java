package item3.runnable;

import java.util.LinkedList;

/**
 * @author 叶嘉卫
 * @date 2022/10/06/12:18
 * @Description 仓库
 */
public class WareHouse {
        /**仓库*/
      public static LinkedList<String> list =  new LinkedList();
      /**仓库最大容量*/
      public static final int MAX_SIZE = 50;
      public static int count=0;

    /**生产*/
   public int getProducts() {
       synchronized (list) {
           if(count>=50){
               list.notifyAll();
               return 0;
           }
           while (list.size() + 1 > MAX_SIZE) {
              System.out.println("仓库以满");
              try {
                  list.wait();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
           }
           list.add("产品" + list.size()+1);
           count++;
           System.out.println(Thread.currentThread().getName()+"生产了一个"+list.getLast()+"目前库存："+list.size());
           list.notifyAll();
       }
       return 1;
   }

    /**消费*/
   public int RemoveProducts() {
           synchronized (list) {
               while(list.size()<=0&&count<50)
               {
                   System.out.println("仓库没有存货");
                   try {
                       list.wait();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
               }
               System.out.println("消费者"+Thread.currentThread().getName()+"消费了一个"+list.getLast());
                   list.remove();
               System.out.println("目前库存："+list.size());
                   list.notifyAll();
           }
           if(count==50&&list.size()==0){
               return 0;
           }
            return 1;
       }



}
