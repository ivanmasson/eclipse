
public class inciso2 {
	
	//Lo hago con la foto principal y otras dos cualquiera, pero preciso la lista ordenado por parecidos.
	
		public inciso2()
		{ 
			imagen img = new imagen("actorPrincipal.bmp");
			imagen img1 = new imagen("postulante1.bmp");
			imagen img2 = new imagen("postulante7.bmp");
			
			this.imprimir(img);
			this.imprimir(img1);
			this.imprimir(img2);
			
		}
		
		public void imprimir(imagen img)
		{
			int [] a = img.distribucionDeTonos();
			double p = img.getPromedio2();
			double d = img.getDesvio2();
			
			System.out.println("La probabilidad de la distribucion de tonos es: " + p + ".");
			System.out.println("El desvio de la distribucion de tonos es: "+ d + "."); 
			System.out.println("[Tono;Distribucion]");
			for(int i=0; i<a.length; i++)
					System.out.print("[" + i + ";" + a[i] + "]");
			System.out.println(" ");
			System.out.println("------------------------------------------------------------");
		}

}
