package mapa;

import java.util.Vector;
import java.io.*;

/*
 * Los valores para las celdas son:
 * 
 * libre
 * avion
 * montana
 * turbulencia
 * tormenta
 * viento
 * 
 */
public class mapa {
	int x;	//tamagno del mapa (0 .. x-1)
	int y; 
	Vector tablero;  //contenido
	
	/*
	 * Constructores, vacío y con parámetros.
	 */
	
	public mapa() {
		super();
		// TODO Auto-geunnerated constructor stub
	}
	
	/*
	 * Se da el tamano en x (filas) e y (columnas).
	 * 
	 * Generamos un vector de n elementos y en cada
	 * posicion metemos un vector de y elementos.
	 * 
	 * Lo genermos vacio, todas las celdas libres.
	 */
	
	public mapa(int x, int y) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.tablero = new Vector();
		for (int i = 0; i < x; i ++){
			Vector aux = new Vector();
			for (int j = 0; j < y; j ++){
				aux.add(j,"libre");
			}	
			this.tablero.add(i, aux);
		}
	}

	/*
	 * Acessores y mutadores para tablero, x e y.
	 */
	
	public Vector getTablero() {
		return tablero;
	}

	public void setTablero(Vector tablero) {
		this.tablero = tablero;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	/*
	 * Métodos para acceder fácilmente a las celdas.
	 */
	
	/*
	 * Al acceder a la celda, si es de fuera del tablero
	 * devuleve la cadena vacía. Sino devuelve el contenido.
	 */
	
	public String dameCelda (int x, int y){
		Vector aux;
		if ((x<0)||(x>=this.getX())||(y<0)||(y>=this.getY())){
			return "";
		}
		else {
			aux = (Vector)this.tablero.elementAt(x);
			String celda = (String)aux.elementAt(y);
			return celda;
		}	
	}
	
	/*
	 * Sustituye el contenido de la celda, ponemos
	 * el contenido que les pasamos.
	 */
	public void ponCelda (int x, int y, String val){
		((Vector)this.tablero.elementAt(x)).set(y,val);		
	}
	
	/*
	 * Metodo para leer de consola
	 */
	
	/*
	 * Metodo para leer de consola
	 */
	public String readString(){
		BufferedReader br
	      = new BufferedReader(new InputStreamReader(System.in), 1);

	    // Declare and initialize the string
	    String s = "";

	    // Get the string from the keyboard
	    try
	    {
	      s = br.readLine();
	    }
	    catch (IOException ex)
	    {
	      System.out.println(ex);
	    }
	     return s;
	}
	/*
	 * Metodo para actualizar el Mapa por consola, va actualizar solo las celdas que el usuario pida, 
	 * mateniendo las celdas iniciales salvo que se pida un cambio de las mismas. Antes de efectuar 
	 * el cambio informa del estado actual de la celda y pregunta si deseas continuar.
	 */
	public boolean actualizaMapa(){
		System.out.println("Desea actualizar el mapa(S/N)?");
		String s = readString();
		if (s.equals("s")||s.equals("S")){
	    		while (s.equals("s")||s.equals("S")){
	    			System.out.println("Introduzca el valor de X");
	    			int x= Integer.parseInt(readString());
	    			System.out.println("Introduzca el valor de Y");
	    			int y= Integer.parseInt(readString());
	    			if ((x<0)||(x>=this.getX())||(y<0)||(y>=this.getY())){
	    				System.out.print("Introduzca de forma correcta los datos la proxima vez. ");
	    				System.out.print("Recuerde que el tamao actual del mapa es: ");
	    				System.out.println("(" + this.getX() + ", "+this.getY()+")");
	    			}
	    			else {
	    				System.out.println("El valor actual de la celda es:"+this.dameCelda(x,y));
	    				System.out.println("Deseas continuar con el cambio(S/N)?");
	    				s= readString();
	    				if (s.equals("s")||s.equals("S")){
	    					System.out.print("Introduzca el valor de la celda");
		    				System.out.println("Recuerde L(libre), A(avion), M(montana), Tu(turbulencia), To (Tormenta), V(viento)");
		    				String v = readString();
		    				if (v.equals("l") || v.equals("L")){
		    					ponCelda(x,y,"libre");
		    				}
		    				else{
		    					if (v.equals("a") || v.equals("A")){
		    						ponCelda(x,y,"avion");
		    					}
		    					else{
		    						if (v.equals("m") || v.equals("M")){
		    							ponCelda(x,y,"montana");
		    						}
		    						else{
		    							if (v.equals("Tu") || v.equals("tu") || v.equals("TU") || v.equals("tU")){
		    								ponCelda(x,y,"tubulencia");
		    							}
		    							else{
		    								if (v.equals("To") || v.equals("to") || v.equals("TO") || v.equals("tO")){
		    									ponCelda(x,y,"avion");
		    								}
		    								else{
		    									if (v.equals("v") || v.equals("V")){
		    										ponCelda(x,y,"viento");
		    									}
		    									else{
						    						System.out.println("Si quiere actualizar el mapa, introduce bien los datos (TORPE)");
		    									}
		    								}
		    							}
		    						}
			    				}
		    				}

	    				}
	    				System.out.println("Desea seguir actualizando(S/N)?");
	    				s = readString();
	    			}
	    		}
	    		return true;
	    }
	    else{
	    		return false;
	    }
	}
}
