package Dominio.Usuario;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuarios implements WithGlobalEntityManager {
    public static RepositorioUsuarios instancia = new RepositorioUsuarios();

    public static List<Usuario> usuariosLogueados = new ArrayList<>();

    public List<Usuario> listar() {
        return entityManager()//
                .createQuery("from Usuario", Usuario.class) //
                .getResultList();
    }

    public Usuario getById(Long id){
        return entityManager().find(Usuario.class, id);
    }


    public void agregar(Usuario usuario) {
        entityManager().persist(usuario);
    }
}
