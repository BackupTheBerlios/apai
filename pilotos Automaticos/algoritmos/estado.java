package algoritmos;
import java.util.Vector;
import mapa.mapa;


public class estado {
	
	int x;	//representamos el avion con sus coordenadas
	int y;
	estado padre; 
	int	valor; //representacion del valor heuristico
	int coste; //coste de llegar al nodo desde el inicial

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
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.valor = 0;
		this.coste = 0;
	}
	
	/*
	 * Se da la posicion del avion y el nodo padre.
	 */
	
	public estado(int x, int y, estado padre) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.padre = padre;
		this.valor = 0;
		this.coste = padre.getCoste() + 1 ;
	}
	
	/*
	 * Se da la posicion del avion, el nodo padre y 
	 * el valor heuristico
	 */
	
	public estado(int x, int y, estado padre, int valor) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.padre = padre;
		this.valor = valor;
		this.coste = padre.getCoste() + 1;
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
	
	/*
	 * Metodos de los operadores de movimiento.
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
	 * Metodo para mostrar las coordenadas de un estado.
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
				//System.out.print("Valor del nuevo (valor)");
				//System.out.println(aux.getValor());
				//System.out.print("Valor del de la lista (heu)");
				//System.out.println(heu);
				if (heu >= aux.getValor()){
					//System.out.println("Entro en el if porque heu>=valor");
					i ++;		
				}
				else {
					encontrado = true;
				}
			}
		}	
		System.out.print("La posicion es ");
		System.out.println (i);
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
	 * Metodo para calcular las distintas heurisiticas
	 */
	
	public int calculaHeurisitica (String heuristica, estado objetivo, mapa m){
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
			this.mostrar();
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
