package item3.runnable;

/**
 * @author 叶嘉卫
 * @date
 * @Description 多个生产者对应多个消费者
 */
public class Test {
    public static void main(String[] args) {
        WareHouse wareHouse = new WareHouse();
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
        consumer.start();
        producer.start();
    }
}
