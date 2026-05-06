package modelo;

import java.util.*;

public class Grafo {

    private List<Localidad> localidades = new ArrayList<>();
    private List<Conexion> conexiones = new ArrayList<>();

    public void agregarLocalidad(Localidad l) {
        localidades.add(l);
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public List<Conexion> getConexiones() {
        return conexiones;
    }

    public void generarGrafoCompleto(CalculadorCosto calc) {
        conexiones.clear();

        for (int i = 0; i < localidades.size(); i++) {
            for (int j = i + 1; j < localidades.size(); j++) {
                Localidad a = localidades.get(i);
                Localidad b = localidades.get(j);

                double costo = calc.calcularCosto(a, b);
                conexiones.add(new Conexion(a, b, costo));
            }
        }
    }
}
