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
	 * ( miro en abiertos y en cerrados ) 
	 * si es el mismo estado (equals es true), esta contenido. 
	 */
	
	public boolean contains (estado e){
		boolean esta;
		esta = false;
		estado aux;
		for (int i = 0; i< this.getAbiertos().size(); i++){
			aux = (estado)this.getAbiertos().elementAt(i);
			if (aux.equals(e)){
				esta = esta || true;
			}
		}
		for (int i = 0; i< this.getCerrados().size(); i++){
			aux = (estado)this.getCerrados().elementAt(i);
			if (aux.equals(e)){
				esta = esta || true;
			}
		}
		return esta;
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
	
	public void generarSucesor(estado e, mapa m, String s){
		estado abajo = e.moverAbajo();
		estado derecha = e.moverDerecha();
		estado izquierda = e.moverIzquierda();
		estado arriba = e.moverArriba();
		if (!(abajo.peligro(m) || this.contains(abajo))) {
			this.abiertos.add(abajo);
		}
		if (!(derecha.peligro(m) || this.contains(derecha))) {
			this.abiertos.add(derecha);
		}
		if (!(izquierda.peligro(m) || this.contains(izquierda))) {
			this.abiertos.add(izquierda);
		}
		if (!(arriba.peligro(m) || this.contains(arriba))) {
			this.abiertos.add(arriba);
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
		else{
			if (abiertos.isEmpty()){
				System.out.println("infinito");
				camino.clear(); 
			}
		}
	}
	
	/*
	 *  Muestra un vector.
	 */
	public void mostrar(Vector v){
		if (v.isEmpty()){
			System.out.println("Vacío");
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
