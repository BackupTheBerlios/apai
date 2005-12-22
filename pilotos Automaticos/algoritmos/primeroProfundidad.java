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
	
	public boolean comprobarObjetivo(estado eo){
		return true;
			// no basta con == hay que hacer equals
	}

	public void generarSucesor(estado e, mapa m){
		if (!e.moverAbajo().peligro(m)) {
			abiertos.add(0, e.moverAbajo());
		}
		if (!e.moverDerecha().peligro(m)) {
			abiertos.add(0, e.moverDerecha());
		}
		if (!e.moverIzquierda().peligro(m)) {
			abiertos.add(0, e.moverIzquierda());
		}
		if (!e.moverArriba().peligro(m)) {
			abiertos.add(0, e.moverArriba());
		}
	}
	
	public void mostrarCamino(){
		
	}
	/* 1. Si el estado inicial es el objetivo, salir y retornar éxito.
	   2. Sino, haga lo siguiente hasta que se obtenga señal de éxito o fracaso:
	         1. Genere un sucesor E del estado inicial. Si no hay más sucesores, retorne con señal de fracaso.
	         2. Llame recursivamente al algoritmo, esta vez con E como el estado inicial.
	         3. Si la señal es éxito, retorne, de otra manera, continúe en este lazo.
	*/
	
	public void resolver(mapa m){
		estado actual = new estado();
		abiertos = new Vector();
		cerrados = new Vector();
		camino = new Vector();
		abiertos.add(inicial);
		actual = (estado)abiertos.firstElement();
		while (!abiertos.isEmpty() && (!actual.equals(objetivo))){
			abiertos.removeElement(abiertos.firstElement());
			cerrados.add(actual);
			generarSucesor(actual,m);
		}
		if (actual.equals(objetivo)){
			camino = actual.generarCamino(this.inicial);
		}
	}
}

