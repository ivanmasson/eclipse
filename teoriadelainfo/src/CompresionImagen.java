import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;


public class CompresionImagen {
	
	private static final int N = 256;
	
	CompresionImagen() {
		double p[] = new double[256];
		
		for(int i = 0; i < 256; i++)
			p[i] = 0.00390625;
		
		Nodo n = huffman(p);
		Hashtable<Integer,String> cod = new Hashtable<Integer,String>();
		String s = new String();
		armar_tabla_codif(cod,n,s);
		
		codificarImagen(cod,"actorPrincipal.bmp",p,"actorCompr.cmp");
	}
	
	// Toma un buffer con la codificacion completa en binario de la imagen, lo empaqueta en paquetes de 16 bits y guarda en el archivo que se pasa como parametro en la ruta
	public void guardarCodificacion(StringBuffer bf, String ruta, double p[], int alto, int ancho) {
		File f = new File(ruta);
		FileOutputStream arch_comp = null;
		try {
			arch_comp = new FileOutputStream(f);
			DataOutputStream data = new DataOutputStream(arch_comp);
			
			//ESCRIBO LA RESOLUCION DE LA IMAGEN EN LAS 2 PRIMERAS LINEAS DEL ARCHIVO...
			data.writeInt(alto);
			data.writeChar('\n');
			data.writeInt(ancho);
			data.writeChar('\n');
			
			//AQUI ABAJO SE ESCRIBE LA INFO PARA LA DESCOMPRESION, OSEA EL VECTOR DE PROB
			for(int i = 0; i < N; i++) {
				data.writeDouble(p[i]);
				data.writeChar('\n');
			}
			
			int buffer = 0;
			int cant_digitos = 0;
		
			for(int i = 0; i < bf.length(); i++) {
				buffer = buffer<<1;
				char bit =  bf.charAt(i);
				if(bit == 1)
					buffer = buffer | 1;
				cant_digitos++;
				if(cant_digitos == 16) {
					data.writeChar(buffer);
					buffer = 0;
					cant_digitos = 0;
				}
			}
		
			if((cant_digitos < 16) && (cant_digitos != 0)) {
				buffer = buffer << (16 - cant_digitos);
				data.writeChar(buffer);
			}
			data.writeChar('\n');
						
			data.flush();
			data.close();
		}
		catch(Exception e){}
		
		finally{
			try {
				if(arch_comp != null)
					arch_comp.close();
			}
			catch (Exception e) {}
		}
			
	}
	

	public void codificarImagen(Hashtable<Integer,String> cod, String ruta, double p[], String ruta_dest) {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(ruta));
		} catch (IOException e) {
		}
		Raster r = img.getData();
		StringBuffer codificacion = new StringBuffer();
		
		for(int i = 0; i < r.getWidth(); i++)
			for(int j = 0; j < r.getHeight(); j++)
				codificacion.append(cod.get(r.getSample(i, j, 0))); // Pongo toda la codificacion entera de la imagen que saco de la tabla en un Buffer
		
		//Ahora empaqueto los unos y ceros del buffer en paquetes de 16 bits y los escribo en el archivo con el metodo dado que dio la catedra (modificado)
		guardarCodificacion(codificacion,ruta_dest,p,r.getHeight(),r.getWidth());
		
	}
	
	//Arma la tabla de codificacion para cada tono mediante el arbol de huffman recorriendolo en preorden, para el parametro string se le pasa un string vacio
	public void armar_tabla_codif(Hashtable<Integer,String> cod, Nodo n, String s) {
		
		if(n != null) {
			if(n.getTono() != -1)
				cod.put(n.getTono(), s);
			armar_tabla_codif(cod,n.getIzq(),s.concat("0"));
			armar_tabla_codif(cod,n.getDer(),s.concat("1"));
		}
			
	}
	
	// p[] --> Vector de probabilidades de los 256 tonos
	
	public static Nodo huffman(double p[]) {
		PriorityQueue<Nodo> q = new PriorityQueue<Nodo>();
		for(int i=0; i<N; i++)
			if(p[i] != 0.0)
				q.add(new Nodo(i,p[i],null,null));
		while(q.size() > 1) {
			Nodo a = q.remove();
			Nodo b = q.remove();
			Nodo c = new Nodo(-1,a.getProb()+b.getProb(),a,b);
			q.add(c);
		}
		return q.element();
	}
	

}