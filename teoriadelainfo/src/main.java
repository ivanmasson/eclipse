import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;
import javax.imageio.ImageIO;


public class main {
	public static long getPromedio(int matriz[][],int w, int h)
	{
		long suma=0;
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				suma+=matriz[i][j];
		
		
		return  suma/(w*h);
		
		
	}
	
	public static void main(String[] args) {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("actorPrincipal.bmp"));
		} catch (IOException e) {
		}
		int h=img.getHeight();
		int w=img.getWidth();
		
		int [][] matriz = new int[w][h];
		//int [] rgbArray = new int[w*h];
		
		Raster r=img.getData();
		
	//	img.getRGB(0, 0, w,h, rgbArray, 0, 0);
		
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				matriz[i][j]=r.getSample(i, j, 0);
			
		
		
		//int pixel=img.getRGB(1, 1)& 0x00ffffff;//elimino el alpha
		
	
		System.out.println(getPromedio(matriz,w,h)+" "+465*600);
		
	
	
	}

}
