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
	 * Constructores, vacío y con parámetros.
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
	 * la heurística elegida.
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
	 * Método que muestra el camino recorrido 
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
	 * Método que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 * 
	 * Al insertarlos en abiertos, tenemos en cuenta 
	 * como extrae los hijos el algoritmo para 
	 * insertarlos en orden y tener que sacar
	 * siempre el primero.
	 * 
	 * Hay que añadir en orden en función de la heurística
	 */
	
	public void generarSucesor(estado e, mapa m) {
			if (!e.moverAbajo().peligro(m)) {
				int aux;
				aux = e.moverAbajo().calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + e.getValor();
				e.moverAbajo().setValor(aux);
				int i = e.moverAbajo().damePosicion(this.abiertos,aux);
				this.abiertos.add(i,e.moverAbajo());
			}
			if (!e.moverDerecha().peligro(m)) {
				int aux;
				aux = e.moverDerecha().calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + e.getValor();
				e.moverDerecha().setValor(aux);
				int i = e.moverDerecha().damePosicion(this.abiertos,aux);
				this.abiertos.add(i,e.moverDerecha());
			}
			if (!e.moverIzquierda().peligro(m)) {
				int aux;
				aux = e.moverIzquierda().calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + e.getValor();
				e.moverIzquierda().setValor(aux);
				int i = e.moverIzquierda().damePosicion(this.abiertos,aux);
				this.abiertos.add(i,e.moverIzquierda());
			}
			if (!e.moverArriba().peligro(m)) {
				int aux;
				aux = e.moverArriba().calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + e.getValor();
				e.moverArriba().setValor(aux);
				int i = e.moverArriba().damePosicion(this.abiertos,aux);
				this.abiertos.add(i,e.moverArriba());
			}	 
	}
	
	/* 
	 * Añadir abiertos el estado inicial.
	 * 
	 * Hasta que no me queden nodos o encuentre la solución:
	 * - Sacar de abiertos el primero
	 * - Añadirlo a cerrados
	 * - generar los sucesores: al generarlos los insertamos en orden
	 * para luego tener que extraer el primero que coincide con el de menor heuristica. 
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 */
	public void resolver(mapa m) {
		// TODO Auto-generated method stub
		estado actual = new estado();
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
