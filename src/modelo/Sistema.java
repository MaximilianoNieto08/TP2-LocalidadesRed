package modelo;

import java.util.List;

public class Sistema {

	private Grafo grafo = new Grafo();
	
	public void ingresarLocalidad() {
		
	}
	
	public List<Conexion> calcularMST() {
		KruskalMST kruskal = new KruskalMST();
        List<Conexion> mst = kruskal.calcularMST(grafo.getLocalidades(), grafo.getConexiones());
        return mst;
	}
	public double calcularCosto() {
		return 0.0;
	}
	
	
	
}
