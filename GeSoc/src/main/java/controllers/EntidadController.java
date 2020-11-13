package controllers;

import Dominio.Entidad.*;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EntidadController implements WithGlobalEntityManager, TransactionalOps {



    public ModelAndView mostrarEntidadCategoria(Request request, Response response){
        String categoriaBuscada = request.queryParams("filtro");

        Map<String, Object> parametros = new HashMap<String, Object>();

        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }

        parametros.put("usuario", usuarioLogueado.get());

        List<EntidadOrganizacional> entidadesOrganizacionales =
                categoriaBuscada != null ?
                        RepositorioEntidadOrganizacional.instancia.buscarPorCategoria(categoriaBuscada):
                        RepositorioEntidadOrganizacional.instancia.listar();;

        parametros.put("entidades", entidadesOrganizacionales);
        /*"entidadesCategoria.html.hbs"*//*"categorias.html.hbs"*/
        return new ModelAndView(parametros,"categoriasBuscar.html.hbs");
    }

    public ModelAndView getEditEntidad(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<String, Object>();
		
        Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

        if(!usuarioLogueado.isPresent()) {
            response.redirect("/login");
            return null;
        }else {
            modelo.put("usuario", usuarioLogueado.get());
        }
		
        
        long id = Long.parseLong(request.params(":id"));

        Optional<EntidadBase> entidadBase = Optional.empty();
                
        entidadBase = Optional.ofNullable(RepositorioEntidadBase.instancia.getById(id));
                        
        if(entidadBase.isPresent()) {
        	modelo.put("entidad", entidadBase.get());
        	return new ModelAndView(modelo, "entidades/editarBase.html.hbs");
        }else {
        	Optional<EntidadJuridica> entidadJuridica = Optional.empty();
        	entidadJuridica = Optional.ofNullable(RepositorioEntidadJuridica.instancia.getById(id));
        	if(entidadJuridica.isPresent()) {
        		modelo.put("entidad", entidadJuridica.get());
            	return new ModelAndView(modelo, "entidades/editarJuridica.html.hbs");
        	}else {
        		response.redirect("/entidades");
        		return null;
        	}
        }
    }
    
    public Void postEditEntidad(Request request, Response response) {
    	long id = Long.parseLong(request.params(":id"));
		
    	Optional<Usuario> usuarioLogueado = UsuariosController.getUsuarioLogueado(request);

    	if(!usuarioLogueado.isPresent()) {
           response.redirect("/login");
           return null;
        }
	
    	Optional<EntidadBase> entidadBase = Optional.empty();
        
        entidadBase = Optional.ofNullable(RepositorioEntidadBase.instancia.getById(id));
                        
        if(entidadBase.isPresent()) {
        	EntidadBase entidad = entidadBase.get();
        	entidad.setNombreFicticio(request.queryParams("nombreFicticio"));
        	entidad.setDescripcion(request.queryParams("descripcion"));
        }else {
        	Optional<EntidadJuridica> entidadJuridica = Optional.empty();
        	entidadJuridica = Optional.ofNullable(RepositorioEntidadJuridica.instancia.getById(id));
        	if(entidadJuridica.isPresent()) {
        		
        	}
        }
        return null;
    }
    
}
