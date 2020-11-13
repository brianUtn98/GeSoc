package Dominio.Entidad;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriaDeEntidad  implements WithGlobalEntityManager {
    public static RepositorioCategoriaDeEntidad instancia = new RepositorioCategoriaDeEntidad();

    public static List<CategoriaDeEntidad> categoriasDeEntidad = new ArrayList<>();

    public List<CategoriaDeEntidad> listar() {
        return entityManager()//
                .createQuery("from CategoriaDeEntidad", CategoriaDeEntidad.class) //
                .getResultList();

    }

    public CategoriaDeEntidad getById(Long id){
        return entityManager().find(CategoriaDeEntidad.class, id);
    }


    public void agregar(CategoriaDeEntidad categoriaDeEntidad) {
        entityManager().persist(categoriaDeEntidad);
    }
}
