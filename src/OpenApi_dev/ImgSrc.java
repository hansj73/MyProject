package OpenApi_dev;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgSrc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				String imgUrl="http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF136774_170403_0218460.jpg@http://www.kopis.or.kr/upload/pfmIntroImage/PF_PF136774_170403_0218461.jpg@";
				BufferedImage image=null;
				String[] a=null;
				int width = 0;
				int height = 0;
				String PosterImgUrl="http://www.kopis.or.kr/upload/pfmPoster";
				String imageUrl="";
				
				 StringBuffer sb = new StringBuffer();
		        sb.append("<p>");
		        try {
		        	
		        	a=imgUrl.split("@");
		        	
		        	for(String i: a){
		        		System.out.println(i);
		        		if(!"".equals(i))sb.append("<img src=\""+i+"\"/><br/>");
		        	}
		        	sb.append("<p>");
		        	System.out.println(sb.toString());
		        	
		        		
		        	
		/*        	urlFileName=new String[]{"PF_PF136975_170417_110311.jpg","PF_PF137087_170421_104201.gif","PF_PF137079_170420_155443.gif","PF_PF136954_170414_100633.jpg"};
		        	
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
//			            System.out.println("::::fileNm::"+fileNm+"::file_ext:::"+file_ext);
			            String localPath="G:/downImage";
			            ImageIO.write(bufferdImage, file_ext, new File(localPath+"/"+fileNm));
//			            System.out.println(fileNm+"다운완료");
			            
		        	   }catch(Exception e){
		        	   }
		*/	        	
		        
		        	
		        } catch (Exception e) {
		         e.printStackTrace();
		        }
	}

}
