package modelo;

import java.util.*;

public class KruskalMST {

    public List<Conexion> calcularMST(List<Localidad> nodos, List<Conexion> aristas) {

        Collections.sort(aristas);
        UnionFind uf = new UnionFind(nodos.size());

        List<Conexion> resultado = new ArrayList<>();

        for (Conexion c : aristas) {
            int i = nodos.indexOf(c.getOrigen());
            int j = nodos.indexOf(c.getDestino());

            if (uf.find(i) != uf.find(j)) {
                uf.union(i, j);
                resultado.add(c);
            }
        }

        return resultado;
    }
}
