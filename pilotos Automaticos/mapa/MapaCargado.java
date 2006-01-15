package mapa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import algoritmos.estado;

public class MapaCargado {

	mapa m;
	estado ini, fin;
	
	public estado getFin() {
		return fin;
	}
	public void setFin(estado fin) {
		this.fin = fin;
	}
	public estado getIni() {
		return ini;
	}
	public void setIni(estado ini) {
		this.ini = ini;
	}
	public mapa getMapa() {
		return m;
	}
	public void setM(mapa m) {
		this.m = m;
	}
	
	public MapaCargado(File archivo){
		int x,y,xIni,yIni,xDest,yDest;
		x = y = xIni = yIni = xDest = yDest = -1;

		int lin = 0;
		
	    //declared here only to make visible to finally clause
	    BufferedReader entrada = null;
	    try {
	      //use buffering
	      //this implementation reads one line at a time
	      //FileReader always assumes default encoding is OK!
	      entrada = new BufferedReader( new FileReader(archivo) );
	      String linea = null;
	      /*
	       * Empezamos a leer.  Necesitamos 3 líneas:
	       * 	Dimensiones del mapa
	       * 	Posición inicial
	       * 	Posición de destino
	       */
	      int i = 1;
	      while (i<=3){
	    	  	if( (linea = entrada.readLine()) != null){
	    	  		linea = linea.trim();
	    	  		if (linea.length() > 0 && (linea.charAt(0) != '#')) {
	    	  			System.out.println("Entramos a leer cords " + i);
	    	  			System.out.println(++lin);
	    	  			System.out.println(linea);
	    	  			System.out.println(linea.charAt(0));
	    	  			switch(i){
	    	  				case 1 : x = Integer.parseInt(linea.substring(0,linea.indexOf(',')).trim());
	    	  					 	 y = Integer.parseInt(linea.substring(linea.indexOf(',')+1).trim());
	    	  					 	 i++;
	    	  					 	 break;
	    	  					 
	    	  				case 2 : xIni = Integer.parseInt(linea.substring(0,linea.indexOf(',')).trim());
	    	  						 yIni = Integer.parseInt(linea.substring(linea.indexOf(',')+1).trim());
	    	  						 i++;
	    	  						 break;
	  					 
	    	  				case 3 : xDest = Integer.parseInt(linea.substring(0,linea.indexOf(',')).trim());
	    	  						 yDest = Integer.parseInt(linea.substring(linea.indexOf(',')+1).trim());
	    	  						 i++;
	    	  						 break;
	    	  			}
	    	  		}
	    	  	} // Introducir control de errores!!!!!
	      }
	      m = new mapa(x,y);
	      ini = new estado(xIni,yIni);
	      fin = new estado(xDest,yDest);
	      
	      
	      //Mientras queden líneas, vamos añadiendo obstaculos
	      while (( linea = entrada.readLine()) != null){
	        linea = linea.trim();
	        if (linea.length() > 0 && linea.charAt(0) != '#' ){
	        		System.out.println("Entramos a leer obst ");
	  			System.out.println(++lin);
	  			
	        		String coord = linea.substring(0,linea.lastIndexOf(',')).trim();
	        		String obs = linea.substring(linea.lastIndexOf(',')+1).trim();
	        		String obstaculo = "";
	        		int xObs,yObs;
	        		
	        		if (obs.equals("A"))
	        			obstaculo = "avion";
	        		else if (obs.equals("M"))
	        			obstaculo = "montana";
	        		else if (obs.equals("To"))
	        			obstaculo = "tormenta";
	        		else if (obs.equals("Tu"))
	        			obstaculo = "turbulencia";
	        		else if (obs.equals("V"))
	        			obstaculo = "viento";
	        		xObs = Integer.parseInt(coord.substring(0,coord.indexOf(',')).trim());
				yObs = Integer.parseInt(coord.substring(coord.indexOf(',')+1).trim());
	        		m.ponCelda(xObs, yObs, obstaculo);
	        }
	        
	      }
	    }
	    catch (FileNotFoundException ex) {
	      ex.printStackTrace();
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    finally {
	      try {
	        if (entrada!= null) {
	          //flush and close both "input" and its underlying FileReader
	          entrada.close();
	        }
	      }
	      catch (IOException ex) {
	        ex.printStackTrace();
	      }
	    }
	}

}
