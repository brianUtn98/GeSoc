package Dominio;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProveedor implements WithGlobalEntityManager {
    public static RepositorioProveedor instancia = new RepositorioProveedor();

    public List<Provedor> listar() {
        return entityManager()//
                .createQuery("from Provedor", Provedor.class) //
                .getResultList();
    }

    public Provedor getById(Long id){
        return entityManager().find(Provedor.class, id);
    }


    public void agregar(Provedor provedor) {
        entityManager().persist(provedor);
    }
}
