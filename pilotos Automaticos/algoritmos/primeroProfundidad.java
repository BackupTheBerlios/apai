package algoritmos;

import java.util.Vector;
import mapa.mapa;


public class primeroProfundidad implements algoritmo {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;

	public primeroProfundidad() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public primeroProfundidad(estado inicial, estado objetivo) {
		super();
		// TODO Auto-generated constructor stub
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.abiertos = new Vector();
		this.cerrados = new Vector();
		this.camino = new Vector();
	}
	
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

	public void generarSucesor(estado e, mapa m){
		estado abajo = e.moverAbajo();
		estado derecha = e.moverDerecha();
		estado izquierda = e.moverIzquierda();
		estado arriba = e.moverArriba();
		if (!arriba.peligro(m)) {
			arriba.setValor(0);
			this.abiertos.add(arriba);
		}
		if (!izquierda.peligro(m)) {
			izquierda.setValor(0);
			this.abiertos.add(izquierda);
		}
		if (!derecha.peligro(m)) {
			derecha.setValor(0);
			this.abiertos.add(derecha);
		}
		if (!abajo.peligro(m)) {
			abajo.setValor(0);
			this.abiertos.add(abajo);
		}
	}
	
	public void mostrarCamino(){
		System.out.print("["); 
		for (int i = 0; i< this.getCamino().size(); i ++){
			((estado)this.getCamino().elementAt(i)).mostrar();
		}
		System.out.println("]");
	}
	/* 1. Si el estado inicial es el objetivo, salir y retornar éxito.
	   2. Sino, haga lo siguiente hasta que se obtenga señal de éxito o fracaso:
	         1. Genere un sucesor E del estado inicial. Si no hay más sucesores, retorne con señal de fracaso.
	         2. Llame recursivamente al algoritmo, esta vez con E como el estado inicial.
	         3. Si la señal es éxito, retorne, de otra manera, continúe en este lazo.
	*/
	
	public void resolver(mapa m){
		estado actual = new estado();
		this.abiertos.add(this.inicial);
		while ((!this.abiertos.isEmpty()) && (!(actual.equals(this.objetivo)))){
			actual = (estado)this.abiertos.lastElement();
			this.abiertos.removeElement(abiertos.lastElement());
			this.cerrados.add(actual);
			generarSucesor(actual, m);
		}
		actual.mostrar();
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
}

