package ui;

import javax.swing.*;
import modelo.*;
import java.util.*;
import org.openstreetmap.gui.jmapviewer.*;

public class MainWindow {

    private JFrame frame;
    private Grafo grafo = new Grafo();
    private JMapViewer map;

    public MainWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        map = new JMapViewer();
        map.setBounds(10, 10, 760, 400);
        frame.getContentPane().add(map);

        JButton btnAgregar = new JButton("Agregar Localidad");
        btnAgregar.setBounds(10, 420, 200, 30);
        frame.getContentPane().add(btnAgregar);

        JButton btnCalcular = new JButton("Calcular MST");
        btnCalcular.setBounds(220, 420, 200, 30);
        frame.getContentPane().add(btnCalcular);

        btnAgregar.addActionListener(e -> agregarLocalidad());
        btnCalcular.addActionListener(e -> calcularMST());
    }

    private void agregarLocalidad() {
    	Localidad a = new Localidad("Nuevo", "ciudad", -44.6, -48.4);
        Localidad b = new Localidad("Ciudad", "Buenos Aires", -34.6, -58.4);
        Localidad c = new Localidad("Nuevo loc2", "ciudad", -22.6, -58.4);
        Localidad e = new Localidad("Ciudad", "Buenos Aires", -39.6, -78.4);
        Localidad r = new Localidad("Ciudad", "Buenos Aires", -28.6, -78.4);
        grafo.agregarLocalidad(b);
        grafo.agregarLocalidad(a);
        grafo.agregarLocalidad(e);
        grafo.agregarLocalidad(c);
        grafo.agregarLocalidad(r);
        MapMarkerDot marker = new MapMarkerDot(b.getLatitud(), b.getLongitud());
        MapMarkerDot marker2 = new MapMarkerDot(a.getLatitud(), a.getLongitud());
        MapMarkerDot marker3 = new MapMarkerDot(e.getLatitud(), e.getLongitud());
        MapMarkerDot marker4 = new MapMarkerDot(c.getLatitud(), c.getLongitud());
        MapMarkerDot marker5 = new MapMarkerDot(r.getLatitud(), r.getLongitud());
        marker.setName(b.getNombre());
        marker2.setName(e.getNombre());
        marker3.setName(a.getNombre());
        marker4.setName(c.getNombre());
        marker5.setName(r.getNombre());
        map.addMapMarker(marker);
        map.addMapMarker(marker2);
        map.addMapMarker(marker3);
        map.addMapMarker(marker4);
        map.addMapMarker(marker5);
    }

    private void calcularMST() {

        CalculadorCosto calc = new CalculadorCosto(10, 0.1, 100);
        grafo.generarGrafoCompleto(calc);

        KruskalMST kruskal = new KruskalMST();
        List<Conexion> mst = kruskal.calcularMST(
                grafo.getLocalidades(),
                grafo.getConexiones()
        );

        for (Conexion c : mst) {
            List<Coordinate> coords = new ArrayList<>();
            coords.add(new Coordinate(c.getOrigen().getLatitud(), c.getOrigen().getLongitud()));
            coords.add(new Coordinate(c.getDestino().getLatitud(), c.getDestino().getLongitud()));
            coords.add(new Coordinate(c.getDestino().getLatitud(), c.getDestino().getLongitud()));
           

            MapPolygonImpl linea = new MapPolygonImpl(coords);
            map.addMapPolygon(linea);
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
