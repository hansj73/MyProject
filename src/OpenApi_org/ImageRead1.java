package OpenApi_org;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;



public class ImageRead1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Image image = null;
		String imageUrl="";
		int width = 0;
		int height = 0;

        try {
        	imageUrl="http://www.kopis.or.kr/upload/pfmPoster/PF_PF136954_170414_100633.jpg";
            URL url = new URL(imageUrl);
            String fileNm = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            String file_ext = fileNm.substring(fileNm.lastIndexOf('.')+1,fileNm.length());
            System.out.println("::::fileNm::"+fileNm+"::file_ext:::"+file_ext);
            
            BufferedImage img = ImageIO.read(url);
            File file=new File("G:/downImage/test1.jpg");
            ImageIO.write(img, "jpg", file);
            
            
        } catch (IOException e) {
         e.printStackTrace();
        }

		
	}

}
