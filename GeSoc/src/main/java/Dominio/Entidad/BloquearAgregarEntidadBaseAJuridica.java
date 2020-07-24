package Dominio.Entidad;

public class BloquearAgregarEntidadBaseAJuridica extends ReglaDeCategoria {
    public boolean sePuedeAgregarEntidadBase() {
        return false;
    }
}
