package Dominio;

import Dominio.Usuario.RepositorioUsuarios;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDireccionPostal implements WithGlobalEntityManager {

    public static RepositorioDireccionPostal instancia = new RepositorioDireccionPostal();

    public static List<DireccionPostal> direccionesPostales = new ArrayList<>();

    public List<DireccionPostal> listar() {
        return entityManager()//
                .createQuery("from DireccionPostal", DireccionPostal.class) //
                .getResultList();
    }

    public DireccionPostal getById(Long id){
        return entityManager().find(DireccionPostal.class, id);
    }


    public void agregar(DireccionPostal direccionPostal) {
        entityManager().persist(direccionPostal);
    }

}
