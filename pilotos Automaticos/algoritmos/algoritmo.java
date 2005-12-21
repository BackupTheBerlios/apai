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
		public void mostrarCamino();
		//public boolean comprobarObjetivo(estado eo);
		public void resolver(estado ei, estado ef, mapa m);
		public void generarSucesor(estado e, mapa m);
}
