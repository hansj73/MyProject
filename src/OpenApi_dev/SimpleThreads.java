package OpenApi_dev;

public class SimpleThreads extends DbConnTest{

	// Display a message, preceded by
    // the name of the current thread
	
	
	
    static void threadMessage(String message) {
        String threadName =Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,message);
        
    }

    private static class MessageLoop implements Runnable {
        public void run() {
        	
            try {
            	int j=1;
            	/*for (;;) {// 무한루푸
*/            		for (int i=0; i<3; i++) {// 무한루푸
                    Thread.sleep(3000);
                    // Print a message
                    
//                   int list_size= DbConnTest.ApiMain();
                   DbConnTest.ApiMain(j++);
                   
                   System.out.println(":::list_size::");
//                  if(list_size ==0){
//                	  System.out.println(":::list_size_break::");
//                	  break;
//                  }
                    threadMessage("DbConnTest.getConnection()");
            	}
          
            } catch (InterruptedException e) {
        
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {

        long patience = 1000 * 60 * 60;
      
        DbConnTest.DbDriverLoad();// 디비 드라이버 로딩

        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
        // loop until MessageLoop
        // thread exits
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}
