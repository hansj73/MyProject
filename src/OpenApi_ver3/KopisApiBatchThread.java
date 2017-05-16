package OpenApi_ver3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class KopisApiBatchThread extends KopisApiBatch{
         
	/**
	 * config 파일 load
	 */
	 
	 private static String getProperty(String keyName) {
	        String value = null;
	       
	        try {
	        	ClassLoader cl=ClassLoader.getSystemClassLoader();
	            URL url = cl.getResource( "OpenApi_ver3/config.properties" );
	            System.out.println(url.toString());
	            
	            Properties props = new Properties();
	            FileInputStream fis = new FileInputStream(url.getFile());
	            props.load(new java.io.BufferedInputStream(fis));
	            value = props.getProperty(keyName).trim();
	            fis.close();        } catch (java.lang.Exception e) {
//	            System.out.println(e.toString());
	        }
	            return value;
	    }

    static void threadMessage(String message) {
        String threadName =Thread.currentThread().getName();
        System.out.format("%s: %s%n",threadName,message);
        
    }

    private static class MessageLoop implements Runnable {
        public void run() {
        	
            String[] importantInfo = getProperty("artOrgName").split(",") ;
        	
        	try {
            	int j=1;
            	
            	 for (String shprfnmfct :importantInfo) 
            	 {
            		 for (;;) 
            		 {// 무한루푸
            			//for (int i=0; i<3; i++) {// 무한루푸*/                    
            			 Thread.sleep(4000);//4초
            			 int list_size= KopisApiBatch.ApiMain(j,shprfnmfct);
            			 /*DbConnTest.ApiMain(j++);*/
            			 /*threadMessage("DbConnTest.getConnection()");*/
            			 System.out.println("::pageCnt::"+j+"::::::"+shprfnmfct);
            			 if(list_size ==0){System.out.println(":::list_size_break::");break;}// if --end
            			 j++;
            		 }// for end
            			j=1;
            	 }//for end
          
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
