package algoritmos;

import java.util.Vector;
import mapa.mapa;

public interface algoritmo {
		public Vector getAbiertos();
		public void setAbiertos(Vector abiertos);
		public estado getInicial();
		public void setInicial(estado inicial);
		public estado getObjetivo();
		public void setObjetivo(estado objetivo);
		public Vector getCerrados();
		public void setCamino(Vector camino);
		public Vector getCamino();
		public void setCerrados(Vector cerrados);
		public void resolver(mapa m);
		public boolean generarSucesor(estado e, mapa m, String s);
		public void mostrar(Vector v);
}
