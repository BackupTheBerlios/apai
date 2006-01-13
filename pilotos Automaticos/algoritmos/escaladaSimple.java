package algoritmos;

import java.util.Vector;

import mapa.mapa;

public class escaladaSimple implements algoritmoInformado {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;
	String heuristica;
	
	/*
	 * Constructores, vacio y con parametros.
	 */
	
	public escaladaSimple() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * Se da el estado inicial y el objetivo.
	 */
	
	public escaladaSimple(estado inicial, estado objetivo) {
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
	
	public escaladaSimple(estado inicial, estado objetivo, String heuristica) {
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
	 * Acesores y mutadores para camino, abiertos, inicial,
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
	 */
	
	public void generarSucesor(estado e, mapa m, String s) {
		if (s.equals("abajo")){
			estado abajo = e.moverAbajo();
			if (!abajo.peligro(m)) {
				int aux;
				aux = abajo.calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + abajo.getCoste();
				abajo.setCtotal(aux + abajo.getValor());
				this.abiertos.add(abajo);
			}
		}
		if (s.equals("derecha")){
			estado derecha = e.moverDerecha();
			if (!derecha.peligro(m)) {
				int aux;
				aux = derecha.calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + derecha.getCoste();
				derecha.setCtotal(aux + derecha.getValor());
				this.abiertos.add(derecha);
			}
		}	
		if (s.equals("izquierda")){
			estado izquierda = e.moverIzquierda();
			if (!izquierda.peligro(m)) {
				int aux;
				aux = izquierda.calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + izquierda.getCoste();
				izquierda.setCtotal(aux + izquierda.getValor());
				this.abiertos.add(izquierda);
			}
		}
		if (s.equals("arriba")){
			estado arriba = e.moverArriba();
			if (!arriba.peligro(m)) {
				int aux;
				aux = arriba.calculaHeurisitica(this.heuristica, this.objetivo, m);
				aux = aux + arriba.getCoste();
				arriba.setCtotal(aux + arriba.getValor());
				this.abiertos.add(arriba);
			}	 
		}	
	}
	
	/* 
	 * 
	 */
	public void resolver(mapa m) {
		estado actual;
		estado nuevoEstado;
		Vector operadores;
		int aux;
		aux = this.inicial.calculaHeurisitica(this.heuristica,this.objetivo,m);
		System.out.println(aux);
		this.inicial.setValor(aux);
		this.objetivo.setValor(0);
		this.abiertos.add(this.inicial);
		actual = this.getInicial();
		System.out.println(actual.getCtotal());
		operadores = regenerarOperadores();
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual.mostrar();
			boolean cambio = false;
			while (operadores.isEmpty() || (!cambio)){
				String op;
				op = (String)operadores.firstElement();
				System.out.println(op);
				generarSucesor(actual, m, op);
				nuevoEstado = (estado)this.abiertos.lastElement();
				nuevoEstado.mostrar();
				System.out.println(nuevoEstado.getCtotal());
				System.out.println(actual.getCtotal());
				if (nuevoEstado.getCtotal() > actual.getCtotal()){
						actual = (estado)this.abiertos.lastElement();
						cambio = true;
						System.out.println("Cambiando actual por nuevo");
				}
				operadores.removeElement(operadores.elementAt(0));	
			}
			if (!cambio){
				System.out.println("Antes de cambiar");
				this.abiertos.removeElementAt(0);
				this.cerrados.add(actual);
				actual = (estado)this.abiertos.firstElement();
				System.out.println("Cambiando actual por primero");
				operadores = regenerarOperadores();
			}
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

	/*
	 *  Añadirmos todos los operadores a operadores sin aplicar.
	 */
	
	public Vector regenerarOperadores(){
		Vector operadores = new Vector();
		operadores.add("abajo");
		operadores.add("derecha");
		operadores.add("izquierda");
		operadores.add("arriba");
		return operadores;
	}
}
