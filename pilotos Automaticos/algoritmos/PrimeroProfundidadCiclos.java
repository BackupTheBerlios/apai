package algoritmos;

import java.util.Vector;
import mapa.mapa;


public class PrimeroProfundidadCiclos implements algoritmo {

	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;

	public PrimeroProfundidadCiclos() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PrimeroProfundidadCiclos(estado inicial, estado objetivo) {
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
	
	/* 1. Si el estado inicial es el objetivo, salir y retornar éxito.
	   2. Sino, haga lo siguiente hasta que se obtenga señal de éxito o fracaso:
	         1. Genere un sucesor E del estado inicial. Si no hay más sucesores, retorne con señal de fracaso.
	         2. Llame recursivamente al algoritmo, esta vez con E como el estado inicial.
	         3. Si la señal es éxito, retorne, de otra manera, continúe en este lazo.
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

