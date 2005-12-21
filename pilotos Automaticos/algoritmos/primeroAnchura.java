package algoritmos;

import java.util.Vector;
import mapa.mapa;

public class primeroAnchura implements algoritmo {
	
	estado inicial;
	estado objetivo;
	Vector abiertos;
	Vector cerrados;
	Vector camino;
	
	public primeroAnchura() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public primeroAnchura(estado inicial, estado objetivo) {
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
	
	/*public boolean comprobarObjetivo(estado eo){
		return true;
			// no basta con == hay que hacer equalsTo()
	}*/

	public void mostrarCamino(){
		System.out.print("[");
		//while (!(this.getCamino().isEmpty())) 
		for (int i = 0; i< this.getCamino().size(); i ++){
			//((estado)this.getCamino().firstElement()).mostrar();
			//this.getCamino().removeElementAt(0);
			((estado)this.getCamino().elementAt(i)).mostrar();
		}
		System.out.println("]");
	}
	public void generarSucesor(estado e, mapa m){
		if (!e.moverAbajo().peligro(m)) {
			abiertos.add(e.moverAbajo());
			/*estado aux = e.moverAbajo();
			System.out.println(aux.getX());
			System.out.println(aux.getY());
			System.out.println("abajo");*/
		}
		if (!e.moverDerecha().peligro(m)) {
			abiertos.add(e.moverDerecha());
			/*estado aux = e.moverDerecha();
			System.out.println(aux.getX());
			System.out.println(aux.getY());
			System.out.println("derecha");*/
		}
		if (!e.moverIzquierda().peligro(m)) {
			abiertos.add(e.moverIzquierda());
			/*estado aux = e.moverIzquierda();
			System.out.println(aux.getX());
			System.out.println(aux.getY());
			System.out.println("izquierda");*/
		}
		if (!e.moverArriba().peligro(m)) {
			abiertos.add(e.moverArriba());
			/*estado aux = e.moverArriba();
			System.out.println(aux.getX());
			System.out.println(aux.getY());
			System.out.println("arriba");*/
		}
	}
	
	/* 1.Crear una variable NODE_LIST y ponerla al estado inicial.
	   2. Hasta que se encuentre el objetivo o hasta que NODE_LIST est vaca haga lo siguiente:
	         1. Eliminar el primer elemento de NODE_LIST, y llamarlo E. Si NODE_LIST estuvo vaca, salir.
	         2. Para cada forma en que cada regla puede ajustarse al estado descrito en E, haga lo siguiente:
	               1. Aplicar la regla para generar un nuevo estado.
	               2. Si el nuevo estado es un estado objetivo, salir y retornar este estado.
	               3. Sino, ada el nuevo estado al final de NODE_LIST.
	*/

	public void resolver( estado ei, estado ef, mapa m){
		estado actual = new estado();
//		abiertos = new Vector();
//		cerrados = new Vector();
//		camino = new Vector();
		abiertos.add(ei);
//		System.out.println("resolver");
		int i = 0;
		while ((!abiertos.isEmpty()) && (!actual.equals(ef))){
			actual = (estado)abiertos.firstElement();
			cerrados.add(actual);
			generarSucesor(actual, m);
			/*System.out.println(!actual.equals(ef));
			System.out.println(actual.getX());
			System.out.println(actual.getY());
			System.out.println("Otra vuelta");*/
			abiertos.removeElementAt(0);
			i++;
			/*if (abiertos.removeElement(abiertos.firstElement())){
				System.out.println("he borrao");
				System.out.println(abiertos.capacity());
			}
			else{
				System.out.println("me rio de ti");
			}*/
		}
		if (actual.equals(ef)){
			camino = actual.generarCamino(ei);
		}
	}	
}