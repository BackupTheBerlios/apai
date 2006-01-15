import mapa.mapa;
import algoritmos.*;
import operaciones.*;

/*
 * Clase Principal donde contendra el menu
 */
public class piloto {

	/*
	 * Eleccion de el algoritmo
	/*
	 * Da la posibilidad de elegir el algoritmo con el que calcularemos el camino.
	 */
	public static algoritmo eleccionA(estado i, estado f, input t){
		algoritmo alg= new primeroAnchura(i,f);;
		String s;
		System.out.print("Que algoritmo desea ejecutar?");
		System.out.println("Anchura(A), Anchura sin Ciclos(AC), aEstrella(AE), Profundidad(P), Profundidad sin Ciclos(PC) o ");
		System.out.println("Escalada Simple (ES)?");
		s= t.readString();
		boolean correcto=false;
		while (!correcto){
			if (s.equals("a")||s.equals("A")){
				//alg= new primeroAnchura(i,f);
				correcto=true;
			}
			if (s.equals("ac")||s.equals("AC")||s.equals("aC")||s.equals("Ac")){
				alg= new primeroAnchuraCiclos(i,f);
				correcto=true;
			}
			if (s.equals("ae")||s.equals("AE")||s.equals("aE")||s.equals("Ae")){
				System.out.println("Que heuristica desea ejecutar manhattan(M) o celdas(C) o manhattanCeldas(MC)?");
				s= t.readString();
				if (s.equals("m")||s.equals("M")){
					alg= new aEstrella(i,f,"manhattan");
					correcto=true;
				}
				if (s.equals("c")||s.equals("C")){
					alg= new aEstrella(i,f,"celdas");
					correcto=true;
				}
				if (s.equals("mc")||s.equals("MC")||s.equals("Mc")||s.equals("mC")){
					alg= new aEstrella(i,f,"manhattanCelda");
					correcto=true;
				}
				if (!correcto){
					System.out.println("Los datos son incorrectos");
					correcto=false;
				}
			}
			if (s.equals("es")||s.equals("ES")||s.equals("Es")||s.equals("eS")){
				System.out.println("Que heuristica desea ejecutar manhattan(M) o celdas(C)?");
				s= t.readString();
				if (s.equals("m")||s.equals("M")){
					alg= new escaladaSimple(i,f,"manhattan");
					correcto=true;
				}
				if (s.equals("c")||s.equals("C")){
					alg= new escaladaSimple(i,f,"celdas");
					correcto=true;
				}
				if (s.equals("mc")||s.equals("MC")||s.equals("Mc")||s.equals("mC")){
					alg= new aEstrella(i,f,"manhattanCelda");
					correcto=true;
				}
				if (!correcto){
					System.out.println("Los datos son incorrectos");
					correcto=false;
				}
			}
			if (s.equals("p")||s.equals("P")){
				alg = new primeroProfundidad(i, f);
				correcto=true;
			}
			if (s.equals("pc")||s.equals("PC")||s.equals("pC")||s.equals("Pc")){
				alg= new PrimeroProfundidadCiclos(i,f);
				correcto=true;
			}
			if(!correcto){
				System.out.println("Introduzca de forma correcta los datos la proxima vez.");
				correcto=false;
				System.out.print("Que algoritmo desea ejecutar?");
				System.out.println("Anchura(A), Anchura sin Ciclos(AC), aEstrella(AE), Profundidad(P), Profundidad sin Ciclos(PC)?");
				s= t.readString();
			}
		}
		return alg;
	}
	
	/*
	 * Metodo que cambia el estado inicial o final del avion.
	 */
	public static estado cambioCelda(input t, mapa m){
		estado e;
		boolean correcto=false;
		System.out.println("Introduzca el valor de X");
		int x= Integer.parseInt(t.readString());
		System.out.println("Introduzca el valor de Y");
		int y= Integer.parseInt(t.readString());
		while (!correcto){
			if ((x<0)||(x>=m.getX())||(y<0)||(y>=m.getY())){
				System.out.print("Introduzca de forma correcta los datos la proxima vez. ");
				System.out.print("Recuerde que el tamao actual del mapa es: ");
				System.out.println("(" + m.getX() + ", "+m.getY()+")");
				System.out.println("No se actualizara.");
				System.out.println("Introduzca el valor de X");
				x= Integer.parseInt(t.readString());
				System.out.println("Introduzca el valor de Y");
				y= Integer.parseInt(t.readString());
			}
			else{
				correcto = true;
			}
		}
		e= new estado(x,y);
		return e;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mapa m = new mapa(4,4);
		m.ponCelda(0,3,"montana");
		m.ponCelda(1,1,"montana");
		m.ponCelda(1,2,"avion");
		m.ponCelda(3,0,"montana");
		m.ponCelda(3,1,"montana");
		estado ini = new estado(0,0);
		estado fin = new estado(2,2);
		String s;
		input t = new input();
		
		algoritmo alg= new aEstrella(ini,fin,"manhattan");
		
		alg.mostrar(alg.getCamino());
			
		System.out.println("Desea realizar algun cambio o seguir ejecutando(S/N)?");
		s= t.readString();
		while (s.equals("s")||s.equals("S")){
			System.out.println("Quiere modificar la celda de inicio ("+ini.getX()+","+ini.getY()+") (S/N)?");
			s= t.readString();
			if (s.equals("s")||s.equals("S")){
				ini = cambioCelda(t,m);
			}
			System.out.println("Quiere modificar la celda objetivo ("+fin.getX()+","+fin.getY()+") (S/N)?");
			s= t.readString();
			if (s.equals("s")||s.equals("S")){
				fin= cambioCelda(t,m);
			}
			alg = eleccionA(ini,fin,t);
			alg.resolver(m);
			alg.mostrar(alg.getCamino());
			System.out.println("Desea realizar algun cambio o seguir ejecutando(S/N)?");
			s= t.readString();
		}
		
		System.out.println("Gracias por tratar de depurarme");
	}
}
