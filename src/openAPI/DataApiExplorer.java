package openAPI;


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
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


public class DataApiExplorer {

	public static void main(String[] args)  {
      
		  try {
			String apiXml= apiData();
			
			ArrayList<DataApiDto> list=processDocument(apiXml);
			/*System.out.println(":::apiXml::"+apiXml);*/
			/*System.out.println(":::list_size::"+list.size());*/
			
			 //데이터 확인용
		       /** 리스트용 데이터 ***/
			    Iterator<DataApiDto> iterator = list.iterator();
		        while (iterator.hasNext()) {
		        	DataApiDto tmp = (DataApiDto) iterator.next();
		        	System.out.println("::::data:::"+tmp.getAddr1().toString());
		            System.out.println("::::data:::"+tmp.getAddr2().toString());
		            System.out.println("::::data:::"+tmp.getAreacode().toString());
		            System.out.println("::::data:::"+tmp.getCat1().toString());
		            System.out.println("::::data:::"+tmp.getCat2().toString());
		            System.out.println("::::data:::"+tmp.getCat3().toString());
		            System.out.println("::::data:::"+tmp.getContentid().toString());
		            System.out.println("::::data:::"+tmp.getContenttypeid().toString());
		            System.out.println("::::data:::"+tmp.getCreatedtime().toString());
		            System.out.println("::::data:::"+tmp.getFirstimage().toString());
		            System.out.println("::::data:::"+tmp.getFirstimage2().toString());
		            System.out.println("::::data:::"+tmp.getMapx().toString());
		            System.out.println("::::data:::"+tmp.getMapy().toString());
		            System.out.println("::::data:::"+tmp.getSigungucode().toString());
		            System.out.println("::::data:::"+tmp.getTel().toString());
		            System.out.println("::::data:::"+tmp.getTitle().toString());
		            System.out.println("::::data:::"+tmp.getZipcode().toString());
		        }
		        
		      
				/**
				 * return 
				 * totalCount,pageNo,numOfRows
				 */
		        processEtcElement(apiXml);
				ArrayList<DataApiDto> listData=processEtcElement(apiXml);
					
				System.out.println("::listData::"+listData.get(0).getTotalCount());
				System.out.println("::listData::"+listData.get(0).getPageNo());
				System.out.println("::listData::"+listData.get(0).getNumOfRows());
					
				
			
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public static String apiData() throws IOException{
		
			StringBuilder urlBuilder = new StringBuilder("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList"); 
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=JEnT6vJMpxYCducETV3cOxnRr552yZV8B2LavF0NnY0QiOVROMvZdXACBLeDMPY2p4a4dH3mMHmRj66IEh4YUA%3D%3D"); /*Service Key*/
	        urlBuilder.append("&contentTypeId=15&areaCode=&sigunguCode=&listYN=Y&MobileOS=ETC&MobileApp=web&arrange=A&numOfRows=12&pageNo=1");
	        urlBuilder.append("&cat1=A02&cat2=A0207"); // 축제 cat1=A02 축제 중분류 cat2=A0207 축제
	        /*urlBuilder.append("&cat1=A02&cat2=A0208"); // 공연/행사  cat2=A*/
	        /*System.out.println("::url::"+urlBuilder.toString());*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/xml");
	        /*System.out.println("Response code: " + conn.getResponseCode());*/
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
	
	private static ArrayList<DataApiDto>  processDocument(String apiXml)  {
	    
	    // xmlPullParser
		ArrayList<DataApiDto> arrayList = new ArrayList<DataApiDto>();
	    try {
	    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	    	XmlPullParser parser = factory.newPullParser();
	    	parser.setInput(new StringReader(apiXml));
	    	
	        int eventType = parser.getEventType();
	        DataApiDto openData = null;
	        while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = parser.getName();
                        if(startTag.equals("item")) {
                        	openData = new DataApiDto();
                        }
                        if(startTag.equals("addr1")) {
                        	openData.setAddr1(parser.nextText());
                        }
                        if(startTag.equals("addr2")) {
                        	openData.setAddr2(parser.nextText());
                        }
                        if(startTag.equals("areacode")) {
                        	openData.setAreacode(parser.nextText());
                        }
                        if(startTag.equals("cat1")) {
                        	openData.setCat1(parser.nextText());
                        }
                        if(startTag.equals("cat2")) {
                        	openData.setCat2(parser.nextText());
                        }
                        if(startTag.equals("cat3")) {
                        	openData.setCat3(parser.nextText());
                        }
                        if(startTag.equals("contentid")) {
                        	openData.setContentid(parser.nextText());
                        }
                        if(startTag.equals("contenttypeid")) {
                        	openData.setContenttypeid(parser.nextText());
                        }
                        if(startTag.equals("createdtime")) {
                        	openData.setCreatedtime(parser.nextText());
                        }
                        if(startTag.equals("firstimage")) {
                        	openData.setFirstimage(parser.nextText());
                        }
                        if(startTag.equals("firstimage2")) {
                        	openData.setFirstimage2(parser.nextText());
                        }
                        if(startTag.equals("mapx")) {
                        	openData.setMapx(parser.nextText());
                        }
                        if(startTag.equals("mapy")) {
                        	openData.setMapy(parser.nextText());
                        }
                        if(startTag.equals("mlevel")) {
                        	openData.setMlevel(parser.nextText());
                        }
                        if(startTag.equals("modifiedtime")) {
                        	openData.setModifiedtime(parser.nextText());
                        }
                        if(startTag.equals("sigungucode")) {
                        	openData.setSigungucode(parser.nextText());
                        }
                        if(startTag.equals("title")) {
                        	openData.setTitle(parser.nextText());
                        }
                        if(startTag.equals("zipcode")) {
                        	openData.setZipcode(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("item")) {
                            arrayList.add(openData);
                        }
                        break;
                }
                eventType = parser.next();
            }// while end
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return arrayList;
	}
	
	public static  ArrayList<DataApiDto> processEtcElement (String apiXml)
    {
		ArrayList<DataApiDto> arrayList = new ArrayList<DataApiDto>();
		try
		{
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
    	XmlPullParser parser = factory.newPullParser();
    	parser.setInput(new StringReader(apiXml));
    	
		int eventType = parser.getEventType();
		DataApiDto openData = null;
		 while(eventType != XmlPullParser.END_DOCUMENT) {
             switch (eventType) {
                 case XmlPullParser.START_TAG:
                     String startTag = parser.getName();
                     if(startTag.equals("response")) {
                     	openData = new DataApiDto();
                     }
                     if(startTag.equals("numOfRows")) {
                     	openData.setNumOfRows(parser.nextText());
                     }
                     if(startTag.equals("pageNo")) {
                     	openData.setPageNo(parser.nextText());
                     }
                     if(startTag.equals("totalCount")) {
                     	openData.setTotalCount(parser.nextText());
                     }
                     break;
                 	case XmlPullParser.END_TAG:
                     String endTag = parser.getName();
                     if(endTag.equals("response")) {
                         arrayList.add(openData);
                     }
                     break;
             }
             eventType = parser.next();
         }// while end
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return arrayList;
    }
}// class end
