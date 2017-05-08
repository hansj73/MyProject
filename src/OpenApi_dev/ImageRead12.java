package OpenApi_dev;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;

public class ImageRead12 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String stream="http://www.kopis.or.kr/upload/pfmPoster/PF_PF136975_170417_110311.jpg";
		BufferedImage bufferedImage=null;
		System.out.println("break::1");
		
		BufferedImage image = ImageIO.read(new URL(stream));
		
		System.out.println("break::11"+image);
		
		File ff = new File(stream);
		
		System.out.println("::fff::"+ff);
		
		 Iterator<ImageReader> iter = ImageIO.getImageReaders(ff);
		 System.out.println("break::2");
		    Exception lastException = null;
		    System.out.println("break::3::"+iter.hasNext());
	        while (iter.hasNext()) {
	            ImageReader reader = null;
	            try {
	                reader = (ImageReader)iter.next();
	                ImageReadParam param = reader.getDefaultReadParam();
	                reader.setInput(stream, true, true);
	                Iterator<ImageTypeSpecifier> imageTypes = reader.getImageTypes(0);
	                while (imageTypes.hasNext()) {
	                    ImageTypeSpecifier imageTypeSpecifier = imageTypes.next();
	                    int bufferedImageType = imageTypeSpecifier.getBufferedImageType();
	                    if (bufferedImageType == BufferedImage.TYPE_BYTE_GRAY) {
	                        param.setDestinationType(imageTypeSpecifier);
	                        break;
	                    }
	                }
	                  bufferedImage = reader.read(0, param);
	                  System.out.println("break::4");
	                if (null != bufferedImage) 
	                {
	                	System.out.println("break::");
	                		break;
	                	
	                }
	            } catch (Exception e) {
	                lastException = e;
	            } finally {
	                if (null != reader) reader.dispose();               
	            }
	        }
	        // If you don't have an image at the end of all readers
	        if (null == bufferedImage) {
	            if (null != lastException) {
	                throw lastException;
	            }
	        }

	}

}
