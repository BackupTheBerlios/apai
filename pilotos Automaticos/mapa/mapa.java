package mapa;

import java.util.Vector;
import operaciones.*;

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
	 * Constructores, vacio y con parametros.
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
	 * Ejemplo de mapa 4*4
	 * (0,0)(1,0)(2,0)(3,0) 
	 * (0,1)(1,1)(2,1)(3,1)
	 * (0,2)(1,2)(2,2)(3,2)
	 * (0,3)(1,3)(2,3)(3,3)
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
	 * Accesores y mutadores para tablero, x e y.
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
	 * Metodos para acceder fácilmente a las celdas.
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
	 * Metodo que solicita la celda. 
	 * 
	 * Auxiliar para actualizar.
	 * 
	 * Te pide las coordenadas de la celda a modificar hasta que sea una del mapa
	 */
	
	public par coordenadas(input t){
		par v;
		System.out.println("Introduzca el valor de X");
		int x= Integer.parseInt(t.readString());
		System.out.println("Introduzca el valor de Y");
		int y= Integer.parseInt(t.readString());
		
		while ((x<0)||(x>=this.getX())||(y<0)||(y>=this.getY())){
			System.out.print("Introduzca de forma correcta los datos la proxima vez. ");
			System.out.print("Recuerde que el tamagno actual del mapa es: ");
			System.out.println("(" + this.getX() + ", "+this.getY()+")");
			System.out.println("Introduzca el valor de X");
			x= Integer.parseInt(t.readString());
			System.out.println("Introduzca el valor de Y");
			y= Integer.parseInt(t.readString());
		}
		
		v = new par(x,y);
		return v;
	}
	
	/*
	 * Metodo que solicita el contenido de la celda.
	 * 
	 * Auxiliar para actualizar.
	 *  
	 * Te pide el contenido hasta que sea un valor valido.
	 */
	
	public void contenido(input t, par p){
		
		System.out.print("Introduzca el valor de la celda");
		System.out.println("Recuerde L(libre), A(avion), Tu(turbulencia), To (Tormenta), V(viento)");
		String v = t.readString();
		boolean b = false;
		
		if (v.equals("l") || v.equals("L")){
			ponCelda(p.getX(),p.getY(),"libre");
			b = true;
		}
		
		if (v.equals("a") || v.equals("A")){
			ponCelda(p.getX(),p.getY(),"avion");
			b = true;
		}
		
		if (v.equals("Tu") || v.equals("tu") || v.equals("TU") || v.equals("tU")){
			ponCelda(p.getX(),p.getY(),"tubulencia");
			b = true;
		}
		
		if (v.equals("To") || v.equals("to") || v.equals("TO") || v.equals("tO")){
			ponCelda(p.getX(),p.getY(),"avion");
			b = true;
		}
		
		if (v.equals("v") || v.equals("V")){
			ponCelda(p.getX(),p.getY(),"viento");
			b = true;
		}
		
		if (!b){
			System.out.println("Si quiere actualizar el mapa, introduce un valor valido");
		}	
	}	
	
	/*
	 * Metodo para actualizar el Mapa por consola, va actualizar solo las celdas que el usuario pida, 
	 * mateniendo las celdas iniciales salvo que se pida un cambio de las mismas. Antes de efectuar 
	 * el cambio informa del estado actual de la celda y pregunta si deseas continuar.
	 */
	
	public boolean actualizaMapa(){
		input t= new input();
		par aux; 
		System.out.println("Desea actualizar el mapa(S/N)?");
		String s = t.readString();
	
		if ( s.equals("s") || s.equals("S") ){
		
			while (s.equals("s")||s.equals("S")){
				aux = coordenadas(t);
				System.out.println("El valor actual de la celda es:" + this.dameCelda(aux.getX(),aux.getY()));
			
				if (this.dameCelda(aux.getX(),aux.getY()).equals("montana")){
					System.out.println("Las montanas no se mueven");
				}
				else{
					System.out.println("Deseas continuar con el cambio(S/N)?");
					s= t.readString();
					if (s.equals("s")||s.equals("S")){
							contenido(t,aux);
					}
				}	
				System.out.println("Desea seguir actualizando(S/N)?");
				s = t.readString();
			}			
			
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
