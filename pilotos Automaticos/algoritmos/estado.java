package algoritmos;
import java.util.Vector;
import mapa.mapa;

public class estado {
	
	int x;	//representamos el avion con sus coordenadas
	int y;
	estado padre;
	int	valor;

	public estado() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public estado(int x, int y) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	
	public estado(int x, int y, estado padre) {
		super();
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.padre = padre;
	}
	
	public estado getPadre() {
		return padre;
	}

	public void setPadre(estado padre) {
		this.padre = padre;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public estado moverArriba(){
		estado arriba = new estado (x, y-1, this);
		return arriba;
	}

	public estado moverAbajo(){
		estado arriba = new estado (x, y+1, this);
		return arriba;
	}
	
	public estado moverDerecha(){
		estado arriba = new estado (x+1, y, this);
		return arriba;
	}
	
	public estado moverIzquierda(){
		estado arriba = new estado (x-1, y, this);
		return arriba;
	}
	
	public void mostrar(){
		System.out.print("(");
		System.out.print(this.x);
		System.out.print(",");
		System.out.print(this.y);
		System.out.print(")");
	}
	
	public boolean peligro(mapa m){
		boolean aux = false;
		String val = m.dameCelda(this.x, this.y);
		//System.out.println(x);
		//System.out.println(y);
		//System.out.println(val);
		if ((val=="avion")||(val=="montaña")||(val=="")){
			aux = aux || true;
		}
		//System.out.println(aux);
		return aux;
	}

	/*
	 * Este método indica donde hay que 
	 * insertar en función de la heurística
	 */
	public int damePosicion(Vector v, int heu){
		int i = 0;
		boolean encontrado = false;
		while (i < v.size() && !encontrado){
			estado aux = (estado) v.elementAt(i);
			if (heu >= aux.getValor()){
				i ++;
			}
			else {
				encontrado = true;
			}
		}
		return i;
	}
	
	public Vector generarCamino(estado ei){
		estado e = this;
		Vector aux = new Vector();
		aux.add(0,e);
		while (!(e.equals(ei))){
			aux.add(0,e.padre);
			e = e.padre;
		}
		return aux;
	}
	
	public boolean equals(estado e){
		boolean aux;
		aux = true;
		if (e.getX()==this.getX()){
			aux = aux && true;
			}
		else{
			aux = aux && false;
		}
		if (e.getY()==this.getY()){
			aux = aux && true;
			}
		else{
			aux = aux && false;
		}
		return aux;
			
		
		}
	
}
