import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class main {
	public static void main(String[] args) {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("prueba.jpg"));
		} catch (IOException e) {
		}
	if (img!= null)
		System.out.println(img.getHeight());
	
	
	
	
	
	}

}
