package OpenApi_dev;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageTest {

	 public static void main(String[] argv) throws Exception {
		    String targetUrl="http://www.kopis.or.kr/upload/pfmPoster/PF_PF136954_170414_100633.jpg";
		    File file = new File(targetUrl);
		    System.out.println("::ffa::"+file.getName());
//		    FileOutputStream in = new FileOutputStream(file.getName());
		    FileOutputStream in = new FileOutputStream("G:/downImage/"+file.getName());
		  
		    URL url = new URL(targetUrl);
		    InputStream fi = url.openStream();
		    
		    int size;
		    while((size=fi.read())>-1){
		    	in.write(size);
		    }
		    in.close();
		    
		    
		    
//		    System.out.println("file::"+file);
//		    File file = new File("G:/downImage/PF_PF136960_170414_111619.gif");
//		    System.out.println(getFormatName(fi,targetUrl));
		    fi.close();
		  }
		  private static String getFormatName(Object o,String targetUrl) throws Exception {
		 			  
			  try {
			  BufferedImage bufferedImage=null;
			  ImageInputStream stream = ImageIO.createImageInputStream(o);
				 Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
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
			                    if (bufferedImageType == BufferedImage.TYPE_INT_BGR) {
			                        param.setDestinationType(imageTypeSpecifier);
			                        break;
			                    }
			                }
			                  bufferedImage = reader.read(0, param);
			                  System.out.println("break::4");
			                if (null != bufferedImage) 
			                {
//			                	System.out.println("break::"+bufferedImage);
			                	ImageRead(bufferedImage,targetUrl);
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
			  } catch (IOException e) {
			 }
			  return null;
		  }
		  
			public static void ImageRead(BufferedImage image,String imageUrl) throws IOException{
				
				//BufferedImage image=null;
				int width = 0;
				int height = 0;
				//String imageUrl="http://www.kopis.or.kr/upload/pfmPoster";
				//imageUrl=PosterImgUrl+"/"+;
				System.out.println(imageUrl);
			
		    	//image = ImageIO.read(new URL(imageUrl));
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
				
					
		  
}
