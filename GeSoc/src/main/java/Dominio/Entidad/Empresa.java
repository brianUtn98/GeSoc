package Dominio.Entidad;

public class Empresa implements TipoDeEntidad {
    private TipoEmpresa tipo;

    public Empresa(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }
}
