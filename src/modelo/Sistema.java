package modelo;

import java.util.List;


public class Sistema {

	private Grafo grafo = new Grafo();
	
	public void ingresarLocalidad(String nombre, String provincia, double latitud, double longitud) {
		
		Localidad loc= new Localidad(nombre, provincia, latitud, longitud);
		this.grafo.agregarLocalidad(loc);
		
	}
	
	public List<Conexion> calcularCosto(double costoPorKm, double porcentajeExtra, double costoInterprovincial){
		CalculadorCosto calculo = new CalculadorCosto(costoPorKm, porcentajeExtra, costoInterprovincial);
        grafo.generarGrafoCompleto(calculo);
        
		List <Conexion> mst = this.calcularMST();
		
		return mst;
		
		
		
	}
	private List<Conexion> calcularMST() {
		KruskalMST kruskal = new KruskalMST();
        List<Conexion> mst = kruskal.calcularMST(grafo.getLocalidades(), grafo.getConexiones());
        return mst;
	}
	
	
	
	
}
