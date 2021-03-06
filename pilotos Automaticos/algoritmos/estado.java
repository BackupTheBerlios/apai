package algoritmos;
import java.util.Vector;
import mapa.mapa;


public class estado {
	
	int x;	//representamos el avion con sus coordenadas
	int y;
	estado padre; //estado padre
	int	valor; //representacion del valor heuristico
	int coste; //coste de llegar al nodo desde el inicial
	int ctotal; //estimacion del coste total -->  ctotal = heuristica + coste

	/*
	 * Constructores, vacio y con parametros.
	 */
	
	public estado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Se da la posicion del avion.
	 */
	
	public estado(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.valor = 0;
		this.coste = 0;
		this.ctotal = 0;
	}
	
	/*
	 * Se da la posicion del avion y el nodo padre.
	 */
	
	public estado(int x, int y, estado padre) {
		super();
		this.x = x;
		this.y = y;
		this.padre = padre;
		this.valor = 0;
		this.coste = padre.getCoste() + 1 ;
		this.ctotal = valor + coste;
	}
	
	/*
	 * Se da la posicion del avion, el nodo padre y 
	 * el valor heuristico. El coste de llegar hasta el
	 * es el del padre + 1 porque
	 * todos los operadores tienen coste 1.
	 */
	
	public estado(int x, int y, estado padre, int valor) {
		super();
		this.x = x;
		this.y = y;
		this.padre = padre;
		this.valor = valor;
		this.coste = padre.getCoste() + 1;
		this.ctotal = this.valor + coste;
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
	
	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public int getCtotal() {
		return ctotal;
	}

	public void setCtotal(int ctotal) {
		this.ctotal = ctotal;
	}
	
	/*
	 * Metodos de los operadores de movimiento.
	 *
	 * Al generar el estado al estado, le asignamos el padre.
	 */

	public estado moverArriba(){
		estado arriba = new estado (this.x, (this.y)-1, this);
		return arriba;
	}

	public estado moverAbajo(){
		estado abajo = new estado (this.x, (this.y)+1, this);
		return abajo;
	}
	
	public estado moverDerecha(){
		estado derecha = new estado ((this.x)+1, this.y, this);
		return derecha;
	}
	
	public estado moverIzquierda(){
		estado izquierda = new estado ((this.x)-1, this.y, this);
		return izquierda;
	}
	
	/*
	 * Metodo para mostrar las coordenadas de un estado en formato (x,y)
	 */
	
	public void mostrar(){
		System.out.print("(");
		System.out.print(this.x);
		System.out.print(",");
		System.out.print(this.y);
		System.out.print(")");
	}
	
	/*
	 * Metodos que indica si un estado es de peligro:
	 * Si hay un avion, una montaña o es de fuera del mapa.
	 */
	
	public boolean peligro(mapa m){
		m.actualizaMapa();
		boolean aux = false;
		String val = m.dameCelda(this.x, this.y);
		if ((val=="avion")||(val=="montana")||(val=="")){
			aux = aux || true;
		}
		return aux;
	}

	/*
	 * Metodo que indica donde hay que 
	 * insertar en funcion de la heuristica.
	 * 
	 * Se coloca el ultimo tras los que tengan menor
	 * o igual valor que el.
	 */
	
	public int damePosicion(Vector v, int heu){
		int i = 0;
		boolean encontrado = false;
		if (v.isEmpty()){
			i = 0;
		}
		else {
			while (i < v.size() && !encontrado){
				estado aux = (estado) v.elementAt(i);
				if (heu >= aux.getCtotal()){
					i ++;		
				}
				else {
					encontrado = true;
				}
			}
		}	
		return i;
	}
	
	/*
	 * Metodo que genera el camino, recorriendo los padres
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
	 * Redefinicion del metodo equals,
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
	
	/*
	 * Metodo para calcular la heurística en funcion del
	 * parametro heuristica del algoritmo. 
	 */
	
	public int calculaHeurisitica (String heuristica, estado objetivo, mapa m){
		m.actualizaMapa();
		int aux = 0;
		if (heuristica == "manhattan"){ 
			aux = this.calculaManhattan(objetivo);
		}
		if (heuristica == "celdas"){
			aux = this.calculaCelda(objetivo, m);
		}
		if (heuristica == "manhattanCelda"){
			aux = this.calculaManhattan(objetivo);
			aux = aux  + this.calculaCelda(objetivo, m);
		}
		return aux;
	}
	
	/*
	 * Metodo para calcular la distancia manhattan al objetivo
	 */
	
	public int calculaManhattan (estado objetivo){
		int aux;
		int distanciaX;
		int distanciaY;
		distanciaX = objetivo.getX() - this.getX();
		distanciaY = objetivo.getY() - this.getY();
		if (distanciaX < 0){
			distanciaX = -1 * distanciaX;
		}
		if (distanciaY < 0){
			distanciaY = -1 * distanciaY;
		}
		aux = distanciaX + distanciaY;
		return aux;
	}
	
	/*
	 * Metodo que asigna un valor en funcion del 
	 * contenido de la celda.
	 * 
	 *  Libre mejor que turbulencia
	 *  Turbulencia mejor que tormenta o viento
	 */
	public int calculaCelda(estado objetivo, mapa m){
		int aux = 1;
		String celda;
		celda = m.dameCelda(this.x,this.y);
		if (this.equals(objetivo)){
			aux = 0; 
			return aux;
		}
		if (celda == "libre"){
			aux = 1; 
		}
		if (celda == "turbulencia"){
			aux = 10; 
		}
		if (celda == "viento"){
			aux = 30; 
		}
		if (celda == "tormenta"){
			aux = 30; 
		}
		return aux;
	}

}
