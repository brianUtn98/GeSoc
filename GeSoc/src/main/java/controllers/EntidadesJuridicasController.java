package controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.DireccionPostal;
import Dominio.RepositorioDireccionPostal;
import Dominio.Entidad.EntidadBase;
import Dominio.Entidad.EntidadJuridica;
import Dominio.Entidad.EntidadOrganizacional;
import Dominio.Entidad.RepositorioEntidades;
import Dominio.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class EntidadesJuridicasController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	public ModelAndView getFormularioNewEntidadJuridica(Request request, Response response) {
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
            
        modelo.put("paises", UbicacionController.getPaisesFromAPI());
        
		return new ModelAndView(modelo, "entidades/newJuridica.html.hbs");
	}
	
	public Void postNewEntidadJuridica(Request request, Response response){
		String nombre = request.queryParams("nombreFicticio");
		
		if(nombre == null) {	        
	        response.redirect("/entidadJuridica?error");	        
			return null;
		}
		
		DireccionPostal direccion = new DireccionPostal(
					request.queryParams("direccion"),
					request.queryParams("pais"),
					request.queryParams("provincia"),
					request.queryParams("cuidad")
				);
		
		withTransaction(() -> {
			RepositorioDireccionPostal.instancia.agregar(direccion);
		});
		
		EntidadOrganizacional entidadJuridica = new EntidadJuridica(
				nombre,
				request.queryParams("razonSocial"),
				request.queryParams("cuit"),
				direccion,
				request.queryParams("codigoInscripcionIGJ")
				);
		
		withTransaction(() -> {
				RepositorioEntidades.instancia.agregar(entidadJuridica);
			}
		);
		
		
		response.redirect("entidades");
		return null;
	}
}
