package controllers;

import Dominio.DireccionPostal;
import Dominio.RepositorioDireccionPostal;
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
        	modelo.put("entidades", RepositorioEntidadJuridica.instancia.listar());
        	modelo.put("entidad", entidadBase.get());
        	return new ModelAndView(modelo, "entidades/editarBase.html.hbs");
        }else {
        	Optional<EntidadJuridica> entidadJuridica = Optional.empty();
        	entidadJuridica = Optional.ofNullable(RepositorioEntidadJuridica.instancia.getById(id));
        	if(entidadJuridica.isPresent()) {
        		UbicacionController ubicacionController = new UbicacionController();
        		DireccionPostal direccion = entidadJuridica.get().getDireccionPostal();
        		modelo.put("entidad", entidadJuridica.get());
        		modelo.put("direccion", direccion);
        		modelo.put("paises", UbicacionController.getPaisesFromAPI());
        		modelo.put("provincias", ubicacionController.getProvinciasFromAPI(direccion.getPais()));
        		modelo.put("ciudades", ubicacionController.getCiudadesFromAPI(direccion.getPais(), direccion.getProvincia()));
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
	
    	EntidadOrganizacional entidad;    	
    	Optional<EntidadBase> entidadBaseOpt = Optional.empty();
    	  	
        entidadBaseOpt = Optional.ofNullable(RepositorioEntidadBase.instancia.getById(id));
                        
        if(entidadBaseOpt.isPresent()) {
        	EntidadBase entidadBase = entidadBaseOpt.get();
        	EntidadJuridica entidadJuridica = RepositorioEntidadJuridica.instancia.getById(Long.parseLong(request.queryParams("entidadJuridica")));
        	entidadBase.setNombreFicticio(request.queryParams("nombreFicticio"));
        	entidadBase.setDescripcion(request.queryParams("descripcion"));
        	if(entidadJuridica != null) {
        		entidadBase.setEntidadJuridica(entidadJuridica);
        	}
        	entidad = entidadBase;
	        withTransaction(() -> {
				RepositorioEntidades.instancia.editar(entidad);
			});
        }else {
        	Optional<EntidadJuridica> entidadJuridicaOpt = Optional.empty();
        	entidadJuridicaOpt = Optional.ofNullable(RepositorioEntidadJuridica.instancia.getById(id));
        	if(entidadJuridicaOpt.isPresent()) {
        		EntidadJuridica entidadJuridica =  entidadJuridicaOpt.get();
        		entidadJuridica.setNombreFicticio(request.queryParams("nombreFicticio"));
        		entidadJuridica.setRazonSocial(request.queryParams("razonSocial"));
        		entidadJuridica.setCuit(request.queryParams("cuit"));
        		entidadJuridica.setCodigoInscripcionIGJ(request.queryParams("codigoInscripcionIGJ"));
        		
        		DireccionPostal direccionPostal = entidadJuridica.getDireccionPostal();
        		
        		direccionPostal.setDireccion(request.queryParams("direccion"));
        		direccionPostal.setPais(request.queryParams("pais"));
        		direccionPostal.setProvincia(request.queryParams("provincia"));
        		direccionPostal.setCiudad(request.queryParams("ciudad"));

        		entidad = entidadJuridica;
        		withTransaction(() -> {
        			RepositorioDireccionPostal.instancia.editar(direccionPostal);
    				RepositorioEntidades.instancia.editar(entidad);
    			});
        	}
        }
        
        response.redirect("/entidades");
        
        return null;
    }
    
}
