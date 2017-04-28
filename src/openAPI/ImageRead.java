package openAPI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.BufferUnderflowException;

import javax.imageio.ImageIO;



public class ImageRead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedImage image=null;
		String urlFileName[]=null;
		int width = 0;
		int height = 0;
		String PosterImgUrl="http://www.kopis.or.kr/upload/pfmPoster";
		String imageUrl="";
        
        try {
//        	imageUrl="http://www.kopis.or.kr/upload/pfmPoster/PF_PF137087_170421_104201.gif";
        	
        	urlFileName=new String[]{"PF_PF137106_170421_135430.jpg","PF_PF137087_170421_104201.gif","PF_PF137079_170420_155443.gif","PF_PF137077_170420_153746.gif"};
        	
        	for(String i : urlFileName)
        	{
        		imageUrl=PosterImgUrl+"/"+i;
        		System.out.println(imageUrl);
        	
	        	image = ImageIO.read(new URL(imageUrl));
	        	BufferedImage bufferdImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_BGR);
	        	Graphics2D graphics = (Graphics2D)bufferdImage.getGraphics();
	        	graphics.setBackground(Color.WHITE);
	        	graphics.drawImage(image,0,0,null);
	            String fileNm = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	            String file_ext = fileNm.substring(fileNm.lastIndexOf('.')+1,fileNm.length());
	            System.out.println("::::fileNm::"+fileNm+"::file_ext:::"+file_ext);
	            String localPath="G:/downImage";
	            ImageIO.write(bufferdImage, file_ext, new File(localPath+"/"+fileNm));
	            System.out.println(fileNm+"다운완료");
        	}
        	
        } catch (IOException e) {
         e.printStackTrace();
        }
	}

}
