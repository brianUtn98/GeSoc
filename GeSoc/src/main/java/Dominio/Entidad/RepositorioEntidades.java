package Dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import Dominio.Usuario.RepositorioUsuarios;
import Dominio.Usuario.Usuario;

public class RepositorioEntidades  implements WithGlobalEntityManager {
	  public static RepositorioEntidades instancia = new RepositorioEntidades();

	    public List<EntidadOrganizacional> listar() {
	        return entityManager()//
	                .createQuery("from EntidadOrganizacional", EntidadOrganizacional.class) //
	                .getResultList();
	    }

	    public EntidadOrganizacional getById(Long id){
	        return entityManager().find(EntidadOrganizacional.class, id);
	    }


	    public void agregar(EntidadOrganizacional entidad) {
	        entityManager().persist(entidad);
	    }
}
