import mapa.mapa;
import algoritmos.*;
import java.util.Vector;

public class prueba {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		mapa m = new mapa(4,4);
		m.ponCelda(2,0,"montaña");
		m.ponCelda(1,1,"montaña");
		m.ponCelda(1,2,"avion");
		m.ponCelda(1,3,"montaña");
		estado ini = new estado(0,0);
		estado fin = new estado(3,0);
		 /*boolean aux = ini.equals(fin);
		System.out.print(aux);*/
		
		algoritmo alg= new primeroAnchura(ini,fin);
		
		/*
		 * Prueba de generar sucesor
		 * 
		 */
		/*alg.generarSucesor(ini,m);
		alg.generarSucesor(fin,m);*/
		alg.resolver(ini,fin,m);
		alg.mostrarCamino();
		
		/*Vector v = new Vector(3);
		
		for (int i = 0; i< 3; i++){
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
