package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import spark.QueryParamsMap;
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
        modelo.put("usuario", usuarioLogueado.get());

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
        
        List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();

        modelo.put("reglas", reglas);
        modelo.put("usuario", usuarioLogueado.get());

        return new ModelAndView(modelo, "categorias/categoria.html.hbs");
	}

	public Void altaCategoria(Request request, Response response) {
		
		Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
        
        String nombre = request.queryParams("nombre");		
		if(nombre == null || nombre == "") {	        
	        response.redirect("/categoria?error");	        
			return null;
		}
		
		CategoriaDeEntidad categoriaNueva = new CategoriaDeEntidad(request.queryParams("nombre"));
		List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();
		Optional<ReglaDeCategoria> reglaSeleccionada = reglas.stream().filter(reg -> reg.getTipo().equals(request.queryParams("reglas"))).findFirst();
		
		withTransaction(() ->{
			if(reglaSeleccionada.isPresent()) {
				ReglaDeCategoria reglaAAplicar =reglaSeleccionada.get();
				RepositorioReglas.instancia.agregar(reglaAAplicar);
				categoriaNueva.agregarRegla(reglaAAplicar);	
			}
			
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
		
        modelo.put("error", request.queryParams().contains("error"));
        
		long id = Long.parseLong(request.params(":id"));        
        Optional<CategoriaDeEntidad> entidad =  RepositorioCategorias.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();

        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }
      
        modelo.put("categoria", entidad.get());
        modelo.put("usuario", usuarioLogueado.get());

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
        
        String nombre = request.queryParams("nombre");		
		if(nombre == null || nombre == "") {	        
	        response.redirect("/categoria/"+id+"?error");	        
			return null;
		}
		categoriaNueva.setNombre(nombre);
        
		withTransaction(() ->{
			RepositorioCategorias.instancia.agregar(categoriaNueva);
		});
		
		response.redirect("/categorias");
		return null;
	}

	public ModelAndView getFormAgrearReglaACategoria(Request request, Response response) {
		Map<String, Object> modelo = new HashMap<>();
		
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
       
       modelo.put("error", request.queryParams().contains("error"));
       
       List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();

       modelo.put("reglas", reglas);
       modelo.put("usuario", usuarioLogueado.get());
       modelo.put("categoria", entidad.get());

       return new ModelAndView(modelo, "categorias/categoriaAgregarRegla.html.hbs");
	}

	public Void agregarReglaACategoria(Request request, Response response) {
		Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

		long id = Long.parseLong(request.params(":id"));
		
        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }
        
        Optional<CategoriaDeEntidad> entidad =  RepositorioCategorias.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();
		
        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }		
        
		List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();
		Optional<ReglaDeCategoria> reglaSeleccionada = reglas.stream().filter(reg -> reg.getTipo().equals(request.queryParams("reglas"))).findFirst();
		
		
		if(entidad.get().getReglas().stream().anyMatch(reg -> reg.getTipo().equals(request.queryParams("reglas")))) {	        
	        response.redirect("/categoria/"+entidad.get().getId()+"/regla?error");	        
			return null;
		}
		
		CategoriaDeEntidad categoriaEnEdicion = entidad.get();
		withTransaction(() ->{
			if(reglaSeleccionada.isPresent()) {
				ReglaDeCategoria reglaAAplicar =reglaSeleccionada.get();
				RepositorioReglas.instancia.agregar(reglaAAplicar);
				categoriaEnEdicion.agregarRegla(reglaAAplicar);	
			}
			
			RepositorioCategorias.instancia.agregar(categoriaEnEdicion);
		});
		response.redirect("/categorias");
		return null;
	}

	
}
