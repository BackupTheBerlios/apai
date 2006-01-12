package algoritmos;

	/*
	 * Los valores de heuristica son
	 * manhattan
	 * celdas
	 * manhattanCeldas
	 */
public interface algoritmoInformado extends algoritmo {
	public String getHeuristica();
	public void setHeuristica(String heuristica);
}