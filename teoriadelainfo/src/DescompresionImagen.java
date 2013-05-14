import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class DescompresionImagen {
	 
	private static final int N = 256;
	
	DescompresionImagen() {
		descomprimirImagen("actorCompr.cmp", "franchute.bmp");
	}
	
	private StringBuffer generaBits(int num) {
		StringBuffer s = new StringBuffer();
		int mascara = 1 << 15;
		for(int i = 0; i<16; i++) {
			if((num & mascara) == 32768)
				s.append("1");
			else
				s.append("0");
			num = num << 1;
		}
		return s;
			
	}
	
	
	//Le paso el arbol de huffman reconstruido con el vector de prob, la ruta imagen comprimida y la ruta donde guardar la img descomprimida
	public void descomprimirImagen(String ruta_img_comprimida, String ruta_destino) {
		FileInputStream arch_comp = null;
		DataInputStream data = null;
		try {
			arch_comp = new FileInputStream(ruta_img_comprimida);
			data = new DataInputStream(arch_comp);
			
			int alto = data.readInt();
			data.readChar();
			int ancho = data.readInt();
			data.readChar();
			
			double p[] = new double[N]; //reconstruyo el vector de prob ERRORRRRRRRRR--->>> escribe el vector todo en cero
			for(int i = 0; i < N; i++) {
				p[i] = data.readDouble();
				data.readChar();
			}
			
//			for(int i = 0; i < 256; i++)
//				p[i] = 0.00390625;
			
			Nodo arbol = CompresionImagen.huffman(p); //reconstruyo el arbol de huffman
			
			StringBuffer bf = new StringBuffer();
			while(data.available() != 0) { //mientras haya caracteres para leer genero sus bits en un buffer
				int b = data.readChar();
				bf.append(generaBits(b));
			}
			
			data.close();
			arch_comp.close();
			
			
			
			
			int pixels[] = getVectorPixels(arbol,bf,alto,ancho);
			for(int i = 0; i< 279000; i++)
				System.out.println(pixels[i]);
					
//			int m[][] = armarMatrizImagen(arbol,bf,alto,ancho); //DEPRECATED
			
			BufferedImage img = getImageFromArray(pixels,ancho,alto);  //transformo el vector de pixeles en una imagen
									
			File archivo_destino = new File(ruta_destino); //guardo la imagen en el destino
			ImageIO.write(img, "bmp", archivo_destino);
			
		}
		catch(Exception e){e.printStackTrace();};
	}
	

	public BufferedImage getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0,0,width,height,pixels);
        return image;
    }
	
	
	public int[] getVectorPixels(Nodo arbol, StringBuffer bf, int alto, int ancho) {
		Nodo it = arbol;
		int pixels[] = new int[ancho*alto*10];
		int x = 0;
		System.out.println("largo bf: " + bf.length());
		for(int i = 0; i < bf.length(); i++) {
		
			if(bf.charAt(i) == '0')
				it = it.getIzq();
			if(bf.charAt(i) == '1')
				it = it.getDer();
		
			if((it.getIzq() == null) && (it.getDer() == null)) {
				pixels[x] = it.getTono();
				x++;
				it = arbol;
			}
		}
		return pixels;
	}
	
	
	//DEPRECATED
//	public static int[][] armarMatrizImagen(Nodo arbol, StringBuffer bf, int alto, int ancho) {
//		Nodo it = arbol;
//		int m[][] = new int[ancho][alto];
//		int x = 0;
//		int y = 0;
//		for(int i = 0; i < bf.length(); i++) {
//			
//			if(bf.charAt(i) == 0)
//				it = it.getIzq();
//			else
//				it = it.getDer();
//			
//			if((it.getIzq() == null) && (it.getDer() == null)) {
//				m[x][y] = it.getTono();
//				if(y < ancho)
//					y++;
//				else {
//					x++; y=0;
//				}
//				it = arbol;
//			}
//		}
//		return m;
//	}

}
