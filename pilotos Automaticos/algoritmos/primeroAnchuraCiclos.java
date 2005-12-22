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
	 * Redefinición del método contains,
	 * si es el mismo estado (equals es true), esta contenido. 
	 */
	
	public boolean contains (estado e){
		boolean esta;
		esta = false;
		estado aux;
		for (int i = 0; i< this.getAbiertos().size(); i++){
			aux = (estado) this.getAbiertos().elementAt(i);
			if (aux.equals(e)){
				esta = esta || true;
			}
		}
		return esta;
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
		System.out.println(this.abiertos.contains(e));
		e.mostrar();
		System.out.println(this.abiertos.contains(e.moverAbajo()));
		e.moverAbajo().mostrar();
		//System.out.println(this.abiertos.contains(e.moverDerecha()));
		//System.out.println(this.abiertos.contains(e.moverIzquierda()));
		//System.out.println(this.abiertos.contains(e.moverArriba()));
		e.moverAbajo().setValor(0);
		e.moverDerecha().setValor(0);
		e.moverIzquierda().setValor(0);
		e.moverArriba().setValor(0);
		if (!(e.moverAbajo().peligro(m)) || (this.abiertos.contains(e.moverAbajo()))) {
			this.abiertos.add(e.moverAbajo());
			//e.moverAbajo().mostrar();
		}
		if (!(e.moverDerecha().peligro(m)) || (this.abiertos.contains(e.moverDerecha()))) {
			this.abiertos.add(e.moverDerecha());
			//e.moverDerecha().mostrar();
		}
		if (!(e.moverIzquierda().peligro(m)) || (this.abiertos.contains(e.moverIzquierda()))){
			this.abiertos.add(e.moverIzquierda());
			//e.moverIzquierda().mostrar();
		}
		if (!(e.moverArriba().peligro(m)) || (this.abiertos.contains(e.moverArriba()))) {
			this.abiertos.add(e.moverArriba());
			//e.moverArriba().mostrar();
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
	 * Si he encontrado el objetivo genero el camino.
	 */
	
	public void resolver(mapa m){
		System.out.println("resolver");
		estado actual = new estado();
		abiertos.add(this.inicial);
		while (!abiertos.isEmpty()){
		//while ((!abiertos.isEmpty()) && (!actual.equals(this.objetivo))){
		//System.out.println("bucle");
			actual = (estado)abiertos.firstElement();
			abiertos.removeElementAt(0);
			cerrados.add(actual);
			actual.mostrar();
			String s = m.dameCelda(actual.getX(),actual.getY());
			System.out.println(s);
			generarSucesor(actual, m);
		}
		if (actual.equals(this.objetivo)){
			System.out.println("solucion");
			camino = actual.generarCamino(this.inicial);
		}
		System.out.println ("abiertos vacio = " + abiertos.isEmpty());
		if (abiertos.isEmpty()){
			//System.out.println("infinito");
			camino.clear(); 
		}
	}
}
