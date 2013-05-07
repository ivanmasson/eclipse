import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;


public class main {
	public static long getCoorrelacion(int vector[],int vector2[])
	{
		long suma=0;
		for (int i=0;i<vector.length;i++)
			suma=suma+((vector[i]& 0x00ffffff)*(vector2[i]& 0x00ffffff));
		return (suma/vector.length);
		
		
	}
	
	public static void main(String[] args) {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("actorPrincipal.bmp"));
		} catch (IOException e) {
		}
		int h=img.getHeight();
		int w=img.getWidth();
		int [] rgbArray = new int[w*h];
		img.getRGB(0, 0, w,h, rgbArray, 0, 0);
		
		//int pixel=img.getRGB(1, 1)& 0x00ffffff;//elimino el alpha
		
		System.out.println(getCoorrelacion(rgbArray,rgbArray));
		
	
	
	}

}
