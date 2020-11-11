package Dominio.Entidad;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadJuridica<E extends EntidadOrganizacional> implements WithGlobalEntityManager {

    public static RepositorioEntidadJuridica<EntidadOrganizacional> instancia = new RepositorioEntidadJuridica<EntidadOrganizacional>();

    public static List<EntidadJuridica> entidadesJuridicas = new ArrayList<>();

    public List<EntidadJuridica> listar() {
        return entityManager()//
                .createQuery("from EntidadJuridica", EntidadJuridica.class) //
                .getResultList();
    }

    public EntidadJuridica getById(Long id){
        return entityManager().find(EntidadJuridica.class, id);
    }

}
