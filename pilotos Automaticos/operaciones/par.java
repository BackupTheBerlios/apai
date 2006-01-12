package operaciones;

public class par {
	int x;
	int y;

	/*
	 * Constructores, vacio y con parametros.
	 */
	
	/*
	 * Contructor sin parametros.
	 */
	public par() {
		super();
		this.x = 0;
		this.y = 0;
	}
	
	/*
	 * Constructor con el par de puntos.
	 */
	public par(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Accesores y mutadores para los dos elementos del par
	 */
	
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
