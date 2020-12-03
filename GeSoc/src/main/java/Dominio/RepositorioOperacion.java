package Dominio;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioOperacion implements WithGlobalEntityManager {
    public static RepositorioOperacion instancia = new RepositorioOperacion();

    public List<Operacion> listar() {
        return entityManager()//
                .createQuery("from Operacion", Operacion.class) //
                .getResultList();
    }

    public Operacion getById(Long id){
        return entityManager().find(Operacion.class, id);
    }


    public void agregar(Operacion operacion) {
        operacion.getEtiquetas().forEach(etiqueta -> entityManager().persist(etiqueta));
        operacion.getDetalle().forEach(item -> entityManager().persist(item));
        entityManager().persist(operacion.getMedioPago());
        entityManager().persist(operacion);
    }
}
