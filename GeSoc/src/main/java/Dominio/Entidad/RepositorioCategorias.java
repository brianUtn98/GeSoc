package Dominio.Entidad;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioCategorias  implements WithGlobalEntityManager {
	  public static RepositorioCategorias instancia = new RepositorioCategorias();

	    public List<CategoriaDeEntidad> listar() {
	        return entityManager()//
	                .createQuery("from CategoriaDeEntidad", CategoriaDeEntidad.class) //
	                .getResultList();
	    }

	    public CategoriaDeEntidad getById(Long id){
	        return entityManager().find(CategoriaDeEntidad.class, id);
	    }


	    public void agregar(CategoriaDeEntidad entidad) {
	        entityManager().persist(entidad);
	    }
}
