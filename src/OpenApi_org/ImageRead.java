package OpenApi_org;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.BufferUnderflowException;

import javax.imageio.ImageIO;



public class ImageRead {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedImage image=null;
		String urlFileName[]=null;
		int width = 0;
		int height = 0;
		String PosterImgUrl="http://www.kopis.or.kr/upload/pfmPoster";
		String imageUrl="";
		
        
        try {
//        	imageUrl="http://www.kopis.or.kr/upload/pfmPoster/PF_PF137087_170421_104201.gif";
        	
        	urlFileName=new String[]{"PF_PF136975_170417_110311.jpg","PF_PF137087_170421_104201.gif","PF_PF137079_170420_155443.gif","PF_PF136954_170414_100633.jpg"};
        	
        	for(String i : urlFileName)
        	{
        		imageUrl=PosterImgUrl+"/"+i;
        		System.out.println(imageUrl);
        	   try{
	        	image = ImageIO.read(new URL(imageUrl));
	        	BufferedImage bufferdImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_BGR);
	        	Graphics2D graphics = (Graphics2D)bufferdImage.getGraphics();
	        	graphics.setBackground(Color.WHITE);
	        	graphics.drawImage(image,0,0,null);
	            String fileNm = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	            String file_ext = fileNm.substring(fileNm.lastIndexOf('.')+1,fileNm.length());
//	            System.out.println("::::fileNm::"+fileNm+"::file_ext:::"+file_ext);
	            String localPath="G:/downImage";
	            ImageIO.write(bufferdImage, file_ext, new File(localPath+"/"+fileNm));
//	            System.out.println(fileNm+"다운완료");
	            
        	   }catch(Exception e){
        		   EtcImageWrite(imageUrl);
        	   }
	        	
        	}
        	
        } catch (IOException e) {
         e.printStackTrace();
        }
	}
	
	 public static void EtcImageWrite(String targetUrl)throws Exception {
		 
//		    String targetUrl="http://www.kopis.or.kr/upload/pfmPoster/PF_PF136954_170414_100633.jpg";
		    File file = new File(targetUrl);
		    System.out.println("::ffa::"+file.getName());
		    FileOutputStream in = new FileOutputStream("G:/downImage/"+file.getName());
		  
		    URL url = new URL(targetUrl);
		    InputStream fi = url.openStream();
		    
		    int size;
		    while((size=fi.read())>-1){
		    	in.write(size);
		    }
		    in.close();
		 
		 System.out.println("::::EtcImageWrite::"+targetUrl);
	 }
		    

}
