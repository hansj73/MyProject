package OpenApi_dev;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DbConnTest {
	static Connection con;
	static Statement stmt;
    static ResultSet rs;
    
    static final String propFile = "G:/study/workspace/OpenApi/config/config.properties";

    public static void  DbDriverLoad()
    {
    	
    	try{
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            	System.out.println("드라이버 로딩 성공");
			} catch (ClassNotFoundException e) {
//				System.out.println("드라이버 로딩 실패");
			}
    }
    
    
    public static void getConnection()
    {
    	 
        try{
            //커넥션을 가져온다.
        	
        	   
        	    
        	  	Properties props = new Properties();
	            // 프로퍼티 파일 스트림에 담기
	            FileInputStream fis = new FileInputStream(propFile);
	            // 프로퍼티 파일 로딩
	            props.load(new java.io.BufferedInputStream(fis));
	            // 항목 읽기

	            String driverClass = props.getProperty("driverClassName") ;
	            String url = props.getProperty("url") ;
	            String username = props.getProperty("username") ;
	            String password= props.getProperty("password") ;
	            
	            // 콘솔 출력
//	            System.out.println(driverClass+":"+url+":"+username+":"+password) ;
	            con = DriverManager.getConnection(url,username, password);
	            System.out.println("커넥션 성공");
	           
        	
        	}catch(Exception e){
        		e.printStackTrace();
        	}
    }
    
    
        
    public  static void getData(String mt20id) throws IOException
    {
    	  String Detail_apiXml="";
    	  StringBuffer sb = new StringBuffer();
    	  String period="";
    	  try{
           	stmt = con.createStatement();
              //데이터를 가져온다.
           	rs = stmt.executeQuery("select count(*) count from OPENAPI_METADATA where sub_title='"+mt20id+"'");
              
              int cnt=(rs.next()==true)?rs.getInt("count"):-1;
              
              System.out.println(":::cnt:::"+cnt);
            
             }catch(SQLException e){
              e.printStackTrace();
              System.out.println("::e::"+e);
          }
        
    }
     
    public static void closeConnection()
    {
    	try{
                //자원 반환
                rs.close();
                stmt.close();
                con.close();
                System.out.println("::::dbClose():::");
            }catch(Exception e){
                e.printStackTrace();
            }
    }
   
    
    public static void ApiMain(int s_num) 
    {
		// TODO Auto-generated method stub
//		KopisApiBatch dbcon = new KopisApiBatch();
		KopisApiExplorer kopisEx = new KopisApiExplorer();
		
		System.out.println(":::s_num:::"+s_num);
		
	
		
/*		  Map<String,String> Api_detail = (Map<String,String>) kopisEx.getKopisList();
		 
		  for( String key : Api_detail.keySet() ){
	            System.out.println( String.format("키 : %s, 값 : %s", key, Api_detail.get(key)) );
	        }
*/
	/*	ArrayList<KopisApiDto> Api_list = (ArrayList<KopisApiDto>) kopisEx.getKopisList();
		
		 System.out.println(Api_list.size());
		 
		 int list_cnt=Api_list.size();
		
		 if(list_cnt >0 ){
			  getConnection(); //** 디비접속***//*
		 }
		 
		 for(int i=0; i<Api_list.size(); i++){
			 try {
				getData(Api_list.get(i).getMt20id());
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		 
		 if(list_cnt >0 ){
			  closeConnection();
		 }*/
		 
//		 return list_cnt;
		 
		 

				
	}


	

}
