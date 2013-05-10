import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;


public class inciso1 {
	
	
	
	
	inciso1(){
		imagen img=new imagen("actorPrincipal.bmp");
		imagen img1=new imagen("postulante1.bmp");
		imagen img2=new imagen("postulante2.bmp");
		imagen img3=new imagen("postulante3.bmp");
		imagen img4=new imagen("postulante4.bmp");
		imagen img5=new imagen("postulante5.bmp");
		imagen img6=new imagen("postulante6.bmp");
		imagen img7=new imagen("postulante7.bmp");
		List<Double> a= new ArrayList<Double>();
		//a.add(img.getCoorelation(img));
		
		a.add(img.getCoorelation(img1));
		a.add(img.getCoorelation(img2));
		a.add(img.getCoorelation(img3));
		a.add(img.getCoorelation(img4));
		a.add(img.getCoorelation(img5));
		a.add(img.getCoorelation(img6));
		a.add(img.getCoorelation(img7));
		double aux=0;
		double max=-999;
		int indice=0;
		
		for(int i=0;i<a.size();i++)
		{	aux=a.get(i);
		
			if (aux>max)
				{max=aux;
				indice=i;
				}
				
		}
		
		indice++;
		System.out.println("El postulante mas parecido es el: "+indice+", Factor de Coorelacion: "+ max);
		
	}
	
	
	
	
	
	
	
	

	

	
	
	
	
	
}
