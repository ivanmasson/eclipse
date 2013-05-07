import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class main {
	public static float getMediaArit(int vector[])
	{
		int suma=0;
		for (int i=0;i<vector.length;i++)
			suma+=vector[i];
		return (suma/vector.length);
		
		
	}
	
	public static void main(String[] args) {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("prueba.jpg"));
		} catch (IOException e) {
		}
		int h=img.getHeight();
		int w=img.getWidth();
		int [] rgbArray = new int[w*h];
		img.getRGB(0, 0, w,h, rgbArray, 0, 0);
		
		//int pixel=img.getRGB(1, 1)& 0x00ffffff;//elimino el alpha
		
		System.out.println(getMediaArit(rgbArray));
		
	
	
	}

}
