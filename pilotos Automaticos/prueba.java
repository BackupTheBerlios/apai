import mapa.mapa;
import algoritmos.*;
//import java.util.Vector;

public class prueba {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		mapa m = new mapa(4,4);
		m.ponCelda(1,1,"montana");
		m.ponCelda(0,3,"avion");
		m.ponCelda(1,0,"avion");
		m.ponCelda(2,3,"avion");
		//m.ponCelda(3,3,"avion");
		estado ini = new estado(0,0);
		estado fin = new estado(3,0);
		//m.ponCelda(3,0,"montana");
		//m.ponCelda(3,1,"montana");
		//m.ponCelda(3,2,"montana");
		//algoritmo alg= new primeroAnchura(ini,fin);
		//algoritmo alg= new primeroAnchuraCiclos(ini,fin);
		algoritmo alg= new aEstrella(ini,fin,"manhattan");
		//algoritmo alg= new aEstrella(ini,fin,"celdas");
		//algoritmo alg = new PrimeroProfundidadCiclos(ini, fin);
		//algoritmo alg = new PrimeroProfundidad(ini, fin);
		//algoritmo alg = new escaladaSimple(ini,fin,"manhattan");
		alg.resolver(m);
		alg.mostrar(alg.getCamino());
		
		/*
		 * Prueba de mas prometedor
		 */
		/*Vector v= new Vector();
		estado e1 = new estado(0,1,ini,1);
		estado e2 = new estado(0,1,ini,2);
		estado e3 = new estado(0,1,ini,4);
		v.add(ini);
		v.add(e1);
		v.add(e2);
		v.add(e3);
		alg.masPrometedor(v);*/
	
		/*
		 * Prueba de equals
		 */
		
		/*boolean aux = ini.equals(fin);
		System.out.print(aux);*/
				
		/*
		 * Prueba de generar sucesor anchura
		 * 
		 */
		
		/*alg.generarSucesor(ini,m);
		alg.generarSucesor(fin,m);*/
		
		/*
		 * Prueba de contains
		 */
		
		/*Vector v = new Vector();
		v.add(ini);
		v.add(fin);
		estado e1 = ini.moverAbajo();
		estado e2 = e1;
		estado e3 = ini.moverDerecha();
		v.add(ini.moverAbajo());
		v.add(e3);
		boolean aux = v.contains(e1);
		System.out.println("Estan e1, e2, e3");
		System.out.println(aux);
		aux = v.contains(e2);
		System.out.println(aux);
		aux = v.contains(e3);
		System.out.println(aux);
		e1.mostrar();
		e1.getPadre().mostrar();
		ini.moverAbajo().mostrar();
		ini.moverAbajo().getPadre().mostrar();
		System.out.println(e1.equals(ini.moverAbajo()));*/
		
		
		/*
		 * Prueba de generar sucesor anchura + ciclos
		 */
		
		/*alg.getAbiertos().add(ini);
		estado e1 = ini.moverAbajo();
		ini.mostrar();
		e1.mostrar();
		alg.getAbiertos().add(e1);
		alg.generarSucesor(ini,m);*/
		
		/*
		 * Prueba de size
		 */
		
		/*for (int i = 0; i< 3; i++){
			Integer n = new Integer(i);
			v.add(n);
		}
		
		for (int i = 0; i<= v.size(); i++){
			System.out.println("a");
		}
		*/
		
		/*
		 * Prueba de peligro
		 * 
		 */
		
		/*estado e1 = new estado (0,0);
		e1.peligro(m);
		estado e2 = new estado (0,1);
		e2.peligro(m);
		estado e3 = new estado (0,2);
		e3.peligro(m);
		estado e4 = new estado (0,3);
		e4.peligro(m);
		estado e16 = new estado (1,0);
		e16.peligro(m);
		estado e5 = new estado (1,1);
		e5.peligro(m);
		estado e6 = new estado (1,2);
		e6.peligro(m);
		estado e7 = new estado (1,3);
		e7.peligro(m);
		estado e8 = new estado (2,0);
		e8.peligro(m);
		estado e9 = new estado (2,1);
		e9.peligro(m);
		estado e10 = new estado (2,2);
		e10.peligro(m);
		estado e11 = new estado (2,3);
		e11.peligro(m);
		estado e12 = new estado (3,0);
		e12.peligro(m);
		estado e13 = new estado (3,1);
		e13.peligro(m);
		estado e14 = new estado (3,2);
		e14.peligro(m);
		estado e15 = new estado (3,3);
		e15.peligro(m);
		estado e17 = new estado (-1,0);
		e17.peligro(m);
		estado e18 = new estado (0,-1);
		e18.peligro(m);
		*/	
	}
}
