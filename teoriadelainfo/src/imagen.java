import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class imagen {
	BufferedImage img = null;
	
	int h=0;
	int w=0;
	
	int [][] matriz = null;
	
	
	imagen(String path){
		
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		}
		h=img.getHeight();
		w=img.getWidth();
		matriz = new int[w][h];
		Raster r=img.getData();
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				matriz[i][j]=r.getSample(i, j, 0);
	}
	
	public int getAncho()
	{
		return w;
	}
	public int getAlto()
	{
		return h;
	}
	public int[][] getMatriz()
	{
		return matriz;
	}
	
	public double getDesvio()
	{
		double promedio=getPromedio();
		//System.out.println(promedio);
		double promedio2=0;
		
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				promedio2=promedio2+Math.pow(matriz[i][j],2);
		promedio2=promedio2/(w*h);
		//System.out.println(Math.sqrt(suma/(w*h)));
		
		return Math.sqrt(promedio2-Math.pow(promedio,2));
	}
	
	public double getPromedio()
	{
		double suma=0;
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				suma+=matriz[i][j];
		
		
		return  suma/(w*h);
		
		
	}
	
	public double getCov(imagen img1)
	{
		double suma=0;
		
		int[][] matriz1=img1.getMatriz();
		double promedio=getPromedio();
		double promedio2=img1.getPromedio();
		
		for(int i=0; i<w;i++)
			for(int j=0; j<h;j++)
				suma=suma+((matriz[i][j])*(matriz1[i][j]));
		
		suma=suma/(w*h);
		
		double prom1=getPromedio();
		double prom2=img1.getPromedio();
		
		//System.out.println(suma);
		
		return  suma-(prom1*prom2);
		
		
	}
	
	public double getCoorelation(imagen img1)
	{
		
		double coovarianza=getCov(img1);
		double desvio1=getDesvio();
	//	System.out.println(desvio1);
		double desvio2=img1.getDesvio();
		//System.out.println(desvio2);
		return coovarianza/(desvio1*desvio2);
		
	}
	
}
