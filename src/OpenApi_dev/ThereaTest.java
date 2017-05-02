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
		int tmp=1;
		for(int i=0; i<3; i++){
		ThereaTest t = new ThereaTest(i);
		
		t.start();
		System.out.println("::tmp::"+tmp++);
		}
		System.out.println("::main..end::");
		
		
		
		

	}

}
