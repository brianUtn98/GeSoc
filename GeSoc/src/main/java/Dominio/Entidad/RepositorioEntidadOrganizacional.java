package Dominio.Entidad;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadOrganizacional implements WithGlobalEntityManager {
    public static RepositorioEntidadOrganizacional instancia = new RepositorioEntidadOrganizacional();

    public static List<EntidadOrganizacional> entidadesOrganizacionales = new ArrayList<>();

    public List<EntidadOrganizacional> listar() {
        return entityManager()//
                .createQuery("from EntidadOrganizacional order by categoria", EntidadOrganizacional.class) //
                .getResultList();
    }

    public EntidadOrganizacional getById(Long id){
        return entityManager().find(EntidadOrganizacional.class, id);
    }

}
