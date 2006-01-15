package algoritmos;

import java.util.Vector;
import mapa.mapa;

public class aEstrella implements algoritmoInformado {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;
	String heuristica;
	
	/*
	 * Constructores, vacio y con parametros.
	 */
	
	public aEstrella() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Se da el estado inicial y el objetivo.
	 */
	
	public aEstrella(estado inicial, estado objetivo) {
		super();
		// TODO Auto-generated constructor stub
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.abiertos = new Vector();
		this.cerrados = new Vector();
		this.camino = new Vector();
	}
	
	/*
	 * Se da el estado inicial, el objetivo y
	 * la heuristica elegida.
	 */
	
	public aEstrella(estado inicial, estado objetivo, String heuristica) {
		super();
		// TODO Auto-generated constructor stub
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.abiertos = new Vector();
		this.cerrados = new Vector();
		this.camino = new Vector();
		this.heuristica = heuristica;
	}
	
	/*
	 * Acessores y mutadores para camino, abiertos, inicial,
	 * objetivo y cerrados.
	 */
	
	public Vector getAbiertos() {
		return abiertos;
	}

	public void setAbiertos(Vector abiertos) {
		this.abiertos = abiertos;
	}

	public Vector getCamino() {
		return camino;
	}

	public void setCamino(Vector camino) {
		this.camino = camino;
	}

	public Vector getCerrados() {
		return cerrados;
	}

	public void setCerrados(Vector cerrados) {
		this.cerrados = cerrados;
	}

	public estado getInicial() {
		return inicial;
	}

	public void setInicial(estado inicial) {
		this.inicial = inicial;
	}

	public estado getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(estado objetivo) {
		this.objetivo = objetivo;
	}

	public String getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(String heuristica) {
		this.heuristica = heuristica;
	}
	
	/*
	 * Metodo que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 */
	
	public boolean generarSucesor(estado e, mapa m, String s) {
		estado abajo = e.moverAbajo();
		estado derecha = e.moverDerecha();
		estado izquierda = e.moverIzquierda();
		estado arriba = e.moverArriba();
		
		if (!abajo.peligro(m)) {
			int aux;
			aux = abajo.calculaHeurisitica(this.heuristica, this.objetivo, m);
			abajo.setValor(aux);
			abajo.setCtotal(abajo.getCoste() + abajo.getValor());
			this.abiertos.add(abajo);
		}
		
		if (!derecha.peligro(m)) {
			int aux;
			aux = derecha.calculaHeurisitica(this.heuristica, this.objetivo, m);
			derecha.setValor(aux);
			derecha.setCtotal(derecha.getCoste() + derecha.getValor());
			this.abiertos.add(derecha);
		}
		
		if (!izquierda.peligro(m)) {
			int aux;
			aux = izquierda.calculaHeurisitica(this.heuristica, this.objetivo, m);
			izquierda.setValor(aux);
			izquierda.setCtotal(izquierda.getCoste() + izquierda.getValor());
			this.abiertos.add(izquierda);
		}
		
		if (!arriba.peligro(m)) {
			int aux;
			aux = arriba.calculaHeurisitica(this.heuristica, this.objetivo, 	m);
			arriba.setValor(aux);
			arriba.setCtotal(arriba.getCoste() + arriba.getValor());
			this.abiertos.add(arriba);
		}	 
		return true;
	}
	
	/* 
	 * Inicializar la lista de abiertos a la lista unaria 
	 * que contiene el estado inicial.
	 * 
	 * Mientras que existan estados en la lsita de abiertos nodos y
	 * no encuentre la solucion:
	 * - Actual es el elemento más pormetedor de abiertos.
	 * - Expandir actual 
	 * - Eliminarlo de abiertos y ponerlo en cerrados
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 * 
	 * Si no quedan elementos en la lista de abiertos es que no
	 * existe solucion y termino indicando la situacion.
	 */
	
	public void resolver(mapa m) {
		estado actual = this.inicial;
		int aux;
		int i;
		aux = this.inicial.calculaHeurisitica(this.heuristica,this.objetivo,m);
		this.inicial.setValor(aux);
		this.objetivo.setValor(0);
		this.abiertos.add(this.inicial);
		
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			i = this.masPrometedor(this.abiertos);
			actual = (estado)this.abiertos.elementAt(i);
			this.abiertos.removeElementAt(i);
			generarSucesor(actual, m,"");
			this.cerrados.add(actual);
		}
		
		if (actual.equals(this.objetivo)){
			this.camino = actual.generarCamino(this.inicial);
		}
		else{
			if (abiertos.isEmpty()){
				System.out.println("Infinito, no existe solucion");
				camino.clear(); 
			}
		}
		
	}
	
	/*
	 * Tal como indica el algoritmo A*, hay que recorrer el vector 
	 * de abiertos, buscando el mas prometedor, esto es, el minimo.
	 * Esta forma es lineal, aunque podramos hacer un divide y vencers
	 * y de esta forma obtener un coste logartmico...
	 */
	
	public int masPrometedor(Vector v){
		int peor = Integer.MAX_VALUE;
		int i = 0;
		int pos = 0;
		while (i < v.size()){
			estado aux = (estado) v.elementAt(i);
			if (aux.getCtotal() < peor){
					pos = i;
					peor = aux.getCtotal();		
			} 
			i++;
		}	
		return pos;
	}
	
	/*
	 *  Muestra un vector.
	 */
	public void mostrar(Vector v){
		if (v.isEmpty()){
			System.out.println("VacÃ­o");
		}
		else{
			System.out.print("["); 
			for (int i = 0; i< v.size(); i ++){
				((estado)v.elementAt(i)).mostrar();
			}
			System.out.println("]");
		}
	}

}
