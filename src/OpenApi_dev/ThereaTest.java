package OpenApi_dev;

public class ThereaTest extends Thread {
	
	int  seq;
	
	public ThereaTest(int seq){
		this.seq = seq;
	}
	
	public void run(){
		System.out.println(":::::Thread run::::");
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			
		}
		System.out.println(this.seq +" thread end...");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<10; i++){
		ThereaTest t = new ThereaTest(i);
		
		t.start();
		}
		System.out.println("::main..end::");
		

	}

}
