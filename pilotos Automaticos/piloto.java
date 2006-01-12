import mapa.mapa;
import algoritmos.*;
import operaciones.*;

/*
 * Clase Principal donde contendra el menu
 */
public class piloto {

	/*
	 * Eleccion de el algoritmo
	 */
	public static algoritmo eleccionA(estado i, estado f, input t){
		algoritmo alg;
		String s;
		System.out.print("Que algoritmo desea ejecutar?");
		System.out.println("Anchura(A), Anchura sin Ciclos(AC), aEstrella(AE), Profundidad(P), Profundidad sin Ciclos(PC)?");
		s= t.readString();
		boolean correcto=false;
		while (!correcto){
			System.out.print("Que algoritmo desea ejecutar?");
			System.out.println("Anchura(A), Anchura sin Ciclos(AC), aEstrella(AE), Profundidad(P), Profundidad sin Ciclos(PC)?");
			s= t.readString();
			if (s.equals("a")||s.equals("A")){
				alg= new primeroAnchura(i,f);
				correcto=true;
			}
			else{
				if (s.equals("ac")||s.equals("AC")||s.equals("aC")||s.equals("Ac")){
					alg= new primeroAnchuraCiclos(i,f);
					correcto=true;
				}
				else{
					if (s.equals("ae")||s.equals("AE")||s.equals("aE")||s.equals("Ae")){
						System.out.println("Que heuristica desea ejecutar manhattan(M) o celdas(C)?");
						s= t.readString();
						if (s.equals("m")||s.equals("M")){
							alg= new aEstrella(i,f,"manhattan");
							correcto=true;
						}
						else{
							if (s.equals("c")||s.equals("C")){
								alg= new aEstrella(i,f,"celdas");
								correcto=true;
							}
							else{
								System.out.println("Los datos son incorrectos");
								correcto=false;
							}
						}
						correcto=true;
					}
					else{
						if (s.equals("p")||s.equals("P")){
							alg = new primeroProfundidad(i, f);
							correcto=true;
						}
						else{
							if (s.equals("pc")||s.equals("PC")||s.equals("pC")||s.equals("Pc")){
								alg= new PrimeroProfundidadCiclos(i,f);
								correcto=true;
							}
							else{
								System.out.println("Introduzca de forma correcta los datos la proxima vez.");
								correcto=false;
							}
						}
					}
				}
			}
		}
		alg= new PrimeroProfundidadCiclos(i,f);
		return alg;
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
		algoritmo alg= new aEstrella(ini,fin,"manhattan");
		input t = new input();
		
		
		//algoritmo alg= new primeroAnchura(ini,fin);
		//algoritmo alg= new primeroAnchuraCiclos(ini,fin);
		//alg= new aEstrella(ini,fin,"manhattan");
		//algoritmo alg= new aEstrella(ini,fin,"celdas");
		//algoritmo alg = new primeroProfundidad(ini, fin);
		alg.resolver(m);
		alg.mostrarCamino();
			
		System.out.println("Desea realizar algun cambio o seguir ejecutando(S/N)?");
		s= t.readString();
		if (s.equals("s")||s.equals("S")){
			System.out.println("Quiere modificar la celda de inicio ("+ini.getX()+","+ini.getY()+") (S/N)?");
			s= t.readString();
			if (s.equals("s")||s.equals("S")){
				System.out.println("Introduzca el valor de X");
				int x= Integer.parseInt(t.readString());
				System.out.println("Introduzca el valor de Y");
				int y= Integer.parseInt(t.readString());
				if ((x<0)||(x>=m.getX())||(y<0)||(y>=m.getY())){
					System.out.print("Introduzca de forma correcta los datos la proxima vez. ");
					System.out.print("Recuerde que el tamao actual del mapa es: ");
					System.out.println("(" + m.getX() + ", "+m.getY()+")");
					System.out.println("No se actualizara.");
				}
				else{
					ini= new estado(x,y); 
				}
			}
			System.out.println("Quiere modificar la celda objetivo ("+fin.getX()+","+fin.getY()+") (S/N)?");
			s= t.readString();
			if (s.equals("s")||s.equals("S")){
				System.out.println("Introduzca el valor de X");
				int x= Integer.parseInt(t.readString());
				System.out.println("Introduzca el valor de Y");
				int y= Integer.parseInt(t.readString());
				if ((x<0)||(x>=m.getX())||(y<0)||(y>=m.getY())){
					System.out.print("Introduzca de forma correcta los datos la proxima vez");
					System.out.print("Recuerde que el tamao actual del mapa es: ");
					System.out.println("(" + m.getX() + ", "+m.getY()+")");
					System.out.println("No se actualizara.");
				}
				else{
					fin= new estado(x,y); 
				}
			}
			
			while (m.actualizaMapa()){
				alg= new aEstrella(ini,fin,"manhattan");
				alg.resolver(m);
				alg.mostrarCamino();
			}
		}
		
		System.out.println("Gracias por tratar de depurarme");
	}
}
