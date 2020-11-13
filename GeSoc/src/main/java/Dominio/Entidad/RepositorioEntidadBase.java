package Dominio.Entidad;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEntidadBase implements WithGlobalEntityManager {

    public static RepositorioEntidadBase instancia = new RepositorioEntidadBase();

    public static List<EntidadBase> entidadesBases = new ArrayList<>();

    public List<EntidadBase> listar() {
        return entityManager()//
                .createQuery("from EntidadBase", EntidadBase.class) //
                .getResultList();
    }

    public EntidadBase getById(Long id){
        return entityManager().find(EntidadBase.class, id);
    }
}
