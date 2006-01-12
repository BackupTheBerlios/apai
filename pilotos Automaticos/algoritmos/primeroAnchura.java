package algoritmos;

import java.util.Vector;
import mapa.mapa;

public class primeroAnchura implements algoritmo {
	
	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;
	
	/*
	 * Constructores, vacio y con parametros.
	 */
	
	public primeroAnchura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Se da el estado inicial y el objetivo.
	 */
	
	public primeroAnchura(estado inicial, estado objetivo) {
		super();
		// TODO Auto-generated constructor stub
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.abiertos = new Vector();
		this.cerrados = new Vector();
		this.camino = new Vector();
	}

	/*
	 * Accesores y mutadores para camino, abiertos, inicial,
	 * objetivo y cerrados.
	 */
	
	public Vector getCamino() {
		return camino;
	}

	public void setCamino(Vector camino) {
		this.camino = camino;
	}

	public Vector getAbiertos() {
		return abiertos;
	}

	public void setAbiertos(Vector abiertos) {
		this.abiertos = abiertos;
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
	public Vector getCerrados() {
		return cerrados;
	}

	public void setCerrados(Vector cerrados) {
		this.cerrados = cerrados;
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
	 */
	
	public void generarSucesor(estado e, mapa m, String s){
		estado abajo = e.moverAbajo();
		estado derecha = e.moverDerecha();
		estado izquierda = e.moverIzquierda();
		estado arriba = e.moverArriba();
		if (!abajo.peligro(m)) {
			abajo.setValor(0);
			this.abiertos.add(abajo);
		}
		if (!derecha.peligro(m)) {
			derecha.setValor(0);
			this.abiertos.add(derecha);
		}
		if (!izquierda.peligro(m)) {
			izquierda.setValor(0);
			this.abiertos.add(izquierda);
		}
		if (!arriba.peligro(m)) {
			arriba.setValor(0);
			this.abiertos.add(arriba);
		}
	}
	
	/* 
	 * Agnadir abiertos el estado inicial.
	 * 
	 * Hasta que no me queden nodos o encuentre la solucion:
	 * - Sacar de abiertos el primero
	 * - Agnadirlo a cerrados
	 * - generar los sucesores
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 */
	
	public void resolver(mapa m){
		estado actual = new estado();
		this.abiertos.add(this.inicial);
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual = (estado)this.abiertos.firstElement();
			this.abiertos.removeElementAt(0);
			this.cerrados.add(actual);
			generarSucesor(actual, m,"");
		}
		if (actual.equals(this.objetivo)){
			this.camino = actual.generarCamino(this.inicial);
		}
	}	
}