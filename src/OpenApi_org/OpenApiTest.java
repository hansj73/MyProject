package OpenApi_org;


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

public class OpenApiTest {

	public static void main(String[] args)  {
		
		
		  String Detail_apiXml="";
		  String List_apiXml="";
      
		  try {
			  /** 리스트조회***/
			 List_apiXml= apiData(); // 리스트 조회
//			 System.out.println(":::apiXml::"+List_apiXml);
			 ArrayList<KopisApiDto> list=processDocument(List_apiXml);
//			 System.out.println(":::list_size::"+list.size());
			  
			 int i=1;
			  //데이터 확인용
		        Iterator<KopisApiDto> iterator = list.iterator();
		        while (iterator.hasNext()) {
		        	KopisApiDto tmp = (KopisApiDto) iterator.next();
//		        	System.out.println("::::공연ID::mt20id:::"+tmp.getMt20id().toString());
		            /*System.out.println("::::data:::"+tmp.getPrfnm().toString());
		            System.out.println("::::data:::"+tmp.getPrfpdfrom().toString());
		            System.out.println("::::data:::"+tmp.getPrfpdto().toString());
		            System.out.println("::::data:::"+tmp.getFcltynm().toString());
		            System.out.println("::::data:::"+tmp.getPoster().toString());
		            System.out.println("::::data:::"+tmp.getGenrenm().toString());
		            System.out.println("::::data:::"+tmp.getPrfstate().toString());*/
		        	
		        	Detail_apiXml= apiDataDetail(tmp.getMt20id().toString()); // 리스트 조회
//					  System.out.println(":::apiXml::"+Detail_apiXml);
					  ArrayList<KopisApiDto> detail=processDocumentDetail(Detail_apiXml);
					 //데이터 확인용
				        Iterator<KopisApiDto> deiterator = detail.iterator();
				        while (deiterator.hasNext()) {
				        	KopisApiDto tmp1 = (KopisApiDto) deiterator.next();
				        
				        	System.out.print(i+"::mt20id::"+tmp1.getMt20id().toString());
				        /*    System.out.print("::prfnm:"+tmp1.getPrfnm().toString());
				            System.out.print("::prfpdfrom::"+tmp1.getPrfpdfrom().toString());
				            System.out.print("::prfpdto::"+tmp1.getPrfpdto().toString());
				            System.out.print("::fcltynm::"+tmp1.getFcltynm().toString());
				            System.out.print("::prfruntime::"+tmp1.getPrfruntime().toString());
				            System.out.print("::prfage::"+tmp1.getPrfage().toString());
				            System.out.print("::prfruntime::"+tmp1.getPrfruntime().toString());
				            System.out.print("::dentrpsnmata::"+tmp1.getEntrpsnm().toString());
				            System.out.print("::pcseguidance::"+tmp1.getPcseguidance().toString());
				            System.out.print("::poster::"+tmp1.getPoster().toString());
				            System.out.print("::genrenm::"+tmp1.getGenrenm().toString());
				            System.out.print("::prfstate::"+tmp1.getPrfstate().toString());
				            System.out.print("::dtguidance::"+tmp1.getDtguidance().toString());*/
				            System.out.print("::::data:::"+tmp1.getStyurl1().toString());
				            System.out.println("\n");

				        }
				        i++;	
		        	
		        }
		        
			  /** detail 페이지 조회 ***/
			  /*
			  Detail_apiXml= apiDataDetail("PF137106"); // 리스트 조회
			  System.out.println(":::apiXml::"+Detail_apiXml);
			  ArrayList<KopisApiDto> detail=processDocumentDetail(Detail_apiXml);
			 //데이터 확인용
		        Iterator<KopisApiDto> diterator = detail.iterator();
		        while (iterator.hasNext()) {
		        	KopisApiDto tmp = (KopisApiDto) diterator.next();
		        
		        	System.out.println("::::data:::"+tmp.getMt20id().toString());
		            System.out.println("::::data:::"+tmp.getPrfnm().toString());
		            System.out.println("::::data:::"+tmp.getPrfpdfrom().toString());
		            System.out.println("::::data:::"+tmp.getPrfpdto().toString());
		            System.out.println("::::data:::"+tmp.getFcltynm().toString());
		            System.out.println("::::data:::"+tmp.getPrfcrew().toString());
		            System.out.println("::::data:::"+tmp.getPrfruntime().toString());
		            System.out.println("::::data:::"+tmp.getStyurl1().toString());

		        }
*/			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
	 * @desc kopsi 공연예술 api 리스트 형태로 데이터 가지고 오기
	 * @return
	 * @throws IOException
	 */
	public static String apiData() throws IOException{
		
			StringBuilder urlBuilder = new StringBuilder("http://www.kopis.or.kr/openApi/restful/pblprfr"); 
	        urlBuilder.append("?service=23a9ef8a8db1420bb4c0044530ff15d0"); /*Service Key*/
	        urlBuilder.append("&stdate=20170501&eddate=20170531&cpage=1&rows=30&prfstate=01");
	        urlBuilder.append("&shprfnmfct="+ URLEncoder.encode("예술의전당","UTF-8") );
	        /*System.out.println("::::urlBuilder.toString():::"+urlBuilder.toString());*/
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
	}// apiData end
	
	/**
	 * @desc 리스트 에서 상세보기호출 
	 * @param mt20id
	 * @return
	 * @throws IOException
	 */
	public static String apiDataDetail(String mt20id) throws IOException{
		
		StringBuilder urlBuilder = new StringBuilder("http://www.kopis.or.kr/openApi/restful/pblprfr/"); 
        urlBuilder.append(mt20id+"?service=23a9ef8a8db1420bb4c0044530ff15d0"); /*Service Key*/
        URL url = new URL(urlBuilder.toString());
        System.out.println(":::::apiDataDetail::::"+urlBuilder.toString());
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
}// apiData end
	
	/**
	 * @desc 리스트 xml parser
	 * @param apiXml
	 * @return
	 */
	
	private static ArrayList<KopisApiDto>  processDocument(String apiXml)  {
	    
	    // xmlPullParser
		ArrayList<KopisApiDto> arrayList = new ArrayList<KopisApiDto>();
	    try {
	        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	        XmlPullParser parser = factory.newPullParser();
	        parser.setInput(new StringReader(apiXml));
	        int eventType = parser.getEventType();
	        KopisApiDto openData = null;
	
	        while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = parser.getName();
                        if(startTag.equals("db")) {
                        	openData = new KopisApiDto();
                        }
                        if(startTag.equals("mt20id")) {
                        	openData.setMt20id(parser.nextText());
                        }
                        if(startTag.equals("prfnm")) {
                        	openData.setPrfnm(parser.nextText());
                        }
                        if(startTag.equals("prfpdfrom")) {
                        	openData.setPrfpdfrom(parser.nextText());
                        }
                        if(startTag.equals("prfpdto")) {
                        	openData.setPrfpdto(parser.nextText());
                        }
                        if(startTag.equals("poster")) {
                        	openData.setPoster(parser.nextText());
                        }
                        if(startTag.equals("genrenm")) {
                        	openData.setGenrenm(parser.nextText());
                        }
                        if(startTag.equals("prfstate")) {
                        	openData.setPrfstate(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("db")) {
                            arrayList.add(openData);
                        }
                        break;
                }
                eventType = parser.next();
            }
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return arrayList;
	}// list end
	
	/**
	 * @desc 상세보기 xml parser
	 * @param apiXml
	 * @return
	 */
	private static ArrayList<KopisApiDto>  processDocumentDetail(String apiXml)  {
	    // xmlPullParser
		ArrayList<KopisApiDto> arrayList = new ArrayList<KopisApiDto>();
	    try {
	        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	        XmlPullParser parser = factory.newPullParser();
	        
	        parser.setInput(new StringReader(apiXml));
	        
	        int eventType = parser.getEventType();
	        KopisApiDto openData = null;
	
	        while(eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        String startTag = parser.getName();
                        if(startTag.equals("db")) {
                        	openData = new KopisApiDto();
                        }
                        if(startTag.equals("mt20id")) {
                        	openData.setMt20id(parser.nextText());
                        }
                        if(startTag.equals("prfnm")) {
                        	openData.setPrfnm(parser.nextText());
                        }
                        if(startTag.equals("prfpdfrom")) {
                        	openData.setPrfpdfrom(parser.nextText());
                        }
                        if(startTag.equals("prfpdto")) {
                        	openData.setPrfpdto(parser.nextText());
                        }
                        if(startTag.equals("fcltynm")) {
                        	openData.setFcltynm(parser.nextText());
                        }
                        if(startTag.equals("prfcrew")) {
                        	openData.setPrfcrew(parser.nextText());
                        }
                        if(startTag.equals("prfruntime")) {
                        	openData.setPrfruntime(parser.nextText());
                        }
                        if(startTag.equals("prfage")) {
                        	openData.setPrfage(parser.nextText());
                        }
                        if(startTag.equals("entrpsnm")) {
                        	openData.setEntrpsnm(parser.nextText());
                        }
                        if(startTag.equals("pcseguidance")) {
                        	openData.setPcseguidance(parser.nextText());
                        }
                        if(startTag.equals("poster")) {
                        	openData.setPoster(parser.nextText());
                        }
                        if(startTag.equals("genrenm")) {
                        	openData.setGenrenm(parser.nextText());
                        }
                        if(startTag.equals("prfstate")) {
                        	openData.setPrfstate(parser.nextText());
                        }
                        if(startTag.equals("dtguidance")) {
                        	openData.setDtguidance(parser.nextText());
                        }
                        if(startTag.equals("styurls")) {
                        	String styurl_img=processImageElement(parser);
                        	openData.setStyurl1(styurl_img);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if(endTag.equals("db")) {
                            arrayList.add(openData);
                        }
                        break;
                }
                eventType = parser.next();
            }
	        
	   }catch(XmlPullParserException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return arrayList;
	}//detail end
	
	/**
	 * @desc image xml parse
	 * @param xpp
	 * @return 이미지 값들을 ':' 구분자로 합쳐서 리턴한다
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public static String processImageElement (XmlPullParser xpp) throws XmlPullParserException, IOException
    {
		int eventType = xpp.getEventType();
		String img_url="";
        while (eventType != xpp.END_DOCUMENT) {
         	if(eventType == xpp.END_TAG) {
                if(xpp.getName().equals("styurls")){
                	break;
                }
            } else if(eventType == xpp.TEXT) {
            	/*else if 문 삭제하지 말것 에러발생*/
                /*System.out.println("styurl"+xpp.getText());
            	img_url=xpp.getText()+":"+img_url;*/
            } else if(xpp.getName().equals("styurl")){
            	/*System.out.println("styurl"+xpp.nextText());*/
            	img_url=xpp.nextText()+":"+img_url;
            }
            eventType = xpp.next();
        }
        /*System.out.println(":::img_url::"+img_url);*/
        return img_url;
    }
		
	
}// class end
