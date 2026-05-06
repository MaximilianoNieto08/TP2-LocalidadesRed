package modelo;

public class CalculadorCosto {

    private double costoPorKm;
    private double porcentajeExtra;
    private double costoInterprovincial;

    public CalculadorCosto(double costoPorKm, double porcentajeExtra, double costoInterprovincial) {
        this.costoPorKm = costoPorKm;
        this.porcentajeExtra = porcentajeExtra;
        this.costoInterprovincial = costoInterprovincial;
    }

    public double calcularCosto(Localidad a, Localidad b) {
        double distancia = distancia(a, b);
        double costo = distancia * costoPorKm;

        if (distancia > 300) {
            costo += costo * porcentajeExtra;
        }

        if (!a.getProvincia().equals(b.getProvincia())) {
            costo += costoInterprovincial;
        }

        return costo;
    }

    private double distancia(Localidad a, Localidad b) {
        final int R = 6371;

        double lat1 = Math.toRadians(a.getLatitud());
        double lon1 = Math.toRadians(a.getLongitud());
        double lat2 = Math.toRadians(b.getLatitud());
        double lon2 = Math.toRadians(b.getLongitud());

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double h = Math.sin(dlat/2) * Math.sin(dlat/2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dlon/2) * Math.sin(dlon/2);

        double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1-h));

        return R * c;
    }
}
