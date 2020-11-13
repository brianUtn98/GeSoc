package controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.Entidad.CategoriaDeEntidad;
import Dominio.Entidad.EntidadBase;
import Dominio.Entidad.EntidadOrganizacional;
import Dominio.Entidad.RepositorioEntidades;
import Dominio.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EntidadesBaseController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	public ModelAndView getFormularioNewEntidadBase(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();
        
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
        else
        	modelo.put("usuario", usuarioLogueado.get());
        
        if(request.queryParams().contains("error")) {
        	modelo.put("errorRequired", "El campo Nombre Ficticio es requerido");
        }
        
		return new ModelAndView(modelo, "entidades/newBase.html.hbs");
	}
	
	public Void postNewEntidadBase(Request request, Response response){
		String nombre = request.queryParams("nombreFicticio");
		
		if(nombre == null) {	        
	        response.redirect("/entidadBase?error");	        
			return null;
		}
		
		EntidadOrganizacional entidadBase = new EntidadBase(
				nombre,
				request.queryParams("descripcion"));
		
		withTransaction(() -> {
				RepositorioEntidades.instancia.agregar(entidadBase);
			}
		);
		
		
		response.redirect("entidades");
		return null;
	}
}
