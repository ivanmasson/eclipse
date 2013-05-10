import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class inciso1 {
	
	BufferedImage img = null;
	
	int h=0;
	int w=0;
	
	int [][] matriz = null;
	int [] rgbArray = null;
	
	
	inciso1(){
		try {
		    img = ImageIO.read(new File("prueba2.jpg"));
		} catch (IOException e) {
		}
		h=img.getHeight();
		w=img.getWidth();
		matriz = new int[w][h];
		rgbArray = new int[w*h];
	}
	
	public long getPromedio(int matriz[][],int w, int h)
	{
		long suma=0;
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				suma+=matriz[i][j];
		
		
		return  suma/(w*h);
		
		
	}
	
	public void inicializar()
	{
		Raster r=img.getData();
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				matriz[i][j]=r.getSample(i, j, 0);
	}
	
	
	
	
	
	
}
