package OpenApi_dev;

public class ThereaTest extends Thread {
	
	int  seq;
	
	public ThereaTest(int seq){
		this.seq = seq;
	}
	
	public void run(){
		
		long start = System.currentTimeMillis();
		System.out.println(":::::Thread run::::");
		try{
			for(int i=0; i<3; i++){
			Thread.sleep(4000);
			System.out.println(":::::Thread run::::"+i);
			}
		}catch(Exception e){
			
		}
	
		System.out.println(this.seq +" thread end...");
		long end = System.currentTimeMillis();

		System.out.println( "실행 시간 : " + ( end - start )/1000.0 +" 초");

		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tmp=1;
		//for(int i=0; i<3; i++){
		ThereaTest t = new ThereaTest(tmp);
		
		t.start();
		System.out.println("::tmp::"+tmp++);
		//}
		System.out.println("::main..end::");
		
		
		
		

	}

}
