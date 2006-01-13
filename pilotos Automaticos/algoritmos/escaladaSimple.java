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
		estado actual = new estado();
		estado auxE;
		int aux;
		aux = this.inicial.calculaHeurisitica(this.heuristica,this.objetivo,m);
		this.inicial.setValor(aux);
		this.objetivo.setValor(0);
		this.abiertos.add(this.inicial);
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual = (estado)this.abiertos.firstElement();
			this.abiertos.removeElementAt(0);
			this.cerrados.add(actual);
			//No se mu bien cual es el que primero debo generar
			generarSucesor(actual, m, "abajo");
			//Una vez generado y evaluarlo
			auxE=(estado)this.abiertos.firstElement();
			if (!auxE.equals(this.objetivo)){
				//Si auxE es mejor que actual entonces actual=auxE repito bucle
				if(auxE.getCtotal()<actual.getCtotal()){
					actual=auxE;
					this.abiertos.removeElementAt(0);
					this.cerrados.add(actual);
					//No se mu bien cual es el segundoque debo generar, ni si seria aqui
					generarSucesor(actual, m, "izquierda");
					
				}
				//sino debere aplicar otro operador diferente al 1º para generar
				//otro sucesor, no?
			}
			 
		}
		if (actual.equals(this.objetivo)){
			this.camino = actual.generarCamino(this.inicial);
		}
	}
}
