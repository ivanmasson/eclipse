import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;
import javax.imageio.ImageIO;


public class main {
	
	
	public static void main(String[] args) {
		
		inciso1 i1= new inciso1();
		imagen img=new imagen("actorPrincipal.bmp");
		imagen img1=new imagen("postulante1.bmp");
		imagen img2=new imagen("postulante2.bmp");
		imagen img3=new imagen("postulante3.bmp");
		imagen img4=new imagen("postulante4.bmp");
		imagen img5=new imagen("postulante5.bmp");
		imagen img6=new imagen("postulante6.bmp");
		imagen img7=new imagen("postulante7.bmp");
		
		System.out.println(img.getCoorelation(img));
		System.out.println(img.getCoorelation(img1));
		System.out.println(img.getCoorelation(img2));
		System.out.println(img.getCoorelation(img3));
		System.out.println(img.getCoorelation(img4));
		System.out.println(img.getCoorelation(img5));
		System.out.println(img.getCoorelation(img6));
		System.out.println(img.getCoorelation(img7));
		
		
		
		
		
		
		
	
		
		
	
	
	}

}
