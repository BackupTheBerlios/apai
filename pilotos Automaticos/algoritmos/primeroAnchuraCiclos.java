package algoritmos;

import java.util.Vector;

import mapa.mapa;

public class primeroAnchuraCiclos implements algoritmo {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;
	
	/*
	 * Constructores, vacío y con parámetros.
	 */
	
	public primeroAnchuraCiclos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Se da el estado inicial y el objetivo.
	 */
	
	public primeroAnchuraCiclos(estado inicial, estado objetivo) {
		super();
		// TODO Auto-generated constructor stub
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.abiertos = new Vector();
		this.cerrados = new Vector();
		this.camino = new Vector();
	}

	/*
	 * Acessores y mutadores para camino, abiertos, inicial,
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
	 * Método que indica donde hay que 
	 * insertar en función de la heurística.
	 * 
	 * Se coloca el último tras los que tengan menor
	 * o igual valor que él.
	 */

	public void mostrarCamino(){
		if (this.getCamino().isEmpty()){
			System.out.println("No existe aolución");
		}
		else{
			System.out.print("["); 
			for (int i = 0; i< this.getCamino().size(); i ++){
				((estado)this.getCamino().elementAt(i)).mostrar();
			}
			System.out.println("]");
		}	
	}
	
	/*
	 * Método que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 * 
	 * Hay control de ciclos.
	 * 
	 * Al insertarlos en abiertos, tenemos en cuenta 
	 * como extrae los hijos el algoritmo para 
	 * insertarlos en orden y tener que sacar
	 * siempre el primero.
	 */
	
	public void generarSucesor(estado e, mapa m){
		System.out.println("generar sucesores");
		if ((!e.moverAbajo().peligro(m)) && (!abiertos.contains(e.moverAbajo()))) { 
			abiertos.add(e.moverAbajo());
		}
		if ((!e.moverDerecha().peligro(m)) && (!abiertos.contains(e.moverAbajo()))) {
			abiertos.add(e.moverDerecha());
		}
		if ((!e.moverIzquierda().peligro(m)) && (!abiertos.contains(e.moverAbajo()))){
			abiertos.add(e.moverIzquierda());
		}
		if ((!e.moverArriba().peligro(m)) && (!abiertos.contains(e.moverAbajo()))) {
			abiertos.add(e.moverArriba());
		}
	}
	
	/* 
	 * Añadir abiertos el estado inicial.
	 * 
	 * Hasta que no me queden nodos o encuentre la solución:
	 * - Sacar de abiertos el primero
	 * - Añadirlo a cerrados
	 * - generar los sucesores
	 * 
	 * Si he encontrado el objetivo genreo el camino.
	 */
	
	public void resolver(mapa m){
		System.out.println("resolver");
		estado actual = new estado();
		abiertos.add(this.inicial);
		while ((!abiertos.isEmpty()) && (!actual.equals(this.objetivo))){
			System.out.println("bucle");
			actual = (estado)abiertos.firstElement();
			abiertos.removeElementAt(0);
			cerrados.add(actual);
			generarSucesor(actual, m);
		}
		if (actual.equals(this.objetivo)){
			System.out.println("solucion");
			camino = actual.generarCamino(this.inicial);
		}
		if (abiertos.isEmpty()){
			System.out.println("infinito");
			camino.clear(); 
		}
	}
}
