package OpenApi_org;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class ApiExplorer1 {

	public static void main(String[] args)  {
      
		  try {
			String apiXml= apiData();
			
			System.out.println(":::apiXml::"+apiXml);
			
			processDocument(apiXml);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static String apiData() throws IOException{
		
		
//		  StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode "); /*URL*/  /*지역코드*/
//		  StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"); /*URL*/  /*지역코드*/
		StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"); 
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=JEnT6vJMpxYCducETV3cOxnRr552yZV8B2LavF0NnY0QiOVROMvZdXACBLeDMPY2p4a4dH3mMHmRj66IEh4YUA%3D%3D"); /*Service Key*/
        urlBuilder.append("&contentTypeId=15&areaCode=&sigunguCode=&listYN=Y&MobileOS=ETC&MobileApp=web&arrange=A&numOfRows=5&pageNo=1");
        urlBuilder.append("&cat1=A02&cat2=A0207"); // 축제 cat1=A02 축제 중분류 cat2=A0207 축제
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/xml");
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
	        //System.out.println(sb.toString());
	        
	        return sb.toString();
		
	}
	
	private static void  processDocument(String apiXml)  {
	    
	    // xmlPullParser
	    try {
	        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	       XmlPullParser parser = factory.newPullParser();
	        parser.setInput(new StringReader(apiXml));
	        int eventType = parser.getEventType();
	       
	
	        do {
	            if(eventType == parser.START_DOCUMENT) {
	                System.out.println("Start document");
	            } else if(eventType == parser.END_DOCUMENT) {
	                System.out.println("End document");
	            } else if(eventType == parser.START_TAG) {
	                processStartElement(parser);
	            } else if(eventType == parser.END_TAG) {
	                processEndElement(parser);
	            } else if(eventType == parser.TEXT) {
	                processText(parser);
	            }
	            eventType = parser.next();
	        } while (eventType != parser.END_DOCUMENT);
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	
	    //return xmlData;
	}
	
	public static void processStartElement (XmlPullParser xpp)
    {
        String name = xpp.getName();
        String uri = xpp.getNamespace();
        if ("".equals (uri)) {
            System.out.println("Start element: " + name);
        } else {
            System.out.println("Start element: {" + uri + "}" + name);
        }
    }
	
	 public static void processEndElement (XmlPullParser xpp)
	    {
	        String name = xpp.getName();
	        String uri = xpp.getNamespace();
	        if ("".equals (uri))
	            System.out.println("End element: " + name);
	        else
	            System.out.println("End element:   {" + uri + "}" + name);
	    }
	 
	static int holderForStartAndLength[] = new int[2];

	 public static void processText (XmlPullParser xpp) throws XmlPullParserException
	    {
	        char ch[] = xpp.getTextCharacters(holderForStartAndLength);
	        int start = holderForStartAndLength[0];
	        int length = holderForStartAndLength[1];
	        System.out.print("Characters:    \"");
	        for (int i = start; i < start + length; i++) {
	            switch (ch[i]) {
	                case '\\':
	                    System.out.print("\\\\");
	                    break;
	                case '"':
	                    System.out.print("\\\"");
	                    break;
	                case '\n':
	                    System.out.print("\\n");
	                    break;
	                case '\r':
	                    System.out.print("\\r");
	                    break;
	                case '\t':
	                    System.out.print("\\t");
	                    break;
	                default:
	                    System.out.print(ch[i]);
	                    break;
	            }
	        }
	        System.out.print("\"\n");
	    }
	 }
