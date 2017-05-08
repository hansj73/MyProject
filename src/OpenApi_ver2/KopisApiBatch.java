package OpenApi_ver2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class KopisApiBatch {
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
              
              if(cnt==0)
              {
              	Detail_apiXml= KopisApiExplorer.apiDataDetail(mt20id); // 리스트 조회
//              	System.out.println(":::Detail_apiXml:::"+Detail_apiXml);
              	
              	ArrayList<KopisApiDto> Api_detail = (ArrayList<KopisApiDto>) KopisApiExplorer.processDocumentDetail(Detail_apiXml);
//              	System.out.println("::api_detail::"+Api_detail.get(0).getMt20id());
              	period=Api_detail.get(0).getPrfpdfrom()+"~"+Api_detail.get(0).getPrfpdto();
              			
              	sb.append("INSERT INTO OPENAPI_METADATA");
              	sb.append("(SEQ,TITLE,SUB_TITLE,REG_DATE,EXTENT,RIGHTS,CHARGE,PERIOD,TIME,REFERENCE_IDENTIFIER_ORG,GENRE)");
              	sb.append("VALUES");
              	sb.append("(OPENAPI_METADATA_SEQUENCE.NEXTVAL,'"+Api_detail.get(0).getPrfnm()+"',");
              	sb.append("'"+mt20id+"',sysdate,'"+Api_detail.get(0).getDtguidance()+"',");
              	sb.append("'"+Api_detail.get(0).getEntrpsnm()+"','"+Api_detail.get(0).getPcseguidance()+"',");
              	sb.append("'"+period+"','"+Api_detail.get(0).getPrfruntime()+"',");
              	sb.append("'"+Api_detail.get(0).getPoster()+"','"+Api_detail.get(0).getGenrenm()+"')");
                          	  
              	stmt.executeUpdate(sb.toString());
              	
              	try {
					KopisApiExplorer.ImageRead(Api_detail.get(0).getPoster());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              	
              	System.out.println("파일 입력 및 이미지 저장 완료");
              }
            	else if(cnt>0)
            	{
            		System.out.println("등록되어 있습니다.");
            	}
            	else
            	{
            		System.out.println(":::error_msg::"+cnt);
            	}
              
            
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
   
    
    public static int  ApiMain(int s_num) 
    {
		// TODO Auto-generated method stub
		KopisApiExplorer kopisEx = new KopisApiExplorer();
		
		ArrayList<KopisApiDto> Api_list = (ArrayList<KopisApiDto>) kopisEx.getKopisList(s_num);
		 
		 int list_cnt=Api_list.size();
		
		 if(list_cnt >0 ){
			  getConnection(); //** 디비접속**
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
		 }
		 
		 System.out.println(":::성공갯수::"+s_num*20);
		 
		 return list_cnt;
				
	}

}
