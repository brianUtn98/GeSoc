package controllers;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.Entidad.CategoriaDeEntidad;
import Dominio.Entidad.EntidadOrganizacional;
import Dominio.Entidad.RepositorioCategorias;
import Dominio.Entidad.RepositorioEntidades;
import Dominio.Mensajes.Mensaje;
import Dominio.Usuario.RepositorioUsuarios;
import Dominio.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;

public class EntidadesController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
	
	public ModelAndView getVistaEntidades(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        List<EntidadOrganizacional> entidades =  RepositorioEntidades.instancia.listar();
        
        /*modelo.put("cantidad", bandeja.size());
        modelo.put("unread", bandeja.stream().filter(msg -> !msg.isLeido()).count());
        modelo.put("mensajes", bandeja);*/
        modelo.put("entidades", entidades);

        return new ModelAndView(modelo, "entidades.html.hbs");
    }
	
	public ModelAndView getFormularioSeleccionCategoria(Request request, Response response) {
		Map<String, Object> modelo = new HashMap<>();

		Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
		
		long id = Long.parseLong(request.params(":id"));

        modelo.put("error", request.queryParams().contains("error"));
        
        List<CategoriaDeEntidad> categorias =  RepositorioCategorias.instancia.listar();
        Optional<EntidadOrganizacional> entidad =  RepositorioEntidades.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();

        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }

        
        modelo.put("categorias", categorias);
        modelo.put("entidad", entidad.get());

        return new ModelAndView(modelo, "entidades/asignarCategoria.html.hbs");
	}

	public Void asignarCategoria(Request request, Response response) {		

		Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
		
		long cat_id = Long.parseLong(request.queryParams("cat_id"));
		long id = Long.parseLong(request.params(":id"));
		
		Optional<EntidadOrganizacional> entidad =  RepositorioEntidades.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();

        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }
        
        Optional<CategoriaDeEntidad> categoria =  RepositorioCategorias.instancia.listar().stream().filter(cat -> cat.getId() == cat_id).findFirst();

        if(!categoria.isPresent()) {
            response.redirect("/categorias");
            return null;
        }
		
        EntidadOrganizacional entidadNueva = entidad.get();
        entidadNueva.setCategoria(categoria.get());
        
		withTransaction(() ->{
			RepositorioEntidades.instancia.agregar(entidadNueva);
		});
		
		response.redirect("/entidades");
		return null;
	}
}