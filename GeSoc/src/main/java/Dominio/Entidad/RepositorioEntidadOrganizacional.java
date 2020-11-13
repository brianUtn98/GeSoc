package Dominio.Entidad;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadOrganizacional implements WithGlobalEntityManager {
    public static RepositorioEntidadOrganizacional instancia = new RepositorioEntidadOrganizacional();

    public static List<EntidadOrganizacional> entidadesOrganizacionales = new ArrayList<>();

    public List<EntidadOrganizacional> listar() {
        return entityManager()//
                .createQuery("from EntidadOrganizacional order by categoria.Nombre", EntidadOrganizacional.class)//
                .getResultList();
    }

    public List<EntidadOrganizacional> buscarPorCategoria(String nombre){
        return entityManager().createQuery("from EntidadOrganizacional c where c.categoria.Nombre like :nombre",EntidadOrganizacional.class)
                .setParameter("nombre", "%" + nombre + "%") //
                .getResultList();
    }


    public EntidadOrganizacional getByCategoria(CategoriaDeEntidad categoriaDeEntidad){
        return entityManager().find(EntidadOrganizacional.class, categoriaDeEntidad.getNombre());
    }

    public EntidadOrganizacional getById(Long id){
        return entityManager().find(EntidadOrganizacional.class, id);
    }

}
