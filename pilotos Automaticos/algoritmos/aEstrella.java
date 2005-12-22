package algoritmos;

import java.util.Vector;
import mapa.mapa;

public class aEstrella implements algoritmo {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;
	
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

	public boolean comprobarObjetivo(estado eo) {
		return true;
		// no basta con == hay que hacer equals
	}

	public void resolver(mapa m) {
		// TODO Auto-generated method stub
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
	
	public void mostrarCamino(){
		
	}

	/*
	 * Hay que añadir en orden en función de la heurística
	 */
	public void generarSucesor(estado e, mapa m) {
		if (!e.moverAbajo().peligro(m)) {
			estado aux = e.moverAbajo();
			int i = aux.damePosicion(abiertos,aux.getValor());
			abiertos.add(i,aux);
		}
		if (!e.moverDerecha().peligro(m)) {
			estado aux = e.moverDerecha();
			int i = aux.damePosicion(abiertos,aux.getValor());
			abiertos.add(i,aux);
		}
		if (!e.moverIzquierda().peligro(m)) {
			estado aux = e.moverIzquierda();
			int i = aux.damePosicion(abiertos,aux.getValor());
			abiertos.add(i,aux);
		}
		if (!e.moverArriba().peligro(m)) {
			estado aux = e.moverArriba();
			int i = aux.damePosicion(abiertos,aux.getValor());
			abiertos.add(i,aux);
		}
	}

}
