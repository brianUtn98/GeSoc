package Dominio;

public class Empresa implements CategoriaDeEntidad {
    private TipoEmpresa tipo;

    public Empresa(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public TipoEmpresa getTipo() {
        return tipo;
    }
}
