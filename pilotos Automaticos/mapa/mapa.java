package mapa;

import java.util.Vector;

/*
 * Los valores para las celdas son:
 * 
 * libre
 * avion
 * montaña
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
	 * Se da el tamaño en x (filas) e y (columnas).
	 * 
	 * Generamos un vector de n elementos y en cada
	 * posición metemos un vector de y elementos.
	 * 
	 * Lo genermos vacío, todas las celdas libres.
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
}
