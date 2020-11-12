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

       /* Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }*/

        List<CategoriaDeEntidad> entidades =  RepositorioCategorias.instancia.listar();
        
        modelo.put("categorias", entidades);

        return new ModelAndView(modelo, "categorias/categorias.html.hbs");
    }

	public ModelAndView getFormularioCategoria(Request request, Response response) {
		Map<String, Object> modelo = new HashMap<>();
		
		/* Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }*/

        modelo.put("error", request.queryParams().contains("error"));
        
        List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();
        reglas.stream().findFirst().get().getNombreRegla();
        modelo.put("reglas", reglas);

        return new ModelAndView(modelo, "categorias/categoria.html.hbs");
	}

	public Void altaCategoria(Request request, Response response) {
		
		/* Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }*/
		
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

		/* Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }*/
		
		long id = Long.parseLong(request.params(":id"));
        modelo.put("error", request.queryParams().contains("error"));
        
        List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();
        Optional<CategoriaDeEntidad> entidad =  RepositorioCategorias.instancia.listar().stream().filter(cat -> cat.getId() == id).findFirst();


        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }

        Optional<ReglaDeCategoria> reglaSeleccionada = entidad.get().getReglas().stream().findFirst();
        if (reglaSeleccionada.isPresent()) {
        	reglas = reglas.stream().filter(reg -> !reg.getTipo().equals(reglaSeleccionada.get().getTipo())).collect(Collectors.toList());
        	modelo.put("reglaSeleccionada", reglaSeleccionada.get());
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
		List<ReglaDeCategoria> reglas =  RepositorioReglas.instancia.listarEnMemoria();
		Optional<ReglaDeCategoria> reglaSeleccionada = reglas.stream().filter(reg -> reg.getTipo().equals(request.queryParams("reglas"))).findFirst();
		

        if(!entidad.isPresent()) {
            response.redirect("/categorias");
            return null;
        }
        
        //me fijo si la regla que tengo es la misma que la me pasaron 
        Optional<ReglaDeCategoria> reglaPrevia = entidad.get().getReglas().stream().filter(reg -> !reg.getTipo().equals(request.queryParams("reglas"))).findFirst();	
        boolean reglaYaEnListado = entidad.get().getReglas().stream().anyMatch(reg -> reg.getTipo().equals(request.queryParams("reglas")));
        
        CategoriaDeEntidad categoriaNueva = entidad.get();
        categoriaNueva.setNombre(request.queryParams("nombre"));
        
		withTransaction(() ->{
			//si hay una regla anterior diferente a la que me pasan la quito
			if(reglaPrevia.isPresent()) 
				entidad.get().quitarRegla(reglaPrevia.get());
			//si la regla nueva y no esta en el listado la agrego
			if(reglaSeleccionada.isPresent() && !reglaYaEnListado ) {
					ReglaDeCategoria reglaAAplicar =reglaSeleccionada.get();
					RepositorioReglas.instancia.agregar(reglaAAplicar);
					categoriaNueva.agregarRegla(reglaAAplicar);	
			}
			
			
			RepositorioCategorias.instancia.agregar(categoriaNueva);
		});
		
		response.redirect("/categorias");
		return null;
	}

	
}
