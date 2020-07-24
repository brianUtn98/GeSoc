package Dominio.Entidad;

public class ImpedirQueEntidadBaseSeaParteDeJuridica extends ReglaDeCategoria {
    public boolean puedeSerParteDeEntidadJuridica() {
        return false;
    }
}
