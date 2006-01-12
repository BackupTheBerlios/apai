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
	 * Metodo que muestra el camino recorrido 
	 * para llegar a un vector
	 */

	public void mostrarCamino(){
		System.out.print("["); 
		for (int i = 0; i< this.getCamino().size(); i ++){
			((estado)this.getCamino().elementAt(i)).mostrar();
		}
		System.out.println("]");
	}
	/*
	 * Metodo que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 * 
	 * Al insertarlos en abiertos, tenemos en cuenta 
	 * como extrae los hijos el algoritmo para 
	 * insertarlos en orden y tener que sacar
	 * siempre el primero.
	 * 
	 * Hay que agnadir en orden en funcion de la heuristica
	 */
	
	public void generarSucesor(estado e, mapa m) {
		estado abajo = e.moverAbajo();
		estado derecha = e.moverDerecha();
		estado izquierda = e.moverIzquierda();
		estado arriba = e.moverArriba();
		System.out.println();
		if (!abajo.peligro(m)) {
			int aux;
			aux = abajo.calculaHeurisitica(this.heuristica, this.objetivo, m);
			aux = aux + abajo.getCoste();
			abajo.setCtotal(aux + abajo.getValor());
			int i = abajo.damePosicion(this.abiertos,abajo.getCtotal());
			this.abiertos.add(i,abajo);
		}
		if (!derecha.peligro(m)) {
			int aux;
			aux = derecha.calculaHeurisitica(this.heuristica, this.objetivo, m);
			aux = aux + derecha.getCoste();
			derecha.setCtotal(aux + derecha.getValor());
			int i = derecha.damePosicion(this.abiertos,derecha.getCtotal());
			this.abiertos.add(i,derecha);
		}
		if (!izquierda.peligro(m)) {
			int aux;
			aux = izquierda.calculaHeurisitica(this.heuristica, this.objetivo, m);
			aux = aux + izquierda.getCoste();
			izquierda.setCtotal(aux + izquierda.getValor());
			int i = izquierda.damePosicion(this.abiertos,izquierda.getCtotal());
			this.abiertos.add(i,izquierda);
		}
		if (!arriba.peligro(m)) {
			int aux;
			aux = arriba.calculaHeurisitica(this.heuristica, this.objetivo, m);
			aux = aux + arriba.getCoste();
			arriba.setCtotal(aux + arriba.getValor());
			int i = arriba.damePosicion(this.abiertos,arriba.getCtotal());
			this.abiertos.add(i,arriba);
		}	 
	}
	
	/* 
	 * Agnadir abiertos el estado inicial.
	 * 
	 * Hasta que no me queden nodos o encuentre la solucion:
	 * - Sacar de abiertos el primero
	 * - Agnadirlo a cerrados
	 * - generar los sucesores: al generarlos los insertamos en orden
	 * para luego tener que extraer el primero que coincide con el de menor heuristica. 
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 */
	public void resolver(mapa m) {
		estado actual = new estado();
		int aux;
		aux = this.inicial.calculaHeurisitica(this.heuristica,this.objetivo,m);
		this.inicial.setValor(aux);
		this.objetivo.setValor(0);
		this.abiertos.add(this.inicial);
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual = (estado)this.abiertos.firstElement();
			this.abiertos.removeElementAt(0);
			this.cerrados.add(actual);
			generarSucesor(actual, m);
			 
		}
		if (actual.equals(this.objetivo)){
			this.camino = actual.generarCamino(this.inicial);
		}
	}
}
