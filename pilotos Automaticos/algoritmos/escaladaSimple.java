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
	 * Metodo que genera los hijos, aplicando los
	 * operadores que no generen situaciones de
	 * peligro.
	 * 
	 * Se genera en funcion del operador que recibe
	 * como parametro.
	 */
	
	public boolean generarSucesor(estado e, mapa m, String s) {
		boolean b = false;
		if (s.equals("abajo")){
			estado abajo = e.moverAbajo();
			if (!abajo.peligro(m)) {
				int aux;
				aux = abajo.calculaHeurisitica(this.heuristica, this.objetivo, m);
				abajo.setValor(aux);
				aux = aux + abajo.getCoste();
				abajo.setCtotal(aux + abajo.getValor());
				this.abiertos.add(abajo);
				b = true;
			}
		}
		if (s.equals("derecha")){
			estado derecha = e.moverDerecha();
			if (!derecha.peligro(m)) {
				int aux;
				aux = derecha.calculaHeurisitica(this.heuristica, this.objetivo, m);
				derecha.setValor(aux);
				aux = aux + derecha.getCoste();
				derecha.setCtotal(aux + derecha.getValor());
				this.abiertos.add(derecha);
				b = true;
			}
		}	
		if (s.equals("izquierda")){
			estado izquierda = e.moverIzquierda();
			if (!izquierda.peligro(m)) {
				int aux;
				aux = izquierda.calculaHeurisitica(this.heuristica, this.objetivo, m);
				izquierda.setValor(aux);
				aux = aux + izquierda.getCoste();
				izquierda.setCtotal(aux + izquierda.getValor());
				this.abiertos.add(izquierda);
				b = true;
			}
		}
		if (s.equals("arriba")){
			estado arriba = e.moverArriba();
			if (!arriba.peligro(m)) {
				int aux;
				aux = arriba.calculaHeurisitica(this.heuristica, this.objetivo, m);
				arriba.setValor(aux);
				aux = aux + arriba.getCoste();
				arriba.setCtotal(aux + arriba.getValor());
				this.abiertos.add(arriba);
				b = true;
			}	 
		}	
		return b;
	}
	
	/* 
	 * Inicializar la lista de abiertos a la lista unaria 
	 * que contiene el estado inicial.
	 * 
	 * Mientras que existan estados en la lista de abiertos nodos y
	 * no encuentre la solucion:
	 * 
	 * - Actual es el primer elemento de abiertos.
	 * - mientras queden operadores po aplicar
	 * 		- aplicar operdor a actual (generamos nuevo)
	 * 		- si nuevo mejor que actual 
	 * 				- eliminar actual de abiertos
	 * 				- actual = nuevo
	 * 
	 * 		- si cambie actualizar, regenrar los operadores.
	 * 		-Si aplique todos eliminarlo.
	 * 
	 * Si he encontrado el objetivo genero el camino.
	 * 
	 * Si no quedan elementos en la lista de abiertos es que no
	 * existe solucion y termino indicando la situacion.
	 */
	
	public void resolver(mapa m) {
		estado actual;
		estado nuevoEstado;
		Vector operadores;
		boolean b = false;
		int aux;
		aux = this.inicial.calculaHeurisitica(this.heuristica,this.objetivo,m);
		this.inicial.setValor(aux);
		this.inicial.setCtotal(aux);
		this.objetivo.setValor(0);
		this.abiertos.add(this.inicial);
		actual = (estado)this.abiertos.firstElement();
		operadores = regenerarOperadores();
		while ((!this.abiertos.isEmpty()) && !(actual.equals(this.objetivo))){
			boolean cambio = false;
			
			while (!(operadores.isEmpty() || cambio)){
				String op;
				op = (String)operadores.firstElement();
				b = generarSucesor(actual, m, op);
			
				if (b){
					nuevoEstado = (estado)this.abiertos.lastElement();
				
					if (nuevoEstado.getCtotal() < actual.getCtotal()){
						this.abiertos.removeElementAt(0);
						this.cerrados.add(actual);
						actual = (estado)this.abiertos.lastElement();
						cambio = true;
					}
					
				}
				
				operadores.removeElementAt(0);
			}
			
			if (cambio){
				operadores = regenerarOperadores();
			}
			else{
		
				if (operadores.isEmpty()){
					this.abiertos.removeElementAt(0);
					this.cerrados.add(actual);
					actual = (estado)this.getAbiertos().firstElement();
					operadores = regenerarOperadores();
				}	
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
