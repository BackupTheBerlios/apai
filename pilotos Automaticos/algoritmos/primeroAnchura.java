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
	}

	/*
	 * Se da el estado inicial y el objetivo.
	 */
	
	public primeroAnchura(estado inicial, estado objetivo) {
		super();
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
	 * Metodo que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 * 
	 * Los insertamos al final de abiertos.
	 */
	
	public boolean generarSucesor(estado e, mapa m, String s){
		
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
		
		return true;
	}
	
	/* 
	 * Inicializar la lista de abiertos a la lista unaria 
	 * que contiene el estado inicial.
	 * 
	 * Mientras que existan estados en la lsita de abiertos nodos y
	 * no encuentre la solucion:
	 * - Actual es el primer elemento de abiertos.
	 * - Expandir actual 
	 * - Eliminarlo de abiertos y ponerlo en cerrados
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 */
	
	public void resolver(mapa m){
		
		estado actual = this.inicial;
		this.abiertos.add(this.inicial);
		
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual = (estado)this.abiertos.firstElement();
			generarSucesor(actual, m,"");
			this.abiertos.removeElementAt(0);
			this.cerrados.add(actual);		
		}
		
		if (actual.equals(this.objetivo)){
			this.camino = actual.generarCamino(this.inicial);
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