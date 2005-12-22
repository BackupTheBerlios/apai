package algoritmos;
import java.util.Vector;
import mapa.mapa;

public class estado {
	
	int x;	//representamos el avion con sus coordenadas
	int y;
	estado padre; 
	int	valor; //representación del valor heurístico

	/*
	 * Constructores, vacío y con parámetros.
	 */
	
	public estado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Se da la posición del avión.
	 */
	
	public estado(int x, int y) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.valor = 0;
	}
	
	/*
	 * Se da la posición del avión y el nodo padre.
	 */
	
	public estado(int x, int y, estado padre) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.padre = padre;
		this.valor = 0;
	}
	
	/*
	 * Se da la posición del avión, el nodo padre y 
	 * el valor heurístico
	 */
	
	public estado(int x, int y, estado padre, int valor) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.padre = padre;
		this.valor = valor;
	}
	
	/*
	 * Acessores y mutadores para padre, val, x e y.
	 */
	
	public estado getPadre() {
		return padre;
	}

	public void setPadre(estado padre) {
		this.padre = padre;
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	/*
	 * Métodos de los operadores de movimiento.
	 */
	
	/*
	 * Al acceder al estado, le asignamos el padre.
	 */
	
	public estado moverArriba(){
		estado arriba = new estado (this.x, (this.y)-1, this);
		return arriba;
	}

	public estado moverAbajo(){
		estado arriba = new estado (this.x, (this.y)+1, this);
		return arriba;
	}
	
	public estado moverDerecha(){
		estado arriba = new estado ((this.x+1), this.y, this);
		return arriba;
	}
	
	public estado moverIzquierda(){
		estado arriba = new estado ((this.x)-1, this.y, this);
		return arriba;
	}
	
	/*
	 * Método para mostrar las coordenadas de un estado.
	 */
	
	public void mostrar(){
		System.out.print("(");
		System.out.print(this.x);
		System.out.print(",");
		System.out.print(this.y);
		System.out.print(")");
	}
	
	/*
	 * Métodos que indica si un estado es de peligro:
	 * Si ahy un avión, una montaña o es de fuera del mapa.
	 */
	
	public boolean peligro(mapa m){
		boolean aux = false;
		String val = m.dameCelda(this.x, this.y);
		if ((val=="avion")||(val=="montaña")||(val=="")){
			aux = aux || true;
		}
		return aux;
	}

	/*
	 * Método que indica donde hay que 
	 * insertar en función de la heurística.
	 * 
	 * Se coloca el último tras los que tengan menor
	 * o igual valor que él.
	 */
	
	public int damePosicion(Vector v, int heu){
		int i = 0;
		boolean encontrado = false;
		while (i < v.size() && !encontrado){
			estado aux = (estado) v.elementAt(i);
			if (heu >= aux.getValor()){
				i ++;
			}
			else {
				encontrado = true;
			}
		}
		return i;
	}
	
	/*
	 * Método que genera el camino, recorriendo los padres
	 * hasta llegar al estado inicial. 
	 */
	
	public Vector generarCamino(estado ei){
		estado e = this;
		Vector aux = new Vector();
		aux.add(0,e);
		while (!(e.equals(ei))){
			aux.add(0,e.padre);
			e = e.padre;
		}
		return aux;
	}

	/*
	 * Redefinición del método equals,
	 * si es la misma celda, son iguales. 
	 */
	
	public boolean equals(estado e){
		boolean aux;
		aux = true;
		if (e.getX()==this.getX()){
			aux = aux && true;
			}
		else{
			aux = aux && false;
		}
		if (e.getY()==this.getY()){
			aux = aux && true;
			}
		else{
			aux = aux && false;
		}
		if (e.getValor()==this.getValor()){
			aux = aux && true;
			}
		else{
			aux = aux && false;
		}
		return aux;	
	}

}
