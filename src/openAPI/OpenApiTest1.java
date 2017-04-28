package openAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;


public class OpenApiTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			try {
				String xmlData = restClient1();
				
				System.out.println(":::::xmlData:::"+xmlData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	
	public static String restClient() throws IOException
	{
		
	       StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=JEnT6vJMpxYCducETV3cOxnRr552yZV8B2LavF0NnY0QiOVROMvZdXACBLeDMPY2p4a4dH3mMHmRj66IEh4YUA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("파라미터영문명","UTF-8") + "=" + URLEncoder.encode("파라미터기본값", "UTF-8")); /*파라미터설명*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        return "afasdF";
	    }
	
	public static String restClient1() throws IOException
	{
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon"+"?ServiceKey=";
		String serviceKey = "JEnT6vJMpxYCducETV3cOxnRr552yZV8B2LavF0NnY0QiOVROMvZdXACBLeDMPY2p4a4dH3mMHmRj66IEh4YUA%3D%3D";
		
		String parameter = "";
		
		//인증키(서비스키) url인코딩
		serviceKey = URLEncoder.encode(serviceKey, "UTF-8");
		
		/* parameter setting
		 * parameter = parameter + "&" + "PARAM1=AAA";
		 * parameter = parameter + "&" + "PARAM2=BBB";
		 * parameter = parameter + "&" + "PARAM3=CCC";
		 * */
		
		addr = addr + serviceKey + parameter;
		
		URL url = new URL(addr);
		InputStream in = url.openStream(); 
		CachedOutputStream bos = new CachedOutputStream();
		IOUtils.copy(in, bos);
		in.close();
		bos.close();
		return bos.getOut().toString();
	    }
		
}


