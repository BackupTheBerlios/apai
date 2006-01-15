package algoritmos;

import java.util.Vector;
import mapa.mapa;


public class PrimeroProfundidadCiclos implements algoritmo {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;

	/*
	 * Constructores, vacío y con parámetros.
	 */
	
	public PrimeroProfundidadCiclos() {
		super();
	}
	
	/*
	 * Se da el estado inicial y el objetivo.
	 */
	
	public PrimeroProfundidadCiclos(estado inicial, estado objetivo) {
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
	 * Redefinición del método contains.
	 *  
	 * Miro en abiertos y en cerrados si está antes de añadirlo, 
	 * para evitar generar dos veces el mismo nodo.
	 * 
	 *  Con esto garantizamos la condición de terminación en caso 
	 *  de que no exista solución.
	 */
	
	public boolean contains (estado e){
		boolean esta = false;
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
	 * Metodo que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 * 
	 * Los insertamos al final de abiertos.
	 * 
	 * Cambiamos el orden de aplicación de los operadores
	 * para garantizar que al sacar los hijo se sacan en
	 * el orden adecuado.
	 * 
	 * Hay control de ciclos. 
	 */
	
	public boolean generarSucesor(estado e, mapa m, String s){
	
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
		
		return true;
	}
	
	/* 
	 * Inicializar la lista de abiertos a la lista unaria 
	 * que contiene el estado inicial.
	 * 
	 * Mientras que existan estados en la lsita de abiertos nodos y
	 * no encuentre la solucion:
	 * - Actual es el último elemento de abiertos.
	 * - Expandir actual 
	 * - Eliminarlo de abiertos y ponerlo en cerrados
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 * 
	 * Si no quedan elementos en la lista de abiertos es que no
	 * existe solución y termino indicando la situación.
	 */
	
	public void resolver(mapa m){
		estado actual = this.inicial;
		this.abiertos.add(this.inicial);
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual = (estado)this.abiertos.lastElement();
			this.abiertos.removeElement(abiertos.lastElement());
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

