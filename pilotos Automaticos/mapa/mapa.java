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
	int x;	//tamagno del mapa -1
	int y; 
	Vector tablero;  
	
	public mapa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/* 
	 * 
	 * Genera un mapa que va de 0 a n, no a n-1
	 * 
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

	public Vector getTablero() {
		return tablero;
	}

	public void setTablero(Vector tablero) {
		this.tablero = tablero;
	}
	
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
	
	public void ponCelda (int x, int y, String val){
		((Vector)this.tablero.elementAt(x)).set(y,val);		
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
	
}
