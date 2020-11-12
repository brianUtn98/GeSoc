package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Dominio.Entidad.CategoriaDeEntidad;
import Dominio.Entidad.EntidadOrganizacional;
import Dominio.Entidad.ReglaDeCategoria;
import Dominio.Entidad.RepositorioCategorias;
import Dominio.Entidad.RepositorioReglas;
import Dominio.Mensajes.Mensaje;
import Dominio.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CategoriasController implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public ModelAndView getVistaCategorias(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        List<CategoriaDeEntidad> entidades =  RepositorioCategorias.instancia.listar();
        
        modelo.put("categorias", entidades);

        return new ModelAndView(modelo, "categorias/categorias.html.hbs");
    }

	public ModelAndView getFormularioCategoria(Request request, Response response) {
		Map<String, Object> modelo = new HashMap<>();
		
		 Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        modelo.put("error", request.queryParams().contains("error"));
        
        List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listar();
        
        modelo.put("reglas", reglas);

        return new ModelAndView(modelo, "categorias/categoria.html.hbs");
	}

	public Void altaCategoria(Request request, Response response) {
		
		 Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
		
		CategoriaDeEntidad categoriaNueva = new CategoriaDeEntidad(request.queryParams("nombre"));
		withTransaction(() ->{
			RepositorioCategorias.instancia.agregar(categoriaNueva);
		});
		response.redirect("/categorias");
		return null;
	}

	public ModelAndView getFormularioEdicionCategoria(Request request, Response response) {
		Map<String, Object> modelo = new HashMap<>();

		 Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
		
		long id = Long.parseLong(request.params(":id"));
        modelo.put("error", request.queryParams().contains("error"));
        
        List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listar();
        Optional<CategoriaDeEntidad> entidad =  RepositorioCategorias.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();

        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }

        
        modelo.put("reglas", reglas);
        modelo.put("categoria", entidad.get());

        return new ModelAndView(modelo, "categorias/categoriaEdicion.html.hbs");
	}

	public Void editarCategoria(Request request, Response response) {
		long id = Long.parseLong(request.params(":id"));
		
		 Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
		
		Optional<CategoriaDeEntidad> entidad =  RepositorioCategorias.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();

        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }
        
        CategoriaDeEntidad categoriaNueva = entidad.get();
        categoriaNueva.setNombre(request.queryParams("nombre"));
        
		withTransaction(() ->{
			RepositorioCategorias.instancia.agregar(categoriaNueva);
		});
		
		response.redirect("/categorias");
		return null;
	}

	
}
