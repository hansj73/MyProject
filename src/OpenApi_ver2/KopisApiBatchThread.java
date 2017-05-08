package OpenApi_ver2;

public class KopisApiBatchThread extends KopisApiBatch{

    static void threadMessage(String message) {
        String threadName =Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,message);
        
    }

    private static class MessageLoop implements Runnable {
        public void run() {
        	try {
            	int j=1;
            	for (;;) {// 무한루푸
            		/*for (int i=0; i<3; i++) {// 무한루푸*/                    
            		Thread.sleep(4000);//4초
                   int list_size= KopisApiBatch.ApiMain(j++);
                   /*DbConnTest.ApiMain(j++);*/
                   /*threadMessage("DbConnTest.getConnection()");*/
                   System.out.println("::pageCnt::"+j);
                  if(list_size ==0){
                	  System.out.println(":::list_size_break::");
                	  break;
                  }
            	}
          
            } catch (InterruptedException e) {
            	System.out.println("::::SimpleThreads::run::error::"+e);
            }
        	
        }
    }

    public static void main(String args[]) throws InterruptedException {

        long patience = 1000 * 60 * 60;
      
        KopisApiBatch.DbDriverLoad();// 디비 드라이버 로딩

        threadMessage("Starting  thread");

        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
       
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(5000); // 5초
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join();
            }
        }
		long end = System.currentTimeMillis();
		System.out.println( "실행 시간 : " + ( end - startTime )/1000.0 +" 초");
        threadMessage("Finally!");
    }
}
